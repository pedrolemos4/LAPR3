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

    private final EstacionamentosDB estacionamentosDB;

    public NotificaEstafetaController(EstafetaDB estafetaDB, VeiculoDB veiculoDB, EntregaDB entregaDB, EstacionamentosDB estacionamentosDB) {
        this.estacionamentosDB = estacionamentosDB;

        String email = UserSession.getInstance().getUser().getEmail();

        this.estafeta = estafetaDB.getEstafetaByEmail(email);

        Entrega entrega = entregaDB.getEntregaAtiva(email);

        int scootId = entrega.getIdVeiculo();

        this.scoot = veiculoDB.getVeiculoById(scootId);
    }

    public boolean simulateParkingVeiculo(int estacionamentoLote, EmailDB emailDB, String path){
        File newFile = new File(path);
        Scanner scan = null;
        String line = null;
        try {
            scan = new Scanner(newFile);
            line = scan.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(scan != null){
                scan.close();
            }
        }

        int estimativa = Integer.parseInt(line);

        Estacionamento estac = estacionamentosDB.getEstacionamentoById(estacionamentoLote);

        estacionamentosDB.addEstacionamentoVeiculo(estac, scoot);

        return emailDB.sendEmail(this.estafeta.getEmail(), "Estacionamento Veiculo", "O veiculo foi estacionado com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.");
    }
}
