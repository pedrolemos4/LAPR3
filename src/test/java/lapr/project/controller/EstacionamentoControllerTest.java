package lapr.project.controller;

import lapr.project.data.EmailDB;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.ParqueDB;
import lapr.project.data.VeiculoDB;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Parque;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EstacionamentoControllerTest {
    private EmailDB emailDB;
    private EstacionamentosDB estacionamentosDB;
    private VeiculoDB veiculoDB;
    private ParqueDB parqueDB;
    private EstacionamentoController instance;

    @BeforeEach
    void setUp(){
        estacionamentosDB = mock(EstacionamentosDB.class);
        veiculoDB = mock(VeiculoDB.class);
        emailDB = mock(EmailDB.class);
        parqueDB = mock(ParqueDB.class);
        instance = new EstacionamentoController(emailDB,estacionamentosDB,veiculoDB,parqueDB);
    }

    @Test
    void checkParkingsTrue() throws FileNotFoundException {
        System.out.println("checkParkingsTrue()");

        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        estacionamento.setNumeroLote(0);
        Parque parque = new Parque(0,1,1,"scooter",1000);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        when(parqueDB.getParqueByID(0)).thenReturn(parque);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);
        assertEquals(true,instance.checkParkings("src/main/java/lapr/project/parking/teste"));
    }

    @Test
    void checkParkingsFalse() throws FileNotFoundException {
        System.out.println("checkParkingsFalse()");
        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);
        Parque parque = new Parque(0,1,1,"scooter",1000);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        when(parqueDB.getParqueByID(0)).thenReturn(parque);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertEquals(false,instance.checkParkings("src/main/java/lapr/project/parking/teste"));
    }

    @Test
    void simulateParkingVeiculo1() throws FileNotFoundException {
        System.out.println("simulateParkingVeiculo1()");

        Estafeta estafeta = new Estafeta(0, "nome", "rodrikcraft@gmail.com", 0, 0, "password", 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        estacionamento.setNumeroLote(0);
        Parque parque = new Parque(0,1,1,"scooter",1000);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        when(parqueDB.getParqueByID(0)).thenReturn(parque);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertEquals(true,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void simulateParkingVeiculo2() {
        System.out.println("simulateParkingVeiculo2()");

        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        try {
            assertEquals(false,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void simulateParkingVeiculo3() throws FileNotFoundException {
        System.out.println("simulateParkingVeiculo3()");

        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        estacionamento.setNumeroLote(0);
        Parque parque = new Parque(0,1,1,"scooter",1000);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        when(parqueDB.getParqueByID(0)).thenReturn(parque);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertEquals(true,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_01.data"));
    }

    @Test
    void simulateParkingVeiculo4() throws FileNotFoundException {
        System.out.println("simulateParkingVeiculo4()");
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        estacionamento.setNumeroLote(0);
        Parque parque = new Parque(0,1,1,"drone",1000);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        when(parqueDB.getParqueByID(0)).thenReturn(parque);

        assertTrue(instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void simulateParkingVeiculo5(){
        System.out.println("simulateParkingVeiculo5()");
        Estafeta estafeta = new Estafeta(0, "nome", "a@gmail.com", 0, 0, "password", 0);
        Veiculo veiculo = new Veiculo("String descricao","drone", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        try {
            assertEquals(false,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2022_02_02_02_02_02.data"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void simulateParkingVeiculo6() throws FileNotFoundException {
        System.out.println("simulateParkingVeiculo6()");

        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        estacionamento.setNumeroLote(0);
        Parque parque = new Parque(0,1,1,"scooter",1000);

        instance = new EstacionamentoController(new EmailDB(),estacionamentosDB,veiculoDB,parqueDB);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        when(parqueDB.getParqueByID(0)).thenReturn(parque);

        assertEquals(true,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_02.data"));
    }

    @Test
    void simulateParkingVeiculo7() throws FileNotFoundException {
        System.out.println("simulateParkingVeiculo7()");

        Estafeta estafeta = new Estafeta(0, "nome", "rodrikcraft@gmail.com", 0, 0, "password", 0);
        Veiculo veiculo = new Veiculo("String descricao","scooter", 0,0, 0,0, 0,0, 0);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        estacionamento.setNumeroLote(0);
        Parque parque = new Parque(0,1,1,"scooter",1000);

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);

        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);

        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);

        String assunto = "Estacionamento Veiculo";
        String mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(false);

        when(parqueDB.getParqueByID(0)).thenReturn(parque);

        assertEquals(false,instance.simulateParkingVeiculo("src/main/java/lapr/project/parking/teste/estimate_2021_02_02_02_02_02.data"));
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
    void getDiretory() {
        instance = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB());
        System.out.println("getDiretory()");
        String expected = "estimate_2021_02_02_02_02_01.data";
        assertEquals(expected,instance.getDiretory("src/main/java/lapr/project/parking/teste"));
    }
}