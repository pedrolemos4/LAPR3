package lapr.project.data;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;
import lapr.project.model.TransferenciaProduto;

public class TransferenciaDB extends DataHandler{
    public boolean realizaPedido(Farmacia fOrig, Farmacia fDest, Produto produto) {
        return true;
    }

    public boolean addTransferencia(TransferenciaProduto trans) {
        return true;
    }
}
