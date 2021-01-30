package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Parque;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EstacionamentoControllerTest {
    private EmailDB emailDB;
    private EstacionamentosDB estacionamentosDB;
    private VeiculoDB veiculoDB;
    private ParqueDB parqueDB;
    private EstacionamentoController instance;
    private EstafetaDB estafetaDB;
    private String path;
    private String pathReserve;

    @BeforeEach
    void setUp() {
        estacionamentosDB = mock(EstacionamentosDB.class);
        veiculoDB = mock(VeiculoDB.class);
        emailDB = mock(EmailDB.class);
        parqueDB = mock(ParqueDB.class);
        estafetaDB = mock(EstafetaDB.class);
        instance = new EstacionamentoController(emailDB, estacionamentosDB, veiculoDB, parqueDB, estafetaDB);
    }

    @Test
    void getDiretory() {
        path = null;
        pathReserve = null;
        instance = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB(),new EstafetaDB());
        System.out.println("getDiretoryTrue()");
        String expected = "estimate_1.data";
        assertEquals(expected, instance.getDiretory("src/main/java/lapr/project/parking/teste/getDirectory"));
    }

    @Test
    void getDiretory1() {
        path = null;
        pathReserve = null;
        instance = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB(),new EstafetaDB());
        System.out.println("getDiretoryTrue1()");
        assertNull(instance.getDiretory("src/main/java/lapr/project/parking/teste/getDirectory1"));
    }

    @Test
    void getDiretory2() {
        path = null;
        pathReserve = null;
        instance = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB(),new EstafetaDB());
        System.out.println("getDiretoryTrue2()");
        assertNull(instance.getDiretory("src/main/java/lapr/project/parking/teste/getDirectory2"));
    }

    @Test
    void getDiretoryNull() {
        path = null;
        pathReserve = null;
        instance = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB(),new EstafetaDB());
        System.out.println("getDiretoryNull()");
        assertNull(instance.getDiretory("src/main/java/lapr/project/parking/teste/casos testes unitários JAVA/getDirectoryNull"));
    }

    @Test
    void checkParkingsTrue() throws Exception {
        System.out.println("checkParkingsTrue()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = "src/main/java/lapr/project/parking/teste/checkParkingsTrue";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/checkParkingsTrue";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo,estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);
        assertTrue(instance.checkParkings(path));

    }

    @Test
    void checkParkingsFalse() throws FileNotFoundException {
        path = null;
        pathReserve = null;
        System.out.println("checkParkingsFalse()");
        assertFalse(instance.checkParkings("src/main/java/lapr/project/parking/teste/casos testes unitários JAVA/getDirectoryNull"));
    }

    @Test
    void checkParkingsFalse1() throws FileNotFoundException {
        System.out.println("checkParkingsFalse1()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.checkParkings(path));
    }

    //Estimativa = -1, falta de .flag
    @Test
    void simulateScooterMalEstacionada() throws Exception {
        System.out.println("simulateScooterMalEstacionada()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada sem sucesso, tente novamente.";
        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(pathtest));

        /*new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);*/
    }

    //Tipo de parque diferente de scooter
    @Test
    void simulateScooterMalEstacionada1() throws Exception {
        System.out.println("simulateScooterMalEstacionada1()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(pathtest));

        /*new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);*/
    }

    //Parque sem carregador
    @Test
    void simulateScooterMalEstacionada2() throws Exception {
        System.out.println("simulateScooterMalEstacionada2()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(pathtest));

        /*new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateScooterMalEstacionada/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);*/
    }

    @Test
    void simulateScooterMalEstacionada3() throws Exception {
        System.out.println("simulateScooterMalEstacionada3()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = null;
        pathReserve = null;

        String pathtest = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        new FileInputStream(new File("src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3/estimate_1.data"));
        new FileInputStream(new File("src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3/estimate_1.data.flag"));
        assertFalse(instance.simulateParkingVeiculo(pathtest));
    }

    @Test
    void simulateScooterMalEstacionada4() throws Exception {
        System.out.println("simulateScooterMalEstacionada4()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3";
        pathReserve = null;

        String pathtest = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        new FileInputStream(new File("src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3/estimate_1.data.flag"));
        assertFalse(instance.simulateParkingVeiculo(pathtest));
    }

    @Test
    void simulateScooterMalEstacionada5() throws Exception {
        System.out.println("simulateScooterMalEstacionada5()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = null;
        pathReserve = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3";

        String pathtest = "src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        new FileInputStream(new File("src/main/java/lapr/project/parking/teste/simulateScooterMalEstacionada3/estimate_1.data.flag"));
        assertFalse(instance.simulateParkingVeiculo(pathtest));
    }

    //Estimativa = -1, falta de .flag
    @Test
    void simulateDroneMalEstacionado() throws Exception {
        System.out.println("simulateDroneMalEstacionado()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateDroneMalEstacionado";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateDroneMalEstacionado";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        String assunto = "Acoplagem Drone";
        String mensagem = "O drone " + 1 + " foi acoplado sem sucesso.";
        when(emailDB.sendEmail("admlapr123@gmail.com","admlapr123@gmail.com" , assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(pathtest));

        /*new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateDroneMalEstacionado/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateDroneMalEstacionado/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);*/
    }

    //Tipo de parque diferente de drone
    @Test
    void simulateDroneMalEstacionado1() throws Exception {
        System.out.println("simulateDroneMalEstacionado1()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateDroneMalEstacionado";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateDroneMalEstacionado";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo,estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(pathtest));

        /*new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateDroneMalEstacionado/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateDroneMalEstacionado/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);*/
    }

    //Parque sem carregador
    @Test
    void simulateDroneMalEstacionado2() throws Exception {
        System.out.println("simulateDroneMalEstacionado2()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateDroneMalEstacionado";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateDroneMalEstacionado";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(pathtest));
    }

    //Estimativa 2 horas
    @Test
    void simulateScooterBemEstacionada() throws Exception {
        System.out.println("simulateScooterBemEstacionada()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateScooterBemEstacionada";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateScooterBemEstacionada";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada com sucesso, com uma estimativa de cerca de 2 horas até estar completamente carregada.";
        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(pathtest));
    }

    @Test
    void simulateScooterBemEstacionada1() throws Exception {
        System.out.println("simulateScooterBemEstacionada1()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0, 5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateScooterBemEstacionada";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateScooterBemEstacionada";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(false);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Atualização de carregamento";
        String mensagem = "Devido à quantidade de carregamentos a serem realizados em simultâneo, a nova estimativa de carregamento do seu veículo é de cerca de 2 horas.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(pathtest));
    }

    //Estimativa 2 horas
    @Test
    void simulateDroneBemEstacionado() throws Exception {
        System.out.println("simulateDroneBemEstacionado()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateDroneBemEstacionado";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateDroneBemEstacionado";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado com sucesso, com uma estimativa de cerca de 2 horas até estar completamente carregado.";
        when(emailDB.sendEmail("admlapr123@gmail.com", "admlapr123@gmail.com", assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(pathtest));

        /*new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateDroneBemEstacionado/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/reserve/simulateDroneBemEstacionado/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);*/
    }

    @Test
    void simulateDroneBemEstacionado1() throws Exception {
        System.out.println("simulateDroneBemEstacionado1()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0, 5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        path = "src/main/java/lapr/project/parking/teste/simulateDroneBemEstacionado";
        pathReserve = "src/main/java/lapr/project/parking/teste/reserve/simulateDroneBemEstacionado";

        String pathtest = path + "/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(false);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Atualização de carregamento";
        String mensagem = "Devido à quantidade de carregamentos a serem realizados em simultâneo, a nova estimativa de carregamento do seu veículo é de cerca de 2 horas.";
        when(emailDB.sendEmail("admlapr123@gmail.com", "admlapr123@gmail.com", assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(pathtest));
    }

    //Notifica estafeta da scooter bem estacionada
    @Test
    void notificaEstafeta1() {
        System.out.println("notificaEstafeta1()");
        path = null;
        pathReserve = null;
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada com sucesso, com uma estimativa de cerca de " + 3 + " horas até estar completamente carregada.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.notificaEstafeta(true, 3, email));
    }

    //Notifica estafeta da scooter mal estacionada
    @Test
    void notificaEstafeta2() {
        System.out.println("notificaEstafeta2()");
        path = null;
        pathReserve = null;
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.notificaEstafeta(false, 3, email));
    }

    //Email não enviado
    @Test
    void notificaEstafeta3() {
        System.out.println("notificaEstafeta3()");
        path = null;
        pathReserve = null;
        String email = "admlapr123@gmail.com";
        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(false);

        assertFalse(instance.notificaEstafeta(false, 3, email));
    }

    //Notifica drone bem estacionado
    @Test
    void notificaAdministrador1() {
        System.out.println("notificaAdministrador2()");
        path = null;
        pathReserve = null;
        String email = "admlapr123@gmail.com";
        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado com sucesso, com uma estimativa de cerca de 3 horas até estar completamente carregado.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.notificaAdministrador(true, 3, 1));
    }
    //Notifica drone mal estacionado
    @Test
    void notificaAdministrador2() {
        System.out.println("notificaAdministrador2()");
        path = null;
        pathReserve = null;
        String email = "admlapr123@gmail.com";
        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado sem sucesso.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.notificaAdministrador(false, 3, 1));
    }

    @Test
    void notificaAdministrador3() {
        System.out.println("notificaAdministrador3()");
        path = null;
        pathReserve = null;
        String email = "admlapr123@gmail.com";
        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado sem sucesso.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(false);

        assertFalse(instance.notificaAdministrador(false, 3, 1));
    }

    @Test
    void updateNotificacaoTrue(){
        System.out.println("updateNotificacaoTrue()");
        path = null;
        pathReserve = null;
        String email = "email";
        String assunto = "Atualização de carregamento";
        String mensagem = "Devido à quantidade de carregamentos a serem realizados em simultâneo, a nova estimativa de carregamento do seu veículo é de cerca de 3 horas.";
        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.updateEstimativa(3,email));
    }

    @Test
    void updateNotificacaoFalse(){
        System.out.println("updateNotificacaoFalse()");
        path = null;
        pathReserve = null;
        String email = "email";
        String assunto = "Atualização de carregamento";
        String mensagem = "Devido à quantidade de carregamentos a serem realizados em simultâneo, a nova estimativa de carregamento do seu veículo é de cerca de 3 horas.";
        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(false);

        assertFalse(instance.updateEstimativa(3,email));
    }

    @Test
    void adicionarEstacionamentoVeiculoTrueScooter() throws SQLException {
        System.out.println("adicionarEstacionamentoVeiculoTrueScooter()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        path = null;
        pathReserve = null;

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada com sucesso, com uma estimativa de cerca de 2 horas até estar completamente carregada.";
        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.adicionarEstacionamentoVeiculo(veiculo,parque,estacionamento,estafeta,2));
    }

    @Test
    void adicionarEstacionamentoVeiculoTrueDrone() throws SQLException {
        System.out.println("adicionarEstacionamentoVeiculoTrueDrone()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        path = null;
        pathReserve = null;

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo, estacionamento.getIdParque())).thenReturn(true);
        when(estacionamentosDB.getEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getUtilizadorEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado com sucesso, com uma estimativa de cerca de 2 horas até estar completamente carregado.";
        when(emailDB.sendEmail("admlapr123@gmail.com", "admlapr123@gmail.com", assunto, mensagem)).thenReturn(true);

        assertTrue(instance.adicionarEstacionamentoVeiculo(veiculo,parque,estacionamento,estafeta,2));
    }

    @AfterEach
    void afterUp() throws Exception {
        if(path != null && pathReserve != null) {
            new File(path + "/estimate_1.data").createNewFile();
            new File(path + "/estimate_1.data.flag").createNewFile();

            File file = new File(path + "/estimate_1.data");
            File fileflag = new File(path + "/estimate_1.data.flag");

            File file1 = new File(pathReserve + "/estimate_1.data");
            File fileflag1 = new File(pathReserve + "/estimate_1.data.flag");

            copyContent(file1, file);
            copyContent(fileflag1, fileflag);
        }
    }

    public static void copyContent(File a, File b)
            throws Exception
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);

        try {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}