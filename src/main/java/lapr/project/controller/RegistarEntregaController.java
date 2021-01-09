package lapr.project.controller;

import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

/**
 *
 * @author beatr
 */
public class RegistarEntregaController {
    
    private EstafetaDB estafetaDB;
    private EntregaDB entregaDB;
    private EncomendaDB encomendaDB;
    private ScooterDB scooterDB;
<<<<<<< HEAD

    public RegistarEntregaController(EstafetaDB estafetaDB, EntregaDB entregaDB, EncomendaDB encomendaDB, ScooterDB scooterDB) {
        this.estafetaDB = estafetaDB;
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.scooterDB = scooterDB;
=======
    
    public RegistarEntregaController(){
        
>>>>>>> 0d189dff8e012264528bfd55a71c53eb0c2b4b9b
    }
    
    public List<Scooter> getListScooter(){
        List<Scooter> listScooter = scooterDB.getListaScooter();
        return listScooter;
    }
    public Estafeta getEstafeta(){
        String email = null;/*AplicacaoPOT.getInstance().getSessaoAtual().getEmailUtilizador();*/
        Estafeta est = estafetaDB.getEstafetaByEmail(email);
        return est;
    }
    
    public Scooter getScooter(int idScooter){
        Scooter s = scooterDB.getScooterById(idScooter);
        return s;
    }
      
    public List<Encomenda> getListaEncomenda(){
        List<Encomenda> list = encomendaDB.getListaEncomenda();
        return list;
    }
    
    public void addEntrega(Entrega en){
        entregaDB.addEntrega(en);
    }
    
    public void addEncomendaEntrega(Entrega e, Encomenda enc){        
        entregaDB.addEncomendaEntrega(e,enc);
    }
       
}
