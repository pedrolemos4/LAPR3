package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class ScooterTest {

    @Test
    void ScooterEmptyConstructorTest(){
        Scooter instance = new Scooter();

        assertEquals(instance,instance);
    }

    @Test
    void ScooterConstructorTest(){
        Scooter instance = new Scooter(85, 50, 30, 40);

        assertEquals(instance,instance);
    }

    /**
     * Test of getPercentagemBateria method, of class Scooter.
     */
    @Test
    public void testGetPercentagemBateria() {
        System.out.println("getPercentagemBateria");
        Scooter instance = new Scooter(85, 50, 30, 40);
        int expResult = 85;
        int result = instance.getPercentagemBateria();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPesoMaximo method, of class Scooter.
     */
    @Test
    public void testGetPesoMaximo() {
        System.out.println("getPesoMaximo");
        Scooter instance = new Scooter(85, 50, 30, 40);
        double expResult = 50.0;
        double result = instance.getPesoMaximo();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getPesoScooter method, of class Scooter.
     */
    @Test
    public void testGetPesoScooter() {
        System.out.println("getPesoScooter");
        Scooter instance = new Scooter(85, 50, 30, 40);
        double expResult = 30.0;
        double result = instance.getPesoScooter();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getPotencia method, of class Scooter.
     */
    @Test
    public void testGetPotencia() {
        System.out.println("getPotencia");
        Scooter instance = new Scooter(85, 50, 30, 40);
        double expResult = 40.0;
        double result = instance.getPotencia();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setPercentagemBateria method, of class Scooter.
     */
    @Test
    public void testSetPercentagemBateria() {
        System.out.println("setPercentagemBateria");
        int percentagemBateria = 75;
        Scooter instance = new Scooter(85, 50, 30, 40);
        instance.setPercentagemBateria(percentagemBateria);
        assertEquals(percentagemBateria, instance.getPercentagemBateria(), 0.0);
    }

    /**
     * Test of setPesoMaximo method, of class Scooter.
     */
    @Test
    public void testSetPesoMaximo() {
        System.out.println("setPesoMaximo");
        double pesoMaximo = 36.0;
        Scooter instance = new Scooter(85, 50, 30, 40);
        instance.setPesoMaximo(pesoMaximo);
        assertEquals(pesoMaximo, instance.getPesoMaximo(), 0.0);
    }

    /**
     * Test of setPesoScooter method, of class Scooter.
     */
    @Test
    public void testSetPesoScooter() {
        System.out.println("setPesoScooter");
        double pesoScooter = 20.0;
        Scooter instance = new Scooter(85, 50, 30, 40);
        instance.setPesoScooter(pesoScooter);
        assertEquals(pesoScooter, instance.getPesoScooter(), 0.0);
    }

    /**
     * Test of setPotencia method, of class Scooter.
     */
    @Test
    public void testSetPotencia() {
        System.out.println("setPotencia");
        double potencia = 29.0;
        Scooter instance = new Scooter(85, 50, 30, 40);
        instance.setPotencia(potencia);
        assertEquals(potencia, instance.getPotencia(), 0.0);
    }
    
}
