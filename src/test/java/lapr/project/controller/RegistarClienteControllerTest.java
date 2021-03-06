/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Utilizador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author josep
 */
public class RegistarClienteControllerTest {

    private RegistarClienteController instance;
    private RegistarClienteController instance1;
    private ClienteDB clienteMock;
    private UtilizadorDB utilizadorMock;
    private EnderecoDB enderecoMock;
    private CartaoDB cartaoMock;
    private Cliente cl;

    @BeforeEach
    void setUp() throws SQLException {
        instance = new RegistarClienteController(new ClienteDB(), new UtilizadorDB(), new EnderecoDB(), new CartaoDB());
        clienteMock = mock(ClienteDB.class);
        utilizadorMock = mock(UtilizadorDB.class);
        enderecoMock = mock(EnderecoDB.class);
        cartaoMock = mock(CartaoDB.class);
        instance1 = new RegistarClienteController(clienteMock, utilizadorMock, enderecoMock, cartaoMock);
        cl = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(clienteMock.addCliente(cl)).thenReturn(true);
    }

    /**
     * Test of login method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testLogin() throws SQLException {
        System.out.println("login");
        Cliente expResult = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(utilizadorMock.validateLogin("email", "password")).thenReturn(123);
        int nif = utilizadorMock.validateLogin("email", "password");
        when(utilizadorMock.getByID(123)).thenReturn(expResult);
        Utilizador result = utilizadorMock.getByID(nif);
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaClientes method, of class RegistarClienteController.
     */
    @Test
    public void testGetListaClientes() throws SQLException {
        System.out.println("GetListaClientes");
        Cliente cliente = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente);
        when(clienteMock.getListaClientes()).thenReturn(expResult);
        assertEquals(expResult, instance1.getListaClientes());
    }

    /**
     * Test of novoCliente method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoCliente() throws SQLException {
        System.out.println("NovoCliente");
        Cliente cliente = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        assertEquals(cliente.toString(), instance.novoCliente(cliente.getClienteNIF(), cliente.getNome(), cliente.getEmail(), cliente.getNumeroSegurancaSocial(), cliente.getCreditos(), cliente.getEnderecoMorada(), cliente.getNumCartaoCredito(), cliente.getPassword()).toString());
    }

    /**
     * Test of registaCliente method, of class RegistarClienteController.
     */
    @Test
    public void testRegistaCliente() throws SQLException {
        System.out.println("registaCliente");
        Cliente cliente = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(clienteMock.registaCliente(cliente)).thenReturn(true);
        assertEquals(true, instance1.registaCliente(cliente));
    }
    
    /**
     * Test of registaCliente method, of class RegistarClienteController.
     */
    @Test
    public void testRegistaCliente1() throws SQLException {
        System.out.println("registaCliente1");
        Cliente cliente = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(clienteMock.registaCliente(cliente)).thenReturn(false);
        assertEquals(false, instance1.registaCliente(cliente));
    }

    /**
     * Test of novoCartao method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoCartao() throws SQLException {
        System.out.println("novoCartao");
        Cartao cart = new Cartao(5, "sdd", 5);
        assertEquals(cart.toString(), instance.novoCartao(cart.getNumeroCartao(), cart.getDataDeValidade(), cart.getCCV()).toString());
    }

    /**
     * Test of novoEndereco method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoEndereco() throws SQLException {
        System.out.println("novoEndereco");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.novoEndereco("Rua do ISEP", 41.45, 30.58, 34.23)).thenReturn(end);
        assertEquals(end.toString(), instance.novoEndereco("Rua do ISEP", 41.45, 30.58, 34.23).toString());
    }

    /**
     * Test of registaEndereco method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaEndereco() throws SQLException {
        System.out.println("registaEndereco");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.registaEndereco(end)).thenReturn(true);
        assertEquals(true, instance1.registaEndereco(end));
    }
    
    /**
     * Test of registaEndereco method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaEndereco1() throws SQLException {
        System.out.println("registaEndereco1");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.registaEndereco(end)).thenReturn(false);
        assertEquals(false, instance1.registaEndereco(end));
    }

    /**
     * Test of registaCartao method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaCartao() throws SQLException, ParseException {
        System.out.println("registaCartao");
        Cartao cart = new Cartao(5, "sdd", 5);
        when(cartaoMock.registaCartao(cart)).thenReturn(true);
        assertEquals(true, instance1.registaCartao(cart));
    }
    
    /**
     * Test of registaCartao method, of class RegistarClienteController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaCartao1() throws SQLException, ParseException, ParseException {
        System.out.println("registaCartao1");
        Cartao cart = new Cartao(5, "sdd", 5);
        when(cartaoMock.registaCartao(cart)).thenReturn(false);
        assertEquals(false, instance1.registaCartao(cart));
    }

    @Test
    void login() {
        System.out.println("login");
        Cliente user = new Cliente(13, "nome", "email",123,0,"morada",13,"password");
        when(utilizadorMock.validateLogin("email","password")).thenReturn(13);
        when(utilizadorMock.getByID(13)).thenReturn(user);

        assertEquals(user,instance1.login("email","password"));
    }
}
