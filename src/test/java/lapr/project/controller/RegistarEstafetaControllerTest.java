package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    public void getListaEstafetas() {
        System.out.println("getListaEstafetas");
        Estafeta estafeta = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        List<Estafeta> expResult = new ArrayList<>();
        expResult.add(estafeta);
        when(estafetaMock.getListaEstafetas()).thenReturn(expResult);
        assertEquals(expResult, instance.getListaEstafetas());
    }

    @Test
    public void novoEstafeta() {
        System.out.println("novoEstafeta");
        Estafeta est = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        RegistarEstafetaController i = new RegistarEstafetaController(new EstafetaDB());
        assertEquals(est.toString(), i.novoEstafeta(est.getNIF(), est.getNome(), est.getEmail(), est.getPesoEstafeta(), est.getNumeroSegurancaSocial(), est.getPassword()).toString());
    }

    @Test
    public void registaEstafeta() {
        System.out.println("registaEstafeta");
        Estafeta est = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        when(estafetaMock.registaEstafeta(est)).thenReturn(true);
        assertEquals(true, instance.registaEstafeta(est));
    }
    
    @Test
    public void registaEstafeta1() {
        System.out.println("registaEstafeta1");
        Estafeta est = new Estafeta(1, "45", "56", 48, 486, "aaa", 1);
        when(estafetaMock.registaEstafeta(est)).thenReturn(false);
        assertEquals(false, instance.registaEstafeta(est));
    }
}
