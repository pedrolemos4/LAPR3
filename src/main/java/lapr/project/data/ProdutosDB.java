package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

public class ProdutosDB extends DataHandler {

    private final List<Produto> listEnc;
    private final List<Integer> listQuant;
    private double peso;
    private double preco;
    private FarmaciaDB fdb;

    public ProdutosDB() {
        listEnc = new ArrayList<>();
        listQuant = new ArrayList<>();
        peso = 0;
        preco = 0;
    }
    public Produto novoProduto(String desig, double peso, double precoBase) {
        return new Produto(desig,peso,precoBase);
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
        if (validaProduto(prod)){
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

        try (Statement stm = getConnection().createStatement()){
            try(ResultSet rSet  = stm.executeQuery(query)) {

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
     * Lista do stock
     *
     * @return
     */
    public List<Produto> getLista() {
        ArrayList<Produto> list = new ArrayList<>();
        String query = "SELECT * FROM produto";

        try (Statement stm = getConnection().createStatement()){
            try(ResultSet rSet  = stm.executeQuery(query)) {

                while (rSet.next()) {
                    int id = rSet.getInt(1);
                    String designacao = rSet.getString(2);
                    double peso2 = rSet.getDouble(3);
                    double precoBase = rSet.getDouble(4);

                    list.add(new Produto(id, designacao, peso2, precoBase));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Adiciona a lista de produtos da encomenda
     * @param prod 
     */
    public boolean addListaProdutos(Produto prod, int qntd) {
        peso = peso + prod.getPeso();
        preco = preco + prod.getPrecoBase();
        listEnc.add(prod);
        listQuant.add(qntd);
        return true;
    }
    
    public List<Integer> getListaQuantidade(){
        return listQuant;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public double getPeso(){
        return peso;
    }

    /**
     * Retorna a lista de produtos da encomenda
     * @return 
     */
    public List<Produto> getListaProdutos() {
        return listEnc;
    }

    /**
     * Remove os produtos da base de dadoa
     * @param lst 
     */
    public void removerProdutosEncomenda(List<Produto> lst, List<Integer> lst2) {
        for(int i=0;i<lst.size();i++){
            Integer y = lst2.get(i);
            while(y>0){
                y--;
                removerProdutosEncomenda(lst.get(i).getDesignacao());
            }
        }
    }
    
    /**
     * Remove os produtos da base de dadoa
     * @param des
     */
    private void removerProdutosEncomenda(String des) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call procRemoverProduto(?) }")) {

                callStmt.setString(1, des);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
