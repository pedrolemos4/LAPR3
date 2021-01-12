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
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Utilizador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
    private EnderecoDB enderecoMock;
    private CartaoDB cartaoMock;
    private Cliente cl;

    @BeforeEach
    void setUp() throws SQLException {
        clienteMock = mock(ClienteDB.class);
        instance = new RegistarClienteController();
        enderecoMock = mock(EnderecoDB.class);
        cartaoMock = mock(CartaoDB.class);
        cl = new Cliente(123, "Teste", "@", 1234, 12, "Ali", 123, "pass");
        when(clienteMock.addCliente(cl)).thenReturn(true);
    }

    /**
     * Test of login method, of class RegistarClienteController.
     */
//    @Test
//    public void testLogin() throws SQLException {
//        System.out.println("login");
//        String email = "";
//        String password = "";
//        RegistarClienteController instance = new RegistarClienteController();
//        Utilizador expResult = null;
//        Utilizador result = instance.login(email, password);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getListaClientes method, of class RegistarClienteController.
     */
//    @Test
//    void testGetListaClientes() throws SQLException {
//        System.out.println("GetListaClientes");
//        Cliente cliente = new Cliente(123, 1234, "Ali", 123);
//        List<Cliente> expResult = new ArrayList<>();
//        expResult.add(cliente);
//        when(clienteMock.getLstClientes()).thenReturn(expResult);
//        assertEquals(expResult, instance.getListaClientes());
//    }

    /**
     * Test of novoCliente method, of class RegistarClienteController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoCliente() throws SQLException {
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
    public void testNovoCartao() throws SQLException {
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
    public void testNovoEndereco() throws SQLException {
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
    public void testRegistaEndereco() throws SQLException {
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
    public void testRegistaCartao() throws SQLException {
        System.out.println("registaCartao");
        Cartao cart = new Cartao(5, "sdd", 5);
        when(cartaoMock.registaCartao(cart)).thenReturn(true);
        assertEquals(true, cartaoMock.registaCartao(cart));
    }
}
