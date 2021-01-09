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
import lapr.project.model.Utilizador;

/**
 *
 * @author josep
 */
public class UtilizadorDB extends DataHandler {

    Utilizador user;
    private final DataHandler dataHandler;

    public UtilizadorDB() {
        this.dataHandler = DataHandler.getInstance();
    }

    public Utilizador novoUtilizador(int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        user = new Utilizador(NIF, nome, email, numeroSegurancaSocial, password);
        return user;
    }

    public void registaUtilizador(Utilizador user) {
        if (validaUtilizador(user)) {
            addUtilizador(user);
        }
    }

    private boolean validaUtilizador(Utilizador user) {
        return user != null;
    }

    public void addUtilizador(Utilizador user) {
        addUtilizador(user.getNIF(), user.getNome(), user.getEmail(), user.getNumeroSegurancaSocial(), user.getPassword());
    }

    private void addUtilizador(int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addUtilizador(?,?,?,?,?) }");
            callStmt.setInt(1, NIF);
            callStmt.setString(2, nome);
            callStmt.setString(3, email);
            callStmt.setInt(4, numeroSegurancaSocial);
            callStmt.setString(5, password);
            callStmt.execute();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilizador> getLstUtilizador() {
        ArrayList<Utilizador> list = new ArrayList<>();
        String query = "SELECT * FROM utilizador";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            while (rSet.next()) {
                int NIF = rSet.getInt(1);
                String nome = rSet.getString(2);
                String email = rSet.getString(3);
                int numeroSegurancaSocial = rSet.getInt(4);
                String password = rSet.getString(5);
                list.add(new Utilizador(NIF, nome, email, numeroSegurancaSocial, password));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
