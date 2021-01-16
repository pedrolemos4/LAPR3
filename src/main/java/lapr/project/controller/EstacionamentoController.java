package lapr.project.controller;

import lapr.project.data.EmailDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.VeiculoDB;
import lapr.project.model.Entrega;
import lapr.project.model.Estacionamento;
import lapr.project.model.Veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class EstacionamentoController {

    private final EntregaDB entregaDB;
    private final EmailDB emailDB;
    private final EstacionamentosDB estacionamentosDB;
    private final VeiculoDB veiculoDB;

    public EstacionamentoController(EntregaDB entregaDB,EmailDB emailDB,EstacionamentosDB estacionamentosDB,VeiculoDB veiculoDB) {
        this.entregaDB = entregaDB;
        this.emailDB = emailDB;
        this.estacionamentosDB = estacionamentosDB;
        this.veiculoDB = veiculoDB;
    }

    public boolean checkParkings(String path) {
        String estimatePath = getDiretory(path);
        path = path + "/" + estimatePath;
        return simulateParkingVeiculo(path);
    }

    public boolean simulateParkingVeiculo(String path){
        File newFile = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(newFile);
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            return false;
        }
        String fileName = newFile.getName();
        String date = fileName.substring(8,18);

        String line = scan.nextLine();
        String[] itens = line.split(",");

        int estimativa = Integer.parseInt(itens[0]);

        String emailEstafeta = itens[1];

        int idVeiculo = Integer.parseInt(itens[2]);

        int numeroLote = Integer.parseInt(itens[3]);

        Entrega entregaAtiva = entregaDB.getEntregaAtiva(emailEstafeta);

        entregaAtiva.setDataFim(date);

        Veiculo veiculo = veiculoDB.getVeiculoById(idVeiculo);

        Estacionamento estac = estacionamentosDB.getEstacionamentoById(numeroLote);

        estacionamentosDB.addEstacionamentoVeiculo(estac, veiculo);

        if(estimativa == -1){
            return notificaEstafeta(false,estimativa,emailEstafeta);
        }else {
            timerCarregamento(estimativa, veiculo);

            if (veiculo.getTipo().equalsIgnoreCase("scooter")) {
                return notificaEstafeta(true, estimativa, emailEstafeta);
            } else {
                return true;
            }
        }
    }

    public boolean notificaEstafeta(boolean bemEstacionado, int estimativa, String email){
        String assunto = "Estacionamento Veiculo";
        String mensagem;
        if(bemEstacionado) {
            mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.";
        }else{
            mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";
        }
        return emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem);
    }

    public void carregamentoCompleto(Veiculo veiculo){
        veiculo.setPercentagemBateria(100);
    }

    public boolean timerCarregamento(int estimativa, Veiculo veiculo){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                carregamentoCompleto(veiculo);
            }
        };

        timer.schedule(timerTask, (estimativa * 3600));

        return true;
    }

    public String getDiretory(String path) {
        String[] pathnames;

        File f = new File(path);

        pathnames = f.list();

        return pathnames[0];
    }
}