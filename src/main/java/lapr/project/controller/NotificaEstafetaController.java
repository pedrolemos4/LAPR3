package lapr.project.controller;

import lapr.project.authorization.FacadeAuthorization;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;
import oracle.sql.TIMESTAMP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class NotificaEstafetaController {

    private Scooter scoot;
    private Estafeta estafeta;
    private Entrega entrega;
    private Estacionamento estac;
    private final EstafetaDB estafetaDB;
    private final EntregaDB entregaDB;
    private final ScooterDB scooterDB;
    private FacadeAuthorization facade;

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

    }

    public boolean checkWellDockedScooter(){
        return false;
    }

}
