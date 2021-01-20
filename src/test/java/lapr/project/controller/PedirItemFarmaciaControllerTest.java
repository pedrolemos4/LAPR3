package lapr.project.controller;

import lapr.project.data.FarmaciaDB;
import lapr.project.data.TransferenciaDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EmailDB;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PedirItemFarmaciaControllerTest {
    private PedirItemFarmaciaController instance;
    private TransferenciaProduto trans;
    TransferenciaDB tdb;
    FarmaciaDB fdb;
    EmailDB emDB;

    @BeforeEach
    void setUp() throws SQLException {
        tdb = mock(TransferenciaDB.class);
        fdb = mock(FarmaciaDB.class);
        emDB = mock(EmailDB.class);
        instance = new PedirItemFarmaciaController(fdb,tdb,emDB);
        Farmacia farm = new Farmacia(1,"email","rua1");
        Farmacia farm2 = new Farmacia(1,"email","rua2");
        Produto prod = new Produto("prod",1,1);
        trans = new TransferenciaProduto(1, 1,2,1,1);
        when(tdb.realizaPedido(farm,farm2,prod,1)).thenReturn(true);
    }

    @Test
    void getFarmaciaByNIF() {
        System.out.println("getFarmaciaByNIF");
        int idFarm = 1;
        Farmacia expResult = new Farmacia(1,"email","rua1");
        when(fdb.getFarmaciaByNIF(idFarm)).thenReturn(expResult);
        Farmacia result = instance.getFarmaciaByNIF(idFarm);
        assertEquals(expResult, result);
    }

    @Test
    void realizaPedido() {
        boolean expResult = true;
        Farmacia f1 = new Farmacia(1,"email","rua");
        Farmacia f2 = new Farmacia(1,"email","rua");
        Produto prod = new Produto("prod",1,1);
        when(tdb.realizaPedido(f1,f2,prod,1)).thenReturn(expResult);
        assertEquals(expResult, instance.realizaPedido(f1,f2,prod,1));
    }
    
    @Test
    void realizaPedido1() {
        boolean expResult = false;
        Farmacia f1 = new Farmacia(1,"email","rua");
        Farmacia f2 = new Farmacia(1,"email","rua");
        Produto prod = new Produto("prod",1,1);
        when(tdb.realizaPedido(f1,f2,prod,1)).thenReturn(expResult);
        assertEquals(expResult, instance.realizaPedido(f1,f2,prod,1));
    }

    /**
     * Test of getListaFarmaciaByProduto method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testGetListaFarmaciaByProduto() {
        System.out.println("getListaFarmaciaByProduto");
        Produto p = new Produto();
        int quant = 0;
        Farmacia f = new Farmacia();
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(f);
        when(fdb.getLstFarmaciasByProdutos(p, quant)).thenReturn(expResult);
        List<Farmacia> result = instance.getListaFarmaciaByProduto(p, quant);
        assertEquals(expResult, result);
    }

    /**
     * Test of generateGrafo method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testGenerateGrafo() {
        System.out.println("generateGrafo");
        List<Farmacia> farms = new ArrayList<>();
        Graph<Endereco, Double> expResult = new Graph<>(false);
        when(fdb.generateGrafo()).thenReturn(expResult);
        Graph<Endereco, Double> result = instance.generateGrafo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFarmaciaProxima method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testGetFarmaciaProxima() {
        System.out.println("getFarmaciaProxima");
        Graph<Endereco, Double> generateGrafo = new Graph<>(false);
        int nif = 123;
        
        Endereco f = new Endereco("",123,4,12);
        Endereco f1 = new Endereco("a",123,4,12);
        Endereco f2 = new Endereco("3",123413123,412121,123333);
        
        Farmacia f5 = new Farmacia();
        f5.setMorada("a");
        
        generateGrafo.insertVertex(f);
        generateGrafo.insertVertex(f1);
        generateGrafo.insertVertex(f2);
        
        double as = 2;
        double asd = 3;
        
        generateGrafo.insertEdge(f, f1, as, 12);
        generateGrafo.insertEdge(f, f2, asd, 13);
        when(fdb.getFarmaciaProxima(generateGrafo,f)).thenReturn(f5);
        assertEquals(f5, instance.getFarmaciaProxima(generateGrafo, f));
    }

    /**
     * Test of enviaNotaEntrega method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testEnviaNotaEntrega() {
        System.out.println("enviaNotaEntrega");
        String email = "asd@gmail.com";
        String email1 = "dsa@gmail.com";
        boolean expResult = true;
        when(emDB.sendEmail(email, email1, "Adicionar Stock.", "Confirmamos que os produtos enviados foram recebidos.")).thenReturn(expResult);
        boolean result = instance.enviaNotaEntrega(email, email1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of enviaNotaEntrega method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testEnviaNotaEntrega1() {
        System.out.println("enviaNotaEntrega1");
        String email = "";
        String email1 = "dsa@gmail.com";
        boolean expResult = false;
        when(emDB.sendEmail("asd@hotmail.com", email1, "Adicionar Stock.", "Confirmamos que os produtos enviados foram recebidos.")).thenReturn(expResult);
        boolean result = instance.enviaNotaEntrega(email, email1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLstFarmacias method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testGetLstFarmacias() {
        System.out.println("getLstFarmacias");
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(new Farmacia(123456789, "dgv","dv"));
        when(fdb.getLstFarmacias()).thenReturn(expResult);
        List<Farmacia> result = instance.getLstFarmacias();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFarmaciaByNIF method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testGetFarmaciaByNIF() {
        System.out.println("getFarmaciaByNIF");
        int nif = 123;
        Farmacia expResult = new Farmacia();
        expResult.setNIF(nif);
        when(fdb.getFarmaciaByNIF(nif)).thenReturn(expResult);
        Farmacia result = instance.getFarmaciaByNIF(nif);
        assertEquals(expResult, result);
    }

    /**
     * Test of realizaPedido method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testRealizaPedido() {
        System.out.println("realizaPedido");
        Farmacia fOri = new Farmacia();
        Farmacia fDest = new Farmacia();
        Produto prod = new Produto();
        int quantidade = 0;
        boolean expResult = true;
        when(tdb.realizaPedido(fOri,fDest,prod,quantidade)).thenReturn(expResult);
        assertEquals(expResult, instance.realizaPedido(fOri, fDest, prod, quantidade));
    }
    
    /**
     * Test of realizaPedido method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testRealizaPedido1() {
        System.out.println("realizaPedido1");
        Farmacia fOri = new Farmacia();
        Farmacia fDest = new Farmacia();
        Produto prod = new Produto();
        int quantidade = 500;
        boolean expResult = false;
        when(tdb.realizaPedido(fOri,fDest,prod,quantidade)).thenReturn(expResult);
        assertEquals(expResult, instance.realizaPedido(fOri, fDest, prod, quantidade));
    }
    
}