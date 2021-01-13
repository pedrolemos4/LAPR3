package lapr.project.model;

/**
 *
 * @author beatr
 */
public class EstadoEstafeta {
    
    private int idEstadoEstafeta;
    private String designacao;
    
    /**
     * Constrói uma instância de EstadoEstafeta recebendo o id do estado do estafeta, o
     * designacao do estado de estafeta.
     *
     * @param idEstadoEstafeta id do estado de estafeta
     * @param designacao designacao do estado de estafeta
     */
    public EstadoEstafeta(int idEstadoEstafeta, String designacao) {
        this.idEstadoEstafeta = idEstadoEstafeta;
        this.designacao = designacao;
    }
    
    /**
     * Constrói uma instância de EstadoEstafeta com o estado do estafeta por omissão.
     */
    public EstadoEstafeta() {
        this.idEstadoEstafeta = 0;
        this.designacao = null;
    }
    
    /**
     * Devolve o id de estado do estafeta
     * @return id de estado do estafeta
     */
    public int getIdEstadoEstafeta() {
        return idEstadoEstafeta;
    }
    

    public void setIdEstadoEstafeta(int idEstadoEstafeta) {
        this.idEstadoEstafeta = idEstadoEstafeta;
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
