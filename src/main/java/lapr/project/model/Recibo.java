/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class Recibo {
    
    private int nif;
    private int id;
    private String data;
    private List<Produto> lst;
    
    /**
     * Cria instancia receibo com nif do cliente e a data gerada
     * @param nif
     * @param data 
     */
    public Recibo(int nif, String data){
        this.id=0;
        this.nif=nif;
        this.data=data;
        lst = new ArrayList<>();
    }
    
    /**
     * Cria construtor vazio
     */
    public Recibo (){
        this.id=0;
        this.nif=0;
        this.data="";
        lst = new ArrayList<>();
    }

    /**
     * Devolve nif
     * @return 
     */
    public int getNif() {
        return nif;
    }

    /**
     * Devolve id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Devolve data
     * @return 
     */
    public String getData() {
        return data;
    }

    /**
     * Devolve a lista
     * @return 
     */
    public List<Produto> getLst() {
        return lst;
    }

    /**
     * Modifica o nif
     * @param nif 
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /**
     * Modifica id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifica data
     * @param data 
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Modifica a lista
     * @param lst 
     */
    public void setLst(List<Produto> lst) {
        this.lst = lst;
    }
    
}