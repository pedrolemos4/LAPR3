package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class ProdutoTest {

    @Test
    void ProdutoEmptyConstructorTest(){
        Produto instance = new Produto();

        assertEquals(instance,instance);
    }

    @Test
    void ProdutoConstructorTest(){
        Produto instance = new Produto("sdf", 50, 58);

        assertEquals(instance,instance);
    }
    
    /**
     * Test of getDesignacao method, of class Produto.
     */
    @Test
    public void testGetDesignacao() {
        System.out.println("getDesignacao");
        Produto instance = new Produto("sdf", 50, 58);
        String expResult = "sdf";
        String result = instance.getDesignacao();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPeso method, of class Produto.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        Produto instance = new Produto("sdf", 50, 58);
        double expResult = 50.0;
        double result = instance.getPeso();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getPrecoBase method, of class Produto.
     */
    @Test
    public void testGetPrecoBase() {
        System.out.println("getPrecoBase");
        Produto instance = new Produto("sdf", 50, 58);
        double expResult = 58;
        double result = instance.getPrecoBase();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDesignacao method, of class Produto.
     */
    @Test
    public void testSetDesignacao() {
        System.out.println("setDesignacao");
        String designacao = "df";
        Produto instance = new Produto("sdf", 50, 58);
        instance.setDesignacao(designacao);
        assertEquals(designacao, instance.getDesignacao());
    }

    /**
     * Test of setPeso method, of class Produto.
     */
    @Test
    public void testSetPeso() {
        System.out.println("setPeso");
        double peso = 25.0;
        Produto instance = new Produto("sdf", 50, 58);
        instance.setPeso(peso);
        assertEquals(peso, instance.getPeso());
    }

    /**
     * Test of setPrecoBase method, of class Produto.
     */
    @Test
    public void testSetPrecoBase() {
        System.out.println("setPrecoBase");
        double precoBase = 56;
        Produto instance = new Produto("sdf", 50, 58);
        instance.setPrecoBase(precoBase);
        assertEquals(precoBase, instance.getPrecoBase());

    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        Produto instance = new Produto("sdf", 50, 58);
        double expResult = 0;
        double result = instance.getId();
        assertEquals(expResult, result);

    }

    @Test
    public void testSetId() {
        System.out.println("setsetId");
        int newid = 56;
        Produto instance = new Produto("sdf", 50, 58);
        instance.setId(newid);
        assertEquals(newid, instance.getId());

    }

    /**
     * Test of toString method, of class Produto.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Produto instance = new Produto("sdf", 50, 58);
        String expResult = "Produto{id=0, designacao=sdf, peso=50.0, precoBase=58.0}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }


    /**
     * Test of hashCode method, of class Produto.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Produto instance = new Produto();
        int expResult = 7;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Produto.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Produto(1, "df", 32, 32);
        Produto instance = new Produto(4, "dfv", 32, 65);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Produto.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Object obj = new Produto(1, "df", 32, 32);
        Produto instance = new Produto(1, "df", 32, 32);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Produto.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Object obj = new Administrador();
        Produto instance = new Produto(1, "df", 32, 32);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Produto.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Object obj = null;
        Produto instance = new Produto(1, "df", 32, 32);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
}
