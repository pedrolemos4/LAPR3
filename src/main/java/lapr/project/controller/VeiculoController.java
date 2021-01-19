package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.VeiculoDB;
import lapr.project.model.Veiculo;

/**
 *
 * @author Tiago
 */
public class VeiculoController {

    private final VeiculoDB veiculoDB;
    
    /**
     * Constroi uma instancia de VeiculoController recebendo uma instancia de VeiculoDB
     * @param veiculoDB instancia de VeiculoDB
     */
    public VeiculoController(VeiculoDB veiculoDB) {
        this.veiculoDB = veiculoDB;
    }
    
    /**
     * Devolve o veiculo recebendo por parametro a descriçao, tipo, capacidade, percentagem bateria, peso maximo, peso do veiculo, potencia, area frontal e estado.
     * @param descricao descriçao do veiculo
     * @param tipo tipo do veiculo
     * @param capacidade capacidade do veiculo
     * @param percentagemBateria percentagem da bateria do veiculo
     * @param pesoMaximo peso maximo que o veiculo pode levar
     * @param pesoVeiculo peso do veiculo
     * @param potencia potencia do veiculo
     * @param areaFrontal area frontal do veiculo
     * @param estado estado do veiculo
     * @return veiculo
     * @throws SQLException 
     */
    public Veiculo addVeiculo(String descricao,String  tipo,int capacidade, 
            double percentagemBateria, double pesoMaximo,double pesoVeiculo, 
            double potencia, double areaFrontal,int estado) throws SQLException {
        Veiculo veiculo = new Veiculo(descricao,tipo, capacidade, percentagemBateria, pesoMaximo,
                pesoVeiculo, potencia, areaFrontal, estado);

        veiculo.setId(veiculoDB.addVeiculo(veiculo));
        return veiculo;
    }
    
    /**
     * Verifica se o veiculo foi atualizado recebendo o veiculo
     * @param veiculo veiculo
     * @return true se o veiculo doi atualizado
     * @throws SQLException 
     */
    public boolean updateVeiculo(Veiculo veiculo) throws SQLException {
        return (veiculoDB.updateVeiculo(veiculo) ? (true) : (false)) ;
    }
    
    /**
     * Devolve uma lista de veiculos
     * @return lista de veiculos
     */
    public List<Veiculo> getListaVeiculo() {
        return veiculoDB.getListaVeiculo();
    }
    
    /**
     * Devolve o veiculo recebendo por parametro o id do veiculo
     * @param id id do veiculo
     * @return veiculo
     */
    public Veiculo getVeiculoById(int id) {
        return veiculoDB.getVeiculoById(id);
    }
    
    /**
     * Verifica se o veiculo foi removido recebendo por parametro o id do veiculo
     * @param idVeiculo id do veiculo
     * @return true se o veiculo foi removido
     * @throws SQLException 
     */
    public boolean removeVeiculo(int idVeiculo) throws SQLException {
        return (veiculoDB.removeVeiculo(idVeiculo) ? (true) : (false));
    }
}
