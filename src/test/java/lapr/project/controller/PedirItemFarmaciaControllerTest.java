package lapr.project.controller;

import lapr.project.data.EstafetaDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.TransferenciaDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PedirItemFarmaciaControllerTest {
    private PedirItemFarmaciaController instance;
    private TransferenciaProduto trans;
    TransferenciaDB tdb;
    FarmaciaDB fdb;

    @BeforeEach
    void setUp() throws SQLException {
        tdb = mock(TransferenciaDB.class);
        fdb = mock(FarmaciaDB.class);
        instance = new PedirItemFarmaciaController(fdb,tdb);
        Farmacia farm = new Farmacia(1,"email","rua1");
        Farmacia farm2 = new Farmacia(1,"email","rua2");
        Produto prod = new Produto("prod",1,1);
        trans = new TransferenciaProduto(1, 1,2,1,1);
        when(tdb.realizaPedido(farm,farm2,prod,1)).thenReturn(true);
    }

    @Test
    void getFarmaciaByNIF() {
        System.out.println("getFarmaciaByNIF");
        int idFarm = 1;
        Farmacia expResult = new Farmacia(1,"email","rua1");
        when(fdb.getFarmaciaByNIF(idFarm)).thenReturn(expResult);
        Farmacia result = instance.getFarmaciaByNIF(idFarm);
        assertEquals(expResult, result);
    }

    @Test
    void realizaPedido() {
        boolean expResult = true;
        Farmacia f1 = new Farmacia(1,"email","rua");
        Farmacia f2 = new Farmacia(1,"email","rua");
        Produto prod = new Produto("prod",1,1);
        when(tdb.realizaPedido(f1,f2,prod,1)).thenReturn(expResult);
        assertEquals(expResult, instance.realizaPedido(f1,f2,prod,1));
    }
}