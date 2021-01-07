package lapr.project.model;

/**
 *
 * @author beatr
 */
public class EstadoEstafeta {
    
    private int id_estado_estafeta;
    private String designacao;
    
    /**
     * Constrói uma instância de EstadoEstafeta recebendo o id do estado do estafeta, o
     * designacao do estado de estafeta.
     *
     * @param id_estado_estafeta id do estado de estafeta
     * @param designacao designacao do estado de estafeta
     */
    public EstadoEstafeta(int id_estado_estafeta, String designacao) {
        this.id_estado_estafeta = id_estado_estafeta;
        this.designacao = designacao;
    }
    
    /**
     * Constrói uma instância de EstadoEstafeta com o estado do estafeta por omissão.
     */
    public EstadoEstafeta() {
        this.id_estado_estafeta = 0;
        this.designacao = null;
    }
    
    /**
     * Devolve o id de estado do estafeta
     * @return id de estado do estafeta
     */
    public int getId_estado_estafeta() {
        return id_estado_estafeta;
    }
    
    /**
     * Modifica o id de estado do estafeta
     * @param id_estado_estafeta o novo id de estado do estafeta
     */
    public void setId_estado_estafeta(int id_estado_estafeta) {
        this.id_estado_estafeta = id_estado_estafeta;
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
