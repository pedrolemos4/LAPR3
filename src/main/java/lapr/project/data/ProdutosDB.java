package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Estafeta;
import lapr.project.model.Produto;

public class ProdutosDB extends DataHandler {

    private Produto prod;
    private final DataHandler dataHandler;
    private List<Produto> lstProdutosEncomenda;
    private List<Produto> lstStock;

    private double peso;
    private double preco;

    public ProdutosDB() {
        this.dataHandler = DataHandler.getInstance();
        lstProdutosEncomenda = new ArrayList<>();
        lstStock = new ArrayList<>();
        peso = 0;
        preco = 0;
    }
    
    public List<Produto> getListaProdutos() {
        return lstProdutosEncomenda;
    }

    public int generateID() {
        return lstStock.size() + 1;
    }

    public Produto novoProduto(int id, String desig, double peso, double preco_base) {
        Produto prod = new Produto(id, desig, peso, preco_base);
        return prod;
        /*for(int i=0;i<lstStock.size();i++){
            if(lstStock.get(i).equals(prod1)){
                return true;
            }
        }
        return false;*/
    }

    public boolean validaProduto(Produto prod) {
        if (prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0) {
            return false;
        }
        return true;
    }

    public void registaProduto(Produto prod) {
        if (validaProduto(prod) == true) {
            addProduto(prod);
        }
    }

    public void addListaProds(Produto prod) {
        if (validaProduto(prod) == true) {
            preco = preco + prod.getPrecoBase();
            peso = peso + prod.getPeso();
            lstProdutosEncomenda.add(prod);
            lstStock.remove(prod);
        }
    }

    public void addProdutos(List<Produto> lprods){
        lstStock.addAll(lprods);
    }

    public void addProduto(Produto prod) {
        addProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase());
    }

    private void addProduto(String desig, double peso, double precoBase) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduto(?,?,?) }");

            callStmt.setString(1, desig);
            callStmt.setDouble(2, peso);
            callStmt.setDouble(3, precoBase);

            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(Produto prod) {
        atualizarProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase());
    }

    private void atualizarProduto(String desig, double peso, double precoBase) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call atualizarProduto(?,?,?) }");

            callStmt.setString(1, desig);
            callStmt.setDouble(2, peso);
            callStmt.setDouble(3, precoBase);

            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validaListaProdutos(List<Produto> lprods) {
        boolean check = true;
        for (Produto prod : lprods) {
            if(prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0){
                check = false;
            } else {
                check = true;
            }
        }
        return check;
    }

    public Produto getProdutoByID(int id) {
        String query = "SELECT * FROM produto p WHERE p.idProduto= " + id;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                String desig = rSet.getString(2);
                double peso = rSet.getDouble(3);
                double precoBase = rSet.getDouble(4);

                return new Produto(id,desig,peso,precoBase/*new EstadoEstafeta(id_estado_estafeta, designacao)*/);
            }
        } catch (SQLException e) {
            Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }

    /**
     * Lista do stock
     * @return 
     */
    public List<Produto> getLista(){
        return lstStock;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public double getPeso(){
        return peso;
    }
    
}
