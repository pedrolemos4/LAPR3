package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

/**
 *
 * @author beatr
 */
public class IniciarEntregaController {
    
    private EntregaDB entregaDB;
    private EncomendaDB encomendaDB;
    private EstafetaDB estafetaDB;
    private EnderecoDB enderecoDB;
    private ScooterDB scooterDB;

    public IniciarEntregaController() {
        this.entregaDB = new EntregaDB();
        this.encomendaDB = new EncomendaDB();
        this.estafetaDB = new EstafetaDB();
        this.enderecoDB = new EnderecoDB();
    }
    
    public List<Entrega> getListaEntregaByNifEstafeta(int nifEstafeta){
        return entregaDB.getListaEntregaByNifEstafeta(nifEstafeta);
    }
    
    public List<Encomenda> getListaEncomendaById(int idEntrega){
        return encomendaDB.getListaEncomendaById(idEntrega);
    }
    
    public Entrega getEntregaById(int idEntrega){
        return entregaDB.getEntregaById(idEntrega);
    }
    
    public Scooter getScooterById(int idScooter){
        return scooterDB.getScooterById(idScooter);
    }
    
    public Estafeta getEstafeta(){
        String email = UserSession.getInstance().getUser().getEmail();
        Estafeta est = estafetaDB.getEstafetaByEmail(email);
        return est;
    }
    
    public Endereco getEnderecoByNifCliente(int nif){
        return enderecoDB.getEnderecoByNifCliente(nif);
    }
    
    public Endereco getEnderecoParque(){
        return enderecoDB.getEnderecoParque();
    }
    
    public LinkedList<Endereco> generateGraph(List<Endereco> listEnderecos, Estafeta est, Scooter scooter, double pesoTotal){
        return entregaDB.generateGraph(listEnderecos, est, scooter, pesoTotal);
    }
}
