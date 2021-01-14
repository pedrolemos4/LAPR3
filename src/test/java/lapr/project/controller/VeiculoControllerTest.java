package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.VeiculoDB;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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
        s = new Veiculo("","drone",100, 23, 45, 3, 435, 34, 1);
        when(veiculoDBMock.addVeiculo(s)).thenReturn(1);
        
    }

    
    /**
     * Test of addVeiculo method, of class VeiculoController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddVeiculo() throws SQLException{
        System.out.println("addScooter");
        String descricao = "";
        String tipo = "";
        int capacidade = 100;
        double percentagemBateria = 0.0;
        double pesoMaximo = 0.0;
        double pesoVeiculo = 0.0;
        double potencia = 0.0;
        double areaFrontal = 0.0;
        int estado = 0;
        Veiculo s = new Veiculo(descricao,tipo,capacidade, percentagemBateria, 
                pesoMaximo, pesoVeiculo, potencia, areaFrontal, estado);
        when(veiculoDBMock.addVeiculo(s)).thenReturn(1);
        instance.addVeiculo(s.getDescricao(),s.getTipo(),s.getCapacidade(), 
                s.getPercentagemBateria(), s.getPesoMaximo(), s.getPesoVeiculo(),
                s.getPotencia(), s.getAreaFrontal(), s.getEstadoVeiculo().getId());
        s.setId(1);
        assertEquals(1, s.getId());

    }

    /**
     * Test of updateScooter method, of class VeiculoController.
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
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaVeiculo()  throws SQLException {
        System.out.println("getListaVeiculo");
        Veiculo veiculo = new Veiculo("","scooter",100, 45, 56, 48, 486, 45, 1);
        List<Veiculo> expResult = new ArrayList<>();
        expResult.add(veiculo);
        when(veiculoDBMock.getListaVeiculo()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaVeiculo());
    }

    /**
     * Test of getVeiculoById method, of class VeiculoController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetVeiculoById()  throws SQLException {
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
        Veiculo e = new Veiculo(descricao,tipo,capacidade, percentagemBateria,
                pesoMaximo, pesoVeiculo, potencia, areaFrontal, estado);
        e.setId(id);
        when(veiculoDBMock.getVeiculoById(id)).thenReturn(e);
        assertEquals(e, instance.getVeiculoById(id));

    }

    /**
     * Test of removeVeiculo method, of class VeiculoController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemoveVeiculo() throws SQLException {
        System.out.println("removeVeiculo");
        int idVeiculo = 1;
        Veiculo veiculo = new Veiculo("","scooter",100, 45, 56, 48, 486, 45, 1);
        veiculo.setId(idVeiculo);
        when(veiculoDBMock.removeVeiculo(idVeiculo)).thenReturn(false);
        assertEquals(false, instance.removeVeiculo(idVeiculo));

    }
    
    /**
     * Test of removeVeiculo method, of class VeiculoController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemoveVeiculo1() throws SQLException {
        System.out.println("removeVeiculo1");
        int idVeiculo = 1;
        Veiculo veiculo = new Veiculo("","scooter",100, 45, 56, 48, 486, 45, 1);
        veiculo.setId(idVeiculo);
        when(veiculoDBMock.removeVeiculo(idVeiculo)).thenReturn(true);
        assertEquals(true, instance.removeVeiculo(idVeiculo));

    }
    
}
