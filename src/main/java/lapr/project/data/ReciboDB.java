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
        if (rec.getData() == null || rec.getId() < 0 || rec.getNif() < 0) {
            return false;
        }
        return true;
    }

    /**
     * Adiciona o recibo
     *
     * @param enc
     */
    public void addRecibo(Recibo rec) throws SQLException {
        addRecibo(rec.getData(), rec.getPreco(), rec.getNif());
    }

    /**
     * Adiciona o recibo à base de dados
     *
     * @param id
     * @param data
     * @param lst
     * @param nif
     */
    private int addRecibo(String data, double preco, int nif) throws SQLException {
        CallableStatement callStmt = null;
        int id=0;
        
        openConnection();

        callStmt = getConnection().prepareCall("{ call addEncomenda(?,?,?) }");

        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        callStmt.setInt(2, nif);
        callStmt.setDouble(3, preco);
        callStmt.setString(4, data);
        
        id = callStmt.getInt(1);
        
        callStmt.execute();
        try {
            closeAll();

        } catch (NullPointerException ex) {

            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

    public void registaRecibo(Recibo rec, Produto prod) {
        try {
            openConnection();

            CallableStatement callStmt1 = getConnection().prepareCall("{ call addLinhaRecibo(?,?) }");

            callStmt1.setInt(1, rec.getId());
            callStmt1.setInt(2, prod.getId());

            callStmt1.execute();
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
