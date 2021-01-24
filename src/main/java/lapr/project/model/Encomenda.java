package lapr.project.model;

import java.util.Objects;

public class Encomenda {
    private final String dataPedida;
    private double preco;
    private double pesoEncomenda;
    private double taxa;
    private int nifCliente;
    private int nifFarmacia;
    private EstadoEncomenda estado;
    private int id;

    /**
     * Constrói uma instância de encomenda recebendo a lista de produtos, a data, o preco, o peso e a taxa da encomenda
     * @param nifCliente nif do cliente
     * @param nifFarmacia nif da farmacia
     * @param dataPedida data em que foi pedida a encomenda
     * @param preco preco da encomenda
     * @param pesoEncomenda peso da encomenda
     * @param taxa taxa da encomenda
     * @param estado estado atual da encomenda
     */
    public Encomenda(int nifCliente, int nifFarmacia, String dataPedida, double preco, double pesoEncomenda, double taxa, int estado) {
        this.nifCliente=nifCliente;
        this.dataPedida = dataPedida;
        this.preco = preco;
        this.pesoEncomenda = pesoEncomenda;
        this.taxa = taxa;
        this.estado = new EstadoEncomenda(estado);
        this.nifFarmacia = nifFarmacia;
    }
    
    /**
     * Constroi uma instancia de Encomenda vazia
     */
    public Encomenda() {
        this.nifCliente=0;
        this.dataPedida = null;
        this.preco = 0;
        this.pesoEncomenda = 0;
        this.taxa = 0;
        this.estado = new EstadoEncomenda();
        this.nifFarmacia = 0;
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
     * Devolve o nif da farmacia
     * @return 
     */
    public int getNifFarmacia() {
        return nifFarmacia;
    }

    /**
     * Modifica o nif da farmacia da encomenda
     * @param nifFarmacia 
     */
    public void setNifFarmacia(int nifFarmacia) {
        this.nifFarmacia = nifFarmacia;
    }
    
    
    
    /**
     * Modifica o id da encomenda
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * Compara o objeto o com a instância criada da Encomenda
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        int a = Double.compare(encomenda.preco, preco);
        int b = Double.compare(encomenda.pesoEncomenda, pesoEncomenda);
        int c = Double.compare(encomenda.taxa, taxa);
        return a == 0 && b == 0 && c == 0 &&
                nifCliente == encomenda.nifCliente &&
                nifFarmacia == encomenda.nifFarmacia &&
                id == encomenda.id &&
                dataPedida.equals(encomenda.dataPedida) &&
                estado.equals(encomenda.estado);
    }

    /**
     * Retorna o hashCode da encomenda
     * @return 
     */
    @Override    
    public int hashCode() {
        return Objects.hash(dataPedida, preco, pesoEncomenda, taxa, nifCliente, nifFarmacia, estado, id);
    }

    /**
     * Método que retorna uma descrição escrita de uma encomenda
     *
     * @return
     */
    @Override
    public String toString() {
        return "Encomenda{" + "dataPedida=" + dataPedida + ", preco=" + preco + ", pesoEncomenda=" + pesoEncomenda + ", taxa=" + taxa + ", nifCliente=" + nifCliente + ", nifFarmacia=" + nifFarmacia + ", estado=" + estado.getDesignacao() + ", id=" + id + '}';
    }
    
    
}
