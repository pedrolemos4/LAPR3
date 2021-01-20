package lapr.project.data;

import lapr.project.model.Estacionamento;
import lapr.project.model.Veiculo;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Drone;
import lapr.project.model.Scooter;

/**
 *
 * @author Tiago
 */
public class VeiculoDB extends DataHandler {

    public VeiculoDB() {
        //dummy constructor
    }

    public boolean registaVeiculo(Veiculo ve) throws SQLException {
        if (validaVeiculo(ve)) {
            addVeiculo(ve);
            return true;
        }
        return false;
    }

    private boolean validaVeiculo(Veiculo ve) {
        return !(ve.getCapacidade() < 0 || ve.getDescricao() == null || ve.getPercentagemBateria() < 0
                || ve.getPesoMaximo() < 0 || ve.getPesoVeiculo() < 0 || ve.getPotencia() < 0);
    }

    /**
     * Adiciona um veículo à base de dados
     *
     * @param veiculo veículo a ser adicionado
     * @return id do veículo
     * @throws SQLException
     */
    public int addVeiculo(Veiculo veiculo) throws SQLException {
        int id = 0;

        try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call addVeiculo(?,?,?,?,?,?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, veiculo.getDescricao());
            callStmt.setInt(3, veiculo.getCapacidade());
            callStmt.setDouble(4, veiculo.getPercentagemBateria());
            callStmt.setDouble(5, veiculo.getPesoMaximo());
            callStmt.setDouble(6, veiculo.getPesoVeiculo());
            callStmt.setDouble(7, veiculo.getPotencia());
            callStmt.setInt(8, veiculo.getEstadoVeiculo().getId());
            callStmt.execute();

            id = callStmt.getInt(1);

            try {

                callStmt.close();

            } catch (SQLException | NullPointerException ex) {

                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
        return id;
    }

    public Drone novoDrone(Veiculo ve, int id, double powerPro) {
        return new Drone(ve, id, powerPro);
    }

    public boolean registaDrone(Drone drone) throws SQLException {
        if (validaDrone(drone)) {
            addDrone(drone);
            return true;
        }
        return false;
    }

    private boolean validaDrone(Drone drone) {
        return !(drone.getPowerPro() < 0);
    }

    public void addDrone(Drone drone) throws SQLException {
        try ( CallableStatement callStmt = getConnection().prepareCall("{ call addDrone(?,?) }")) {
            callStmt.setInt(1, drone.getId());
            callStmt.setDouble(2, drone.getPowerPro());
            callStmt.execute();
            try {

                callStmt.close();

            } catch (SQLException | NullPointerException ex) {

                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
    }

    public Scooter novaScooter(Veiculo ve, int id, double areaFrontal) {
        return new Scooter(ve, id, areaFrontal);
    }

    public boolean registaScooter(Scooter scooter) throws SQLException{
        if(validaScooter(scooter)){
            addScooter(scooter);
            return true;
        }
        return false;
    }
    
    private boolean validaScooter(Scooter scooter){
        return !(scooter.getAreaFrontal()<0);
    }
    
    public void addScooter(Scooter scooter) throws SQLException {
        try ( CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?) }")) {
            callStmt.setInt(1, scooter.getId());
            callStmt.setDouble(2, scooter.getAreaFrontal());
            callStmt.execute();
            try {

                callStmt.close();

            } catch (SQLException | NullPointerException ex) {

                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
    }

    /**
     * Devolve a lista de veiculos
     *
     * @return lista de veículos
     */
    public List<Veiculo> getListaVeiculo() {
        ArrayList<Veiculo> list = new ArrayList<>();
        String query = "SELECT * FROM veiculo WHERE EstadoVeiculoid = 1 AND percentagemBateria = 100 AND tipo = 'scooter'";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                while (rSet.next()) {
                    int id = rSet.getInt(1);
                    String descricao = rSet.getString(2);
                    int capacidade = rSet.getInt(3);
                    double percentagemBateria = rSet.getDouble(4);
                    double pesoMaximo = rSet.getDouble(5);
                    double pesoVeiculo = rSet.getDouble(6);
                    double potencia = rSet.getDouble(7);
                    int idEstado = rSet.getInt(8);

                    Veiculo v = new Veiculo(descricao, capacidade, percentagemBateria,
                            pesoMaximo, pesoVeiculo, potencia, idEstado);
                    v.setId(id);
                    list.add(v);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Devolve o veiculo de acordo com o id recebido por parâmetro
     *
     * @param idVeiculo id do veículo
     * @return veículo
     */
    public Veiculo getVeiculoById(int idVeiculo) {
        String query = "SELECT * FROM veiculo WHERE idVeiculo = " + idVeiculo;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int id = rSet.getInt(1);
                    String descricao = rSet.getString(2);
                    int capacidade = rSet.getInt(3);
                    double percentagemBateria = rSet.getDouble(4);
                    double pesoMaximo = rSet.getDouble(5);
                    double pesoVeiculo = rSet.getDouble(6);
                    double potencia = rSet.getDouble(7);
                    int idEstado = rSet.getInt(8);

                    Veiculo v = new Veiculo(descricao, capacidade, percentagemBateria,
                            pesoMaximo, pesoVeiculo, potencia, idEstado);
                    v.setId(id);
                    return v;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    /**
     * Atualiza a informação de um veículo na base de dados
     *
     * @param veiculo veículo a ser alterado
     * @return true se o veículo foi alterado com sucesso, false se não
     * @throws SQLException
     */
    public boolean updateVeiculo(Veiculo veiculo) throws SQLException {
        boolean removed = false;

        try ( CallableStatement callSmt = getConnection().prepareCall("{ call updateVeiculo(?,?,?,?,?,?,?,?,?) }")) {

            callSmt.setInt(1, veiculo.getId());
            callSmt.setString(2, veiculo.getDescricao());
            callSmt.setInt(3, veiculo.getCapacidade());
            callSmt.setDouble(4, veiculo.getPercentagemBateria());
            callSmt.setDouble(5, veiculo.getPesoMaximo());
            callSmt.setDouble(6, veiculo.getPesoVeiculo());
            callSmt.setDouble(7, veiculo.getPotencia());
            callSmt.setInt(8, veiculo.getEstadoVeiculo().getId());
            callSmt.execute();

            removed = true;
            try {

                callSmt.close();

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }

        return removed;
    }

    /**
     * Remove o veículo da base de dados
     *
     * @param id id do veículo a remover
     * @return true se o veículo foi removido com sucesso, false se não
     * @throws SQLException
     */
    public boolean removeVeiculo(int id) throws SQLException {
        boolean removed = false;

        try ( CallableStatement callV = getConnection().prepareCall("{ call removeVehicle(?) }")) {

            callV.setInt(1, id);

            callV.execute();

            removed = true;

            try {

                callV.close();

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());

            }
        }
        return removed;
    }

    public void addEstacionamentoVeiculo(Estacionamento estac, Veiculo scoot) {
        addEstacionamentoVeiculo(estac.getNumeroLote(), scoot.getId());
    }

    /**
     * Adiciona um veículo ao estacionamento
     *
     * @param numLote id do estacionamento onde irá ficar o veículo
     * @param idVeiculo veículo a estacionar
     */
    private void addEstacionamentoVeiculo(int numLote, int idVeiculo) {

        try {
            openConnection();

            try ( CallableStatement callStmt = getConnection().prepareCall("{ call AddEstacionamentoVeiculo(?,?) }")) {
                callStmt.setInt(1, numLote);
                callStmt.setInt(2, idVeiculo);

                callStmt.execute();
            }

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
