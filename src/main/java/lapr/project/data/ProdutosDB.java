package lapr.project.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;
import oracle.jdbc.OracleTypes;

public class ProdutosDB extends DataHandler {

    private final Map<Produto, Integer> mapEnc;
    private final FarmaciaDB fdb;

    /**
     * Cria uma instância de ProdutosDB
     */
    public ProdutosDB() {
        mapEnc = new HashMap<>();
        fdb = new FarmaciaDB();
    }

    /**
     * Devolve o produto criado com os dados enviados por parâmetro
     *
     * @param desig designação do produto
     * @param peso peso do produto
     * @param precoBase preço base do produto
     * @return novo produto
     */
    public Produto novoProduto(String desig, double peso, double precoBase) {
        return new Produto(desig, peso, precoBase);
    }

    /**
     * Valida o produto criado
     *
     * @param prod produto a verificar
     * @return true se o produto criado for válido, falso se não
     */
    public boolean validaProduto(Produto prod) {
        return !(prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0);
    }

    /**
     * Regista o produto na base de dados
     *
     * @param prod produto a registar
     * @param farm farmácia para onde o produto vai ser enviado
     * @param qtd quantidade do produto a enviar
     * @return true se o produto for registado com sucesso, false se não
     */
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

    /**
     * Adiciona o produto à base de dados
     *
     * @param prod produto a atualizar
     * @return o id do produto criado
     */
    public int addProduto(Produto prod) {
        return addProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase());
    }

    /**
     * Adiciona o produto à base de dados
     *
     * @param desig designação do produto
     * @param peso peso do produto
     * @param precoBase preço base do produto
     * @return id do produto criado
     */
    public int addProduto(String desig, double peso, double precoBase) {
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

    /**
     * Adiciona o produto criado ao stock da farmácia selecionada
     *
     * @param nif nif da farmácia onde será adicionado o produto
     * @param prod id do produto a adicionar
     * @param qtd quantidade a ser adicionada
     */
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

    /**
     * Atualiza as informações do produto na base de dados
     *
     * @param prod produto a ser atualizado
     * @return true se o produto for alterado com sucesso, false se não
     */
    public boolean atualizarProduto(Produto prod) throws SQLException {
        if (validaProduto(prod)) {
            atualizarProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase(), prod.getId());
            return true;
        }
        return false;
    }

    /**
     * Atualiza as informações do produto na base de dados
     *
     * @param desig designação do produto
     * @param peso peso do produto
     * @param precoBase preço base do produto
     * @param id id do produto
     */
    private void atualizarProduto(String desig, double peso, double precoBase, int id) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call atualizarProduto(?,?,?,?) }")) {

            callStmt.setString(1, desig);
            callStmt.setDouble(2, peso);
            callStmt.setDouble(3, precoBase);
            callStmt.setInt(4, id);

            callStmt.execute();
            try {

                closeAll();

            } catch (NullPointerException ex) {
                Logger.getLogger(ProdutosDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
    }

    /**
     * Atualiza o stock de uma farmácia na base de dados
     *
     * @param nif nif da farmácia a atualizar
     * @param idProduto id do produto a atualizar
     * @param quantidade nova quantidade
     * @return true se for atualizado com sucesso, false se não
     */
    public boolean atualizarStock(int nif, int idProduto, int quantidade) {
        boolean removed = false;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAtualizarStock(?,?,?) }")) {
            callStmt.setInt(1, nif);
            callStmt.setInt(2, idProduto);
            callStmt.setInt(3, quantidade);
            callStmt.execute();
            removed = true;
            try {

                closeAll();

            } catch (NullPointerException ex) {
                Logger.getLogger(ProdutosDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }

    /**
     * Devolve o produto cujo id é igual ao recebido por parâmetro
     *
     * @param id id do produto
     * @return produto
     */
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
     * Lista do stock da farmácia recebida por parametro
     *
     * @return mapa com o stock da farmácia
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
                    Produto p = new Produto(designacao, peso2, precoBase);
                    int stock = rSet.getInt(7);
                    if (map.containsKey(p)) {
                        p.setId(id);
                        map.replace(p, map.get(p) + stock);
                    } else {
                        p.setId(id);
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
     * @param prod produto a adicionar
     * @param qntd quantidade do produto
     * @return 
     */
    public boolean addListaProdutos(Produto prod, int qntd) {

        if (mapEnc.containsKey(prod)) {
            Integer get = mapEnc.get(prod);
            mapEnc.replace(prod, get + qntd);
        } else {
            mapEnc.put(prod, qntd);
        }
        return true;
    }

    /**
     * Devolve o mapa de encomendas
     *
     * @return mapa de encomendas
     */
    public Map<Produto, Integer> getMapaEncomenda() {
        return mapEnc;
    }

    /**
     * Remove os produtos da base de dados
     *
     * @param prod produto a ser removido
     * @param nif nif da farmácia
     * @param qtd quantidade do produto
     * @param qtdStock quantidade do produto em stock
     */
    public boolean removerProdutosEncomenda(Produto prod, int nif, int qtd, int qtdStock) {
        int c = qtdStock - qtd;
        return atualizarStock(nif, prod.getId(), c);
    }

    /**
     * Devolve o preco total tendo em conta a taxa
     *
     * @param preco
     * @param taxa taxa da encomenda
     * @return preço total
     */
    public double getPrecoTotal(double preco, double taxa) {
        BigDecimal bd = new BigDecimal(preco + preco*taxa).setScale(2,RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * Devolve o preco
     *
     * @return preco
     */
    public double getPreco() {
        Map<Produto, Integer> mapaEncomenda = getMapaEncomenda();
        double preco = 0;

        for (Map.Entry<Produto, Integer> entry : mapaEncomenda.entrySet()) {
            for (int i = 0; i < mapaEncomenda.get(entry.getKey()); i++) {
                preco = preco + entry.getKey().getPrecoBase();
            }
        }
//        System.out.println("PRECO: "+Math.floor(preco));
        BigDecimal bd = new BigDecimal(preco).setScale(2,RoundingMode.HALF_EVEN);
        return bd.doubleValue();

    }

    /**
     * Devolve o peso
     *
     * @return peso
     */
    public double getPeso() {
        Map<Produto, Integer> mapaEncomenda = getMapaEncomenda();
        double peso = 0;

        for (Map.Entry<Produto, Integer> entry : mapaEncomenda.entrySet()) {
            for (int i = 0; i < mapaEncomenda.get(entry.getKey()); i++) {
                peso = peso + entry.getKey().getPeso();
            }
        }
        BigDecimal bd = new BigDecimal(peso).setScale(2,RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

}
