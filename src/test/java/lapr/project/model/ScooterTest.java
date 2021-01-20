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
        Veiculo instance = new Veiculo("descricao", 100, 85, 50, 30, 40, 1);
        Scooter scooter = new Scooter(instance, 1, 50);

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
        Scooter instance = new Scooter("descricao", 100, 85, 50, 30, 40, 50, 50,1);
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
        Scooter instance = new Scooter("descricao", 100, 85, 50, 30, 40,1, 50,1);
        String expResult = "Veiculo:"
                + "\nDescrição: \t" + "descricao"
                + "\nCapacidade: \t" + 100
                + "\nPercentagem de Bateria= \t" + 85.0
                + "\nPeso máximo= \t" + 50.0
                + "\nPeso do Veiculo= \t" + 30.0
                + "\nPotência= \t" + 40.0
                + "\nEstado: \t" + "Disponível"
                + "\nÁrea frontal: \t" + 50.0;
        assertEquals(expResult, instance.toString());
    }

}
