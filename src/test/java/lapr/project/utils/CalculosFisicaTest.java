/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author beatr
 */
public class CalculosFisicaTest {
    

    /**
     * Test of calculoEnergia method, of class CalculosFisica.
     */
    @Test
    public void testCalculoEnergia() {
        System.out.println("calculoEnergia");
        double pesoEstafeta = 12.0;
        double pesoScooter = 12.0;
        double areaFrontal = 4.0;
        double pesoEncomenda = 3.0;
        Endereco end1 = new Endereco("vfve", 56, 78, 0);
        Endereco end2 = new Endereco("tg", 89, 52, 0);
        double expResult = 1174935.6146757996;
        double result = CalculosFisica.calculoEnergia(pesoEstafeta, pesoScooter, areaFrontal, pesoEncomenda, end1, end2);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculoForcaTotal method, of class CalculosFisica.
     */
    @Test
    public void testCalculoForcaTotal() {
        System.out.println("calculoForcaTotal");
        double pesoTotal = 10.0;
        double areaFrontal = 5.0;
        double expResult = 164.959660528;
        double result = CalculosFisica.calculoForcaTotal(pesoTotal, areaFrontal);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoRoad_slope method, of class CalculosFisica.
     */
    @Test
    public void testCalculoRoad_slope() {
        System.out.println("calculoRoad_slope");
        double pesoTotal = 5.0;
        double expResult = 49.0;
        double result = CalculosFisica.calculoRoad_slope(pesoTotal);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoRoad_load method, of class CalculosFisica.
     */
    @Test
    public void testCalculoRoad_load() {
        System.out.println("calculoRoad_load");
        double pesoTotal = 10.0;
        double expResult = 0.5389999999999999;
        double result = CalculosFisica.calculoRoad_load(pesoTotal);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoAerodynamic_drag_force method, of class CalculosFisica.
     */
    @Test
    public void testCalculoAerodynamic_drag_force() {
        System.out.println("calculoAerodynamic_drag_force");
        double areaFrontal = 10.0;
        double expResult = 132.841321056;
        double result = CalculosFisica.calculoAerodynamic_drag_force(areaFrontal);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoDistancia method, of class CalculosFisica.
     */
    @Test
    public void testCalculoDistancia() {
        System.out.println("calculoDistancia");
        Endereco end1 = new Endereco("vfve", 56, 78, 0);
        Endereco end2 = new Endereco("tg", 89, 52, 0);
        double expResult = 3680.970219328289;
        double result = CalculosFisica.calculoDistancia(end1, end2);
        assertEquals(expResult, result, 0.0);

    }
    
}
