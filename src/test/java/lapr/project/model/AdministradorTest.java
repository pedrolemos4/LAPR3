package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest extends UtilizadorTest {
    @Test
    public void AdministradorConstructorTest(){
        Administrador adminTest = new Administrador(123,"Senhor Admin","@",1234,"pass");

        assertEquals(adminTest,adminTest);
    }

    @Test
    public void AdministradorEmptyConstructorTest(){
        Administrador adminTest = new Administrador();

        assertEquals(adminTest,adminTest);
    }
}