package lapr.project.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Graph;
import lapr.project.model.Utilizador;
import lapr.project.model.Veiculo;

/**
 *
 * @author beatr
 */
public class RegistarEntregaController {
    
    private final UtilizadorDB utilizadorDB;
    private final FarmaciaDB farmaciaDB;
    private final EstafetaDB estafetaDB;
    private final EntregaDB entregaDB;
    private final EncomendaDB encomendaDB;
    private final VeiculoDB veiculoDB;
    private final EnderecoDB enderecoDB;
    private final EmailDB emailDB;
    private final ClienteDB clienteDB;

    public RegistarEntregaController(UtilizadorDB utilizadorDB,FarmaciaDB farmaciaDB, EstafetaDB estafetaDB, EntregaDB entregaDB, EncomendaDB encomendaDB, VeiculoDB veiculoDB, EnderecoDB enderecoDB, EmailDB emailDB, ClienteDB clienteDB) {
        this.utilizadorDB = utilizadorDB;
        this.farmaciaDB = farmaciaDB;
        this.estafetaDB = estafetaDB;
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.veiculoDB = veiculoDB;
        this.enderecoDB = enderecoDB;
        this.emailDB = emailDB;
        this.clienteDB = clienteDB;
    }
    
    public List<Farmacia> getLstFarmacias(){
        return farmaciaDB.getLstFarmacias();
    }
    
    public Farmacia getFarmaciaByNif(int nifFarmacia){
        return farmaciaDB.getFarmaciaByNIF(nifFarmacia);
    }
    
    public List<Veiculo> getListVeiculo(){
        return veiculoDB.getListaVeiculo();
    }
    
    public Endereco getEnderecoOrigem(int nifFarmacia){
        return enderecoDB.getEnderecoByNifFarmacia(nifFarmacia);
    }
    public Estafeta getEstafeta(){
        int nif = UserSession.getInstance().getUser().getNIF();
        return estafetaDB.getEstafetaByNIF(nif);
    }
    
    public Veiculo getVeiculo(int idVeiculo){
        return veiculoDB.getVeiculoById(idVeiculo);
    }
      
    public List<Encomenda> getListaEncomenda(){
        return encomendaDB.getListaEncomenda();
    }
    
    public Entrega addEntrega(String dataInicio, String dataFim, int idVeiculo, int idEstafeta) throws SQLException, ParseException{
        Entrega en = new Entrega(dataInicio, dataFim, idVeiculo, idEstafeta);
        en.setIdEntrega(entregaDB.addEntrega(en));
        return en;
    }
    
    public boolean addEncomendaEntrega(Entrega e, Encomenda enc) throws SQLException{        
        return (entregaDB.addEncomendaEntrega(e,enc)? (true) : (false));
    }
    
    public Endereco getEnderecoByNifCliente(int nif){
        return enderecoDB.getEnderecoByNifCliente(nif);
    }
    
    public Utilizador getUtilizadorByNif(int nif){
        return utilizadorDB.getByID(nif);
    }
    
    public Graph<Endereco,Double> generateGraph(List<Endereco> listEnderecos, Estafeta est, Veiculo veiculo, double pesoTotal){
        return entregaDB.generateGraph(listEnderecos, est, veiculo, pesoTotal);
    }
    
    public double getPath(Graph<Endereco, Double> graph, List<Endereco> listEnderecos, List<Endereco> finalShortPath, Endereco origem, double energia){
        return entregaDB.getPath(graph, listEnderecos, finalShortPath, origem, energia);
    }
    
    public boolean enviarNotaCliente(Farmacia farmacia, Utilizador c){
        return (emailDB.sendEmail(farmacia.getEmail(), c.getEmail(), "Entrega", "A sua entrega está a caminho")? (true) : (false));
    }
    
    public Cliente getClienteByEndereco(Endereco end){
        return clienteDB.getClienteByMorada(end.getMorada());
    }
    
    public String getDuracaoPercurso(List<Endereco> finalShortPath, Veiculo veiculo) throws ParseException{
        return entregaDB.getDuracaoPercurso(finalShortPath, veiculo);
    }
    
    public boolean updateEncomenda(Encomenda encomenda) throws SQLException, ParseException{
        return (encomendaDB.updateEncomenda(encomenda) ? (true) : (false));
    }
    
    public boolean updateEntrega(Entrega entrega) throws SQLException, ParseException{
        return (entregaDB.updateEntrega(entrega) ? (true) : (false));
    }

    public Encomenda getEncomenda(int id) {
        return encomendaDB.getEncomenda(id);
    }
    
}
