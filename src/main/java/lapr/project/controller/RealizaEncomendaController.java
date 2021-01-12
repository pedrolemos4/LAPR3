/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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

    public boolean produtoEncomenda(Produto prod, int qntd) {
        if (verificaProdutoEncomenda(prod, qntd)) {
            return produtoDB.addListaProdutos(prod, qntd);
        } else {
            try {
                String assunto = "Produto nao disponivel.";
                String mensagem = "O produto nao disponivel foi retirado da lista de produtos da sua encomenda.";
                String email = UserSession.getInstance().getUser().getEmail();
                notificaCliente(email, assunto, mensagem);
            } catch (MessagingException ex) {
                Logger.getLogger(RealizaEncomendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean registaEncomenda(Encomenda enc1) throws SQLException {
        return encDB.registaEncomenda(enc1);
    }

    public boolean registaEncomendaProduto(Encomenda enc, Produto p) {
        return encDB.registaEncomendaProduto(enc, p);
    }

    public List<Produto> getListaProdutoEncomenda() {
        return produtoDB.getListaProdutos();
    }

    public List<Produto> getListStock() {
        return produtoDB.getLista();
    }

    public List<Integer> getListQuantidade() {
        return produtoDB.getListaQuantidade();
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

    public boolean novoRecibo(Recibo rec) throws SQLException, MessagingException {
        reciboDB.registaRecibo(rec);
        String assunto = "Recibo.";
        String mensagem = rec.toString();
        String email = UserSession.getInstance().getUser().getEmail();
        return notificaCliente(email, assunto, mensagem);
    }

    public boolean novoRecibo(Recibo rec, Produto prod) {
        return reciboDB.registaRecibo(rec, prod);
    }

    public boolean verificaProdutoEncomenda(Produto prod, int qntd) {
        return getListStock().contains(prod) == true && contarNumeroProds(prod) >= qntd;
    }

    public boolean notificaCliente(String email, String assunto, String mensagem) throws MessagingException {
        return emailDB.sendEmail(email, assunto, mensagem);
    }

    public double getPrecoTotal(double taxa) {
        List<Produto> lst = getListaProdutoEncomenda();
        double preco = 0.0;

        for (int i = 0; i < lst.size(); i++) {
            preco = preco + lst.get(i).getPrecoBase();
        }

        return preco + preco * taxa;
    }

    public void removerProdutosEncomenda(List<Produto> lst, List<Integer> lst2) {
        produtoDB.removerProdutosEncomenda(lst, lst2);
    }

    public int contarNumeroProds(Produto prod) {
        List<Produto> listStock = getListStock();
        int i = 0;
        for (Produto p : listStock) {
            if (p.getDesignacao() == prod.getDesignacao() && p.getPeso() == prod.getPeso() && p.getPrecoBase() == prod.getPrecoBase()) {
                i++;
            }
        }
        return i;
    }

}
