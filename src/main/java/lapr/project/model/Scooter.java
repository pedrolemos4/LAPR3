/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Tiago
 */
public class Scooter extends Veiculo {

    private int id;

    /**
     * Construtor completo
     *
     * @param descricao
     * @param capacidade
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoVeiculo
     * @param potencia
     * @param estado
     * @param areaFrontal
     * @param id
     */
    public Scooter(String descricao, int capacidade, double percentagemBateria,
            double pesoMaximo, double pesoVeiculo, double potencia, int estado,
            double areaFrontal, int id) {
        super(descricao, capacidade, percentagemBateria, pesoMaximo, pesoVeiculo,
                potencia, estado, areaFrontal);
        this.id = id;
    }

    public Scooter(Veiculo ve, int id) {
        super(ve);
        this.id = id;
    }

    public Scooter(int id){
        this.id=id;
    }
    
    @Override
    public int getId(){
        return this.id;
    }
    


    @Override
    public String toString() {
        return super.toString()
                + "\nID Scooter: \t" + id;
    }
}
