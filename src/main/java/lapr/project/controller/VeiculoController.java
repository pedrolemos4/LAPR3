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

    public VeiculoController(VeiculoDB veiculoDB) {
        this.veiculoDB = veiculoDB;
    }

    public Veiculo addVeiculo(String descricao,String  tipo,int capacidade, 
            double percentagemBateria, double pesoMaximo,double pesoVeiculo, 
            double potencia, double areaFrontal,int estado) throws SQLException {
        Veiculo veiculo = new Veiculo(descricao,tipo, capacidade, percentagemBateria, pesoMaximo,
                pesoVeiculo, potencia, areaFrontal, estado);

        veiculo.setId(veiculoDB.addVeiculo(veiculo));
        return veiculo;
    }

    public boolean updateVeiculo(Veiculo veiculo) throws SQLException {
        return (veiculoDB.updateVeiculo(veiculo) ? true : false) ;
    }

    public List<Veiculo> getListaVeiculo() {
        return veiculoDB.getListaVeiculo();
    }

    public Veiculo getVeiculoById(int id) {
        return veiculoDB.getVeiculoById(id);
    }

    public boolean removeVeiculo(int idVeiculo) throws SQLException {
        return (veiculoDB.removeVeiculo(idVeiculo) ? true : false);
    }
}
