package lapr.project.controller;

import java.util.List;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.TransferenciaDB;
import lapr.project.model.Farmacia;
import lapr.project.model.Graph;
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
        return (tdb.realizaPedido(fOri,fDest,prod,quantidade)? true : false);
    }
    
    public List<Farmacia> getListaFarmaciaByProduto(Produto p, int quant){
        return fdb.getLstFarmaciasByProdutos(p, quant);
    }

    public Graph<Farmacia, Double> generateGrafo(List<Farmacia> farms) {
        return fdb.generateGrafo(farms);
    }

    public int getFarmaciaProxima(Graph<Farmacia, Double> generateGrafo, int nif) {
        return fdb.getFarmaciaProxima(generateGrafo,nif);
    }

}
