package lapr.project.controller;

import lapr.project.model.Estafeta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistarEstafetaControllerTest {
    RegistarEstafetaController controller;

    @Test
    void getListaEstafetas() {
        List<Estafeta> le = new ArrayList<>();
        assertEquals(le, controller.getListaEstafetas());
    }

    @Test
    void novoEstafeta() {
        Estafeta est = new Estafeta(1,"estafeta", "est@email.com", 1, 50, "password",1);
        assertEquals(est, controller.novoEstafeta(1,"estafeta", "est@email.com", 1, 50, "password"));
    }

    @Test
    void registaEstafeta() {
    }
}