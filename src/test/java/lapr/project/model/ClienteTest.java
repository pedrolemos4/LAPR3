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
        Cliente novoCliente = new Cliente(3, new Cartao(), new Endereco(), 3, "teste", "@", 123, "teste");
        assertEquals(novoCliente, novoCliente);
    }

    @Test
    void getCreditos() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, cartao, endereco, 3, "teste", "@", 123, "teste");
        assertEquals(3, novoCliente.getCreditos());
    }

    @Test
    void setCreditos() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, cartao, endereco, 3, "teste", "@", 123, "teste");
        novoCliente.setCreditos(2);
        assertEquals(2, novoCliente.getCreditos());
    }

    @Test
    void getCartaoCredito() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, cartao, endereco, 3, "teste", "@", 123, "teste");
        assertEquals(cartao, novoCliente.getCartaoCredito());
    }

    @Test
    void setCartaoCredito() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, cartao, endereco, 3, "teste", "@", 123, "teste");
        Cartao novoCartao = new Cartao(123, "123", 123);
        novoCliente.setCartaoCredito(novoCartao);
        assertEquals(novoCartao, novoCliente.getCartaoCredito());
    }

    @Test
    void getEndereco() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, cartao, endereco, 3, "teste", "@", 123, "teste");
        assertEquals(endereco, novoCliente.getEndereco());
    }

    @Test
    void setEndereco() {
        Cartao cartao = new Cartao();
        Endereco endereco = new Endereco();
        Cliente novoCliente = new Cliente(3, cartao, endereco, 3, "teste", "@", 123, "teste");
        Endereco novoEndereco = new Endereco("nada", 5764, 3242, 21312);
        novoCliente.setEndereco(novoEndereco);
        assertEquals(novoEndereco, novoCliente.getEndereco());
    }
}
