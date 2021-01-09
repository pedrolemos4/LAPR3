/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;

/**
 *
 * @author pedro
 */
public class ReciboDB extends DataHandler{

    private final DataHandler dataHandler;
    private List<Recibo> lst;
    
    public ReciboDB(){
        this.dataHandler = DataHandler.getInstance();
        lst = new ArrayList<>();
    }
    
    public boolean registaRecibo(Recibo rec) {
        if(validaRecibo(rec)){
            addRecibo(rec);
        }
        return false;
    }

    private boolean validaRecibo(Recibo rec) {
        if(rec.getData() == null || rec.getId() < 0 || rec.getNif() < 0){
            return false;
        }
        return true;
    }
    
    /**
     * Adiciona o recibo
     * @param enc 
     */
    public void addRecibo(Recibo rec) {
        addRecibo(rec.getId(), rec.getData(), rec.getNif());
    }
    
    /**
     * Adiciona o recibo à base de dados
     * @param id
     * @param data
     * @param lst
     * @param nif 
     */
    private void addRecibo(int id, String data, int nif){
        
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addEncomenda(?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setInt(2, nif);
            callStmt.setString(3, data);

            callStmt.execute();

            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void registaRecibo(Recibo rec, Produto prod) {
        try {
            openConnection();

            CallableStatement callStmt1 = getConnection().prepareCall("{ call addLinhaRecibo(?,?,?,?) }");

            callStmt1.setInt(1, rec.getId());
            callStmt1.setInt(2, prod.getId());

            callStmt1.execute();
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
