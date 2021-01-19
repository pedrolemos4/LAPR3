package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Caminho {
    
    private Endereco end1;
    private Endereco end2;
    private double roadResistanceCoefficient;
    private double velocidadeVento;
    private double direcaoVento;
    
    /**
     * Constrói uma instância de Caminho recebendo o endereço de origem, o endereço de destino,
     * roadResistanceCoefficient, a velocidade do vento e a sua direçao nesse percurso
     * @param end1 endereço de origem
     * @param end2 endereço de destino
     * @param roadResistanceCoefficient roadResistanceCoefficient
     * @param velocidadeVento velocidade do vento
     * @param direcaoVento direçao do vento
     */
    public Caminho(Endereco end1, Endereco end2, double roadResistanceCoefficient, double velocidadeVento, double direcaoVento) {
        this.end1 = end1;
        this.end2 = end2;
        this.roadResistanceCoefficient = roadResistanceCoefficient;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }
    
    /**
     * Devolve o endereço de origem
     * @return endereço de origem
     */
    public Endereco getEnd1() {
        return end1;
    }
    
    /**
     * Modifica o endereço de origem
     * @param end1 o novo endereço de origem
     */
    public void setEnd1(Endereco end1) {
        this.end1 = end1;
    }
    
    /**
     * Devolve o endereço de destino
     * @return endereço de destino
     */
    public Endereco getEnd2() {
        return end2;
    }
    
    /**
     * Modifica o endereço de destino
     * @param end2 o novo endereço de destino
     */
    public void setEnd2(Endereco end2) {
        this.end2 = end2;
    }
    
    /**
     * Devolve o roadResistanceCoefficient
     * @return roadResistanceCoefficient
     */
    public double getRoadResistanceCoefficient() {
        return roadResistanceCoefficient;
    }
    
    /**
     * Modifica o roadResistanceCoefficient
     * @param roadResistanceCoefficient o novo roadResistanceCoefficient
     */
    public void setRoadResistanceCoefficient(double roadResistanceCoefficient) {
        this.roadResistanceCoefficient = roadResistanceCoefficient;
    }
    
    /**
     * Devolve a velocidade do vento
     * @return velocidade do vento
     */
    public double getVelocidadeVento() {
        return velocidadeVento;
    }
    
    /**
     * Modifica a velocidade do vento
     * @param velocidadeVento a nova velocidade do vento
     */
    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }
    
    /**
     * Devolve a direçao do vento
     * @return direçao do vento
     */
    public double getDirecaoVento() {
        return direcaoVento;
    }
    
    /**
     * Modifica a direçao do vento
     * @param direcaoVento a nova direçao do vento
     */
    public void setDirecaoVento(double direcaoVento) {
        this.direcaoVento = direcaoVento;
    }
    
    /**
     * Devolve a descrição textual do Caminho
     * @return descriçao textual do caminho
     */
    @Override
    public String toString() {
        return "Caminho{" + "end1=" + end1 + ", end2=" + end2 + ", roadResistanceCoefficient=" + roadResistanceCoefficient + ", velocidadeVento=" + velocidadeVento + ", direcaoVento=" + direcaoVento + '}';
    }
    
}
