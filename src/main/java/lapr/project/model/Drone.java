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
public class Drone extends Veiculo {

    private int id;
    private double powerPro;

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
     * @param powerPro
     * @param id
     */
    public Drone(String descricao, int capacidade, double percentagemBateria,
            double pesoMaximo, double pesoVeiculo, double potencia, int estado,
            double powerPro, int id) {
        super(descricao, capacidade, percentagemBateria, pesoMaximo, pesoVeiculo,
                potencia, estado);
        this.id = id;
        this.powerPro = powerPro;
    }

    /**
     * Construtor que recebe uma instância de veículo,o id e a powerpro
     * @param ve
     * @param id
     * @param powerPro 
     */
    public Drone(Veiculo ve, int id, double powerPro) {
        super(ve);
        this.id = id;
        this.powerPro = powerPro;
    }

    /**
     * Retorna o atributo power pro do drone
     *
     * @return
     */
    public double getPowerPro() {
        return powerPro;
    }

    /**
     * Modifica o atributo power pro do drone
     *
     * @param powerPro
     */
    public void setPowerPro(double powerPro) {
        this.powerPro = powerPro;
    }

    /**
     * Método que retorna uma descrição escrita de um drone
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nPower pro: \t" + powerPro;
    }
}
