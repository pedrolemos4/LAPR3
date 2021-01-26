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
public class ScooterTest {

    @Test
    void ScooterCompleteConstructorTest() {
        Scooter scooter = new Scooter("descricao", 100, 85, 50, 30, 40, 1, 50, 1);

        assertEquals(scooter, scooter);
    }

    @Test
    void ScooterCopyConstructorTest() {
        Veiculo instance = new Veiculo("descricao", 100, 85, 50, 30, 40, 1,50);
        Scooter scooter = new Scooter(instance, 1);

        assertEquals(scooter, scooter);
    }

    @Test
    void ScooterConstructorTest() {
        Scooter scooter = new Scooter(1);
        assertEquals(scooter, scooter);
    }

    /**
     * Test of getAreaFrontal method, of class Scooter.
     */
    @Test
    public void testGetAreaFrontal() {
        System.out.println("getAreaFrontal");
        Scooter instance = new Scooter("descricao", 100, 85, 50, 30, 40, 1, 50, 1);
        double expResult = 50.0;
        double result = instance.getAreaFrontal();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAreaFrontal method, of class Scooter.
     */
    @Test
    public void testSetAreaFrontal() {
        System.out.println("setAreaFrontal");
        Scooter instance = new Scooter("descricao", 100, 85, 50, 30, 40, 50, 50, 1);
        double expResult = 30.0;
        instance.setAreaFrontal(expResult);
        assertEquals(expResult, instance.getAreaFrontal(), 0.0);
    }

    /**
     * Test of toString method, of class Scooter.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Scooter instance = new Scooter("descricao", 100, 85, 50, 30, 40, 1, 50, 1);
        instance.setId(1);
        String expResult = "Veiculo:\n" +
                "Id: \t1\n" +
                "Descrição: \tdescricao\n" +
                "Capacidade: \t100.0\n" +
                "Percentagem de Bateria= \t85.0\n" +
                "Peso máximo= \t50.0\n" +
                "Peso do Veiculo= \t30.0\n" +
                "Potência= \t40.0\n" +
                "Estado: \tDisponível\n" +
                "Area Frontal: \t50.0\n" +
                "ID Scooter: \t1";
        assertEquals(expResult, instance.toString());
    }

    /**
     * Test of getId method, of class Scooter.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Scooter instance = new Scooter("descricao", 100, 85, 50, 30, 40, 1, 50, 1);
        int expResult = 1;
        instance.setId(1);
        int result = instance.getId();
        assertEquals(expResult, result);
    }

}
