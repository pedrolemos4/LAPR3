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

    public Entrega getEntregaAtiva(String email){

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call getEntregaAtiva(?) }");
            callStmt.setString(1,email);

            callStmt.execute();

            closeAll();
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
