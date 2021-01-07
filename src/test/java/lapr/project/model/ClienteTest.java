package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void getCreditos() {
        Cartao cartao = new Cartao();
        Cliente novoCliente = new Cliente(3,cartao,3,"teste","@",123,"teste");
        assertEquals(3,novoCliente.getCreditos());
    }

    @Test
    void setCreditos() {
        Cartao cartao = new Cartao();
        Cliente novoCliente = new Cliente(3,cartao,3,"teste","@",123,"teste");
        novoCliente.setCreditos(2);
        assertEquals(2,novoCliente.getCreditos());
    }

    @Test
    void getCartaoCredito() {
        Cartao cartao = new Cartao();
        Cliente novoCliente = new Cliente(3,cartao,3,"teste","@",123,"teste");

        assertEquals(cartao,novoCliente.getCartaoCredito());
    }

    @Test
    void setCartaoCredito() {
        Cartao cartao = new Cartao();
        Cliente novoCliente = new Cliente(3,cartao,3,"teste","@",123,"teste");

        Cartao novoCartao = new Cartao(123,"123",123);
        novoCliente.setCartaoCredito(novoCartao);

        assertEquals(novoCartao,novoCliente.getCartaoCredito());
    }
}