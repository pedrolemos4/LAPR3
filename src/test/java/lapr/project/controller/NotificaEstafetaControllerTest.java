package lapr.project.controller;

import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificaEstafetaControllerTest {

    @Test
    void NotificaEstafetaControllerConstructorTest(){
        NotificaEstafetaController instance = new NotificaEstafetaController(new EstafetaDB(), new ScooterDB(), new EntregaDB());

        assertEquals(instance,instance);
    }

}