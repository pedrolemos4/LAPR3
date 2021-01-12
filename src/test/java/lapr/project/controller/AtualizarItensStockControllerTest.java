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
public class AtualizarItensStockControllerTest {
    private AtualizarItensStockController instance;
    private ProdutosDB produtoMock;
    private Produto produto;

    @BeforeEach
    void setUp() throws SQLException{
        produtoMock = mock(ProdutosDB.class);
        instance = new AtualizarItensStockController(produtoMock);
        produto = new Produto("teste",50.0,70);
    }
    /**
     * Test of getListaProdutos method, of class AtualizarItensStockController.
     */
    @Test
    public void testGetListaProdutos() {
        System.out.println("getListaProdutos");
        Produto produto = new Produto("teste1", 60, 70);
        List<Produto> expResult = new ArrayList<>();
        expResult.add(produto);
        when(produtoMock.getLista()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaProdutos());
    }

    /**
     * Test of getProdutoByID method, of class AtualizarItensStockController.
     */
    @Test
    public void testGetProdutoByID() {
        System.out.println("getProdutoByID");
        Produto expResult  = new Produto(1,"teste",50.0,70);
        when(produtoMock.getProdutoByID(1)).thenReturn(expResult);
        assertEquals(expResult,instance.getProdutoByID(1));
    }

    /**
     * Test of atualizarProduto method, of class AtualizarItensStockController.
     */
    @Test
    public void testAtualizarProduto() {
        System.out.println("atualizarProduto");
        Produto prod2  = new Produto("teste",50.0,70);
        when(produtoMock.atualizarProduto(prod2)).thenReturn(true);
        assertEquals(true,instance.atualizarProduto(prod2));
    }

}
