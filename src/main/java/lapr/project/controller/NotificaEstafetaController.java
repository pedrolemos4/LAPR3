package lapr.project.controller;

import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

    public void simulateParkingScooter() {
        File newFile = new File("..\\parking\\estimativa*.data");

    }

    public boolean checkWellDockedScooter(){
        return false;
    }



}
