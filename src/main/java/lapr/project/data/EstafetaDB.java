package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lapr.project.model.EstadoEstafeta;
import lapr.project.model.Estafeta;
import oracle.jdbc.OracleTypes;

public class EstafetaDB extends DataHandler{
    Estafeta est;
    private final DataHandler dataHandler;
    private List<Estafeta> lstEstafetas;

    public EstafetaDB() {
        this.dataHandler = DataHandler.getInstance();
        lstEstafetas = new ArrayList<>();
    }

    public List<Estafeta> getLstEstafetas() {
        return lstEstafetas;
    }

    public void novoEstafeta(int nif, String nome, String email, double peso, int nss, String pwd) {
        est = new Estafeta(nif, nome, email, peso, nss, pwd, new EstadoEstafeta(1,"disponível"));
    }

    public void registaEstafeta(Estafeta est) {
        if (validaEstafeta(est)){
            addEstafeta(est);
        }
    }

    public boolean validaEstafeta(Estafeta est) {
        if(est.getPesoEstafeta() < 0 || est.getNome() == null || est.getPassword() == null || est.getEstado() == null){
            return false;
        }
        return true;
    }

    public void addEstafeta(Estafeta est) {
        lstEstafetas.add(est);
    }

    public Estafeta getEstafetaByEmail(String email) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getEstafetaByEmail(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getEstafetaByEmail".
            callStmt.setString(4, email);

            // Executa a invocação da função "getEstafetaByEmail".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                double pesoEstafeta = rSet.getDouble(1);
                int NIF = rSet.getInt(2);
                String nome = rSet.getString(3);
                String emailE = rSet.getString(4);
                int numeroSegurancaSocial = rSet.getInt(5);
                String password = rSet.getString(6);
                int id_estado_estafeta = rSet.getInt(7);
                String designacao = rSet.getString(8);

                return new Estafeta(NIF, nome, emailE, pesoEstafeta, numeroSegurancaSocial, password, new EstadoEstafeta(id_estado_estafeta, designacao));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Sailor with ID:" + email);
    }
}
