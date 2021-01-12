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
    private EstacionamentosDB estacionamentoMock;
    private ParqueDB parqueMock;
    private Estacionamento estac;

    @BeforeEach
    public void setUp() throws SQLException {
        estacionamentoMock = mock(EstacionamentosDB.class);
        instance = new InserirEstacionamentosController();
        parqueMock = mock(ParqueDB.class);
        estac = new Estacionamento(123456789, 5, 1);
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
        when(estacionamentoMock.getLstEstacionamentosByNif(1)).thenReturn(expResult);
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
        assertEquals(estacionamento, estacionamentoMock.novoEstacionamento(1, 0, 0));
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
//        when(estacionamentoMock.registaEstacionamento(estacionamento)).thenReturn(true);
//        assertEquals(true, estacionamentoMock.registaEstacionamento(estacionamento));
    }

    /**
     * Test of getNumMaxParqueByNIF method, of class
     * InserirEstacionamentosController.
     *
     * @throws java.sql.SQLException
     */
//    @Test
//    public void testGetNumMaxParqueByNIF() throws SQLException {
//        System.out.println("getNumMaxParqueByNIF");
//        Parque parque = new Parque(111111111, "algures", 20);
//        parqueMock.addParque(parque);
//        int expResult = parque.getNIF();
//        int result = parqueMock.getNumMaxParqueByNIF(111111111);
//        assertEquals(expResult, result);
//    }

}
