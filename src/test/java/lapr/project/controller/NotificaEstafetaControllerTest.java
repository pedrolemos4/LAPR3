package lapr.project.controller;

import java.io.FileNotFoundException;
import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotificaEstafetaControllerTest {

    private NotificaEstafetaController instance;
    private VeiculoDB veiculoDBMock;
    private EstacionamentosDB estacionamentoDBMock;
    private EstafetaDB estafetaDBMock;
    private EntregaDB entregaDBMock;
    private EmailDB emailDBMock;
    private Veiculo s;
    private Estafeta e;
    private Entrega et;
    private Estacionamento est;

    @BeforeEach
    void setUp() throws SQLException {
        veiculoDBMock = mock(VeiculoDB.class);
        estacionamentoDBMock = mock(EstacionamentosDB.class);
        estafetaDBMock = mock(EstafetaDB.class);
        emailDBMock = mock(EmailDB.class);
        entregaDBMock = mock(EntregaDB.class);

        s = new Veiculo(1,"","Scooter",100, 23, 45, 3, 435, 34, 1);
        e = new Estafeta(1,"1","abf@gmail.com",1,1,"1",1);
        et = new Entrega("",null,1,1);
        est = new Estacionamento(1,1,12);

        UserSession.getInstance().setUser(e);

        veiculoDBMock.addVeiculo(s);
        estafetaDBMock.addEstafeta(e);
        entregaDBMock.addEntrega(et);
        estacionamentoDBMock.addEstacionamento(est);

        when(estafetaDBMock.getEstafetaByEmail(e.getEmail())).thenReturn(e);
        when(entregaDBMock.getEntregaAtiva(e.getEmail())).thenReturn(et);
        when(veiculoDBMock.getVeiculoById(s.getId())).thenReturn(s);

        instance = new NotificaEstafetaController(estafetaDBMock,veiculoDBMock,entregaDBMock,estacionamentoDBMock);
    }

    @Test
    public void NotificaEstafetaControllerConstructorTest(){
        assertEquals(instance, instance);
    }
    
    @Test
    void simulateParkingVeiculo(){
        String email = "abf@gmail.com";
        String assunto = "Estacionamento Veiculo";
        int estimativa = 3;
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.";
        when(emailDBMock.sendEmail(email, assunto, mensagem)).thenReturn(true);
        assertTrue(instance.simulateParkingVeiculo(1,emailDBMock,"src/main/java/lapr/project/parking/scooter/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void simulateParkingVeiculo1(){
        String email = "abf@gmail.com";
        String assunto = "Estacionamento Veiculo";
        int estimativa = 3;
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.";
        when(emailDBMock.sendEmail(email, assunto, mensagem)).thenReturn(false);
        assertFalse(instance.simulateParkingVeiculo(1,emailDBMock,"src/main/java/lapr/project/parking/scooter/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void simulateParkingVeiculo2(){
        assertFalse(instance.simulateParkingVeiculo(1,emailDBMock,"src/main/java/lapr/project/parking/scooter/estimate_2022_02_02_02_02_02.data"));
    }
}