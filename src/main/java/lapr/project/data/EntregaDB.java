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
import lapr.project.model.Drone;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Graph;
import lapr.project.model.GraphAlgorithms;
import lapr.project.model.Scooter;
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
    private final EncomendaDB encDB = new EncomendaDB();
    private final VeiculoDB veiDB = new VeiculoDB();
    private final CaminhoDB caminhoDB = new CaminhoDB();

    /**
     * Adiciona uma entrega à base de dados
     * @param entrega entrega a adicionar
     * @return id da entrega
     * @throws SQLException
     * @throws ParseException
     */
    public int addEntrega(Entrega entrega) throws SQLException, ParseException {

        int id = 0;

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call AddEntrega(?,?,?,?) }")) {
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
     * @param e entrega onde se vão adicionar as encomendas
     * @param enc encomenda a adicionar
     * @return true se for adicionada com sucesso, falso se não
     * @throws SQLException
     */
    public boolean addEncomendaEntrega(Entrega e, Encomenda enc) throws SQLException {
        boolean flag = false;
        
        try ( CallableStatement callSmt = getConnection().prepareCall("{ call AddEncomendaEntrega(?,?) }")) {
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
     * Gera o grafo com os endereços existentes como vértices
     * @param listEnderecos lista de endereços
     * @param est estafeta
     * @param veiculo veículo
     * @param atributo atributo
     * @param pesoTotalEntrega peso total de entrega
     * @return grafo com os endereços e as ruas definidas
     */
    public Graph<Endereco,Double> generateGraph(List<Endereco> listEnderecos, Estafeta est, Veiculo veiculo, double atributo, double pesoTotalEntrega) {
        
        Graph<Endereco, Double> graph = new Graph<>(true);
        List<Caminho> listCaminhos = new ArrayList<>(caminhoDB.getAllCaminhos());
        
        double energiaGasta = 0;
        
        for(Caminho c : listCaminhos){
            graph.insertVertex(c.getEnd1());
            graph.insertVertex(c.getEnd2());
        }
        
        for(Caminho caminho : listCaminhos){
            for(Endereco end : listEnderecos){
                if((veiculo.getDescricao()).equalsIgnoreCase(SCOOTER)){
                    energiaGasta = CalculosFisica.calculoEnergiaScooter(est.getPesoEstafeta(), veiculo.getPesoVeiculo(), atributo,
                        pesoTotalEntrega, caminho.getEnd1(), caminho.getEnd2(), caminho.getRoadResistanceCoefficient(), caminho.getDirecaoVento(), caminho.getVelocidadeVento());
                    graph.insertEdge(caminho.getEnd1(), caminho.getEnd2(), 1.0, energiaGasta);
                }
                if((veiculo.getDescricao()).equalsIgnoreCase(DRONE)){
                    energiaGasta = CalculosFisica.calculoEnergiaDrone(veiculo.getPesoVeiculo(), atributo, veiculo.getPotencia(),
                        pesoTotalEntrega, caminho.getEnd1(), caminho.getEnd2(),caminho.getDirecaoVento(), caminho.getVelocidadeVento());
                    graph.insertEdge(caminho.getEnd1(), caminho.getEnd2(), 1.0, energiaGasta);
                }
                if(end.getMorada().equals(caminho.getEnd2().getMorada())){
                    Encomenda enc1 = getEncomendaByMorada(caminho.getEnd2().getMorada());
                    pesoTotalEntrega = pesoTotalEntrega - encDB.getEncomenda(enc1.getId()).getPesoEncomenda();
                }
            }
        }
        return graph;
    }

    /**
     * Retorna o caminho
     * @param graph grafo a ver o caminho
     * @param listEnderecos lista de endereços do grafo
     * @param finalShortPath lista de endereços com o caminho com menos energia gasta
     * @param origem vértice de origem
     * @param energia energia gasta nessa rua
     * @return valor da energia
     */
    public double getPath(Graph<Endereco, Double> graph, List<Endereco> listEnderecos, List<Endereco> finalShortPath, Endereco origem, double energia) {
        double dFinal;
        if (!listEnderecos.isEmpty()) {
            double dist;
            Endereco end = null;
            double min = Double.MAX_VALUE;
            for (Endereco c : listEnderecos) {
                LinkedList<Endereco> shortPath = new LinkedList<>();
                dist = GraphAlgorithms.shortestPath(graph, origem, c, shortPath);
                if (dist < min) {
                    min = dist;
                    end = c;
                }
            }
            listEnderecos.remove(end);
            LinkedList<Endereco> shortPath1 = new LinkedList<>();
            dist = GraphAlgorithms.shortestPath(graph, origem, end, shortPath1);

            if (!shortPath1.isEmpty()) {
                finalShortPath.addAll(shortPath1.subList(1, shortPath1.size()));
                dFinal = getPath(graph, listEnderecos, finalShortPath, end, min);
                energia = energia + dFinal;
            } else {
                finalShortPath.clear();
                return 0;
            }
        }
        return energia;
    }

    /**
     * Retorna a encomenda cuja morada do cliente é a recebida por parâmetro
     * @param morada morada do cliente
     * @return encomenda
     */
    public Encomenda getEncomendaByMorada(String morada) {
        String query = "SELECT e.idEncomenda,e.datapedida,e.preco, e.pesoEncomenda, e.taxa, e.estadoencomendaidestadoencomenda, e.clienteutilizadornif FROM encomenda e INNER JOIN cliente c ON e.ClienteUtilizadorNIF = c.UtilizadorNIF WHERE c.Enderecomorada = '" + morada+"'";

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
     * @param finalShortPath lista de endereços com o caminho com menos energia gasta
     * @param veiculo veículo utilizado
     * @return duração do percurso
     * @throws ParseException
     */
    public String getDuracaoPercurso(List<Endereco> finalShortPath, Veiculo veiculo) throws ParseException{
        double distancia = 0;
        int aux = 0;
        int i = finalShortPath.size() - 1;
        double tempo = 0;
        for (Endereco end : finalShortPath) {
            if (aux < i && finalShortPath.size() > 2) {
                if((veiculo.getDescricao()).equalsIgnoreCase(SCOOTER)){
                    distancia = CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), finalShortPath.get(aux).getAltitude(), finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), finalShortPath.get(aux + 1).getAltitude());
                    Caminho c = new CaminhoDB().getCaminhoByEnderecos(finalShortPath.get(aux).getMorada(), finalShortPath.get(aux + 1).getMorada());
                    tempo = CalculosFisica.calculoTempo(distancia, c.getVelocidadeVento(), c.getDirecaoVento());
                }
                if((veiculo.getDescricao()).equalsIgnoreCase(DRONE)){
                    distancia = CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), 0, finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), 0);
                    Caminho c = new CaminhoDB().getCaminhoByEnderecos(finalShortPath.get(aux).getMorada(), finalShortPath.get(aux + 1).getMorada());
                    tempo = CalculosFisica.calculoTempo(distancia, c.getVelocidadeVento(), c.getDirecaoVento());
                }
                aux = aux + 1;
            }
        }
        String s = String.format("%06d", (int)tempo);
        DateFormat format = new SimpleDateFormat("HHmmss");
        DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        Date date2 = format.parse(s);
       
        return format1.format(date2);
    }

    /**
     * Atualiza a entrega na base de dados
     * @param entrega entrega a ser utilizada
     * @return true se a entrega foi atualizada com sucesso, false se não
     * @throws SQLException
     * @throws ParseException
     */
    public boolean updateEntrega(Entrega entrega) throws SQLException, ParseException {
        boolean updated = false;

        try ( CallableStatement callSmt = getConnection().prepareCall("{ call updateEntrega(?,?,?,?,?) }")) {
                        
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
