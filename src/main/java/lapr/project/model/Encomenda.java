package lapr.project.model;

public class Encomenda {
    private String dataPedida;
    private double preco;
    private double pesoEncomenda;
    private double taxa;
    private EstadoEncomenda estado;

    /**
     * Constrói uma instância de encomenda recebendo a data, o preco, o peso e a taxa da encomenda
     * @param dataPedida data em que foi pedida a encomenda
     * @param preco preco da encomenda
     * @param pesoEncomenda peso da encomenda
     * @param taxa taxa da encomenda
     */
    public Encomenda(String dataPedida, double preco, double pesoEncomenda, double taxa) {
        this.dataPedida = dataPedida;
        this.preco = preco;
        this.pesoEncomenda = pesoEncomenda;
        this.taxa = taxa;
    }

    /**
     * Devolve o valor da data em que foi pedida a encomenda
     * @return data em que foi pedida a encomenda
     */
    public String getDataPedida() {
        return dataPedida;
    }

    /**
     * Modifica o valor da data da encomenda pelo valor recebido por parâmetro
     * @param dataPedida nova data da encomenda
     */
    public void setDataPedida(String dataPedida) {
        this.dataPedida = dataPedida;
    }

    /**
     * Devolve o valor do preco da encomenda
     * @return preco da encomenda
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Modifica o valor do preco da encomenda pelo valor recebido por parâmetro
     * @param preco novo preco da encomenda
     */
    public void setPreco(int preco) {
        this.preco = preco;
    }

    /**
     * Devolve o valor do peso da encomenda
     * @return peso da encomenda
     */
    public double getPesoEncomenda() {
        return pesoEncomenda;
    }

    /**
     * Modifica o valor do peso da encomenda pelo valor recebido por parâmetro
     * @param pesoEncomenda novo peso da encomenda
     */
    public void setPesoEncomenda(int pesoEncomenda) {
        this.pesoEncomenda = pesoEncomenda;
    }

    /**
     * Devolve o valor da taxa da encomenda
     * @return taxa da encomenda
     */
    public double getTaxa() {
        return taxa;
    }

    /**
     * Modifica o valor do taxa da encomenda pelo valor recebido por parâmetro
     * @param taxa nova taxa da encomenda
     */
    public void setTaxa(int taxa) {
        this.taxa = taxa;
    }
}
