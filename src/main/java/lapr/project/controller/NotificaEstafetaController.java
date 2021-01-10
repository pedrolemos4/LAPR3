package lapr.project.controller;

import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.EmailDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.mail.*;
import javax.security.auth.login.Configuration;

public class NotificaEstafetaController {

    private final Scooter scoot;
    private final Estafeta estafeta;
    private final Entrega entrega;
    private Estacionamento estac;
    private final EstafetaDB estafetaDB;
    private final EntregaDB entregaDB;
    private final ScooterDB scooterDB;
    private final FacadeAuthorization facade;

    public NotificaEstafetaController(EstafetaDB estafetaDB, ScooterDB scooterDB, EntregaDB entregaDB) {
        this.estafetaDB = estafetaDB;
        this.scooterDB = scooterDB;
        this.entregaDB = entregaDB;
        this.facade = POTApplication.getFacadeAuthorization();

        String email = facade.getCurrentSession().getUser().getEmail();

        this.estafeta = estafetaDB.getEstafetaByEmail(email);

        this.entrega = entregaDB.getEntregaAtiva(email);

        int scootId = this.entrega.getIdScooter();

        this.scoot = scooterDB.getScooterById(scootId);
    }

    public void simulateParkingScooter(String email) throws FileNotFoundException {
        String path = "C:\\ARQCP\\partilha\\LAPR3\\estimate_2021_02_02_02_02_02.data";
        File newFile = new File(path);

        try (Scanner scan = new Scanner(newFile)) {
            String line = scan.nextLine();
            int estimativa = Integer.parseInt(line);

            EmailDB emaildb = new EmailDB();
            emaildb.sendEmail(email, "Estacionamento Scooter", "A Scooter foi estacionada com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.");
        }
    }
}
