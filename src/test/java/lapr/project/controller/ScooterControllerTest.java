package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author beatr
 */
public class ScooterControllerTest {
        
    public ScooterControllerTest() {
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
        ScooterDB scooterDBMock = mock(ScooterDB.class);
        when(scooterDBMock.addScooter(s)).thenReturn(1);
        assertEquals(1, scooterDBMock.addScooter(s));

    }
    
    /**
     * Test of addScooter method, of class ScooterController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddScooter1() throws SQLException {
        System.out.println("addScooter1");
        String descricao = "";
        int percentagemBateria = 1;
        double pesoMaximo = 45.0;
        double pesoScooter = 56.0;
        double potencia = 12.0;
        double areaFrontal = 0.0;
        int estado = 1;
        Scooter s = new Scooter(descricao, percentagemBateria, pesoMaximo, pesoScooter, potencia, areaFrontal, estado);
        ScooterDB scooterDBMock = mock(ScooterDB.class);
        when(scooterDBMock.addScooter(s)).thenReturn(2);
        assertEquals(2, scooterDBMock.addScooter(s));

    }

    /**
     * Test of updateScooter method, of class ScooterController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpdateScooter() throws SQLException {
        System.out.println("updateScooter");
        Scooter scooter = new Scooter("", 45, 56, 48, 486, 45, 1);
        ScooterDB scooterDBMock = mock(ScooterDB.class);
        when(scooterDBMock.updateScooter(scooter)).thenReturn(false);
        assertEquals(false, scooterDBMock.updateScooter(scooter));

    }

    /**
     * Test of getListaScooter method, of class ScooterController.
     */
    @Test
    public void testGetListaScooter() {
        System.out.println("getListaScooter");
        Scooter scooter = new Scooter("", 45, 56, 48, 486, 45, 1);
        List<Scooter> expResult = new ArrayList<>();
        expResult.add(scooter);
        ScooterDB scooterDBMock = mock(ScooterDB.class);
        when(scooterDBMock.getListaScooter()).thenReturn(expResult);
        assertEquals(expResult, scooterDBMock.getListaScooter());
    }

    /**
     * Test of getScooterById method, of class ScooterController.
     */
    @Test
    public void testGetScooterById() {
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
        ScooterDB scooterDBMock = mock(ScooterDB.class);
        when(scooterDBMock.getScooterById(id)).thenReturn(e);
        assertEquals(e, scooterDBMock.getScooterById(id));

    }

    /**
     * Test of removeScooter method, of class ScooterController.
     */
    @Test
    public void testRemoveScooter() throws Exception {
        System.out.println("removeScooter");
        int idScooter = 1;
        Scooter scooter = new Scooter("", 45, 56, 48, 486, 45, 1);
        scooter.setId(idScooter);
        ScooterDB scooterDBMock = mock(ScooterDB.class);
        when(scooterDBMock.removeScooter(idScooter)).thenReturn(false);
        assertEquals(false, scooterDBMock.updateScooter(scooter));

    }
    
}
