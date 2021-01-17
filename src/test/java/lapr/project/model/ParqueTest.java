package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParqueTest {

    @Test
    void ParqueEmptyConstructorTest() {
        Parque instance = new Parque();
        assertEquals(instance, instance);
    }

    @Test
    void ParqueConstructorTest() {
        Parque instance = new Parque(123, 20, "drones");
        assertEquals(instance, instance);
    }

    /**
     * Test of getNIF method, of class Parque.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Parque instance = new Parque(111111111, 20, "drones");
        int expResult = 111111111;
        int result = instance.getNIF();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNIF method, of class Parque.
     */
    @Test
    public void testSetNIF() {
        System.out.println("setNIF");
        int NIF = 112221112;
        Parque instance = new Parque(112221112, 20, "drones");
        instance.setNIF(NIF);
        assertEquals(NIF, instance.getNIF());
    }

    /**
     * Test of getNumeroMaximo method, of class Parque.
     */
    @Test
    void getNumeroMaximo() {
        System.out.println("getNumeroMaximo");
        Parque instance = new Parque(123, 20, "drones");
        int expResult = 20;
        int result = instance.getNumeroMaximo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumeroMaximo method, of class Parque.
     */
    @Test
    void setNumeroMaximo() {
        System.out.println("setNumeroMaximo");
        int numeroMaximo = 15;
        Parque instance = new Parque(123, 20, "drones");
        instance.setNumeroMaximo(15);
        assertEquals(numeroMaximo, instance.getNumeroMaximo());
    }

    @Test
    public void testToString() {
        Parque instance = new Parque(123, 20, "drones");
        String expResult = "Parque{id=0, nif=123, numeroMaximo=20, tipo=drones}";
        assertEquals(expResult, instance.toString());
    }
}
