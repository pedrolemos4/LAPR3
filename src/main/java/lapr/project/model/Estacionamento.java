package lapr.project.model;

public class Estacionamento {
    private int numeroLote;
    private int carregador;

    /**
     * Constrói uma instância de estacionamento com o número do lugar e um valor
     * que mostra se tem estacao de carregamento sendo 0 não e 1 sim
     * @param numeroLote número do lugar do estacionamento
     * @param carregador valor que mostra se tem estacao de carregamento sendo 0 não e 1 sim
     */
    public Estacionamento(int numeroLote, int carregador){
        this.numeroLote = numeroLote;
        this.carregador = carregador;
    }

    /**
     * Constrói uma instância de estacionamento com o número do lugar por omissão e
     * o carregador como 0 por omissão
     */
    public Estacionamento(){
        this.numeroLote = 0;
        this.carregador = 0;
    }

    /**
     * Devolve o valor do número do lugar do estacionamento
     * @return número do lugares do estacionamento
     */
    public int getNumeroLote() {
        return numeroLote;
    }

    /**
     * Modifica o valor do número do lugar do estacionamento com o valor recebido por parâmetro
     * @param numeroLote novo número do lugar do estacionamento
     */
    public void setNumeroLote(int numeroLote) {
        this.numeroLote = numeroLote;
    }

    /**
     * Devolve o valor do carregador no estacionamento
     * @return valor do carregador no estacionamento
     */
    public int getCarregador() {
        return carregador;
    }

    /**
     * Modifica o valor do carregador no estacionamento com o valor recebido por parâmetro
     * @param carregador novo valor do carregador no estacionamento
     */
    public void setCarregador(int carregador) {
        this.carregador = carregador;
    }
}
