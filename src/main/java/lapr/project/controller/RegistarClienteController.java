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

    public RegistarClienteController() {
        this.clienteDB = new ClienteDB();
        this.utilizadorDB = new UtilizadorDB();
        this.enderecoDB = new EnderecoDB();
        this.cartaoDB = new CartaoDB();
    }

    public Utilizador login(String email, String password) {
        Utilizador user = null;
        int nif = utilizadorDB.validateLogin(email, password);
        user = utilizadorDB.getByID(nif);
        return user;
    }

    public List<Cliente> getListaClientes() {
        return clienteDB.getLstClientes();
    }

    public Cliente novoCliente(int NIF, String nome, String email, int numeroSegurancaSocial, String morada, int numCC, String password) {
        Cliente cl = clienteDB.novoCliente(NIF, nome, email, numeroSegurancaSocial, morada, numCC, password);
        return cl;
    }

    public Cartao novoCartao(int numCC, String dataValidade, int CCV) {
        Cartao cc = cartaoDB.novoCartao(numCC, dataValidade, numCC);
        return cc;
    }

    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        Endereco end = enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
        return end;
    }

    public void registaCliente(Cliente cl) {
        clienteDB.registaCliente(cl);
    }

    public void registaEndereco(Endereco end) {
        enderecoDB.registaEndereco(end);
    }

    public void registaCartao(Cartao cc) {
        cartaoDB.registaCartao(cc);
    }

}
