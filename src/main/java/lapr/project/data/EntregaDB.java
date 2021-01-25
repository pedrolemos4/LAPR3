package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Caminho;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Graph;
import lapr.project.model.GraphAlgorithms;
import lapr.project.model.Parque;
import lapr.project.model.Veiculo;
import lapr.project.utils.CalculosFisica;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author beatr
 */
public class EntregaDB extends DataHandler {

    private static final String DRONE = "drone";
    private static final String SCOOTER = "scooter";
    private final ParqueDB parqueDB = new ParqueDB();
    private final CaminhoDB caminhoDB = new CaminhoDB();
    private final FarmaciaDB far = new FarmaciaDB();

    /**
     * Adiciona uma entrega à base de dados
     *
     * @param entrega entrega a adicionar
     * @return id da entrega
     * @throws SQLException
     * @throws ParseException
     */
    public int addEntrega(Entrega entrega) throws SQLException, ParseException {

        int id = 0;

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call AddEntrega(?,?,?,?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            java.util.Date date = sdf1.parse(entrega.getDataInicio());
            java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());
            callStmt.setTimestamp(4, sqlStartDate);
            java.util.Date date1 = sdf1.parse(entrega.getDataFim());
            java.sql.Timestamp sqlEndDate = new java.sql.Timestamp(date1.getTime());
            callStmt.setTimestamp(5, sqlEndDate);
            callStmt.setInt(3, entrega.getIdVeiculo());
            callStmt.setInt(2, entrega.getidEstafeta());
            callStmt.setDouble(6, entrega.getPesoEntrega());
            callStmt.execute();
            id = callStmt.getInt(1);
            try {

                closeAll();

            } catch (NullPointerException ex) {

                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }

        return id;
    }

