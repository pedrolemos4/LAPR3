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
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Endereco;

/**
 *
 * @author josep
 */
public class EnderecoDB extends DataHandler {

    /**
     * Cria um novo endereço
     *
     * @param morada morada do endereço
     * @param latitude latitude do endereço
     * @param longitude longitude do endereço
     * @param altitude altitude do endereço
     * @return novo endereço criado
     */
    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        return new Endereco(morada, latitude, longitude, altitude);
    }

    /**
     * Regista o endereço
     *
     * @param end endereço
     * @return
     */
    public boolean registaEndereco(Endereco end) {
        if (validaEndereco(end)) {
            addEndereco(end);
        }
        return true;
    }

    /**
     * Valida endereço
     *
     * @param end endereço
     * @return true se os dados do endereço são válidos
     */
    public boolean validaEndereco(Endereco end) {
        return !(end == null || end.getMorada().isEmpty() || end.getLatitude() < 0 || end.getLongitude() < 0 || end.getAltitude() < 0);
    }

    /**
     * Adiciona o endereço à base de dados
     *
     * @param end endereço
     * @return
     */
    public boolean addEndereco(Endereco end) {
        addEndereco(end.getMorada(), end.getLatitude(), end.getLongitude(), end.getAltitude());
        return true;
    }

    /**
     * Adiciona o endereço à base de dados
     *
     * @param morada morada do endereço
     * @param latitude latitude do endereço
     * @param longitude longitude do endereço
     * @param altitude altitude do endereço
     */
    public void addEndereco(String morada, double latitude, double longitude, double altitude) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addEndereco(?,?,?,?) }")) {
                callStmt.setString(1, morada);
                callStmt.setDouble(2, latitude);
                callStmt.setDouble(3, longitude);
                callStmt.setDouble(4, altitude);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna a lista de todos os endereços registados
     *
     * @return lista dos endereços
     */
    public List<Endereco> getLstEnderecos() {
        ArrayList<Endereco> list = new ArrayList<>();
        String query = "SELECT * FROM endereco";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    String morada = rSet.getString(1);
                    double latitude = rSet.getDouble(2);
                    double longitude = rSet.getDouble(3);
                    double altitude = rSet.getDouble(4);
                    list.add(new Endereco(morada, latitude, longitude, altitude));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    /**
     * Retorna endereço do cliente recebendo o nif por parãmetro
     *
     * @param nif nif do cliente
     * @return endereço do cliente
     */
    public Endereco getEnderecoByNifCliente(int nif) {
        String query = "SELECT * FROM endereco e INNER JOIN cliente c ON e.morada = c.Enderecomorada WHERE c.UtilizadorNIF = " + nif;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    String morada = rSet.getString(1);
                    double latitude = rSet.getDouble(2);
                    double longitude = rSet.getDouble(3);
                    double altitude = rSet.getDouble(4);

                    return new Endereco(morada, latitude, longitude, altitude);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    /**
     * Retorna endereço do parque
     *
     * @return endereço do parque
     */
    public Endereco getEnderecoParque() {
        String query = "SELECT * FROM endereco e INNER JOIN parque c ON e.morada = c.Enderecomorada";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    String morada = rSet.getString(1);
                    double latitude = rSet.getDouble(2);
                    double longitude = rSet.getDouble(3);
                    double altitude = rSet.getDouble(4);

                    return new Endereco(morada, latitude, longitude, altitude);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
