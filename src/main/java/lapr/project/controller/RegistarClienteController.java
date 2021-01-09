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
    private Utilizador user;
    private Endereco end;
    private Cartao cc;

    public RegistarClienteController(ClienteDB clienteDB, UtilizadorDB utilizadorDB, EnderecoDB enderecoDB, CartaoDB cartaoDB) {
        this.clienteDB = clienteDB;
        this.utilizadorDB = utilizadorDB;
        this.enderecoDB = enderecoDB;
        this.cartaoDB = cartaoDB;
    }

    public List<Cliente> getListaClientes() {
        return clienteDB.getLstClientes();
    }

    public Cliente novoCliente(int NIF, String nome, String email, String morada, double latitude, double longitude, double altitude, int numSegSocial, String password, int numCC, String dataValidade, int CCV) {
        end = enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
        cc = cartaoDB.novoCartao(numCC, dataValidade, numCC);
        user = utilizadorDB.novoUtilizador(NIF, nome, email, numSegSocial, password);
        Cliente cl = clienteDB.novoCliente(NIF, morada, numCC);
        return cl;
    }

    public void registaCliente(Cliente cl) {
        clienteDB.registaCliente(cl);
    }

    public void registaUtilizador() {
        utilizadorDB.registaUtilizador(user);
    }

    public void registaEndereco() {
        enderecoDB.registaEndereco(end);
    }

    public void registaCartao() {
        cartaoDB.registaCartao(cc);
    }
}
