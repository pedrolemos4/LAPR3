package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;
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
    private ScooterDB scooterDB;
    private RegistarEntregaController instance;
    
    public RegistarEntregaControllerTest() {
    }
    
    @BeforeEach
    void setUp() throws SQLException {
        estafetaDB = mock(EstafetaDB.class);
        entregaDB = mock(EntregaDB.class);
        encomendaDB = mock(EncomendaDB.class);
        scooterDB = mock(ScooterDB.class);
        instance = new RegistarEntregaController(estafetaDB, entregaDB, encomendaDB, scooterDB);
        
    }

    /**
     * Test of getListScooter method, of class RegistarEntregaController.
     */
    @Test
    public void testGetListScooter() throws SQLException {
        System.out.println("getListScooter");
        List<Scooter> expResult = new ArrayList<>();
        expResult.add(new Scooter("sddd", 45, 89, 7, 52, 78, 85));
        when(scooterDB.getListaScooter()).thenReturn(expResult);
        assertEquals(expResult, instance.getListScooter());

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
     * Test of getScooter method, of class RegistarEntregaController.
     */
    @Test
    public void testGetScooter() throws SQLException {
        System.out.println("getScooter");
        int idScooter = 1;
        Scooter expResult = new Scooter(idScooter, "", 12, 45, 48, 56, 48, 89);
        when(scooterDB.getScooterById(idScooter)).thenReturn(expResult);
        Scooter result = instance.getScooter(idScooter);
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
    public void testAddEntrega() throws SQLException  {
        System.out.println("addEntrega");
        Entrega en = new Entrega("12-10-2001", "12-10-2001", 45, 41);
        instance.addEntrega(en);

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
