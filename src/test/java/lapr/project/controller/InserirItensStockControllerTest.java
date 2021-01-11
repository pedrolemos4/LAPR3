/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Tiago
 */
public class InserirItensStockControllerTest {

    private InserirItensStockController instance;
    private ProdutosDB produtoMock;
    private Produto prod;

    @BeforeEach
    void setUp() throws SQLException {
        produtoMock = mock(ProdutosDB.class);
        instance = new InserirItensStockController(produtoMock);
        prod = new Produto("teste", 50.0, 70);
    }

    /**
     * Test of getListaProdutos method, of class InserirItensStockController.
     */
    @Test
    public void testGetListaProdutos() {
        System.out.println("getListaProdutos");
        Produto produto = new Produto("teste1", 60, 70);
        List<Produto> expResult = new ArrayList<>();
        expResult.add(produto);
        when(produtoMock.getListaProdutos()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaProdutos());
    }

    /**
     * Test of novoProduto method, of class InserirItensStockController.
     */
    @Test
    public void testNovoProduto() {
        System.out.println("novoProduto");
        Produto produto = produtoMock.novoProduto("prod", 69, 71);
        when(produtoMock.novoProduto("prod", 69, 71)).thenReturn(produto);
        assertEquals(produto, produtoMock.novoProduto("prod", 69, 71));
    }

    /**
     * Test of registaProduto method, of class InserirItensStockController.
     */
    @Test
    public void testRegistaProduto() {
        System.out.println("registaProduto");
        Produto prod1 = new Produto("prod1", 77, 88);
        when(produtoMock.registaProduto(prod1)).thenReturn(true);
        assertEquals(true,produtoMock.registaProduto(prod1));
    }

}
