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
            e.printStackTrace();
        }
        return list;
    }
}
