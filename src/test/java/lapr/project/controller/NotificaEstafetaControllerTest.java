package lapr.project.controller;

import lapr.project.data.*;
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

        s = new Scooter(1,"", 23, 45, 3, 435, 34, 1);
        e = new Estafeta(1,"1","1",1,1,"1",1);
        et = new Entrega("",null,1,1);
        est = new Estacionamento(1,1,12);

        UserSession.getInstance().setUser(e);

        scooterDBMock.addScooter(s);
        estafetaDBMock.addEstafeta(e);
        entregaDBMock.addEntrega(et);
        estacionamentoDBMock.addEstacionamento(est);

        when(estafetaDBMock.getEstafetaByEmail(e.getEmail())).thenReturn(e);
        when(entregaDBMock.getEntregaAtiva(e.getEmail())).thenReturn(et);
        when(scooterDBMock.getScooterById(s.getId())).thenReturn(s);

        instance = new NotificaEstafetaController(estafetaDBMock,scooterDBMock,entregaDBMock,estacionamentoDBMock);
    }

    @Test
    public void NotificaEstafetaControllerConstructorTest(){
        assertEquals(instance, instance);
    }
}