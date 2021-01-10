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
import lapr.project.model.Encomenda;
import lapr.project.model.Produto;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pedro
 */
public class EncomendaDB extends DataHandler {

    private Encomenda enc;
    private final ProdutosDB produtoDB;
    private final DataHandler dataHandler;
    private List<Encomenda> lstEnc;

    public EncomendaDB() {
        this.dataHandler = DataHandler.getInstance();
        produtoDB = new ProdutosDB();
        lstEnc = new ArrayList<>();
    }

    /**
     * Devolve a lista de produtos da encomenda
     *
     * @return
     */
    public List<Produto> getListaProdutos() {
        return produtoDB.getListaProdutos();
    }

    /**
     * Devolve a encomenda com base no nif do cliente
     *
     * @param nif
     * @return
     */
    public Encomenda getEncomenda(int nif) {
        for (int i = 0; i < getListaEncomenda().size(); i++) {
            Encomenda get = getListaEncomenda().get(i);
            if (get.getNif() == nif) {
                return get;
            }
        }
        return null;
    }

    /**
     * Valida a encomenda
     *
     * @param enc
     * @return
     */
    public boolean validaEncomenda(Encomenda enc) {
        if (enc.getNif() != 0 && enc.getDataPedida() != null && enc.getEstado() != 0 && enc.getId() != 0 && enc.getLst() != null && enc.getPesoEncomenda() > 0 && enc.getPreco() > 0 && enc.getTaxa() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Regista e encomenda se for válida
     *
     * @param enc
     * @return
     */
    public boolean registaEncomenda(Encomenda enc) throws SQLException {
        if (validaEncomenda(enc)) {
            addEncomenda(enc);
        }
        return false;
    }

    /**
     * Adiciona a encomenda à base de dados
     *
     * @param enc
     */
    public void addEncomenda(Encomenda enc) throws SQLException {
        addEncomenda(enc.getNif(), enc.getDataPedida(), enc.getPreco(), enc.getPesoEncomenda(), enc.getTaxa(), enc.getEstado(), enc.getLst());
    }

    /**
     * Adiciona a encomenda à base de dados
     *
     * @param id
     * @param cliente
     * @param dataPedida
     * @param preco
     * @param pesoEncomenda
     * @param taxa
     * @param estado
     * @param lst
     */
    private int addEncomenda(int nif, String dataPedida, double preco, double pesoEncomenda, double taxa, int estado, List<Produto> lst) throws SQLException {

        CallableStatement callStmt = null;
        int id = 0;
        openConnection();

        callStmt = getConnection().prepareCall("{ call addEncomenda(?,?,?,?,?,?) }");

        callStmt.registerOutParameter(1, OracleTypes.INTEGER);
        callStmt.setInt(2, nif);
        callStmt.setString(3, dataPedida);
        callStmt.setDouble(4, preco);
        callStmt.setDouble(5, pesoEncomenda);
        callStmt.setDouble(6, taxa);
        callStmt.setInt(7, estado);

        callStmt.execute();
        id = callStmt.getInt(1);

        try {

            closeAll();

        } catch (NullPointerException ex) {

            Logger.getLogger(ScooterDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

    /**
     * Guarda na base de dados a lista de produtos por encomenda
     *
     * @param enc
     * @param p
     */
    public void registaEncomendaProduto(Encomenda enc, Produto p) {
        try {
            openConnection();

            CallableStatement callStmt1 = getConnection().prepareCall("{ call addEncomendaProduto(?,?) }");

            callStmt1.setInt(1, enc.getId());
            callStmt1.setInt(2, p.getId());

            callStmt1.execute();
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devolve a lista de encomendas
     *
     * @return
     */
    public List<Encomenda> getListaEncomenda() {
        ArrayList<Encomenda> list = new ArrayList<>();
        String query = "SELECT * FROM encomenda WHERE EstadoEncomendaidEstadoEncomenda = 1";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            while (rSet.next()) {
                int idEncomenda = rSet.getInt(1);
                Timestamp dataPedida = rSet.getTimestamp(2);
                double preco = rSet.getDouble(3);
                double pesoEncomenda = rSet.getDouble(4);
                double taxa = rSet.getDouble(5);
                int estado = rSet.getInt(6);
                int nif = rSet.getInt(7);

                list.add(new Encomenda(nif, dataPedida.toString(), preco, pesoEncomenda, taxa, estado));
            }
            return list;

        } catch (SQLException e) {
            Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return list;
    }

    /**
     * Devolve a lista de encomendas
     *
     * @param idEntrega
     * @return
     */
    public List<Encomenda> getListaEncomendaById(int idEntrega) {
        ArrayList<Encomenda> list = new ArrayList<>();
        String query = "SELECT * FROM encomenda e INNER JOIN EncomendaEntrega ee ON ee.EntregaidEntrega = e.idEntrega WHERE e.idEntrega = " + idEntrega;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            while (rSet.next()) {
                int idEncomenda = rSet.getInt(1);
                Timestamp dataPedida = rSet.getTimestamp(2);
                double preco = rSet.getDouble(3);
                double pesoEncomenda = rSet.getDouble(4);
                double taxa = rSet.getDouble(5);
                int estado = rSet.getInt(6);
                int nif = rSet.getInt(7);

                list.add(new Encomenda(nif, dataPedida.toString(), preco, pesoEncomenda, taxa, estado));
            }
            return list;

        } catch (SQLException e) {
            Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return list;
    }

}
