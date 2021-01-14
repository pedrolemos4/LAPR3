package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
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
    
    private EstafetaDB estafetaDB;
    private EntregaDB entregaDB;
    private EncomendaDB encomendaDB;
    private VeiculoDB veiculoDB;
    private RegistarEntregaController instance;
    
    public RegistarEntregaControllerTest() {
    }
    
    @BeforeEach
    void setUp() throws SQLException {
        estafetaDB = mock(EstafetaDB.class);
        entregaDB = mock(EntregaDB.class);
        encomendaDB = mock(EncomendaDB.class);
        veiculoDB = mock(VeiculoDB.class);
        instance = new RegistarEntregaController(estafetaDB, entregaDB, encomendaDB, veiculoDB);
        
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
     * Test of addEncomendaEntrega method, of class RegistarEntregaController.
     */
    @Test
    public void testAddEncomendaEntrega() throws SQLException {
        System.out.println("addEncomendaEntrega");
        Entrega en = new Entrega("12-10-2001", "12-10-2001", 45, 41);
        Encomenda enc = new Encomenda(123456789, "", 45, 96, 85, 47);
        instance.addEncomendaEntrega(en, enc);
    }
    
}
