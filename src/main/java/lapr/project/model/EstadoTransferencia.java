package lapr.project.model;

public class EstadoTransferencia {
    private int idEstadoTransferencia;
    private String designacao;

    /**
     * Constrói uma instância de EstadoEstafeta recebendo o id do estado do estafeta, o
     * designacao do estado de estafeta.
     *
     * @param idEstadoTransferencia id do estado de estafeta
     * @param designacao designacao do estado de estafeta
     */
    public EstadoTransferencia(int idEstadoTransferencia, String designacao) {
        this.idEstadoTransferencia = idEstadoTransferencia;
        this.designacao = designacao;
    }

    /**
     * Constrói uma instância de EstadoEstafeta com o estado do estafeta por omissão.
     */
    public EstadoTransferencia() {
        this.idEstadoTransferencia = 0;
        this.designacao = null;
    }

    /**
     * Devolve o id de estado do estafeta
     * @return id de estado do estafeta
     */
    public int getIdEstadoTransferencia() {
        return idEstadoTransferencia;
    }


    public void setidEstadoTransferencia(int idEstadoTransferencia) {
        this.idEstadoTransferencia = idEstadoTransferencia;
    }

    /**
     * Devolve a designacao do estado de estafeta
     * @return designacao do estado de estafeta
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Modifica a designacao do estado de estafeta
     * @param designacao a nova designacao do estado d
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

}

