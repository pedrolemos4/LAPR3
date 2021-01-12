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
public class Farmacia {

    private int nif;

    /**
     * Constrói uma instância de farmácia com o nif da mesma e o seu parque
     *
     * @param nif nif da farmácia
     */
    public Farmacia(int nif) {
        this.nif = nif;
    }

    /**
     * Constrói uma instância de farmácia com o nif e o parque por omissão
     */
    public Farmacia() {
        this.nif = 0;
    }

    /**
     * Devolve o valor do nif da farmácia
     *
     * @return nif da farmácia
     */
    public int getNIF() {
        return nif;
    }

    /**
     * Modifica o valor do nif da farmácia com o valor recebido por parâmetro
     *
     * @param nif novo nif da farmácia
     */
    public void setNIF(int nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        return "Farmacia{" + "nif=" + nif + '}';
    }
}
