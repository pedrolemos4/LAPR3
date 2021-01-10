package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;

/**
 *
 * @author beatr
 */
public class EntregaDB extends DataHandler{
    
    public void addEntrega(Entrega entrega) {
        addEntrega(entrega.getDataInicio(),entrega.getDataFim(),entrega.getIdScooter(), entrega.getidEstafeta());
    }

    private void addEntrega(String dataInicio, String dataFim, int scooter, int estafeta) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call AddEntrega(?,?,?,?) }");
            Timestamp dInicio = Timestamp.valueOf(dataInicio);
            callStmt.setTimestamp(3, dInicio);
            Timestamp dFim = Timestamp.valueOf(dataFim);
            callStmt.setTimestamp(4, dFim);
            callStmt.setInt(1, scooter);
            callStmt.setInt(2, estafeta);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addEncomendaEntrega(Entrega e, Encomenda enc){
        addEncomendaEntrega(e.getIdEntrega(), enc.getId());
    }
    
    private void addEncomendaEntrega(int idEntrega, int idEncomenda){
        
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call AddEncomendaEntrega(?,?) }");
            callStmt.setInt(1, idEntrega);
            callStmt.setInt(2, idEncomenda);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Entrega getEntregaByID(int entregaID){
        String query = "SELECT * FROM entrega WHERE identrega = " + entregaID;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                int idEstafeta = rSet.getInt(2);
                int idScooter = rSet.getInt(3);
                String dataInicio = rSet.getString(4);
                String dataFim = rSet.getString(5);

                return new Entrega(dataInicio, dataFim, idScooter, idEstafeta);
            }

        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }

    public Entrega getEntregaAtiva(String email){

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call getEntregaAtiva(?) }");
            callStmt.setString(1,email);

            callStmt.execute();

            int v_res = callStmt.getInt(1);

            closeAll();

            return getEntregaByID(v_res);
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
