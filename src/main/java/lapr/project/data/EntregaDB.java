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
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Graph;
import lapr.project.model.GraphAlgorithms;
import lapr.project.model.Veiculo;
import lapr.project.utils.CalculosFisica;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author beatr
 */
public class EntregaDB extends DataHandler {
    
    private final EncomendaDB encDB = new EncomendaDB();

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

                callStmt.close();

            } catch (SQLException | NullPointerException ex) {

                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }

        return id;
    }

    public boolean addEncomendaEntrega(Entrega e, Encomenda enc) throws SQLException {
        boolean flag = false;
        
        try ( CallableStatement callSmt = getConnection().prepareCall("{ call AddEncomendaEntrega(?,?) }")) {
                callSmt.setInt(1, e.getIdEntrega());
                callSmt.setInt(2, enc.getId());

                callSmt.execute();
                flag = true;
            try {
                callSmt.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
        return flag;

    }

    public Entrega getEntregaById(int idEntrega) {
        String query = "SELECT * FROM entrega WHERE idEntrega = " + idEntrega;

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int id = rSet.getInt(1);
                    int nif = rSet.getInt(2);
                    int idVeiculo = rSet.getInt(3);
                    Timestamp dataInicio = rSet.getTimestamp(4);
                    Timestamp dataFim = rSet.getTimestamp(5);

                    return new Entrega(dataInicio.toString(), dataFim.toString(), idVeiculo, nif);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    public Entrega getEntregaAtiva(String email) {

        try {
            openConnection();

            int vRes;
            try (CallableStatement callStmt = getConnection().prepareCall("{ call getEntregaAtiva(?) }")) {
                callStmt.setString(1, email);

                callStmt.execute();

                vRes = callStmt.getInt(1);
            }

            closeAll();

            return getEntregaById(vRes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Entrega> getListaEntregaByNifEstafeta(int nifEstafeta) {
        ArrayList<Entrega> list = new ArrayList<>();
        String query = "SELECT * FROM entrega e INNER JOIN estafeta est ON est.UtilizadorNIF = e.EstafetaUtilizadorNIF WHERE e.EstafetaUtilizadorNIF = " + nifEstafeta;

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {

                while (rSet.next()) {
                    int idEntrega = rSet.getInt(1);
                    int nif = rSet.getInt(2);
                    int idVeiculo = rSet.getInt(3);
                    Timestamp dataInicio = rSet.getTimestamp(4);
                    Timestamp dataFim = rSet.getTimestamp(5);

                    list.add(new Entrega(dataInicio.toString(), dataFim.toString(), idVeiculo, nif));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    public Graph<Endereco,Double> generateGraph(List<Endereco> listEnderecos, Estafeta est, Veiculo veiculo, double pesoTotalEntrega) {
        
        Graph<Endereco, Double> graph = new Graph<>(true);
        double energiaGasta = 0;
        for (Endereco e : listEnderecos) {
            graph.insertVertex(e);
        }

        int i = listEnderecos.size() - 1;
        if((veiculo.getTipo()).equals("scooter")){
            energiaGasta = CalculosFisica.calculoEnergiaScooter(est.getPesoEstafeta(), veiculo.getPesoVeiculo(), veiculo.getAreaFrontal(), pesoTotalEntrega, listEnderecos.get(0), listEnderecos.get(i));
        }
        if((veiculo.getTipo()).equals("drone")){
            energiaGasta = CalculosFisica.calculoEnergiaDrone(veiculo.getPesoVeiculo(), veiculo.getAreaFrontal(), pesoTotalEntrega, listEnderecos.get(0), listEnderecos.get(i));
        }
        if(listEnderecos.size() == 2){
            System.out.println("aqui");
            graph.insertEdge(listEnderecos.get(0), listEnderecos.get(1), 1.0, energiaGasta);
        }else if(listEnderecos.size()> 2){
            graph.insertEdge(listEnderecos.get(0), listEnderecos.get(1), 1.0, energiaGasta);
            graph.insertEdge(listEnderecos.get(0), listEnderecos.get(i), 1.0, energiaGasta);
        }

        int aux = 1;
        for (Endereco end : listEnderecos) {
            if (aux < i && listEnderecos.size() > 2) {
                Encomenda enc1 = getEncomendaByMorada(listEnderecos.get(aux).getMorada()); //mal provavelmente
                if((veiculo.getTipo()).equalsIgnoreCase("scooter")){
                    energiaGasta = CalculosFisica.calculoEnergiaScooter(est.getPesoEstafeta(), veiculo.getPesoVeiculo(), veiculo.getAreaFrontal(), pesoTotalEntrega, listEnderecos.get(aux), listEnderecos.get(aux + 1));
                    graph.insertEdge(listEnderecos.get(aux), listEnderecos.get(aux + 1), 1.0, energiaGasta);
                }
                if((veiculo.getTipo()).equalsIgnoreCase("drone")){
                    energiaGasta = CalculosFisica.calculoEnergiaDrone(veiculo.getPesoVeiculo(), veiculo.getAreaFrontal(), pesoTotalEntrega, listEnderecos.get(aux), listEnderecos.get(aux + 1));
                    graph.insertEdge(listEnderecos.get(aux), listEnderecos.get(aux + 1), 1.0, energiaGasta);
                }
                aux = aux + 1;
                pesoTotalEntrega = pesoTotalEntrega - encDB.getEncomenda(enc1.getId()).getPesoEncomenda();
            }
        }
        return graph;
    }

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
                    Encomenda en = new Encomenda(nif, dataPedida.toString(), preco, pesoEncomenda, taxa, estado);
                    en.setId(idEncomenda);
                    return en;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    } 
    
    public String getDuracaoPercurso(List<Endereco> finalShortPath, Veiculo veiculo) throws ParseException{
        double distancia = 0;
        int aux = 0;
        int i = finalShortPath.size() - 1;
        for (Endereco end : finalShortPath) {
            if (aux < i && finalShortPath.size() > 2) {
                if((veiculo.getTipo()).equalsIgnoreCase("scooter")){
                    distancia = CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), finalShortPath.get(aux).getAltitude(), finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), finalShortPath.get(aux + 1).getAltitude());
                }
                if((veiculo.getTipo()).equalsIgnoreCase("drone")){
                    distancia = CalculosFisica.calculoDistancia(finalShortPath.get(aux).getLatitude(), finalShortPath.get(aux).getLongitude(), 0, finalShortPath.get(aux + 1).getLatitude(), finalShortPath.get(aux + 1).getLongitude(), 0);
                }
                aux = aux + 1;
            }
        }
        double tempo = CalculosFisica.calculoTempo(distancia);
        String s = String.format("%06d", (int)tempo);
        DateFormat format = new SimpleDateFormat("HHmmss");
        DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        Date date2 = format.parse(s);
       
        return format1.format(date2);
    }
    
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

                callSmt.close();

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }

        return updated;

    }
}
