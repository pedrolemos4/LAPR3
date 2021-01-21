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

    /**
     * Cria um novo cliente
     *
     * @param nif nif do cliente
     * @param nome nome do cliente
     * @param email email do cliente
     * @param numeroSegurancaSocial número de segurança social do cliente
     * @param creditos número de créditos do cliente
     * @param password password do cliente
     * @param morada morada do cliente
     * @param numCC número do cartão de cidadão do cliente
     * @return o novo cliente criado
     */
    public Cliente novoCliente(int nif, String nome, String email, int numeroSegurancaSocial, double creditos, String morada, long numCC, String password) {
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
        UtilizadorDB userDB = new UtilizadorDB();
        userDB.addUtilizador(cl.getNIF(), cl.getNome(), cl.getEmail(), cl.getNumeroSegurancaSocial(), cl.getPassword());
        addCliente(cl.getNIF(), cl.getCreditos(), cl.getEnderecoMorada(), cl.getNumCartaoCredito());
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
    public void addCliente(int nif, double creditos, String enderecoMorada, long numCC) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addCliente(?,?,?,?) }")) {
                callStmt.setInt(1, nif);
                callStmt.setDouble(2, creditos);
                callStmt.setString(3, enderecoMorada);
                callStmt.setLong(4, numCC);
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

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    int creditos = rSet.getInt(2);
                    String enderecoMorada = rSet.getString(3);
                    long numCC = rSet.getLong(4);

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
     * Procura cliente por nif recebido
     *
     * @param nif nif do cliente
     * @return cliente com o nif especificado por parâmetro
     */
    public Cliente getClienteByNIF(int nif) {
        String query = "SELECT c.utilizadornif, c.creditos, c.enderecomorada, c.cartaonumerocartaocredito "
                + "FROM cliente c "
                + "INNER JOIN utilizador u ON c.UtilizadorNIF = u.NIF "
                + "WHERE u.NIF = " + nif;
        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int id1 = rSet.getInt(1);
                    double creds = rSet.getDouble(2);
                    String morada = rSet.getString(3);
                    long num = rSet.getLong(4);

                    return new Cliente(id1,creds,morada,num);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    /**
     * Procura cliente pelo endereco do cliente
     *
     * @param end endereco do cliente
     * @return cliente com a morada especificada por parâmetro
     */
    public Cliente getClienteByMorada(String end) {
        String query = "SELECT c.utilizadorNIF, c.creditos, c.enderecomorada, c.cartaonumerocartaocredito FROM cliente c INNER JOIN endereco e ON c.EnderecoMorada = e.morada WHERE c.EnderecoMorada = '" + end + "'";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                if (rSet.next()) {
                    int aInt = rSet.getInt(1);
                    double aInt1 = rSet.getDouble(2);
                    String string = rSet.getString(3);
                    long aInt2 = rSet.getLong(4);
                    return new Cliente(aInt, aInt1, string, aInt2);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    /**
     * Remove uma certa quantidade de créditos de um cliente com base na data em que fez a encomenda
     * @param nif nif do cliente
     * @param creditosData data
     * @return true se removeu com sucesso, false se não
     * @throws SQLException
     */
    public boolean removerCreditos(int nif, double creditosData) throws SQLException {

        boolean removed = false;

        try ( CallableStatement callV = getConnection().prepareCall("{ call procRemoverCreditos(?,?) }")) {

            callV.setInt(1, nif);
            callV.setDouble(2, creditosData);
            callV.execute();

            removed = true;
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.WARNING, ex.getMessage());

        }

        return removed;
    }

    /**
     * Adiciona uma certa quantidade de créditos a um cliente
     * @param c cliente a adicionar os créditos
     * @param d valor em créditos
     * @return true se adicionou com sucesso, false se não
     */
    public boolean addCreditos(Cliente c, double d) {
        boolean bool = false;
        
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addCreditosCliente(?,?) }")) {
                callStmt.setInt(1, c.getClienteNIF());
                callStmt.setDouble(2, d);
                
                callStmt.execute();
                
                bool=true;
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bool;
    }
}
