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
    
    private int id;
    private String designacao;
    private double peso;
    private double precoBase;
    /**
     * Constrói instância produto com o designacao, peso e o preco base.
     * @param designacao
     * @param peso
     * @param precoBase 
     */
    public Produto(String designacao, double peso, double precoBase) {
        this.id = 0;
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
     * Cria uma instância com id, designacao, peso e precoBase
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
     * Devolve o id do veiculo
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
    public void setId(int id) {
        this.id=id;
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

    /**
     * Devolve a descrição do produto
     * @return 
     */
    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", designacao=" + designacao + ", peso=" + peso + ", precoBase=" + precoBase + '}';
    }

    /**
     * Devolve o hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Verifica se o obj é igual à instância do produto
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        return this.id == other.id;
    }
    
}
