/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Graph;
import static lapr.project.model.GraphAlgorithms.shortestPath;
import lapr.project.model.Produto;
import lapr.project.utils.CalculosFisica;

/**
 *
 * @author josep
 */
public class FarmaciaDB extends DataHandler {

    /**
     * Cria uma nova farmácia
     *
     * @param nif nif da farmácia
     * @param email email da farmácia
     * @param morada morada da farmácia
     * @return nova farmacia criada
     */
    public Farmacia novaFarmacia(int nif, String email, String morada) {
        return new Farmacia(nif, email, morada);
    }

    /**
     * Regista a farmacia
     *
     * @param farm farmacia
     * @return
     */
    public boolean registaFarmacia(Farmacia farm) {
        if (validaFarmacia(farm)) {
            addFarmacia(farm);
        }
        return true;
    }

    /**
     * Valida a farmacia
     *
     * @param farm farmacia
     * @return true se a farmacia é valida
     */
    public boolean validaFarmacia(Farmacia farm) {
        return !(farm == null || farm.getNIF() <= 0 || farm.getEmail().isEmpty());
    }

    /**
     * Adiciona a farmacia à base de dados
     *
     * @param farm farmacia
     * @return
     */
    public boolean addFarmacia(Farmacia farm) {
        addFarmacia(farm.getNIF(), farm.getEmail(), farm.getMorada());
        return true;
    }

    /**
     * Adiciona a farmacia à base de dados
     *
     * @param nif nif da farmácia
     * @param email email da farmácia
     * @param morada morada da farmácia
     */
    public void addFarmacia(int nif, String email, String morada) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addFarmacia(?,?,?) }")) {
                callStmt.setInt(1, nif);
                callStmt.setString(2, email);
                callStmt.setString(3, morada);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna lista com todas as farmácias
     *
     * @return lista das farmácias
     */
    public List<Farmacia> getLstFarmacias() {
        ArrayList<Farmacia> list = new ArrayList<>();
        String query = "SELECT * FROM farmacia";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada = rSet.getString(3);
                    list.add(new Farmacia(nif, email, morada));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    public Farmacia getFarmaciaByNIF(int nif) {
        String query = "SELECT * FROM farmacia f INNER JOIN endereco e ON f.morada = e.morada WHERE f.nif =" + nif;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada = rSet.getString(3);

                    return new Farmacia(nif, email, morada);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    /**
     * Retorna lista com todas as farmácias
     *
     * @return lista das farmácias
     */
    public List<Farmacia> getLstFarmaciasByProdutos(Produto p, int quant) {
        ArrayList<Farmacia> list = new ArrayList<>();
        String query = "SELECT * FROM farmacia f INNER JOIN stockfarmacia s ON s.farmacianif = f.nif AND s.stock >= " + quant
                + "INNER JOIN produto p ON s.produtoidproduto = p.idProduto AND p.idProduto =" + p.getId();

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada = rSet.getString(3);
                    list.add(new Farmacia(nif, email, morada));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    public Graph<Farmacia, Double> generateGrafo(List<Farmacia> farms) {
        Graph<Farmacia, Double> graph = new Graph<>(false);

        for (Farmacia f : farms) {
            graph.insertVertex(f);
        }

        for (Farmacia f : graph.vertices()) {
            for (Farmacia f2 : farms) {
                if (!f.equals(f2)) {
                    EnderecoDB endDB = new EnderecoDB();
                    Endereco en1 = endDB.getEnderecoByNifFarmacia(f.getNIF());
                    Endereco en2 = endDB.getEnderecoByNifFarmacia(f2.getNIF());
                    graph.insertEdge(f, f2, null, CalculosFisica.calculoDistancia(en1.getLatitude(), en1.getLongitude(), en1.getAltitude(),
                            en2.getLatitude(), en2.getLongitude(), en2.getAltitude()));
                }
            }
        }
        return graph;
    }

    public int getFarmaciaProxima(Graph<Farmacia, Double> graph, int nif) {
        LinkedList<Farmacia> shortPath = new LinkedList<>();
        double min = 999999999;
        int nif1 = 0;

        for (Farmacia f1 : graph.vertices()) {
            double valor = shortestPath(graph, getFarmaciaByNIF(nif), f1, shortPath);
            if (valor < min) {
                min = valor;
                nif1 = f1.getNIF();
            }
        }
        return nif1;
    }

}
