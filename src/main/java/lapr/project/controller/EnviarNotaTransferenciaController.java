package lapr.project.controller;

import lapr.project.data.FarmaciaDB;
import lapr.project.data.ProdutosDB;
import lapr.project.data.TransferenciaDB;
import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

public class EnviarNotaTransferenciaController {
    private final TransferenciaDB tbd;

    public EnviarNotaTransferenciaController(TransferenciaDB tbd){
        this.tbd = tbd;
    }

    public boolean enviarNotaTransferencia(Farmacia fOrig, Farmacia fDest, Produto prod, int qtd){
        return tbd.enviarNotaTransferencia(fOrig,fDest,prod,qtd);
    }
}
