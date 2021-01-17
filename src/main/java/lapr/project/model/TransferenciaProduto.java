package lapr.project.model;

public class TransferenciaProduto {
    private final int idTrans;
    private final int idRemetente;
    private final int idDestinatario;
    private final int idProduto;
    private final int quantidade;

    public TransferenciaProduto(int idTrans, int idRemetente, int idDestinatario, int idProduto, int quantidade){
        this.idTrans = idTrans;
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public TransferenciaProduto(){
        this.idTrans = 0;
        this.idRemetente = 0;
        this.idDestinatario = 0;
        this.idProduto = 0;
        this.quantidade = 0;
    }

    public int getIdTrans() {
        return idTrans;
    }

    public int getIdRemetente() {
        return idRemetente;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
