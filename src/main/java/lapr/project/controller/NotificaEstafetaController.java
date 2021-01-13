package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NotificaEstafetaController {

    private final Veiculo scoot;
    private final Estafeta estafeta;
    private final Entrega entrega;

    private final EstacionamentosDB estacionamentosDB;

    public NotificaEstafetaController(EstafetaDB estafetaDB, VeiculoDB veiculoDB, EntregaDB entregaDB, EstacionamentosDB estacionamentosDB) {
        this.estacionamentosDB = estacionamentosDB;

        String email = UserSession.getInstance().getUser().getEmail();

        this.estafeta = estafetaDB.getEstafetaByEmail(email);

        this.entrega = entregaDB.getEntregaAtiva(email);

        int scootId = this.entrega.getIdVeiculo();

        this.scoot = veiculoDB.getVeiculoById(scootId);
    }

    public boolean simulateParkingVeiculo(int estacionamentoLote) throws FileNotFoundException {
        String path = "src/main/java/lapr/project/parking/estimate_2021_02_02_02_02_02.data";
        File newFile = new File(path);
        try (Scanner scan = new Scanner(newFile)) {
            String line = scan.nextLine();
            int estimativa = Integer.parseInt(line);

            EmailDB emaildb = new EmailDB();

            Estacionamento estac = estacionamentosDB.getEstacionamentoById(estacionamentoLote);

            estacionamentosDB.addEstacionamentoVeiculo(estac,scoot);

            return emaildb.sendEmail(this.estafeta.getEmail(), "Estacionamento Veiculo", "O veiculo foi estacionada com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.");
        }catch(Exception e){
            return false;
        }
    }
}
