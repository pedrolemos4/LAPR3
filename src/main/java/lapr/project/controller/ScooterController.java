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

    private final ScooterDB scooterDataHandler;

    public ScooterController(ScooterDB scooterDataHandler) {
        this.scooterDataHandler = scooterDataHandler;
    }

    public Scooter addScooter(String descricao,int percentagemBateria, double pesoMaximo,
            double pesoScooter, double potencia, int estado) throws SQLException {
        Scooter scooter = new Scooter(descricao,percentagemBateria, pesoMaximo, pesoScooter, potencia, estado);

        scooter.setId(scooterDataHandler.addScooter(scooter));
        return scooter;
    }
    
    public boolean removeScooter(int idScooter) throws SQLException {
        boolean removed = false;

        removed = scooterDataHandler.removeScooter(idScooter);

        return removed;
    }
}
