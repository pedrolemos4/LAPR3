package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstacionamentoTest {

    @Test
    void getNumeroLote() {
        System.out.println("getNumeroLote");
        Estacionamento instance = new Estacionamento(1,0);
        int expResult = 1;
        int result = instance.getNumeroLote();
        assertEquals(expResult, result);
    }

    @Test
    void setNumeroLote() {
        System.out.println("setNumeroLote");
        int numeroLote = 15;
        Estacionamento instance = new Estacionamento(1,0);
        instance.setNumeroLote(15);
        assertEquals(numeroLote, instance.getNumeroLote());
    }

    @Test
    void getCarregador() {
        System.out.println("getCarregador");
        Estacionamento instance = new Estacionamento(1,0);
        int expResult = 0;
        int result = instance.getCarregador();
        assertEquals(expResult, result);
    }

    @Test
    void setCarregador() {
        System.out.println("setCarregador");
        int carregador = 1;
        Estacionamento instance = new Estacionamento(1,0);
        instance.setCarregador(1);
        assertEquals(carregador, instance.getCarregador());
    }
}