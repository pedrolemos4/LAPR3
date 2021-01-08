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
    private int percentagemBateria;
    private double pesoMaximo;
    private double pesoScooter;
    private double potencia;
    private int idEstado;

    /**
     * Contrói uma instância recebendo a percentagem da bateria, o peso máximo o
     * peso da scooter e a potência da scooter.
     *
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoScooter
     * @param potencia
     */
    public Scooter(int percentagemBateria, double pesoMaximo, double pesoScooter, double potencia, int estado) {
        this.id = 0;
        this.percentagemBateria = percentagemBateria;
        this.pesoMaximo = pesoMaximo;
        this.pesoScooter = pesoScooter;
        this.potencia = potencia;
        this.idEstado = estado;
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
        this.idEstado = 0;
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
     * Devolve a percentagem da bateria
     *
     * @return
     */
    public int getPercentagemBateria() {
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
     * Devolve a potência
     *
     * @return
     */
    public double getPotencia() {
        return potencia;
    }

    /**
     * Devolve o estado da scooter
     *
     * @return
     */
    public int getEstadoScooter() {
        return idEstado;
    }

    /**
     * Modifica o id
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
    public void setPercentagemBateria(int percentagemBateria) {
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
     * Modifica a potencia
     *
     * @param potencia
     */
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public void setEstadoScooter(int estado) {
        this.idEstado = estado;
    }
}
