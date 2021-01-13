package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ProdutosDB;
import lapr.project.data.ReciboDB;
import lapr.project.login.UserSession;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.utils.Data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

class RealizaEncomendaControllerTest {

    private RealizaEncomendaController instance;
    private ProdutosDB produtoDB;
    private ProdutosDB pDB;
    private EncomendaDB encDB;
    private ClienteDB cliDB;
    private ReciboDB reciboDB;
    private EmailDB emailDB;
    
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
    }

    /**
     * Test of produtoEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testProdutoEncomenda() {
        System.out.println("produtoEncomenda");
        Produto prod = new Produto("a",1,1);
        prod.setId(1);
        boolean expResult = false;
        assertEquals(expResult, instance.produtoEncomenda(prod,1));
    }
    
    /**
     * Test of produtoEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testProdutoEncomenda1() {
        System.out.println("produtoEncomenda1");
        Produto prod = new Produto("a",1,1);
        prod.setId(1);
        boolean expResult = true;
        assertEquals(expResult, instance.produtoEncomenda(prod,1));
    }

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
     * Test of registaEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomenda1() throws Exception {
        System.out.println("registaEncomenda1");
        boolean expResult = false;
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
     * Test of registaEncomendaProduto method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomendaProduto1() {
        System.out.println("registaEncomendaProduto1");
        Encomenda enc = new Encomenda(1234,"02-01-2000",10,20,10, 1);
        Produto p = new Produto();
        boolean expResult = false;
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

    /**
     * Test of getNifCliente method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetCliente() {
        System.out.println("getNifCliente");
        Cliente c = new Cliente(1, "nome", "email", 1, 1, "morada", 1, "password");
        UserSession.getInstance().setUser(c);
        Cliente expResult = c;
        when(cliDB.getClienteByEmail(c.getEmail())).thenReturn(c);
        assertEquals(expResult, instance.getCliente());
    }

    /**
     * Test of novoRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testNovoRecibo_Recibo() throws Exception {
        System.out.println("novoRecibo");
        Recibo rec = new Recibo();
        boolean expResult = reciboDB.registaRecibo(rec);
        when(instance.notificaCliente("", "", "")).thenReturn(expResult);
        assertEquals(expResult, instance.novoRecibo(rec));
    }

    /**
     * Test of novoRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testNovoRecibo_Recibo_Produto() {
        System.out.println("novoRecibo");
        Recibo rec = new Recibo();
        Produto prod = new Produto();
        boolean expResult = true;
        when(reciboDB.registaRecibo(rec, prod)).thenReturn(expResult);
        assertEquals(expResult, instance.novoRecibo(rec,prod));
    }
    
    /**
     * Test of novoRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testNovoRecibo_Recibo_Produto1() {
        System.out.println("novoRecibo");
        Recibo rec = new Recibo();
        Produto prod = new Produto();
        boolean expResult = false;
        when(reciboDB.registaRecibo(rec, prod)).thenReturn(expResult);
        assertEquals(expResult, instance.novoRecibo(rec,prod));
    }

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
    
    /**
     * Test of verificaProdutoEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testVerificaProdutoEncomenda1() {
        System.out.println("verificaProdutoEncomenda1");
        Produto prod = new Produto("sdf", 50, 58);
        prod.setId(1);
        int qntd = 1;
        boolean expResult = true;
        boolean result = instance.verificaProdutoEncomenda(prod, qntd);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPrecoTotal method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetPrecoTotal() {
        System.out.println("getPrecoTotal");
        double taxa = 0.3;
        List<Produto> lst = new ArrayList<>();
        Produto p = new Produto("sdf", 50, 58);
        p.setPrecoBase(5);
        lst.add(p);
        when(produtoDB.getListaProdutos()).thenReturn(lst);
        double expResult = lst.get(0).getPrecoBase() + lst.get(0).getPrecoBase()*taxa;
        assertEquals(expResult, instance.getPrecoTotal(taxa));
    }

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

    /**
     * Test of notificaCliente method, of class RealizaEncomendaController.
     */
    @Test
    public void testNotificaCliente() throws Exception {
        System.out.println("notificaCliente");
        String email = "a";
        String assunto = "b";
        String mensagem = "c";
        boolean expResult = true;
        when(emailDB.sendEmail(email, assunto, mensagem)).thenReturn(expResult);
        assertEquals(expResult, instance.notificaCliente(email, assunto, mensagem));
    }
    
    /**
     * Test of notificaCliente method, of class RealizaEncomendaController.
     */
    @Test
    public void testNotificaCliente1() throws Exception {
        System.out.println("notificaCliente1");
        String email = "a";
        String assunto = "b";
        String mensagem = "c";
        boolean expResult = false;
        when(emailDB.sendEmail(email, assunto, mensagem)).thenReturn(expResult);
        assertEquals(expResult, instance.notificaCliente(email, assunto, mensagem));
    }

    /**
     * Test of contarNumeroProds method, of class RealizaEncomendaController.
     */
    @Test
    public void testContarNumeroProds() {
        System.out.println("contarNumeroProds");
        Produto prod = new Produto("sdf", 50, 58);
        List<Produto> lst = new ArrayList<>();
        lst.add(prod);
        when(produtoDB.getLista()).thenReturn(lst);
        int expResult = 1;
        assertEquals(expResult, instance.contarNumeroProds(prod));
    }

    /**
     * Test of getCreditosData method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetCreditosData() {
        System.out.println("getCreditosData");
        Data date = new Data("02/01/2021");
        double preco = 10.0;
        double expResult = 5.0;
        RealizaEncomendaController instance1 = new RealizaEncomendaController(new ProdutosDB(), new EncomendaDB(), new ReciboDB(), new ClienteDB(), new EmailDB());
        assertEquals(expResult, instance1.getCreditosData(date, preco));
    }

    /**
     * Test of removerCreditos method, of class RealizaEncomendaController.
     */
    @Test
    public void testRemoverCreditos() throws Exception {
        System.out.println("removerCreditos");
        Cliente c1 = new Cliente();
        double creditosData = 0.0;
        boolean expResult = true;
        boolean expResult1 = true;
        when(cliDB.addCliente(c1)).thenReturn(expResult1);
        when(cliDB.removerCreditos(c1.getEmail(), creditosData)).thenReturn(expResult);
        assertEquals(expResult, instance.removerCreditos(c1.getEmail(), creditosData));
    }
    
    /**
     * Test of removerCreditos method, of class RealizaEncomendaController.
     */
    @Test
    public void testRemoverCreditos1() throws Exception {
        System.out.println("removerCreditos1");
        Cliente c1 = new Cliente();
        double creditosData = 0.0;
        boolean expResult = false;
        boolean expResult1 = false;
        when(cliDB.addCliente(c1)).thenReturn(expResult1);
        when(cliDB.removerCreditos(c1.getEmail(), creditosData)).thenReturn(expResult);
        assertEquals(expResult, instance.removerCreditos(c1.getEmail(), creditosData));
    }
}