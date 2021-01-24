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
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1,5);
        double expResult = 50.0;
        double result = instance.getLargura();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPowerPro method, of class Drone.
     */
    @Test
    public void testSetPowerPro() {
        System.out.println("setPowerPro");
        double powerPro = 30.0;
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1,5);
        instance.setLargura(powerPro);
        assertEquals(powerPro, instance.getLargura());
    }

    /**
     * Test of toString method, of class Drone.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1,5);
        String expResult = "Veiculo:"
                + "\nDescrição: \t" + instance.getDescricao()
                + "\nCapacidade: \t" + instance.getCapacidade()
                + "\nPercentagem de Bateria= \t" + instance.getPercentagemBateria()
                + "\nPeso máximo= \t" + instance.getPesoMaximo()
                + "\nPeso do Veiculo= \t"+ instance.getPesoVeiculo()
                + "\nPotência= \t" + instance.getPotencia()
                +"\nEstado: \t" + instance.getEstadoVeiculo().getDesignacao()
                +"\nArea Frontal: \t" + instance.getAreaFrontal()
                + "\nPower pro: \t" + instance.getLargura();
        assertEquals(expResult, instance.toString());
    }

    /**
     * Test of getId method, of class Drone.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Drone instance = new Drone("descricao", 100, 85, 50, 30, 40, 1, 50, 1,5);
        instance.setId(1);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }


}
