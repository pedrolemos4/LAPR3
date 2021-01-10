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
import lapr.project.model.Parque;

/**
 *
 * @author josep
 */
public class ParqueDB extends DataHandler {

    private final DataHandler dataHandler;

    public ParqueDB() {
        this.dataHandler = DataHandler.getInstance();
    }

    /**
     * Cria um novo parque
     *
     * @param nif nif do parque/farmácia
     * @param morada morada do parque
     * @param numMax limite máximo de scooters do parque
     * @return novo parque criado
     */
    public Parque novoParque(int nif, String morada, int numMax) {
        Parque park = new Parque(nif, morada, numMax);
        return park;
    }

    /**
     * Regista o parque
     *
     * @param park parque
     */
    public void registaParque(Parque park) {
        if (validaParque(park)) {
            addParque(park);
        }
    }

    /**
     * Valida o parque
     *
     * @param park parque
     * @return true se o parque é valido
     */
    public boolean validaParque(Parque park) {
        return !(park.getNIF() < 0 || park.getNumeroMaximo() < 0 || park.getMorada().isEmpty());
    }

    /**
     * Adiciona o parque à base de dados
     *
     * @param park parque
     */
    public void addParque(Parque park) {
        addParque(park.getNIF(), park.getMorada(), park.getNumeroMaximo());
    }

    /**
     * Adiciona o parque à base de dados
     *
     * @param nif nif do parque/farmácia
     * @param morada morada do parque
     * @param numMax limite máximo de scooters do parque
     */
    private void addParque(int nif, String morada, int numMax) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addParque(?,?,?) }");
            callStmt.setInt(1, nif);
            callStmt.setString(2, morada);
            callStmt.setInt(3, numMax);
            callStmt.execute();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista com todos os parques registados
     *
     * @return lista dos parques
     */
    public List<Parque> getLstParques() {
        ArrayList<Parque> list = new ArrayList<>();
        String query = "SELECT * FROM parque";

        Statement stm = null;
        ResultSet rSet = null;

        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            while (rSet.next()) {
                int nif = rSet.getInt(1);
                String morada = rSet.getString(2);
                int numMax = rSet.getInt(3);
                list.add(new Parque(nif, morada, numMax));
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

    /**
     * Retorna o limite máximo de scooters do parque recebendo o nif da farmácia
     * referente ao parque
     *
     * @param parqueNif nif do parque
     * @return limite máximo de scooters
     */
    public int getNumMaxParqueByNIF(int parqueNif) {
        int numMax = 0;
        String query = "SELECT p.numeroMaximo FROM parque p INNER JOIN estacionamento e ON p.FaarmaciaNIF = e.ParqueFarmaciaNIF WHERE p.FaarmaciaNIF = " + parqueNif;
        Statement stm = null;
        ResultSet rSet = null;
        try {
            stm = getConnection().createStatement();
            rSet = stm.executeQuery(query);
            numMax = rSet.getInt(1);
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
        return numMax;
    }
}
