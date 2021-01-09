/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Cartao;

/**
 *
 * @author josep
 */
public class CartaoDB extends DataHandler {

    Cartao cc;
    private final DataHandler dataHandler;
    private List<Cartao> lstCartoes;

    public CartaoDB() {
        this.dataHandler = DataHandler.getInstance();
    }

    public Cartao novoCartao(int numeroCartao, String dataDeValidade, int CCV) {
        cc = new Cartao(numeroCartao, dataDeValidade, CCV);
        return cc;
    }

    public void registaCartao(Cartao cc) {
        if (validaCartao(cc)) {
            addCartao(cc);
        }
    }

    private boolean validaCartao(Cartao cc) {
        return !(cc == null || cc.getNumeroCartao() <= 0 || cc.getCCV() <= 0 || !cc.getDataDeValidade().isEmpty());
    }

    public void addCartao(Cartao cc) {
        addCartao(cc.getNumeroCartao(), cc.getDataDeValidade(), cc.getCCV());
    }

    private void addCartao(int numeroCartao, String dataDeValidade, int CCV) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addCartao(?,?,?) }");
            callStmt.setInt(1, numeroCartao);
            Timestamp dValidade = Timestamp.valueOf(dataDeValidade);
            callStmt.setTimestamp(2, dValidade);
            callStmt.setInt(3, CCV);
            callStmt.execute();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cartao> getLstClientes() {
        ArrayList<Cartao> list = new ArrayList<>();
        String query = "SELECT * FROM cartao";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            while (rSet.next()) {
                int numeroCartao = rSet.getInt(1);
                String dataDeValidade = rSet.getTimestamp(2).toString();
                int CCV = rSet.getInt(3);
                list.add(new Cartao(numeroCartao, dataDeValidade, CCV));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
