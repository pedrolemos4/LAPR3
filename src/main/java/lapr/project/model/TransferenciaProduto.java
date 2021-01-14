package lapr.project.model;

public class TransferenciaProduto {
    private int idTrans;
    private int idRemetente;
    private int idDestinatario;
    private int idProduto;
    private int quantidade;
    private int idEstadoTransferencia;

    public TransferenciaProduto(int idTrans, int idRemetente, int idDestinatario, int idProduto, int quantidade, int idEstadoTransferencia){
        this.idTrans = idTrans;
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.idEstadoTransferencia = idEstadoTransferencia;
    }

    public TransferenciaProduto(){
        this.idTrans = 0;
        this.idRemetente = 0;
        this.idDestinatario = 0;
        this.idProduto = 0;
        this.quantidade = 0;
        this.idEstadoTransferencia = 0;
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

    public int getIdEstadoTransferencia() {
        return idEstadoTransferencia;
    }
}
