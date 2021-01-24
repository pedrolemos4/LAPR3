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
     * Retorna a lista de todos os endereços onde os drones possam ir
     * @return lista dos endereços onde os drones possam ir
     */
    public List<Endereco> getLstEnderecosDrone() {
        ArrayList<Endereco> list = new ArrayList<>();
        String query = "SELECT * FROM endereco WHERE altitude < 150";

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
     * Retorna um endereço da base de dados
     * @param query query da base de dados
     * @return endereço
     */
    public Endereco getQuery(String query){
        Endereco end = new Endereco();
        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    String morada = rSet.getString(1);
                    double latitude = rSet.getDouble(2);
                    double longitude = rSet.getDouble(3);
                    double altitude = rSet.getDouble(4);

                    end = new Endereco(morada, latitude, longitude, altitude);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return end;
    }

    /**
     * Retorna endereço do cliente recebendo o nif por parâmetro
     *
     * @param nif nif do cliente
     * @return endereço do cliente
     */
    public Endereco getEnderecoByNifCliente(int nif) {
        String query = "SELECT e.morada, e.latitude, e.longitude, e.altitude FROM endereco e INNER JOIN cliente c ON e.morada = c.Enderecomorada WHERE c.UtilizadorNIF = " + nif;
        return getQuery(query);
    }

    /**
     * Retorna endereço da farmacia recebendo o nif por parâmetro
     *
     * @param nifFarmacia nif da farmacia
     * @return endereço da farmacia
     */
    public Endereco getEnderecoByNifFarmacia(int nifFarmacia) {
        String query = "SELECT e.morada, e.latitude, e.longitude, e.altitude FROM endereco e INNER JOIN farmacia f ON e.morada = f.morada WHERE f.NIF = " + nifFarmacia;
        return getQuery(query);
    }

    /**
     * Retorna endereço da farmacia recebendo a morada por parâmetro
     *
     * @param farmMorada morada da farmacia
     * @return endereço da farmacia
     */
    public Endereco getEnderecoByMorada(String farmMorada) {
        String query = "SELECT * FROM endereco e WHERE e.morada = '" + farmMorada + "'";
        return getQuery(query);
    }

}
