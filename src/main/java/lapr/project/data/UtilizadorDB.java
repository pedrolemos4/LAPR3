/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Cliente;
import lapr.project.model.Utilizador;
import oracle.jdbc.OracleTypes;

/**
 * @author josep
 */
public class UtilizadorDB extends DataHandler {

    private final DataHandler dataHandler;

    public UtilizadorDB() {
        this.dataHandler = DataHandler.getInstance();
    }

    public Utilizador novoUtilizador(int nif, String nome, String email, int numeroSegurancaSocial, String password) {
        return new Utilizador(nif, nome, email, numeroSegurancaSocial, password);
    }

    public void registaUtilizador(Utilizador user) {
        if (validaUtilizador(user)) {
            addUtilizador(user);
        }
    }

    public boolean validaUtilizador(Utilizador user) {
        return user != null;
    }

    public void addUtilizador(Utilizador user) {
        addUtilizador(user.getNIF(), user.getNome(), user.getEmail(), user.getNumeroSegurancaSocial(), user.getPassword());
    }

    public void addUtilizador(int nif, String nome, String email, int numeroSegurancaSocial, String password) {
        try {
            openConnection();
            try (CallableStatement callStmt = getConnection().prepareCall("{ call addUtilizador(?,?,?,?,?) }")) {
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

    public List<Utilizador> getLstUtilizador() {
        ArrayList<Utilizador> list = new ArrayList<>();
        String query = "SELECT * FROM utilizador";

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    String nome = rSet.getString(2);
                    String email = rSet.getString(3);
                    int numeroSegurancaSocial = rSet.getInt(4);
                    String password = rSet.getString(5);
                    list.add(new Utilizador(nif, nome, email, numeroSegurancaSocial, password));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Validates if the loggin has been successfull
     *
     * @param email    username or email
     * @param password password
     * @return if the loggin was successfull or not
     */
    public int validateLogin(String email, String password) {
        int result = 0;
        try (CallableStatement callStmt = dataHandler.getConnection().prepareCall("{ ? = call funcValidateLogin(?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, email);
            callStmt.setString(3, password);
            callStmt.execute();

            result = callStmt.getInt(1);


        } catch (SQLException e) {
            Logger.getLogger(UtilizadorDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return result;
    }

    public Cliente getByID(int nif) {
        String query = "SELECT * FROM cliente p,utilizador s "
                + "WHERE p.Utilizadornif= " + nif 
                + " AND s.nif = " + nif;

        try (Statement stm = getConnection().createStatement()){
            try(ResultSet rSet  = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int creditos = rSet.getInt(2);
                    String morada = rSet.getString(3);
                    int numCC = rSet.getInt(4);
                    String nome = rSet.getString(6);
                    String email = rSet.getString(7);
                    int nSegSocial = rSet.getInt(8);
                    String password = rSet.getString(9);

                    return new Cliente(nif, nome, email, nSegSocial, creditos, morada, numCC, password);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(UtilizadorDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
