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
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());

        assertEquals(instance,instance);
    }

    @Test
    void getDataPedida() {
        System.out.println("getDataPedida");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
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
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
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
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
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
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
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
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
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
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        double expResult = 10;
        double result = instance.getTaxa();
        assertEquals(expResult, result);
    }

    @Test
    void setTaxa() {
        System.out.println("setTaxa");
        double taxa = 11;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setTaxa(11);
        assertEquals(taxa, instance.getTaxa());
    }

    @Test
    void getEstado() {
        System.out.println("getEstado");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda(1,"encomendado"));
        String expResult = "encomendado";
        String result = instance.getEstado().getDesignacao();
        assertEquals(expResult, result);
    }

    @Test
    void setEstado() {
        System.out.println("setEstado");
        String estado = "entregue";
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setEstado(new EstadoEncomenda(2,"entregue"));
        assertEquals(estado, instance.getEstado().getDesignacao());
    }

    /**
     * Test of getCliente method, of class Encomenda.
     */
    @org.junit.Test
    public void testGetCliente() {
        System.out.println("getCliente");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        Cliente expResult = c;
        Cliente result = instance.getCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLst method, of class Encomenda.
     */
    @org.junit.Test
    public void testGetLst() {
        System.out.println("getLst");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        List<Produto> expResult = lp;
        List<Produto> result = instance.getLst();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDataPedida method, of class Encomenda.
     */
    @org.junit.Test
    public void testGetDataPedida() {
        System.out.println("getDataPedida");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        String expResult = "01-01-2000";
        String result = instance.getDataPedida();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPreco method, of class Encomenda.
     */
    @org.junit.Test
    public void testGetPreco() {
        System.out.println("getPreco");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        double expResult = 10.0;
        double result = instance.getPreco();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPreco method, of class Encomenda.
     */
    @org.junit.Test
    public void testSetPreco() {
        System.out.println("setPreco");
        int preco = 10;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setPreco(preco);
    }

    /**
     * Test of getPesoEncomenda method, of class Encomenda.
     */
    @org.junit.Test
    public void testGetPesoEncomenda() {
        System.out.println("getPesoEncomenda");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        double expResult = 10.0;
        double result = instance.getPesoEncomenda();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLst method, of class Encomenda.
     */
    @org.junit.Test
    public void testSetLst() {
        System.out.println("setLst");
        List<Produto> lst = null;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setLst(lst);
    }

    /**
     * Test of setPesoEncomenda method, of class Encomenda.
     */
    @org.junit.Test
    public void testSetPesoEncomenda() {
        System.out.println("setPesoEncomenda");
        int pesoEncomenda = 0;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setPesoEncomenda(pesoEncomenda);
    }

    /**
     * Test of getTaxa method, of class Encomenda.
     */
    @org.junit.Test
    public void testGetTaxa() {
        System.out.println("getTaxa");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        double expResult = 10.0;
        double result = instance.getTaxa();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTaxa method, of class Encomenda.
     */
    @org.junit.Test
    public void testSetTaxa() {
        System.out.println("setTaxa");
        int taxa = 0;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setTaxa(taxa);
    }

    /**
     * Test of getEstado method, of class Encomenda.
     */
    @org.junit.Test
    public void testGetEstado() {
        System.out.println("getEstado");
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        EstadoEncomenda expResult = new EstadoEncomenda();
        EstadoEncomenda result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEstado method, of class Encomenda.
     */
    @org.junit.Test
    public void testSetEstado() {
        System.out.println("setEstado");
        EstadoEncomenda estado = null;
        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        Cliente c = new Cliente();
        lp.add(p);
        Encomenda instance = new Encomenda(lp, c,"01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setEstado(estado);
    }
}