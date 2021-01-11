package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegistarEstafetaControllerTest {
    private RegistarEstafetaController instance;
    private EstafetaDB estafetaMock;
    private Estafeta est;

    @BeforeEach
    void setUp() throws SQLException {
        estafetaMock = mock(EstafetaDB.class);
        instance = new RegistarEstafetaController(estafetaMock);
        est = new Estafeta(1, "23", "45", 3, 435, "aaa", 1);
        when(estafetaMock.addEstafeta(est)).thenReturn(true);
    }

    @Test
    void getListaEstafetas() {
        System.out.println("getListaEstafetas");
        Estafeta estafeta = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        List<Estafeta> expResult = new ArrayList<>();
        expResult.add(estafeta);
        when(estafetaMock.getListaEstafetas()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaEstafetas());
    }

    @Test
    void novoEstafeta() {
        System.out.println("registaEstafeta");
        Estafeta est = estafetaMock.novoEstafeta(1, "45", "56", 48, 486, "aaa");
        when(estafetaMock.novoEstafeta(1, "45", "56", 48, 486, "aaa")).thenReturn(est);
        assertEquals(est, estafetaMock.novoEstafeta(1, "45", "56", 48, 486, "aaa"));
    }

    @Test
    void registaEstafeta() {
        System.out.println("registaEstafeta");
        Estafeta est = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        when(estafetaMock.registaEstafeta(est)).thenReturn(true);
        assertEquals(true, estafetaMock.registaEstafeta(est));
    }
}