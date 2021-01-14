package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

public class ProdutosDB extends DataHandler {

    private final Map<Produto, Integer> mapEnc;
    private double peso;
    private double preco;
    private FarmaciaDB fdb;

    public ProdutosDB() {
        mapEnc = new HashMap<>();
        peso = 0;
        preco = 0;
    }

    public Produto novoProduto(String desig, double peso, double precoBase) {
        return new Produto(desig, peso, precoBase);
    }

    public boolean validaProduto(Produto prod) {
        return !(prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0);
    }

    public boolean registaProduto(Produto prod, int farm) {
        if (validaProduto(prod)) {
            for (Farmacia f : fdb.getLstFarmacias()) {
                if (f.getNIF() == farm) {
                    f.addStock(prod);
                }
            }
            addProduto(prod);
            return true;
        }
        return false;
    }

    public void addProduto(Produto prod) {
        addProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase());
    }

    private void addProduto(String desig, double peso, double precoBase) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call addProduto(?,?,?) }")) {

                callStmt.setString(1, desig);
                callStmt.setDouble(2, peso);
                callStmt.setDouble(3, precoBase);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean atualizarProduto(Produto prod) {
        if (validaProduto(prod)) {
            atualizarProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase(), prod.getId());
            return true;
        }
        return false;
    }

    private void atualizarProduto(String desig, double peso, double precoBase, int id) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call atualizarProduto(?,?,?,?) }")) {

                callStmt.setString(1, desig);
                callStmt.setDouble(2, peso);
                callStmt.setDouble(3, precoBase);
                callStmt.setInt(4, id);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validaListaProdutos(List<Produto> lprods) {
        boolean check = true;
        for (Produto prod1 : lprods) {
            if (prod1.getDesignacao() == null || prod1.getPeso() < 0 || prod1.getPrecoBase() < 0) {
                check = false;
            } else {
                check = true;
            }
        }
        return check;
    }

    public Produto getProdutoByID(int id) {
        String query = "SELECT * FROM produto p WHERE p.idProduto= " + id;

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int id1 = rSet.getInt(1);
                    String desig = rSet.getString(2);
                    double peso1 = rSet.getDouble(3);
                    double precoBase = rSet.getDouble(4);

                    return new Produto(id1, desig, peso1, precoBase);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    /**
     * Lista do stock da farmacia recebida por parametro
     *
     * @return
     */
    public Map<Produto, Integer> getLista(int nif) {
        Map<Produto, Integer> map = new HashMap<>();
        String query = "SELECT * FROM produto p INNER JOIN StockFarmacia s ON s.ProdutoidProduto = p.idProduto AND s.FarmaciaNIF = " + nif;

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {

                while (rSet.next()) {
                    int id = rSet.getInt(1);
                    String designacao = rSet.getString(2);
                    double peso2 = rSet.getDouble(3);
                    double precoBase = rSet.getDouble(4);
                    Produto p = new Produto(id, designacao, peso2, precoBase);
                    if (map.containsKey(p)) {
                        Integer get = map.get(p);
                        map.replace(p, get + 1);
                    } else {
                        map.put(p, 1);
                    }
                }
                return map;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Adiciona ao mapa os produtos e quantidade da encomenda
     *
     * @param prod
     */
    public boolean addListaProdutos(Produto prod, int qntd) {

        if (mapEnc.containsKey(prod) == true) {
            Integer get = mapEnc.get(prod);
            mapEnc.replace(prod, get + qntd);
        }
        mapEnc.put(prod, qntd);
        return true;
    }

    /**
     * Devolve o mapa de encomendas
     *
     * @return
     */
    public Map<Produto, Integer> getMapaEncomenda() {
        return mapEnc;
    }

    /**
     * Remove os produtos da base de dados
     *
     * @param lst
     */
    public boolean removerProdutosEncomenda(Map<Produto, Integer> map) {
        boolean bo = false;
        for (Produto p : map.keySet()) {
            Integer get = map.get(p);
            while (get > 0) {
                bo = removerProdutosEncomenda(p.getDesignacao());
                get--;
            }
        }
        return bo;
    }

    /**
     * Remove os produtos da base de dados
     *
     * @param des
     */
    private boolean removerProdutosEncomenda(String des) {
        boolean removed = false;

        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call procRemoverProduto(?) }")) {

                callStmt.setString(1, des);
                removed = true;
                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return removed;
    }

    /**
     * Devolve o preco total tendo em conta a taxa
     *
     * @param taxa
     * @return
     */
    public double getPrecoTotal(double taxa) {
        Map<Produto, Integer> mapaEncomenda = getMapaEncomenda();
        double preco = 0.0;

        for (Produto prod : mapaEncomenda.keySet()) {
            preco = preco + prod.getPrecoBase();
        }

        return preco + preco * taxa;
    }

    /**
     * Devolve o preco
     *
     * @return
     */
    public double getPreco() {
        Map<Produto, Integer> mapaEncomenda = getMapaEncomenda();
        double preco = 0;

        for (Produto p : mapaEncomenda.keySet()) {
            preco = preco + p.getPrecoBase();
        }
        return preco;
    }

    /**
     * Devolve o peso
     *
     * @return
     */
    public double getPeso() {
        Map<Produto, Integer> mapaEncomenda = getMapaEncomenda();
        double peso = 0;

        for (Produto p : mapaEncomenda.keySet()) {
            peso = peso + p.getPrecoBase();
        }
        return peso;
    }

}
