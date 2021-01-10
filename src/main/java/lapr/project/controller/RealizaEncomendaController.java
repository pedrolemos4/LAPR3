/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.data.ProdutosDB;
import lapr.project.data.ReciboDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.EstadoEncomenda;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;

/**
 *
 * @author pedro
 */
public class RealizaEncomendaController {

    private ProdutosDB produtoDB;
    private EncomendaDB encDB;
    private ClienteDB cliDB;
    private ReciboDB reciboDB;
    private EmailDB emailDB;

    public RealizaEncomendaController() {
        produtoDB = new ProdutosDB();
        encDB = new EncomendaDB();
        reciboDB = new ReciboDB();
        cliDB = new ClienteDB();
        emailDB = new EmailDB();
    }

    public void produtoEncomenda(Produto prod) {
        if (verificaProdutoEncomenda(prod) == true) {
            produtoDB.addListaProdutos(prod);
        } else {
            notificaCliente();
        }
    }

    public void registaEncomenda(Encomenda enc1) throws SQLException {
        encDB.registaEncomenda(enc1);
    }

    public void registaEncomendaProduto(Encomenda enc, Produto p) {
        encDB.registaEncomendaProduto(enc, p);
    }

    public List<Produto> getListaProdutoEncomenda() {
        return produtoDB.getLista();
    }

    public List<Produto> getListStock() {
        return produtoDB.getListaProdutos();
    }

    public Produto getProdutoByID(int id) {
        return produtoDB.getProdutoByID(id);
    }

    public double getPreco() {
        return produtoDB.getPreco();
    }

    public double getPeso() {
        return produtoDB.getPeso();
    }

    public int getNifCliente() {
        String email = UserSession.getInstance().getUser().getEmail();
        Cliente cliente = cliDB.getClienteByEmail(email);
        return cliente.getNIF();
    }

    public void novoRecibo(Recibo rec) throws SQLException {
        reciboDB.registaRecibo(rec);
    }

    public void novoRecibo(Recibo rec, Produto prod) {
        reciboDB.registaRecibo(rec, prod);
    }

    public boolean verificaProdutoEncomenda(Produto prod) {
        return getListStock().contains(prod);
    }

    public void notificaCliente() {
        String assunto = "Produto nao disponivel.";
        String mensagem = "O produto nao disponivel foi retirado da lista de produtos da sua encomenda.";
        String email = UserSession.getInstance().getUser().getEmail();
        emailDB.sendEmail(email, assunto, mensagem);
    }

    public double getPrecoTotal(double taxa) {
        List<Produto> lst = getListaProdutoEncomenda();
        double preco=0.0;
        
        for(int i=0;i<lst.size();i++){
            preco = preco + lst.get(i).getPrecoBase();
        }
        
        return preco + preco * taxa;
        
    }

}
