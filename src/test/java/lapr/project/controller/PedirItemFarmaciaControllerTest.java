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
        Graph<Farmacia, Double> expResult = new Graph<>(false);
        when(fdb.generateGrafo(farms)).thenReturn(expResult);
        Graph<Farmacia, Double> result = instance.generateGrafo(farms);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFarmaciaProxima method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testGetFarmaciaProxima() {
        System.out.println("getFarmaciaProxima");
        Graph<Farmacia, Double> generateGrafo = new Graph<>(false);
        int nif = 0;
        int expResult = 0;
        Farmacia f = new Farmacia(123,"","");
        
        Farmacia f1 = new Farmacia(1234,"","");
        int result = instance.getFarmaciaProxima(generateGrafo, nif);
        assertEquals(expResult, result);
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
     * Test of getLstFarmacias method, of class PedirItemFarmaciaController.
     */
    @Test
    public void testGetLstFarmacias() {
        System.out.println("getLstFarmacias");
        List<Farmacia> expResult = new ArrayList<>();
        List<Farmacia> result = instance.getLstFarmacias();
        assertEquals(expResult, result);
    }
}