package lapr.project.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Graph;
import lapr.project.model.Utilizador;
import lapr.project.model.Veiculo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
/**
 *
 * @author beatr
 */
public class RegistarEntregaControllerTest {
    
    private UtilizadorDB utilizadorDB;
    private FarmaciaDB farmaciaDB;
    private EstafetaDB estafetaDB;
    private EntregaDB entregaDB;
    private EncomendaDB encomendaDB;
    private VeiculoDB veiculoDB;
    private EnderecoDB enderecoDB;
    private EmailDB emailDB;
    private ClienteDB clienteDB;
    private RegistarEntregaController instance;
    
    public RegistarEntregaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws SQLException {
        utilizadorDB = mock(UtilizadorDB.class);
        farmaciaDB = mock(FarmaciaDB.class);
        estafetaDB = mock(EstafetaDB.class);
        entregaDB = mock(EntregaDB.class);
        encomendaDB = mock(EncomendaDB.class);
        veiculoDB = mock(VeiculoDB.class);
        enderecoDB = mock(EnderecoDB.class);
        emailDB = mock(EmailDB.class);
        clienteDB = mock(ClienteDB.class);
        instance = new RegistarEntregaController(utilizadorDB, farmaciaDB, estafetaDB, entregaDB, encomendaDB, veiculoDB, enderecoDB, emailDB, clienteDB);
        
    }

