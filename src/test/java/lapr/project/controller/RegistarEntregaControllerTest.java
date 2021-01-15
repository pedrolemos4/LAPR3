package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Veiculo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
/**
 *
 * @author beatr
 */
public class RegistarEntregaControllerTest {
    
    private FarmaciaDB farmaciaDB;
    private EstafetaDB estafetaDB;
    private EntregaDB entregaDB;
    private EncomendaDB encomendaDB;
    private VeiculoDB veiculoDB;
    private EnderecoDB enderecoDB;
    private RegistarEntregaController instance;
    
    public RegistarEntregaControllerTest() {
    }

    @BeforeEach
    void setUp() throws SQLException {
        farmaciaDB = mock(FarmaciaDB.class);
        estafetaDB = mock(EstafetaDB.class);
        entregaDB = mock(EntregaDB.class);
        encomendaDB = mock(EncomendaDB.class);
        veiculoDB = mock(VeiculoDB.class);
        enderecoDB = mock(EnderecoDB.class);
        instance = new RegistarEntregaController(farmaciaDB, estafetaDB, entregaDB, encomendaDB, veiculoDB, enderecoDB);
        
    }

    /**
     * Test of getListVeiculo method, of class RegistarEntregaController.
     */
    @Test
    public void testGetListVeiculo() throws SQLException {
        System.out.println("getListVeiculo");
        List<Veiculo> expResult = new ArrayList<>();
        expResult.add(new Veiculo("sddd","drone",100, 45, 89, 7, 52, 78, 85));
        when(veiculoDB.getListaVeiculo()).thenReturn(expResult);
        assertEquals(expResult, instance.getListVeiculo());

    }

    /**
     * Test of getEstafeta method, of class RegistarEntregaController.
     */
    @Test
    public void testGetEstafeta() throws SQLException {
        System.out.println("getEstafeta");
        Estafeta est = new Estafeta(123456789, "sd", "vfdada", 15, 45, "rs", 62);
        UserSession.getInstance().setUser(est);
        when(estafetaDB.getEstafetaByEmail(est.getEmail())).thenReturn(est);
        Estafeta result = instance.getEstafeta();
        assertEquals(est, result);

    }

    /**
     * Test of getVeiculo method, of class RegistarEntregaController.
     */
    @Test
    public void testGetVeiculo() throws SQLException {
        System.out.println("getVeiculo");
        int idVeiculo = 1;
        Veiculo expResult = new Veiculo(idVeiculo, "descricao","scooter",100, 12, 45, 48, 56, 48, 89);
        when(veiculoDB.getVeiculoById(idVeiculo)).thenReturn(expResult);
        Veiculo result = instance.getVeiculo(idVeiculo);
        assertEquals(expResult, result);

    }

    /**
     * Test of getListaEncomenda method, of class RegistarEntregaController.
     */
    @Test
    public void testGetListaEncomenda() throws SQLException {
        System.out.println("getListaEncomenda");
        List<Encomenda> expResult = new ArrayList<>();
        expResult.add(new Encomenda(123456789, "12-02-2001", 56, 48, 47, 89));
        
        when(encomendaDB.getListaEncomenda()).thenReturn(expResult);
        List<Encomenda> result = instance.getListaEncomenda();
        assertEquals(expResult, result);

    }

    /**
     * Test of addEntrega method, of class RegistarEntregaController.
     */
    @Test
    public void testAddEntrega() throws SQLException {
        System.out.println("addEntrega");
        String dataInicio = "12/11/2015";
        String dataFim = null;
        int idVeiculo = 1;
        int idEstafeta = 3;
        Entrega expResult = new Entrega(dataInicio, dataFim, idVeiculo, idEstafeta);
        when(entregaDB.addEntrega(expResult)).thenReturn(1);
        instance.addEntrega(expResult.getDataInicio(),expResult.getDataFim(), expResult.getIdVeiculo(), expResult.getidEstafeta());
        expResult.setIdEntrega(1);
        assertEquals(1, expResult.getIdEntrega());

    }

    /**
     * Test of getLstFarmacias method, of class RegistarEntregaController.
     */
    @Test
    public void testGetLstFarmacias() {
        System.out.println("getLstFarmacias");
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(new Farmacia(123456789, "dvas", "dfs"));
        when(farmaciaDB.getLstFarmacias()).thenReturn(expResult);
        List<Farmacia> result = instance.getLstFarmacias();
        assertEquals(expResult, result);

    }

    /**
     * Test of getEnderecoOrigem method, of class RegistarEntregaController.
     */
    @Test
    public void testGetEnderecoOrigem() {
        System.out.println("getEnderecoOrigem");
        int nifFarmacia = 123456789;
        Endereco expResult = new Endereco("sxdc", 45, 47, 58);
        when(enderecoDB.getEnderecoByNifFarmacia(nifFarmacia)).thenReturn(expResult);
        Endereco result = instance.getEnderecoOrigem(nifFarmacia);
        assertEquals(expResult, result);

    }

    /**
     * Test of getEnderecoByNifCliente method, of class RegistarEntregaController.
     */
    @Test
    public void testGetEnderecoByNifCliente() {
        System.out.println("getEnderecoByNifCliente");
        int nif = 123456789;
        Endereco expResult = new Endereco("sxdc", 45, 47, 58);
        when(enderecoDB.getEnderecoByNifCliente(nif)).thenReturn(expResult);
        Endereco result = instance.getEnderecoByNifCliente(nif);
        assertEquals(expResult, result);

    }

    /**
     * Test of generateGraph method, of class RegistarEntregaController.
     */
    @Test
    public void testGenerateGraph() {
        System.out.println("generateGraph");
        List<Endereco> listEnderecos = new ArrayList<>();
        listEnderecos.add(new Endereco("df", 56, 84, 12));
        listEnderecos.add(new Endereco("de", 4, 23, 43));
        Estafeta est = new Estafeta(123456789, 1, 56);
        Veiculo veiculo = new Veiculo("fr", "veiculo", 100,15, 15, 85, 78, 45, 1);
        double pesoTotal = 12.0;
        List<Endereco> expResult = new LinkedList<>();
        List<Endereco> result = instance.generateGraph(listEnderecos, est, veiculo, pesoTotal);
        assertEquals(expResult, result);

    }

    /**
     * Test of addEncomendaEntrega method, of class RegistarEntregaController.
     */
    @Test
    public void testAddEncomendaEntrega() throws Exception {
        System.out.println("addEncomendaEntrega");
        Entrega e = new Entrega("15/02/2001", "15/02/2001", 2, 34);
        Encomenda enc = new Encomenda(123456789, "15/02/2001", 2, 3, 3, 2);
        boolean expResult = false;
        when(entregaDB.addEncomendaEntrega(e, enc)).thenReturn(expResult);
        boolean result = instance.addEncomendaEntrega(e, enc);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of addEncomendaEntrega method, of class RegistarEntregaController.
     */
    @Test
    public void testAddEncomendaEntrega1() throws Exception {
        System.out.println("addEncomendaEntrega1");
        Entrega e = new Entrega("15/02/2001", "15/02/2001", 2, 34);
        Encomenda enc = new Encomenda(123456789, "15/02/2001", 2, 3, 3, 2);
        boolean expResult = true;
        when(entregaDB.addEncomendaEntrega(e, enc)).thenReturn(expResult);
        boolean result = instance.addEncomendaEntrega(e, enc);
        assertEquals(expResult, result);

    }
    
}
