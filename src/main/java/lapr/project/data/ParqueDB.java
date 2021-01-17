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

    /**
     * Cria um novo parque
     *
     * @param nif nif do parque/farmácia
     * @param numMax limite máximo de veiculos do parque
     * @param tipo tipo de veículos do parque
     * @return novo parque criado
     */
    public Parque novoParque(int nif, int numMax, String tipo) {
        return new Parque(nif, numMax, tipo);
    }

    /**
     * Regista o parque
     *
     * @param lparks lista dos parques a serem registados
     * @return
     */
    public boolean registaParques(List<Parque> lparks) {
        for (Parque park : lparks) {
            if (validaParque(park)) {
                System.out.println("VALIDOU PARQUE");
                addParque(park);
            }
        }
        return true;
    }

    /**
     * Valida o parque
     *
     * @param park parque
     * @return true se o parque é valido
     */
    public boolean validaParque(Parque park) {
        return !(park.getNIF() < 0 || park.getNumeroMaximo() < 0);
    }

    /**
     * Adiciona o parque à base de dados
     *
     * @param park parque
     */
    public void addParque(Parque park) {
        addParque(park.getNIF(), park.getNumeroMaximo(), park.getTipo());
    }

    /**
     * Adiciona o parque à base de dados
     *
     * @param nif nif do parque/farmácia
     * @param numMax limite máximo de veiculos do parque
     * @param tipo tipo do parque
     */
    public void addParque(int nif, int numMax, String tipo) {
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ call addParque(?,?,?) }")) {
                callStmt.setInt(1, nif);
                callStmt.setInt(2, numMax);
                callStmt.setString(3, tipo);
                callStmt.execute();
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista com todos os parques de uma determinada farmácia
     *
     * @param NIF nif da farmácia
     * @return lista dos parques da farmácia
     */
    public List<Parque> getLstParquesByFarmaciaNif(int NIF) {
        ArrayList<Parque> list = new ArrayList<>();
        String query = "SELECT * FROM parque p WHERE p.FarmaciaNIF =" + NIF;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    int numMax = rSet.getInt(2);
                    String tipo = rSet.getString(3);
                    list.add(new Parque(nif, numMax, tipo));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(ParqueDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    /**
     * Lista com todos os parques registados
     *
     * @return lista dos parques
     */
    public List<Parque> getLstParques() {
        ArrayList<Parque> list = new ArrayList<>();
        String query = "SELECT * FROM parque";

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int nif = rSet.getInt(1);
                    int numMax = rSet.getInt(2);
                    String tipo = rSet.getString(3);
                    list.add(new Parque(nif, numMax, tipo));
                }
                return list;
            }
        } catch (SQLException e) {
            Logger.getLogger(ParqueDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return list;
    }

    /**
     * Retorna o limite máximo de veiculos do parque recebendo o nif da farmácia
     * referente ao parque
     *
     * @param parqueNif nif do parque
     * @return limite máximo de veiculos
     */
    public int getNumMaxParqueByNIF(int parqueNif) {
        int numMax = 0;
        String query = "SELECT p.numeroMaximo FROM parque p INNER JOIN estacionamento e ON p.FarmaciaNIF = e.ParqueFarmaciaNIF WHERE p.FarmaciaNIF = " + parqueNif;
        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                numMax = rSet.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ParqueDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return numMax;
    }
}
