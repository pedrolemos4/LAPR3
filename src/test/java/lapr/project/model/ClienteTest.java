package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest extends UtilizadorTest {

    @Test
    public void ClienteEmptyConstructorTest() {
        Cliente clienteTest = new Cliente();

        assertEquals(clienteTest, clienteTest);
    }

    @Test
    public void ClienteConstructorTest() {
        Cliente novoCliente = new Cliente(3, 0, "a", 3, "teste", "@", 123, "teste");
        assertEquals(novoCliente, novoCliente);
    }

    @Test
    void getCreditos() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, 0, "a", 3, "teste", "@", 123, "teste");
        assertEquals(3, novoCliente.getCreditos());
    }

    @Test
    void setCreditos() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, 0, "a", 3, "teste", "@", 123, "teste");
        novoCliente.setCreditos(2);
        assertEquals(2, novoCliente.getCreditos());
    }

    @Test
    void getCartaoCredito() {
        int cartao = 0;
        Cliente novoCliente = new Cliente(3, 0, "a", 3, "teste", "@", 123, "teste");
        assertEquals(cartao, novoCliente.getCartaoCredito());
    }

    @Test
    void setCartaoCredito() {
        int novoCartao = 0;
        Cliente novoCliente = new Cliente(3, 0, "a", 3, "teste", "@", 123, "teste");
        novoCliente.setCartaoCredito(novoCartao);
        assertEquals(novoCartao, novoCliente.getCartaoCredito());
    }

    @Test
    void getEndereco() {
        String endereco = "a";
        Cliente novoCliente = new Cliente(3, 0, "a", 3, "teste", "@", 123, "teste");
        assertEquals(endereco, novoCliente.getEndereco());
    }

    @Test
    void setEndereco() {
        Cliente novoCliente = new Cliente(3, 0, "a", 3, "teste", "@", 123, "teste");
        String novoEndereco = "a";
        novoCliente.setEndereco(novoEndereco);
        assertEquals(novoEndereco, novoCliente.getEndereco());
    }
}
