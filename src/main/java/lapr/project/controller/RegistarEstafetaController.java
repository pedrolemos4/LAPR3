package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;

import java.util.List;
import lapr.project.data.UtilizadorDB;
import lapr.project.model.Utilizador;

public class RegistarEstafetaController {

    private final EstafetaDB estafetaDB;
    private final UtilizadorDB utilizadorDB;
    
    /**
     * Constroi uma instancia de RegistarClienteController recebendo uma instancia de EstafetaDB e UtilizadorDB
     * @param estafetaDB instancia de EstafetaDB
     * @param utilizadorDB instancia de UtilizadorDB
     */
    public RegistarEstafetaController(EstafetaDB estafetaDB, UtilizadorDB utilizadorDB){
        this.estafetaDB = estafetaDB;
        this.utilizadorDB = utilizadorDB;
    }
    
    /**
     * Devolve o utilizador que fez login recebendo por parametro o email e a password
     * @param email email do utilizador
     * @param password password do utilizador
     * @return utilizador
     */
    public Utilizador login(String email, String password) {
        int nif = utilizadorDB.validateLogin(email, password);
        return estafetaDB.getEstafetaByNIF(nif);
    }
    
    /**
     * Devolve uma lista de estafetas
     * @return lista de estafetas
     */
    public List<Estafeta> getListaEstafetas() {
        return estafetaDB.getListaEstafetas();
    }
    
    /**
     * Devolve um estafeta recebendo por parametro o nif, nome, email, peso, numero de segurança social e password
     * @param nif nif do estafeta
     * @param nome nome do estafeta
     * @param email email do estafeta
     * @param peso peso do estafeta
     * @param nss numero de segurança social do estafeta
     * @param pwd password do estafeta
     * @return estafeta
     */
    public Estafeta novoEstafeta(int nif, String nome, String email, double peso, int nss, String pwd) {
        return estafetaDB.novoEstafeta(nif,nome,email,peso,nss,pwd);
    }
    
    /**
     * Verifica se estafeta foi registado recebendo por parametro o estafeta
     * @param est estafeta
     * @return true se o estafeta foi registado
     */
    public boolean registaEstafeta(Estafeta est) {
        return (estafetaDB.registaEstafeta(est) ? (true) : (false));
    }
}
