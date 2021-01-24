package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncomendaTest {

    @Test
    void EncomendaConstructorTest(){
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);

        assertEquals(instance,instance);
    }

    @Test
    void getDataPedida() {
        System.out.println("getDataPedida");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        String expResult = "01-01-2000";
        String result = instance.getDataPedida();
        assertEquals(expResult, result);
    }

    @Test
    void getPreco() {
        System.out.println("getPreco");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10,1);
        double expResult = 10;
        double result = instance.getPreco();
        assertEquals(expResult, result);
    }

    @Test
    void setPreco() {
        System.out.println("setPreco");
        double preco = 11;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        instance.setPreco(11);
        assertEquals(preco, instance.getPreco());
    }

    @Test
    void getPesoEncomenda() {
        System.out.println("getPesoEncomenda");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        double expResult = 10;
        double result = instance.getPesoEncomenda();
        assertEquals(expResult, result);
    }

    @Test
    void setPesoEncomenda() {
        System.out.println("setPesoEncomenda");
        double peso = 11;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        instance.setPesoEncomenda(11);
        assertEquals(peso, instance.getPesoEncomenda());
    }

    @Test
    void getTaxa() {
        System.out.println("getTaxa");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda( c.getNIF(),12,"01-01-2000",10,10,10, 1);
        double expResult = 10;
        double result = instance.getTaxa();
        assertEquals(expResult, result);
    }
    
    @Test
    public void setTaxa() {
        System.out.println("getTaxa");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda( c.getNIF(),12,"01-01-2000",10,10,10, 1);
        double expResult = 10;
        double result = instance.getTaxa();
        assertEquals(expResult, result);
    }

    @Test
    public void getEstado() {
        System.out.println("getEstado");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        EstadoEncomenda e = new EstadoEncomenda(1);
        int expResult = 1;
        int result = instance.getEstado().getEstado();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCliente method, of class Encomenda.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente(123,"Teste","@",1234,12,"Ali",123,"pass");
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        int expResult = c.getNIF();
        int result = instance.getNif();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDataPedida method, of class Encomenda.
     */
    @Test
    public void testGetDataPedida() {
        System.out.println("getDataPedida");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        String expResult = "01-01-2000";
        String result = instance.getDataPedida();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPreco method, of class Encomenda.
     */
    @Test
    public void testGetPreco() {
        System.out.println("getPreco");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        double expResult = 10.0;
        double result = instance.getPreco();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPreco method, of class Encomenda.
     */
    @Test
    public void testSetPreco() {
        System.out.println("setPreco");
        int preco = 10;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10,1);
        instance.setPreco(preco);
    }

    /**
     * Test of getPesoEncomenda method, of class Encomenda.
     */
    @Test
    public void testGetPesoEncomenda() {
        System.out.println("getPesoEncomenda");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        double expResult = 10.0;
        double result = instance.getPesoEncomenda();
        assertEquals(expResult, result, 0.0);
    }
    /**
     * Test of setPesoEncomenda method, of class Encomenda.
     */
    @Test
    public void testSetPesoEncomenda() {
        System.out.println("setPesoEncomenda");
        int pesoEncomenda = 0;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        instance.setPesoEncomenda(pesoEncomenda);
    }

    /**
     * Test of getTaxa method, of class Encomenda.
     */
    @Test
    public void testGetTaxa() {
        System.out.println("getTaxa");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        double expResult = 10.0;
        double result = instance.getTaxa();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTaxa method, of class Encomenda.
     */
    @Test
    public void testSetTaxa() {
        System.out.println("setTaxa");
        int taxa = 0;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        instance.setTaxa(taxa);
    }

    @Test
    public void testSetNif() {
        System.out.println("setNif");
        int nif = 0;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(c.getNIF(),12,"01-01-2000",10,10,10, 1);
        instance.setNif(nif);
    }

    /**
     * Test of getId method, of class Encomenda.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        int expResult = 1;
        instance.setId(expResult);
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class Encomenda.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 2;
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getEstado method, of class Encomenda.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);;
        int expResult = 1;
        int result = instance.getEstado().getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEstado method, of class Encomenda.
     */
    @Test
    public void testSetEstado() {
        System.out.println("setEstado");
        int estado = 0;
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        EstadoEncomenda e = new EstadoEncomenda(estado,"");
        instance.setEstado(e);
    }

    /**
     * Test of getNifFarmacia method, of class Encomenda.
     */
    @Test
    public void testGetNifFarmacia() {
        System.out.println("getNifFarmacia");
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        int expResult = 12;
        assertEquals(expResult, instance.getNifFarmacia());
    }

    /**
     * Test of setNifFarmacia method, of class Encomenda.
     */
    @Test
    public void testSetNifFarmacia() {
        System.out.println("setNifFarmacia");
        int nifFarmacia = 0;
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        instance.setNifFarmacia(nifFarmacia);
    }

    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = instance;
        boolean expResult = true;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Encomenda instance = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456779,12,"01-01-2000",10,10,10, 2);
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of hashCode method, of class Encomenda.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Encomenda instance = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
//        int expResult =  654590372;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//
//    }

    /**
     * Test of toString method, of class Encomenda.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        String expResult = "Encomenda{dataPedida=01-01-2000, preco=10.0, pesoEncomenda=10.0, taxa=10.0, nifCliente=123456789, nifFarmacia=12, estado=Encomendado, id=0}";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);

    }

}