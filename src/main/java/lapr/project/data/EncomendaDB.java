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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Produto;
import lapr.project.utils.Data;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pedro
 */
public class EncomendaDB extends DataHandler {

    private final ProdutosDB produtoDB;
    private final ClienteDB cliDB;

    public EncomendaDB() {
        produtoDB = new ProdutosDB();
        cliDB = new ClienteDB();
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
        return enc.getNif() != 0 && enc.getDataPedida() != null
                && (enc.getEstado().getEstado() < 3 || enc.getEstado().getEstado() > 0)
                && enc.getPesoEncomenda() > 0 && enc.getPreco() > 0 && enc.getTaxa() > 0;
    }

    /**
     * Regista e encomenda se for válida
     *
     * @param enc
     * @return
     */
    public int registaEncomenda(Encomenda enc) throws SQLException, ParseException {
        System.out.println("Nif: " + enc.getNif());
        System.out.println("Data Pedida: " + enc.getDataPedida());
        System.out.println("Estado: " + enc.getEstado().getEstado());
        System.out.println("Id: " + enc.getId());
        System.out.println("Lista: " + enc.getLst());
        System.out.println("Peso: " + enc.getPesoEncomenda());
        System.out.println("Preco: " + enc.getPreco());
        System.out.println("Taxa: " + enc.getTaxa());
        if (validaEncomenda(enc)) {
            System.out.println("Linha 71 Encomenda DB");
            enc.setId(addEncomenda(enc));
            return enc.getId();
        }
        return 0;
    }

    /**
     * Adiciona a encomenda à base de dados
     *
     * @param enc
     * @return
     * @throws java.sql.SQLException
     */
    public int addEncomenda(Encomenda enc) throws SQLException, ParseException {
        return addEncomenda(enc.getNif(), enc.getDataPedida(), enc.getPreco(), enc.getPesoEncomenda(), enc.getTaxa(), enc.getEstado().getEstado());
    }

    /**
     * Adiciona a encomenda à base de dados
     *
     * @param dataPedida
     * @param preco
     * @param pesoEncomenda
     * @param taxa
     * @param estado
     * @param lst
     */
    private int addEncomenda(int nif, String dataPedida, double preco, double pesoEncomenda, double taxa, int estado) throws SQLException, ParseException {
        int id = 0;
        openConnection();

        try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call addEncomenda(?,?,?,?,?,?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            java.util.Date date = sdf1.parse(dataPedida);
            java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());
            callStmt.setTimestamp(2, sqlStartDate);

            callStmt.setDouble(3, preco);
            callStmt.setDouble(4, pesoEncomenda);
            callStmt.setDouble(5, taxa);
            callStmt.setInt(6, estado);
            callStmt.setInt(7, nif);
            callStmt.execute();
            id = callStmt.getInt(1);
        }
        try {

            closeAll();

        } catch (NullPointerException ex) {

            Logger.getLogger(VeiculoDB.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return id;
    }

    /**
     * Guarda na base de dados a lista de produtos por encomenda
     *
     * @param enc
     * @param p
     * @return
     */
    public boolean registaEncomendaProduto(Encomenda enc, Produto p,int stock) {
        if (validaEncomenda(enc)) {
            return registaEncomendaProduto(enc.getId(), p.getId(),stock);
        }
        return false;
    }

    private boolean registaEncomendaProduto(int enc, int p,int stock) {
        boolean aux = false;
        try {
            openConnection();

            try ( CallableStatement callStmt1 = getConnection().prepareCall("{ call addEncomendaProduto(?,?,?) }")) {

               
                callStmt1.setInt(1, enc);
                callStmt1.setInt(2, p);
                callStmt1.setInt(3, stock);

                callStmt1.execute();
                aux=true;
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aux;
    }

    /**
     * Devolve a lista de encomendas
     *
     * @return
     */
    public List<Encomenda> getListaEncomenda() {
        String query = "SELECT * FROM encomenda WHERE EstadoEncomendaidEstadoEncomenda = 1";

        return getFromDatabase(query);
    }

    /**
     * Devolve a lista de encomendas
     *
     * @param idEntrega
     * @return
     */
    public List<Encomenda> getListaEncomendaById(int idEntrega) {
        String query = "SELECT * FROM encomenda e INNER JOIN EncomendaEntrega ee ON ee.EntregaidEntrega = e.idEntrega WHERE e.idEntrega = " + idEntrega;
        return getFromDatabase(query);
    }

    public List<Encomenda> getFromDatabase(String query) {
        ArrayList<Encomenda> list = new ArrayList<>();
        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

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
            }
        } catch (SQLException e) {
            Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    public double getCreditosData(Data date, double preco) {
        String dataI = "01/01/2021";
        String dataM = "31/07/2021";
        String dataF = "31/12/2021";

        Data d2 = new Data(dataI);
        Data d3 = new Data(dataM);
        Data d4 = new Data(dataF);

        if (date.getMes() >= d2.getMes() && d3.getMes() >= date.getMes()) {
            return preco * 3;
        }
        if (date.getMes() > d3.getMes() && d4.getMes() >= date.getMes()) {
            return preco * 5;
        }
        return -1;
    }

    public boolean geraCreditos(Cliente c, double precoTotal) {
        
        if(precoTotal>50 && precoTotal<100){
            return cliDB.addCreditos(c,precoTotal/5);
        }
        if(precoTotal>100){
            return cliDB.addCreditos(c,precoTotal/3);
        }
        return false;
    }
    
    public boolean updateEncomenda(Encomenda encomenda) throws SQLException, ParseException {
        boolean updated = false;

        try ( CallableStatement callSmt = getConnection().prepareCall("{ call updateEncomenda(?,?,?,?,?,?,?) }")) {
                        
            callSmt.setInt(1, encomenda.getId());
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            java.util.Date date = sdf1.parse(encomenda.getDataPedida());
            java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());
            callSmt.setTimestamp(2, sqlStartDate);
            callSmt.setDouble(3, encomenda.getPreco());
            callSmt.setDouble(4, encomenda.getPesoEncomenda());
            callSmt.setDouble(5, encomenda.getTaxa());
            callSmt.setDouble(6, encomenda.getEstado().getEstado());
            callSmt.setDouble(7, encomenda.getNif());

            callSmt.execute();

            updated = true;
            try {

                callSmt.close();

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            }
        }

        return updated;

    }
    

}
