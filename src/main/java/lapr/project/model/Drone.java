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
    private double hoverPower;

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
     * @param hoverPower
     * @param id
     */
    public Drone(String descricao, int capacidade, double percentagemBateria,
            double pesoMaximo, double pesoVeiculo, double potencia, int estado,
            double hoverPower, int id, double areaFrontal) {
        super(descricao, capacidade, percentagemBateria, pesoMaximo, pesoVeiculo,
                potencia, estado, areaFrontal);
        this.id = id;
        this.hoverPower = hoverPower;
    }

    /**
     * Construtor que recebe uma instância de veículo,o id e a powerpro
     * @param ve
     * @param id
     * @param hoverPower
     */
    public Drone(Veiculo ve, int id, double hoverPower) {
        super(ve);
        this.id = id;
        this.hoverPower = hoverPower;
    }
    
    /**
     * Controi uma instância Drone com o id e a power pro
     * @param id
     * @param hoverPower
     */
    public Drone(int id, double hoverPower){
        this.id = id;
        this.hoverPower = hoverPower;
    }

    /**
     * Devolve o id do Drone
     * @return 
     */
    @Override
    public int getId(){
        return this.id;
    }
    
    /**
     * Retorna o atributo power pro do drone
     *
     * @return
     */
    public double getHoverPower() {
        return hoverPower;
    }

    /**
     * Modifica o atributo power pro do drone
     *
     * @param largura
     */
    public void setHoverPower(double hoverPower) {
        this.hoverPower = hoverPower;
    }

    /**
     * Método que retorna uma descrição escrita de um drone
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nLargura: \t" + hoverPower;
    }
}
