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
    private List<Produto> listEnc;

    private double peso;
    private double preco;

    public ProdutosDB() {
        this.dataHandler = DataHandler.getInstance();
        listEnc = new ArrayList<>();
        peso = 0;
        preco = 0;
    }
    public Produto novoProduto(String desig, double peso, double preco_base) {
        Produto prod = new Produto(desig, peso, preco_base);
        return prod;
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
        if (validaProduto(prod)){
            atualizarProduto(prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase(), prod.getId());
        }
    }

    private void atualizarProduto(String desig, double peso, double precoBase, int id) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call atualizarProduto(?,?,?,?) }");

            callStmt.setString(1, desig);
            callStmt.setDouble(2, peso);
            callStmt.setDouble(3, precoBase);
            callStmt.setInt(4, id);

            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validaListaProdutos(List<Produto> lprods) {
        boolean check = true;
        for (Produto prod : lprods) {
            if (prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0) {
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

                return new Produto(desig, peso, precoBase/*new EstadoEstafeta(id_estado_estafeta, designacao)*/);
            }
        } catch (SQLException e) {
            Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
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
     *
     * @return
     */
    public List<Produto> getLista() {
        ArrayList<Produto> list = new ArrayList<>();
        String query = "SELECT * FROM produto";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            while (rSet.next()) {
                String designacao = rSet.getString(2);
                double peso = rSet.getDouble(3);
                double precoBase = rSet.getDouble(4);

                list.add(new Produto(designacao, peso, precoBase));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addListaProdutos(int id) {
        Produto produtoByID = getProdutoByID(id);
        peso = peso + produtoByID.getPeso();
        preco = preco + produtoByID.getPrecoBase();
        listEnc.add(produtoByID);
    }

    public List<Produto> getListaProdutos(){
        return listEnc;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public double getPeso(){
        return peso;
    }
    
}
