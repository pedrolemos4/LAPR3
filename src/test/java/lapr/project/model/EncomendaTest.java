package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncomendaTest {

    @Test
    void EncomendaConstructorTest(){
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());

        assertEquals(instance,instance);
    }

    @Test
    void getDataPedida() {
        System.out.println("getDataPedida");
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        String expResult = "01-01-2000";
        String result = instance.getDataPedida();
        assertEquals(expResult, result);
    }

    @Test
    void setDataPedida() {
        System.out.println("setDataPedida");
        String data = "01-01-2001";
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setDataPedida("01-01-2001");
        assertEquals(data, instance.getDataPedida());
    }

    @Test
    void getPreco() {
        System.out.println("getPreco");
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        double expResult = 10;
        double result = instance.getPreco();
        assertEquals(expResult, result);
    }

    @Test
    void setPreco() {
        System.out.println("setPreco");
        double preco = 11;
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setPreco(11);
        assertEquals(preco, instance.getPreco());
    }

    @Test
    void getPesoEncomenda() {
        System.out.println("getPesoEncomenda");
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        double expResult = 10;
        double result = instance.getPesoEncomenda();
        assertEquals(expResult, result);
    }

    @Test
    void setPesoEncomenda() {
        System.out.println("setPesoEncomenda");
        double peso = 11;
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setPesoEncomenda(11);
        assertEquals(peso, instance.getPesoEncomenda());
    }

    @Test
    void getTaxa() {
        System.out.println("getTaxa");
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        double expResult = 10;
        double result = instance.getTaxa();
        assertEquals(expResult, result);
    }

    @Test
    void setTaxa() {
        System.out.println("setTaxa");
        double taxa = 11;
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda());
        instance.setTaxa(11);
        assertEquals(taxa, instance.getTaxa());
    }

    @Test
    void getEstado() {
        System.out.println("getEstado");
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda(1,"encomendado"));
        String expResult = "encomendado";
        String result = instance.getEstado().getDesignacao();
        assertEquals(expResult, result);
    }

    @Test
    void setEstado() {
        System.out.println("setEstado");
        String estado = "entregue";
        Encomenda instance = new Encomenda("01-01-2000",10,10,10, new EstadoEncomenda(2,"cancelada"));
        instance.setEstado(new EstadoEncomenda(2,"entregue"));
        assertEquals(estado, instance.getEstado().getDesignacao());
    }
}