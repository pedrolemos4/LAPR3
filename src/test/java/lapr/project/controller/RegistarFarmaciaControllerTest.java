/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EnderecoDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author josep
 */
public class RegistarFarmaciaControllerTest {

    private RegistarFarmaciaController instance;
    private FarmaciaDB farmaciaMock;
    private EnderecoDB enderecoMock;
    private ParqueDB parqueMock;
    private Farmacia farm;

    @BeforeEach
    public void setUp() throws SQLException {
        farmaciaMock = mock(FarmaciaDB.class);
        instance = new RegistarFarmaciaController();
        parqueMock = mock(ParqueDB.class);
        enderecoMock = mock(EnderecoDB.class);
        farm = new Farmacia(123456789);
        when(farmaciaMock.addFarmacia(farm)).thenReturn(true);
    }

    /**
     * Test of getListaFarmacias method, of class RegistarFarmaciaController.
     */
    @Test
    public void testGetListaFarmacias() {
        System.out.println("getListaFarmacias");
        Farmacia farmacia = new Farmacia(123456789);
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(farmacia);
        when(farmaciaMock.getLstFarmacias()).thenReturn(expResult);
    }

    /**
     * Test of novaFarmacia method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovaFarmacia() throws SQLException {
        System.out.println("novaFarmacia");
        Farmacia farmacia = new Farmacia(123456789);
        when(farmaciaMock.novaFarmacia(123456789)).thenReturn(farmacia);
        assertEquals(farmacia, farmaciaMock.novaFarmacia(123456789));
    }

    /**
     * Test of novoParque method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoParque() throws SQLException {
        System.out.println("novoParque");
        Parque parque = new Parque(111111111, "algures", 20);
        when(parqueMock.novoParque(111111111, "algures", 20)).thenReturn(parque);
        assertEquals(parque, parqueMock.novoParque(111111111, "algures", 20));
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
        assertEquals(end, enderecoMock.novoEndereco("Rua do ISEP", 41.45, 30.58, 34.23));
    }

    /**
     * Test of registaFarmacia method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaFarmacia() throws SQLException {
        System.out.println("registaFarmacia");
        Farmacia farmacia = new Farmacia(123456789);
        when(farmaciaMock.registaFarmacia(farmacia)).thenReturn(true);
        assertEquals(true, farmaciaMock.registaFarmacia(farmacia));
    }

    /**
     * Test of registaParque method, of class RegistarFarmaciaController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaParque() throws SQLException {
        System.out.println("registaParque");
        Parque parque = new Parque(111111111, "algures", 20);
        when(parqueMock.registaParque(parque)).thenReturn(true);
        assertEquals(true, parqueMock.registaParque(parque));
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
        assertEquals(true, enderecoMock.registaEndereco(end));
    }

}