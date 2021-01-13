package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AtualizarEstafetaControllerTest {
    private AtualizarEstafetaController instance;
    private EstafetaDB estafetaMock;
    private Estafeta est;

    @BeforeEach
    public void setUp() throws SQLException {
        estafetaMock = mock(EstafetaDB.class);
        instance = new AtualizarEstafetaController(estafetaMock);
        est = new Estafeta(1, "23", "45", 3, 435, "aaa", 1);
        when(estafetaMock.addEstafeta(est)).thenReturn(true);
    }
    @Test
    public void getListaEstafetas() {
        Estafeta estafeta = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        List<Estafeta> expResult = new ArrayList<>();
        expResult.add(estafeta);
        when(estafetaMock.getListaEstafetas()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaEstafetas());
    }

    @Test
    public void getEstafetaByEmail() {
        Estafeta expResult = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        when(estafetaMock.getEstafetaByEmail("56")).thenReturn(expResult);
        assertEquals(expResult, instance.getEstafetaByEmail("56"));
    }

    @Test
    public void atualizarEstafeta() {
        System.out.println("atualizarEstafeta");
        Estafeta est = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        when(estafetaMock.atualizarEstafeta(est)).thenReturn(true);
        assertEquals(true, instance.atualizarEstafeta(est));
    }
    
    @Test
    public void atualizarEstafeta1() {
        System.out.println("atualizarEstafeta1");
        Estafeta est = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        when(estafetaMock.atualizarEstafeta(est)).thenReturn(false);
        assertEquals(false, instance.atualizarEstafeta(est));
    }
}