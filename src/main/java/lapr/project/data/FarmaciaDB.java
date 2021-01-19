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

    EnderecoDB end = new EnderecoDB();

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
     * @return true se o registo for feito com sucesso, falso se não
     */
    public boolean registaFarmacia(Farmacia farm) {
        if (validaFarmacia(farm)) {
            addFarmacia(farm);
            return true;
        }
        return false;
    }

    /**
     * Valida a farmacia
     *
     * @param farm farmacia a ser validada
     * @return true se a farmacia é valida, false se não
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
            try (CallableStatement callStmt = getConnection().prepareCall("{ call addFarmacia(?,?,?) }")) {
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

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
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

    /**
     * Devolve a farmácia cujo nif é igual ao enviado por parâmetro
     *
     * @param nif nif da farmácia
     * @return farmácia
     */
    public Farmacia getFarmaciaByNIF(int nif) {
        String query = "SELECT * FROM farmacia f INNER JOIN endereco e ON f.morada = e.morada WHERE f.nif =" + nif;

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada = rSet.getString(3);

                    return new Farmacia(nif, email, morada);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
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

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada = rSet.getString(3);
                    list.add(new Farmacia(nif, email, morada));
                }
                if (list.isEmpty()) {
                    list = new ArrayList<>(getLstFarmaciasByProduto(p));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }

        return list;
    }

    /**
     * Devolve a lista de farmácias que possuem o produto enviado por parâmetro
     *
     * @param p produto
     * @return lista de farmácias
     */
    private ArrayList<Farmacia> getLstFarmaciasByProduto(Produto p) {
        ArrayList<Farmacia> list = new ArrayList<>();
        String query = "SELECT * FROM farmacia f INNER JOIN stockfarmacia s ON s.farmacianif = f.nif "
                + "INNER JOIN produto p ON s.produtoidproduto = p.idProduto AND p.idProduto =" + p.getId();

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
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

    /**
     * Devolve a farmacia pelo endereco
     * @param morada
     * @return 
     */
    private Farmacia getFarmaciaByEndereco(String morada) {

        String query = "SELECT * FROM farmacia f WHERE f.morada = '" + morada + "'";

        Farmacia f = new Farmacia();

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada1 = rSet.getString(3);
                    f = (new Farmacia(nif, email, morada1));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return f;
    }

    /**
     * Gera um grafo com a lista de enderecos das farmácias e a distância entre
     * elas
     *
     * @param farms lista de farmácias
     * @return grafo
     */
    public Graph<Endereco, Double> generateGrafo(List<Farmacia> farms) {

        Graph<Endereco, Double> graph = new Graph<>(true);

        for (Farmacia f : farms) {
            graph.insertVertex(end.getEnderecoByNifFarmacia(f.getNIF()));
        }

        for (Endereco en1 : graph.vertices()) {
            for (Endereco en2 : graph.vertices()) {
                if (!en1.equals(en2)) {
                    //contruir as arestas com base na leitura do ficheiro
                    graph.insertEdge(en1, en2, null, CalculosFisica.calculoDistancia(en1.getLatitude(), en1.getLongitude(), en1.getAltitude(),
                            en2.getLatitude(), en2.getLongitude(), en2.getAltitude()));
                }
            }
        }
        return graph;
    }
//ALTERAR MÉTODO DE CIMA

    /**
     * Devolve a farmácia mais próxima do endereco cliente recebido por parâmetro
     *
     * @param graph grafo com a lista de farmácias
     * @param enderecoCliente
     * @return farmácia mais próxima
     */
    public Farmacia getFarmaciaProxima(Graph<Endereco, Double> graph, Endereco enderecoCliente) {
        LinkedList<Endereco> shortPath = new LinkedList<>();
        double min = 999999999;
        int nif1 = 0;
        Farmacia farmaciaByEndereco = null;

        graph.insertVertex(enderecoCliente);

        for (Endereco f1 : graph.vertices()) {
            double valor = shortestPath(graph, f1, enderecoCliente, shortPath);
            if (valor < min) {
                min = valor;
                farmaciaByEndereco = getFarmaciaByEndereco(f1.getMorada());
            }
        }
        return farmaciaByEndereco;
    }

}
