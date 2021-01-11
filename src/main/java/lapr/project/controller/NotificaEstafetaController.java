package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NotificaEstafetaController {

    private final Scooter scoot;
    private final Estafeta estafeta;
    private final Entrega entrega;

    private final EstacionamentosDB estacionamentosDB;

    public NotificaEstafetaController(EstafetaDB estafetaDB, ScooterDB scooterDB, EntregaDB entregaDB, EstacionamentosDB estacionamentosDB) {
        this.estacionamentosDB = estacionamentosDB;

        String email = UserSession.getInstance().getUser().getEmail();

        this.estafeta = estafetaDB.getEstafetaByEmail(email);

        this.entrega = entregaDB.getEntregaAtiva(email);

        int scootId = this.entrega.getIdScooter();

        this.scoot = scooterDB.getScooterById(scootId);
    }

    public void simulateParkingScooter(int estacionamentoLote) throws FileNotFoundException {
        String path = "C:\\ARQCP\\partilha\\LAPR3\\estimate_2021_02_02_02_02_02.data";
        File newFile = new File(path);

        try (Scanner scan = new Scanner(newFile)) {
            String line = scan.nextLine();
            int estimativa = Integer.parseInt(line);

            this.scoot.setEstadoScooter(2);

            EmailDB emaildb = new EmailDB();

            Estacionamento estac = estacionamentosDB.getEstacionamentoById(estacionamentoLote);

            estacionamentosDB.addEstacionamentoScooter(estac,scoot);
            emaildb.sendEmail(this.estafeta.getEmail(), "Estacionamento Scooter", "A Scooter foi estacionada com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.");
        }
    }
}
