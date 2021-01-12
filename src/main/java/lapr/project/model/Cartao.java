package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Cartao {

    private int numeroCartao;
    private String dataDeValidade;
    private int ccv;
    
    /**
     * Constrói uma instância de Cartao recebendo o numero de cartao, a
     * data de validade do cartao e o CCV do cartao
     * @param numeroCartao o numero de cartao
     * @param dataDeValidade a data de validade de cartao
     * @param ccv o CCV de cartao
     */
    public Cartao(int numeroCartao, String dataDeValidade, int ccv) {
        this.numeroCartao = numeroCartao;
        this.dataDeValidade = dataDeValidade;
        this.ccv = ccv;
    }
    
    /**
     * Constrói uma instância de Cartao com o cartao por omissão.
     */
    public Cartao() {
        this.numeroCartao = 0;
        this.dataDeValidade = null;
        this.ccv = 0;
    }
    
    /**
     * Devolve o numero de cartao.
     *
     * @return numero de cartao.
     */
    public int getNumeroCartao() {
        return numeroCartao;
    }
    
    /**
     * Modifica o numero de cartao
     * @param numeroCartao o novo numero de cartao
     */
    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    
    /**
     * Devolve a data de validade do cartao
     * @return data de validade do cartao
     */
    public String getDataDeValidade() {
        return dataDeValidade;
    }
    
    /**
     * Modifica a data de validade do cartao
     * @param dataDeValidade a nova data de validade do cartao
     */
    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }
    
    /**
     * Devolve o CCV do cartao
     * @return CCV do cartao
     */
    public int getCCV() {
        return ccv;
    }
    
    /**
     * Modifica o CCV do cartao
     * @param ccv o novo CCV do cartao
     */
    public void setCCV(int ccv) {
        this.ccv = ccv;
    }
    
}
