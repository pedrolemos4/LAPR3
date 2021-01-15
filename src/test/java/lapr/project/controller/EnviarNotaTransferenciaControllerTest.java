package lapr.project.controller;

import lapr.project.data.FarmaciaDB;
import lapr.project.data.ProdutosDB;
import lapr.project.data.TransferenciaDB;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EnviarNotaTransferenciaControllerTest {
    EnviarNotaTransferenciaController instance;
    TransferenciaDB tMock;

    @BeforeEach
    void setUp() throws SQLException {
        tMock = mock(TransferenciaDB.class);
        instance = new EnviarNotaTransferenciaController(tMock);
    }

    @Test
    void enviarNotaTransferencia() {
        boolean expResult = true;
        Farmacia f1 = new Farmacia(1,"email","rua");
        Farmacia f2 = new Farmacia(1,"email","rua");
        Produto prod = new Produto("prod",1,1);
        when(tMock.enviarNotaTransferencia(f1,f2,prod,1)).thenReturn(expResult);
        assertEquals(expResult, instance.enviarNotaTransferencia(f1,f2,prod,1));
    }
}