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
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Cartao;

/**
 *
 * @author josep
 */
public class CartaoDB extends DataHandler {

    public CartaoDB() {
    }

    /**
     * Cria um novo cartão de cidadão
     *
     * @param numeroCartao número do cartão de cidadão
     * @param dataDeValidade data de validade do cartão de cidadão
     * @param CCV código de segurança do cartão de cidadão
     * @return o novo cartão de cidadão criado
     */
    public Cartao novoCartao(int numeroCartao, String dataDeValidade, int CCV) {
        return new Cartao(numeroCartao, dataDeValidade, CCV);
    }

    /**
     * Regista cartão de cidadão
     *
     * @param cc cartão de cidadão
     * @return
     */
    public boolean registaCartao(Cartao cc) {
        if (validaCartao(cc)) {
            addCartao(cc);
        }
        return true;
    }

    /**
     * Valida cartão de cidadão
     *
     * @param cc cartão de cidadão
     * @return true se os dados do cartão de cidadão são válidos
     */
    public boolean validaCartao(Cartao cc) {
        return !(cc == null || cc.getNumeroCartao() < 0 || cc.getCCV() < 0 || !cc.getDataDeValidade().isEmpty());
    }

    /**
     * Adiciona cartão de cidadão à base de dados
     *
     * @param cc cartão de cidadão
     * @return
     */
    public boolean addCartao(Cartao cc) {
        addCartao(cc.getNumeroCartao(), cc.getDataDeValidade(), cc.getCCV());
        return true;
    }

    /**
     * Adiciona cartão de cidadão à base de dados
     *
     * @param numeroCartao número do cartão de cidadão
     * @param dataDeValidade data de validade do cartão de cidadão
     * @param CCV código de segurança do cartão de cidadão
     */
    public void addCartao(int numeroCartao, String dataDeValidade, int CCV) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addCartao(?,?,?) }")) {
                callStmt.setInt(1, numeroCartao);
                Timestamp dValidade = Timestamp.valueOf(dataDeValidade);
                callStmt.setTimestamp(2, dValidade);
                callStmt.setInt(3, CCV);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os cartões de cidadão
     *
     * @return lista de cartões de cidadão registados
     */
    public List<Cartao> getLstCC() {
        ArrayList<Cartao> list = new ArrayList<>();
        String query = "SELECT * FROM cartao";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int numeroCartao = rSet.getInt(1);
                    String dataDeValidade = rSet.getTimestamp(2).toString();
                    int CCV = rSet.getInt(3);
                    list.add(new Cartao(numeroCartao, dataDeValidade, CCV));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(CartaoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }
}
