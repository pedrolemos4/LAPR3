/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pedro
 */
public class ReciboDB extends DataHandler {
    /**
     * Regista o recibo na base de dados
     * @param rec recibo a registar
     * @return true se o recibo for registado com sucesso, false se não
     * @throws SQLException
     * @throws ParseException
     */
    public boolean registaRecibo(Recibo rec) throws SQLException, ParseException {
        if (validaRecibo(rec)) {
            int id = addRecibo(rec);
            rec.setId(id);
            return true;
        }
        return false;
    }

    /**
     * Valida o recibo recebido opr parâmetro
     * @param rec recibo a verificar
     * @return true se o recibo for válido, false se não
     */
    private boolean validaRecibo(Recibo rec) {
        return !(rec.getData() == null ||  rec.getNif() < 0 || rec.getIdEncomenda() < 0);
    }

    /**
     * Adiciona o recibo à base de dados
     * @param rec recibo a adicionar
     * @return id do recibo
     * @throws SQLException
     * @throws ParseException
     */
    public int addRecibo(Recibo rec) throws SQLException, ParseException {
        return addRecibo(rec.getData(), rec.getPreco(), rec.getNif(), rec.getIdEncomenda());
    }

    /**
     * Adiciona o recibo à base de dados
     * @param data data do recibo
     * @param preco preco da encomenda
     * @param nif nif do cliente
     * @param idEncomenda id da encomenda
     * @return id do recibo
     * @throws SQLException
     * @throws ParseException
     */
    private int addRecibo(String data, double preco, int nif, int idEncomenda) throws SQLException, ParseException {
        int id = 0;

        openConnection();

        try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call addRecibo(?,?,?,?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            java.util.Date date = sdf1.parse(data);
            java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());
            callStmt.setTimestamp(2, sqlStartDate);
            callStmt.setDouble(3, preco);
            callStmt.setInt(4, nif);
            callStmt.setInt(5, idEncomenda);
            System.out.println("idEncomenda "+idEncomenda);
            System.out.println("sqlStartDate "+sqlStartDate);
            System.out.println("preco "+preco);
            System.out.println("nif "+nif);
            System.out.println("Antes do execute addRecibo");
            
            callStmt.execute();
            id = callStmt.getInt(1);
            System.out.println("ID apos recibo: "+id);

        }
        try {
            closeAll();

        } catch (NullPointerException ex) {

            Logger.getLogger(ReciboDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

    /**
     * Regista o produto na linha do recibo
     * @param rec recibo onde se vai registar
     * @param prod produto a registar
     * @param quant quantidade do produto
     * @return true se o produto for registado com sucesso, false se não
     */
    public boolean registaRecibo(Recibo rec, Produto prod, int quant) {
        if (validaRecibo(rec)) {
            return registaRecibo(rec.getId(), prod.getId(), quant);
        }
        return false;
    }

    /**
     * Regista o produto na linha do recibo
     * @param rec recibo onde se vai registar
     * @param prod produto a registar
     * @param quant quantidade do produto
     * @return true se o produto for registado com sucesso, false se não
     */
    private boolean registaRecibo(int rec, int prod, int quant) {
        boolean bo = false;
        try {
            openConnection();

            try ( CallableStatement callStmt1 = getConnection().prepareCall("{ call addLinhaRecibo(?,?,?) }")) {

                callStmt1.setInt(1, rec);
                callStmt1.setInt(2, prod);
                callStmt1.setInt(3, quant);

                callStmt1.execute();
                bo=true;
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bo;
    }

}
