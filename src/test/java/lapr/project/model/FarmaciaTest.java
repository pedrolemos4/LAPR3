package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Farmacia instance = new Farmacia(123456789,"email1", "rua1");
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
     * Test of getStock method, of class Farmacia.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        Farmacia instance = new Farmacia(123456789,"email","rua2");
        List<Produto> expResult = instance.getStock();
        List<Produto> result = instance.getStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of addStock method, of class Farmacia.
     */
    @Test
    public void testAddStock() {
        System.out.println("addStock");
        Farmacia instance = new Farmacia(123456789,"email1","rua3");
        Produto p = new Produto("sdf", 50, 58);

        List<Produto> expResult = new ArrayList<>();
        expResult.add(p);
        instance.addStock(p);
        List<Produto> result = instance.getStock();

        assertEquals(expResult, result);
    }

    /**
     * Test of getMorada method, of class Farmacia.
     */
    @Test
    void getEmail() {
        System.out.println("getEmail");
        Farmacia instance = new Farmacia(123456789, "email1","rua1");
        String expResult = "email1";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMorada method, of class Farmacia.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "email2";
        Farmacia instance = new Farmacia();
        instance.setEmail("email2");
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getMorada method, of class Farmacia.
     */
    @Test
    void getMorada() {
        System.out.println("getNif");
        Farmacia instance = new Farmacia(123456789, "email","rua1");
        String expResult = "rua1";
        String result = instance.getMorada();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMorada method, of class Farmacia.
     */
    @Test
    public void testSetMorada() {
        System.out.println("setMorada");
        String morada = "rua32";
        Farmacia instance = new Farmacia();
        instance.setMorada(morada);
        assertEquals(morada, instance.getMorada());
    }

    /**
     * Test of toString method, of class Farmacia.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Farmacia instance = new Farmacia(123456789,"email" ,"rua4");
        String expResult = "Farmacia{nif=123456789, morada=rua4}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
