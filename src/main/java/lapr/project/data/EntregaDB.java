package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author beatr
 */
public class EntregaDB extends DataHandler {

    public int addEntrega(Entrega entrega) throws SQLException {

        CallableStatement callStmt = null;
        int id = 0;

        callStmt = getConnection().prepareCall("{ call AddEntrega(?,?,?,?) }");
        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        Timestamp dInicio = Timestamp.valueOf(entrega.getDataInicio());
        callStmt.setTimestamp(4, dInicio);
        Timestamp dFim = Timestamp.valueOf(entrega.getDataFim());
        callStmt.setTimestamp(5, dFim);
        callStmt.setInt(3, entrega.getIdScooter());
        callStmt.setInt(2, entrega.getidEstafeta());

        callStmt.execute();
        id = callStmt.getInt(1);

        closeAll();
        try {

            callStmt.close();

        } catch (SQLException | NullPointerException ex) {

            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

    public void addEncomendaEntrega(Entrega e, Encomenda enc) {
        addEncomendaEntrega(e.getIdEntrega(), enc.getId());
    }

    private void addEncomendaEntrega(int idEntrega, int idEncomenda) {

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

    /**
     * Devolve a lista de encomendas
     *
     * @return
     */
    public List<Entrega> getListaEntrega() {
        ArrayList<Entrega> list = new ArrayList<>();
        String query = "SELECT * FROM entrega";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            while (rSet.next()) {
                int idEntrega = rSet.getInt(1);
                int nif = rSet.getInt(2);
                int idscooter = rSet.getInt(3);
                Timestamp dataInicio = rSet.getTimestamp(4);
                Timestamp dataFim = rSet.getTimestamp(5);

                list.add(new Entrega(dataInicio.toString(), dataFim.toString(), idscooter, nif));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Entrega getEntregaById(int idEntrega) {
        String query = "SELECT * FROM entrega WHERE idEntrega = " + idEntrega;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                int nif = rSet.getInt(2);
                int idscooter = rSet.getInt(3);
                Timestamp dataInicio = rSet.getTimestamp(4);
                Timestamp dataFim = rSet.getTimestamp(5);

                return new Entrega(dataInicio.toString(), dataFim.toString(), idscooter, nif);
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

            return getEntregaById(v_res);
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
