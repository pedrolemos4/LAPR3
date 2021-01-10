/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.ClienteDB;
import lapr.project.data.EncomendaDB;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;

/**
 *
 * @author pedro
 */
public class RealizaPagamentoController {

    private FacadeAuthorization facade;
    private ClienteDB cliDB;
    private EncomendaDB encDB;

    public RealizaPagamentoController() {
        cliDB = new ClienteDB();
        encDB = new EncomendaDB();
        this.facade = POTApplication.getFacadeAuthorization();
    }

    public Encomenda getEncomenda(int nif) {
        return encDB.getEncomenda(nif);
    }
    
    public double getPreco(Encomenda enc){
        return getEncomenda(enc.getNif()).getPesoEncomenda() * getEncomenda(enc.getNif()).getTaxa();
    }
    
    public double getPeso(Encomenda enc){
        return getEncomenda(enc.getNif()).getPesoEncomenda();
    }

    public int getNifCliente() {
        String email = facade.getCurrentSession().getUser().getEmail();
        Cliente cliente = cliDB.getClienteByEmail(email);
        return cliente.getNIF();
    }

}
