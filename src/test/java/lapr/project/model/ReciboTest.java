package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReciboTest {

    private Recibo instance;

    @Test
    public void ReciboEmptyConstructorTest() {
        instance = new Recibo();

        assertEquals(instance, instance);
    }

    @Test
    void ProdutoConstructorTest() {
        Recibo instance = new Recibo(123, 10.2, "data", 321);

        assertEquals(instance, instance);
    }

    @Test
    void getNif() {
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        assertEquals(123, instance.getNif());
    }

    @Test
    void getId() {
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        assertEquals(0, instance.getId());
    }

    @Test
    void getData() {
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        assertEquals("data", instance.getData());
    }

    @Test
    void setNif() {
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        instance.setNif(2);
        assertEquals(2, instance.getNif());
    }

    @Test
    void setId() {
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        instance.setId(2);
        assertEquals(2, instance.getId());
    }

    @Test
    void setData() {
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        instance.setData("dataExpec");
        assertEquals("dataExpec", instance.getData());
    }

    /**
     * Test of getPreco method, of class Recibo.
     */
    @Test
    public void testGetPreco() {
        System.out.println("getPreco");
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        double expResult = 10.2;
        double result = instance.getPreco();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPreco method, of class Recibo.
     */
    @Test
    public void testSetPreco() {
        System.out.println("setPreco");
        double preco = 0.0;
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        instance.setPreco(preco);
    }

    /**
     * Test of getIdEncomenda method, of class Recibo.
     */
    @Test
    public void testGetIdEncomenda() {
        System.out.println("getIdEncomenda");
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        int expResult = 321;
        int result = instance.getIdEncomenda();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Recibo.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        int expResult = 123;
        int result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Recibo.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getData method, of class Recibo.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        String expResult = "data";
        String result = instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Recibo.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        int nif = 0;
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        instance.setNif(nif);
    }

    /**
     * Test of setId method, of class Recibo.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        instance.setId(id);
    }

    /**
     * Test of setData method, of class Recibo.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        String data = "";
        Recibo instance = new Recibo(123, 10.2, "data", 321);
        instance.setData(data);
    }

    /**
     * Test of getMap method, of class Recibo.
     */
    @Test
    public void testGetMap() {
        System.out.println("getMap");
        Recibo instance = new Recibo();
        Map<Produto, Integer> expResult = new HashMap<>();
        assertEquals(expResult, instance.getMap());
    }

    /**
     * Test of setLst method, of class Recibo.
     */
    @Test
    public void testSetLst() {
        System.out.println("setLst");
        Map<Produto, Integer> mapa = new HashMap<>();
        Recibo instance = new Recibo();
        instance.setLst(mapa);
    }

    /**
     * Test of toString method, of class Recibo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Recibo instance1 = new Recibo();
        String expResult = instance1.toString();
        Recibo instance2 = new Recibo();
        String result = instance2.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Recibo.
     */
    @Test
    public void testToString1() {
        System.out.println("toString1");
        StringBuilder s = new StringBuilder();
        String expResult = "Recibo{nif=12, id=0, preco=20.0, data=01/01/2001, idEncomenda=1";
        Recibo instance = new Recibo(12,20.0,"01/01/2001",1);
        s.append("Recibo{").append("nif=").append(instance.getNif()).append(", id=").append(instance.getId()).append(", preco=").append(instance.getPreco()).append(", data=").append(instance.getData()).append(", idEncomenda=").append(instance.getIdEncomenda());
        s.append(", produto=").append(new Produto(1,"produto",12,15)).append("quantidade=").append(5).append('}');
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
