package lapr.project.controller;

import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Graph;
import lapr.project.model.Veiculo;

/**
 *
 * @author beatr
 */
public class IniciarEntregaController {
    
    private final EntregaDB entregaDB;
    private final EncomendaDB encomendaDB;
    private final EstafetaDB estafetaDB;
    private final EnderecoDB enderecoDB;
    private final VeiculoDB veiculoDB;

    public IniciarEntregaController(EntregaDB entregaDB, EncomendaDB encomendaDB, EstafetaDB estafetaDB, EnderecoDB enderecoDB, VeiculoDB veiculoDB) {
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.estafetaDB = estafetaDB;
        this.enderecoDB = enderecoDB;
        this.veiculoDB = veiculoDB;
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
    
    public Veiculo getVeiculoById(int idVeiculo){
        return veiculoDB.getVeiculoById(idVeiculo);
    }
    
    public Estafeta getEstafeta(){
        int nif = UserSession.getInstance().getUser().getNIF();
        return estafetaDB.getEstafetaByNIF(nif);
    }
    
    public Endereco getEnderecoByNifCliente(int nif){
        return enderecoDB.getEnderecoByNifCliente(nif);
    }
    
    public Graph<Endereco, Double> generateGraph(List<Endereco> listEnderecos, Estafeta est, Veiculo veiculo, double pesoTotal){
        return entregaDB.generateGraph(listEnderecos, est, veiculo, pesoTotal);
    }
}
