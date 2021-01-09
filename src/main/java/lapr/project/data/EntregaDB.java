package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
/**
 *
 * @author beatr
 */
public class EntregaDB extends DataHandler{
    
    /**
     * Devolve a lista de encomendas
     * @return 
     */
    public List<Encomenda> getListaEncomenda() {
        ArrayList<Encomenda> list = new ArrayList<>();
        String query = "SELECT * FROM encomenda";
        
        Statement stm = null;
        ResultSet rSet = null;
        
        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            
            while (rSet.next()) {
                int idEncomenda = rSet.getInt(1);
                Timestamp dataPedida = rSet.getTimestamp(2);
                double preco = rSet.getDouble(3);
                double pesoEncomenda = rSet.getDouble(4);
                double taxa = rSet.getDouble(5);
                int estado = rSet.getInt(6);
                int nif = rSet.getInt(7);
                                
                list.add(new Encomenda(nif, dataPedida.toString(), preco, pesoEncomenda, taxa, estado));
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void addEntrega(Entrega entrega) {
        addEntrega(entrega.getDataInicio(),entrega.getDataFim(),entrega.getIdScooter(), entrega.getidEstafeta());
    }


    private void addEntrega(String dataInicio, String dataFim, int scooter, int estafeta) {

        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "addSailor" armazenado
             *  na BD.
             *
             *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER)
             *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call addEntrega(?,?,?,?) }");
            Timestamp dInicio = Timestamp.valueOf(dataInicio);
            callStmt.setTimestamp(1, dInicio);
            Timestamp dFim = Timestamp.valueOf(dataFim);
            callStmt.setTimestamp(2, dFim);
            callStmt.setInt(3, scooter);
            callStmt.setInt(4, estafeta);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
