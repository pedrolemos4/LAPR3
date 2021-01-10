package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.authorization.FacadeAuthorization;
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
    private FacadeAuthorization facade;

    public RegistarEntregaController() {
        this.estafetaDB = new EstafetaDB();
        this.entregaDB = new EntregaDB();
        this.encomendaDB = new EncomendaDB();
        this.scooterDB = new ScooterDB();
        this.facade = POTApplication.getFacadeAuthorization();
    }
    
    public List<Scooter> getListScooter(){
        List<Scooter> listScooter = scooterDB.getListaScooter();
        return listScooter;
    }
    public Estafeta getEstafeta(){
        String email = facade.getCurrentSession().getUser().getEmail();
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
    
    public void addEntrega(Entrega en) throws SQLException{
        entregaDB.addEntrega(en);
    }
    
    public void addEncomendaEntrega(Entrega e, Encomenda enc){        
        entregaDB.addEncomendaEntrega(e,enc);
    }
       
}
