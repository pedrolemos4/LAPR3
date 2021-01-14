package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoTransferenciaTest {

    @Test
    void EntadoTransferenciaEmptyConstructorTest(){
        EstadoTransferencia estado = new EstadoTransferencia();

        assertEquals(estado,estado);
    }

    @Test
    void EstadoTransferenciaConstructorTest(){
        EstadoTransferencia estado = new EstadoTransferencia(123,"design");

        assertEquals(estado,estado);
    }

    @Test
    void getIdEstadoTransferencia() {
        EstadoTransferencia estado = new EstadoTransferencia();

        assertEquals(0,estado.getIdEstadoTransferencia());
    }

    @Test
    void setidEstadoTransferencia() {
        EstadoTransferencia estado = new EstadoTransferencia();
        estado.setidEstadoTransferencia(2);

        assertEquals(2,estado.getIdEstadoTransferencia());
    }

    @Test
    void getDesignacao() {
        EstadoTransferencia estado = new EstadoTransferencia();

        assertNull(estado.getDesignacao());
    }

    @Test
    void setDesignacao() {
        EstadoTransferencia estado = new EstadoTransferencia();

        estado.setDesignacao("Teste");
        assertEquals("Teste", estado.getDesignacao());
    }
}