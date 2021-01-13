package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstacionamentoTest {

    @Test
    void EstacionamentoEmptyConstructorTest() {
        Estacionamento instance = new Estacionamento();

        assertEquals(instance, instance);
    }

    @Test
    void EstacionamentoConstructorTest() {
        Estacionamento instance = new Estacionamento(1, 0, 0);
        assertEquals(instance, instance);
    }

    /**
     * Test of getNumeroLote method, of class Estacionamento.
     */
    @Test
    void getNumeroLote() {
        System.out.println("getNumeroLote");
        Estacionamento instance = new Estacionamento(1, 0, 0);
        int expResult = 1;
        int result = instance.getNumeroLote();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumeroLote method, of class Estacionamento.
     */
    @Test
    void setNumeroLote() {
        System.out.println("setNumeroLote");
        int numeroLote = 15;
        Estacionamento instance = new Estacionamento(1, 0, 0);
        instance.setNumeroLote(15);
        assertEquals(numeroLote, instance.getNumeroLote());
    }

    /**
     * Test of getCarregador method, of class Estacionamento.
     */
    @Test
    void getCarregador() {
        System.out.println("getCarregador");
        Estacionamento instance = new Estacionamento(1, 0, 0);
        int expResult = 0;
        int result = instance.getCarregador();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCarregador method, of class Estacionamento.
     */
    @Test
    void setCarregador() {
        System.out.println("setCarregador");
        int carregador = 1;
        Estacionamento instance = new Estacionamento(1, 0, 0);
        instance.setCarregador(1);
        assertEquals(carregador, instance.getCarregador());
    }

    /**
     * Test of getNif method, of class Estacionamento.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Estacionamento instance = new Estacionamento(1, 0, 546456);
        int expResult = 546456;
        int result = instance.getNIF();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNif method, of class Estacionamento.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        int nif = 987654321;
        Estacionamento instance = new Estacionamento(1, 0, 546456);
        instance.setNIF(nif);
        assertEquals(nif, instance.getNIF());

    }

    /**
     * Test of toString method, of class Estacionamento.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Estacionamento instance = new Estacionamento(1, 0, 546456);
        String expResult = "Estacionamento{" + "numeroLote=" + 1 + ", carregador=" + 0 + ", nif=" + 546456 + '}';;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
