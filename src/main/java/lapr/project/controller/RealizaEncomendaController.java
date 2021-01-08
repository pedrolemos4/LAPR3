/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lapr.project.data.ProdutosDB;
import lapr.project.model.Encomenda;
import lapr.project.model.EstadoEncomenda;
import lapr.project.model.Produto;

/**
 *
 * @author pedro
 */
public class RealizaEncomendaController {

    private final ProdutosDB produtoDB;
    //private final ClienteDB clienteDB;
    private Encomenda enc;

    public RealizaEncomendaController(ProdutosDB produtoDB) {
        this.produtoDB = produtoDB;
    }

    public void novaEncomenda() {
        EstadoEncomenda ee = new EstadoEncomenda(1, "encomendado");
        enc = new Encomenda(getListaProdutos(), /*cliente,*/ getDataPedida(), produtoDB.getPreco(), produtoDB.getPeso(), ee);
    }

    /**
     * Devolve a data quando a encomenda é pedida
     * @return 
     */
    public String getDataPedida() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return date.toString();
    }

    public void produtoEncomenda(Produto prod) {
        produtoDB.registaProduto(prod);
    }

    public List<Produto> getListaProdutos() {
        return produtoDB.getListaProdutos();
    }

}
