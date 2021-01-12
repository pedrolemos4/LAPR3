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

/**
 *
 * @author josep
 */
public class ClienteDB extends DataHandler {

    public ClienteDB() {
    }

    /**
     * Cria um novo cliente
     *
     * @param nif nif do cliente
     * @param nome nome do cliente
     * @param email email do cliente
     * @param numeroSegurancaSocial número de segurança social do cliente
     * @param creditos
     * @param password password do cliente
     * @param morada morada do cliente
     * @param numCC número do cartão de cidadão do cliente
     * @return o novo cliente criado
     */
    public Cliente novoCliente(int nif, String nome, String email, int numeroSegurancaSocial, int creditos, String morada, int numCC, String password) {
        return new Cliente(nif, nome, email, numeroSegurancaSocial, creditos, morada, numCC, password);
    }

    /**
     * Regista o cliente
     *
     * @param cl o cliente
     * @return
     */
    public boolean registaCliente(Cliente cl) {
        if (validaCliente(cl)) {
            addCliente(cl);
        }
        return true;
    }

    /**
     * Valida o cliente recebido
     *
     * @param cl o cliente
     * @return true se os dados do cliente forem válidos
     */
    public boolean validaCliente(Cliente cl) {
        return !(cl == null || cl.getClienteNIF() < 0 || cl.getNumCartaoCredito() < 0);
    }

    /**
     * Adiciona o cliente à base de dados
     *
     * @param cl o cliente
     * @return
     */
    public boolean addCliente(Cliente cl) {
        addCliente(cl.getNIF(), cl.getCreditos(), cl.getEnderecoMorada(), cl.getNumCartaoCredito());
        UtilizadorDB userDB = new UtilizadorDB();
        userDB.addUtilizador(cl.getNIF(), cl.getNome(), cl.getEmail(), cl.getNumeroSegurancaSocial(), cl.getPassword());
        return true;
    }

    /**
     * Adiciona o cliente à base de dados
     *
     * @param nif nif do cliente
     * @param creditos créditos do cliente
     * @param enderecoMorada morada do cliente
     * @param numCC número do cartão de cidadão do cliente
     */
    public void addCliente(int nif, double creditos, String enderecoMorada, int numCC) {
        try {
            openConnection();
            try (CallableStatement callStmt = getConnection().prepareCall("{ call addCliente(?,?,?,?) }")) {
                callStmt.setInt(1, nif);
                callStmt.setDouble(2, creditos);
                callStmt.setString(3, enderecoMorada);
                callStmt.setInt(4, numCC);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os clientes
     *
     * @return lista de todos os clientes registados
     */
    public List<Cliente> getListaClientes() {
        ArrayList<Cliente> list = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    int creditos = rSet.getInt(2);
                    String enderecoMorada = rSet.getString(3);
                    int numCC = rSet.getInt(4);

                    list.add(new Cliente(nif, creditos, enderecoMorada, numCC));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Procura cliente por email recebido
     *
     * @param email email do cliente
     * @return cliente
     */
    public Cliente getClienteByEmail(String email) {
        String query = "SELECT * FROM cliente e INNER JOIN utilizador u ON e.UtilizadorNIF = u.NIF WHERE u.email= " + email;

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int aInt = rSet.getInt(1);
                    int aInt1 = rSet.getInt(2);
                    String string = rSet.getString(3);
                    int aInt2 = rSet.getInt(4);
                    return new Cliente(aInt, aInt1, string, aInt2);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    public boolean removerCreditos(String email, double creditosData) throws SQLException {

        boolean removed = false;

        try (CallableStatement callV = getConnection().prepareCall("{ call removeCreditos(?,?) }")) {

            callV.setString(1, email);
            callV.setDouble(2, creditosData);
            callV.execute();

            removed = true;
            callV.close();
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());

        }

        return removed;
    }
}
