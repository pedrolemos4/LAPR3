package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

/**
 *
 * @author beatr
 */
public class RegistarEntregaController {
    
    private final EstafetaDB estafetaDB;
    private final EntregaDB entregaDB;
    private final EncomendaDB encomendaDB;
    private final ScooterDB scooterDB;

    public RegistarEntregaController(EstafetaDB estafetaDB, EntregaDB entregaDB, EncomendaDB encomendaDB, ScooterDB scooterDB) {
        this.estafetaDB = estafetaDB;
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.scooterDB = scooterDB;
    }
    
    public List<Scooter> getListScooter(){
        return scooterDB.getListaScooter();
    }
    public Estafeta getEstafeta(){
        String email = UserSession.getInstance().getUser().getEmail();
        return estafetaDB.getEstafetaByEmail(email);
    }
    
    public Scooter getScooter(int idScooter){
        return scooterDB.getScooterById(idScooter);
    }
      
    public List<Encomenda> getListaEncomenda(){
        return encomendaDB.getListaEncomenda();
    }
    
    public void addEntrega(Entrega en) throws SQLException{
        entregaDB.addEntrega(en);
    }
    
    public void addEncomendaEntrega(Entrega e, Encomenda enc){        
        entregaDB.addEncomendaEntrega(e,enc);
    }
       
}
