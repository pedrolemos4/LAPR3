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
public class Scooter {

    private int id;
    private String descricao;
    private double percentagemBateria;
    private double pesoMaximo;
    private double pesoScooter;
    private double potencia;
    private double areaFrontal;
    private EstadoScooter estado;

    /**
     * Constrói uma instância recebendo o id, a descrição,a percentagem da bateria, o
     * peso máximo o peso da scooter,a potência da scooter e o estado da scooter
     * 
     * @param id
     * @param descricao
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoScooter
     * @param potencia
     * @param estado 
     */
    public Scooter(int id, String descricao, double percentagemBateria, 
            double pesoMaximo, double pesoScooter, double potencia, double areaFrontal,int estado) {
        this.id = id;
        this.descricao = descricao;
        this.percentagemBateria = percentagemBateria;
        this.pesoMaximo = pesoMaximo;
        this.pesoScooter = pesoScooter;
        this.potencia = potencia;
        this.areaFrontal=areaFrontal;
        this.estado = new EstadoScooter(estado);
    }

    /**
     * Constrói uma instância recebendo a descrição,a percentagem da bateria, o
     * peso máximo o peso da scooter,a potência da scooter e o estado da scooter
     *
     * @param descricao
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoScooter
     * @param potencia
     * @param estado
     */
    public Scooter(String descricao, double percentagemBateria, double pesoMaximo,
            double pesoScooter, double potencia,double areaFrontal, int estado) {
        this.id = 0;
        this.descricao = descricao;
        this.percentagemBateria = percentagemBateria;
        this.pesoMaximo = pesoMaximo;
        this.pesoScooter = pesoScooter;
        this.potencia = potencia;
        this.areaFrontal=areaFrontal;
        this.estado = new EstadoScooter(estado);
    }

    /**
     * Constrói uma instância vazia da scooter
     */
    public Scooter() {
        this.id = 0;
        this.percentagemBateria = 0;
        this.pesoMaximo = 0;
        this.pesoScooter = 0;
        this.potencia = 0;
        this.estado = new EstadoScooter(0);
    }

    /**
     * Devolve o id da scooter
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Devolve a descrição da scooter.
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Devolve a percentagem da bateria
     *
     * @return
     */
    public double getPercentagemBateria() {
        return percentagemBateria;
    }

    /**
     * Devolve o peso máximo
     *
     * @return
     */
    public double getPesoMaximo() {
        return pesoMaximo;
    }

    /**
     * Devolve o peso da scooter
     *
     * @return
     */
    public double getPesoScooter() {
        return pesoScooter;
    }

    /**
     * Devolve a potência da scooter
     *
     * @return
     */
    public double getPotencia() {
        return potencia;
    }
    
    /**
     * Devolve a área frontal da scooter
     * @return 
     */
    public double getAreaFrontal(){
        return areaFrontal;
    }

    /**
     * Devolve o estado da scooter
     *
     * @return
     */
    public EstadoScooter getEstadoScooter() {
        return estado;
    }

    /**
     * Modifica o id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifica a percentagem da bateria
     *
     * @param percentagemBateria
     */
    public void setPercentagemBateria(double percentagemBateria) {
        this.percentagemBateria = percentagemBateria;
    }

    /**
     * Modifica o peso máximo
     *
     * @param pesoMaximo
     */
    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    /**
     * Modifica o peso da scooter
     *
     * @param pesoScooter
     */
    public void setPesoScooter(double pesoScooter) {
        this.pesoScooter = pesoScooter;
    }

    /**
     * Modifica a potencia da scooter
     *
     * @param potencia
     */
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    /**
     * Modifica a área frontal da scooter
     * @param areaFrontal 
     */
    public void setAreaFrontal(double areaFrontal){
        this.areaFrontal=areaFrontal;
    }
    
    /**
     * Modifica o estado da scooter
     *
     * @param estado
     */
    public void setEstadoScooter(int estado) {
        this.estado = new EstadoScooter(estado);
    }
}
