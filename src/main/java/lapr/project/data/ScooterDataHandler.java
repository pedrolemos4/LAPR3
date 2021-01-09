package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

        callStmt = getConnection().prepareCall("{ ? = call funcAddScooter(?,?,?,?,?,?) }");
        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        callStmt.setDouble(2, scooter.getPercentagemBateria());
        callStmt.setDouble(3, scooter.getPesoMaximo());
        callStmt.setDouble(4, scooter.getPesoScooter());
        callStmt.setDouble(5, scooter.getPotencia());
        callStmt.setInt(6, scooter.getEstadoScooter().getId());
        callStmt.execute();

        id = callStmt.getInt(1);

        try {

            callStmt.close();

        } catch (SQLException | NullPointerException ex) {

            Logger.getLogger(ScooterDataHandler.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }
    
    /**
     * Devolve a lista de scooters
     * @return 
     */
    public List<Scooter> getListaScooter() {
        ArrayList<Scooter> list = new ArrayList<>();
        String query = "SELECT * FROM scooter WHERE EstadoScooterid = 1";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            while (rSet.next()) {
                int id = rSet.getInt(1);
                double percentagemBateria = rSet.getDouble(2);
                double pesoMaximo = rSet.getDouble(3);
                double pesoScooter = rSet.getDouble(4);
                double potencia = rSet.getDouble(5);
                int idEstado = rSet.getInt(6);

                list.add(new Scooter(percentagemBateria, pesoMaximo, pesoScooter, potencia, idEstado));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * Devolve a lista de scooters
     * @param idScooter
     * @return 
     */
    public Scooter getScooterById(int idScooter) {
        String query = "SELECT * FROM scooter WHERE idScooter = " + idScooter;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                double percentagemBateria = rSet.getDouble(2);
                double pesoMaximo = rSet.getDouble(3);
                double pesoScooter = rSet.getDouble(4);
                double potencia = rSet.getDouble(5);
                int idEstado = rSet.getInt(6);
                
                return new Scooter(percentagemBateria, pesoMaximo, pesoScooter, potencia, idEstado);
            }

        } catch (SQLException e) {
            Logger.getLogger(ScooterDataHandler.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ScooterDataHandler.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }

    public boolean removeScooter(int id) throws SQLException {
        boolean removed = false;
        CallableStatement callV = null;

        callV = getConnection().prepareCall("{ call removeVehicle(?) }");

        callV.setInt(1, id);

        callV.execute();

        removed = true;

        try {

            callV.close();

        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(ScooterDataHandler.class.getName()).log(Level.WARNING, ex.getMessage());

        }
        return removed;
    }

}
