package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    @Test
    void PagamentoEmptyConstructorTest(){
        Pagamento instance = new Pagamento();

        assertEquals(instance,instance);
    }

}