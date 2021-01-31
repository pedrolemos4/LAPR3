package lapr.project.controller;

import lapr.project.data.VeiculoDB;
import lapr.project.model.Drone;
import lapr.project.model.Scooter;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author beatr
 */
public class VeiculoControllerTest {

    private VeiculoController instance;
    private VeiculoDB veiculoDBMock;
    private Veiculo s;

    public VeiculoControllerTest() {
    }

    @BeforeEach
    void setUp() throws SQLException {
        veiculoDBMock = mock(VeiculoDB.class);
        instance = new VeiculoController(veiculoDBMock);
        s = new Veiculo("", 100, 23, 45, 3, 435, 1,5);
        when(veiculoDBMock.addVeiculo(s)).thenReturn(1);

    }

    /**
     * Test of addVeiculo method, of class VeiculoController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddVeiculo() throws SQLException {
        System.out.println("addScooter");
        String descricao = "";
        int capacidade = 100;
        double percentagemBateria = 0.0;
        double pesoMaximo = 0.0;
        double pesoVeiculo = 0.0;
        double potencia = 0.0;
        int estado = 0;
        double areaFrontal = 0.0;
        Veiculo s = new Veiculo(descricao, capacidade, percentagemBateria,
                pesoMaximo, pesoVeiculo, potencia, estado, areaFrontal);
        when(veiculoDBMock.addVeiculo(s)).thenReturn(1);
        instance.addVeiculo(s.getDescricao(), s.getCapacidade(),
                s.getPercentagemBateria(), s.getPesoMaximo(), s.getPesoVeiculo(),
                s.getPotencia(), s.getEstadoVeiculo().getId(),s.getAreaFrontal());
        s.setId(1);
        assertEquals(1, s.getId());

    }
    
    /**
     * Test of addEntrega method, of class RegistarEntregaController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddVeiculo1() throws SQLException {
        System.out.println("addVeiculo1");
        String descricao = "";
        int capacidade = 100;
        double percentagemBateria = 23.0;
        double pesoMaximo = 34.0;
        double pesoVeiculo = 54.0;
        double potencia = 245.0;
        double areaFrontal = 50.0;
        int estado = 1;
        Veiculo expResult = new Veiculo(descricao, capacidade, percentagemBateria,
                pesoMaximo, pesoVeiculo, potencia, estado, areaFrontal);
        when(veiculoDBMock.addVeiculo(expResult)).thenReturn(1);
        Veiculo v = instance.addVeiculo(expResult.getDescricao(),expResult.getCapacidade(), expResult.getPercentagemBateria(), expResult.getPesoMaximo(),expResult.getPesoVeiculo(),expResult.getPotencia(), expResult.getEstadoVeiculo().getId(),expResult.getAreaFrontal());
        expResult.setId(1);
        v.setId(veiculoDBMock.addVeiculo(expResult));
        assertEquals(expResult.toString(), v.toString());
    }

    /**
     * Test of updateScooter method, of class VeiculoController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpdateVeiculo() throws SQLException {
        System.out.println("updateVeiculo");
        when(veiculoDBMock.updateVeiculo(s)).thenReturn(false);
        assertEquals(false, instance.updateVeiculo(s));

    }

    /**
     * Test of updateScooter method, of class VeiculoController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpdateVeiculo1() throws SQLException {
        System.out.println("updateVeiculo1");
        when(veiculoDBMock.updateVeiculo(s)).thenReturn(true);
        assertEquals(true, instance.updateVeiculo(s));

    }

    /**
     * Test of getListaVeiculo method, of class VeiculoController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaVeiculo() throws SQLException {
        System.out.println("getListaVeiculo");
        Veiculo veiculo = new Veiculo("", 100, 45, 56, 48, 486, 1,5);
        List<Veiculo> expResult = new ArrayList<>();
        expResult.add(veiculo);
        when(veiculoDBMock.getListaVeiculo()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaVeiculo());
    }

    /**
     * Test of getVeiculoById method, of class VeiculoController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetVeiculoById() throws SQLException {
        System.out.println("getVeiculoById");
        int id = 2;
        String descricao = "";
        String tipo = "";
        int capacidade = 0;
        double percentagemBateria = 0;
        double pesoMaximo = 0.0;
        double pesoVeiculo = 0.0;
        double potencia = 0.0;
        double areaFrontal = 0.0;
        int estado = 0;
        Veiculo e = new Veiculo(descricao, capacidade, percentagemBateria,
                pesoMaximo, pesoVeiculo, potencia, estado,areaFrontal);
        e.setId(id);
        when(veiculoDBMock.getVeiculoById(id)).thenReturn(e);
        assertEquals(e, instance.getVeiculoById(id));

    }

    /**
     * Test of removeVeiculo method, of class VeiculoController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemoveVeiculo() throws SQLException {
        System.out.println("removeVeiculo");
        int idVeiculo = 1;
        Veiculo veiculo = new Veiculo("", 100, 45, 56, 48, 486, 1,8);
        veiculo.setId(idVeiculo);
        when(veiculoDBMock.removeVeiculo(idVeiculo)).thenReturn(false);
        assertEquals(false, instance.removeVeiculo(idVeiculo));

    }

    /**
     * Test of removeVeiculo method, of class VeiculoController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemoveVeiculo1() throws SQLException {
        System.out.println("removeVeiculo1");
        int idVeiculo = 1;
        Veiculo veiculo = new Veiculo("", 100, 45, 56, 48, 486, 1,5);
        veiculo.setId(idVeiculo);
        when(veiculoDBMock.removeVeiculo(idVeiculo)).thenReturn(true);
        assertEquals(true, instance.removeVeiculo(idVeiculo));

    }

    /**
     * Test of registaDrone method, of class VeiculoController.
     */
    @Test
    public void testRegistaDrone() throws Exception {
        System.out.println("registaDrone");
        Veiculo ve = new Veiculo(1, "", 100, 45, 56, 48, 486, 1,3);
        int id = 1;
        double largura = 42;
        Drone dr = new Drone(ve, id, largura);
        Drone dr1 = new Drone(ve, 4, 56);
        boolean expResult = false;
        boolean result = instance.registaDrone(dr);
        when(veiculoDBMock.registaDrone(dr1)).thenReturn(false);
        assertEquals(false, instance.registaDrone(dr));
    }
    
