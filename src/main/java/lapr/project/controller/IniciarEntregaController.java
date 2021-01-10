package lapr.project.controller;

import java.util.List;
import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;

/**
 *
 * @author beatr
 */
public class IniciarEntregaController {
    
    private EntregaDB entregaDB;
    private EncomendaDB encomendaDB;
    private EstafetaDB estafetaDB;
    private EnderecoDB enderecoDB;
    private FacadeAuthorization facade;

    public IniciarEntregaController() {
        this.entregaDB = new EntregaDB();
        this.encomendaDB = new EncomendaDB();
        this.facade = new FacadeAuthorization();
    }
    
    public List<Entrega> getListaEntrega(){
        return entregaDB.getListaEntrega();
    }
    
    public List<Encomenda> getListaEncomenda(int idEntrega){
        return encomendaDB.getListaEncomenda(idEntrega);
    }
    
    public Estafeta getEstafeta(){
        String email = facade.getCurrentSession().getUser().getEmail();
        Estafeta est = estafetaDB.getEstafetaByEmail(email);
        return est;
    }
    
    public Endereco getEnderecoByNifCliente(int nif){
        return enderecoDB.getEnderecoByNifCliente(nif);
    }
}
