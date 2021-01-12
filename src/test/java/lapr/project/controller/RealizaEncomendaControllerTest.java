package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ProdutosDB;
import lapr.project.data.ReciboDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.stubbing.OngoingStubbing;

class RealizaEncomendaControllerTest {

    private RealizaEncomendaController instance;
    private ProdutosDB produtoDB;
    private EncomendaDB encDB;
    private ClienteDB cliDB;
    private ReciboDB reciboDB;
    private EmailDB emailDB;
    private Encomenda enc;
    
    public RealizaEncomendaControllerTest() {
    }
    
    @BeforeEach
    void setUp() throws SQLException {
        produtoDB = mock(ProdutosDB.class);
        encDB = mock(EncomendaDB.class);
        emailDB = mock(EmailDB.class);
        cliDB = mock(ClienteDB.class);
        reciboDB = mock(ReciboDB.class);
        instance = new RealizaEncomendaController(produtoDB, encDB, reciboDB, cliDB,emailDB);
        enc = new Encomenda(123,"01-01-2000",10,10,10, 1);
        when(encDB.addEncomenda(enc)).thenReturn(1);
        
    }

//    /**
//     * Test of produtoEncomenda method, of class RealizaEncomendaController.
//     */
//    @Test
//    public void testProdutoEncomenda() {
//        System.out.println("produtoEncomenda");
//        Produto prod = new Produto();
//        int qntd = 1;
//        when(encDB.).thenReturn(1);
//        instance.produtoEncomenda(prod, qntd);
//    }

    /**
     * Test of registaEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomenda() throws Exception {
        System.out.println("registaEncomenda");
        boolean expResult = true;
        Encomenda enc = new Encomenda(1234,"02-01-2000",10,20,10, 1);
        when(encDB.registaEncomenda(enc)).thenReturn(expResult);
        assertEquals(expResult, instance.registaEncomenda(enc));
    }

    /**
     * Test of registaEncomendaProduto method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomendaProduto() {
        System.out.println("registaEncomendaProduto");
        Encomenda enc = new Encomenda(1234,"02-01-2000",10,20,10, 1);
        Produto p = new Produto();
        boolean expResult = true;
        when(encDB.registaEncomendaProduto(enc, p)).thenReturn(expResult);
        assertEquals(expResult, instance.registaEncomendaProduto(enc, p));
    }

    /**
     * Test of getListaProdutoEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetListaProdutoEncomenda() {
        System.out.println("getListaProdutoEncomenda");
        List<Produto> expResult = new ArrayList<>();
        Produto p = new Produto();
        expResult.add(p);
        when(produtoDB.getListaProdutos()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaProdutoEncomenda());
    }

    /**
     * Test of getListStock method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetListStock() {
        System.out.println("getListStock");
        Produto p = new Produto();
        List<Produto> expResult = new ArrayList<>();
        expResult.add(p);
        when(produtoDB.getLista()).thenReturn(expResult);
        assertEquals(expResult, instance.getListStock());
    }

    /**
     * Test of getListQuantidade method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetListQuantidade() {
        System.out.println("getListQuantidade");
        List<Integer> expResult = new ArrayList<>();
        expResult.add(1);
        
        when(produtoDB.getListaQuantidade()).thenReturn(expResult);
        assertEquals(expResult, instance.getListQuantidade());
    }

    /**
     * Test of getProdutoByID method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetProdutoByID() {
        System.out.println("getProdutoByID");
        Produto expResult = new Produto("sdf", 50, 58);
        int id=2;
        expResult.setId(id);
        when(produtoDB.getProdutoByID(id)).thenReturn(expResult);
        assertEquals(expResult, instance.getProdutoByID(id));
    }

    /**
     * Test of getPreco method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetPreco() {
        System.out.println("getPreco");
        double expResult = 10;
        when(produtoDB.getPreco()).thenReturn(expResult);
        assertEquals(expResult, instance.getPreco());
    }

    /**
     * Test of getPeso method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        double expResult = 10;
        when(produtoDB.getPeso()).thenReturn(expResult);
        assertEquals(expResult, instance.getPeso());
    }

//    /**
//     * Test of getNifCliente method, of class RealizaEncomendaController.
//     */
//    @Test
//    public void testGetNifCliente() {
//        System.out.println("getNifCliente");
//        String email = "a";
//        int expResult = 0;
//        when(cliDB.getClienteByEmail(email).getNIF()).thenReturn(expResult);
//        assertEquals(expResult, instance.getNifCliente());
//    }

//    /**
//     * Test of novoRecibo method, of class RealizaEncomendaController.
//     */
//    @Test
//    public void testNovoRecibo_Recibo() throws Exception {
//        System.out.println("novoRecibo");
//        Recibo rec = new Recibo();
//        boolean expResult = reciboDB.registaRecibo(rec);
//        when(instance.notificaCliente("", "", "")).thenReturn(expResult);
//        assertEquals(expResult, instance.novoRecibo(rec));
//    }

//    /**
//     * Test of novoRecibo method, of class RealizaEncomendaController.
//     */
//    @Test
//    public void testNovoRecibo_Recibo_Produto() {
//        System.out.println("novoRecibo");
//        Recibo rec = null;
//        Produto prod = null;
//        RealizaEncomendaController instance = null;
//        instance.novoRecibo(rec, prod);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of verificaProdutoEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testVerificaProdutoEncomenda() {
        System.out.println("verificaProdutoEncomenda");
        Produto prod = new Produto("sdf", 50, 58);
        prod.setId(1);
        int qntd = 1;
        boolean expResult = false;
        boolean result = instance.verificaProdutoEncomenda(prod, qntd);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of notificaCliente method, of class RealizaEncomendaController.
//     */
//    @Test
//    public void testNotificaCliente() throws Exception {
//        System.out.println("notificaCliente");
//        String email = "";
//        String assunto = "";
//        String mensagem = "";
//        RealizaEncomendaController instance = null;
//        instance.notificaCliente(email, assunto, mensagem);
//    }
//
//    /**
//     * Test of getPrecoTotal method, of class RealizaEncomendaController.
//     */
//    @Test
//    public void testGetPrecoTotal() {
//        System.out.println("getPrecoTotal");
//        RealizaEncomendaController instance1 = null;
//        double taxa = 0.3;
//        List<Produto> lst = new ArrayList<>();
//        Produto p = new Produto("sdf", 50, 58);
//        p.setPrecoBase(5);
//        lst.add(p);
//        double expResult = p.getPrecoBase() + p.getPrecoBase()*taxa;
//        double result = instance1.getPrecoTotal(taxa);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of removerProdutosEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testRemoverProdutosEncomenda() {
        System.out.println("removerProdutosEncomenda");
        List<Produto> lst = new ArrayList<>();
        List<Integer> lst2 = new ArrayList<>();
        Produto p = new Produto();
        Produto p1 = new Produto();
        lst.add(p);
        lst.add(p1);
        lst2.add(1);
        lst2.add(2);
        
        instance.removerProdutosEncomenda(lst, lst2);
    }
}