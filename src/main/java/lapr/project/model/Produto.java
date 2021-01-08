/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.data.ProdutosDB;

/**
 *
 * @author pedro
 */
public class Produto {
    
    private int id;
    private String designacao;
    private double peso;
    private double precoBase;
    /**
     * Constrói instância produto com o designacao, peso e o preco base.
     * @param id
     * @param designacao
     * @param peso
     * @param precoBase 
     */
    public Produto(int id, String designacao, double peso, double precoBase) {
        this.id = id;
        this.designacao = designacao;
        this.peso = peso;
        this.precoBase = precoBase;
    }
    
    /**
     * Constrói uma instância do produto vazia.
     */
    public Produto(){
        this.id = 0;
        this.designacao = null;
        this.peso = 0;
        this.precoBase = 0;
    }

    /**
     * Devolve o id da scooter
     *
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * Devolve a designacao
     * @return 
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Devolve o peso
     * @return 
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Devolve o preco base
     * @return 
     */
    public double getPrecoBase() {
        return precoBase;
    }

    /**
     * Modifica o id da produto
     *
     * @return
     */
    public int setId(int id) {
        return this.id=id;
    }
    
    /**
     * Modifica a designacao
     * @param designacao 
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * Modifica o peso
     * @param peso 
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Modifica preco base
     * @param precoBase 
     */
    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }
    
}
