/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.Parque;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @param maxCap capacidade máxima de carregamento do parque
     * @return novo parque criado
     */
    public Parque novoParque(int nif, int numMax, String tipo, int maxCap) {
        return new Parque(nif, numMax, tipo, maxCap);
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
                park.setIdParque(addParque(park));
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
        return !(park.getNIF() < 0 || park.getNumeroMaximo() < 0 || park.getMaxCap() <= 0);
    }

    /**
     * Adiciona o parque à base de dados
     *
     * @param park parque
     * @return id do parque
     */
    public int addParque(Parque park) {
        return addParque(park.getNIF(), park.getNumeroMaximo(), park.getTipo(), park.getMaxCap());
    }

    /**
     * Adiciona o parque à base de dados
     *
     * @param nif nif do parque/farmácia
     * @param numMax limite máximo de veiculos do parque
     * @param tipo tipo do parque
     * @param maxCap capacidade máxima de carregamento do parque
     * @return id do parque
     */
    public int addParque(int nif, int numMax, String tipo, int maxCap) {
        int idParque = 0;
        try {
            openConnection();
            try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call addParque(?,?,?,?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setInt(2, nif);
                callStmt.setInt(3, numMax);
                callStmt.setString(4, tipo);
                callStmt.setInt(5, maxCap);
                callStmt.execute();
                idParque = callStmt.getInt(1);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idParque;
    }

    /**
     * Lista com todos os parques de uma determinada farmácia
     *
     * @param nif nif da farmácia
     * @return lista dos parques da farmácia
     */
    public List<Parque> getLstParquesByFarmaciaNif(int nif) {
        ArrayList<Parque> list = new ArrayList<>();
        String query = "SELECT * FROM parque p WHERE p.FarmaciaNIF =" + nif;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                while (rSet.next()) {
                    int id = rSet.getInt(1);
                    int nifF = rSet.getInt(2);
                    int numMax = rSet.getInt(3);
                    String tipo = rSet.getString(4);
                    int maxCap = rSet.getInt(5);
                    list.add(new Parque(id, nifF, numMax, tipo, maxCap));
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
     * @param farmNIF nif da farmácia
     * @param parqueID id do parque
     * @return limite máximo de veiculos
     */
    public int getNumMaxByFarmaciaNifParqueId(int farmNIF, int parqueID) {
        int numMax = 0;
        String query = "SELECT p.numeroMaximo FROM parque p INNER JOIN farmacia f ON p.FarmaciaNIF = f.NIF WHERE p.FarmaciaNIF = " + farmNIF + "AND p.idParque =" + parqueID;
        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {
                numMax = rSet.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ParqueDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return numMax;
    }

    public Parque getParqueByID(int id) {
        String query = "SELECT * FROM parque p WHERE p.IdParque =" + id;

        try ( Statement stm = getConnection().createStatement()) {
            try ( ResultSet rSet = stm.executeQuery(query)) {

                if (rSet.next()) {
                    id = rSet.getInt(1);
                    int farmaciaNIF = rSet.getInt(2);
                    int numeroMaximo = rSet.getInt(3);
                    String tipo = rSet.getString(4);
                    int maxCap = rSet.getInt(5);
                    return new Parque(id, farmaciaNIF, numeroMaximo, tipo, maxCap);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(FarmaciaDB.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
