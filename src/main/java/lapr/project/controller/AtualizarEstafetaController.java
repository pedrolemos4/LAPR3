package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;

import java.util.List;

public class AtualizarEstafetaController {
    private final EstafetaDB estafetaDB;

    public AtualizarEstafetaController(){
        this.estafetaDB = new EstafetaDB();
    }

    public List<Estafeta> getListaEstafetas() {
        return estafetaDB.getListaEstafetas();
    }

    public Estafeta getEstafetaByEmail(String email) {
        Estafeta est = estafetaDB.getEstafetaByEmail(email);
        return est;
    }

    public void atualizarEstafeta(Estafeta est) {
         estafetaDB.atualizarEstafeta(est);
    }
}
