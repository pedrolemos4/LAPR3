package lapr.project.model;


/**
 *
 * @author pedro
 */
public class Veiculo {

    private int id;
    private String descricao;
    private double capacidade;
    private double percentagemBateria;
    private double pesoMaximo;
    private double pesoVeiculo;
    private double potencia;
    private EstadoVeiculo estado;
    private double areaFrontal;

    /**
     * Constrói uma instância recebendo o id, a descrição,a percentagem da bateria, o
     * peso máximo o peso da veículo,a potência do veículo e o estado do veículo
     * 
     * @param id
     * @param descricao
     * @param capacidade
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoVeiculo
     * @param potencia
     * @param estado 
     * @param areaFrontal 
     */
    public Veiculo(int id, String descricao,double capacidade, double percentagemBateria, 
            double pesoMaximo, double pesoVeiculo, double potencia,int estado, double areaFrontal) {
        this.id = id;
        this.descricao = descricao;
        this.capacidade= capacidade;
        this.percentagemBateria = percentagemBateria;
        this.pesoMaximo = pesoMaximo;
        this.pesoVeiculo = pesoVeiculo;
        this.potencia = potencia;
        this.estado = new EstadoVeiculo(estado);
        this.areaFrontal = areaFrontal;
    }

    /**
     * Constrói uma instância recebendo a descrição,a percentagem da bateria, o
     * peso máximo o peso da veículo,a potência do veículo e o estado do veículo
     *
     * @param descricao
     * @param capacidade
     * @param percentagemBateria
     * @param pesoMaximo
     * @param pesoVeiculo
     * @param potencia
     * @param areaFrontal
     * @param estado
     */
    public Veiculo(String descricao,double capacidade,double percentagemBateria, 
            double pesoMaximo,double pesoVeiculo, double potencia,int estado, double areaFrontal) {
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.percentagemBateria = percentagemBateria;
        this.pesoMaximo = pesoMaximo;
        this.pesoVeiculo = pesoVeiculo;
        this.potencia = potencia;
        this.estado = new EstadoVeiculo(estado);
        this.areaFrontal = areaFrontal;
    }

    /**
     * Constrói umainstância de veículo recebendo outro veículo
     * @param outroVeiculo novo veículo
     */
    public Veiculo(Veiculo outroVeiculo){
        this.id = outroVeiculo.id;
        this.descricao = outroVeiculo.descricao;
        this.capacidade=outroVeiculo.capacidade;
        this.percentagemBateria = outroVeiculo.percentagemBateria;
        this.pesoMaximo = outroVeiculo.pesoMaximo;
        this.pesoVeiculo = outroVeiculo.pesoVeiculo;
        this.potencia = outroVeiculo.potencia;
        this.estado = new EstadoVeiculo(outroVeiculo.estado);
        this.areaFrontal = outroVeiculo.areaFrontal;
    }
    
    /**
     * Constrói uma instância vazia do veículo
     */
    public Veiculo() {
        this.id = 0;
        this.descricao = null;
        this.capacidade = 0;
        this.percentagemBateria = 0;
        this.pesoMaximo = 0;
        this.pesoVeiculo = 0;
        this.potencia = 0;
        this.estado = new EstadoVeiculo(0);
        this.areaFrontal = 0;
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
     * Devolve o estado do veiculo
     *
     * @return
     */
    public EstadoVeiculo getEstadoVeiculo(){
        return estado;
    }

    /**
     * Devolve a capacidade da bateria do veículo
     * @return 
     */
    public double getCapacidade(){
        return capacidade;
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
     * Modifica o estado do veículo pelo estado recebido por parametro
     *
     * @param estado
     */
    public void setEstadoVeiculo(int estado) {
        this.estado = new EstadoVeiculo(estado);
    }

    /**
     * Modifica a capacidade do veículo pelo estado recebido por parametro
     *
     * @param capacidade
     */
    public void setCapacidade(double capacidade){
        this.capacidade=capacidade;
    }

    /**
     * Devolve a area frontal
     * @return 
     */
    public double getAreaFrontal() {
        return areaFrontal;
    }

    /**
     * Modifica a area frontal
     * @param areaFrontal 
     */
    public void setAreaFrontal(double areaFrontal) {
        this.areaFrontal = areaFrontal;
    }

    /**
     * Devolve a instância de Veículo no formato String
     * @return string com a instância de veículo
     */
    @Override
    public String toString() {
        return "Veiculo:" 
                + "\nDescrição: \t" + descricao 
                + "\nCapacidade: \t" + capacidade
                + "\nPercentagem de Bateria= \t" + percentagemBateria 
                + "\nPeso máximo= \t" + pesoMaximo 
                + "\nPeso do Veiculo= \t"+ pesoVeiculo 
                + "\nPotência= \t" + potencia 
                +"\nEstado: \t" + estado.getDesignacao()
                +"\nArea Frontal: \t" + areaFrontal;
    }
    
    
}
