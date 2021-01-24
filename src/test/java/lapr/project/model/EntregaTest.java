/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class EntregaTest {
    
    private Entrega entrega = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    void EntregaEmptyConstructorTest(){
        Entrega instance = new Entrega();

        assertEquals(instance,instance);
    }

    @Test
    void EntregaConstructorTest(){
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 1, 2);
        
        assertEquals(instance,instance);
    }

    /**
     * Test of getIdEntrega method, of class Entrega.
     */
    @Test
    public void testGetIdEntrega() {
        System.out.println("getIdEntrega");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);
        
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
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);
        
        instance.setIdEntrega(idEntrega);
        assertEquals(idEntrega, instance.getIdEntrega());
    }

    /**
     * Test of getDataInicio method, of class Entrega.
     */
    @Test
    public void testGetDataInicio() {
        System.out.println("getDataInicio");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);
        
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
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);
        
        instance.setDataInicio(dataInicio);
        assertEquals(dataInicio, instance.getDataInicio());
 
    }

    /**
     * Test of getDataFim method, of class Entrega.
     */
    @Test
    public void testGetDataFim() {
        System.out.println("getDataFim");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 1);
        
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
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);
        
        instance.setDataFim(dataFim);
        assertEquals(dataFim, instance.getDataFim());
    }




    /**
     * Test of getVeiculo method, of class Entrega.
     */
    @Test
    public void testGetIdVeiculo() {
        System.out.println("getIdVeiculo");
        int expResult = 1;
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);
        
        
        int result = instance.getIdVeiculo();
        assertEquals(expResult, result);

    }

    /**
     * Test of setVeiculo method, of class Entrega.
     */
    @Test
    public void testSetIdVeiculo() {
        System.out.println("setIdVeiculo");
        int veiculo = 3;
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 2, 2);
        
        instance.setIdVeiculo(veiculo);
        assertEquals(veiculo, instance.getIdVeiculo());
    }

    /**
     * Test of getEstafeta method, of class Entrega.
     */
    @Test
    public void testGetIdEstafeta() {
        System.out.println("getIdEstafeta");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252", 1, 1, 2);
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
        Entrega instance = new Entrega("25/12/2252", "26/12/2252",1, 1, 2);
        
        instance.setEstafeta(estafeta);
        assertEquals(estafeta, instance.getidEstafeta());
        
    }

    /**
     * Test of toString method, of class Entrega.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252",1, 1, 2);
        String expResult = "Entrega{idEntrega=0, dataInicio=25/12/2252, dataFim=26/12/2252, idVeiculo=1, idEstafeta=1, pesoEntrega=2.0}";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);

    }


    /**
     * Test of getPesoEntrega method, of class Entrega.
     */
    @Test
    public void testGetPesoEntrega() {
        System.out.println("getPesoEntrega");
        Entrega instance = new Entrega("25/12/2252", "26/12/2252",1, 1, 2);
        double expResult = 2.0;
        double result = instance.getPesoEntrega();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setPesoEntrega method, of class Entrega.
     */
    @Test
    public void testSetPesoEntrega() {
        System.out.println("setPesoEntrega");
        double pesoEntrega = 7.0;
        Entrega instance = new Entrega("25/12/2252", "26/12/2252",1, 1, 2);
        instance.setPesoEntrega(pesoEntrega);

    }

    
}
