/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;

/**
 *
 * @author josep
 */
public class ClienteDB extends DataHandler {

    Cliente cl;
    EnderecoDB edb;
    CartaoDB cdb;
    private final DataHandler dataHandler;
    private List<Cliente> lstClientes;

    public ClienteDB() {
        this.dataHandler = DataHandler.getInstance();
        lstClientes = new ArrayList<>();
    }

    public List<Cliente> getLstClientes() {
        return lstClientes;
    }

    public Cliente novoCliente(Cartao cartaoCredito, Endereco end, int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        cl = new Cliente(0, cartaoCredito, end, NIF, nome, email, numeroSegurancaSocial, password);
        return cl;
    }

    public boolean registaCliente(Cliente cl) {
        if (validaCliente(cl)) {
            return addCliente(cl);
        }
        return false;
    }

    public boolean validaCliente(Cliente cl) {
        if (cl.getNome() == null || cl.getNome().isEmpty() || cl.getNIF() <= 0 || cl.getEmail() == null || cl.getEmail().isEmpty() || cl.getNumeroSegurancaSocial() <= 0 || cl.getPassword() == null || cl.getPassword().isEmpty()) {
            return false;
        }
        if (!edb.validaEndereco(cl.getEndereco())) {
            return false;
        }
        if (!cdb.validaCartao(cl.getCartaoCredito())) {
            return false;
        }
        for (Cliente c : lstClientes) {
            if (c.equals(cl)) {
                return false;
            }
        }

        return true;
    }

    public boolean addCliente(Cliente cl) {
        return lstClientes.add(cl);
    }
    
    public Cliente getEstafetaByEmail(String email) {
        String query = "SELECT * FROM cliente e INNER JOIN utilizador u ON e.UtilizadorNIF = u.NIF WHERE e.email= " + email;
        
        Statement stm = null;
        ResultSet rSet = null;
        
//        DADOS CLIENTE(NIF, nome, email, numeroSegurancaSocial, password);
//        this.creditos = creditos;
//        this.endereco = endereco;
//        this.cartaoCredito = cartaoCredito;
        
        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            
            if (rSet.next()) {
                

                return new Cliente();
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
