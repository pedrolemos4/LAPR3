package lapr.project.model;

public class EstadoEncomenda {
    private int idEstadoEncomenda;
    private String designacao;

    /**
     * Constrói uma instância de estado da encomenda com o id e a designação do estado
     * @param idEstadoEncomenda id do estado da encomenda
     * @param designacao designação do estado da encomenda
     */
    public EstadoEncomenda(int idEstadoEncomenda, String designacao){
        this.idEstadoEncomenda = idEstadoEncomenda;
        this.designacao = designacao;
    }

    /**
     * Constrói uma instância de estado da encomenda com o id e designacao por omissão
     */
    public EstadoEncomenda(){
        this.idEstadoEncomenda = 0;
        this.designacao = "";
    }

    /**
     * Devolve o id do estado da encomenda
     * @return 
     */
    public int getIdEstadoEncomenda() {
        return idEstadoEncomenda;
    }

    /**
     * Devolve a designação do estado da encomenda
     * @return designação do estado da encomenda
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Modifica o valor da designação do estado da encomenda com o valor recebido por parâmetro
     * @param designacao nova designação do estado da encomenda
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
