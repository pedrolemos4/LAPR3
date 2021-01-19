package lapr.project.controller;

import lapr.project.data.EnderecoDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;
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
 * @author josep
 */
public class RegistarFarmaciaControllerTest {

    private RegistarFarmaciaController instance;
    private RegistarFarmaciaController instance1;
    private FarmaciaDB farmaciaMock;
    private EnderecoDB enderecoMock;
    private ParqueDB parqueMock;
    private Farmacia farm;

    @BeforeEach
    public void setUp() throws SQLException {
        farmaciaMock = mock(FarmaciaDB.class);
        instance = new RegistarFarmaciaController(new FarmaciaDB(), new ParqueDB(), new EnderecoDB());
        parqueMock = mock(ParqueDB.class);
        enderecoMock = mock(EnderecoDB.class);
        farm = new Farmacia(123456789, "email", "rua1");
        instance1 = new RegistarFarmaciaController(farmaciaMock, parqueMock, enderecoMock);
        when(farmaciaMock.addFarmacia(farm)).thenReturn(true);
    }

    /**
     * Test of getListaFarmacias method, of class RegistarFarmaciaController.
     */
    @Test
    public void testGetListaFarmacias() {
        System.out.println("getListaFarmacias");
        Farmacia farmacia = new Farmacia(123456789, "email", "rua1");
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(farmacia);
        when(farmaciaMock.getLstFarmacias()).thenReturn(expResult);
        assertEquals(expResult, instance1.getListaFarmacias());
    }

    /**
     * Test of novaFarmacia method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovaFarmacia() throws SQLException {
        System.out.println("novaFarmacia");
        Farmacia farmacia = new Farmacia(123456789, "email", "rua1");
        when(farmaciaMock.novaFarmacia(123456789, "email", "rua1")).thenReturn(farmacia);
        assertEquals(farmacia.toString(), instance.novaFarmacia(farmacia.getNIF(), farmacia.getEmail(), farmacia.getMorada()).toString());
    }

    /**
     * Test of novoParque method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoParque() throws SQLException {
        System.out.println("novoParque");
        Parque parque = new Parque(111111111, 20, "drones", 1000);
        when(parqueMock.novoParque(111111111, 20, "drones", 1000)).thenReturn(parque);
        assertEquals(parque.toString(), instance.novoParque(parque.getNIF(), parque.getNumeroMaximo(), parque.getTipo(), parque.getMaxCap()).toString());
    }

    /**
     * Test of novoEndereco method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoEndereco() throws SQLException {
        System.out.println("novoEndereco");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.novoEndereco("Rua do ISEP", 41.45, 30.58, 34.23)).thenReturn(end);
        assertEquals(end.toString(), instance.novoEndereco(end.getMorada(), end.getLatitude(), end.getLongitude(), end.getAltitude()).toString());
    }

    /**
     * Test of registaFarmacia method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaFarmacia() throws SQLException {
        System.out.println("registaFarmacia");
        Farmacia farmacia = new Farmacia(123456789, "email1", "rua1");
        when(farmaciaMock.registaFarmacia(farmacia)).thenReturn(true);
        assertEquals(true, instance1.registaFarmacia(farmacia));
    }

    /**
     * Test of registaFarmacia method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaFarmacia1() throws SQLException {
        System.out.println("registaFarmacia1");
        Farmacia farmacia = new Farmacia(123456789, "email1", "rua1");
        when(farmaciaMock.registaFarmacia(farmacia)).thenReturn(false);
        assertEquals(false, instance1.registaFarmacia(farmacia));
    }

    /**
     * Test of registaParque method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaParques() throws SQLException {
        System.out.println("registaParques");
        List<Parque> expResult = new ArrayList<>();
        Parque parque = new Parque(111111111, 20, "drones", 1000);
        expResult.add(parque);
        when(parqueMock.registaParques(expResult)).thenReturn(true);
        assertEquals(true, instance1.registaParques(expResult));
    }

    /**
     * Test of registaParque method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaParques1() throws SQLException {
        System.out.println("registaParques1");
        List<Parque> expResult = new ArrayList<>();
        Parque parque = new Parque(111111111, 20, "drones", 1000);
        expResult.add(parque);
        when(parqueMock.registaParques(expResult)).thenReturn(false);
        assertEquals(false, instance1.registaParques(expResult));
    }

    /**
     * Test of registaEndereco method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaEndereco() throws SQLException {
        System.out.println("registaEndereco");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.registaEndereco(end)).thenReturn(true);
        assertEquals(true, instance1.registaEndereco(end));
    }

    /**
     * Test of registaEndereco method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaEndereco1() throws SQLException {
        System.out.println("registaEndereco1");
        Endereco end = new Endereco("Rua do ISEP", 41.45, 30.58, 34.23);
        when(enderecoMock.registaEndereco(end)).thenReturn(false);
        assertEquals(false, instance1.registaEndereco(end));
    }

    @Test
    void getListaParquesByFarmaciaNif() {
        System.out.println("getListaParquesByFarmaciaNif");
        Farmacia farmacia = new Farmacia(123456789, "email1", "rua1");
        Parque parque1 = new Parque(111111111, 20, "drones", 1000);
        Parque parque2 = new Parque(222222222, 15, "scooters", 1000);
        List<Parque> listParques = new ArrayList<>();
        listParques.add(parque1);
        listParques.add(parque2);
        when(parqueMock.getLstParquesByFarmaciaNif(farmacia.getNIF())).thenReturn(listParques);
        assertEquals(listParques, instance1.getListaParquesByFarmaciaNif(123456789));
    }

    @Test
    void getFarmaciaByNIF() {
        System.out.println("getFarmaciaByNIF");
        Farmacia farmacia = new Farmacia(1, "a", "a");
        when(farmaciaMock.getFarmaciaByNIF(1)).thenReturn(farmacia);

        assertEquals(farmacia, instance1.getFarmaciaByNIF(1));
    }

    @Test
    void getEnderecoByMorada() {
        System.out.println("getEnderecoByMorada");
        Endereco endereco = new Endereco("a", 1, 1, 1);
        when(enderecoMock.getEnderecoByMorada("a")).thenReturn(endereco);
        assertEquals(endereco, instance1.getEnderecoByMorada("a"));
    }
}
