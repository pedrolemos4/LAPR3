package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoEstafetaTest {

    @Test
    void getId_estado_estafeta() {
        EstadoEstafeta estado = new EstadoEstafeta();

        assertEquals(0,estado.getId_estado_estafeta());
    }

    @Test
    void setId_estado_estafeta() {
        EstadoEstafeta estado = new EstadoEstafeta();
        estado.setId_estado_estafeta(2);

        assertEquals(2,estado.getId_estado_estafeta());
    }

    @Test
    void getDesignacao() {
        EstadoEstafeta estado = new EstadoEstafeta();

        assertNull(estado.getDesignacao());
    }

    @Test
    void setDesignacao() {
        EstadoEstafeta estado = new EstadoEstafeta();

        estado.setDesignacao("Teste");
        assertEquals("Teste", estado.getDesignacao());
    }
}