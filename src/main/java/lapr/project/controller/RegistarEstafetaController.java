package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;

import java.util.List;
import lapr.project.data.UtilizadorDB;
import lapr.project.model.Utilizador;

public class RegistarEstafetaController {

    private final EstafetaDB estafetaDB;
    private final UtilizadorDB utilizadorDB;

    public RegistarEstafetaController(EstafetaDB estafetaDB, UtilizadorDB utilizadorDB){
        this.estafetaDB = estafetaDB;
        this.utilizadorDB = utilizadorDB;
    }
    
    public Utilizador login(String email, String password) {
        int nif = utilizadorDB.validateLogin(email, password);
        return estafetaDB.getEstafetaByNIF(nif);
    }

    public List<Estafeta> getListaEstafetas() {
        return estafetaDB.getListaEstafetas();
    }

    public Estafeta novoEstafeta(int nif, String nome, String email, double peso, int nss, String pwd) {
        return estafetaDB.novoEstafeta(nif,nome,email,peso,nss,pwd);
    }

    public boolean registaEstafeta(Estafeta est) {
        return (estafetaDB.registaEstafeta(est) ? (true) : (false));
    }
}
