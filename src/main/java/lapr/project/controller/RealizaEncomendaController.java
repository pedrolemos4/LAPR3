/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

/**
 *
 * @author pedro
 */
public class RealizaEncomendaController {
    
    private final ProdutosDB produtoDB;
    
    public RealizaEncomendaController(ProdutosDB produtoDB){
        this.produtoDB=produtoDB;
    }
    
    public void produtoEncomenda(Produto prod){
        produtoDB.novoProduto(prod.getId(), prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase());
    }

//    /**
//     * Lista dos clientes todos
//     */
//    public void getListClients(){
//        
//    }
//    
//    /**
//     * Regista Encomenda
//     */
//    public void registaEncomenda(){
//        
//    }
    
}
