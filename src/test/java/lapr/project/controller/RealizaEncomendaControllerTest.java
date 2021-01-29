package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.*;
import lapr.project.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RealizaEncomendaControllerTest {

    private RealizaEncomendaController instance;
    private ProdutosDB produtoDB;
    private ProdutosDB pDB;
    private EncomendaDB encDB;
    private ClienteDB cliDB;
    private ReciboDB reciboDB;
    private EmailDB emailDB;
    private EnderecoDB endDB;

    public RealizaEncomendaControllerTest() {
    }

    @BeforeEach
    void setUp() throws SQLException {
        produtoDB = mock(ProdutosDB.class);
        encDB = mock(EncomendaDB.class);
        emailDB = mock(EmailDB.class);
        cliDB = mock(ClienteDB.class);
        reciboDB = mock(ReciboDB.class);
        endDB = mock(EnderecoDB.class);
        instance = new RealizaEncomendaController(produtoDB, encDB, reciboDB, cliDB, emailDB, endDB);
    }

    /**
     * Test of produtoEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testProdutoEncomenda() {
        System.out.println("produtoEncomenda");
        Produto prod = new Produto("a", 1, 1);
        prod.setId(1);

        Produto prod1 = new Produto("a1", 11, 11);
        prod1.setId(2);

        int nif = 12;
        Map<Produto,Integer> m = new HashMap<>();
        m.put(prod, 1);
        m.put(prod1,2);

        boolean expResult = true;
        when(produtoDB.getLista(nif)).thenReturn(m);
        when(produtoDB.addListaProdutos(prod, 1)).thenReturn(expResult);
        assertEquals(expResult, instance.produtoEncomenda(nif, prod, 1));
    }
    
    /**
     * Test of produtoEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testProdutoEncomenda1() {
        System.out.println("produtoEncomenda1");
        Produto prod = new Produto("a", 1, 1);
        prod.setId(1);

        Produto prod1 = new Produto("a1", 11, 11);
        prod1.setId(2);

        int nif = 12;
        Map<Produto,Integer> m = new HashMap<>();
        m.put(prod, 1);
        m.put(prod1,2);

        boolean expResult = false;
        when(produtoDB.getLista(nif)).thenReturn(m);
        when(produtoDB.addListaProdutos(prod, 4)).thenReturn(expResult);
        assertEquals(expResult, instance.produtoEncomenda(nif, prod, 1));
    }

    /**
     * Test of registaEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomenda() throws Exception {
        System.out.println("registaEncomenda");
        int expResult = 1;
        Encomenda enc = new Encomenda(1234, 12, "02-01-2000", 10, 20, 10, 1);

        when(encDB.registaEncomenda(enc)).thenReturn(expResult);
        assertEquals(expResult, instance.registaEncomenda(enc));
    }

    /**
     * Test of registaEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomenda1() throws Exception {
        System.out.println("registaEncomenda1");
        int expResult = 1;
        Encomenda enc = new Encomenda(1234, 12,"02-01-2000", 10, 20, 10, 1);
        when(encDB.registaEncomenda(enc)).thenReturn(expResult);
        assertEquals(expResult, instance.registaEncomenda(enc));
    }

    /**
     * Test of registaEncomendaProduto method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomendaProduto() {
        System.out.println("registaEncomendaProduto");
        Encomenda enc = new Encomenda(1234, 12,"02-01-2000", 10, 20, 10, 1);
        Produto p = new Produto();
        int stock = 1;
        boolean expResult = true;
        when(encDB.registaEncomendaProduto(enc, p, stock)).thenReturn(expResult);
        assertEquals(expResult, instance.registaEncomendaProduto(enc, p, stock));
    }

    /**
     * Test of registaEncomendaProduto method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testRegistaEncomendaProduto1() {
        System.out.println("registaEncomendaProduto1");
        Encomenda enc = new Encomenda(1234, 12,"02-01-2000", 10, 20, 10, 1);
        Produto p = new Produto("sdf", 50, 58);
        boolean expResult = false;
        int stock = 1;
        when(encDB.registaEncomendaProduto(enc, p, stock)).thenReturn(expResult);
        assertEquals(expResult, instance.registaEncomendaProduto(enc, p, stock));
    }

    /**
     * Test of getListStock method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetListStock() {
        System.out.println("getListStock");
        Produto p = new Produto("sdf", 50, 58);
        Map<Produto, Integer> expResult = new HashMap<>();
        int nif = 12;
        expResult.put(p, 1);
        when(produtoDB.getLista(nif)).thenReturn(expResult);
        assertEquals(expResult, instance.getListStock(nif));
    }

    /**
     * Test of getProdutoByID method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetProdutoByID() {
        System.out.println("getProdutoByID");
        Produto expResult = new Produto("sdf", 50, 58);
        int id = 2;
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
        when(cliDB.getClienteByNIF(c.getNIF())).thenReturn(c);
        assertEquals(expResult, instance.getCliente());
    }

    /**
     * Test of novoRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testNovoRecibo_Recibo_Produto() {
        System.out.println("novoRecibo");
        Recibo rec = new Recibo();
        Produto prod = new Produto("sdf", 50, 58);
        boolean expResult = true;
        int quant = 0;
        when(reciboDB.registaRecibo(rec, prod, quant)).thenReturn(expResult);
        assertEquals(expResult, instance.novoRecibo(rec, prod, quant));
    }

    /**
     * Test of novoRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testNovoRecibo_Recibo_Produto1() {
        System.out.println("novoRecibo");
        Recibo rec = new Recibo();
        Produto prod = new Produto("sdf", 50, 58);
        boolean expResult = false;
        int quant = 0;
        when(reciboDB.registaRecibo(rec, prod, quant)).thenReturn(expResult);
        assertEquals(expResult, instance.novoRecibo(rec, prod, quant));
    }

    /**
     * Test of verificaProdutoEncomenda method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testVerificaProdutoEncomenda() {
        System.out.println("verificaProdutoEncomenda");
        Produto prod = new Produto("sdf", 50, 58);
        prod.setId(1);
        int qntd = 2;
        int nif = 12;
        boolean expResult = false;
        Map<Produto, Integer> expResult1 = new HashMap<>();
        expResult1.put(prod, 1);
        when(produtoDB.getLista(123)).thenReturn(expResult1);
        boolean result = instance.verificaProdutoEncomenda(nif, prod, qntd);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificaProdutoEncomenda method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testVerificaProdutoEncomenda1() {
        System.out.println("verificaProdutoEncomenda1");
        Produto prod = new Produto("sdfa", 58, 50);
        prod.setId(2);
        int qntd = 2;
        boolean expResult = true;
        Map<Produto, Integer> expResult1 = new HashMap<>();
        expResult1.put(prod, 4);
        when(produtoDB.getLista(1234)).thenReturn(expResult1);
        boolean result = instance.verificaProdutoEncomenda(1234, prod, qntd);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificaProdutoEncomenda method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testVerificaProdutoEncomenda2() {
        System.out.println("verificaProdutoEncomenda2");
        Produto prod = new Produto("sdfas", 23, 41);
        prod.setId(2);
        int qntd = 2;
        int nif = 12;
        boolean expResult = false;
        Map<Produto, Integer> expResult1 = new HashMap<>();
        when(produtoDB.getLista(123)).thenReturn(expResult1);
        boolean result = instance.verificaProdutoEncomenda(nif, prod, qntd);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificaProdutoEncomenda method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testVerificaProdutoEncomenda3() {
        System.out.println("verificaProdutoEncomenda3");
        Produto prod = new Produto("sdfas", 23, 41);
        prod.setId(2);
        int qntd = 2;
        int nif = 12;
        boolean expResult = false;
        Map<Produto, Integer> expResult1 = new HashMap<>();
        Produto p2 = new Produto("sdfs", 231, 413);
        expResult1.put(p2, 3);
        when(produtoDB.getLista(123)).thenReturn(expResult1);
        boolean result = instance.verificaProdutoEncomenda(nif, prod, qntd);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of verificaProdutoEncomenda method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testVerificaProdutoEncomenda4() {
        System.out.println("verificaProdutoEncomenda4");
        Produto prod = new Produto("sdfa", 58, 50);
        prod.setId(2);
        int qntd = 2;
        boolean expResult = true;
        Map<Produto, Integer> expResult1 = new HashMap<>();
        expResult1.put(prod, 2);
        when(produtoDB.getLista(1234)).thenReturn(expResult1);
        boolean result = instance.verificaProdutoEncomenda(1234, prod, qntd);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditosData method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetCreditosData() {
        System.out.println("getCreditosData");
        Data date = new Data("02/01/2021");
        double preco = 10.0;
        double expResult = 30.0;
        RealizaEncomendaController instance1 = new RealizaEncomendaController(new ProdutosDB(), new EncomendaDB(), new ReciboDB(), new ClienteDB(), new EmailDB(), new EnderecoDB());
        assertEquals(expResult, instance1.getCreditosData(date, preco));
    }

    /**
     * Test of removerCreditos method, of class RealizaEncomendaController.
     */
    @Test
    public void testRemoverCreditos() throws Exception {
        System.out.println("removerCreditos");
        Cliente c1 = new Cliente(1, "nome", "email", 1, 1, "morada", 1, "password");
        double creditosData = 0.0;
        boolean expResult = true;
        boolean expResult1 = true;
        when(cliDB.addCliente(c1)).thenReturn(expResult1);
        when(cliDB.removerCreditos(c1.getClienteNIF(), creditosData)).thenReturn(expResult);
        assertEquals(expResult, instance.removerCreditos(c1.getClienteNIF(), creditosData));
    }

    /**
     * Test of removerCreditos method, of class RealizaEncomendaController.
     */
    @Test
    public void testRemoverCreditos1() throws Exception {
        System.out.println("removerCreditos1");
        Cliente c1 = new Cliente(1, "nome", "email", 1, 1, "morada", 1, "password");
        double creditosData = 0.0;
        boolean expResult = false;
        boolean expResult1 = false;
        when(cliDB.addCliente(c1)).thenReturn(expResult1);
        when(cliDB.removerCreditos(c1.getClienteNIF(), creditosData)).thenReturn(expResult);
        assertEquals(expResult, instance.removerCreditos(c1.getClienteNIF(), creditosData));
    }

    /**
     * Test of getMapaEncomenda method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetMapaEncomenda() {
        System.out.println("getMapaEncomenda");
        Map<Produto, Integer> expResult = new HashMap<>();

        Produto prod = new Produto("sdfa", 58, 50);
        expResult.put(prod,1);

        Boolean expResult1 = true;
        when(produtoDB.addListaProdutos(prod, 1)).thenReturn(expResult1);
        when(produtoDB.getMapaEncomenda()).thenReturn(expResult);
        assertEquals(expResult, instance.getMapaEncomenda());
    }

    /**
     * Test of removerProdutosEncomenda method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testRemoverProdutosEncomenda() {
        System.out.println("removerProdutosEncomenda");
        int map = 2;
        Produto p = new Produto("sdfa", 58, 50);
        int nif = 123;
        int mapStock = 1;
        Boolean expResult = true;
        int expResult1 = 1;
        boolean expResult2 = true;
        when(produtoDB.addProduto(p)).thenReturn(expResult1);
        when(produtoDB.addProdutoStock(nif,expResult1,3)).thenReturn(expResult2);
        when(produtoDB.removerProdutosEncomenda(p, nif, map, mapStock)).thenReturn(expResult);
        assertEquals(expResult, instance.removerProdutosEncomenda(p, nif, map, mapStock));
    }
    
    /**
     * Test of removerProdutosEncomenda method, of class
     * RealizaEncomendaController.
     */
    @Test
    public void testRemoverProdutosEncomenda1() {
        System.out.println("removerProdutosEncomenda1");
        int map = 40;
        Produto p = new Produto("sdfa", 58, 50);
        int nif = 123;
        int mapStock = 0;
        Boolean expResult = true;
        int expResult1 = 1;
        boolean expResult2 = true;
        when(produtoDB.addProduto(p)).thenReturn(expResult1);
        when(produtoDB.addProdutoStock(nif,expResult1,3)).thenReturn(expResult2);
        when(produtoDB.removerProdutosEncomenda(p, nif, map, mapStock)).thenReturn(expResult);
        assertEquals(expResult, instance.removerProdutosEncomenda(p, nif, map, mapStock));
    }

    /**
     * Test of getPrecoTotal method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetPrecoTotal() {
        System.out.println("getPrecoTotal");
        double taxa = 0.1;
        double expResult = 1.1;
        when(produtoDB.getPrecoTotal(1, taxa)).thenReturn(expResult);
        double result = instance.getPrecoTotal(1, taxa);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPrecoTotal method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetPrecoTotal1() {
        System.out.println("getPrecoTotal1");
        double taxa = 0.15;
        double expResult = 1.15;
        when(produtoDB.getPrecoTotal(1, taxa)).thenReturn(expResult);
        double result = instance.getPrecoTotal(1, taxa);
        assertEquals(expResult, result);
    }

    /**
     * Test of notificaCliente method, of class RealizaEncomendaController.
     */
    @Test
    public void testNotificaCliente() {
        System.out.println("notificaCliente");
        String email = "admlapr123@gmail.com";
        String assunto = "Teste";
        String mensagem = "Mensagem teste";
        boolean expResult = true;
        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(expResult);
        boolean result = instance.notificaCliente(email, assunto, mensagem);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificaCliente method, of class RealizaEncomendaController.
     */
    @Test
    public void testNotificaCliente1() {
        System.out.println("notificaCliente1");
        String email = "123@gmail.com";
        String assunto = "Teste";
        String mensagem = "Mensagem teste";
        boolean expResult = false;
        when(emailDB.sendEmail("admlapr123", email, assunto, mensagem)).thenReturn(expResult);
        boolean result = instance.notificaCliente(email, assunto, mensagem);
        assertEquals(expResult, result);
    }


    /**
     * Test of registaRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaRecibo() throws Exception {
        System.out.println("registaRecibo");
        Recibo rec = new Recibo(123, 10.2, "data", 321);
        boolean expResult = true;
        when(reciboDB.registaRecibo(rec)).thenReturn(expResult);
        boolean result = instance.registaRecibo(rec);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of registaRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testRegistaRecibo1() throws Exception {
        System.out.println("registaRecibo1");
        Recibo rec = new Recibo(123, 10.2, "data", 321);
        rec.setData(null);
        boolean expResult = false;
        when(reciboDB.registaRecibo(rec)).thenReturn(expResult);
        boolean result = instance.registaRecibo(rec);
        assertEquals(expResult, result);
    }

    /**
     * Test of geraCreditos method, of class RealizaEncomendaController.
     */
    @Test
    public void testGeraCreditos() {
        System.out.println("geraCreditos");
        Cliente c = new Cliente(1, "nome", "email", 1, 1, "morada", 1, "password");
        double precoTotal = 2.0;
        boolean expResult = true;
        when(encDB.geraCreditos(c, precoTotal)).thenReturn(expResult);
        assertEquals(expResult, instance.geraCreditos(c, precoTotal));
    }
    
    /**
     * Test of geraCreditos method, of class RealizaEncomendaController.
     */
    @Test
    public void testGeraCreditos1() {
        System.out.println("geraCreditos1");
        Cliente c = null;
        double precoTotal = 2.0;
        boolean expResult = false;
        when(encDB.geraCreditos(c, precoTotal)).thenReturn(expResult);
        assertEquals(expResult, instance.geraCreditos(c, precoTotal));
    }

    /**
     * Test of novoRecibo method, of class RealizaEncomendaController.
     */
    @Test
    public void testNovoRecibo() {
        System.out.println("novoRecibo");
        Recibo rec = new Recibo(123, 10.2, "data", 321);
        Produto prod = new Produto("sdfa", 58, 50);
        int quant = 3;
        boolean expResult = false;
        when(reciboDB.registaRecibo(rec, prod, quant)).thenReturn(expResult);
        boolean result = instance.novoRecibo(rec, prod, quant);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnderecoByNifCliente method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetEnderecoByNifCliente() {
        System.out.println("getEnderecoByNifCliente");
        Cliente c = new Cliente(1, "nome", "email", 1, 1, "Rua da FEUP", 1, "password");
        Boolean expResult1 = true;
        when(cliDB.addCliente(c)).thenReturn(expResult1);
        Endereco expResult = new Endereco("Rua da FEUP", 43.45, 31.70, 35.23);
        when(endDB.getEnderecoByNifCliente(c.getClienteNIF())).thenReturn(expResult);
        Endereco result = instance.getEnderecoByNifCliente(c.getClienteNIF());
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getEnderecoByNifCliente method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetEnderecoByNifCliente1() {
        System.out.println("getEnderecoByNifCliente1");
        int nif = 123;
        Endereco expResult = new Endereco("Rua da FEUP", 43.45, 31.70, 35.23);
        expResult.setMorada("adeus");
        when(endDB.getEnderecoByNifCliente(nif)).thenReturn(expResult);
        Endereco result = instance.getEnderecoByNifCliente(nif);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnderecoOrigem method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetEnderecoOrigem() {
        System.out.println("getEnderecoOrigem");
        int nifFarmacia = 0;
        Endereco expResult = new Endereco("Rua da FEUP", 43.45, 31.70, 35.23);
        when(endDB.getEnderecoByNifFarmacia(nifFarmacia)).thenReturn(expResult);
        Endereco result = instance.getEnderecoOrigem(nifFarmacia);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getEnderecoOrigem method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetEnderecoOrigem1() {
        System.out.println("getEnderecoOrigem1");
        int nifFarmacia = 123;
        Endereco expResult = new Endereco("Rua da FEUP", 43.45, 31.70, 35.23);
        expResult.setMorada("ola");
        when(endDB.getEnderecoByNifFarmacia(nifFarmacia)).thenReturn(expResult);
        Endereco result = instance.getEnderecoOrigem(nifFarmacia);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaxa method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetTaxa() {
        System.out.println("getTaxa");
        double preco = 30.0;
        double expResult = 0.3;
        when(encDB.getTaxa(preco)).thenReturn(expResult);
        double result = instance.getTaxa(preco);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTaxa method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetTaxa1() {
        System.out.println("getTaxa1");
        double preco = 51.0;
        double expResult = 0.2;
        when(encDB.getTaxa(preco)).thenReturn(expResult);
        double result = instance.getTaxa(preco);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTaxa method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetTaxa2() {
        System.out.println("getTaxa2");
        double preco = 101.0;
        double expResult = 0.15;
        when(encDB.getTaxa(preco)).thenReturn(expResult);
        double result = instance.getTaxa(preco);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLstEnderecos method, of class RealizaEncomendaController.
     */
    @Test
    public void testGetLstEnderecos() {
        System.out.println("getLstEnderecos");
        List<Endereco> expResult = new ArrayList<>();

        Endereco e1 = new Endereco("Rua da FEUP", 43.45, 31.70, 35.23);
        Endereco e2 = new Endereco("Rua do ISEP", 44.05, 32, 36);
        Endereco e3 = new Endereco("Rua da FEP", 42.35, 30, 35);

        expResult.add(e1);
        expResult.add(e2);
        expResult.add(e3);

        when(endDB.getLstEnderecos()).thenReturn(expResult);
        List<Endereco> result = instance.getLstEnderecos();
        assertEquals(expResult, result);
    }
}
