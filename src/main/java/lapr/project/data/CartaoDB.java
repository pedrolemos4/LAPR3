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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public CartaoDB(){
        //dummy constructor
    }
    
    /**
     * Cria um novo cartão de crédito
     *
     * @param numeroCartao número do cartão de crédito
     * @param dataDeValidade data de validade do cartão de crédito
     * @param ccv código de segurança do cartão de crédito
     * @return o novo cartão de crédito criado
     */
    public Cartao novoCartao(long numeroCartao, String dataDeValidade, int ccv) {
        return new Cartao(numeroCartao, dataDeValidade, ccv);
    }

    /**
     * Regista cartão de crédito
     *
     * @param cc cartão de crédito
     * @return
     */
    public boolean registaCartao(Cartao cc) throws ParseException {
        if (validaCartao(cc)) {
            addCartao(cc);
        }
        return true;
    }

    /**
     * Valida cartão de crédito
     *
     * @param cc cartão de crédito
     * @return true se os dados do cartão de crédito são válidos
     */
    public boolean validaCartao(Cartao cc) {
        return !(cc == null || cc.getNumeroCartao() < 0 || cc.getCCV() < 0 || cc.getDataDeValidade().isEmpty());
    }

    /**
     * Adiciona cartão de crédito à base de dados
     *
     * @param cc cartão de crédito
     * @return
     */
    public boolean addCartao(Cartao cc) throws ParseException {
        addCartao(cc.getNumeroCartao(), cc.getDataDeValidade(), cc.getCCV());
        return true;
    }

    /**
     * Adiciona cartão de crédito à base de dados
     *
     * @param numeroCartao número do cartão de crédito
     * @param dataDeValidade data de validade do cartão de crédito
     * @param ccv código de segurança do cartão de crédito
     * @throws java.text.ParseException
     */
    public void addCartao(long numeroCartao, String dataDeValidade, int ccv) throws ParseException {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addCartao(?,?,?) }")) {
                callStmt.setLong(1, numeroCartao);
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date = sdf1.parse(dataDeValidade);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                callStmt.setDate(2, sqlStartDate);
                callStmt.setInt(3, ccv);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os cartões de crédito
     *
     * @return lista de cartões de crédito registados
     */
    public List<Cartao> getLstCC() {
        ArrayList<Cartao> list = new ArrayList<>();
        String query = "SELECT * FROM cartao";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    long numeroCartao = rSet.getLong(1);
                    String dataDeValidade = rSet.getTimestamp(2).toString();
                    int ccv = rSet.getInt(3);
                    list.add(new Cartao(numeroCartao, dataDeValidade, ccv));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(CartaoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }
}
