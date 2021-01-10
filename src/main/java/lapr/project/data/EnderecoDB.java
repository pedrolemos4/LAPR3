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

    Endereco end;
    private final DataHandler dataHandler;

    public EnderecoDB() {
        this.dataHandler = DataHandler.getInstance();
    }

    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        end = new Endereco(morada, latitude, longitude, altitude);
        return end;
    }

    public void registaEndereco(Endereco end) {
        if (validaEndereco(end)) {
            addEndereco(end);
        }
    }

    public boolean validaEndereco(Endereco end) {
        if (end == null || end.getMorada().isEmpty() || end.getLatitude() < 0 || end.getLongitude() < 0 || end.getAltitude() < 0) {
            return false;
        }
        return true;
    }

    public void addEndereco(Endereco end) {
        addEndereco(end.getMorada(), end.getLatitude(), end.getLongitude(), end.getAltitude());
    }

    private void addEndereco(String morada, double latitude, double longitude, double altitude) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addEndereco(?,?,?,?) }");
            callStmt.setString(1, morada);
            callStmt.setDouble(2, latitude);
            callStmt.setDouble(3, longitude);
            callStmt.setDouble(4, altitude);
            callStmt.execute();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Endereco> getLstEnderecos() {
        ArrayList<Endereco> list = new ArrayList<>();
        String query = "SELECT * FROM endereco";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            while (rSet.next()) {
                String morada = rSet.getString(1);
                double latitude = rSet.getDouble(2);
                double longitude = rSet.getDouble(3);
                double altitude = rSet.getDouble(4);
                list.add(new Endereco(morada, latitude, longitude, altitude));
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return list;
    }
    
    public Endereco getEnderecoByNifCliente(int nif){
        String query = "SELECT * FROM endereco e INNER JOIN cliente c ON e.morada = c.Enderecomorada WHERE c.UtilizadorNIF = " + nif;

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                String morada = rSet.getString(1);
                double latitude = rSet.getDouble(2);
                double longitude = rSet.getDouble(3);
                double altitude = rSet.getDouble(4);
                
                return new Endereco(morada, latitude, longitude, altitude);
            }

        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }
    
    public Endereco getEnderecoParque(){
        String query = "SELECT * FROM endereco e INNER JOIN parque c ON e.morada = c.Enderecomorada ";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);

            if (rSet.next()) {
                String morada = rSet.getString(1);
                double latitude = rSet.getDouble(2);
                double longitude = rSet.getDouble(3);
                double altitude = rSet.getDouble(4);
                
                return new Endereco(morada, latitude, longitude, altitude);
            }

        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }
}
