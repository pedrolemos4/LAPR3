/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.util.ArrayList;
import java.util.List;
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
        if (cl.getNome() == null || cl.getNome().isBlank() || cl.getNIF() <= 0 || cl.getEmail() == null || cl.getEmail().isBlank() || cl.getNumeroSegurancaSocial() <= 0 || cl.getPassword() == null || cl.getPassword().isBlank()) {
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
}
