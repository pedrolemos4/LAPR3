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
import lapr.project.model.Farmacia;

/**
 *
 * @author josep
 */
public class FarmaciaDB extends DataHandler {

    /**
     * Cria uma nova farmácia
     *
     * @param nif nif da farmácia
     * @param email email da farmácia
     * @param morada morada da farmácia
     * @return nova farmacia criada
     */
    public Farmacia novaFarmacia(int nif, String email, String morada) {
        return new Farmacia(nif, email, morada);
    }

    /**
     * Regista a farmacia
     *
     * @param farm farmacia
     * @return
     */
    public boolean registaFarmacia(Farmacia farm) {
        if (validaFarmacia(farm)) {
            addFarmacia(farm);
        }
        return true;
    }

    /**
     * Valida a farmacia
     *
     * @param farm farmacia
     * @return true se a farmacia é valida
     */
    public boolean validaFarmacia(Farmacia farm) {
        return !(farm == null || farm.getNIF() <= 0);
    }

    /**
     * Adiciona a farmacia à base de dados
     *
     * @param farm farmacia
     * @return
     */
    public boolean addFarmacia(Farmacia farm) {
        addFarmacia(farm.getNIF(), farm.getMorada());
        return true;
    }

    /**
     * Adiciona a farmacia à base de dados
     *
     * @param nif nif da farmácia
     */
    public void addFarmacia(int nif, String morada) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addFarmacia(?,?) }")) {
                callStmt.setInt(1, nif);
                callStmt.setString(2, morada);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna lista com todas as farmácias
     *
     * @return lista das farmácias
     */
    public List<Farmacia> getLstFarmacias() {
        ArrayList<Farmacia> list = new ArrayList<>();
        String query = "SELECT * FROM farmacia";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada = rSet.getString(3);
                    list.add(new Farmacia(nif, email, morada));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    public Farmacia getFarmaciaByNIF(int nif) {
        String query = "SELECT * FROM farmacia f INNER JOIN endereco e ON f.morada = e.morada WHERE f.nif =" + nif;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    nif = rSet.getInt(1);
                    String email = rSet.getString(2);
                    String morada = rSet.getString(3);

                    return new Farmacia(nif, email, morada);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EnderecoDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

}
