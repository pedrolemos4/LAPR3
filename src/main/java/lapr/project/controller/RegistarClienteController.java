/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Utilizador;

import java.text.ParseException;
import java.util.List;

/**
 *
 * @author josep
 */
public class RegistarClienteController {

    private final ClienteDB clienteDB;
    private final UtilizadorDB utilizadorDB;
    private final EnderecoDB enderecoDB;
    private final CartaoDB cartaoDB;
    
    /**
     * Constroi uma instancia de RegistarClienteController recebendo uma instancia de ClienteDB, UtilizadorDB, EnderecoDB, CartaoDB
     * @param cldb instancia de ClienteDB
     * @param udb instancia de UtilizadorDB
     * @param edb instancia de EnderecoDB
     * @param cdb instancia de CartaoDB
     */
    public RegistarClienteController(ClienteDB cldb, UtilizadorDB udb, EnderecoDB edb, CartaoDB cdb) {
        this.clienteDB = cldb;
        this.utilizadorDB = udb;
        this.enderecoDB = edb;
        this.cartaoDB = cdb;
    }
    
    /**
     * Devolve um utilizador recebendo o email e password
     * @param email email
     * @param password password
     * @return utilizador
     */
    public Utilizador login(String email, String password) {
        int nif = utilizadorDB.validateLogin(email, password);
        return utilizadorDB.getByID(nif);
    }
    
    /**
     * Devolve a lista de clientes
     * @return lista de clientes
     */
    public List<Cliente> getListaClientes() {
        return clienteDB.getListaClientes();
    }
    
    /**
     * Devolve um cliente recebendo por parametro o nif, nome,email,numero de segurança social, creditos, morada, numero de cartao de credito e password
     * @param nif nif do cliente
     * @param nome nome do cliente
     * @param email email do cliente
     * @param numeroSegurancaSocial o numero de seegurança social do cliente
     * @param creditos os creditos do cliente
     * @param morada a morada do cliente
     * @param numCC o numero do cartao de credito do cliente
     * @param password a password do cliente
     * @return  cliente
     */
    public Cliente novoCliente(int nif, String nome, String email, int numeroSegurancaSocial, double creditos, String morada, int numCC, String password) {
        return clienteDB.novoCliente(nif, nome, email, numeroSegurancaSocial, creditos, morada, numCC, password);
    }
    
    /**
     * Devolve um cartao recebendo por parametro o numero de cartao de credito, a data de validade e o codigo de segurança
     * @param numCC o numero do cartao de credito
     * @param dataValidade a data de validade do cartao de credito
     * @param ccv o codigo de segurança do cartao de credito
     * @return cartao
     */
    public Cartao novoCartao(int numCC, String dataValidade, int ccv) {
        return cartaoDB.novoCartao(numCC, dataValidade, ccv);
    }
    
    /**
     * Devolve o endereco recebendo por parametro a morada, latitude,longitude e altitude
     * @param morada morada do endereço
     * @param latitude latitude do endereço
     * @param longitude longitude do endereço
     * @param altitude altitude do endereço
     * @return endereço
     */
    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        return enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
    }
    
    /**
     * Verifica se o cliente é registado recebendo o cliente por parametro
     * @param cl cliente
     * @return true se o cliente for registado
     */
    public boolean registaCliente(Cliente cl) {
        return (clienteDB.registaCliente(cl) ? (true) : (false));
    }
    
    /**
     * Verifica se o endereço é registado recebendo o endereço por parametro
     * @param end endereço
     * @return true se o endereço for registado
     */
    public boolean registaEndereco(Endereco end) {
        return (enderecoDB.registaEndereco(end) ? (true) : (false));
    }
    
    /**
     * Verifica se o cartao é registado recebendo o cartao por parametro
     * @param cc cartao
     * @return true se o cartao for registado
     * @throws java.text.ParseException
     */
    public boolean registaCartao(Cartao cc) throws ParseException {
        return (cartaoDB.registaCartao(cc) ? (true) : (false));
    }

}
