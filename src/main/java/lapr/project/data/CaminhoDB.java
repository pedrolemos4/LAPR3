package lapr.project.data;

import lapr.project.model.Caminho;
import lapr.project.model.Endereco;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author beatr
 */
public class CaminhoDB extends DataHandler {

    public final EnderecoDB end = new EnderecoDB();

    public void addCaminho(String morada1, String morada2, double roadResistanceCoefficient, double velocidadeVento,
            double direcaoVento, String tipo) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addCaminho(?,?,?,?,?,?) }")) {
            callStmt.setString(1, morada1);
            callStmt.setString(2, morada2);
            callStmt.setDouble(3, roadResistanceCoefficient);
            callStmt.setDouble(4, velocidadeVento);
            callStmt.setDouble(5, direcaoVento);
            callStmt.setString(6, tipo);
            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public Caminho getCaminhoByEnderecos(String morada1, String morada2) {
        String query = "SELECT * FROM caminho WHERE morada1 = '" + morada1 + "' AND morada2 = '" + morada2 + "'";

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                if (rSet.next()) {
                    String morada3 = rSet.getString(1);
                    String morada4 = rSet.getString(2);
                    Double roadResistanceCoefficient = rSet.getDouble(3);
                    Double velocidadeVento = rSet.getDouble(4);
                    Double direcaoVento = rSet.getDouble(5);

                    Endereco end1 = end.getEnderecoByMorada(morada3);
                    Endereco end2 = end.getEnderecoByMorada(morada4);

                    return new Caminho(end1, end2, roadResistanceCoefficient, velocidadeVento, direcaoVento);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(CaminhoDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            closeAll();
        }
        return null;
    }

}
