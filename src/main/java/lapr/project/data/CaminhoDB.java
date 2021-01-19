package lapr.project.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Caminho;
import lapr.project.model.Endereco;

/**
 *
 * @author beatr
 */
public class CaminhoDB extends DataHandler{
    
    public List<Caminho> getAllCaminhos(){
        String query = "SELECT * FROM caminho";
        ArrayList<Caminho> list = new ArrayList<>();
        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {

                while (rSet.next()) {
                    String morada1 = rSet.getString(1);
                    String morada2 = rSet.getString(2);
                    Double roadResistanceCoefficient = rSet.getDouble(3);
                    Double velocidadeVento = rSet.getDouble(4);
                    Double direcaoVento = rSet.getDouble(5);
                    
                    Endereco end1 = new EnderecoDB().getEnderecoByMorada(morada1);
                    Endereco end2 = new EnderecoDB().getEnderecoByMorada(morada2);
                    
                    Caminho e = new Caminho(end1, end2, roadResistanceCoefficient, velocidadeVento, direcaoVento);
                    list.add(e);
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(CaminhoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }
    
    public Caminho getCaminhoByEnderecos(String morada1, String morada2){
        String query = "SELECT * FROM caminho WHERE morada1 = '" + morada1+"' AND morada2 = '" + morada2 + "'";

        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                if (rSet.next()) {
                    String morada3 = rSet.getString(1);
                    String morada4 = rSet.getString(2);
                    Double roadResistanceCoefficient = rSet.getDouble(3);
                    Double velocidadeVento = rSet.getDouble(4);
                    Double direcaoVento = rSet.getDouble(5);
                    
                    Endereco end1 = new EnderecoDB().getEnderecoByMorada(morada3);
                    Endereco end2 = new EnderecoDB().getEnderecoByMorada(morada4);
                    
                    Caminho e = new Caminho(end1, end2, roadResistanceCoefficient, velocidadeVento, direcaoVento);
                    return e;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(CaminhoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }
    
}
