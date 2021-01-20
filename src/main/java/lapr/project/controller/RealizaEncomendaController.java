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
import lapr.project.data.EnderecoDB;
import lapr.project.data.ProdutosDB;
import lapr.project.data.ReciboDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
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
    private final EnderecoDB edb;
    
    /**
     * Constroi uma instancia de RealizaEncomendaController recebendo uma instancia de ProdutosDB, EncomendaDB, ReciboDB, ClienteDB e EmailDB
     * @param prod uma instancia de ProdutosDB
     * @param enc uma instancia de EncomendaDB
     * @param rec uma instancia de ReciboDB
     * @param cl uma instancia de ClienteDB
     * @param em uma instancia de EmailDB
     */
    public RealizaEncomendaController(ProdutosDB prod, EncomendaDB enc, ReciboDB rec, ClienteDB cl, EmailDB em, EnderecoDB ed) {
        this.produtoDB = prod;
        this.encDB = enc;
        this.reciboDB = rec;
        this.cliDB = cl;
        this.emailDB = em;
        this.edb = ed;
    }
    
    /**
     * Verifica se produto foi adicionada recebendo o nif da farmacia, o produto e a quantidade
     * @param nif nif da farmacia
     * @param prod produto
     * @param qntd quantidade do produto
     * @return true se o produto for adicionado
     */
    public boolean produtoEncomenda(int nif, Produto prod, int qntd) {
        if (verificaProdutoEncomenda(nif, prod, qntd)) {
            return (produtoDB.addListaProdutos(prod, qntd) ? (true) : (false));
        }
        return false;
    }
    
    /**
     * Devolve o id da encomenda
     * @param enc1 encomenda
     * @return id da encomenda
     * @throws SQLException
     * @throws ParseException 
     */
    public int registaEncomenda(Encomenda enc1) throws SQLException, ParseException {
        return (encDB.registaEncomenda(enc1));
    }
    
    /**
     * Verifica se o produto da encomenda é registado recebendo a encomenda, o produto e o stock
     * @param enc encomenda
     * @param p produto
     * @param stock stock
     * @return true se o produto da encomenda for registado
     */
    public boolean registaEncomendaProduto(Encomenda enc, Produto p,int stock) {
        return (encDB.registaEncomendaProduto(enc, p,stock) ? (true) : (false));
    }
    
    /**
     * Devolve um map cuja key é o produto e o value a sua quantidade recebendo o nif da farmacia
     * @param nif nif da farmacia
     * @return map cuja key é o produto e o value a sua quantidade
     */
    public Map<Produto, Integer> getListStock(int nif) {
        return produtoDB.getLista(nif);
    }
    
    /**
     * Devolve um map cuja key é o produto e o value a sua quantidade
     * @return map cuja key é o produto e o value a sua quantidade
     */
    public Map<Produto, Integer> getMapaEncomenda() {
        return produtoDB.getMapaEncomenda();
    }
    
    /**
     * Devolve o produto recebendo o id do produto
     * @param id id do produto
     * @return produto
     */
    public Produto getProdutoByID(int id) {
        return produtoDB.getProdutoByID(id);
    }
    
    /**
     * Devolve o cliente que fez login
     * @return cliente
     */
    public Cliente getCliente() {
        int nif = UserSession.getInstance().getUser().getNIF();
        return cliDB.getClienteByNIF(nif);
    }
    
    /**
     * Verifica se o recibo é registado recebendo por parametro o recibo, produto e quantidade
     * @param rec recibo
     * @param prod produto
     * @param quant quantidade
     * @return true se o recibo foi registado
     */
    public boolean novoRecibo(Recibo rec, Produto prod, int quant) {
        return (reciboDB.registaRecibo(rec, prod, quant) ? (true) : (false));
    }
    
    /**
     * Verifica se o produto da encomenda é valido recebendo o nif da farmacia, o produto e a quantidade
     * @param nif nif da farmacia
     * @param prod produto
     * @param qntd quantidade do produto
     * @return true se o produto da encomenda for valido
     */
    public boolean verificaProdutoEncomenda(int nif, Produto prod, int qntd) {
        return ((getListStock(nif).containsKey(prod) && getListStock(nif).get(prod)>=qntd) ? (true) : (false));
    }
    
    /**
     * Verifica se notificao ao cliente é enviada recebendo o email, o assunto e a mensagem
     * @param email email
     * @param assunto assunto
     * @param mensagem mensagem
     * @return true se notificao ao cliente for enviada
     */
    public boolean notificaCliente(String email, String assunto, String mensagem) {
        return (emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem) ? (true) : (false));
    }
    
    /**
     * Verifica se o produto da encomenda é removido recebendo o produto, o nif da farmacia,
     * @param prod
     * @param nif
     * @param map
     * @param mapStock
     * @return 
     */
    public boolean removerProdutosEncomenda(Produto prod, int nif, int map, int mapStock) {
        return (produtoDB.removerProdutosEncomenda(prod, nif, map, mapStock) ? (true) : (false));
    }
    
    /**
     * Devolve os creditos recebendo a data e o preco
     * @param date data
     * @param preco preco
     * @return creditos
     */
    public double getCreditosData(Data date, double preco) {
        return encDB.getCreditosData(date, preco);
    }
    
    /**
     * Verifica se creditos froam removidos recebendo o nif do cliente e os creditos do cliente
     * @param nif nif do cliente
     * @param creditosData creditos do cliente
     * @return true se os creditos foram removidos
     * @throws SQLException 
     */
    public boolean removerCreditos(int nif, double creditosData) throws SQLException {
        return (cliDB.removerCreditos(nif, creditosData) ? (true) : (false));
    }
    
    /**
     * Devolve o preco de um produto
     * @return preco de um produto
     */
    public double getPreco() {
        return produtoDB.getPreco();
    }
    
    /**
     * Devolve o peso de um produto
     * @return peso de um produto
     */
    public double getPeso() {
        return produtoDB.getPeso();
    }
    
    /**
     * Devolve o preco de uma encomenda recebendo um mapa e a taxa
     * @param map cuja key é o produto e o value a sua quantidade
     * @param taxa taxa
     * @return preco de uma encomenda
     */
    public double getPrecoTotal(Map<Produto,Integer>map,double taxa) {
        return produtoDB.getPrecoTotal(map,taxa);
    }
    
    /**
     * Verifica se o recibo é registado recebendo o recibo
     * @param rec recibo
     * @return true se o recibo for recebido
     * @throws SQLException
     * @throws ParseException 
     */
    public boolean registaRecibo(Recibo rec) throws SQLException, ParseException {
        return (reciboDB.registaRecibo(rec) ? (true) : (false));
    }
    
    /**
     * Verifica se os creditos são gerados recebendo o cliente e o precoTotal
     * @param c cliente
     * @param precoTotal preco total do cliente
     * @return true se os creditos são gerados
     */
    public boolean geraCreditos(Cliente c, double precoTotal) {
        return (encDB.geraCreditos(c,precoTotal) ? (true) : (false));
    }

    /**
     * Devolve o endereco pelo nif do cliente
     * @param nif
     * @return 
     */
    public Endereco getEnderecoByNifCliente(int nif) {
        return edb.getEnderecoByNifCliente(nif);
    }
    
    /**
     * Devolve o endereço de uma farmacia recebendo por parametro o nif da farmacia
     * @param nifFarmacia nif da farmacia
     * @return endereço da farmacia
     */
    public Endereco getEnderecoOrigem(int nifFarmacia) {
        return edb.getEnderecoByNifFarmacia(nifFarmacia);
    }

}
