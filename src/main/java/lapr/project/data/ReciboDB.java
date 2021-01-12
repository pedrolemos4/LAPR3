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
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pedro
 */
public class ReciboDB extends DataHandler {

    private final DataHandler dataHandler;
    private List<Recibo> lst;

    public ReciboDB() {
        this.dataHandler = DataHandler.getInstance();
        lst = new ArrayList<>();
    }

    public boolean registaRecibo(Recibo rec) throws SQLException {
        if (validaRecibo(rec)) {
            addRecibo(rec);
        }
        return false;
    }

    private boolean validaRecibo(Recibo rec) {
        return !(rec.getData() == null || rec.getId() < 0 || rec.getNif() < 0 || rec.getIdEncomenda() < 0);
    }


    public void addRecibo(Recibo rec) throws SQLException {
        addRecibo(rec.getData(), rec.getPreco(), rec.getNif(), rec.getIdEncomenda());
    }

    private int addRecibo(String data, double preco, int nif, int idEncomenda) throws SQLException {
        int id=0;
        
        openConnection();

        try (CallableStatement callStmt = getConnection().prepareCall("{ call addEncomenda(?,?,?,?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, nif);
            callStmt.setDouble(3, preco);
            callStmt.setString(4, data);
            callStmt.setInt(5, idEncomenda);

            id = callStmt.getInt(1);

            callStmt.execute();
        }
        try {
            closeAll();

        } catch (NullPointerException ex) {

            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }
    
    public boolean registaRecibo(Recibo rec, Produto prod){
        if(validaRecibo(rec)==true){
            registaRecibo(rec.getId(), prod.getId());
            return true;
        }
        return false;
    }
    
    private int registaRecibo(int rec, int prod) {
        int id=0;
        try {
            openConnection();

            try (CallableStatement callStmt1 = getConnection().prepareCall("{ call addLinhaRecibo(?,?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt1.setInt(2, rec);
                callStmt1.setInt(3, prod);

                id = callStmt1.getInt(1);
                
                callStmt1.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
