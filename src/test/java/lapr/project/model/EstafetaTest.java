package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class EstafetaTest extends UtilizadorTest{
    
    /**
     * Test of getPesoEstafeta method, of class Estafeta.
     */
    @Test
    public void testGetPesoEstafeta() {
        System.out.println("getPesoEstafeta");
        Estafeta instance = new Estafeta(58, 123, "ss", "dd", 55, "sd");
        double expResult = 58.0;
        double result = instance.getPesoEstafeta();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setPesoEstafeta method, of class Estafeta.
     */
    @Test
    public void testSetPesoEstafeta() {
        System.out.println("setPesoEstafeta");
        double pesoEstafeta = 69.0;
        Estafeta instance = new Estafeta();
        instance.setPesoEstafeta(pesoEstafeta);
        assertEquals(pesoEstafeta, instance.getPesoEstafeta(), 0.0);

    }
    
}
