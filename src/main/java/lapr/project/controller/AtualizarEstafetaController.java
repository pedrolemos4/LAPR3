package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;

import java.util.List;

public class AtualizarEstafetaController {
    private final EstafetaDB estafetaDB;

    public AtualizarEstafetaController(EstafetaDB estafetaDB){
        this.estafetaDB = estafetaDB;
    }

    public List<Estafeta> getListaEstafetas() {
        return estafetaDB.getListaEstafetas();
    }

    public Estafeta getEstafetaByNIF(int nif) {
        return estafetaDB.getEstafetaByNIF(nif);
    }

    public boolean atualizarEstafeta(Estafeta est) {
        return (estafetaDB.atualizarEstafeta(est)? true : false);
    }
}
