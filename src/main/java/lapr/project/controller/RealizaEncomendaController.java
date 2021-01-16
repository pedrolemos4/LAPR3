/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.text.ParseException;
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
            return (produtoDB.addListaProdutos(prod, qntd) ? true : false);
        }
        return false;
    }

    public int registaEncomenda(Encomenda enc1) throws SQLException, ParseException {
        System.out.println("Regista Encomenda Linha 52");
        return (encDB.registaEncomenda(enc1));
    }

    public boolean registaEncomendaProduto(Encomenda enc, Produto p,int stock) {
        return (encDB.registaEncomendaProduto(enc, p,stock) ? true : false);
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
        int nif = UserSession.getInstance().getUser().getNIF();
        System.out.println("get cliente");
        System.out.println(nif);
        return cliDB.getClienteByNIF(nif);
    }

    public boolean novoRecibo(Recibo rec, Produto prod, int quant) {
        return (reciboDB.registaRecibo(rec, prod, quant) ? true : false);
    }

    public boolean verificaProdutoEncomenda(int nif, Produto prod, int qntd) {
        Map<Produto, Integer> map = getListStock(nif);
        for(Produto p : map.keySet()){
            System.out.println(p.toString());
            System.out.println(map.get(p));
        }
        return ((getListStock(nif).containsKey(prod) && getListStock(nif).get(prod)>=qntd) ? true : false);
    }

    public boolean notificaCliente(String email, String assunto, String mensagem) {
        return (emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem) ? true : false);
    }

    public boolean removerProdutosEncomenda(Produto prod, int nif, int map, int mapStock) {
        return (produtoDB.removerProdutosEncomenda(prod, nif, map, mapStock) ? true : false);
    }

    public double getCreditosData(Data date, double preco) {
        return encDB.getCreditosData(date, preco);
    }

    public boolean removerCreditos(int nif, double creditosData) throws SQLException {
        return (cliDB.removerCreditos(nif, creditosData) ? true : false);
    }

    public double getPreco() {
        return produtoDB.getPreco();
    }

    public double getPeso() {
        return produtoDB.getPeso();
    }

    public double getPrecoTotal(Map<Produto,Integer>map,double taxa) {
        return produtoDB.getPrecoTotal(map,taxa);
    }

    public boolean registaRecibo(Recibo rec) throws SQLException, ParseException {
        return (reciboDB.registaRecibo(rec) ? true : false);
    }

    public boolean geraCreditos(Cliente c, double precoTotal) {
        return (encDB.geraCreditos(c,precoTotal) ? true : false);
    }

}
