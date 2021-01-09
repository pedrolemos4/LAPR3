package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Estacionamento;
import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Tiago
 */
public class ScooterDB extends DataHandler {

    public ScooterDB() {
    }

    public int addScooter(Scooter scooter) throws SQLException {
        CallableStatement callStmt = null;
        int id = 0;

        callStmt = getConnection().prepareCall("{ ? = call addScooter(?,?,?,?,?,?) }");
        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        callStmt.setString(2, scooter.getDescricao());
        callStmt.setDouble(3, scooter.getPercentagemBateria());
        callStmt.setDouble(4, scooter.getPesoMaximo());
        callStmt.setDouble(5, scooter.getPesoScooter());
        callStmt.setDouble(6, scooter.getPotencia());
        callStmt.setInt(7, scooter.getEstadoScooter().getId());
        callStmt.execute();

        id = callStmt.getInt(1);

        try {

            callStmt.close();

        } catch (SQLException | NullPointerException ex) {

            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

    /**
     * Devolve a lista de scooters
     *
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
                String descricao = rSet.getString(2);
                double percentagemBateria = rSet.getDouble(3);
                double pesoMaximo = rSet.getDouble(4);
                double pesoScooter = rSet.getDouble(5);
                double potencia = rSet.getDouble(6);
                int idEstado = rSet.getInt(7);

                list.add(new Scooter(descricao, percentagemBateria, pesoMaximo, pesoScooter, potencia, idEstado));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Devolve a lista de scooters
     *
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
                String descricao = rSet.getString(2);
                double percentagemBateria = rSet.getDouble(3);
                double pesoMaximo = rSet.getDouble(4);
                double pesoScooter = rSet.getDouble(5);
                double potencia = rSet.getDouble(6);
                int idEstado = rSet.getInt(7);

                return new Scooter(descricao, percentagemBateria, pesoMaximo, pesoScooter, potencia, idEstado);
            }

        } catch (SQLException e) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }

    public boolean updateScooter(Scooter scooter) throws SQLException {
        CallableStatement callSmt = null;

        callSmt = getConnection().prepareCall("{ call updateScooter(?,?,?,?,?,?,?) }");

        callSmt.setInt(1, scooter.getId());
        callSmt.setString(2, scooter.getDescricao());
        callSmt.setDouble(3, scooter.getPercentagemBateria());
        callSmt.setDouble(4, scooter.getPesoMaximo());
        callSmt.setDouble(5, scooter.getPesoScooter());
        callSmt.setDouble(6, scooter.getPotencia());
        callSmt.setInt(7, scooter.getEstadoScooter().getId());
        callSmt.execute();

        try {

            callSmt.close();

        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }

        return false;

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
            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());

        }
        return removed;
    }

    public void addEstacionamentoScooter(Estacionamento estac, Scooter scoot){
        addEstacionamentoScooter(estac.getNumeroLote(), scoot.getId());
    }

    private void addEstacionamentoScooter(int numLote, int idScooter){

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call AddEstacionamentoScooter(?,?) }");
            callStmt.setInt(1, numLote);
            callStmt.setInt(2, idScooter);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
