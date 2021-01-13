package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Estacionamento;
import lapr.project.model.Veiculo;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Tiago
 */
public class VeiculoDB extends DataHandler {

    public VeiculoDB() {
    }

    public int addVeiculo(Veiculo veiculo) throws SQLException {
        int id = 0;

        try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call addVeiculo(?,?,?,?,?,?,?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, veiculo.getDescricao());
            callStmt.setString(3, veiculo.getTipo());
            callStmt.setDouble(4, veiculo.getPercentagemBateria());
            callStmt.setDouble(5, veiculo.getPesoMaximo());
            callStmt.setDouble(6, veiculo.getPesoVeiculo());
            callStmt.setDouble(7, veiculo.getPotencia());
            callStmt.setDouble(8, veiculo.getAreaFrontal());
            callStmt.setInt(9, veiculo.getEstadoVeiculo().getId());
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
     * @return
     */
    public List<Veiculo> getListaVeiculo() {
        ArrayList<Veiculo> list = new ArrayList<>();
        String query = "SELECT * FROM veiculo WHERE EstadoVeiculoid = 1 AND percentagemBateria = 100 AND tipo = 'Scooter'";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                while (rSet.next()) {
                    int id = rSet.getInt(1);
                    String descricao = rSet.getString(2);
                    String tipo = rSet.getString(3);
                    double percentagemBateria = rSet.getDouble(4);
                    double pesoMaximo = rSet.getDouble(5);
                    double pesoVeiculo = rSet.getDouble(6);
                    double potencia = rSet.getDouble(7);
                    double areaFrontal = rSet.getDouble(8);
                    int idEstado = rSet.getInt(9);

                    list.add(new Veiculo(descricao, tipo, percentagemBateria, pesoMaximo,
                            pesoVeiculo, potencia, areaFrontal, idEstado));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Devolve o veiculo de acordo com o seu id
     *
     * @param idVeiculo
     * @return
     */
    public Veiculo getVeiculoById(int idVeiculo) {
        String query = "SELECT * FROM veiculo WHERE idVeiculo = " + idVeiculo;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int id = rSet.getInt(1);
                    String descricao = rSet.getString(2);
                    String tipo = rSet.getString(3);
                    double percentagemBateria = rSet.getDouble(4);
                    double pesoMaximo = rSet.getDouble(5);
                    double pesoVeiculo = rSet.getDouble(6);
                    double potencia = rSet.getDouble(7);
                    double areaFrontal = rSet.getDouble(8);
                    int idEstado = rSet.getInt(9);

                    return new Veiculo(descricao, tipo, percentagemBateria, pesoMaximo,
                            pesoVeiculo, potencia, areaFrontal, idEstado);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    public boolean updateVeiculo(Veiculo veiculo) throws SQLException {
        boolean removed = false;

        try ( CallableStatement callSmt = getConnection().prepareCall("{ call updateVeiculo(?,?,?,?,?,?,?,?,?) }")) {

            callSmt.setInt(1, veiculo.getId());
            callSmt.setString(2, veiculo.getDescricao());
            callSmt.setString(3, veiculo.getTipo());
            callSmt.setDouble(3, veiculo.getPercentagemBateria());
            callSmt.setDouble(4, veiculo.getPesoMaximo());
            callSmt.setDouble(5, veiculo.getPesoVeiculo());
            callSmt.setDouble(6, veiculo.getPotencia());
            callSmt.setDouble(7, veiculo.getAreaFrontal());
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
