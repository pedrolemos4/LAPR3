package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/**
 *
 * @author beatr
 */
public class ScooterControllerTest {
    
    private ScooterController instance;
    private ScooterDB scooterDBMock;
    private Scooter s;
    
    public ScooterControllerTest() {
    }
    
    @BeforeEach
    void setUp() throws SQLException {
        scooterDBMock = mock(ScooterDB.class);
        instance = new ScooterController(scooterDBMock);
        s = new Scooter("", 23, 45, 3, 435, 34, 1);
        when(scooterDBMock.addScooter(s)).thenReturn(1);
        
    }

    
    /**
     * Test of addScooter method, of class ScooterController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddScooter() throws SQLException{
        System.out.println("addScooter");
        String descricao = "";
        int percentagemBateria = 0;
        double pesoMaximo = 0.0;
        double pesoScooter = 0.0;
        double potencia = 0.0;
        double areaFrontal = 0.0;
        int estado = 0;
        Scooter s = new Scooter(descricao, percentagemBateria, pesoMaximo, pesoScooter, potencia, areaFrontal, estado);
        when(scooterDBMock.addScooter(s)).thenReturn(1);
        assertEquals(1, scooterDBMock.addScooter(s));

    }

    /**
     * Test of updateScooter method, of class ScooterController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpdateScooter() throws SQLException {
        System.out.println("updateScooter");
        when(scooterDBMock.updateScooter(s)).thenReturn(false);
        assertEquals(false, instance.updateScooter(s));

    }

    /**
     * Test of getListaScooter method, of class ScooterController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaScooter()  throws SQLException {
        System.out.println("getListaScooter");
        Scooter scooter = new Scooter("", 45, 56, 48, 486, 45, 1);
        List<Scooter> expResult = new ArrayList<>();
        expResult.add(scooter);
        when(scooterDBMock.getListaScooter()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaScooter());
    }

    /**
     * Test of getScooterById method, of class ScooterController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetScooterById()  throws SQLException {
        System.out.println("getScooterById");
        int id = 2;
        String descricao = "";
        int percentagemBateria = 0;
        double pesoMaximo = 0.0;
        double pesoScooter = 0.0;
        double potencia = 0.0;
        double areaFrontal = 0.0;
        int estado = 0;
        Scooter e = new Scooter(descricao, percentagemBateria, pesoMaximo, pesoScooter, potencia, areaFrontal, estado);
        e.setId(id);
        when(scooterDBMock.getScooterById(id)).thenReturn(e);
        assertEquals(e, instance.getScooterById(id));

    }

    /**
     * Test of removeScooter method, of class ScooterController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemoveScooter() throws SQLException {
        System.out.println("removeScooter");
        int idScooter = 1;
        Scooter scooter = new Scooter("", 45, 56, 48, 486, 45, 1);
        scooter.setId(idScooter);
        when(scooterDBMock.removeScooter(idScooter)).thenReturn(false);
        assertEquals(false, instance.removeScooter(idScooter));

    }
    
}