    /**
     * Test of registaDrone method, of class VeiculoController.
     */
    @Test
    public void testRegistaDrone1() throws Exception {
        System.out.println("registaDrone1");
        Veiculo ve = new Veiculo(1, "", 100, 45, 56, 48, 486, 1,5);
        int id = 1;
        double largura = 42;
        Drone dr = new Drone(ve, id, largura);
        boolean expResult = true;
        boolean result = instance.registaDrone(dr);
        when(veiculoDBMock.registaDrone(dr)).thenReturn(true);
        assertEquals(true, instance.registaDrone(dr));
    }

    /**
     * Test of novoDrone method, of class VeiculoController.
     */
    @Test
    public void testNovoDrone() {
        System.out.println("novoDrone");
        Veiculo ve = new Veiculo(1, "", 100, 45, 56, 48, 486, 1,3);
        int id = 1;
        double largura = 42;
        Drone expResult = new Drone("", 100, 45, 56, 48, 486, 1, largura, id,3);
        expResult.setId(1);
        VeiculoController vC = new VeiculoController(new VeiculoDB());
        assertEquals(expResult.toString(), vC.novoDrone(ve, expResult.getId(),
                expResult.getHoverPower()).toString());
    }

    /**
     * Test of registaScooter method, of class VeiculoController.
     * @throws java.lang.Exception
     */
    @Test
    public void testRegistaScooter() throws Exception {
        System.out.println("registaScooter");
        Veiculo ve = new Veiculo(1, "", 100, 45, 56, 48, 486, 1,5);
        int id = 1;
        Scooter scooter = new Scooter(ve, id);
        boolean expResult = true;
        when(veiculoDBMock.registaScooter(scooter)).thenReturn(expResult);
        assertEquals(expResult, instance.registaScooter(scooter));
    }
    
