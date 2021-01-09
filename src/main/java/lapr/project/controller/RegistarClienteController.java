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
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;

/**
 *
 * @author josep
 */
public class RegistarClienteController {

    private final ClienteDB clienteDB;
    private final EnderecoDB enderecoDB;
    private final CartaoDB cartaoDB;

    public RegistarClienteController(ClienteDB clienteDB, EnderecoDB enderecoDB, CartaoDB cartaoDB) {
        this.clienteDB = clienteDB;
        this.enderecoDB = enderecoDB;
        this.cartaoDB = cartaoDB;
    }

    public List<Cliente> getListaClientes() {
        return clienteDB.getLstClientes();
    }

    public void novoCliente(int nif, String nome, String email, String morada, double latitude, double longitude, double altitude, int numSegSocial, String password, int numCC, String dataValidade, int CCV) {
        Endereco end = enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
        Cartao cc = cartaoDB.novoCartao(numCC, dataValidade, numCC);
        //Cliente cl = clienteDB.novoCliente(cc, end, nif, nome, email, numSegSocial, password);
        //registaCliente(cl);
    }

    public boolean registaCliente(Cliente cl) {
        return clienteDB.registaCliente(cl);
    }
}
