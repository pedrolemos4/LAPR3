package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;

import java.util.List;

public class RegistarEstafetaController {

    private final EstafetaDB estafetaDB;

    public RegistarEstafetaController(EstafetaDB estafetaDB){
        this.estafetaDB = estafetaDB;
    }

    public List<Estafeta> getListaEstafetas() {
        return estafetaDB.getListaEstafetas();
    }

    public Estafeta novoEstafeta(int nif, String nome, String email, double peso, int nss, String pwd) {
        return estafetaDB.novoEstafeta(nif,nome,email,peso,nss,pwd);
    }

    public boolean registaEstafeta(Estafeta est) {
        return estafetaDB.registaEstafeta(est);
    }
}
