package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Parque;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 *
 * @author beatr
 */
public class IniciarEntregaControllerTest {
    
    private EstafetaDB estafetaDB;
    private EntregaDB entregaDB;
    private EncomendaDB encomendaDB;
    private EnderecoDB enderecoDB;
    private ScooterDB scooterDB;
    private IniciarEntregaController instance;
    
    public IniciarEntregaControllerTest() {
        
    }
    
    @BeforeEach
    void setUp() throws SQLException {
        estafetaDB = mock(EstafetaDB.class);
        entregaDB = mock(EntregaDB.class);
        encomendaDB = mock(EncomendaDB.class);
        scooterDB = mock(ScooterDB.class);
        enderecoDB = mock(EnderecoDB.class);
        instance = new IniciarEntregaController(entregaDB, encomendaDB, estafetaDB, enderecoDB, scooterDB);
    }
    
    /**
     * Test of getListaEntregaByNifEstafeta method, of class IniciarEntregaController.
     */
    @Test
    public void testGetListaEntregaByNifEstafeta() {
        System.out.println("getListaEntregaByNifEstafeta");
        int nifEstafeta = 123456789;
        List<Entrega> expResult = new ArrayList<>();
        expResult.add(new Entrega("12-12-1004", "23-11-2001", nifEstafeta, 1));
        when(entregaDB.getListaEntregaByNifEstafeta(nifEstafeta)).thenReturn(expResult);
        List<Entrega> result = instance.getListaEntregaByNifEstafeta(nifEstafeta);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of getListaEncomendaById method, of class IniciarEntregaController.
     */
    @Test
    public void testGetListaEncomendaById() {
        System.out.println("getListaEncomendaById");
        int idEntrega = 1;
        List<Encomenda> expResult = new ArrayList<>();
        expResult.add(new Encomenda(123456789, "14-01-2015", 5, 56, 48, 85));
        when(encomendaDB.getListaEncomendaById(idEntrega)).thenReturn(expResult);
        List<Encomenda> result = instance.getListaEncomendaById(idEntrega);
        assertEquals(expResult, result);

    }

    /**
     * Test of getEntregaById method, of class IniciarEntregaController.
     */
    @Test
    public void testGetEntregaById() {
        System.out.println("getEntregaById");
        int idEntrega = 1;
        Entrega expResult = new Entrega("12-12-1004", "23-11-2001", 123456789, 1);
        when(entregaDB.getEntregaById(idEntrega)).thenReturn(expResult);
        Entrega result = instance.getEntregaById(idEntrega);
        assertEquals(expResult, result);

    }

    /**
     * Test of getScooterById method, of class IniciarEntregaController.
     */
    @Test
    public void testGetScooterById() {
        System.out.println("getScooterById");
        int idScooter = 1;
        Scooter expResult = new Scooter("de", 74, 15, 47, 47, 85, 86);
        when(scooterDB.getScooterById(idScooter)).thenReturn(expResult);
        Scooter result = instance.getScooterById(idScooter);
        assertEquals(expResult, result);

    }

    /**
     * Test of getEstafeta method, of class IniciarEntregaController.
     */
    @Test
    public void testGetEstafeta() {
        System.out.println("getEstafeta");
        Estafeta est = new Estafeta(123456789, "sd", "vfdada", 15, 45, "rs", 62);
        UserSession.getInstance().setUser(est);
        when(estafetaDB.getEstafetaByEmail(est.getEmail())).thenReturn(est);
        Estafeta result = instance.getEstafeta();
        assertEquals(est, result);

    }

    /**
     * Test of getEnderecoByNifCliente method, of class IniciarEntregaController.
     */
    @Test
    public void testGetEnderecoByNifCliente(){
        System.out.println("getEnderecoByNifCliente");
        int nif = 123456789;
        Cliente c = new Cliente(nif, 15, "df", 12);
        c.setEnderecoMorada("df");
        Endereco expResult = new Endereco("df", 56, 84, 12);
        when(enderecoDB.getEnderecoByNifCliente(c.getClienteNIF())).thenReturn(expResult);
        Endereco result = instance.getEnderecoByNifCliente(nif);
        assertEquals(expResult, result);

    }

    /**
     * Test of getEnderecoParque method, of class IniciarEntregaController.
     */
    @Test
    public void testGetEnderecoParque() {
        System.out.println("getEnderecoParque");
        Parque p = new Parque(123456789, "df", 15);
        Endereco expResult = new Endereco("df", 56, 84, 12);
        when(enderecoDB.getEnderecoParque()).thenReturn(expResult);
        assertEquals(expResult, instance.getEnderecoParque());

    }

    /**
     * Test of generateGraph method, of class IniciarEntregaController.
     */
    @Test
    public void testGenerateGraph() {
        System.out.println("generateGraph");
        List<Endereco> listEnderecos = new ArrayList<>();
        listEnderecos.add(new Endereco("df", 56, 84, 12));
        listEnderecos.add(new Endereco("de", 4, 23, 43));
        Estafeta est = new Estafeta(123456789, 1, 56);
        Scooter scooter = new Scooter("fr", 15, 15, 85, 78, 45, 1);
        double pesoTotal = 12.0;
        List<Endereco> expResult = new LinkedList<>();
        List<Endereco> result = instance.generateGraph(listEnderecos, est, scooter, pesoTotal);
        assertEquals(expResult, result);

    }
    
}
