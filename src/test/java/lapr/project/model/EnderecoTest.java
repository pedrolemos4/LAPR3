package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Tiago
 */
public class EnderecoTest {

    @Test
    void EnderecoEmptyConstructorTest() {
        Endereco instance = new Endereco();

        assertEquals(instance, instance);
    }

    @Test
    void EnderecoConstructorTest() {
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);

        assertEquals(instance, instance);
    }

    /**
     * Test of getMorada method, of class Endereco.
     */
    @Test
    public void testGetMorada() {
        System.out.println("getMorada");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        String expResult = "Rua do ISEP";
        String result = instance.getMorada();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Endereco.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Endereco instance = new Endereco("Rua da FEUP", 43.45, 31.70, 35.23);
        double expResult = 43.45;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLongitude method, of class Endereco.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Endereco instance = new Endereco("Rua da FCUP", 46.76, 34.54, 98.78);
        double expResult = 34.54;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAltitude method, of class Endereco.
     */
    @Test
    public void testGetAltitude() {
        System.out.println("getAltitude");
        Endereco instance = new Endereco("Rua da Portucalense", 55.32, 43.23, 67.97);
        double expResult = 67.97;
        double result = instance.getAltitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMorada method, of class Endereco.
     */
    @Test
    public void testSetMorada() {
        System.out.println("setMorada");
        String morada = "Rua do Estádio";
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        instance.setMorada(morada);
        assertEquals(morada, instance.getMorada());
    }

    /**
     * Test of setLatitude method, of class Endereco.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 54.0;
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        instance.setLatitude(latitude);
        assertEquals(latitude, instance.getLatitude(), 0.0);
    }

    /**
     * Test of setLongitude method, of class Endereco.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 60.0;
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        instance.setLongitude(longitude);
        assertEquals(longitude, instance.getLongitude());
    }

    /**
     * Test of setAltitude method, of class Endereco.
     */
    @Test
    public void testSetAltitude() {
        System.out.println("setAltitude");
        double altitude = 10.2;
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        instance.setAltitude(altitude);
        assertEquals(altitude, instance.getAltitude());
    }

    /**
     * Test of toString method, of class Endereco.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        String expResult = "Endereco{morada=Rua do ISEP, latitude=41.45, longitude=30.58, altitude=34.23}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Endereco instance1 = instance;
        boolean expResult = true;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Endereco instance1 = new Endereco("Rua do ISEPU", 41.45, 30.58, 34.23);
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Endereco instance1 = new Endereco("Rua do ISEP", 41.46, 30.58, 34.23);
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Endereco instance1 = new Endereco("Rua do ISEP", 41.45, 30.59, 34.23);
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals4");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Endereco instance1 = new Endereco("Rua do ISEP", 41.45, 30.58, 34.24);
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals5() {
        System.out.println("equals5");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Endereco instance1 = null;
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals6() {
        System.out.println("equals6");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Object instance1 = new Cliente();
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals7() {
        System.out.println("equals7");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        Endereco instance1 = new Endereco("Rua do ISEP", 41.46, 30.59, 34.24);
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Endereco.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        int expResult = instance.hashCode();
        assertEquals(expResult, instance.hashCode());
    }

    /**
     * Test of hashCode method, of class Endereco.
     */
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Endereco instance = new Endereco("Rua do ISEP", 0, 30.58, 34.23);
        int expResult = instance.hashCode();
        assertEquals(expResult, instance.hashCode());
    }

    /**
     * Test of hashCode method, of class Endereco.
     */
    @Test
    public void testHashCode2() {
        System.out.println("hashCode2");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 0, 34.23);
        int expResult = instance.hashCode();
        assertEquals(expResult, instance.hashCode());
    }

    /**
     * Test of hashCode method, of class Endereco.
     */
    @Test
    public void testHashCode3() {
        System.out.println("hashCode3");
        Endereco instance = new Endereco("Rua do ISEP", 41.45, 30.58, 0);
        int expResult = instance.hashCode();
        assertEquals(expResult, instance.hashCode());
    }

    /**
     * Test of hashCode method, of class Endereco.
     */
    @Test
    public void testHashCode4() {
        System.out.println("hashCode4");
        Endereco instance = new Endereco("Rua do ISEP", 0, 0, 0);
        int expResult = instance.hashCode();
        assertEquals(expResult, instance.hashCode());
    }

    /**
     * Test of hashCode method, of class Endereco.
     */
    @Test
    public void testHashCode5() {
        System.out.println("hashCode5");
        Endereco instance = new Endereco(null, 0, 0, 0);
        int expResult = instance.hashCode();
        assertEquals(expResult, instance.hashCode());
    }
}
