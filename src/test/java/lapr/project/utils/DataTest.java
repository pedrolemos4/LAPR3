/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Produto;
import org.junit.jupiter.api.Test;

import static lapr.project.utils.Data.*;
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
     * Test of compareTo method, of class Data.
     */
    @Test
    public void testCompareTo1() {
        System.out.println("compareTo1");
        Data outraData = new Data("01/01/2021");
        Data instance = new Data("02/01/2021");
        int expResult = 1;
        int result = instance.compareTo(outraData);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of compareTo method, of class Data.
     */
    @Test
    public void testCompareTo2() {
        System.out.println("compareTo2");
        Data outraData = new Data("02/01/2021");
        Data instance = new Data("01/01/2021");
        int expResult = -1;
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
        boolean result = isAnoBissexto(ano);
        assertEquals(expResult, result);
    }

    /**
     * Test of dataAtual method, of class Data.
     */
    @Test
    public void testDataAtual() {
        System.out.println("dataAtual");
        Data expResult = dataAtual();
        Data result = dataAtual();
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
        assertEquals(expResult, result);
    }

    @Test
    public void testContaDias1() {
        System.out.println("contaDias1");
        Data instance = new Data(2021,Mes.MARCO,10);
        int expResult = 737859;
        int result = instance.contaDias();
        assertEquals(expResult, result);
    }

    @Test
    public void testConstructorOutraData(){
        Data outraData = new Data(2020, Mes.JANEIRO,12);
        Data instance = new Data(outraData);
        assertEquals(outraData,instance);
    }


    @Test
    void numeroDeDiasJaneiro() {
        assertEquals(31,Mes.JANEIRO.numeroDeDias(2019));
    }

    @Test
    void numeroDeDiasFevereiroBissexto() {
        assertEquals(29,Mes.FEVEREIRO.numeroDeDias(2020));
    }

    @Test
    void numeroDeDiasFevereiroNaoBissexto() {
        assertEquals(28,Mes.FEVEREIRO.numeroDeDias(2019));
    }

    @Test
    void obterMes() {
        assertEquals(Mes.MARCO,Mes.obterMes(3));
    }

    @Test
    void valueOf() {
        assertEquals(Mes.JANEIRO,Mes.valueOf("JANEIRO"));
    }

    @Test
    void hashCodeTest() {
        Data data = new Data(1,Mes.JANEIRO,2020);
        Data dataExpected = new Data(1,Mes.JANEIRO,2020);

        assertEquals(dataExpected.hashCode(),data.hashCode());
    }

    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Data outroObjeto = new Data("02/01/2021");
        Data instance = new Data("01/01/2021");
        boolean expResult = false;
        boolean result = instance.equals(outroObjeto);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Data instance = new Data("01/01/2021");
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Data instance = new Data("01/01/2021");
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals4() {
        System.out.println("equals4");
        Produto outroObjeto = new Produto();
        Data instance = new Data("01/01/2021");
        boolean expResult = false;
        boolean result = instance.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void simpleEnumTest(){
        System.out.println("simpleEnumTest");
        Mes mes = Mes.JANEIRO;
        assertEquals("Janeiro", mes.toString());
        Mes mes1 = Mes.FEVEREIRO;
        assertEquals("Fevereiro", mes1.toString());
        Mes mes2 = Mes.MARCO;
        assertEquals("Março", mes2.toString());
        Mes mes3 = Mes.ABRIL;
        assertEquals("Abril", mes3.toString());
        Mes mes4 = Mes.MAIO;
        assertEquals("Maio", mes4.toString());
        Mes mes5 = Mes.JUNHO;
        assertEquals("Junho", mes5.toString());
        Mes mes6 = Mes.JULHO;
        assertEquals("Julho", mes6.toString());
        Mes mes7 = Mes.AGOSTO;
        assertEquals("Agosto", mes7.toString());
        Mes mes8 = Mes.SETEMBRO;
        assertEquals("Setembro", mes8.toString());
        Mes mes9 = Mes.OUTUBRO;
        assertEquals("Outubro", mes9.toString());
        Mes mes10 = Mes.NOVEMBRO;
        assertEquals("Novembro", mes10.toString());
        Mes mes11 = Mes.DEZEMBRO;
        assertEquals("Dezembro", mes11.toString());
    }
}
