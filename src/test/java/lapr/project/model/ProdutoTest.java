package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class ProdutoTest {
    
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
        int expResult = 58;
        int result = instance.getPrecoBase();
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
        int precoBase = 56;
        Produto instance = new Produto("sdf", 50, 58);
        instance.setPrecoBase(precoBase);
        assertEquals(precoBase, instance.getPrecoBase());

    }
    
}
