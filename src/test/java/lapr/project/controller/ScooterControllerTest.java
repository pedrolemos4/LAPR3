/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Tiago
 */
public class ScooterControllerTest {

    /**
     * Test of addScooter method, of class ScooterController.
     */
    @Test
    public void testAddScooter() throws Exception {
        System.out.println("addScooter");
        ScooterDB scooterDataHandler =new ScooterDB();
        String descricao = "descricao";
        int percentagemBateria = 85;
        double pesoMaximo = 50;
        double pesoScooter = 30;
        double potencia = 40;
        int estado = 1;
        ScooterController instance = new ScooterController(scooterDataHandler);
        Scooter expResult = new Scooter(descricao,percentagemBateria, pesoMaximo, pesoScooter, potencia,estado);
        Scooter result = instance.addScooter(descricao,percentagemBateria, pesoMaximo, pesoScooter, potencia, estado);
        assertEquals(expResult, result);
    }

}
