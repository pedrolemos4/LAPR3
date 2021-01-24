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
    private double largura;

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
     * @param largura
     * @param id
     */
    public Drone(String descricao, int capacidade, double percentagemBateria,
            double pesoMaximo, double pesoVeiculo, double potencia, int estado,
            double largura, int id, double areaFrontal) {
        super(descricao, capacidade, percentagemBateria, pesoMaximo, pesoVeiculo,
                potencia, estado, areaFrontal);
        this.id = id;
        this.largura = largura;
    }

    /**
     * Construtor que recebe uma instância de veículo,o id e a powerpro
     * @param ve
     * @param id
     * @param largura
     */
    public Drone(Veiculo ve, int id, double largura) {
        super(ve);
        this.id = id;
        this.largura = largura;
    }
    
    /**
     * Controi uma instância Drone com o id e a power pro
     * @param id
     * @param largura
     */
    public Drone(int id, double largura){
        this.id = id;
        this.largura = largura;
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
    public double getLargura() {
        return largura;
    }

    /**
     * Modifica o atributo power pro do drone
     *
     * @param largura
     */
    public void setLargura(double largura) {
        this.largura = largura;
    }

    /**
     * Método que retorna uma descrição escrita de um drone
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nPower pro: \t" + largura;
    }
}
