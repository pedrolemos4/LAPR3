/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Tiago
 */
public class DroneTest {

    @Test
    public void testConstructor() {
        Drone instance = new Drone(1,50);
        assertEquals(instance,instance);
    }

    /**
     * Test of getPowerPro method, of class Drone.
     */
    @Test
    public void testGetPowerPro() {
        System.out.println("getPowerPro");
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1);
        double expResult = 50.0;
        double result = instance.getPowerPro();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPowerPro method, of class Drone.
     */
    @Test
    public void testSetPowerPro() {
        System.out.println("setPowerPro");
        double powerPro = 30.0;
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1);
        instance.setPowerPro(powerPro);
        assertEquals(powerPro, instance.getPowerPro());
    }

    /**
     * Test of toString method, of class Drone.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1);
        String expResult = "Veiculo:"
                + "\nDescrição: \t" + "descricao"
                + "\nCapacidade: \t" + 100.0
                + "\nPercentagem de Bateria= \t" + 85.0
                + "\nPeso máximo= \t" + 50.0
                + "\nPeso do Veiculo= \t" + 30.0
                + "\nPotência= \t" + 40.0
                + "\nEstado: \t" + "Disponível"
                + "\nPower pro: \t" + 50.0;
        assertEquals(expResult, instance.toString());
    }

    /**
     * Test of getId method, of class Drone.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1);
        instance.setId(1);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

}
