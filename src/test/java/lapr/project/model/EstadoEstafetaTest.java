package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoEstafetaTest {

    @Test
    void EntadoEstafetaEmptyConstructorTest(){
        EstadoEstafeta estado = new EstadoEstafeta();

        assertEquals(estado,estado);
    }

    @Test
    void EntadoEstafetaConstructorTest(){
        EstadoEstafeta estado = new EstadoEstafeta(123,"design");

        assertEquals(estado,estado);
    }

    @Test
    void getIdEstadoEstafeta() {
        EstadoEstafeta estado = new EstadoEstafeta();

        assertEquals(0,estado.getIdEstadoEstafeta());
    }

    @Test
    void setIdEstadoEstafeta() {
        EstadoEstafeta estado = new EstadoEstafeta();
        estado.setIdEstadoEstafeta(2);

        assertEquals(2,estado.getIdEstadoEstafeta());
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