package lapr.project.model;

import java.util.List;

public class Encomenda {
    private String dataPedida;
    private double preco;
    private double pesoEncomenda;
    private String morada;
    private double taxa;
    private List<Produto> lst;
    private EstadoEncomenda estado;

    /**
     * Constrói uma instância de encomenda recebendo a lista de produtos, a data, o preco, o peso e a taxa da encomenda
     * @param lst
     * @param dataPedida data em que foi pedida a encomenda
     * @param preco preco da encomenda
     * @param pesoEncomenda peso da encomenda
     * @param taxa taxa da encomenda
     * @param estado estado atual da encomenda
     */
    public Encomenda(List<Produto> lst, String morada, String dataPedida, double preco, double pesoEncomenda, double taxa, EstadoEncomenda estado) {
        this.lst=lst;
        this.morada=morada;
        this.dataPedida = dataPedida;
        this.preco = preco;
        this.pesoEncomenda = pesoEncomenda;
        this.taxa = taxa;
        this.estado = estado;
    }

    /**
     * Devolve a lista de produtos
     * @return 
     */
    public List<Produto> getLst() {
        return lst;
    }

    /**
     * Devolve a morada
     * @return 
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Modifica a morada
     * @param morada 
     */
    public void setMorada(String morada) {
        this.morada = morada;
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
     * Modifica a lista de encomenda
     * @param lst 
     */
    public void setLst(List<Produto> lst) {
        this.lst = lst;
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

    /**
     * Devolve o valor do estado da encomenda
     * @return estado da encomenda
     */
    public EstadoEncomenda getEstado() {
        return estado;
    }

    /**
     * Modifica o valor do estado da encomenda pelo valor recebido por parâmetro
     * @param estado novo estado da encomenda
     */
    public void setEstado(EstadoEncomenda estado) {
        this.estado = estado;
    }
}
