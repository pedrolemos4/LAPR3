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
public class Veiculo {

    private int id;
    private String descricao;
    private String tipo;
    private double percentagemBateria;
    private double pesoMaximo;
    private double pesoVeiculo;
    private double potencia;
    private double areaFrontal;
    private EstadoVeiculo estado;

    /**
     * Constrói uma instância recebendo o id, a descrição,a percentagem da bateria, o
     * peso máximo o peso da veículo,a potência do veículo e o estado do veículo
     * 
     * @param id
     * @param descricao
     * @param tipo
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoVeiculo
     * @param potencia
     * @param areaFrontal
     * @param estado 
     */
    public Veiculo(int id, String descricao,String tipo, double percentagemBateria, 
            double pesoMaximo, double pesoVeiculo, double potencia, double areaFrontal,int estado) {
        this.id = id;
        this.descricao = descricao;
        this.percentagemBateria = percentagemBateria;
        this.pesoMaximo = pesoMaximo;
        this.pesoVeiculo = pesoVeiculo;
        this.potencia = potencia;
        this.areaFrontal=areaFrontal;
        this.estado = new EstadoVeiculo(estado);
    }

    /**
     * Constrói uma instância recebendo a descrição,a percentagem da bateria, o
     * peso máximo o peso da veículo,a potência do veículo e o estado do veículo
     *
     * @param descricao
     * @param tipo
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoVeiculo
     * @param potencia
     * @param areaFrontal
     * @param estado
     */
    public Veiculo(String descricao, String tipo,double percentagemBateria, double pesoMaximo,
            double pesoVeiculo, double potencia,double areaFrontal, int estado) {
        this.descricao = descricao;
        this.percentagemBateria = percentagemBateria;
        this.pesoMaximo = pesoMaximo;
        this.pesoVeiculo = pesoVeiculo;
        this.potencia = potencia;
        this.areaFrontal=areaFrontal;
        this.estado = new EstadoVeiculo(estado);
    }

    /**
     * Constrói uma instância vazia do veículo
     */
    public Veiculo() {
        this.id = 0;
        this.percentagemBateria = 0;
        this.pesoMaximo = 0;
        this.pesoVeiculo = 0;
        this.potencia = 0;
        this.estado = new EstadoVeiculo(0);
    }

    /**
     * Devolve o id do veículo
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Devolve a descrição do veículo.
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Devolve o tipo do veículo
     * @return 
     */
    public String getTipo(){
        return tipo;
    }
    
    /**
     * Modifica o tipo do veiculo
     * @param tipo o novo tipo do veiculo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * Devolve o peso do veiculo
     *
     * @return
     */
    public double getPesoVeiculo() {
        return pesoVeiculo;
    }

    /**
     * Devolve a potência do veiculo
     *
     * @return
     */
    public double getPotencia() {
        return potencia;
    }
    
    /**
     * Devolve a área frontal do veiculo
     * @return 
     */
    public double getAreaFrontal(){
        return areaFrontal;
    }

    /**
     * Devolve o estado do veiculo
     *
     * @return
     */
    public EstadoVeiculo getEstadoVeiculo(){
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
     * Modifica a descrição do veículo.
     * @param descricao 
     */
    public void setDescricao (String descricao){
        this.descricao=descricao;
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
     * Modifica o peso da veículo
     *
     * @param pesoVeiculo
     */
    public void setPesoVeiculo(double pesoVeiculo) {
        this.pesoVeiculo= pesoVeiculo;
    }

    /**
     * Modifica a potencia do veículo
     *
     * @param potencia
     */
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    /**
     * Modifica a área frontal do veículo
     * @param areaFrontal 
     */
    public void setAreaFrontal(double areaFrontal){
        this.areaFrontal=areaFrontal;
    }
    
    /**
     * Modifica o estado do veículo
     *
     * @param estado
     */
    public void setEstadoVeiculo(int estado) {
        this.estado = new EstadoVeiculo(estado);
    }

    @Override
    public String toString() {
        return "Veiculo: \tid" + id 
                + "\nTipo: \t" + tipo 
                + "\nDescrição: \t" + descricao 
                + "\nPercentagem de Bateria= \t" + percentagemBateria 
                + "\nPeso máximo= \t" + pesoMaximo 
                + "\nPeso do Veiculo= \t"+ pesoVeiculo 
                + "\nPotência= \t" + potencia 
                + "\nÁrea frontal= \t" + areaFrontal 
                +"\nEstado: \t" + estado.getDesignacao();
    }
    
    
}
