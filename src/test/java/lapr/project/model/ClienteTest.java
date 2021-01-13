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
    public void ClienteConstructor2Test() {
        Cliente novoCliente = new Cliente(123,"Teste","@",1234,12,"Ali",123,"pass");
        assertEquals(novoCliente, novoCliente);
    }

    @Test
    public void ClienteConstructorTest() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals(novoCliente, novoCliente);
    }

    @Test
    public void getClienteNIF() {
        Cliente novoCliente = new Cliente(56, 123, "não sei", 65746);
        assertEquals(56, novoCliente.getClienteNIF());
    }

    @Test
    public void setClienteNIF() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setClienteNIF(78);
        assertEquals(78, novoCliente.getClienteNIF());
    }

    @Test
    public void getCreditos() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals(56, novoCliente.getCreditos());
    }

    @Test
    public void setCreditos() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setCreditos(2.0);
        assertEquals(2, novoCliente.getCreditos());
    }

    @Test
    public void getNumCartaoCredito() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals(65746, novoCliente.getNumCartaoCredito());
    }

    @Test
    public void setNumCartaoCredito() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setNumCartaoCredito(3422);
        assertEquals(3422, novoCliente.getNumCartaoCredito());
    }

    @Test
    public void getEnderecoMorada() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        assertEquals("não sei", novoCliente.getEnderecoMorada());
    }

    @Test
    public void setEnderecoMorada() {
        Cliente novoCliente = new Cliente(123, 56, "não sei", 65746);
        novoCliente.setEnderecoMorada("sei");
        assertEquals("sei", novoCliente.getEnderecoMorada());
    }

    /**
     * Test of toString method, of class Cliente.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cliente instance = new Cliente(123, 56, "não sei", 65746);
        String expResult = "Cliente{nif=123, creditos=56.0, enderecomorada=não sei, numCartaoCredito65746}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