    /**
     * Test of registaScooter method, of class VeiculoController.
     * @throws java.lang.Exception
     */
    @Test
    public void testRegistaScooter1() throws Exception {
        System.out.println("registaScooter1");
        Veiculo ve = new Veiculo(1, "", 100, 45, 56, 48, 486, 1,5);
        int id = 1;
        Scooter scooter = new Scooter(ve, id);
        Scooter scooter1 = new Scooter(ve, 4);
        boolean expResult = false;
        boolean result = instance.registaScooter(scooter);
        when(veiculoDBMock.registaScooter(scooter1)).thenReturn(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of novaScooter method, of class VeiculoController.
     */
    @Test
    public void testNovaScooter() {
        System.out.println("novaScooter");
        Veiculo ve = new Veiculo(1, "", 100, 45, 56, 48, 486, 1,3);
        int id = 1;
        Scooter expResult = new Scooter(ve, id);
        VeiculoController vC = new VeiculoController(new VeiculoDB());
        assertEquals(expResult.toString(), vC.novaScooter(ve, id).toString());
    }

    /**
     * Test of updateDrone method, of class VeiculoController.
     */
    @Test
    public void testUpdateDrone() throws Exception {
        System.out.println("updateDrone");
        int id = 1;
        double powerPro = 12.0;
        boolean expResult = false;
        when(veiculoDBMock.updateDrone(id, powerPro)).thenReturn(expResult);
        boolean result = instance.updateDrone(id, powerPro);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of updateDrone method, of class VeiculoController.
     */
    @Test
    public void testUpdateDrone1() throws Exception {
        System.out.println("updateDrone");
        int id = 1;
        double powerPro = 12.0;
        boolean expResult = true;
        when(veiculoDBMock.updateDrone(id, powerPro)).thenReturn(expResult);
        boolean result = instance.updateDrone(id, powerPro);
        assertEquals(expResult, result);

    }

    /**
     * Test of removeDrone method, of class VeiculoController.
     */
    @Test
    public void testRemoveDrone() throws Exception {
        System.out.println("removeDrone");
        int idVeiculo = 0;
        boolean expResult = false;
        when(veiculoDBMock.removeDrone(idVeiculo)).thenReturn(expResult);
        boolean result = instance.removeDrone(idVeiculo);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of removeDrone method, of class VeiculoController.
     */
    @Test
    public void testRemoveDrone1() throws Exception {
        System.out.println("removeDrone1");
        int idVeiculo = 1;
        boolean expResult = true;
        when(veiculoDBMock.removeDrone(idVeiculo)).thenReturn(expResult);
        boolean result = instance.removeDrone(idVeiculo);
        assertEquals(expResult, result);

    }

    /**
     * Test of removeScooter method, of class VeiculoController.
     */
    @Test
    public void testRemoveScooter() throws Exception {
        System.out.println("removeScooter");
        int idVeiculo = 2;
        boolean expResult = false;
        when(veiculoDBMock.removeScooter(idVeiculo)).thenReturn(expResult);
        boolean result = instance.removeScooter(idVeiculo);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of removeScooter method, of class VeiculoController.
     */
    @Test
    public void testRemoveScooter1() throws Exception {
        System.out.println("removeScooter1");
        int idVeiculo = 2;
        boolean expResult = true;
        when(veiculoDBMock.removeScooter(idVeiculo)).thenReturn(expResult);
        boolean result = instance.removeScooter(idVeiculo);
        assertEquals(expResult, result);

    }

}
