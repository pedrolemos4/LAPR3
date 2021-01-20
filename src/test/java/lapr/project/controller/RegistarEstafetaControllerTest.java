package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.UtilizadorDB;
import lapr.project.model.Utilizador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegistarEstafetaControllerTest {

    private RegistarEstafetaController instance;
    private EstafetaDB estafetaMock;
    private UtilizadorDB utilizadorMock;
    private Estafeta est;

    @BeforeEach
    void setUp() throws SQLException {
        estafetaMock = mock(EstafetaDB.class);
        utilizadorMock = mock(UtilizadorDB.class);
        instance = new RegistarEstafetaController(estafetaMock, utilizadorMock);
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
        RegistarEstafetaController i = new RegistarEstafetaController(new EstafetaDB(), new UtilizadorDB());
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

    /**
     * Test of login method, of class RegistarEstafetaController.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "email";
        String password = "password";
        Utilizador expResult = new Utilizador(123456789, "nome",email, 15, password);
        when(utilizadorMock.validateLogin(email, password)).thenReturn(expResult.getNIF());
        Estafeta est = new Estafeta(expResult.getNIF(), 1, 45);
        when(estafetaMock.getEstafetaByNIF(expResult.getNIF())).thenReturn(est);
        Utilizador result = instance.login(email, password);
        assertEquals(est, result);

    }
}
