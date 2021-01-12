/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Utilizador;

/**
 *
 * @author josep
 */
public class RegistarClienteController {

    private final ClienteDB clienteDB;
    private final UtilizadorDB utilizadorDB;
    private final EnderecoDB enderecoDB;
    private final CartaoDB cartaoDB;

    public RegistarClienteController(ClienteDB cldb, UtilizadorDB udb, EnderecoDB edb, CartaoDB cdb) {
        this.clienteDB = cldb;
        this.utilizadorDB = udb;
        this.enderecoDB = edb;
        this.cartaoDB = cdb;
    }

    public Utilizador login(String email, String password) {
        int nif = utilizadorDB.validateLogin(email, password);
        return utilizadorDB.getByID(nif);
    }

    public List<Cliente> getListaClientes() {
        return clienteDB.getListaClientes();
    }

    public Cliente novoCliente(int NIF, String nome, String email, int numeroSegurancaSocial, double creditos, String morada, int numCC, String password) {
        return clienteDB.novoCliente(NIF, nome, email, numeroSegurancaSocial, creditos, morada, numCC, password);
    }

    public Cartao novoCartao(int numCC, String dataValidade, int ccv) {
        return cartaoDB.novoCartao(numCC, dataValidade, ccv);
    }

    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        return enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
    }

    public boolean registaCliente(Cliente cl) {
        return clienteDB.registaCliente(cl);
    }

    public boolean registaEndereco(Endereco end) {
        return enderecoDB.registaEndereco(end);
    }

    public boolean registaCartao(Cartao cc) {
        return cartaoDB.registaCartao(cc);
    }

}
