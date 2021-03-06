package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
     * Test of novoProduto method, of class InserirItensStockController.
     */
    @Test
    public void testNovoProduto() {
        System.out.println("novoProduto");
        Produto produto = new Produto("prod", 69, 71);
        InserirItensStockController i = new InserirItensStockController(new ProdutosDB());
        Produto p = i.novoProduto(produto.getDesignacao(), produto.getPeso(), produto.getPrecoBase());
        assertEquals(produto.toString(), p.toString());
    }

    /**
     * Test of registaProduto method, of class InserirItensStockController.
     */
    @Test
    public void testRegistaProduto() {
        System.out.println("registaProduto");
        Produto prod1 = new Produto("prod1", 77, 88);
        when(produtoMock.registaProduto(prod1,1,1)).thenReturn(true);
        assertEquals(true, instance.registaProduto(prod1,1,1));
    }
    
    /**
     * Test of registaProduto method, of class InserirItensStockController.
     */
    @Test
    public void testRegistaProduto1() {
        System.out.println("registaProduto1");
        Produto prod1 = new Produto("prod1", 77, 88);
        when(produtoMock.registaProduto(prod1,1,1)).thenReturn(false);
        assertEquals(false, instance.registaProduto(prod1,1,1));
    }

    /**
     * Test of getListaProdutos method, of class InserirItensStockController.
     */
    @Test
    public void testGetListaProdutos() {
        System.out.println("getListaProdutos");
        int nif = 12;
        Produto p = new Produto();
        Map<Produto, Integer> expResult = new HashMap<>();
        expResult.put(p,1);
        when(produtoMock.getLista(nif)).thenReturn(expResult);
        assertEquals(expResult, instance.getListaProdutos(nif));
    }

}
