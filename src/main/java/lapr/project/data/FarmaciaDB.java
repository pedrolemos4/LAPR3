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
import lapr.project.model.Farmacia;

/**
 *
 * @author josep
 */
public class FarmaciaDB extends DataHandler {

    private final DataHandler dataHandler;

    public FarmaciaDB() {
        this.dataHandler = DataHandler.getInstance();
    }

    /**
     * Cria uma nova farmácia
     *
     * @param nif nif da farmácia
     * @return nova farmacia criada
     */
    public Farmacia novaFarmacia(int nif) {
        Farmacia farm = new Farmacia(nif);
        return farm;
    }

    /**
     * Regista a farmacia
     *
     * @param farm farmacia
     */
    public void registaFarmacia(Farmacia farm) {
        if (validaFarmacia(farm)) {
            addFarmacia(farm);
        }
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
     */
    public void addFarmacia(Farmacia farm) {
        addFarmacia(farm.getNIF());
    }

    /**
     * Adiciona a farmacia à base de dados
     *
     * @param nif nif da farmácia
     */
    private void addFarmacia(int nif) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addFarmacia(?) }");
            callStmt.setInt(1, nif);
            callStmt.execute();
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

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            while (rSet.next()) {
                int nif = rSet.getInt(1);
                list.add(new Farmacia(nif));
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
}
