package lapr.project.model;

public class EstadoEncomenda {

    private int idEstadoEncomenda;
    private String designacao;
    private int estado;

    /**
     * Constrói uma instância de estado da encomenda com o id e a designação do
     * estado
     *
     * @param idEstadoEncomenda id do estado da encomenda
     * @param designacao designação do estado da encomenda
     */
    public EstadoEncomenda(int estado, String designacao) {
        this.estado = estado;
        this.idEstadoEncomenda = 0;
        this.designacao = designacao;
    }

    /**
     * Constrói uma instância de estado da encomenda com o id e designacao por
     * omissão
     */
    public EstadoEncomenda() {
        this.idEstadoEncomenda = 0;
        this.estado = 0;
        this.designacao = "";
    }

    /**
     * Controi uma instância de estado encomenda com o estado
     *
     * @param estado
     */
    public EstadoEncomenda(int estado) {
        this.idEstadoEncomenda = 0;
        this.estado = estado;
        if (estado == 1) {
            this.designacao = "Encomendado";
        }
        if (estado == 2) {
            this.designacao = "Entregando";
        }
        if (estado == 3) {
            this.designacao = "Entregue";
        }
    }

    /**
     * Devolve o id do estado da encomenda
     *
     * @return
     */
    public int getIdEstadoEncomenda() {
        return idEstadoEncomenda;
    }

    /**
     * Devolve a designação do estado da encomenda
     *
     * @return designação do estado da encomenda
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Modifica o valor da designação do estado da encomenda com o valor
     * recebido por parâmetro
     *
     * @param designacao nova designação do estado da encomenda
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * Devolve o estado
     *
     * @return
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Modifica o id
     *
     * @param idEstadoEncomenda
     */
    public void setIdEstadoEncomenda(int idEstadoEncomenda) {
        this.idEstadoEncomenda = idEstadoEncomenda;
    }

    /**
     * Modifica o estado
     *
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }
}
