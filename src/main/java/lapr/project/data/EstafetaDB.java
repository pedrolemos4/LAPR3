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

public class EstafetaDB extends DataHandler{
    Estafeta est;
    private final List<Estafeta> lstEstafetas;

    public EstafetaDB() {
        lstEstafetas = new ArrayList<>();
    }

    /**
     * Devolve a lista de estafetas existentes
     * @return lista de estafetas
     */
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

    /**
     * Cria um novo estafeta
     * @param nif nif do estafeta
     * @param nome nome do estafeta
     * @param email email do estafeta
     * @param peso peso do estafeta
     * @param nss número de segurança social do estafeta
     * @param pwd password do estafeta
     * @return novo estafeta
     */
    public Estafeta novoEstafeta(int nif, String nome, String email, double peso, int nss, String pwd) {
        est = new Estafeta(nif, nome, email, peso, nss, pwd, 1 /*new EstadoEstafeta(1,"disponível")*/);
        return est;
    }

    /**
     * Regista um estafeta na base de dados
     * @param est estafeta a ser registado
     * @return true se o estafeta for registado, falso se não
     */
    public boolean registaEstafeta(Estafeta est) {
        if (validaEstafeta(est)){
            addEstafeta(est);
            return true;
        }
        return false;
    }

    /**
     * Verifica se o estafeta é valido
     * @param est estafeta a verificar
     * @return true se o estafeta for válido, falso se não
     */
    public boolean validaEstafeta(Estafeta est) {
        return !(est.getPesoEstafeta() < 0 || est.getNome() == null || est.getPassword() == null || est.getEstado() == 0/*null*/);
    }

    /**
     * Adiciona o estafeta à base de dados
     * @param est estafeta a ser adicionado
     * @return true
     */
    public boolean addEstafeta(Estafeta est) {
        UtilizadorDB userDB = new UtilizadorDB();
        userDB.addUtilizador(est.getNIF(), est.getNome(), est.getEmail(), est.getNumeroSegurancaSocial(), est.getPassword());
        addEstafeta(est.getNIF(), est.getEstado(), est.getPesoEstafeta());
        lstEstafetas.add(est);
        return true;
    }

    /**
     * Adiciona o estafeta à base de dados
     * @param nif nif do estafeta
     * @param estadoEstafeta estado do estafeta
     * @param peso peso do estafeta
     */
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

    /**
     * Atualiza as informações de um estafeta na base de dados
     * @param est estafeta a ser alterado
     * @return true se o estafeta for atualizado, falso se não
     */
    public boolean atualizarEstafeta(Estafeta est) {
        if (validaEstafeta(est)){
            atualizarEstafeta(est.getNIF(), est.getEstado(), est.getPesoEstafeta());
            atualizarUtilizador(est.getNIF(), est.getNome(), est.getEmail(), est.getNumeroSegurancaSocial(), est.getPassword());
            return true;
        }
        return false;
    }

    /**
     * Atualiza as informações de um estafeta na base de dados
     * @param nif nif do estafeta
     * @param estadoEstafeta estado do estafeta
     * @param peso peso do estafeta
     */
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

    /**
     * Atualiza as informações de um utilizador na base de dados
     * @param nif nif do utilizador
     * @param nome nome do utilizador
     * @param email email do utilzador
     * @param numeroSegurancaSocial número de segurança social do utilizador
     * @param password password do utilizador
     */
    private void atualizarUtilizador(int nif, String nome, String email, int numeroSegurancaSocial, String password) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call atualizarUtilizador(?,?,?,?,?) }")) {

                callStmt.setInt(1, nif);
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

    /**
     * Devolve o estafeta cujo nif é igual ao recebido por parâmetro
     * @param nif nif do estafeta
     * @return estafeta
     */
    public Estafeta getEstafetaByNIF(int nif) {
        String query = "SELECT * FROM estafeta e INNER JOIN utilizador u ON e.UtilizadorNIF = u.NIF WHERE u.NIF= " + nif;

        try (Statement stm = getConnection().createStatement()){
            try(ResultSet rSet  = stm.executeQuery(query)) {

                if (rSet.next()) {
                    nif = rSet.getInt(1);
                    int idEstadoEstafeta = rSet.getInt(2);
                    double peso = rSet.getDouble(3);

                    return new Estafeta(nif, idEstadoEstafeta, peso/*new EstadoEstafeta(id_estado_estafeta, designacao)*/);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EstafetaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
