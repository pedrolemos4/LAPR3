package lapr.project.data;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;
import lapr.project.model.TransferenciaProduto;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class TransferenciaDB extends DataHandler{
    public boolean realizaPedido(Farmacia fOrig, Farmacia fDest, Produto produto, int quantidade) {
        addTransferencia(fOrig.getNIF(), fDest.getNIF(), produto.getId(), quantidade, 1);
        return true;
    }

    public void addTransferencia(int idRem, int idDes, int idProd, int qtd, int estado) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call addTransferencia(?,?,?,?,?) }")) {

                callStmt.setInt(1, idRem);
                callStmt.setInt(2, idDes);
                callStmt.setInt(3, idProd);
                callStmt.setInt(4, qtd);
                callStmt.setInt(5, estado);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
