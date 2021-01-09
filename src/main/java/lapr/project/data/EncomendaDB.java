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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.EstadoEncomenda;
import lapr.project.model.Produto;

/**
 *
 * @author pedro
 */
public class EncomendaDB extends DataHandler{

    private Encomenda enc;
    private final ProdutosDB produtoDB;
    private final DataHandler dataHandler;
    private List<Encomenda> lstEnc;
    
    public EncomendaDB(){
        this.dataHandler = DataHandler.getInstance();
        produtoDB = new ProdutosDB();
        lstEnc = new ArrayList<>();
    }
    
    /**
     * Cria nova encomenda
     */
    public void novaEncomenda() {
        EstadoEncomenda ee = new EstadoEncomenda(1, "encomendado");
        //enc = new Encomenda(new Cliente(), getData(), produtoDB.getPreco(), produtoDB.getPeso(), 1.0, ee);
//        enc.setLst(getListaProdutos());
    }
    
    /**
     * Devolve a data quando a encomenda é pedida
     * @return 
     */
    public String getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return date.toString();
    }

    /**
     * Devolve a lista de produtos da encomenda
     * @return 
     */
    public List<Produto> getListaProdutos() {
        return produtoDB.getListaProdutos();
    }
    
    /**
     * Valida a encomenda
     * @param enc
     * @return 
     */
    public boolean validaEncomenda(Encomenda enc){
        if(enc.getNif()!=0 && enc.getDataPedida()!= null && enc.getEstado()!=0 && enc.getId()!= 0 && enc.getLst()!= null && enc.getPesoEncomenda()>0 && enc.getPreco()>0 && enc.getTaxa()>0){
            return true;
        }
        return false;
    }
    
    /**
     * Regista e encomenda se for válida
     * @param enc
     * @return 
     */
    public boolean registaEncomenda(Encomenda enc) {
        if(validaEncomenda(enc)){
            addEncomenda(enc);
        }
        return false;
    }

    /**
     * Adiciona a lista de encomendas a encomenda
     * @param enc 
     */
    public void addEncomenda(Encomenda enc) {
        addEncomenda(enc.getId(), enc.getNif(), enc.getDataPedida(), enc.getPreco(), enc.getPesoEncomenda(), enc.getTaxa(), enc.getEstado(), enc.getLst());
    }
    
    /**
     * Adiciona a encomenda à base de dados
     * @param id
     * @param cliente
     * @param dataPedida
     * @param preco
     * @param pesoEncomenda
     * @param taxa
     * @param estado
     * @param lst 
     */
    private void addEncomenda(int id, int nif, String dataPedida, double preco, double pesoEncomenda, double taxa, int estado, List<Produto> lst) {

        try {
            openConnection();
            
            CallableStatement callStmt = getConnection().prepareCall("{ call addEncomenda(?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setInt(2, nif);
            callStmt.setString(3, dataPedida);
            callStmt.setDouble(4, preco);
            callStmt.setDouble(5, pesoEncomenda);
            callStmt.setDouble(6, taxa);
            callStmt.setInt(7, estado);
            
            callStmt.execute();
            
            CallableStatement callStmt1 = getConnection().prepareCall("{ call addEncomendaProduto(?,?,?,?) }");
            
            for(int i=0;i<lst.size();i++){
                callStmt1.setInt(1, id);
                callStmt1.setInt(2, lst.get(i).getId());
            }
            
            closeAll();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Devolve a lista de encomendas
     * @return 
     */
    public List<Encomenda> getListaEncomenda() {
        ArrayList<Encomenda> list = new ArrayList<>();
        String query = "SELECT * FROM encomenda WHERE EstadoEncomendaidEstadoEncomenda = 1";
        
        Statement stm = null;
        ResultSet rSet = null;
        
        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            
            while (rSet.next()) {
                int idEncomenda = rSet.getInt(1);
                Timestamp dataPedida = rSet.getTimestamp(2);
                double preco = rSet.getDouble(3);
                double pesoEncomenda = rSet.getDouble(4);
                double taxa = rSet.getDouble(5);
                int estado = rSet.getInt(6);
                int nif = rSet.getInt(7);
                                
                list.add(new Encomenda(nif, dataPedida.toString(), preco, pesoEncomenda, taxa, estado));
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
