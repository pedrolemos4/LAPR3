/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author josep
 */
public class RegistarClienteControllerTest {

    private RegistarClienteController instance;
    private ClienteDB clienteMock;
    private UtilizadorDB utilizadorMock;
    private EnderecoDB enderecoMock;
    private CartaoDB cartaoMock;
    private Cliente cl;

    @BeforeEach
    void setUp() throws SQLException {
        clienteMock = mock(ClienteDB.class);
        utilizadorMock = mock(UtilizadorDB.class);
        instance = new RegistarClienteController();
        enderecoMock = mock(EnderecoDB.class);
        cartaoMock = mock(CartaoDB.class);
        cl = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(clienteMock.addCliente(cl)).thenReturn(true);
    }

    /**
     * Test of login method, of class RegistarClienteController.
     *
     * @throws java.sql.SQLException
     */
//    @Test
//    public void testLogin() throws SQLException {
//        System.out.println("login");
//        Utilizador expResult = new Utilizador(111111111, "Ronaldo", "email", 112323232, "password");
//        int nif = utilizadorMock.validateLogin("email", "password");
//        System.out.println(nif);
//        Utilizador result = utilizadorMock.getByID(nif);
//        assertEquals(expResult, result);
//    }
    /**
     * Test of getListaClientes method, of class RegistarClienteController.
     */
    @Test
    void testGetListaClientes() throws SQLException {
        System.out.println("GetListaClientes");
        Cliente cliente = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente);
        when(clienteMock.getLstClientes()).thenReturn(expResult);
    }

    /**
     * Test of novoCliente method, of class RegistarClienteController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    void testNovoCliente() throws SQLException {
        System.out.println("NovoCliente");
        Cliente cliente = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(clienteMock.novoCliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass")).thenReturn(cliente);
        assertEquals(cliente, clienteMock.novoCliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass"));
    }

    /**
     * Test of registaCliente method, of class RegistarClienteController.
     */
    @Test
    void testRegistaCliente() throws SQLException {
        System.out.println("registaCliente");
        Cliente cliente = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(clienteMock.registaCliente(cliente)).thenReturn(true);
        assertEquals(true, clienteMock.registaCliente(cliente));
    }

    /**
     * Test of novoCartao method, of class RegistarClienteController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    void testNovoCartao() throws SQLException {
        System.out.println("novoCartao");
        Cartao cart = new Cartao(5, "sdd", 5);
        when(cartaoMock.novoCartao(5, "sdd", 5)).thenReturn(cart);
        assertEquals(cart, cartaoMock.novoCartao(5, "sdd", 5));
    }

    /**
     * Test of novoEndereco method, of class RegistarClienteController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    void testNovoEndereco() throws SQLException {
        System.out.println("novoEndereco");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.novoEndereco("Rua do ISEP", 41.45, 30.58, 34.23)).thenReturn(end);
        assertEquals(end, enderecoMock.novoEndereco("Rua do ISEP", 41.45, 30.58, 34.23));
    }

    /**
     * Test of registaEndereco method, of class RegistarClienteController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    void testRegistaEndereco() throws SQLException {
        System.out.println("registaEndereco");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.registaEndereco(end)).thenReturn(true);
        assertEquals(true, enderecoMock.registaEndereco(end));
    }

    /**
     * Test of registaCartao method, of class RegistarClienteController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    void testRegistaCartao() throws SQLException {
        System.out.println("registaCartao");
        Cartao cart = new Cartao(5, "sdd", 5);
        when(cartaoMock.registaCartao(cart)).thenReturn(true);
        assertEquals(true, cartaoMock.registaCartao(cart));
    }
}
