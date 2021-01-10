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
        EstadoEncomenda instance = new EstadoEncomenda(1,"entregue");

        assertEquals(instance,instance);
    }

    @Test
    void getDesignacao() {
        System.out.println("getDesignacao");
        EstadoEncomenda instance = new EstadoEncomenda(1,"entregue");
        String expResult = "entregue";
        String result = instance.getDesignacao();
        assertEquals(expResult, result);
    }

    @Test
    void setDesignacao() {
        System.out.println("setDesignacao");
        String designacao = "em falta";
        EstadoEncomenda instance = new EstadoEncomenda(2,"entregue");
        instance.setDesignacao("em falta");
        assertEquals(designacao, instance.getDesignacao());
    }

    @Test
    void getId_estado_encomenda(){
        System.out.println("getId_estado_encomenda");
        EstadoEncomenda instance = new EstadoEncomenda(2,"entregue");
        assertEquals(2, instance.getId_estado_encomenda());
    }
}