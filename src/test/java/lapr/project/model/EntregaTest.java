/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class EntregaTest {
    
    @Test
    void EntregaEmptyConstructorTest(){
        Entrega instance = new Entrega();

        assertEquals(instance,instance);
    }

    @Test
    void EntregaConstructorTest(){
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        assertEquals(instance,instance);
    }

    /**
     * Test of getIdEntrega method, of class Entrega.
     */
    @Test
    public void testGetIdEntrega() {
        System.out.println("getIdEntrega");
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        int expResult = 1;
        instance.setIdEntrega(1);
        int result = instance.getIdEntrega();
        assertEquals(expResult, result);

    }

    /**
     * Test of setIdEntrega method, of class Entrega.
     */
    @Test
    public void testSetIdEntrega() {
        System.out.println("setIdEntrega");
        int idEntrega = 8;
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        instance.setIdEntrega(idEntrega);
        assertEquals(idEntrega, instance.getIdEntrega());
    }

    /**
     * Test of getDataInicio method, of class Entrega.
     */
    @Test
    public void testGetDataInicio() {
        System.out.println("getDataInicio");
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        String expResult = "25/12/2252";
        String result = instance.getDataInicio();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDataInicio method, of class Entrega.
     */
    @Test
    public void testSetDataInicio() {
        System.out.println("setDataInicio");
        String dataInicio = "24/12/2252";
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        instance.setDataInicio(dataInicio);
        assertEquals(dataInicio, instance.getDataInicio());
 
    }

    /**
     * Test of getDataFim method, of class Entrega.
     */
    @Test
    public void testGetDataFim() {
        System.out.println("getDataFim");
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        String expResult = "26/12/2252";
        String result = instance.getDataFim();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDataFim method, of class Entrega.
     */
    @Test
    public void testSetDataFim() {
        System.out.println("setDataFim");
        String dataFim = "25/12/2252";
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        instance.setDataFim(dataFim);
        assertEquals(dataFim, instance.getDataFim());
    }

    /**
     * Test of getListEncomendas method, of class Entrega.
     */
    @Test
    public void testGetListEncomendas() {
        System.out.println("getListEncomendas");
        Encomenda enc = new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er"));
        List<Encomenda> expResult = new ArrayList<>();
        expResult.add(enc);
        
        Scooter scooter = new Scooter(87, 56, 48, 96, 1);
        Estafeta estafeta = new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel"));
        List<Encomenda> list = new ArrayList<>();
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", list, scooter, estafeta);
        instance.addListEncomendas(enc);
        List<Encomenda> result = instance.getListEncomendas();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of setListEncomendas method, of class Entrega.
     */
    @Test
    public void testSetListEncomendas() {
        System.out.println("setListEncomendas");
        List<Encomenda> expResult = new ArrayList<>();
        expResult.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        expResult.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(123456789, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        instance.setListEncomendas(expResult);
        assertEquals(expResult, instance.getListEncomendas());
    }

    /**
     * Test of getScooter method, of class Entrega.
     */
    @Test
    public void testGetScooter() {
        System.out.println("getScooter");
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Scooter expResult = new Scooter(87, 56, 48, 96, 1);
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, expResult, new Estafeta(123456789, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        
        Scooter result = instance.getScooter();
        assertEquals(expResult, result);

    }

    /**
     * Test of setScooter method, of class Entrega.
     */
    @Test
    public void testSetScooter() {
        System.out.println("setScooter");
        Scooter scooter = new Scooter(87, 56, 48, 96, 2);
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, scooter, new Estafeta(123456789, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        instance.setScooter(scooter);
        assertEquals(scooter, instance.getScooter());
    }

    /**
     * Test of getEstafeta method, of class Entrega.
     */
    @Test
    public void testGetEstafeta() {
        System.out.println("getEstafeta");
        Estafeta est =new Estafeta(123456789, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel"));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", null, new Scooter(87, 56, 48, 96, 1),est);
        Estafeta expResult =est;
        Estafeta result = instance.getEstafeta();
        assertEquals(expResult, result);

    }

    /**
     * Test of setEstafeta method, of class Entrega.
     */
    @Test
    public void testSetEstafeta() {
        System.out.println("setEstafeta");
        Estafeta estafeta = new Estafeta(987654321, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel"));
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(123456789, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        instance.setEstafeta(estafeta);
        assertEquals(estafeta, instance.getEstafeta());
        
    }

    /**
     * Test of addListEncomendas method, of class Entrega.
     */
    @org.junit.Test
    public void testAddListEncomendas() {
        System.out.println("addListEncomendas");
        Encomenda enc = new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er"));
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda("25/12/2252", 15, 20, 5, new EstadoEncomenda(5, "er")));
        listEncomendas.add(new Encomenda("25/12/2252", 10, 25, 6, new EstadoEncomenda(8, "wd")));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", listEncomendas, new Scooter(87, 56, 48, 96, 1), new Estafeta(123456789, "ddd", "ni", 15, 56, "dd", new EstadoEstafeta(5, "disponivel")));
        
        instance.addListEncomendas(enc);

    }
    
}
