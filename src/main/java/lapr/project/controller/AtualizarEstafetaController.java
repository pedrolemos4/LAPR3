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

    public Estafeta getEstafetaByEmail(String email) {
        return estafetaDB.getEstafetaByEmail(email);
    }

    public void atualizarEstafeta(Estafeta est) {
         estafetaDB.atualizarEstafeta(est);
    }
}
