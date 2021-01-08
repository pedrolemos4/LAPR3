/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.Encomenda;
import lapr.project.model.EstadoEncomenda;
import lapr.project.model.Produto;

/**
 *
 * @author pedro
 */
public class EncomendaDB {

    private Encomenda enc;
    private final ProdutosDB produtoDB;
    private final DataHandler dataHandler;
    private List<Encomenda> lstEnc;
    
    public EncomendaDB(){
        this.dataHandler = DataHandler.getInstance();
        produtoDB = new ProdutosDB();
        lstEnc = new ArrayList<>();
    }
    
    /**
     * Cria nova encomenda
     */
    public void novaEncomenda() {
        EstadoEncomenda ee = new EstadoEncomenda(1, "encomendado");
        enc = new Encomenda(getListaProdutos(), new Cliente(), getData(), produtoDB.getPreco(), produtoDB.getPeso(), ee);
    }

    /**
     * Gera id com base no tamanho da lista de encomendas
     * @return 
     */
    public int generateId(){
        return lstEnc.size()+1;
    }
    
    /**
     * Devolve a data quando a encomenda é pedida
     * @return 
     */
    public String getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return date.toString();
    }

    /**
     * Devolve a lista de produtos da encomenda
     * @return 
     */
    public List<Produto> getListaProdutos() {
        return produtoDB.getListaProdutos();
    }
    
    /**
     * Valida a encomenda
     * @param enc
     * @return 
     */
    public boolean validaEncomenda(Encomenda enc){
        if(enc.getCliente()!=null && enc.getDataPedida()!= null && enc.getEstado()!=null && enc.getId()!= 0 && enc.getLst()!= null && enc.getPesoEncomenda()>0 && enc.getPreco()>0 && enc.getTaxa()>0){
            return true;
        }
        return false;
    }
    
    /**
     * Regista e encomenda se for válida
     * @param enc
     * @return 
     */
    public boolean registaEncomenda(Encomenda enc) {
        if(validaEncomenda(enc)){
            addEncomenda(enc);
        }
        return false;
    }

    /**
     * Adiciona a lista de encomendas a encomenda
     * @param enc 
     */
    private void addEncomenda(Encomenda enc) {
        lstEnc.add(enc);
    }
    
}
