package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Veiculo;

/**
 *
 * @author beatr
 */
public class RegistarEntregaController {
    
    private final FarmaciaDB farmaciaDB;
    private final EstafetaDB estafetaDB;
    private final EntregaDB entregaDB;
    private final EncomendaDB encomendaDB;
    private final VeiculoDB veiculoDB;
    private final EnderecoDB enderecoDB;

    public RegistarEntregaController(FarmaciaDB farmaciaDB, EstafetaDB estafetaDB, EntregaDB entregaDB, EncomendaDB encomendaDB, VeiculoDB veiculoDB, EnderecoDB enderecoDB) {
        this.farmaciaDB = farmaciaDB;
        this.estafetaDB = estafetaDB;
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.veiculoDB = veiculoDB;
        this.enderecoDB = enderecoDB;
    }
    
    public List<Farmacia> getLstFarmacias(){
        return farmaciaDB.getLstFarmacias();
    }
    
    public List<Veiculo> getListVeiculo(){
        return veiculoDB.getListaVeiculo();
    }
    
    public Endereco getEnderecoOrigem(int nifFarmacia){
        return enderecoDB.getEnderecoByNifFarmacia(nifFarmacia);
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
    
    public Endereco getEnderecoByNifCliente(int nif){
        return enderecoDB.getEnderecoByNifCliente(nif);
    }
    
    public List<Endereco> generateGraph(List<Endereco> listEnderecos, Estafeta est, Veiculo veiculo, double pesoTotal){
        return entregaDB.generateGraph(listEnderecos, est, veiculo, pesoTotal);
    }
       
}
