package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReciboTest {

    @Test
    void ReciboEmptyConstructorTest(){
        Recibo instance = new Recibo();

        assertEquals(instance,instance);
    }

    @Test
    void ProdutoConstructorTest(){
        Recibo instance = new Recibo(123,10.2,"data",321);

        assertEquals(instance,instance);
    }

    @Test
    void getNif() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        assertEquals(123,instance.getNif());
    }

    @Test
    void getId() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        assertEquals(0,instance.getId());
    }

    @Test
    void getData() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        assertEquals("data",instance.getData());
    }

    @Test
    void getLst() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        assertEquals(new ArrayList<>(),instance.getLst());
    }

    @Test
    void setNif() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setNif(2);
        assertEquals(2,instance.getNif());
    }

    @Test
    void setId() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setId(2);
        assertEquals(2,instance.getId());
    }

    @Test
    void setData() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setData("dataExpec");
        assertEquals("dataExpec",instance.getData());
    }

    @Test
    void setLst() {
        Recibo instance = new Recibo(123,10.2,"data",321);
        List<Produto> expcLst = new ArrayList<>();
        instance.setLst(expcLst);
        assertEquals(expcLst,instance.getLst());
    }

    /**
     * Test of getPreco method, of class Recibo.
     */
    @org.junit.Test
    public void testGetPreco() {
        System.out.println("getPreco");
        Recibo instance = new Recibo(123,10.2,"data",321);
        double expResult = 10.2;
        double result = instance.getPreco();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPreco method, of class Recibo.
     */
    @org.junit.Test
    public void testSetPreco() {
        System.out.println("setPreco");
        double preco = 0.0;
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setPreco(preco);
    }

    /**
     * Test of getIdEncomenda method, of class Recibo.
     */
    @org.junit.Test
    public void testGetIdEncomenda() {
        System.out.println("getIdEncomenda");
        Recibo instance = new Recibo(123,10.2,"data",321);
        int expResult = 321;
        int result = instance.getIdEncomenda();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Recibo.
     */
    @org.junit.Test
    public void testGetNif() {
        System.out.println("getNif");
        Recibo instance = new Recibo(123,10.2,"data",321);
        int expResult = 123;
        int result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Recibo.
     */
    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        Recibo instance = new Recibo(123,10.2,"data",321);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getData method, of class Recibo.
     */
    @org.junit.Test
    public void testGetData() {
        System.out.println("getData");
        Recibo instance = new Recibo(123,10.2,"data",321);
        String expResult = "data";
        String result = instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLst method, of class Recibo.
     */
    @org.junit.Test
    public void testGetLst() {
        System.out.println("getLst");
        Recibo instance = new Recibo(123,10.2,"data",321);
        List<Produto> expResult = new ArrayList<>();
        List<Produto> result = instance.getLst();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Recibo.
     */
    @org.junit.Test
    public void testSetNif() {
        System.out.println("setNif");
        int nif = 0;
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setNif(nif);
    }

    /**
     * Test of setId method, of class Recibo.
     */
    @org.junit.Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setId(id);
    }

    /**
     * Test of setData method, of class Recibo.
     */
    @org.junit.Test
    public void testSetData() {
        System.out.println("setData");
        String data = "";
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setData(data);
    }

    /**
     * Test of setLst method, of class Recibo.
     */
    @org.junit.Test
    public void testSetLst() {
        System.out.println("setLst");
        List<Produto> lst = null;
        Recibo instance = new Recibo(123,10.2,"data",321);
        instance.setLst(lst);
    }
}