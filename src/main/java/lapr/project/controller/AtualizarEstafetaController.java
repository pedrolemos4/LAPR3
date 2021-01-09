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
        return estafetaDB.getLstEstafetas();
    }

    public void getEstafetaByEmail(String email) {
        Estafeta est = estafetaDB.getEstafetaByEmail(email);
        atualizarEstafeta(est);
    }

    public void atualizarEstafeta(Estafeta est) {
         estafetaDB.atualizarEstafeta(est);
    }
}
