package lapr.project.controller;

import java.util.List;
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
    
    private final EntregaDB entregaDB;
    private final EncomendaDB encomendaDB;
    private final EstafetaDB estafetaDB;
    private final EnderecoDB enderecoDB;
    private final ScooterDB scooterDB;

    public IniciarEntregaController(EntregaDB entregaDB, EncomendaDB encomendaDB, EstafetaDB estafetaDB, EnderecoDB enderecoDB, ScooterDB scooterDB) {
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.estafetaDB = estafetaDB;
        this.enderecoDB = enderecoDB;
        this.scooterDB = scooterDB;
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
        return estafetaDB.getEstafetaByEmail(email);
    }
    
    public Endereco getEnderecoByNifCliente(int nif){
        return enderecoDB.getEnderecoByNifCliente(nif);
    }
    
    public Endereco getEnderecoParque(){
        return enderecoDB.getEnderecoParque();
    }
    
    public List<Endereco> generateGraph(List<Endereco> listEnderecos, Estafeta est, Scooter scooter, double pesoTotal){
        return entregaDB.generateGraph(listEnderecos, est, scooter, pesoTotal);
    }
}
