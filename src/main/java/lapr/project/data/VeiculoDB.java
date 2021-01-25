package lapr.project.data;

import lapr.project.model.Drone;
import lapr.project.model.Estacionamento;
import lapr.project.model.Scooter;
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

        try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call addVeiculo(?,?,?,?,?,?,?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, veiculo.getDescricao());
            callStmt.setDouble(3, veiculo.getCapacidade());
            callStmt.setDouble(4, veiculo.getPercentagemBateria());
            callStmt.setDouble(5, veiculo.getPesoMaximo());
            callStmt.setDouble(6, veiculo.getPesoVeiculo());
            callStmt.setDouble(7, veiculo.getPotencia());
            callStmt.setInt(8, veiculo.getEstadoVeiculo().getId());
            callStmt.setDouble(9, veiculo.getAreaFrontal());
            callStmt.execute();

            id = callStmt.getInt(1);

            try {

                closeAll();

            } catch (NullPointerException ex) {

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
        return !(drone.getLargura() < 0);
    }

    public void addDrone(Drone drone) throws SQLException {
        try ( CallableStatement callStmt = getConnection().prepareCall("{ call addDrone(?,?) }")) {
            callStmt.setInt(1, drone.getId());
            callStmt.setDouble(2, drone.getLargura());
            callStmt.execute();
            try {

                closeAll();

            } catch (NullPointerException ex) {

                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
    }

    public Scooter novaScooter(Veiculo ve, int id) {
        return new Scooter(ve, id);
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
        try ( CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?) }")) {
            callStmt.setInt(1, scooter.getId());
//            callStmt.setDouble(2, scooter.getAreaFrontal());
            callStmt.execute();
            try {
                closeAll();
            } catch (NullPointerException ex) {
                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
    }

    /**
     * Devolve a lista de veiculos cujo peso maximo é inferior ou igual ao peso maximo da entrega
     * @param pesoMaximoEntrega peso maximo da entrega
     * @param nifFarmacia nif da farmacia
     * @return lista de veículos
     */
    public List<Veiculo> getListaVeiculoEntrega(double pesoMaximoEntrega, int nifFarmacia) {
        ArrayList<Veiculo> list = new ArrayList<>();
        String query = "SELECT v.idveiculo, v.descricao, v.capacidade, v.percentagemBateria, v.pesoMaximo, v.pesoVeiculo, v.potencia, v.estadoveiculoid, v.areaFrontal "
                + " FROM veiculo v INNER JOIN estacionamentoveiculo e ON e.veiculoidveiculo = v.idveiculo"
                + " INNER JOIN parque p ON p.idparque = e.estacionamentoidparque WHERE"
                + " v.EstadoVeiculoid = 1 AND p.farmacianif = " + nifFarmacia + 
                " AND v.percentagemBateria = 100 AND e.datafim is null AND v.pesoMaximo >= " + pesoMaximoEntrega;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                while (rSet.next()) {
                    int id = rSet.getInt(1);
                    String descricao = rSet.getString(2);
                    double capacidade = rSet.getDouble(3);
                    double percentagemBateria = rSet.getDouble(4);
                    double pesoMaximo = rSet.getDouble(5);
                    double pesoVeiculo = rSet.getDouble(6);
                    double potencia = rSet.getDouble(7);
                    int idEstado = rSet.getInt(8);
                    double areaFrontal = rSet.getDouble(9);

                    Veiculo v = new Veiculo(descricao, capacidade, percentagemBateria,
                            pesoMaximo, pesoVeiculo, potencia, idEstado, areaFrontal);
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
     * Devolve a lista de veiculos
     * @return lista de veículos
     */
    public List<Veiculo> getListaVeiculo() {
        ArrayList<Veiculo> list = new ArrayList<>();
        String query = "SELECT * FROM veiculo";

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
                    double areaFrontal = rSet.getDouble(9);

                    Veiculo v = new Veiculo(descricao, capacidade, percentagemBateria,
                            pesoMaximo, pesoVeiculo, potencia, idEstado, areaFrontal);
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
                    double areaFrontal = rSet.getDouble(9);

                    Veiculo v = new Veiculo(descricao, capacidade, percentagemBateria,
                            pesoMaximo, pesoVeiculo, potencia, idEstado, areaFrontal);
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
     * Devolve o scooter de acordo com o id recebido por parâmetro
     *
     * @param idVeiculo id do veículo
     * @return veículo
     */
    public Scooter getScooterById(int idVeiculo) {
        String query = "SELECT * FROM scooter s INNER JOIN veiculo v ON v.idveiculo = s.id WHERE v.idVeiculo = " + idVeiculo;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int id = rSet.getInt(1);                                 
                    Veiculo v = getVeiculoById(idVeiculo);
                    Scooter s = new Scooter(v, id);
                    s.setId(id);
                    return s;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }
    
    
    /**
     * Devolve o drone de acordo com o id recebido por parâmetro
     *
     * @param idVeiculo id do veículo
     * @return veículo
     */
    public Drone getDroneById(int idVeiculo) {
        String query = "SELECT * FROM drone d INNER JOIN veiculo v ON v.idveiculo = d.idDrone WHERE v.idVeiculo = " + idVeiculo;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int id = rSet.getInt(1);
                    double powerPro = rSet.getDouble(2);
                                 
                    Veiculo v = getVeiculoById(idVeiculo);
                    Drone d = new Drone(v, id, powerPro);
                    d.setId(id);
                    return d;
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
            callSmt.setDouble(3, veiculo.getCapacidade());
            callSmt.setDouble(4, veiculo.getPercentagemBateria());
            callSmt.setDouble(5, veiculo.getPesoMaximo());
            callSmt.setDouble(6, veiculo.getPesoVeiculo());
            callSmt.setDouble(7, veiculo.getPotencia());
            callSmt.setInt(8, veiculo.getEstadoVeiculo().getId());
            callSmt.setDouble(9, veiculo.getAreaFrontal());
            callSmt.execute();

            removed = true;
            try {

                closeAll();

            } catch (NullPointerException ex) {
                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }

        return removed;
    }
    
    /**
     * Atualiza a informação de um drone na base de dados
     *
     * @param id id do drone
     * @param powerPro power pro do drone
     * @return true se o drone foi alterado com sucesso, false se não
     * @throws SQLException
     */
    public boolean updateDrone(int id, double powerPro) throws SQLException {
        boolean removed = false;
        try ( CallableStatement callSmt = getConnection().prepareCall("{ call updateDrone(?,?) }")) {
            callSmt.setInt(1, id);
            callSmt.setDouble(2, powerPro);
            callSmt.execute();
            removed = true;
            try {
                closeAll();
            } catch (NullPointerException ex) {
                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
        return removed;
    }
    
//    /**
//     * Atualiza a informação de uma scooter na base de dados
//     *
//     * @param id id da scooter
//     * @param areaFrontal areaFrontal da scooter
//     * @return true se a scooter foi alterado com sucesso, false se não
//     * @throws SQLException
//     */
//    public boolean updateScooter(int id, double areaFrontal) throws SQLException {
//        boolean removed = false;
//        try ( CallableStatement callSmt = getConnection().prepareCall("{ call updateScooter(?,?) }")) {
//            callSmt.setInt(1, id);
//            callSmt.setDouble(2, areaFrontal);
//            callSmt.execute();
//            removed = true;
//            try {
//                closeAll();
//            } catch (NullPointerException ex) {
//                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
//            }
//        }
//        return removed;
//    }

    /**
     * Remove o veículo da base de dados
     *
     * @param id id do veículo a remover
     * @return true se o veículo foi removido com sucesso, false se não
     * @throws SQLException
     */
    public boolean removeVeiculo(int id) throws SQLException {
        boolean removed = false;
        try ( CallableStatement callV = getConnection().prepareCall("{ call procRemoveveiculo(?) }")) {
            callV.setInt(1, id);
            callV.execute();
            removed = true;
            try {
                closeAll();
            } catch (NullPointerException ex) {
                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
        return removed;
    }
    
    /**
     * Remove o drone da base de dados
     *
     * @param id id do veículo a remover
     * @return true se o veículo foi removido com sucesso, false se não
     * @throws SQLException
     */
    public boolean removeDrone (int id) throws SQLException {
        boolean removed = false;
        try ( CallableStatement callV = getConnection().prepareCall("{ call procRemovedrone(?) }")) {
            callV.setInt(1, id);
            callV.execute();
            removed = true;
            try {
                closeAll();
            } catch (NullPointerException ex) {
                Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }
        return removed;
    }
    
    /**
     * Remove o scooter da base de dados
     *
     * @param id id do veículo a remover
     * @return true se o veículo foi removido com sucesso, false se não
     * @throws SQLException
     */
        public boolean removeScooter (int id) throws SQLException {
        boolean removed = false;
        try ( CallableStatement callV = getConnection().prepareCall("{ call procRemovescooter(?) }")) {
            callV.setInt(1, id);
            callV.execute();
            removed = true;
            try {
                closeAll();
            } catch (NullPointerException ex) {
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
