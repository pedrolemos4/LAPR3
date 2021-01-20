package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Graph;
import lapr.project.model.Parque;
import lapr.project.model.Veiculo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
    private VeiculoDB veiculoDB;
    private IniciarEntregaController instance;

    public IniciarEntregaControllerTest() {

    }

    @BeforeEach
    void setUp() throws SQLException {
        estafetaDB = mock(EstafetaDB.class);
        entregaDB = mock(EntregaDB.class);
        encomendaDB = mock(EncomendaDB.class);
        veiculoDB = mock(VeiculoDB.class);
        enderecoDB = mock(EnderecoDB.class);
        instance = new IniciarEntregaController(entregaDB, encomendaDB, estafetaDB, enderecoDB, veiculoDB);
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

//    /**
//     * Test of getVeiculoById method, of class IniciarEntregaController.
//     */
//    @Test
//    public void testGetVeiculoById() {
//        System.out.println("getVeiculoById");
//        int idVeiculo = 1;
//        Veiculo expResult = new Veiculo(1, "descricao", "drone", 100,74, 15, 47, 47, 85, 86);
//        when(veiculoDB.getVeiculoById(idVeiculo)).thenReturn(expResult);
//        Veiculo result = instance.getVeiculoById(idVeiculo);
//        assertEquals(expResult, result);
//
//    }

    /**
     * Test of getEstafeta method, of class IniciarEntregaController.
     */
    @Test
    public void testGetEstafeta() {
        System.out.println("getEstafeta");
        Estafeta est = new Estafeta(123456789, "sd", "vfdada", 15, 45, "rs", 62);
        UserSession.getInstance().setUser(est);
        when(estafetaDB.getEstafetaByNIF(est.getNIF())).thenReturn(est);
        Estafeta result = instance.getEstafeta();
        assertEquals(est, result);

    }

    /**
     * Test of getEnderecoByNifCliente method, of class
     * IniciarEntregaController.
     */
    @Test
    public void testGetEnderecoByNifCliente() {
        System.out.println("getEnderecoByNifCliente");
        int nif = 123456789;
        Cliente c = new Cliente(nif, 15, "df", 12);
        c.setEnderecoMorada("df");
        Endereco expResult = new Endereco("df", 56, 84, 12);
        when(enderecoDB.getEnderecoByNifCliente(c.getClienteNIF())).thenReturn(expResult);
        Endereco result = instance.getEnderecoByNifCliente(nif);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of getEnderecoParque method, of class IniciarEntregaController.
//     */
//    @Test
//    public void testGetEnderecoParque() {
//        System.out.println("getEnderecoParque");
//        Parque p = new Parque(123456789, "df", 15, "drones");
//        Endereco expResult = new Endereco("df", 56, 84, 12);
//        when(enderecoDB.getEnderecoParque()).thenReturn(expResult);
//        assertEquals(expResult, instance.getEnderecoParque());
//
//    }

    /**
     * Test of generateGraph method, of class IniciarEntregaController.
     */
//    @Test
//    public void testGenerateGraph() {
//        System.out.println("generateGraph");
//        List<Endereco> listEnderecos = new LinkedList<>();
//        Endereco e1 = new Endereco("dfad", 23, 34, 1);
//        listEnderecos.add(e1);
//        Endereco e2 = new Endereco("hte", 3, 5, 2);
//        listEnderecos.add(e2);
//        Endereco e3 = new Endereco("rrs", 34, 111, 34);
//        listEnderecos.add(e3);
//        Estafeta est = new Estafeta(123456789, 1, 15);
//        Veiculo veiculo = new Veiculo(1, "fsss", "scooter", 34, 12, 34, 45, 75, 12, 54);
//        double pesoTotal = 24.0;
//        Graph<Endereco, Double> expResult = new Graph<>(true) ;
//        expResult.insertVertex(e1);
//        expResult.insertVertex(e2);
//        expResult.insertVertex(e3);
//        when(entregaDB.generateGraph(listEnderecos, est, veiculo, pesoTotal)).thenReturn(expResult);
//        Graph<Endereco, Double> result = instance.generateGraph(listEnderecos, est, veiculo, pesoTotal);
//
//        assertEquals(expResult, result);
//
//    }

}
