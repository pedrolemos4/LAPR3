/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.Map;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.data.ProdutosDB;
import lapr.project.data.ReciboDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import lapr.project.utils.Data;

/**
 *
 * @author pedro
 */
public class RealizaEncomendaController {

    private final ProdutosDB produtoDB;
    private final EncomendaDB encDB;
    private final ClienteDB cliDB;
    private final ReciboDB reciboDB;
    private final EmailDB emailDB;

    public RealizaEncomendaController(ProdutosDB prod, EncomendaDB enc, ReciboDB rec, ClienteDB cl, EmailDB em) {
        this.produtoDB = prod;
        this.encDB = enc;
        this.reciboDB = rec;
        this.cliDB = cl;
        this.emailDB = em;
    }

    public boolean produtoEncomenda(int nif, Produto prod, int qntd) {
        if (verificaProdutoEncomenda(nif, prod, qntd)) {
            return (produtoDB.addListaProdutos(prod, qntd)? true : false);
        } else {
            String assunto = "Produto nao disponivel.";
            String mensagem = "O produto nao disponivel foi retirado da lista de produtos da sua encomenda.";
            String email = UserSession.getInstance().getUser().getEmail();
            notificaCliente(email, assunto, mensagem);
        }
        return false;
    }

    public boolean registaEncomenda(Encomenda enc1) throws SQLException {
        return (encDB.registaEncomenda(enc1) ? true : false);
    }

    public boolean registaEncomendaProduto(Encomenda enc, Produto p) {
        return (encDB.registaEncomendaProduto(enc, p) ? true : false);
    }

    public Map<Produto, Integer> getListStock(int nif) {
        return produtoDB.getLista(nif);
    }

    public Map<Produto, Integer> getMapaEncomenda() {
        return produtoDB.getMapaEncomenda();
    }

    public Produto getProdutoByID(int id) {
        return produtoDB.getProdutoByID(id);
    }

    public Cliente getCliente() {
        String email = UserSession.getInstance().getUser().getEmail();
        return cliDB.getClienteByEmail(email);
    }

    public boolean emailRecibo(Recibo rec) throws SQLException {
        reciboDB.registaRecibo(rec);
        String assunto = "Recibo.";
        String mensagem = rec.toString();
        String email = UserSession.getInstance().getUser().getEmail();
        return notificaCliente(email, assunto, mensagem);
    }

    public boolean novoRecibo(Recibo rec, Produto prod, int quant) {
        return (reciboDB.registaRecibo(rec, prod, quant) ? true : false);
    }

    public boolean verificaProdutoEncomenda(int nif, Produto prod, int qntd) {
        return ((getListStock(nif).containsKey(prod) && getListStock(nif).get(prod)>=qntd) ? true : false);
    }

    public boolean notificaCliente(String email, String assunto, String mensagem) {
        return (emailDB.sendEmail("admlapr123@gmail.com",email, assunto, mensagem) ? true : false);
    }

    public boolean removerProdutosEncomenda(Map<Produto,Integer> map, int nif) {
        return produtoDB.removerProdutosEncomenda(map,nif);
    }

    public double getCreditosData(Data date, double preco) {
        return encDB.getCreditosData(date, preco);
    }

    public boolean removerCreditos(String email, double creditosData) throws SQLException {
        return (cliDB.removerCreditos(email, creditosData) ? true : false);
    }

    public double getPreco() {
        return produtoDB.getPreco();
    }

    public double getPeso() {
        return produtoDB.getPeso();
    }

    public double getPrecoTotal(double taxa) {
        return produtoDB.getPrecoTotal(taxa);
    }
    
}
