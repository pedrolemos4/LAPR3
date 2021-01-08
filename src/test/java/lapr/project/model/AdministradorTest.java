package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest extends UtilizadorTest {

    @Test
    public void AdministradorConstructorTest(){
        Administrador adminTest = new Administrador();

        assertEquals(adminTest,adminTest);
    }
}