/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Tiago
 */
public class ScooterDataHandler {

    private final DataHandler dataHandler;

    public ScooterDataHandler() {
        this.dataHandler = DataHandler.getInstance();
    }
    
    public int addScooter(Scooter scooter) throws SQLException {
        CallableStatement callStmt = null;
        int id = 0;

        callStmt = dataHandler.getConnection().prepareCall("{ ? = call funcAddScooter(?,?,?,?,?,?,?,?,?,?,?) }");
        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        callStmt.setDouble(2, scooter.getPercentagemBateria());
        callStmt.setDouble(3, scooter.getPesoMaximo());
        callStmt.setDouble(4, scooter.getPesoScooter());
        callStmt.setDouble(5, scooter.getPotencia());
        callStmt.setString(6, scooter.getEstadoScooter().getDesignacao());
        callStmt.execute();

        id = callStmt.getInt(1);

        try {

            callStmt.close();

        } catch (SQLException ex) {

            Logger.getLogger(ScooterDataHandler.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

}
