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
    
    public Farmacia(int nif){
        this.nif=nif;
    }
    
    public Farmacia(){
        this.nif = 0;
    }
    
    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }
    
}
