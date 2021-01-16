package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        instance = new EstacionamentoController(entregaDB,emailDB,estacionamentosDB,veiculoDB);
    }

    @Test
    void checkParkingsTrue() {
        System.out.println("checkParkingsTrue()");

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
        assertEquals(true,instance.checkParkings("src/main/java/lapr/project/parking/teste"));
    }

    @Test
    void checkParkingsFalse() {
        System.out.println("checkParkingsFalse()");
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

        assertEquals(false,instance.checkParkings("src/main/java/lapr/project/parking/teste"));
    }

    @Test
    void simulateParkingVeiculo1() {
        System.out.println("simulateParkingVeiculo1()");

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

        assertEquals(true,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void simulateParkingVeiculo2() {
        System.out.println("simulateParkingVeiculo2()");

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

        assertEquals(false,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste"));


    }

    @Test
    void simulateParkingVeiculo3() {
        System.out.println("simulateParkingVeiculo3()");

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

        assertEquals(true,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_01.data"));
    }

    @Test
    void simulateParkingVeiculo4() {
        System.out.println("simulateParkingVeiculo4()");
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
    void simulateParkingVeiculo5() {
        System.out.println("simulateParkingVeiculo5()");
        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Entrega entrega = new Entrega("String dataInicio",null, 0, 0);
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);

        when(entregaDB.getEntregaAtiva(estafeta.getEmail())).thenReturn(entrega);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2022_02_02_02_02_02.data");

        assertEquals("Ficheiro não encontrado", outputStreamCaptor.toString().trim());
    }

    @Test
    void notificaEstafeta1() {
        System.out.println("notificaEstafeta1()");
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertEquals(true,instance.notificaEstafeta(true,3,email));
    }

    @Test
    void notificaEstafeta2() {
        System.out.println("notificaEstafeta2()");
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertEquals(true,instance.notificaEstafeta(false,3,email));
    }

    @Test
    void notificaEstafeta3() {
        System.out.println("notificaEstafeta3()");
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(false);

        assertEquals(false,instance.notificaEstafeta(false,3,email));
    }

    @Test
    void carregamentoCompleto() {
        System.out.println("carregamentoCompleto()");
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        veiculo.setPercentagemBateria(100);
        Veiculo veiculo1 = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        instance.carregamentoCompleto(veiculo1);
        assertEquals(veiculo1.getPercentagemBateria(),veiculo.getPercentagemBateria(),0);
    }

    @Test
    void timerCarregamento() {
        System.out.println("timerCarregamento()");
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);

        assertEquals(true,instance.timerCarregamento(1,veiculo));
    }

    @Test
    void getDiretory() {
        instance = new EstacionamentoController(new EntregaDB(), new EmailDB(), new EstacionamentosDB(), new VeiculoDB());
        System.out.println("getDiretory()");
        String expected = "estimate_2021_02_02_02_02_01.data";
        assertEquals(expected,instance.getDiretory("src/main/java/lapr/project/parking/teste"));
    }
}