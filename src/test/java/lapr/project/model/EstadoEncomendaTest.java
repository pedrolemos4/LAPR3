package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoEncomendaTest {

    @Test
    void EntadoEncomendaEmptyConstructorTest(){
        EstadoEncomenda instance = new EstadoEncomenda();

        assertEquals(instance,instance);
    }

    @Test
    void EntadoEncomendaConstructorTest(){
        EstadoEncomenda instance = new EstadoEncomenda(1,"Entregue");

        assertEquals(instance,instance);
    }

    @Test
    void EntadoEncomenda1ConstructorTest(){
        EstadoEncomenda instance = new EstadoEncomenda(1);

        assertEquals(instance,instance);
    }

    @Test
    void EntadoEncomenda2ConstructorTest(){
        EstadoEncomenda instance = new EstadoEncomenda(2);

        assertEquals(instance,instance);
    }

    @Test
    void EntadoEncomenda3ConstructorTest(){
        EstadoEncomenda instance = new EstadoEncomenda(3);

        assertEquals(instance,instance);
    }

    @Test
    public void testGetDesignacao() {
        System.out.println("getDesignacao");
        EstadoEncomenda instance = new EstadoEncomenda(1,"Entregue");
        String expResult = "Entregue";
        String result = instance.getDesignacao();
        assertEquals(expResult, result);
    }

    @Test
    void setDesignacao() {
        System.out.println("setDesignacao");
        String designacao = "em falta";
        EstadoEncomenda instance = new EstadoEncomenda(2,"Entregando");
        instance.setDesignacao("em falta");
        assertEquals(designacao, instance.getDesignacao());
    }

    /**
     * Test of getIdEstadoEncomenda method, of class EstadoEncomenda.
     */
    @Test
    public void testGetIdEstadoEncomenda() {
        System.out.println("getId_estado_encomenda");
        EstadoEncomenda instance = new EstadoEncomenda(2,"Entregando");
        assertEquals(0, instance.getIdEstadoEncomenda());
        
        EstadoEncomenda instance1 = new EstadoEncomenda(3,"Entregue");
        assertEquals(0, instance1.getIdEstadoEncomenda());

        EstadoEncomenda instance2 = new EstadoEncomenda(1,"Encomendado");
        instance2.setIdEstadoEncomenda(2);
        assertEquals(2, instance2.getIdEstadoEncomenda());
    }


    /**
     * Test of setDesignacao method, of class EstadoEncomenda.
     */
    @Test
    public void testSetDesignacao() {
        System.out.println("setDesignacao");
        String designacao = "";
        EstadoEncomenda instance = new EstadoEncomenda();
        instance.setDesignacao(designacao);
    }

    /**
     * Test of getEstado method, of class EstadoEncomenda.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        EstadoEncomenda instance = new EstadoEncomenda();
        int expResult = 0;
        int result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdEstadoEncomenda method, of class EstadoEncomenda.
     */
    @Test
    public void testSetIdEstadoEncomenda() {
        System.out.println("setIdEstadoEncomenda");
        int idEstadoEncomenda = 0;
        EstadoEncomenda instance = new EstadoEncomenda();
        instance.setIdEstadoEncomenda(idEstadoEncomenda);
    }

    /**
     * Test of setEstado method, of class EstadoEncomenda.
     */
    @Test
    public void testSetEstado() {
        System.out.println("setEstado");
        int estado = 0;
        EstadoEncomenda instance = new EstadoEncomenda();
        instance.setEstado(estado);
    }
}