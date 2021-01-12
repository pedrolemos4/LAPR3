package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class Encomenda {
    private final String dataPedida;
    private double preco;
    private double pesoEncomenda;
    private double taxa;
    private int nifCliente;
    private List<Produto> lst;
    private int estado;
    private int id;

    /**
     * Constrói uma instância de encomenda recebendo a lista de produtos, a data, o preco, o peso e a taxa da encomenda
     * @param nifCliente
     * @param dataPedida data em que foi pedida a encomenda
     * @param preco preco da encomenda
     * @param pesoEncomenda peso da encomenda
     * @param taxa taxa da encomenda
     * @param estado estado atual da encomenda
     */
    public Encomenda(int nifCliente, String dataPedida, double preco, double pesoEncomenda, double taxa, int estado) {
        this.lst=new ArrayList<>();
        this.nifCliente=nifCliente;
        this.dataPedida = dataPedida;
        this.preco = preco;
        this.pesoEncomenda = pesoEncomenda;
        this.taxa = taxa;
        this.estado = estado;
    }

    /**
     * Devolve o nif do cliente
     * @return 
     */
    public int getNif() {
        return nifCliente;
    }
    
    
    /**
     * Modifica o nif do cliente
     * @param nifCliente
     */
    public void setNif(int nifCliente) {
        this.nifCliente = nifCliente;
    }
    /**
     * Devolve o id
     * @return 
     */
    public int getId() {
        return id;
    }
    
    /**
     * Modifica o id da encomenda
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolve a lista de produtos
     * @return 
     */
    public List<Produto> getLst() {
        return new ArrayList<>(lst);
    }
    
    
    /**
     * Devolve o valor da data em que foi pedida a encomenda
     * @return data em que foi pedida a encomenda
     */
    public String getDataPedida() {
        return dataPedida;
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
        this.lst = new ArrayList<>(lst);
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
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    /**
     * Devolve o valor do estado da encomenda
     * @return estado da encomenda
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Modifica o valor do estado da encomenda pelo valor recebido por parâmetro
     * @param estado novo estado da encomenda
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }
}