    @After
    public void tearDown() throws Exception {
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
        when(estafetaDB.getEstafetaByNIF(est.getNIF())).thenReturn(est);
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
    public void testAddEntrega() throws SQLException, ParseException {
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

    /**
     * Test of generateGraph method, of class RegistarEntregaController.
     */
    @Test
    public void testGenerateGraph() {
        System.out.println("generateGraph");
        List<Endereco> listEnderecos = new LinkedList<>();
        Endereco e1 = new Endereco("dfad", 23, 34, 1);
        listEnderecos.add(e1);
        Endereco e2 = new Endereco("hte", 3, 5, 2);
        listEnderecos.add(e2);
        Endereco e3 = new Endereco("rrs", 34, 111, 34);
        listEnderecos.add(e3);
        Estafeta est = new Estafeta(123456789, 1, 15);
        Veiculo veiculo = new Veiculo(1, "fsss", "scooter", 34, 12, 34, 45, 75, 12, 54);
        double pesoTotal = 24.0;
        Graph<Endereco, Double> expResult = new Graph<>(true) ;
        expResult.insertVertex(e1);
        expResult.insertVertex(e2);
        expResult.insertVertex(e3);
        when(entregaDB.generateGraph(listEnderecos, est, veiculo, pesoTotal)).thenReturn(expResult);
        Graph<Endereco, Double> result = instance.generateGraph(listEnderecos, est, veiculo, pesoTotal);

        assertEquals(expResult, result);

    }

    /**
     * Test of getPath method, of class RegistarEntregaController.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Graph<Endereco, Double> graph = new Graph<>(true) ;
        List<Endereco> listEnderecos = new LinkedList<>();
        Endereco e1 = new Endereco("dfad", 23, 34, 1);
        listEnderecos.add(e1);
        Endereco e2 = new Endereco("hte", 3, 5, 2);
        listEnderecos.add(e2);
        Endereco e3 = new Endereco("rrs", 34, 111, 34);
        listEnderecos.add(e3);
        List<Endereco> finalShortPath = new LinkedList<>();
        Endereco origem = e1;
        double energia = 0.0;
        double expResult = 0.0;
        RegistarEntregaController teste = new RegistarEntregaController(new UtilizadorDB(), new FarmaciaDB(), new EstafetaDB(), new EntregaDB(), new EncomendaDB(), new VeiculoDB(), new EnderecoDB(), new EmailDB(), new ClienteDB());
        double result = teste.getPath(graph, listEnderecos, finalShortPath, origem, energia);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getFarmaciaByNif method, of class RegistarEntregaController.
     */
    @Test
    public void testGetFarmaciaByNif() {
        System.out.println("getFarmaciaByNif");
        int nifFarmacia = 123456789;
        Farmacia expResult = new Farmacia(123456789, "rhb", "evfg");
        when(farmaciaDB.getFarmaciaByNIF(nifFarmacia)).thenReturn(expResult);
        Farmacia result = instance.getFarmaciaByNif(nifFarmacia);
        assertEquals(expResult, result);

    }

    /**
     * Test of enviarNotaCliente method, of class RegistarEntregaController.
     */
    @Test
    public void testEnviarNotaCliente() {
        System.out.println("enviarNotaCliente");
        Farmacia farmacia = new Farmacia(123456789, "sert", "vfd");
        Cliente c = new Cliente(123456789, "bf", "fbh", 47, 85, "fhg", 34, "dfbg");
        boolean expResult = false;
        when(emailDB.sendEmail(farmacia.getEmail(), c.getEmail(), "dv", "dbg")).thenReturn(expResult);
        boolean result = instance.enviarNotaCliente(farmacia, c);
        assertEquals(expResult, result);

    }

    /**
     * Test of getClienteByEndereco method, of class RegistarEntregaController.
     */
    @Test
    public void testGetClienteByEndereco() {
        System.out.println("getClienteByEndereco");
        Endereco end = new Endereco("vnrj", 34, 32, 12);
        Cliente expResult = new Cliente(123456789, "bf", "fbh", 47, 85, "fhg", 34, "dfbg");
        when(clienteDB.getClienteByMorada(end.getMorada())).thenReturn(expResult);
        Cliente result = instance.getClienteByEndereco(end);
        assertEquals(expResult, result);

    }

    /**
     * Test of getDuracaoPercurso method, of class RegistarEntregaController.
     */
    @Test
    public void testGetDuracaoPercurso() throws ParseException {
        System.out.println("getDuracaoPercurso");        
        List<Endereco> finalShortPath = new LinkedList<>();
        Endereco e1 = new Endereco("dfad", 23, 34, 1);
        finalShortPath.add(e1);
        Endereco e2 = new Endereco("hte", 3, 5, 2);
        finalShortPath.add(e2);
        Endereco e3 = new Endereco("rrs", 34, 111, 34);
        finalShortPath.add(e3);
        Veiculo veiculo = new Veiculo(1, "fsss", "scooter", 34, 12, 34, 45, 75, 12, 54);
        String expResult = "17:06:00";
        RegistarEntregaController teste = new RegistarEntregaController(new UtilizadorDB(), new FarmaciaDB(), new EstafetaDB(), new EntregaDB(), new EncomendaDB(), new VeiculoDB(), new EnderecoDB(), new EmailDB(), new ClienteDB());
        String result = teste.getDuracaoPercurso(finalShortPath, veiculo);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateEntrega method, of class RegistarEntregaController.
     */
    @Test
    public void testUpdateEntrega() throws Exception {
        System.out.println("updateEntrega");
        Entrega entrega = new Entrega("15/02/2001", "15/02/2001", 2, 34);
        boolean expResult = true;
        when(entregaDB.updateEntrega(entrega)).thenReturn(expResult);
        boolean result = instance.updateEntrega(entrega);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of updateEntrega method, of class RegistarEntregaController.
     */
    @Test
    public void testUpdateEntrega1() throws Exception {
        System.out.println("updateEntrega1");
        Entrega entrega = new Entrega("15/02/2001", "15/02/2001", 2, 34);
        boolean expResult = false;
        when(entregaDB.updateEntrega(entrega)).thenReturn(expResult);
        boolean result = instance.updateEntrega(entrega);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateEncomenda method, of class RegistarEntregaController.
     */
    @Test
    public void testUpdateEncomenda1() throws Exception {
        System.out.println("updateEncomenda1");
        Encomenda encomenda = new Encomenda(123456789, "12/01/2015", 51, 74, 85, 7);
        boolean expResult = false;
        when(encomendaDB.updateEncomenda(encomenda)).thenReturn(expResult);
        boolean result = instance.updateEncomenda(encomenda);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of updateEncomenda method, of class RegistarEntregaController.
     */
    @Test
    public void testUpdateEncomenda2() throws Exception {
        System.out.println("updateEncomenda2");
        Encomenda encomenda = new Encomenda(123456789, "12/01/2015", 51, 74, 85, 7);
        boolean expResult = true;
        when(encomendaDB.updateEncomenda(encomenda)).thenReturn(expResult);
        boolean result = instance.updateEncomenda(encomenda);
        assertEquals(expResult, result);

    }

    /**
     * Test of getUtilizadorByNif method, of class RegistarEntregaController.
     */
    @Test
    public void testGetUtilizadorByNif() {
        System.out.println("getUtilizadorByNif");
        int nif = 123456678;
        Cliente expResult = new Cliente(nif, 12, "fg", 15);
        when(utilizadorDB.getByID(nif)).thenReturn(expResult);
        Utilizador result = instance.getUtilizadorByNif(nif);
        assertEquals(expResult, result);

    }
}
