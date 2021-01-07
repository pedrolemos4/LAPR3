/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author pedro
 */
public class Produto {
    
    private String designacao;
    private double peso;
    private int precoBase;
    
    /**
     * Constrói instância produto com o designacao, peso e o preco base.
     * @param designacao
     * @param peso
     * @param precoBase 
     */
    public Produto(String designacao, double peso, int precoBase) {
        this.designacao = designacao;
        this.peso = peso;
        this.precoBase = precoBase;
    }
    
    /**
     * Constrói uma instância do produto vazia.
     */
    public Produto(){
        this.designacao = null;
        this.peso = 0;
        this.precoBase = 0;
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
    public int getPrecoBase() {
        return precoBase;
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
    public void setPrecoBase(int precoBase) {
        this.precoBase = precoBase;
    }
    
}
