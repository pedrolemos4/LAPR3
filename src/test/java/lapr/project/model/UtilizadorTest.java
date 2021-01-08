/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author Tiago
 */
public class UtilizadorTest {

    @Test
    void UtilizadorEmptyConstructorTest(){
        Utilizador instance = new Utilizador();

        assertEquals(instance,instance);
    }

    @Test
    void UtilizadorConstructorTest(){
        Utilizador instance = new Utilizador(111111111,"Ronaldo","email",112323232,"password");

        assertEquals(instance,instance);
    }

    /**
     * Test of getNIF method, of class Utilizador.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Utilizador instance = new Utilizador(111111111,"Ronaldo","email",112323232,"password");
        int expResult = 111111111;
        int result = instance.getNIF();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNIF method, of class Utilizador.
     */
    @Test
    public void testSetNIF() {
        System.out.println("setNIF");
        int NIF = 112221112;
        Utilizador instance = new Utilizador(222222222,"Messi","email1",324232313,"password1");
        instance.setNIF(NIF);
        assertEquals(NIF,instance.getNIF());
    }

    /**
     * Test of getNome method, of class Utilizador.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Utilizador instance = new Utilizador(333333333,"Arnaldo","email2",390483293,"password2");
        String expResult = "Arnaldo";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNome method, of class Utilizador.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "Ricardinho";
        Utilizador instance = new Utilizador(444444444,"Falcão","email3",839842929,"password3");
        instance.setNome(nome);
        assertEquals(nome,instance.getNome());
    }

    /**
     * Test of getEmail method, of class Utilizador.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Utilizador instance = new Utilizador(555555555,"Ronaldinho","email4",999999999,"password4");
        String expResult = "email4";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Utilizador.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "email5";
        Utilizador instance = new Utilizador(666666666,"Xavi","email6",292121203,"password5");
        instance.setEmail(email);
        assertEquals(email,instance.getEmail());
    }

    /**
     * Test of getNumeroSegurancaSocial method, of class Utilizador.
     */
    @Test
    public void testGetNumeroSegurancaSocial() {
        System.out.println("getNumeroSegurancaSocial");
        Utilizador instance = new Utilizador(777777777,"Iniesta","emaiil7",910202937,"password6");
        int expResult = 910202937;
        int result = instance.getNumeroSegurancaSocial();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumeroSegurancaSocial method, of class Utilizador.
     */
    @Test
    public void testSetNumeroSegurancaSocial() {
        System.out.println("setNumeroSegurancaSocial");
        int numeroSegurancaSocial = 384728192;
        Utilizador instance = new Utilizador(456654456,"Puyol","email8",121232324,"password7");
        instance.setNumeroSegurancaSocial(numeroSegurancaSocial);
        assertEquals(numeroSegurancaSocial,instance.getNumeroSegurancaSocial());
    }

    /**
     * Test of getPassword method, of class Utilizador.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Utilizador instance = new Utilizador(909909909,"Piqué","email9",909876787,"password8");
        String expResult = "password8";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Utilizador.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "password10";
        Utilizador instance = new Utilizador(444553212,"Casillas","email10",281910192,"password9");
        instance.setPassword(password);
        assertEquals(password,instance.getPassword());
    }
    
}
