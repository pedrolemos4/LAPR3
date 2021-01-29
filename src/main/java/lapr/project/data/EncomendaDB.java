package lapr.project.data;

import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Produto;
import lapr.project.utils.Data;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class EncomendaDB extends DataHandler {

    private final ClienteDB cliDB;

    public EncomendaDB() {
        cliDB = new ClienteDB();
    }

    /**
     * Devolve a encomenda com base no id da encomenda
     *
     * @param id id da encomenda
     * @return encomenda cujo id é o recebido por parâmetro
     */
    public Encomenda getEncomenda(int id) {
        String query = "SELECT * FROM encomenda WHERE idEncomenda = " + id;
        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int idEncomenda = rSet.getInt(1);
                    Timestamp dataPedida = rSet.getTimestamp(2);
                    double preco = rSet.getDouble(3);
                    double pesoEncomenda = rSet.getDouble(4);
                    double taxa = rSet.getDouble(5);
                    int estado = rSet.getInt(6);
                    int nif = rSet.getInt(7);
                    int nifFarmacia = rSet.getInt(8);
                    Encomenda e = new Encomenda(nif, nifFarmacia, dataPedida.toString(), preco, pesoEncomenda, taxa,
                            estado);
                    e.setId(idEncomenda);
                    return e;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
            return null;
        } finally {
            closeAll();
        }
        return null;
    }

    /**
     * Valida a encomenda
     *
     * @param enc encomenda a ser validada
     * @return true se a encomenda for válida, false se não
     */
    public boolean validaEncomenda(Encomenda enc) {
        return enc.getNif() != 0 && enc.getDataPedida() != null
                && (enc.getEstado().getEstado() < 3 || enc.getEstado().getEstado() > 0) && enc.getPesoEncomenda() > 0
                && enc.getPreco() > 0 && enc.getTaxa() > 0;
    }

    /**
     * Regista e encomenda se for válida
     *
     * @param enc encomenda a ser registada
     * @return id da encomenda, retorna 0 se a encomenda não for registada
     */
    public int registaEncomenda(Encomenda enc) throws SQLException, ParseException {
        if (validaEncomenda(enc)) {
            enc.setId(addEncomenda(enc));
            return enc.getId();
        }
        return 0;
    }

    /**
     * Adiciona a encomenda à base de dados
     *
     * @param enc encomenda a ser adicionada
     * @return true se for adicionada com sucesso, false se não
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    public int addEncomenda(Encomenda enc) throws SQLException, ParseException {
        return addEncomenda(enc.getNif(), enc.getNifFarmacia(), enc.getDataPedida(), enc.getPreco(),
                enc.getPesoEncomenda(), enc.getTaxa(), enc.getEstado().getEstado());
    }

    /**
     * Adiciona a encomenda à base de dados
     *
     * @param nif           nif do cliente
     * @param nifFarmacia   nif da farmácia
     * @param dataPedida    data em que a encomenda foi pedida
     * @param preco         preço da encomenda
     * @param pesoEncomenda peso da encomenda
     * @param taxa          taxa da encomenda
     * @param estado        estado da encomenda
     * @return
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    public int addEncomenda(int nif, int nifFarmacia, String dataPedida, double preco, double pesoEncomenda,
            double taxa, int estado) throws SQLException, ParseException {
        int id;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call addEncomenda(?,?,?,?,?,?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = sdf1.parse(dataPedida);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            callStmt.setDate(2, sqlStartDate);
            callStmt.setInt(3, nifFarmacia);
            callStmt.setDouble(4, preco);
            callStmt.setDouble(5, pesoEncomenda);
            callStmt.setDouble(6, taxa);
            callStmt.setInt(7, estado);
            callStmt.setInt(8, nif);
            callStmt.execute();
            id = callStmt.getInt(1);
            return id;
        } catch (NullPointerException ex) {
            Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, ex.getMessage());
            return 0;
        } finally {
            closeAll();
        }
    }

    /**
     * Guarda na base de dados a lista de produtos por encomenda
     *
     * @param enc encomenda a registar
     * @param p   produto a registar
     * @return true se os dados foram registados, false se não
     */
    public boolean registaEncomendaProduto(Encomenda enc, Produto p, int stock) {
        if (validaEncomenda(enc)) {
            return registaEncomendaProduto(enc.getId(), p.getId(), stock);
        }
        return false;
    }

    /**
     * Guarda na base de dados a lista de produtos por encomenda
     *
     * @param enc   encomenda a registar
     * @param p     produto a registar
     * @param stock quantidade do produto a registar
     * @return true se os dados foram registados, false se não
     */
    private boolean registaEncomendaProduto(int enc, int p, int stock) {
        try {
            try (CallableStatement callStmt1 = getConnection().prepareCall("{ call addEncomendaProduto(?,?,?) }")) {
                callStmt1.setInt(1, enc);
                callStmt1.setInt(2, p);
                callStmt1.setInt(3, stock);
                callStmt1.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Devolve a lista de encomendas realizadas numa determinada farmácia
     *
     * @param nifFarmacia nif da farmacia
     * @return lista de encomenda
     */
    public List<Encomenda> getListaEncomenda(int nifFarmacia) {
        String query = "SELECT * FROM encomenda WHERE EstadoEncomendaidEstadoEncomenda = 1 AND nifFarmacia = "
                + nifFarmacia;
        return getFromDatabase(query);
    }

    /**
     * Devolve a lista de encomendas pelo id de entrega
     *
     * @param idEntrega id da entrega
     * @return lista de encomendas
     */
    public List<Encomenda> getListaEncomendaById(int idEntrega) {
        String query = "SELECT * FROM encomenda e INNER JOIN EncomendaEntrega ee ON ee.EntregaidEntrega = e.idEntrega WHERE e.idEntrega = "
                + idEntrega;
        return getFromDatabase(query);
    }

    /**
     * Retorna a lista de encomendas presentes na base de dados
     *
     * @param query query de pesquisa
     * @return lista de encomendas
     */
    public List<Encomenda> getFromDatabase(String query) {
        ArrayList<Encomenda> list = new ArrayList<>();
        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int idEncomenda = rSet.getInt(1);
                    Timestamp dataPedida = rSet.getTimestamp(2);
                    double preco = rSet.getDouble(3);
                    double pesoEncomenda = rSet.getDouble(4);
                    double taxa = rSet.getDouble(5);
                    int estado = rSet.getInt(6);
                    int nif = rSet.getInt(7);
                    int nifFarmacia = rSet.getInt(8);
                    Encomenda e = new Encomenda(nif, nifFarmacia, dataPedida.toString(), preco, pesoEncomenda, taxa,
                            estado);
                    e.setId(idEncomenda);
                    list.add(e);
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
            return Collections.emptyList();
        } finally {
            closeAll();
        }
    }

    /**
     * Retorna o valor dos créditos
     *
     * @param date  data de quando foi feita a encomenda
     * @param preco preco do produto
     * @return valor dos créditos
     */
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

    /**
     * Gera e adiciona créditos oa cliente
     *
     * @param c          cliente a adicionar os créditos
     * @param precoTotal preco total da encomenda
     * @return true se os créditos foram adicionados com sucesso, false se não
     */
    public boolean geraCreditos(Cliente c, double precoTotal) {

        if (precoTotal > 50 && precoTotal < 100) {
            return cliDB.addCreditos(c, precoTotal / 5);
        }
        if (precoTotal > 100) {
            return cliDB.addCreditos(c, precoTotal / 3);
        }
        return false;
    }

    /**
     * Atualiza a encomenda recebendo o id e o estado por parâmetro
     *
     * @param idEncomenda id da encomenda
     * @param estado      estado da encomenda
     * @return true se atualizou a encomenda com sucesso, false se não
     * @throws SQLException
     */
    public boolean updateEncomenda(int idEncomenda, int estado) throws SQLException {
        try (CallableStatement callSmt = getConnection().prepareCall("{ call updateEncomenda(?,?) }")) {
            callSmt.setInt(2, estado);
            callSmt.setInt(1, idEncomenda);
            callSmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(EncomendaDB.class.getName()).log(Level.WARNING, e.getMessage());
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Calcua a taxa da encomenda com base no preco dos produtos.
     *
     * @param preco
     * @return
     */
    public double getTaxa(double preco) {
        if (preco < 50) {
            return 0.3;
        }
        if (preco >= 50 && preco < 100) {
            return 0.2;
        }
        return 0.15;
    }
}
