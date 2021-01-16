package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EstacionamentoControllerTest {

    private EntregaDB entregaDB;
    private EstafetaDB estafetaDB;
    private EmailDB emailDB;
    private EstacionamentosDB estacionamentosDB;
    private VeiculoDB veiculoDB;
    private EstacionamentoController instance;

    @BeforeEach
    void setUp(){
        estafetaDB = mock(EstafetaDB.class);
        entregaDB = mock(EntregaDB.class);
        estacionamentosDB = mock(EstacionamentosDB.class);
        veiculoDB = mock(VeiculoDB.class);
        emailDB = mock(EmailDB.class);
        instance = new EstacionamentoController(entregaDB,estafetaDB,emailDB,estacionamentosDB,veiculoDB);
    }

    @Test
    void checkParkings() {
        instance = mock(EstacionamentoController.class);
        when(instance.checkParkings()).thenReturn(true);
        assertTrue(instance.checkParkings());
    }

    @Test
    void simulateParkingVeiculo1() {
        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Entrega entrega = new Entrega("String dataInicio",null, 0, 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);

        when(entregaDB.getEntregaAtiva(estafeta.getEmail())).thenReturn(entrega);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void simulateParkingVeiculo2() {
        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Entrega entrega = new Entrega("String dataInicio",null, 0, 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);

        when(entregaDB.getEntregaAtiva(estafeta.getEmail())).thenReturn(entrega);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertFalse(instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/"));
    }

    @Test
    void simulateParkingVeiculo3() {
        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Entrega entrega = new Entrega("String dataInicio",null, 0, 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);

        when(entregaDB.getEntregaAtiva(estafeta.getEmail())).thenReturn(entrega);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_01.data"));
    }

    @Test
    void simulateParkingVeiculo4() {
        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Entrega entrega = new Entrega("String dataInicio",null, 0, 0);
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);

        when(entregaDB.getEntregaAtiva(estafeta.getEmail())).thenReturn(entrega);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void notificaEstafeta1() {
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.notificaEstafeta(true,3,email));
    }

    @Test
    void notificaEstafeta2() {
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.notificaEstafeta(false,3,email));
    }

    @Test
    void notificaEstafeta3() {
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(false);

        assertFalse(instance.notificaEstafeta(false,3,email));
    }

    @Test
    void carregamentoCompleto() {
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        veiculo.setPercentagemBateria(100);
        Veiculo veiculo1 = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        instance.carregamentoCompleto(veiculo1);
        assertEquals(veiculo1.getPercentagemBateria(),veiculo.getPercentagemBateria(),0);
    }

    @Test
    void timerCarregamento() {
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);

        assertTrue(instance.TimerCarregamento(1,veiculo));
    }

    @Test
    void getDiretory() {
        String expected = "estimate_2021_02_02_02_02_02.data";
        assertEquals(expected,instance.getDiretory("src/main/java/lapr/project/parking/estacionamento/"));
    }
}