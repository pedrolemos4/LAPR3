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
public class ScooterDataHandler extends DataHandler{


    public ScooterDataHandler() {
    }
    
    public int addScooter(Scooter scooter) throws SQLException {
        CallableStatement callStmt = null;
        int id = 0;

        callStmt = getConnection().prepareCall("{ ? = call funcAddScooter(?,?,?,?,?,?,?,?,?,?,?) }");
        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        callStmt.setDouble(2, scooter.getPercentagemBateria());
        callStmt.setDouble(3, scooter.getPesoMaximo());
        callStmt.setDouble(4, scooter.getPesoScooter());
        callStmt.setDouble(5, scooter.getPotencia());
        callStmt.setInt/*String*/(6, scooter.getEstadoScooter()/*.getDesignacao()*/);
        callStmt.execute();

        id = callStmt.getInt(1);

        try {

            callStmt.close();

        } catch (SQLException | NullPointerException ex) {

            Logger.getLogger(ScooterDataHandler.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

}
