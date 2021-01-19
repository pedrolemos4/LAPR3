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
        Parque instance = new Parque(123, 20, "drones", 1000);
        assertEquals(instance, instance);
    }

    /**
     * Test of getNIF method, of class Parque.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Parque instance = new Parque(111111111, 20, "drones", 1000);
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
        Parque instance = new Parque(112221112, 20, "drones", 1000);
        instance.setNIF(NIF);
        assertEquals(NIF, instance.getNIF());
    }

    /**
     * Test of getNumeroMaximo method, of class Parque.
     */
    @Test
    public void testGetNumeroMaximo() {
        System.out.println("getNumeroMaximo");
        Parque instance = new Parque(123, 20, "drones", 1000);
        int expResult = 20;
        int result = instance.getNumeroMaximo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumeroMaximo method, of class Parque.
     */
    @Test
    public void testSetNumeroMaximo() {
        System.out.println("setNumeroMaximo");
        int numeroMaximo = 15;
        Parque instance = new Parque(123, 20, "drones", 1000);
        instance.setNumeroMaximo(15);
        assertEquals(numeroMaximo, instance.getNumeroMaximo());
    }

    /**
     * Test of toString method, of class Parque.
     */
    @Test
    public void testToString() {
        Parque instance = new Parque(123, 20, "drones", 1000);
        String expResult = "Parque{id=0, nif=123, numeroMaximo=20, tipo=drones, maxCap=1000}";
        assertEquals(expResult, instance.toString());
    }

    /**
     * Test of getTipo method, of class Parque.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Parque instance = new Parque(123, 20, "drones", 1000);
        String expResult = "drones";
        String result = instance.getTipo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTipo method, of class Parque.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        String expResult = "scooters";
        Parque instance = new Parque(123, 20, "drones", 1000);
        instance.setTipo(expResult);
        assertEquals(expResult, instance.getTipo());
    }

    /**
     * Test of getIdParque method, of class Parque.
     */
    @Test
    public void testGetIdParque() {
        System.out.println("getIdParque");
        Parque instance = new Parque(5, 123, 20, "drones", 1000);
        int expResult = 5;
        int result = instance.getIdParque();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdParque method, of class Parque.
     */
    @Test
    public void testSetIdParque() {
        System.out.println("setIdParque");
        int id = 5;
        Parque instance = new Parque(2, 123, 20, "drones", 1000);
        instance.setIdParque(id);
        assertEquals(id, instance.getIdParque());
    }

    /**
     * Test of getMaxCap method, of class Parque.
     */
    @Test
    public void testGetMaxCap() {
        System.out.println("getMaxCap");
        Parque instance = new Parque(5, 123, 20, "drones", 1000);
        int expResult = 1000;
        int result = instance.getMaxCap();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdParque method, of class Parque.
     */
    @Test
    public void testSetMaxCap() {
        System.out.println("setIdParque");
        int maxCap = 1500;
        Parque instance = new Parque(2, 123, 20, "drones", 1000);
        instance.setMaxCap(maxCap);
        assertEquals(maxCap, instance.getMaxCap());
    }

}
