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
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Estacionamento;
import lapr.project.model.Veiculo;

/**
 *
 * @author josep
 */
public class EstacionamentosDB extends DataHandler {

    /**
     * Cria um novo estacionamento
     *
     * @param numLote número de lote do estacionamento
     * @param carregador disponibilidade do carregador do estacionamento (1 se
     * disponível, 0 se não esta disponível)
     * @param idParque id do parque
     * @return novo estacionamento criado
     */
    public Estacionamento novoEstacionamento(int numLote, int carregador, int idParque) {
        return new Estacionamento(numLote, carregador, idParque);
    }

    /**
     * Regista o estacionamento
     *
     * @param lestac
     * @return
     */
    public boolean registaEstacionamento(List<Estacionamento> lestac) {
        for (Estacionamento estac : lestac) {
            if (validaEstacionamento(estac)) {
                addEstacionamento(estac);
            }
        }
        return true;
    }

    /**
     * Valida o estacionamento
     *
     * @param estac estacionamento
     * @return true se o estacionamento é valido
     */
    public boolean validaEstacionamento(Estacionamento estac) {
        return !(estac == null || estac.getNumeroLote() <= 0 || estac.getCarregador() < 0 || estac.getCarregador() > 1 || estac.getIdParque()< 0);
    }

    /**
     * Adiciona o estacionamento à base de dados
     *
     * @param estac estacionamento
     * @return
     */
    public boolean addEstacionamento(Estacionamento estac) {
        addEstacionamento(estac.getNumeroLote(), estac.getCarregador(), estac.getIdParque());
        return true;
    }

    /**
     * Adiciona o estacionamento à base de dados
     *
     * @param numeroLote número de lote do estacionamento
     * @param carregador disponibilidade do carregador do estacionamento (1 se
     * disponível, 0 se não esta disponível)
     * @param idParque id do parque
     */
    public void addEstacionamento(int numeroLote, int carregador, int idParque) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addEstacionamento(?,?,?) }")) {
                callStmt.setInt(1, numeroLote);
                callStmt.setInt(2, carregador);
                callStmt.setInt(3, idParque);
                callStmt.execute();
            }
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

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int numLote = rSet.getInt(1);
                    int carregador = rSet.getInt(2);
                    int idParque = rSet.getInt(3);
                    list.add(new Estacionamento(numLote, carregador, idParque));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Retorna lista de estacionamento de um determinado parque recendo o seu
     * nif por parâmetro
     *
     * @param farmNIF
     * @param parqueID
     * @return lista de estacionamento do parque
     */
    public List<Estacionamento> getListaEstacionamentosByFarmaciaNifParqueId(int farmNIF, int parqueID) {
        ArrayList<Estacionamento> list = new ArrayList<>();
        String query = "SELECT * FROM estacionamento e INNER JOIN parque p ON p.FarmaciaNIF = e.ParqueFarmaciaNIF "
                + "INNER JOIN farmacia f ON f.NIF = e.ParqueFarmaciaNIF "
                + "WHERE e.ParqueFarmaciaNIF = " + farmNIF + "AND p.FarmaciaNIF = " + farmNIF + "AND p.idParque = " + parqueID;
        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int numLote = rSet.getInt(1);
                    int carregador = rSet.getInt(2);
                    int idParque = rSet.getInt(3);
                    list.add(new Estacionamento(numLote, carregador, idParque));
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Estacionamento getEstacionamentoById(int loteEstacionameto) {
        String query = "SELECT * FROM estacionamento WHERE numerolote = " + loteEstacionameto;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    int lote = rSet.getInt(1);
                    int carregador = rSet.getInt(2);
                    int idParque = rSet.getInt(3);
                    return new Estacionamento(lote, carregador, idParque);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EntregaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    public boolean addEstacionamentoVeiculo(Estacionamento estacionamento, Veiculo veiculo) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addEstacionamentoVeiculo(?,?,?,?) }")) {
                callStmt.setInt(1, estacionamento.getIdParque());
                callStmt.setInt(2, veiculo.getId());
                callStmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                callStmt.execute();
            }
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
