/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author pedro
 */
public class DataTest {
    
    public DataTest() {
    }

    /**
     * Test of getAno method, of class Data.
     */
    @Test
    public void testGetAno() {
        System.out.println("getAno");
        Data instance = new Data("01/01/2021");
        int expResult = 2021;
        int result = instance.getAno();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMes method, of class Data.
     */
    @Test
    public void testGetMes() {
        System.out.println("getMes");
        Data instance = new Data("01/01/2021");
        int expResult = 01;
        int result = instance.getMes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDia method, of class Data.
     */
    @Test
    public void testGetDia() {
        System.out.println("getDia");
        Data instance = new Data("01/01/2021");
        int expResult = 1;
        int result = instance.getDia();
        assertEquals(expResult, result);
    }

    /**
     * Test of toAnoMesDiaString method, of class Data.
     */
    @Test
    public void testToAnoMesDiaString() {
        System.out.println("toAnoMesDiaString");
        Data instance = new Data("01/01/2021");
        String expResult = String.format("%02d/%02d/%04d", instance.getDia(), instance.getMes(), instance.getAno());
        String result = instance.toAnoMesDiaString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Data.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Data outroObjeto = new Data("01/01/2021");
        Data instance = new Data("01/01/2021");
        boolean expResult = true;
        boolean result = instance.equals(outroObjeto);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Data.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Data outraData = new Data("01/01/2021");
        Data instance = new Data("01/01/2021");
        int expResult = 0;
        int result = instance.compareTo(outraData);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMaior method, of class Data.
     */
    @Test
    public void testIsMaior() {
        System.out.println("isMaior");
        Data outraData = new Data("01/01/2021");
        Data instance = new Data("02/01/2021");
        boolean expResult = true;
        boolean result = instance.isMaior(outraData);
        assertEquals(expResult, result);
    }

    /**
     * Test of diferenca method, of class Data.
     */
    @Test
    public void testDiferenca_Data() {
        System.out.println("diferenca");
        Data outraData = new Data("01/01/2021");
        Data instance = new Data("02/01/2021");
        int expResult = 1;
        int result = instance.diferenca(outraData);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAnoBissexto method, of class Data.
     */
    @Test
    public void testIsAnoBissexto() {
        System.out.println("isAnoBissexto");
        int ano = 2020;
        boolean expResult = true;
        boolean result = Data.isAnoBissexto(ano);
        assertEquals(expResult, result);
    }

    /**
     * Test of dataAtual method, of class Data.
     */
    @Test
    public void testDataAtual() {
        System.out.println("dataAtual");
        Data expResult = Data.dataAtual();
        Data result = Data.dataAtual();
        assertEquals(expResult, result);
    }

    /**
     * Test of diferenca method, of class Data.
     */
    @Test
    public void testDiferenca() {
        System.out.println("diferenca");
        Data outraData = new Data("01/01/2021");
        Data instance = new Data("02/01/2021");
        int expResult = 1;
        int result = instance.diferenca(outraData);
        assertEquals(expResult, result);
    }

    /**
     * Test of numeroDeDias method, of class Data.
     */
    @Test
    public void testContaDias() {
        System.out.println("contaDias");
        Data instance = new Data("01/01/2021");
        int expResult = 737791;
        int result = instance.contaDias();
        System.out.println(instance.contaDias());
        assertEquals(expResult, result);
    }
    
}
