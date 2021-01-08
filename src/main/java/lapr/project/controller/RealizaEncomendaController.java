/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    private final ProdutosDB produtoDB;
    private Encomenda enc;
    private EncomendaDB encDB;

    public RealizaEncomendaController(ProdutosDB produtoDB, EncomendaDB encDB) {
        this.produtoDB = produtoDB;
        this.encDB = encDB;
    }

    public void novaEncomenda() {
        encDB.novaEncomenda();
    }

    public void produtoEncomenda(Produto prod) {
        produtoDB.registaProduto(prod);
    }
    
    public boolean registaEncomenda(){
        return encDB.registaEncomenda(enc);
    }

}
