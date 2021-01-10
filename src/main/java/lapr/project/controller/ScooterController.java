/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

/**
 *
 * @author Tiago
 */
public class ScooterController {

    private final ScooterDB scooterDB;

    public ScooterController(ScooterDB scooterDB) {
        this.scooterDB = scooterDB;
    }

    public Scooter addScooter(String descricao, int percentagemBateria, double pesoMaximo,
            double pesoScooter, double potencia,double areaFrontal, int estado) throws SQLException {
        Scooter scooter = new Scooter(descricao, percentagemBateria, pesoMaximo, 
                pesoScooter, potencia,areaFrontal, estado);

        scooter.setId(scooterDB.addScooter(scooter));
        return scooter;
    }

    public boolean updateScooter(int idScooter, String descricao, int percentagemBateria,
            double pesoMaximo, double pesoScooter, double potencia,double areaFrontal, int estado) throws SQLException {
        boolean removed = false;
        Scooter scooter = new Scooter(idScooter, descricao, percentagemBateria, pesoMaximo,
                pesoScooter, potencia,areaFrontal, estado);

        removed = scooterDB.updateScooter(scooter);
        return removed;
    }

    public boolean removeScooter(int idScooter) throws SQLException {
        boolean removed = false;

        removed = scooterDB.removeScooter(idScooter);

        return removed;
    }
}
