/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Estacionamento;
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
public class InserirEstacionamentosControllerTest {

    private InserirEstacionamentosController instance;
    private InserirEstacionamentosController instance1;
    private EstacionamentosDB estacionamentoMock;
    private ParqueDB parqueMock;
    private FarmaciaDB farmaciaMock;
    private Estacionamento estac;

    @BeforeEach
    public void setUp() throws SQLException {
        instance = new InserirEstacionamentosController(new EstacionamentosDB(), new ParqueDB(), new FarmaciaDB());
        estacionamentoMock = mock(EstacionamentosDB.class);
        parqueMock = mock(ParqueDB.class);
        farmaciaMock = mock(FarmaciaDB.class);
        estac = new Estacionamento(123456789, 5, 1);
        instance1 = new InserirEstacionamentosController(estacionamentoMock, parqueMock, farmaciaMock);
        when(estacionamentoMock.addEstacionamento(estac)).thenReturn(true);
    }

    /**
     * Test of getListaEstacionamentos method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaEstacionamentos() throws SQLException {
        System.out.println("getListaEstacionamentos");
        Estacionamento estacionamento = new Estacionamento(1, 0, 0);
        List<Estacionamento> expResult = new ArrayList<>();
        expResult.add(estacionamento);
        when(estacionamentoMock.getLstEstacionamentos()).thenReturn(expResult);
        assertEquals(expResult, instance1.getListaEstacionamentos());
    }

    /**
     * Test of getListaEstacionamentosByParqueNif method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaEstacionamentosByFarmaciaNifParqueId() throws SQLException {
        System.out.println("getListaEstacionamentosByParqueNif");
        Farmacia farm = new Farmacia(123456789, "email", "rua1");
        farmaciaMock.addFarmacia(farm);
        Parque parque = new Parque(1, 123456789, 20, "drones");
        parqueMock.addParque(parque);
        Estacionamento estacionamento1 = new Estacionamento(1, 0, 123456789);
        Estacionamento estacionamento2 = new Estacionamento(2, 1, 123456789);
        List<Estacionamento> expResult = new ArrayList<>();
        expResult.add(estacionamento1);
        expResult.add(estacionamento2);
        when(estacionamentoMock.getListaEstacionamentosByFarmaciaNifParqueId(farm.getNIF(), parque.getIdParque())).thenReturn(expResult);
        assertEquals(expResult, instance1.getListaEstacionamentosByFarmaciaNifParqueId(farm.getNIF(), parque.getIdParque()));
    }

    /**
     * Test of novoEstacionamento method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testNovoEstacionamento() throws SQLException {
        System.out.println("novoEstacionamento");
        Estacionamento estacionamento = new Estacionamento(1, 0, 0);
        when(estacionamentoMock.novoEstacionamento(1, 0, 0)).thenReturn(estacionamento);
        assertEquals(estacionamento.toString(), instance.novoEstacionamento(estacionamento.getNumeroLote(), estacionamento.getCarregador(), estacionamento.getNIF()).toString());
    }

    /**
     * Test of registaEstacionamentos method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaEstacionamentos() throws SQLException {
        System.out.println("registaEstacionamentos");
        Estacionamento estacionamento = new Estacionamento(1, 0, 0);
        List<Estacionamento> lestac = new ArrayList<>();
        lestac.add(estacionamento);
        when(estacionamentoMock.registaEstacionamento(lestac)).thenReturn(true);
        assertEquals(true, instance1.registaEstacionamentos(lestac));
    }

    /**
     * Test of registaEstacionamentos method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegistaEstacionamentos1() throws SQLException {
        System.out.println("registaEstacionamentos1");
        Estacionamento estacionamento = new Estacionamento(1, 0, 0);
        List<Estacionamento> lestac = new ArrayList<>();
        lestac.add(estacionamento);
        when(estacionamentoMock.registaEstacionamento(lestac)).thenReturn(false);
        assertEquals(false, instance1.registaEstacionamentos(lestac));
    }

    /**
     * Test of getNumMaxParqueByNIF method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetNumMaxByFarmaciaNifParqueId() throws SQLException {
        System.out.println("getNumMaxParqueByNIF");
        Farmacia farm = new Farmacia(123456789, "email", "rua1");
        farmaciaMock.addFarmacia(farm);
        Parque parque = new Parque(1, 123456789, 20, "drones");
        parqueMock.addParque(parque);
        int expResult = parque.getNumeroMaximo();
        when(parqueMock.getNumMaxByFarmaciaNifParqueId(123456789, 1)).thenReturn(20);
        int result = instance1.getNumMaxByFarmaciaNifParqueId(123456789, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaFarmacias method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaFarmacias() throws SQLException {
        System.out.println("getListaFarmacias");
        Farmacia farmacia = new Farmacia(123456789, "email", "rua1");
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(farmacia);
        when(farmaciaMock.getLstFarmacias()).thenReturn(expResult);
        assertEquals(expResult, instance1.getListaFarmacias());
    }

    /**
     * Test of getListaParquesByFarmaciaNif method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetListaParquesByFarmaciaNif() throws SQLException {
        System.out.println("getListaParquesByFarmaciaNif");
        Farmacia farmacia1 = new Farmacia(123456789, "email", "rua1");
        Farmacia farmacia2 = new Farmacia(234, "email", "rua1");
        List<Farmacia> expResult = new ArrayList<>();
        expResult.add(farmacia1);
        expResult.add(farmacia2);
    //    when(estacionamentoMock.()).thenReturn(expResult);
        assertEquals(expResult, instance1.getListaFarmacias());
    }

    /**
     * Test of getFarmaciaByNIF method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetFarmaciaByNIF() throws SQLException {
        System.out.println("getFarmaciaByNIF");
        Farmacia farmacia = new Farmacia(1, "a", "a");
        when(farmaciaMock.getFarmaciaByNIF(1)).thenReturn(farmacia);
        assertEquals(farmacia, instance1.getFarmaciaByNIF(1));
    }

    /**
     * Test of getParqueByFarmaciaNifParqueId method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetParqueByFarmaciaNifParqueId() throws SQLException {
        System.out.println("getParqueByFarmaciaNifParqueId");
        int farmNIF = 0;
        int idParque = 0;
        InserirEstacionamentosController instance = null;
        Parque expResult = null;
        Parque result = instance.getParqueByFarmaciaNifParqueId(farmNIF, idParque);
        assertEquals(expResult, result);
    }
}
