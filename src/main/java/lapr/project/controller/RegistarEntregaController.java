package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Veiculo;

/**
 *
 * @author beatr
 */
public class RegistarEntregaController {
    
    private final EstafetaDB estafetaDB;
    private final EntregaDB entregaDB;
    private final EncomendaDB encomendaDB;
    private final VeiculoDB veiculoDB;

    public RegistarEntregaController(EstafetaDB estafetaDB, EntregaDB entregaDB, EncomendaDB encomendaDB, VeiculoDB veiculoDB) {
        this.estafetaDB = estafetaDB;
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.veiculoDB = veiculoDB;
    }
    
    public List<Veiculo> getListVeiculo(){
        return veiculoDB.getListaVeiculo();
    }
    public Estafeta getEstafeta(){
        String email = UserSession.getInstance().getUser().getEmail();
        return estafetaDB.getEstafetaByEmail(email);
    }
    
    public Veiculo getVeiculo(int idVeiculo){
        return veiculoDB.getVeiculoById(idVeiculo);
    }
      
    public List<Encomenda> getListaEncomenda(){
        return encomendaDB.getListaEncomenda();
    }
    
    public Entrega addEntrega(String dataInicio, String dataFim, int idVeiculo, int idEstafeta) throws SQLException{
        Entrega en = new Entrega(dataInicio, dataFim, idVeiculo, idEstafeta);
        en.setIdEntrega(entregaDB.addEntrega(en));
        return en;
    }
    
    public void addEncomendaEntrega(Entrega e, Encomenda enc){        
        entregaDB.addEncomendaEntrega(e,enc);
    }
       
}
