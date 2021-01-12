package lapr.project.login;

import lapr.project.model.Utilizador;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @Test
    void getInstance() {
        UserSession us = UserSession.getInstance();
        assertEquals(us, UserSession.getInstance());
    }

    @Test
    void setUser() {
        UserSession us = new UserSession();
        Utilizador us1 = new Utilizador();
        us.setUser(us1);
        assertEquals(us1,us.getUser());
    }

    @Test
    void doLogout() {
        UserSession us = new UserSession();
        us.doLogout();
        assertNull(us.getUser());
    }

    @Test
    void getUser() {
        UserSession us = new UserSession();
        Utilizador us1 = new Utilizador();
        us.setUser(us1);
        assertEquals(us1,us.getUser());
    }
}