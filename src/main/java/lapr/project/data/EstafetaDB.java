package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Estafeta;
import lapr.project.model.Produto;

public class EstafetaDB extends DataHandler{
    Estafeta est;
    private final List<Estafeta> lstEstafetas;

    public EstafetaDB() {
        lstEstafetas = new ArrayList<>();
    }

    public List<Estafeta> getListaEstafetas() {
        ArrayList<Estafeta> list = new ArrayList<>();
        String query = "SELECT * FROM estafeta";

        try (Statement stm = getConnection().createStatement()){
            try(ResultSet rSet  = stm.executeQuery(query)) {

                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    int idEstado = rSet.getInt(2);
                    double peso = rSet.getDouble(3);

                    list.add(new Estafeta(nif, idEstado, peso));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
        lstEstafetas.add(est);
        addUtilizador(est.getNIF(), est.getNome(), est.getEmail(), est.getNumeroSegurancaSocial(), est.getPassword());
    }

    private void addEstafeta(int nif, int estadoEstafeta, double peso) {

        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call addEstafeta(?,?,?) }")) {

                callStmt.setInt(1, nif);
                callStmt.setInt(2, estadoEstafeta);
                callStmt.setDouble(3, peso);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUtilizador(int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        try {
            openConnection();
            try (CallableStatement callStmt = getConnection().prepareCall("{ call addUtilizador(?,?,?,?,?) }")) {
                callStmt.setInt(1, NIF);
                callStmt.setString(2, nome);
                callStmt.setString(3, email);
                callStmt.setInt(4, numeroSegurancaSocial);
                callStmt.setString(5, password);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEstafeta(Estafeta est) {
        if (validaEstafeta(est)){
            atualizarEstafeta(est.getNIF(), est.getEstado(), est.getPesoEstafeta());
            atualizarUtilizador(est.getNIF(), est.getNome(), est.getEmail(), est.getNumeroSegurancaSocial(), est.getPassword());
        }
    }

    private void atualizarEstafeta(int nif, int estadoEstafeta, double peso) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call atualizarEstafeta(?,?,?) }")) {

                callStmt.setInt(1, nif);
                callStmt.setInt(2, estadoEstafeta);
                callStmt.setDouble(3, peso);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void atualizarUtilizador(int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call atualizarUtilizador(?,?,?,?,?) }")) {

                callStmt.setInt(1, NIF);
                callStmt.setString(2, nome);
                callStmt.setString(3, email);
                callStmt.setInt(4, numeroSegurancaSocial);
                callStmt.setString(5, password);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estafeta getEstafetaByEmail(String email) {
        String query = "SELECT * FROM estafeta e INNER JOIN utilizador u ON e.UtilizadorNIF = u.NIF WHERE e.email= " + email;

        try (Statement stm = getConnection().createStatement()){
            try(ResultSet rSet  = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int NIF = rSet.getInt(1);
                    int id_estado_estafeta = rSet.getInt(2);
                    double peso = rSet.getDouble(3);

                    return new Estafeta(NIF, id_estado_estafeta, peso/*new EstadoEstafeta(id_estado_estafeta, designacao)*/);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
