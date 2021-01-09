package lapr.project.controller;

import java.util.List;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.model.Encomenda;
import lapr.project.model.Estafeta;

/**
 *
 * @author beatr
 */
public class RegistarEntregaController {
    
    private EstafetaDB estafetaDB;
    private EntregaDB entregaDB;
    
    public RegistarEntregaController(){
        
    }
    
    public Estafeta getEstafeta(){
        String email = null;/*AplicacaoPOT.getInstance().getSessaoAtual().getEmailUtilizador();*/
        Estafeta est = estafetaDB.getEstafetaByEmail(email);
        return est;
    }
    
    public void novaEntrega(String dataInicio, String dataFim){
        
    }
    
    public List<Encomenda> getListaEncomenda(){
        List<Encomenda> list = entregaDB.getListaEncomenda();
        return list;
    }
       
}
