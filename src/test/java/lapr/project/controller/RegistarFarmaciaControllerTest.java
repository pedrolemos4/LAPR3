package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EnderecoDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
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
public class RegistarFarmaciaControllerTest {
    
    private FarmaciaDB farmaciaDBMock;
    private ParqueDB parqueDBMock;
    private EnderecoDB enderecoDBMock;
    private RegistarFarmaciaController instance;
    
    public RegistarFarmaciaControllerTest() {
    }
    
    @BeforeEach
    void setUp() throws SQLException {
        farmaciaDBMock = mock(FarmaciaDB.class);
        parqueDBMock = mock(ParqueDB.class);
        enderecoDBMock = mock(EnderecoDB.class);
        instance = new RegistarFarmaciaController(farmaciaDBMock, parqueDBMock, enderecoDBMock);
        
    }

    /**
     * Test of getListaFarmacias method, of class RegistarFarmaciaController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaFarmacias() throws SQLException {
        System.out.println("getListaFarmacias");
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(new Farmacia(123456789));
        when(farmaciaDBMock.getLstFarmacias()).thenReturn(expResult);
        List<Farmacia> result = instance.getListaFarmacias();
        assertEquals(expResult, result);

    }

    /**
     * Test of novaFarmacia method, of class RegistarFarmaciaController.
     */
    @Test
    public void testNovaFarmacia(){
        System.out.println("novaFarmacia");
        int nif = 123456789;
        Farmacia expResult = new Farmacia(nif);
        Farmacia result = instance.novaFarmacia(nif);
        assertEquals(expResult, result);

    }

    /**
     * Test of novoParque method, of class RegistarFarmaciaController.
     */
    @Test
    public void testNovoParque(){
        System.out.println("novoParque");
        int nif = 123456789;
        String morada = "";
        int numMax = 0;
        Parque expResult = new Parque(nif, morada, numMax);
        Parque result = instance.novoParque(nif, morada, numMax);
        assertEquals(expResult, result);

    }

    /**
     * Test of novoEndereco method, of class RegistarFarmaciaController.
     */
    @Test
    public void testNovoEndereco() {
        System.out.println("novoEndereco");
        String morada = "";
        double latitude = 45.0;
        double longitude = 56.0;
        double altitude = 89.0;
        Endereco expResult = new Endereco(morada, latitude, longitude, altitude);
        Endereco result = instance.novoEndereco(morada, latitude, longitude, altitude);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of registaFarmacia method, of class RegistarFarmaciaController.
//     * @throws java.sql.SQLException
//     */
//    @Test
//    public void testRegistaFarmacia() throws SQLException {
//        System.out.println("registaFarmacia");
//        when(farmaciaDBMock.addFarmacia(new Farmacia(123456789))));
//        instance.registaFarmacia(farm);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of registaParque method, of class RegistarFarmaciaController.
//     */
//    @Test
//    public void testRegistaParque() throws SQLException {
//        System.out.println("registaParque");
//        Parque park = null;
//        RegistarFarmaciaController instance = new RegistarFarmaciaController();
//        instance.registaParque(park);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of registaEndereco method, of class RegistarFarmaciaController.
//     */
//    @Test
//    public void testRegistaEndereco() throws SQLException {
//        System.out.println("registaEndereco");
//        Endereco end = null;
//        RegistarFarmaciaController instance = new RegistarFarmaciaController();
//        instance.registaEndereco(end);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
