package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;
import oracle.jdbc.OracleTypes;

public class ProdutosDB extends DataHandler {

    private final Map<Produto, Integer> mapEnc;
    private final FarmaciaDB fdb;

    public ProdutosDB() {
        mapEnc = new HashMap<>();
        fdb = new FarmaciaDB();
    }

    public Produto novoProduto(String desig, double peso, double precoBase) {
        return new Produto(desig, peso, precoBase);
    }

    public boolean validaProduto(Produto prod) {
        return !(prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0);
    }

    public boolean registaProduto(Produto prod, int farm, int qtd) {
        if (validaProduto(prod)) {
            prod.setId(addProduto(prod));
            for (Farmacia f : fdb.getLstFarmacias()) {
                if (f.getNIF() == farm) {
                    addProdutoStock(f.getNIF(), prod.getId(), qtd);
                }
            }
            return true;
        }
        return false;
    }

    public int addProduto(Produto prod) {
        return addProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase());
    }

    private int addProduto(String desig, double peso, double precoBase) {
        int id = 0;
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call addProduto(?,?,?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setString(2, desig);
                callStmt.setDouble(3, peso);
                callStmt.setDouble(4, precoBase);

                callStmt.execute();
                id = callStmt.getInt(1);
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void addProdutoStock(int nif, int prod, int qtd) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call addProdutoStock(?,?,?) }")) {

                callStmt.setInt(1, nif);
                callStmt.setInt(2, prod);
                callStmt.setInt(3, qtd);

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

    public boolean atualizarStock(int nif, int idProduto, int quantidade) {
        boolean removed = false;
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call procAtualizarStock(?,?,?) }")) {

                callStmt.setInt(1, nif);
                callStmt.setInt(2, idProduto);
                callStmt.setInt(3, quantidade);
                removed = true;
                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return removed;
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
                    int stock = rSet.getInt(7);
                    if (map.containsKey(p)) {
                        Integer get = map.get(p);
                        map.replace(p, get + stock);
                    } else {
                        map.put(p, stock);
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
     * @param
     */
    public boolean removerProdutosEncomenda(Produto prod, int nif, int map, int mapStock) {
        return atualizarStock(nif, prod.getId(), mapStock-map);
    }

    /**
     * Devolve o preco total tendo em conta a taxa
     *
     * @param taxa
     * @return
     */
    public double getPrecoTotal(Map<Produto, Integer> mapaEncomenda, double taxa) {

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
            for (int i = 0; i < mapaEncomenda.get(p); i++) {
                preco = preco + p.getPrecoBase();
            }
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
            for (int i = 0; i < mapaEncomenda.get(p); i++) {
                peso = peso + p.getPeso();
            }
        }
        return peso;
    }

}
