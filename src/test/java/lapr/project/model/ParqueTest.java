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
        Parque instance = new Parque(123, "algures", 20);
        assertEquals(instance, instance);
    }

    /**
     * Test of getNIF method, of class Parque.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Parque instance = new Parque(111111111, "algures", 20);
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
        Parque instance = new Parque(112221112, "algures", 20);
        instance.setNIF(NIF);
        assertEquals(NIF, instance.getNIF());
    }

    /**
     * Test of getMorada method, of class Parque.
     */
    @Test
    public void testGetMorada() {
        System.out.println("getMorada");
        Parque instance = new Parque(123, "algures", 20);
        String expResult = "algures";
        String result = instance.getMorada();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMorada method, of class Parque.
     */
    @Test
    public void testSetMorada() {
        System.out.println("setMorada");
        String morada = "Rua do Estádio";
        Parque instance = new Parque(123, "algures", 20);
        instance.setMorada(morada);
        assertEquals(morada, instance.getMorada());
    }

    /**
     * Test of getNumeroMaximo method, of class Parque.
     */
    @Test
    void getNumeroMaximo() {
        System.out.println("getNumeroMaximo");
        Parque instance = new Parque(123, "algures", 20);
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
        Parque instance = new Parque(123, "algures", 20);
        instance.setNumeroMaximo(15);
        assertEquals(numeroMaximo, instance.getNumeroMaximo());
    }

    @Test
    public void testToString(){
        Parque instance = new Parque(123, "algures", 20);
        String expResult = instance.toString();
        assertEquals(expResult,instance.toString());
    }
}
