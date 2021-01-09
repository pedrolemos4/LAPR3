/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.ClienteDB;
import lapr.project.data.EncomendaDB;
import lapr.project.data.ProdutosDB;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.EstadoEncomenda;
import lapr.project.model.Produto;

/**
 *
 * @author pedro
 */
public class RealizaEncomendaController {

    private ProdutosDB produtoDB;
    private EncomendaDB encDB;
    private ClienteDB cliDB;
    private FacadeAuthorization facade;

    public RealizaEncomendaController() {
        produtoDB = new ProdutosDB();
        encDB = new EncomendaDB();
        cliDB = new ClienteDB();
        this.facade = POTApplication.getFacadeAuthorization();
    }

    public void produtoEncomenda(Produto prod) {
        produtoDB.registaProduto(prod);
    }
    
    public boolean registaEncomenda(Encomenda enc1){
        return encDB.registaEncomenda(enc1);
    }
    
    public List<Produto> getListaEncomenda(){
        return produtoDB.getLista();
    }
    
    public List<Produto> getListStock(){
        return produtoDB.getListaProdutos();
    }
    
    public Produto getProdutoByID(int id){
        return produtoDB.getProdutoByID(id);
    }
    
    public double getPreco(){
        return produtoDB.getPreco();
    }
    
    public double getPeso(){
        return produtoDB.getPeso();
    }
    
    public int getNifCliente(){
        String email = facade.getCurrentSession().getUser().getEmail();
        Cliente cliente = cliDB.getClienteByEmail(email);
        return cliente.getNIF();
    }

}
