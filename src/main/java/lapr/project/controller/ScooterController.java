/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ScooterDataHandler;
import lapr.project.model.Scooter;

/**
 *
 * @author Tiago
 */
public class ScooterController {

    private final ScooterDataHandler scooterDataHandler;

    public ScooterController(ScooterDataHandler scooterDataHandler) {
        this.scooterDataHandler = scooterDataHandler;
    }

    public Scooter addScooter(int percentagemBateria, double pesoMaximo,
            double pesoScooter, double potencia, int estado) throws SQLException {
        Scooter scooter = new Scooter(percentagemBateria, pesoMaximo, pesoScooter, potencia, estado);

        scooter.setId(scooterDataHandler.addScooter(scooter));
        return scooter;
    }
}
