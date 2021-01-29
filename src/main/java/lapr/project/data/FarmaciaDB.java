/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.*;
import lapr.project.utils.CalculosFisica;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author josep
 */
public class FarmaciaDB extends DataHandler {

    private final CaminhoDB cam = new CaminhoDB();

    /**
     * Cria uma nova farmácia
     *
     * @param nif    nif da farmácia
     * @param email  email da farmácia
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
     * @param nif    nif da farmácia
     * @param email  email da farmácia
     * @param morada morada da farmácia
     */
    public boolean addFarmacia(int nif, String email, String morada) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addFarmacia(?,?,?) }")) {
            callStmt.setInt(1, nif);
            callStmt.setString(2, email);
            callStmt.setString(3, morada);
            callStmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
            return false;
        } finally {
            closeAll();
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
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
            return Collections.emptyList();
        } finally {
            closeAll();
        }
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
            return null;
        } finally {
            closeAll();
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
        String query = "SELECT * FROM farmacia f INNER JOIN stockfarmacia s ON s.farmacianif = f.nif AND s.stock >= "
                + quant + "INNER JOIN produto p ON s.produtoidproduto = p.idProduto AND p.idProduto =" + p.getId();

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
            return Collections.emptyList();
        } finally {
            closeAll();
        }
    }

    /**
     * Devolve a lista de farmácias que possuem o produto enviado por parâmetro
     *
     * @param p produto
     * @return lista de farmácias
     */
    public List<Farmacia> getLstFarmaciasByProduto(Produto p) {
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
            return Collections.emptyList();
        } finally {
            closeAll();
        }
    }

    /**
     * Devolve a farmacia pelo endereco
     *
     * @param morada
     * @return
     */
    public Farmacia getFarmaciaByEndereco(String morada) {

        String query = "SELECT * FROM farmacia f WHERE f.morada = '" + morada + "'";

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada1 = rSet.getString(3);
                    return (new Farmacia(nif, email, morada1));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
            return null;
        } finally {
            closeAll();
        }
        return null;
    }

    /**
     * Gera um grafo com a lista de enderecos das farmácias, das ruas e das ruas dos
     * clientes e a distância entre elas
     *
     * @param graph
     * @return grafo
     */
    public Graph<Endereco, Double> generateGrafo(Graph<Endereco, Double> graph, List<Endereco> lst) {

        for (Endereco endereco : lst) {
            graph.insertVertex(endereco);
        }

        for (Endereco e1 : graph.vertices()) {
            for (Endereco e : graph.vertices()) {
                if (cam.getCaminhoByEnderecos(e.getMorada(), e1.getMorada()) != null) {
                    graph.insertEdge(e, e1, 1.0, CalculosFisica.calculoDistancia(e.getLatitude(), e.getLongitude(),
                            e.getAltitude(), e1.getLatitude(), e1.getLongitude(), e1.getAltitude()));
                }
            }
        }

        return graph;
    }

    /**
     * Devolve a farmácia mais próxima do endereco cliente recebido por parâmetro
     *
     * @param graph           grafo com a lista de farmácias
     * @param enderecoCliente
     * @param list
     * @return farmácia mais próxima
     */
    public Farmacia getFarmaciaProxima(Graph<Endereco, Double> graph, Endereco enderecoCliente, List<Endereco> list) {

        double min = Double.MAX_VALUE;
        Farmacia farmaciaByEndereco = null;
        for (Endereco f1 : graph.vertices()) {
            if (getFarmaciaByEndereco(f1.getMorada()) != null && !list.contains(f1)) {
                LinkedList<Endereco> shortPath = new LinkedList<>();
                double valor = GraphAlgorithms.shortestPath(graph, enderecoCliente, f1, shortPath);
                if (valor < min && valor != 0) {
                    min = valor;
                    farmaciaByEndereco = getFarmaciaByEndereco(f1.getMorada());
                }
            }
        }
        return farmaciaByEndereco;

    }
}
