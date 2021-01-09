package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.EstadoEstafeta;
import lapr.project.model.Estafeta;
import lapr.project.model.Produto;
import oracle.jdbc.OracleTypes;

public class EstafetaDB extends DataHandler{
    Estafeta est;
    private final List<Estafeta> lstEstafetas;

    public EstafetaDB() {
        lstEstafetas = new ArrayList<>();
    }

    public List<Estafeta> getLstEstafetas() {
        return lstEstafetas;
    }

    public Estafeta novoEstafeta(int nif, String nome, String email, double peso, int nss, String pwd) {
        est = new Estafeta(nif, nome, email, peso, nss, pwd, 1 /*new EstadoEstafeta(1,"disponível")*/);
        return est;
    }

    public void registaEstafeta(Estafeta est) {
        if (validaEstafeta(est)){
            addEstafeta(est);
        }
    }

    public boolean validaEstafeta(Estafeta est) {
        if(est.getPesoEstafeta() < 0 || est.getNome() == null || est.getPassword() == null || est.getEstado() == 0/*null*/){
            return false;
        }
        return true;
    }

    public void addEstafeta(Estafeta est) {
        addEstafeta(est.getNIF(), est.getEstado(), est.getPesoEstafeta());
    }

    private void addEstafeta(int nif, int estadoEstafeta, double peso) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addEstafeta(?,?,?) }");

            callStmt.setInt(1, nif);
            callStmt.setInt(2, estadoEstafeta);
            callStmt.setDouble(3, peso);

            callStmt.execute();
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estafeta getEstafetaByEmail(String email) {
        String query = "SELECT * FROM estafeta e INNER JOIN utilizador u ON e.UtilizadorNIF = u.NIF WHERE e.email= " + email;
        
        Statement stm = null;
        ResultSet rSet = null;
        
        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            
            if (rSet.next()) {
                double pesoEstafeta = rSet.getDouble(1);
                int NIF = rSet.getInt(2);
                String nome = rSet.getString(3);
                String emailE = rSet.getString(4);
                int numeroSegurancaSocial = rSet.getInt(5);
                String password = rSet.getString(6);
                int id_estado_estafeta = rSet.getInt(7);
                String designacao = rSet.getString(8);

                return new Estafeta(NIF, nome, emailE, pesoEstafeta, numeroSegurancaSocial, password, id_estado_estafeta/*new EstadoEstafeta(id_estado_estafeta, designacao)*/);
            }
        } catch (SQLException e) {
            Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }
}
