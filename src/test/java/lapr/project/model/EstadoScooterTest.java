/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tiago
 */
public class EstadoScooterTest {

    @Test
    public void EstadoScooterEmptyConstructorTest() {
        EstadoScooter estado = new EstadoScooter();
        
        assertEquals(estado, estado);
    }

    @Test
    public void EstadoScooterConstructorTest() {
        EstadoScooter estado = new EstadoScooter(1, "Disponível");

        assertEquals(estado,estado);
    }

    /**
     * Test of getDesignacao method, of class EstadoScooter.
     */
    @Test
    public void testGetDesignacao() {
        System.out.println("getDesignacao");
        EstadoScooter instance = new EstadoScooter(1);
        String expResult = "Disponível";
        String result = instance.getDesignacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDesignacao method, of class EstadoScooter.
     */
    @Test
    public void testSetDesignacao() {
        System.out.println("setDesignacao");
        String designacao = "Indisponível";
        EstadoScooter instance = new EstadoScooter(1);
        instance.setDesignacao(designacao);
        assertEquals(designacao,instance.getDesignacao());
    }

    @Test
    public void testgetId() {
        System.out.println("getId");
        EstadoScooter instance = new EstadoScooter(1);
        assertEquals(1,instance.getId());
    }

}
