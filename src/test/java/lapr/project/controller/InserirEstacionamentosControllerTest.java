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
import lapr.project.data.ParqueDB;
import lapr.project.model.Estacionamento;
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
    private Estacionamento estac;

    @BeforeEach
    public void setUp() throws SQLException {
        instance = new InserirEstacionamentosController(new EstacionamentosDB(), new ParqueDB());
        estacionamentoMock = mock(EstacionamentosDB.class);
        parqueMock = mock(ParqueDB.class);
        estac = new Estacionamento(123456789, 5, 1);
        instance1 = new InserirEstacionamentosController(estacionamentoMock, parqueMock);
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
    public void testGetListaEstacionamentosByParqueNif() throws SQLException {
        System.out.println("getListaEstacionamentosByParqueNif");
        Estacionamento estacionamento = new Estacionamento(1, 0, 0);
        List<Estacionamento> expResult = new ArrayList<>();
        expResult.add(estacionamento);
        when(estacionamentoMock.getLstEstacionamentosByNif(estacionamento.getNIF())).thenReturn(expResult);
        assertEquals(expResult, instance1.getListaEstacionamentosByParqueNif(estacionamento.getNIF()));
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
    public void testGetNumMaxParqueByNIF() throws SQLException {
        System.out.println("getNumMaxParqueByNIF");
        Parque parque = new Parque(111111111, 20, "drones");
        parqueMock.addParque(parque);
        int expResult = parque.getNumeroMaximo();
        when(parqueMock.getNumMaxParqueByNIF(111111111)).thenReturn(20);
        int result = instance1.getNumMaxParqueByNIF(111111111);
        assertEquals(expResult, result);
    }
}
