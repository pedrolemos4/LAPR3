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
public class Farmacia {
    
    private int nif;
    private Parque parque;

    /**
     * Constrói uma instância de farmácia com o nif da mesma
     * @param nif nif da farmácia
     */
    public Farmacia(int nif){
        this.nif = nif;
    }

    /**
     * Constrói uma instância de farmácia com o nif por omissão
     */
    public Farmacia(){
        this.nif = 0;
    }

    /**
     * Devolve o valor do nif da farmácia
     * @return nif da farmácia
     */
    public int getNif() {
        return nif;
    }

    /**
     * Modifica o valor do nif da farmácia com o valor recebido por parâmetro
     * @param nif novo nif da farmácia
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /**
     * Devolve a instância do parque da farmácia
     * @return parque da farmácia
     */
    public Parque getParque() {
        return parque;
    }

    /**
     * Modifica a instância de parque da farmácia com o parque recebido por parâmetro
     * @param parque novo parque da farmácia
     */
    public void setParque(Parque parque) {
        this.parque = parque;
    }

}
