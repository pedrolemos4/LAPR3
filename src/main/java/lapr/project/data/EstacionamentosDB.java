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
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Estacionamento;

/**
 *
 * @author josep
 */
public class EstacionamentosDB extends DataHandler {

    private final DataHandler dataHandler;

    public EstacionamentosDB() {
        this.dataHandler = DataHandler.getInstance();
    }

    /**
     * Cria um novo estacionamento
     *
     * @param numLote número de lote do estacionamento
     * @param carregador disponibilidade do carregador do estacionamento (1 se
     * disponível, 0 se não esta disponível)
     * @param nif nif da farmácia/parque
     * @return novo estacionamento criado
     */
    public Estacionamento novoEstacionamento(int numLote, int carregador, int nif) {
        Estacionamento estac = new Estacionamento(numLote, carregador, nif);
        return estac;
    }

    /**
     * Regista o estacionamento
     *
     * @param estac estacionamento
     */
    public void registaEstacionamento(Estacionamento estac) {
        if (validaEstacionamento(estac)) {
            addEstacionamento(estac);
        }
    }

    /**
     * Valida o estacionamento
     *
     * @param estac estacionamento
     * @return true se o estacionamento é valido
     */
    public boolean validaEstacionamento(Estacionamento estac) {
        return !(estac == null || estac.getNumeroLote() <= 0 || estac.getCarregador() < 0 || estac.getCarregador() > 1 || estac.getNIF() < 0);
    }

    /**
     * Adiciona o estacionamento à base de dados
     *
     * @param estac estacionamento
     */
    public void addEstacionamento(Estacionamento estac) {
        addEstacionamento(estac.getNumeroLote(), estac.getCarregador(), estac.getNIF());
    }

    /**
     * Adiciona o estacionamento à base de dados
     *
     * @param numLote número de lote do estacionamento
     * @param carregador disponibilidade do carregador do estacionamento (1 se
     * disponível, 0 se não esta disponível)
     * @param nif nif da farmácia/parque
     */
    private void addEstacionamento(int numeroLote, int carregador, int nif) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addEstacionamento(?,?,?) }");
            callStmt.setInt(1, numeroLote);
            callStmt.setInt(2, carregador);
            callStmt.setInt(3, nif);
            callStmt.execute();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna lista com todos os estacionamentos
     *
     * @return lista dos estacionamentos
     */
    public List<Estacionamento> getLstEstacionamentos() {
        ArrayList<Estacionamento> list = new ArrayList<>();
        String query = "SELECT * FROM estacionamento";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            while (rSet.next()) {
                int numLote = rSet.getInt(1);
                int carregador = rSet.getInt(2);
                int nif = rSet.getInt(3);
                list.add(new Estacionamento(numLote, carregador, nif));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
