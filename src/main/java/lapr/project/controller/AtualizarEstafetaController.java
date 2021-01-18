package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;

import java.util.List;

public class AtualizarEstafetaController {
    private final EstafetaDB estafetaDB;
    
    /**
     * Constroi uma instancia de AtualizarEstafetaController recebendo uma instancia de EstafetaDB
     * @param estafetaDB instancia de EstafetaDB
     */
    public AtualizarEstafetaController(EstafetaDB estafetaDB){
        this.estafetaDB = estafetaDB;
    }
    
    /**
     * Devolve lista de estafetas
     * @return lista de estafetas
     */
    public List<Estafeta> getListaEstafetas() {
        return estafetaDB.getListaEstafetas();
    }
    
    /**
     * Devolve um estafeta recebendo o seu nif por parametro
     * @param nif nif do estafeta
     * @return estafeta
     */
    public Estafeta getEstafetaByNIF(int nif) {
        return estafetaDB.getEstafetaByNIF(nif);
    }
    
    /**
     * Verifica se estafeta é atualizado recenbendo o estafeta por parametro
     * @param est estafeta que vai ser atualizado ou nao
     * @return true se os atributos do estafeta foram atualizados
     */
    public boolean atualizarEstafeta(Estafeta est) {
        return (estafetaDB.atualizarEstafeta(est)? (true) : (false));
    }
}
