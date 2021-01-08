package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class FarmaciaTest {
    
    /**
     * Test of getNif method, of class Farmacia.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Farmacia instance = new Farmacia(123456789);
        int expResult = 123456789;
        int result = instance.getNif();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNif method, of class Farmacia.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        int nif = 987654321;
        Farmacia instance = new Farmacia();
        instance.setNif(nif);
        assertEquals(nif, instance.getNif());

    }

    /**
     * Test of getParque method, of class Farmacia.
     */
    @Test
    public void testGetParque() {
        System.out.println("getParque");
        Farmacia instance = new Farmacia(12345789);
        instance.setParque(new Parque(5));
        int expResult = 5;
        assertEquals(expResult, instance.getParque().getNumeroMaximo());

    }

    /**
     * Test of setParque method, of class Farmacia.
     */
    @Test
    public void testSetParque() {
        System.out.println("setParque");
        Parque parque = new Parque(6);
        Farmacia instance = new Farmacia();
        instance.setParque(parque);
        assertEquals(parque, instance.getParque());
    }
    
}
