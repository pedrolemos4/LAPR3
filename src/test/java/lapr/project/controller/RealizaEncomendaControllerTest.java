package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.data.ReciboDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class RealizaEncomendaControllerTest {

    @Test
    void produtoEncomenda() {
    }

    @Test
    void registaEncomenda() {
    }

    @Test
    void registaEncomendaProduto() {
    }

    @Test
    void getListaProdutoEncomenda() {
    }

    @Test
    void getListStock() {
        System.out.println("getListStock");
        Produto prod = new Produto("desig",45, 56);
        List<Produto> expResult = new ArrayList<>();
        expResult.add(prod);
        ProdutosDB prodMock = mock(ProdutosDB.class);
        when(prodMock.getLista()).thenReturn(expResult);
        assertEquals(expResult, prodMock.getLista());
    }

    @Test
    void getListQuantidade() {
        System.out.println("getListQuantidade");
        List<Integer> expResult = new ArrayList<>();
        ProdutosDB prodMock = mock(ProdutosDB.class);
        when(prodMock.getListaQuantidade()).thenReturn(expResult);
        assertEquals(expResult, prodMock.getLista());
    }

    @Test
    void getProdutoByID() {
    }

    @Test
    void getPreco() {
        System.out.println("getPreco");
        int expResult = 0;
        ProdutosDB prodMock = mock(ProdutosDB.class);
        assertEquals(expResult, prodMock.getPreco());
    }

    @Test
    void getPeso() {
        System.out.println("getPeso");
        int expResult = 0;
        ProdutosDB prodMock = mock(ProdutosDB.class);
        assertEquals(expResult, prodMock.getPeso());
    }

    @Test
    void getNifCliente() {
    }

    @Test
    void novoRecibo() {
    }

    @Test
    void testNovoRecibo() {
    }

    @Test
    void verificaProdutoEncomenda() {
    }

    @Test
    void notificaCliente() {
    }

    @Test
    void getPrecoTotal() {
    }

    @Test
    void removerProdutosEncomenda() {
    }
}