    /**
     * Adiciona uma encomenda à entrega
     *
     * @param e entrega onde se vão adicionar as encomendas
     * @param enc encomenda a adicionar
     * @return true se for adicionada com sucesso, falso se não
     * @throws SQLException
     */
    public boolean addEncomendaEntrega(Entrega e, Encomenda enc) throws SQLException {
        boolean flag = false;

        try (CallableStatement callSmt = getConnection().prepareCall("{ call AddEncomendaEntrega(?,?) }")) {
            callSmt.setInt(1, e.getIdEntrega());
            callSmt.setInt(2, enc.getId());

            callSmt.execute();
            flag = true;
            try {
                closeAll();
            } catch (NullPointerException ex) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
        return flag;

    }

    /**
     * Gera o grafo da scooter com os endereços existentes como vértices
     *
     * @param listEnderecos lista de endereços
     * @param listEnderecosEncomenda
     * @param est estafeta
     * @param veiculo veículo
     * @param pesoTotalEntrega peso total de entrega
     * @return grafo com os endereços e as ruas definidas
     */
    public Graph<Endereco, Double> generateGraphScooter(List<Endereco> listEnderecos, List<Endereco> listEnderecosEncomenda, Estafeta est, Veiculo veiculo, double pesoTotalEntrega) {
        Graph<Endereco, Double> graph = new Graph<>(true);

        double energiaGasta = 0;

        for (Endereco e : listEnderecos) {
            graph.insertVertex(e);
        }

        for (Endereco e : graph.vertices()) {
            for (Endereco end : graph.vertices()) {
                if (caminhoDB.getCaminhoByEnderecos(end.getMorada(), e.getMorada()) != null) {
                    energiaGasta = CalculosFisica.calculoEnergiaScooter(est.getPesoEstafeta(), veiculo.getPesoVeiculo(), veiculo.getAreaFrontal(),
                            pesoTotalEntrega, end, e, caminhoDB.getCaminhoByEnderecos(end.getMorada(), e.getMorada()).getRoadResistanceCoefficient(),
                            caminhoDB.getCaminhoByEnderecos(end.getMorada(), e.getMorada()).getDirecaoVento(),
                            caminhoDB.getCaminhoByEnderecos(end.getMorada(), e.getMorada()).getVelocidadeVento());
                    graph.insertEdge(end, e, 1.0, energiaGasta);
                    if (listEnderecosEncomenda.contains(end)) {
                        pesoTotalEntrega = pesoTotalEntrega - getEncomendaByMorada(end.getMorada()).getPesoEncomenda();
                        listEnderecosEncomenda.remove(e);
                    }
                }
            }
        }
        return graph;
    }

    /**
     * Gera o grafo do drone com os endereços existentes como vértices
     *
     * @param listEnderecos lista de endereços
     * @param listEnderecosEncomenda lista dos endereços das encomendas
     * realizadas
     * @param est estafeta
     * @param veiculo veículo
     * @param atributo atributo
     * @param pesoTotalEntrega peso total de entrega
     * @return grafo com os endereços e as ruas definidas
     */
    public Graph<Endereco, Double> generateGraphDrone(List<Endereco> listEnderecos, List<Endereco> listEnderecosEncomenda, Estafeta est, Veiculo veiculo, double atributo, double pesoTotalEntrega) {
        Graph<Endereco, Double> graph = new Graph<>(true);
        double energiaGasta = 0;

        for (Endereco e : listEnderecos) {
            graph.insertVertex(e);
        }

        for (Endereco e : graph.vertices()) {
            for (Endereco endereco : graph.vertices()) {
                if (caminhoDB.getCaminhoByEnderecos(endereco.getMorada(), e.getMorada()) != null) {
                    energiaGasta = CalculosFisica.calculoEnergiaDrone(veiculo.getPesoVeiculo(), atributo, veiculo.getAreaFrontal(),
                            pesoTotalEntrega, endereco, e, caminhoDB.getCaminhoByEnderecos(endereco.getMorada(), e.getMorada()).getDirecaoVento(),
                            caminhoDB.getCaminhoByEnderecos(endereco.getMorada(), e.getMorada()).getVelocidadeVento());
                    graph.insertEdge(endereco, e, 1.0, energiaGasta);

                    if (listEnderecosEncomenda.contains(e)) {
                        pesoTotalEntrega = pesoTotalEntrega - getEncomendaByMorada(e.getMorada()).getPesoEncomenda();
                        listEnderecosEncomenda.remove(e);
                    }
                }

            }
        }
        return graph;
    }

    /**
     * Retorna o caminho
     *
     * @param graph grafo a ver o caminho
     * @param listEnderecos lista de endereços do grafo
     * @param finalShortPath lista de endereços com o caminho com menos energia
     * gasta
     * @param origem vértice de origem
     * @param energia energia gasta nessa rua
     * @param v veiculo usado no percurso
     * @return valor da energia
     */
    public double getPath(Graph<Endereco, Double> graph, ArrayList<Endereco> listEnderecos, LinkedList<Endereco> finalShortPath, Endereco origem, double energia, Veiculo v) {
        double dFinal;
        if (!listEnderecos.isEmpty()) {
            double dist;
            Endereco endereco = null;
            double min = Double.MAX_VALUE;
            for (Endereco c : listEnderecos) {
                LinkedList<Endereco> shortPath = new LinkedList<>();
                dist = GraphAlgorithms.shortestPath(graph, origem, c, shortPath);
                if (dist < min) {
                    min = dist;
                    endereco = c;
                }
            }
            listEnderecos.remove(endereco);
            LinkedList<Endereco> shortPath1 = new LinkedList<>();
            dist = GraphAlgorithms.shortestPath(graph, origem, endereco, shortPath1);

            if (!shortPath1.isEmpty()) {
                finalShortPath.addAll(shortPath1.subList(1, shortPath1.size()));
                LinkedList<Endereco> l = checkCaminho(graph, finalShortPath, v);
                dFinal = getPath(graph, listEnderecos, l, endereco, min, v);
                energia = energia + dFinal;
            } else {
                finalShortPath.clear();
                return 0;
            }
        }
        return energia;
    }

    public LinkedList<Endereco> checkCaminho(Graph<Endereco, Double> graph, List<Endereco> finalShortPath, Veiculo v) {
        double distanciaVeiculo = CalculosFisica.getDistanciaQuePodePercorrer(v.getCapacidade(), v.getPercentagemBateria(), v.getPotencia());
        double distancia = 0;
        LinkedList<Endereco> list = new LinkedList<>();
        int i = finalShortPath.size() - 1;
        list.add(finalShortPath.get(0));
        for(Endereco e : finalShortPath){
            System.out.println("listainicial : " + e);
        }
        for(Endereco e : list){
            System.out.println("lista que vou adicionar endereco: " + e);
        }
        for (int aux = 0; aux < i; aux++) {
            if (v.getDescricao().equalsIgnoreCase(SCOOTER)) {
                distancia = distancia + CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), finalShortPath.get(aux).getAltitude(), finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), finalShortPath.get(aux + 1).getAltitude());
                System.out.println("distancia: " +distancia);
                System.out.println("diatanciaVeiculo: " +distanciaVeiculo);
                if (distanciaVeiculo < distancia) {
                    List<Endereco> lista = getListComParqueMaisProximo(graph, list, v);
                    list.addAll(lista);
                    System.out.println("lista1: " +list);
                    break;
                }
                list.add(finalShortPath.get(aux + 1));
            }
            if (v.getDescricao().equalsIgnoreCase(DRONE)) {
                distancia = distancia + CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), 0, finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), 0);
                if (distanciaVeiculo < distancia) {
                    List<Endereco> lista = getListComParqueMaisProximo(graph, list, v);
                    list.addAll(lista);
                    break;
                }
                list.add(finalShortPath.get(aux + 1));
            }
        }
        for(Endereco e : list){
            System.out.println("ListaEnderecoFINAL: " + e);
        }
        
        return list;
    }

    private List<Endereco> getListComParqueMaisProximo(Graph<Endereco, Double> graph, LinkedList<Endereco> list, Veiculo v) {
        double min = Double.MAX_VALUE;
        LinkedList<Endereco> listaFinal = new LinkedList<>();

        for (Endereco f1 : graph.vertices()) {
            if (far.getFarmaciaByEndereco(f1.getMorada()) != null) {
                for (Parque p : parqueDB.getLstParquesByFarmaciaNif(far.getFarmaciaByEndereco(f1.getMorada()).getNIF())) {
                    if (v.getDescricao().equalsIgnoreCase(SCOOTER) && p.getTipo().equalsIgnoreCase(SCOOTER)) {
                        LinkedList<Endereco> shortPath = new LinkedList<>();
                        double valor = GraphAlgorithms.shortestPath(graph, list.getLast(), f1, shortPath);
                        if (valor < min /*&& valor != 0*/) {
                            min = valor;
                            listaFinal = shortPath;
                        }
                    }
                    if(v.getDescricao().equalsIgnoreCase(DRONE) && p.getTipo().equalsIgnoreCase(DRONE)) {
                        LinkedList<Endereco> shortPath = new LinkedList<>();
                        double valor = GraphAlgorithms.shortestPath(graph, list.getLast(), f1, shortPath);
                        if (valor < min /*&& valor != 0*/) {
                            min = valor;
                            listaFinal = shortPath;
                        }
                    }
                }
            }
        }
        return listaFinal;
    }

    /**
     * Retorna a encomenda cuja morada do cliente é a recebida por parâmetro
     *
     * @param morada morada do cliente
     * @return encomenda
     */
    public Encomenda getEncomendaByMorada(String morada) {
        String query = "SELECT e.idEncomenda,e.datapedida,e.preco, e.pesoEncomenda, e.taxa, e.estadoencomendaidestadoencomenda, e.clienteutilizadornif, e.nifFarmacia FROM encomenda e INNER JOIN cliente c ON e.ClienteUtilizadorNIF = c.UtilizadorNIF WHERE c.Enderecomorada = '" + morada + "'";

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                if (rSet.next()) {
                    int idEncomenda = rSet.getInt(1);
                    Timestamp dataPedida = rSet.getTimestamp(2);
                    double preco = rSet.getDouble(3);
                    double pesoEncomenda = rSet.getDouble(4);
                    double taxa = rSet.getDouble(5);
                    int estado = rSet.getInt(6);
                    int nif = rSet.getInt(7);
                    int nifFarm = rSet.getInt(8);
                    Encomenda en = new Encomenda(nif, nifFarm, dataPedida.toString(), preco, pesoEncomenda, taxa, estado);
                    en.setId(idEncomenda);
                    return en;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    /**
     * Retorna o valor da duração do percurso
     *
     * @param finalShortPath lista de endereços com o caminho com menos energia gasta
     * @param veiculo veículo utilizado
     * @return duração do percurso
     * @throws ParseException
     */
    public String getDuracaoPercurso(List<Endereco> finalShortPath, Veiculo veiculo) throws ParseException {
        double distancia = 0;
        int aux = 0;
        int i = finalShortPath.size() - 1;
        double tempo = 0;
        for (aux = 0; aux < i; aux++) {
            if ((veiculo.getDescricao()).equalsIgnoreCase(SCOOTER)) {
                distancia = CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), finalShortPath.get(aux).getAltitude(), finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), finalShortPath.get(aux + 1).getAltitude());
                Caminho c = caminhoDB.getCaminhoByEnderecos(finalShortPath.get(aux).getMorada(), finalShortPath.get(aux + 1).getMorada());
                tempo = tempo + CalculosFisica.calculoTempo(distancia, c.getVelocidadeVento(), c.getDirecaoVento());
            }
            if ((veiculo.getDescricao()).equalsIgnoreCase(DRONE)) {
                distancia = CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), 0, finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), 0);
                Caminho c = caminhoDB.getCaminhoByEnderecos(finalShortPath.get(aux).getMorada(), finalShortPath.get(aux + 1).getMorada());
                tempo = tempo + CalculosFisica.calculoTempo(distancia, c.getVelocidadeVento(), c.getDirecaoVento());
            }
        }
        String s = String.format("%06d", (int) tempo);
        DateFormat format = new SimpleDateFormat("HHmmss");
        DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        Date date2 = format.parse(s);

        return format1.format(date2);
    }

    /**
     * Atualiza a entrega na base de dados
     *
     * @param entrega entrega a ser utilizada
     * @return true se a entrega foi atualizada com sucesso, false se não
     * @throws SQLException
     * @throws ParseException
     */
    public boolean updateEntrega(Entrega entrega) throws SQLException, ParseException {
        boolean updated = false;

        try (CallableStatement callSmt = getConnection().prepareCall("{ call updateEntrega(?,?,?,?,?,?) }")) {

            callSmt.setInt(1, entrega.getIdEntrega());
            callSmt.setInt(2, entrega.getidEstafeta());
            callSmt.setInt(3, entrega.getIdVeiculo());

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            java.util.Date date = sdf1.parse(entrega.getDataInicio());
            java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());
            callSmt.setTimestamp(4, sqlStartDate);

            java.util.Date date1 = sdf1.parse(entrega.getDataFim());
            java.sql.Timestamp sqlEndDate = new java.sql.Timestamp(date1.getTime());
            callSmt.setTimestamp(5, sqlEndDate);
            callSmt.setDouble(6, entrega.getPesoEntrega());

            callSmt.execute();

            updated = true;
            try {
                closeAll();

            } catch (NullPointerException ex) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
        return updated;
    }
}
