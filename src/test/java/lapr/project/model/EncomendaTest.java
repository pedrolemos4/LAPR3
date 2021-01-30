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
        int expResult = 1;
        int result = instance.getEstado();
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
        int result = instance.getEstado();
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
        instance.setEstado(2);
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
        Encomenda instance1 = new Encomenda(123456779,13,"01-01-2000",10,10,10, 1);
        boolean expResult = false;
        boolean result = instance.equals(instance1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Encomenda outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,12,"01-01-2000",10,10,10, 1);
        boolean expResult = false;
        boolean result = instance1.equals(outroObjeto);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Encomenda outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,13,"01-02-2000",10,10,10, 1);
        boolean expResult = false;
        boolean result = instance1.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals4");
        Encomenda outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,13,"01-01-2000",13,10,10, 1);
        boolean expResult = false;
        boolean result = instance1.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals5() {
        System.out.println("equals5");
        Encomenda outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,13,"01-01-2000",10,14,10, 1);
        boolean expResult = false;
        boolean result = instance1.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals6() {
        System.out.println("equals6");
        Encomenda outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,13,"01-01-2000",10,10,13, 1);
        boolean expResult = false;
        boolean result = instance1.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals7() {
        System.out.println("equals7");
        Encomenda outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,13,"01-01-2000",10,10,10, 2);
        boolean expResult = false;
        boolean result = instance1.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals8() {
        System.out.println("equals8");
        Encomenda outroObjeto = new Encomenda();
        Encomenda instance1 = new Encomenda(123456789,13,"01-02-2000",10,10,10, 2);
        boolean expResult = false;
        boolean result = instance1.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals9() {
        System.out.println("equals9");
        Encomenda instance1 = new Encomenda(123456789,13,"01-02-2000",10,10,10, 2);
        boolean expResult = false;
        boolean result = instance1.equals(null);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals10() {
        System.out.println("equals10");
        Produto p = new Produto();
        Encomenda instance1 = new Encomenda(123456789,13,"01-02-2000",10,10,10, 2);
        boolean expResult = false;
        boolean result = instance1.equals(p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals11() {
        System.out.println("equals11");
        Object outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,13,"01-02-2000",10,10,10, 1);
        Encomenda o = (Encomenda) outroObjeto;
        boolean expResult = false;
        boolean result = instance1.getDataPedida().equals(o.getDataPedida())
                && instance1.getEstado() == o.getEstado()
                && instance1.getId() == o.getId()
                && instance1.getNif() == o.getNif()
                && instance1.getNifFarmacia() == o.getNifFarmacia()
                && Double.compare(instance1.getPesoEncomenda(), o.getPesoEncomenda()) == 0
                && Double.compare(instance1.getTaxa(), o.getTaxa()) == 0
                && Double.compare(instance1.getPreco(), o.getPreco()) == 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals12() {
        System.out.println("equals12");
        Object outroObjeto = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda instance1 = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda o = (Encomenda) outroObjeto;
        boolean expResult = true;
        boolean result = instance1.getDataPedida().equals(o.getDataPedida())
                && instance1.getEstado() == o.getEstado()
                && instance1.getId() == o.getId()
                && instance1.getNif() == o.getNif()
                && instance1.getNifFarmacia() == o.getNifFarmacia()
                && Double.compare(instance1.getPesoEncomenda(), o.getPesoEncomenda()) == 0
                && Double.compare(instance1.getTaxa(), o.getTaxa()) == 0
                && Double.compare(instance1.getPreco(), o.getPreco()) == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals13() {
        System.out.println("equals13");
        Produto p = new Produto();
        Encomenda instance1 = new Encomenda(123456789,13,"01-02-2000",10,10,10, 2);
        Encomenda instance2 = new Encomenda(123456789,13,"01-12-2000",10,10,10, 1);
        boolean expResult = false;
        boolean result = instance1.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Encomenda.
     */
    @Test
    public void testEquals14() {
        System.out.println("equals14");
        Produto p = new Produto();
        Encomenda instance1 = new Encomenda(123456789,13,"01-02-2000",10,10,10, 2);
        Encomenda instance2 = new Encomenda(123456789,13,"01-12-2000",10,10,10, 1);
        instance1.setId(1);
        instance2.setId(3);
        boolean expResult = false;
        boolean result = instance1.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Encomenda.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Encomenda instance = new Encomenda(123456789,13,"01-01-2000",10,10,10, 1);
        Encomenda expResult = instance;
        assertEquals(expResult.hashCode(), instance.hashCode());
    }

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

    /**
     * Test of toString method, of class Encomenda.
     */
    @Test
    public void testToString1() {
        System.out.println("toString1");
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 2);
        String expResult = "Encomenda{dataPedida=01-01-2000, preco=10.0, pesoEncomenda=10.0, taxa=10.0, nifCliente=123456789, nifFarmacia=12, estado=Entregando, id=0}";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Encomenda.
     */
    @Test
    public void testToString2() {
        System.out.println("toString2");
        Encomenda instance = new Encomenda(123456789,12,"01-01-2000",10,10,10, 3);
        String expResult = "Encomenda{dataPedida=01-01-2000, preco=10.0, pesoEncomenda=10.0, taxa=10.0, nifCliente=123456789, nifFarmacia=12, estado=Entregue, id=0}";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);

    }

}