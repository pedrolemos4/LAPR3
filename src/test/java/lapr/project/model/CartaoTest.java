package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class CartaoTest {

    @Test
    void CartaoEmptyConstructorTest(){
        Cartao instance = new Cartao();

        assertEquals(instance,instance);
    }
    
    @Test
    void CartaoConstructorTest(){
        Cartao instance = new Cartao(1, "14-02-2013", 45);
        
        assertEquals(instance,instance);
    }
    
    /**
     * Test of getNumeroCartao method, of class Cartao.
     */
    @Test
    public void testGetNumeroCartao() {
        System.out.println("getNumeroCartao");
        Cartao instance = new Cartao(5,"sdd",5);
        long expResult = 5;
        long result = instance.getNumeroCartao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumeroCartao method, of class Cartao.
     */
    @Test
    public void testSetNumeroCartao() {
        System.out.println("setNumeroCartao");
        long numeroCartao = 6;
        Cartao instance = new Cartao(5,"sdd",5);
        instance.setNumeroCartao(numeroCartao);
        assertEquals(numeroCartao, instance.getNumeroCartao());
    }

    /**
     * Test of getDataDeValidade method, of class Cartao.
     */
    @Test
    public void testGetDataDeValidade() {
        System.out.println("getDataDeValidade");
        Cartao instance = new Cartao(5,"12-12-2001",6);
        String expResult = "12-12-2001";
        String result = instance.getDataDeValidade();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDataDeValidade method, of class Cartao.
     */
    @Test
    public void testSetDataDeValidade() {
        System.out.println("setDataDeValidade");
        String dataDeValidade = "23-11-2007";
        Cartao instance = new Cartao(5,"12-12-2001",6);
        instance.setDataDeValidade(dataDeValidade);
        assertEquals(dataDeValidade, instance.getDataDeValidade());

    }

    /**
     * Test of getCCV method, of class Cartao.
     */
    @Test
    public void testGetCCV() {
        System.out.println("getCCV");
        Cartao instance = new Cartao(5,"12-12-2001",6);
        int expResult = 6;
        int result = instance.getCCV();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCCV method, of class Cartao.
     */
    @Test
    public void testSetCCV() {
        System.out.println("setCCV");
        int CCV = 1;
        Cartao instance = new Cartao(5,"12-12-2001",6);
        instance.setCCV(CCV);
        assertEquals(CCV, instance.getCCV());
    }

    /**
     * Test of toString method, of class Cartao.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cartao instance = new Cartao(5,"12-12-2001",6);
        String expResult = "Cartao{numeroCartao=5, dataDeValidade=12-12-2001, ccv=6}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
