package lapr.project.controller;

import lapr.project.data.FarmaciaDB;
import lapr.project.data.TransferenciaDB;
import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

public class PedirItemFarmaciaController {
    FarmaciaDB fdb;
    TransferenciaDB tdb;

    public PedirItemFarmaciaController(FarmaciaDB fdb, TransferenciaDB tdb){
        this.fdb = fdb;
        this.tdb = tdb;
    }

    public Farmacia getFarmaciaByNIF(int nif) {
        return fdb.getFarmaciaByNIF(nif);
    }

    public boolean realizaPedido(Farmacia fOri, Farmacia fDest, Produto prod, int quantidade) {
        return tdb.realizaPedido(fOri,fDest,prod,quantidade);
    }

}
