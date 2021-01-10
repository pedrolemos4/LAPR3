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
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals(novoCliente, novoCliente);
    }

    @Test
    void getClienteNIF() {
        Cliente novoCliente = new Cliente(56, 123, "não sei", 65746);
        assertEquals(56, novoCliente.getClienteNIF());
    }

    @Test
    void setClienteNIF() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setClienteNIF(78);
        assertEquals(78, novoCliente.getClienteNIF());
    }

    @Test
    void getCreditos() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals(56, novoCliente.getCreditos());
    }

    @Test
    void setCreditos() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setCreditos(2);
        assertEquals(2, novoCliente.getCreditos());
    }

    @Test
    void getNumCartaoCredito() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals(65746, novoCliente.getNumCartaoCredito());
    }

    @Test
    void setNumCartaoCredito() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setNumCartaoCredito(3422);
        assertEquals(3422, novoCliente.getNumCartaoCredito());
    }

    @Test
    void getEnderecoMorada() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals("não sei", novoCliente.getEnderecoMorada());
    }

    @Test
    void setEnderecoMorada() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setEnderecoMorada("sei");
        assertEquals("sei", novoCliente.getEnderecoMorada());
    }
}
