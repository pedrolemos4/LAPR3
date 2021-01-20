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
    private double areaFrontal;

    /**
     * Construtor completo
     * @param descricao
     * @param capacidade
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoVeiculo
     * @param potencia
     * @param estado
     * @param areaFrontal 
     */
    public Scooter(String descricao, int capacidade, double percentagemBateria,
            double pesoMaximo, double pesoVeiculo, double potencia, int estado,
            double areaFrontal) {
        this.areaFrontal = areaFrontal;
    }

    public Scooter(Veiculo ve,int id, double areaFrontal ){
        super(ve);
        this.id=id;
        this.areaFrontal=areaFrontal;
    }
    /**
     * Devolve a área frontal do veiculo
     *
     * @return
     */
    public double getAreaFrontal() {
        return areaFrontal;
    }

    /**
     * Modifica a área frontal do veículo
     *
     * @param areaFrontal
     */
    public void setAreaFrontal(double areaFrontal) {
        this.areaFrontal = areaFrontal;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nÁrea frontal: \t" + areaFrontal;
    }
}
