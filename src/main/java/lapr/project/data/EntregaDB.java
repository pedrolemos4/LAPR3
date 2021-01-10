package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import lapr.project.model.Scooter;
import lapr.project.utils.CalculosFisica;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author beatr
 */
public class EntregaDB extends DataHandler {

    public int addEntrega(Entrega entrega) throws SQLException {

        CallableStatement callStmt = null;
        int id = 0;

        callStmt = getConnection().prepareCall("{ ? = call AddEntrega(?,?,?,?) }");
        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        Timestamp dInicio = Timestamp.valueOf(entrega.getDataInicio());
        callStmt.setTimestamp(4, dInicio);
        Timestamp dFim = Timestamp.valueOf(entrega.getDataFim());
        callStmt.setTimestamp(5, dFim);
        callStmt.setInt(3, entrega.getIdScooter());
        callStmt.setInt(2, entrega.getidEstafeta());

        callStmt.execute();
        id = callStmt.getInt(1);

        closeAll();
        try {

            callStmt.close();

        } catch (SQLException | NullPointerException ex) {

            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

    public void addEncomendaEntrega(Entrega e, Encomenda enc) {
        addEncomendaEntrega(e.getIdEntrega(), enc.getId());
    }

    private void addEncomendaEntrega(int idEntrega, int idEncomenda) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call AddEncomendaEntrega(?,?) }");
            callStmt.setInt(1, idEntrega);
            callStmt.setInt(2, idEncomenda);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Devolve a lista de encomendas
//     *
//     * @return
//     */
//    public List<Entrega> getListaEntrega() {
//        ArrayList<Entrega> list = new ArrayList<>();
//        String query = "SELECT * FROM entrega";
//
//        Statement stm = null;
//        ResultSet rSet = null;
//
//        try {
//            stm = getConnection().createStatement();
//            rSet = stm.executeQuery(query);
//
//            while (rSet.next()) {
//                int idEntrega = rSet.getInt(1);
//                int nif = rSet.getInt(2);
//                int idscooter = rSet.getInt(3);
//                Timestamp dataInicio = rSet.getTimestamp(4);
//                Timestamp dataFim = rSet.getTimestamp(5);
//
//                list.add(new Entrega(dataInicio.toString(), dataFim.toString(), idscooter, nif));
//            }
//            return list;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public Entrega getEntregaById(int idEntrega) {
        String query = "SELECT * FROM entrega WHERE idEntrega = " + idEntrega;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                int nif = rSet.getInt(2);
                int idscooter = rSet.getInt(3);
                Timestamp dataInicio = rSet.getTimestamp(4);
                Timestamp dataFim = rSet.getTimestamp(5);

                return new Entrega(dataInicio.toString(), dataFim.toString(), idscooter, nif);
            }

        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }

    public Entrega getEntregaAtiva(String email) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call getEntregaAtiva(?) }");
            callStmt.setString(1, email);

            callStmt.execute();

            int v_res = callStmt.getInt(1);

            closeAll();

            return getEntregaById(v_res);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Entrega> getListaEntregaByNifEstafeta(int nifEstafeta) {
        ArrayList<Entrega> list = new ArrayList<>();
        String query = "SELECT * FROM entrega e INNER JOIN estafeta est ON est.UtilizadorNIF = e.EstafetaUtilizadorNIF WHERE e.EstafetaUtilizadorNIF = " + nifEstafeta;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            while (rSet.next()) {
                int idEntrega = rSet.getInt(1);
                int nif = rSet.getInt(2);
                int idscooter = rSet.getInt(3);
                Timestamp dataInicio = rSet.getTimestamp(4);
                Timestamp dataFim = rSet.getTimestamp(5);

                list.add(new Entrega(dataInicio.toString(), dataFim.toString(), idscooter, nif));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Endereco> generateGraph(List<Endereco> listEnderecos, List<Encomenda> listEncomenda, Estafeta est, Scooter scooter, double pesoTotalEntrega) {
        Graph<Endereco, Double> graph = new Graph<>(true);

        for (Endereco e : listEnderecos) {
            graph.insertVertex(e);
        }

        int i = listEnderecos.size() - 1;
        double energiaGasta = CalculosFisica.calculoEnergia(est.getPesoEstafeta(),scooter.getPesoScooter(),scooter.getAreaFrontal(), pesoTotalEntrega,listEnderecos.get(0), listEnderecos.get(i));
        graph.insertEdge(listEnderecos.get(0), listEnderecos.get(i), 1.0, energiaGasta);
                
        
        int aux = 0;
        for (Endereco end : listEnderecos){
            if (aux < i && listEnderecos.size() > 2) {
                Encomenda enc1 = getEncomendaByMorada(listEnderecos.get(aux).getMorada());
                energiaGasta = CalculosFisica.calculoEnergia(est.getPesoEstafeta(),scooter.getPesoScooter(),scooter.getAreaFrontal(), pesoTotalEntrega,listEnderecos.get(aux), listEnderecos.get(aux + 1));
                graph.insertEdge(listEnderecos.get(aux), listEnderecos.get(aux + 1), 1.0, energiaGasta);
                aux = aux + 1;
                pesoTotalEntrega = pesoTotalEntrega - enc1.getPesoEncomenda();
            }
        }
        
        LinkedList<Endereco> finalShortPath = new LinkedList<>();
        getPath(graph, listEnderecos, finalShortPath, listEnderecos.get(0), 0);
        return finalShortPath;
    }
    
     public double getPath(Graph<Endereco,Double> graph, List<Endereco> listEnderecos, LinkedList<Endereco> finalShortPath, Endereco origem, double energia) {
        double dFinal;
        if (!listEnderecos.isEmpty()) {
            double dist;
            Endereco end = null;
            double min = Double.MAX_VALUE;
            for (Endereco c : listEnderecos) {
                    LinkedList<Endereco> shortPath = new LinkedList<>();
                    dist = GraphAlgorithms.shortestPath(graph, origem, c, shortPath);
                    if (dist < min) {
//                        System.out.println("dist: " + dist);
//                        System.out.println("shortPath: " + shortPath);
//                        System.out.println(" ");
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
    
    public Encomenda getEncomendaByMorada(String morada){
        String query = "SELECT * FROM encomenda e INNER JOIN cliente c ON e.ClienteUtilizadorNIF = c.UtilizadorNIF WHERE c.Enderecomorada = " + morada;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                int idEncomenda = rSet.getInt(1);
                Timestamp dataPedida = rSet.getTimestamp(2);
                double preco = rSet.getDouble(3);
                double pesoEncomenda = rSet.getDouble(4);
                double taxa = rSet.getDouble(5);
                int estado = rSet.getInt(6);
                int nif = rSet.getInt(7);

                return new Encomenda(nif, dataPedida.toString(), preco, pesoEncomenda, taxa, estado);
            }

        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }

}
