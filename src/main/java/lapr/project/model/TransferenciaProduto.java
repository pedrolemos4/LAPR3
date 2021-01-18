package lapr.project.model;

public class TransferenciaProduto {
    private final int idTrans;
    private final int idRemetente;
    private final int idDestinatario;
    private final int idProduto;
    private final int quantidade;

    /**
     * Constrói uma instância de transferência com o id, o id da farmácia que fornece, id da farmácia que recebe,
     * o id do produto e a quantidade do produto
     * @param idTrans
     * @param idRemetente
     * @param idDestinatario
     * @param idProduto
     * @param quantidade
     */
    public TransferenciaProduto(int idTrans, int idRemetente, int idDestinatario, int idProduto, int quantidade){
        this.idTrans = idTrans;
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    /**
     * Constrói uma instância de transferência com os valores por omissão
     */
    public TransferenciaProduto(){
        this.idTrans = 0;
        this.idRemetente = 0;
        this.idDestinatario = 0;
        this.idProduto = 0;
        this.quantidade = 0;
    }

    /**
     * Devolve o valor do id da transferência
     * @return id da transferência
     */
    public int getIdTrans() {
        return idTrans;
    }

    /**
     * Devolve o valor do id da farmácia que envia os produtos
     * @return id da farmácia que envia os produtos
     */
    public int getIdRemetente() {
        return idRemetente;
    }

    /**
     * Devolve o valor do id da farmácia que recebe os produtos
     * @return id da farmácia que recebe os produtos
     */
    public int getIdDestinatario() {
        return idDestinatario;
    }

    /**
     * Devolve o valor do id do produto enviado
     * @return id do produto enviado
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * Devolve a quantidade do produto enviado
     * @return quantidade do produto enviado
     */
    public int getQuantidade() {
        return quantidade;
    }
}
