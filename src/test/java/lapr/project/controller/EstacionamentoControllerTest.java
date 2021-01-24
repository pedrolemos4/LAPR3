package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Parque;
import lapr.project.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        instance = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB(),new EstafetaDB());
        System.out.println("getDiretory()");
        String expected = "estimate_1.data";
        assertEquals(expected, instance.getDiretory("src/main/java/lapr/project/parking/teste/casosJAVA/getDirectory"));
    }

    @Test
    void getDiretoryNull() {
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
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/checkParkingsTrue";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        //File file = new File(path + "/estimate_1.data");
        //File fileflag = new File(path + "/estimate_1.data.flag");

        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada sem sucesso, tente novamente.";

        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);
        assertTrue(instance.checkParkings(path));

        new File(path + "/estimate_1.data").createNewFile();
        new File(path + "/estimate_1.data.flag").createNewFile();

        File file = new File(path + "/estimate_1.data");
        File fileflag = new File(path + "/estimate_1.data.flag");

        System.out.println(file.getPath());

        System.out.println(fileflag.getPath());

        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/CheckParkingsTrue/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/CheckParkingsTrue/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    @Test
    void checkParkingsFalse() throws FileNotFoundException {
        System.out.println("checkParkingsFalse()");
        assertFalse(instance.checkParkings("src/main/java/lapr/project/parking/teste/casos testes unitários JAVA/getDirectoryNull"));
    }

    //Estimativa = -1, falta de .flag
    @Test
    void simulateScooterMalEstacionada() throws Exception {
        System.out.println("simulateScooterMalEstacionada()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateScooterMalEstacionada/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada sem sucesso, tente novamente.";
        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterMalEstacionada/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterMalEstacionada/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Tipo de parque diferente de scooter
    @Test
    void simulateScooterMalEstacionada1() throws Exception {
        System.out.println("simulateScooterMalEstacionada1()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateScooterMalEstacionada/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterMalEstacionada/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterMalEstacionada/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Parque sem carregador
    @Test
    void simulateScooterMalEstacionada2() throws Exception {
        System.out.println("simulateScooterMalEstacionada2()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateScooterMalEstacionada/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterMalEstacionada/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterMalEstacionada/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Estimativa = -1, falta de .flag
    @Test
    void simulateDroneMalEstacionado() throws Exception {
        System.out.println("simulateDroneMalEstacionado()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateDroneMalEstacionado/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        String assunto = "Acoplagem Drone";
        String mensagem = "O drone " + 1 + " foi acoplado sem sucesso.";
        when(emailDB.sendEmail("admlapr123@gmail.com","admlapr123@gmail.com" , assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneMalEstacionado/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneMalEstacionado/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Tipo de parque diferente de drone
    @Test
    void simulateDroneMalEstacionado1() throws Exception {
        System.out.println("simulateDroneMalEstacionado1()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateDroneMalEstacionado/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneMalEstacionado/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneMalEstacionado/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Parque sem carregador
    @Test
    void simulateDroneMalEstacionado2() throws Exception {
        System.out.println("simulateDroneMalEstacionado2()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 0, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateDroneMalEstacionado/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);

        assertFalse(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneMalEstacionado/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneMalEstacionado/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Estimativa 2 horas
    @Test
    void simulateScooterBemEstacionada() throws Exception {
        System.out.println("simulateScooterBemEstacionada()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "scooter", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "scooter", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateScooterBemEstacionada/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Estacionamento Scooter";
        String mensagem = "A scooter foi estacionada com sucesso, com uma estimativa de cerca de 2 horas até estar completamente carregada.";
        when(emailDB.sendEmail("admlapr123@gmail.com", estafeta.getEmail(), assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterBemEstacionada/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateScooterBemEstacionada/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Estimativa 2 horas
    @Test
    void simulateDroneBemEstacionado() throws Exception {
        System.out.println("simulateDroneBemEstacionado()");

        Estafeta estafeta = new Estafeta(1, "um", "um", 20, 20, "pass", 0);
        Veiculo veiculo = new Veiculo(1, "drone", 120, 55, 50, 50, 50, 0,5);
        Estacionamento estacionamento = new Estacionamento(0, 1, 0);
        Parque parque = new Parque(0, 1, 1, "drone", 20);
        String path = "src/main/java/lapr/project/parking/teste/casosJAVA/simulateDroneBemEstacionado/estimate_1.data";

        when(veiculoDB.getVeiculoById(veiculo.getId())).thenReturn(veiculo);
        when(estacionamentosDB.getEstacionamentoById(estacionamento.getNumeroLote())).thenReturn(estacionamento);
        when(estacionamentosDB.addEstacionamentoVeiculo(estacionamento, veiculo)).thenReturn(true);
        when(parqueDB.getParqueByID(0)).thenReturn(parque);
        when(estafetaDB.getEstafetaByNIF(estafeta.getNIF())).thenReturn(estafeta);
        when(veiculoDB.updateVeiculo(veiculo)).thenReturn(true);

        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado com sucesso, com uma estimativa de cerca de 2 horas até estar completamente carregado.";
        when(emailDB.sendEmail("admlapr123@gmail.com", "admlapr123@gmail.com", assunto, mensagem)).thenReturn(true);

        assertTrue(instance.simulateParkingVeiculo(path));

        new File(path).createNewFile();
        new File(path + ".flag").createNewFile();

        File file = new File(path);
        File fileflag = new File(path + ".flag");
        File file1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneBemEstacionado/estimate_1.data");
        File fileflag1 = new File("src/main/java/lapr/project/parking/teste/casosJAVA/reserve/simulateDroneBemEstacionado/estimate_1.data.flag");

        copyContent(file1,file);
        copyContent(fileflag1, fileflag);
    }

    //Notifica estafeta da scooter bem estacionada
    @Test
    void notificaEstafeta1() {
        System.out.println("notificaEstafeta1()");
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
        String email = "admlapr123@gmail.com";
        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado sem sucesso.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(true);

        assertTrue(instance.notificaAdministrador(false, 3, 1));
    }

    @Test
    void notificaAdministrador3() {
        System.out.println("notificaAdministrador3()");
        String email = "admlapr123@gmail.com";
        String assunto = "Acoplagem Drone";
        String mensagem = "O drone 1 foi acoplado sem sucesso.";

        when(emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem)).thenReturn(false);

        assertFalse(instance.notificaAdministrador(false, 3, 1));
    }

    public static void copyContent(File a, File b)
            throws Exception
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);

        try {

            int n;

            // read() function to read the
            // byte of data
            while ((n = in.read()) != -1) {
                // write() function to write
                // the byte of data
                out.write(n);
            }
        }
        finally {
            if (in != null) {

                // close() function to close the
                // stream
                in.close();
            }
            // close() function to close
            // the stream
            if (out != null) {
                out.close();
            }
        }
        //System.out.println("File Copied");
    }
}