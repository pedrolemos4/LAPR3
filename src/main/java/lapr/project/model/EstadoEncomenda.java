package lapr.project.model;

public class EstadoEncomenda {
    private int id_estado_encomenda;
    private String designacao;

    /**
     * Constrói uma instância de estado da encomenda com o id e a designação do estado
     * @param id_estado_encomenda id do estado da encomenda
     * @param designacao designação do estado da encomenda
     */
    public EstadoEncomenda(int id_estado_encomenda, String designacao){
        this.id_estado_encomenda = id_estado_encomenda;
        this.designacao = designacao;
    }

    /**
     * Constrói uma instância de estado da encomenda com o id e designacao por omissão
     */
    public EstadoEncomenda(){
        this.id_estado_encomenda = 0;
        this.designacao = "";
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
