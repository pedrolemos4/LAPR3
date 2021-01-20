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
    
    private Entrega entrega = new Entrega("25/12/2252", "26/12/2252", 1, 2);
    @Test
    void EntregaEmptyConstructorTest(){
        Entrega instance = new Entrega();

        assertEquals(instance,instance);
    }

    @Test
    void EntregaConstructorTest(){
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 1);
        
        assertEquals(instance,instance);
    }

    /**
     * Test of getIdEntrega method, of class Entrega.
     */
    @Test
    public void testGetIdEntrega() {
        System.out.println("getIdEntrega");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
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
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
        instance.setIdEntrega(idEntrega);
        assertEquals(idEntrega, instance.getIdEntrega());
    }

    /**
     * Test of getDataInicio method, of class Entrega.
     */
    @Test
    public void testGetDataInicio() {
        System.out.println("getDataInicio");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
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
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
        instance.setDataInicio(dataInicio);
        assertEquals(dataInicio, instance.getDataInicio());
 
    }

    /**
     * Test of getDataFim method, of class Entrega.
     */
    @Test
    public void testGetDataFim() {
        System.out.println("getDataFim");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
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
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
        instance.setDataFim(dataFim);
        assertEquals(dataFim, instance.getDataFim());
    }

    /**
     * Test of getListEncomendas method, of class Entrega.
     */
    @Test
    public void testGetListEncomendas() {
        System.out.println("getListEncomendas");
        List<Encomenda> expResult = new ArrayList<>();
        Encomenda e = new Encomenda(2,12,"25/12/2252", 15, 20, 5, 1);
        expResult.add(e);
        
        List<Encomenda> list = new ArrayList<>();
        list.add(e);
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);

        instance.setListEncomendas(list);
        
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
        expResult.add(new Encomenda(2,12,"25/12/2252", 15, 20, 5, 1));
        expResult.add(new Encomenda(3,13,"25/12/2252", 10, 25, 6, 2));
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
        instance.setListEncomendas(expResult);
        assertEquals(expResult, instance.getListEncomendas());
    }

    /**
     * Test of getVeiculo method, of class Entrega.
     */
    @Test
    public void testGetIdVeiculo() {
        System.out.println("getIdVeiculo");
        int expResult = 1;
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
        
        int result = instance.getIdVeiculo();
        assertEquals(expResult, result);

    }

    /**
     * Test of setVeiculo method, of class Entrega.
     */
    @Test
    public void testSetIdVeiculo() {
        System.out.println("setIdVeiculo");
        int veiculo = 2;
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2);
        
        instance.setVeiculo(veiculo);
        assertEquals(veiculo, instance.getIdVeiculo());
    }

    /**
     * Test of getEstafeta method, of class Entrega.
     */
    @Test
    public void testGetIdEstafeta() {
        System.out.println("getIdEstafeta");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 1);
        int expResult = 1;
        int result = instance.getidEstafeta();
        assertEquals(expResult, result);

    }

    /**
     * Test of setEstafeta method, of class Entrega.
     */
    @Test
    public void testSetIdEstafeta() {
        System.out.println("setIdEstafeta");
        int estafeta = 2;
        Entrega instance = new Entrega("25/12/2252", "26/12/2252",1, 1);
        
        instance.setEstafeta(estafeta);
        assertEquals(estafeta, instance.getidEstafeta());
        
    }

    
}
