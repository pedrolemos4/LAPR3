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

/**
 *
 * @author Tiago
 */
public class VeiculoDB extends DataHandler {

    public VeiculoDB() {
        //dummy constructor
    }

    /**
     * Adiciona um veículo à base de dados
     * @param veiculo veículo a ser adicionado
     * @return id do veículo
     * @throws SQLException
     */
    public int addVeiculo(Veiculo veiculo) throws SQLException {
        int id = 0;

        try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call addVeiculo(?,?,?,?,?,?,?,?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, veiculo.getDescricao());
            callStmt.setString(3, veiculo.getTipo());
            callStmt.setInt(4, veiculo.getCapacidade());
            callStmt.setDouble(5, veiculo.getPercentagemBateria());
            callStmt.setDouble(6, veiculo.getPesoMaximo());
            callStmt.setDouble(7, veiculo.getPesoVeiculo());
            callStmt.setDouble(8, veiculo.getPotencia());
            callStmt.setDouble(9, veiculo.getAreaFrontal());
            callStmt.setInt(10, veiculo.getEstadoVeiculo().getId());
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
                    String tipo = rSet.getString(3);
                    int capacidade = rSet.getInt(4);
                    double percentagemBateria = rSet.getDouble(5);
                    double pesoMaximo = rSet.getDouble(6);
                    double pesoVeiculo = rSet.getDouble(7);
                    double potencia = rSet.getDouble(8);
                    double areaFrontal = rSet.getDouble(9);
                    int idEstado = rSet.getInt(10);
                    
                    Veiculo v = new Veiculo(descricao, tipo,capacidade, percentagemBateria,
                            pesoMaximo,pesoVeiculo, potencia, areaFrontal, idEstado);
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
                    String tipo = rSet.getString(3);
                    int capacidade = rSet.getInt(4);
                    double percentagemBateria = rSet.getDouble(5);
                    double pesoMaximo = rSet.getDouble(6);
                    double pesoVeiculo = rSet.getDouble(7);
                    double potencia = rSet.getDouble(8);
                    double areaFrontal = rSet.getDouble(9);
                    int idEstado = rSet.getInt(10);

                    Veiculo v = new Veiculo(descricao, tipo,capacidade, percentagemBateria,
                            pesoMaximo, pesoVeiculo, potencia, areaFrontal, idEstado);
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
     * @param veiculo veículo a ser alterado
     * @return true se o veículo foi alterado com sucesso, false se não
     * @throws SQLException
     */
    public boolean updateVeiculo(Veiculo veiculo) throws SQLException {
        boolean removed = false;

        try ( CallableStatement callSmt = getConnection().prepareCall("{ call updateVeiculo(?,?,?,?,?,?,?,?,?,?) }")) {

            callSmt.setInt(1, veiculo.getId());
            callSmt.setString(2, veiculo.getDescricao());
            callSmt.setString(3, veiculo.getTipo());
            callSmt.setInt(4, veiculo.getCapacidade());
            callSmt.setDouble(5, veiculo.getPercentagemBateria());
            callSmt.setDouble(6, veiculo.getPesoMaximo());
            callSmt.setDouble(7, veiculo.getPesoVeiculo());
            callSmt.setDouble(8, veiculo.getPotencia());
            callSmt.setDouble(9, veiculo.getAreaFrontal());
            callSmt.setInt(10, veiculo.getEstadoVeiculo().getId());
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
