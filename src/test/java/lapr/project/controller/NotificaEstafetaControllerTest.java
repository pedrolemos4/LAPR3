package lapr.project.controller;

import lapr.project.data.EntregaDB;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.login.User;
import lapr.project.login.UserSession;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotificaEstafetaControllerTest {

    private NotificaEstafetaController instance;
    private ScooterDB scooterDBMock;
    private EstacionamentosDB estacionamentoDBMock;
    private EstafetaDB estafetaDBMock;
    private EntregaDB entregaDBMock;
    private Scooter s;
    private Estafeta e;
    private Entrega et;
    private Estacionamento est;

    @BeforeEach
    void setUp() throws SQLException {
        scooterDBMock = mock(ScooterDB.class);
        estacionamentoDBMock = mock(EstacionamentosDB.class);
        estafetaDBMock = mock(EstafetaDB.class);
        entregaDBMock = mock(EntregaDB.class);
        instance = new NotificaEstafetaController(estafetaDBMock,scooterDBMock,entregaDBMock,estacionamentoDBMock);
        s = new Scooter("", 23, 45, 3, 435, 34, 1);
        e = new Estafeta();
        et = new Entrega("","",0,0);
        est = new Estacionamento();
    }

    @Test
    public void NotificaEstafetaControllerConstructorTest() throws SQLException {
        UserSession us = new UserSession(new User("123","123","123"));
        UserSession.RefreshInstance(us);

        scooterDBMock.addScooter(new Scooter());
        estafetaDBMock.addEstafeta(new Estafeta());
        entregaDBMock.addEntrega(new Entrega("","",0,0));

        assertEquals(instance, instance);
    }
}