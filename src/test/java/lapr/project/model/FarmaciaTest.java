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
        int result = instance.getNIF();
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
        instance.setNIF(nif);
        assertEquals(nif, instance.getNIF());

    }

    /**
     * Test of toString method, of class Farmacia.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Farmacia instance = new Farmacia(123456789);
        String expResult = "Farmacia{nif=123456789}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
}
