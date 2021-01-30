package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private final CaminhoDB caminhoDB;
    
    /**
     * Constroi uma instancia de RegistarClienteController recebendo uma instancia de UtilizadorDB, FarmaciaDB, EstafetaDB, EntregaDB, EncomendaDB, VeiculoDB, EnderecoDB, EmailDB, ClienteDB e CaminhoDB
     * @param utilizadorDB instancia de UtilizadorDB
     * @param farmaciaDB instancia de FarmaciaDB
     * @param estafetaDB instancia de EstafetaDB
     * @param entregaDB instancia de EntregaDB
     * @param encomendaDB instancia de EncomendaDB
     * @param veiculoDB instancia de VeiculoDB
     * @param enderecoDB instancia de EnderecoDB
     * @param emailDB instancia de EmailDB
     * @param clienteDB instancia de ClienteDB
     * @param caminhoDB instancia de CaminhoDB
     */
    public RegistarEntregaController(UtilizadorDB utilizadorDB, FarmaciaDB farmaciaDB, EstafetaDB estafetaDB, EntregaDB entregaDB, EncomendaDB encomendaDB, VeiculoDB veiculoDB, EnderecoDB enderecoDB, EmailDB emailDB, ClienteDB clienteDB, CaminhoDB caminhoDB) {
        this.utilizadorDB = utilizadorDB;
        this.farmaciaDB = farmaciaDB;
        this.estafetaDB = estafetaDB;
        this.entregaDB = entregaDB;
        this.encomendaDB = encomendaDB;
        this.veiculoDB = veiculoDB;
        this.enderecoDB = enderecoDB;
        this.emailDB = emailDB;
        this.clienteDB = clienteDB;
        this.caminhoDB = caminhoDB;
    }
    
    /**
     * Devolve uma lista de farmacias
     * @return lista e farmacias
     */
    public List<Farmacia> getLstFarmacias() {
        return farmaciaDB.getLstFarmacias();
    }
    
    /**
     * Devolve uma farmacia recebendo por parametro o nif da farmacia
     * @param nifFarmacia nif da farmacia
     * @return farmacia
     */
    public Farmacia getFarmaciaByNif(int nifFarmacia) {
        return farmaciaDB.getFarmaciaByNIF(nifFarmacia);
    }
    
    /**
     * Devolve uma lista de veiculos cujo peso maximo seja menor ou igual ao peso maximo da entrega
     * @param pesoMaximoEntrega peso maximo da entrega
     * @param nifFarmacia nif da faramcia
     * @return lista de veiculos
     */
    public List<Veiculo> getListaVeiculoEntrega(double pesoMaximoEntrega, int nifFarmacia) {
        return veiculoDB.getListaVeiculoEntrega(pesoMaximoEntrega, nifFarmacia);
    }
    
    /**
     * Devolve o endereço de uma farmacia recebendo por parametro o nif da farmacia
     * @param nifFarmacia nif da farmacia
     * @return endereço da farmacia
     */
    public Endereco getEnderecoOrigem(int nifFarmacia) {
        return enderecoDB.getEnderecoByNifFarmacia(nifFarmacia);
    }
    
    /**
     * Devolve o estafeta que fez login
     * @return estafeta
     */
    public Estafeta getEstafeta() {
        int nif = UserSession.getInstance().getUser().getNIF();
        return estafetaDB.getEstafetaByNIF(nif);
    }
    
    /**
     * Devolve um veiculo recebendo por parametro o id do veiculo
     * @param idVeiculo id do veiculo
     * @return veiculo
     */
    public Veiculo getVeiculo(int idVeiculo) {
        return veiculoDB.getVeiculoById(idVeiculo);
    }
    
    /**
     * Devolve uma lista de encomendas relativas a uma farmacia
     * @param nifFarmacia nif da farmacia
     * @return lista de encomendas
     */
    public List<Encomenda> getListaEncomenda(int nifFarmacia) {
        return encomendaDB.getListaEncomenda(nifFarmacia);
    }
    
    /**
     * Devolve uma entrega recebendo por parametro a data de inicio, a data de fim, o id do veiculo, o id do estafeta.
     * @param dataInicio data de inicio
     * @param dataFim data de fim
     * @param idVeiculo id do veiculo
     * @param idEstafeta id do estafeta
     * @param pesoEntrega peso da entrega
     * @return entrega
     * @throws SQLException
     * @throws ParseException 
     */
    public Entrega addEntrega(String dataInicio, String dataFim, int idVeiculo, int idEstafeta, double pesoEntrega) throws SQLException, ParseException {
        Entrega en = new Entrega(dataInicio, dataFim, idVeiculo, idEstafeta, pesoEntrega);
        en.setIdEntrega(entregaDB.addEntrega(en));
        return en;
    }
    
    /**
     * Verifica se a encomenda relativa à entrega foi registada recebendo por parametro a entrega e a encomenda
     * @param e entrega
     * @param enc encomenda
     * @return true se a encomenda relativa à entrega foi registada
     * @throws SQLException 
     */
    public boolean addEncomendaEntrega(Entrega e, Encomenda enc) throws SQLException {
        return (entregaDB.addEncomendaEntrega(e, enc) ? (true) : (false));
    }
    
    /**
     * Devolve o endereço do cliente recebendo por parametro o nif do cliente
     * @param nif nif do cliente
     * @return endereço do cliente
     */
    public Endereco getEnderecoByNifCliente(int nif) {
        return enderecoDB.getEnderecoByNifCliente(nif);
    }
    
    /**
     * Devolve um utilizador recebendo por parametro o nif
     * @param nif nif
     * @return utilizador
     */
    public Utilizador getUtilizadorByNif(int nif) {
        return utilizadorDB.getByID(nif);
    }
    
    /**
     * Devolve um grafo da scooter recebendo por parametro uma lista de endereços, o estafeta, o veiculo e o pesoTotal
     * @param listEnderecos lista de endereços
     * @param listEnderecosEncomenda lista de endereços dos clientes que fizeram encomendas relativas à entrega
     * @param est estafeta associado à entrega
     * @param veiculo veiculo associado à entrega
     * @param pesoTotal peso total da entrega
     * @return 
     */
    public Graph<Endereco, Double> generateGraphScooter(List<Endereco> listEnderecos,List<Endereco> listEnderecosEncomenda, Estafeta est, Veiculo veiculo, double pesoTotal) {
        return entregaDB.generateGraphScooter(listEnderecos,listEnderecosEncomenda, est, veiculo, pesoTotal);
    }
    
     /**
     * Devolve um grafo da scooter recebendo por parametro uma lista de endereços, o estafeta, o veiculo e o pesoTotal
     * @param listEnderecos lista de endereços
     * @param listEnderecosEncomenda lista de endereços dos clientes que fizeram encomendas relativas à entrega
     * @param veiculo veiculo associado à entrega
     * @param atributo atributo do veiculo
     * @param pesoTotal peso total da entrega
     * @return 
     */
    public Graph<Endereco, Double> generateGraphDrone(List<Endereco> listEnderecos,List<Endereco> listEnderecosEncomenda, Veiculo veiculo, double atributo, double pesoTotal) {
        return entregaDB.generateGraphDrone(listEnderecos,listEnderecosEncomenda, veiculo, atributo, pesoTotal);
    }

    public Graph<Endereco, Double> generateGrafo(Graph<Endereco, Double> graphDistancia,List<Endereco> listEnderecos, String tipoCaminho) {
        return farmaciaDB.generateGrafo(graphDistancia, listEnderecos, tipoCaminho);
    }
    
    /**
     * Devolve a energia gasta no percurso
     * @param graphEnergia o grafo de energia
     * @param graphDistancia o grafo de energia
     * @param listEnderecos lista de endereços dos clientes que fizeram encomendas relativas à entrega
     * @param finalShortPath lista de endereços presentes no caminho com custo de energia mais baixo
     * @param origem endereço de origem
     * @param energia a energia inicial
     * @param v veiculo usado no percurso
     * @return energia gasta no percurso
     */
    public double getPath(Graph<Endereco, Double> graphEnergia,Graph<Endereco, Double> graphDistancia, List<Endereco> listEnderecos, LinkedList<Endereco> finalShortPath, Endereco origem, double energia, Veiculo v, double distanciaVeiculo) {
        return entregaDB.getPath(graphEnergia, graphDistancia, listEnderecos, finalShortPath, origem, energia, v, distanciaVeiculo);
    }
    
    /**
     * Verifica se a nota ao cliente foi enviada recebendo a farmacia e o utilizador
     * @param farmacia farmacia que vai enviar a nota
     * @param c utilizador que vai receber a nota
     * @return true se a nota ao cliente foi enviada
     */
    public boolean enviarNotaCliente(Farmacia farmacia, Utilizador c) {
        return (emailDB.sendEmail(farmacia.getEmail(), c.getEmail(), "Entrega", "A sua entrega está a caminho") ? (true) : (false));
    }
    
    /**
     * Devolve o cliente recebendo por parametro o endereço do cliente
     * @param end endereço do cliente
     * @return cliente
     */
    public Cliente getClienteByEndereco(Endereco end) {
        return clienteDB.getClienteByMorada(end.getMorada());
    }
    
    /**
     * Devolve a duraçao do percurso da entrega
     * @param finalShortPath lista de endereços presentes no caminho com custo de energia mais baixo
     * @param veiculo veiculo associado à entrega
     * @return duraçao do percurso da entrega
     * @throws ParseException 
     */
    public String getDuracaoPercurso(List<Endereco> finalShortPath, Veiculo veiculo) throws ParseException {
        return entregaDB.getDuracaoPercurso(finalShortPath, veiculo);
    }
    
    /**
     * Verifica se a encomenda foi atualizada recebendo por parametro o id de encomenda e o estado
     * @param idEncomenda id da encomenda
     * @param estado estado da encomenda
     * @return true se a encomenda foi atualizada
     * @throws SQLException 
     */
    public boolean updateEncomenda(int idEncomenda, int estado) throws SQLException {
        return (encomendaDB.updateEncomenda(idEncomenda, estado) ? (true) : (false));
    }

    
    /**
     * Devolve a encomenda recebendo por parametro o id da encomenda
     * @param id id da encomenda
     * @return encomenda
     */
    public Encomenda getEncomenda(int id) {
        return encomendaDB.getEncomenda(id);
    }
    
    /**
     * Devolve o drone recebendo por parametro o id do veiculo
     * @param idVeiculo id do veiculo
     * @return drone
     */
    public Drone getDroneById(int idVeiculo){
        return veiculoDB.getDroneById(idVeiculo);
    }
    
    /**
     * Devolve uma lista de endereços por onde o drone pode ir
     * @return lista de endereços por onde o drone pode ir
     */
    public List<Endereco> getLstEnderecosDrone(){
        return enderecoDB.getLstEnderecosDrone();
    }
    
    /**
     * Devolve uma lista de endereços por onde a scooter pode ir
     * @return lista de endereços por onde o drone pode ir
     */
    public List<Endereco> getLstEnderecosScooter(){
        return enderecoDB.getLstEnderecos();
    }
    
    /**
     * Devolve o caminho entre duas moradas
     * @param morada1
     * @param morada2
     * @return caminho entre duas moradas
     */
    public Caminho getCaminhoByEnderecos(String morada1, String morada2){
        return caminhoDB.getCaminhoByEnderecos(morada1, morada2);
    }
}
