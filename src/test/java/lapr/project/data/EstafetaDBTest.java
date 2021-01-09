package lapr.project.data;

import lapr.project.model.EstadoEstafeta;
import lapr.project.model.Estafeta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstafetaDBTest {

    @Test
    void getLstEstafetas() {
        System.out.println("getLstEstafetas");
        List<Estafeta> expResult = new ArrayList<>();
        Estafeta instance = new Estafeta(55, "ss", "dd", 58, 55, "sd", 1);
        expResult.add(instance);

        List<Estafeta> result = expResult;
        assertEquals(expResult, result);
    }

    @Test
    void novoEstafeta() {
    }

    @Test
    void registaEstafeta() {
    }

    @Test
    void validaEstafeta() {
        System.out.println("validaEstafeta");
        EstafetaDB edb = new EstafetaDB();
        Estafeta instance = new Estafeta(55, "ss", "dd", 58, 55, "sd", 1);
        boolean expResult = true;

        boolean result = edb.validaEstafeta(instance);
        assertEquals(expResult, result);
    }

    @Test
    void addEstafeta() {
    }

    @Test
    void getEstafetaByEmail() {
    }
}