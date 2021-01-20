package lapr.project.controller;

import lapr.project.data.EmailDB;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.ParqueDB;
import lapr.project.data.VeiculoDB;
import lapr.project.model.Estacionamento;
import lapr.project.model.Parque;
import lapr.project.model.Veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class EstacionamentoController {

    private final EmailDB emailDB;
    private final EstacionamentosDB estacionamentosDB;
    private final VeiculoDB veiculoDB;
    private final ParqueDB parqueDB;

    /**
     * Constroi uma instancia de EstacionamentoController recebendo uma
     * instancia de emailDB, estacionamentosDB, veiculoDB
     *
     * @param emailDB instancia de emailDB
     * @param estacionamentosDB instancia de estacionamentosDB
     * @param veiculoDB instancia de veiculoDB
     */
    public EstacionamentoController(EmailDB emailDB, EstacionamentosDB estacionamentosDB, VeiculoDB veiculoDB, ParqueDB parqueDB) {
        this.emailDB = emailDB;
        this.estacionamentosDB = estacionamentosDB;
        this.veiculoDB = veiculoDB;
        this.parqueDB = parqueDB;
    }

    /**
     * Verifica a pasta destinada à simulação de carregamentos à procura de
     * ficheiros para simular o carregamento
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public boolean checkParkings(String path) throws FileNotFoundException {
        String estimatePath = getDiretory(path);
        path = path + "/" + estimatePath;
        return simulateParkingVeiculo(path);
    }

    /**
     * Método principal para a simulação do carregamento dos veículos, interaje
     * com a Base de Dados para a recolha de dados sobre o estacionamento e para
     * a criação da instância de um novo estacionamento.
     *
     * @param path
     * @return true se o método conseguir criar a instância de estacionamento
     * sem nenhum erro
     * @throws FileNotFoundException quando não é possível encontrar o ficheiro
     * encontrado
     */
    public boolean simulateParkingVeiculo(String path) throws FileNotFoundException {
        File newFile = new File(path);
        Scanner scan = new Scanner(newFile);

        String line = scan.nextLine();

        scan.close();

        String[] itens = line.split(",");

        int estimativa = Integer.parseInt(itens[0]);

        String emailEstafeta = itens[1];

        int idVeiculo = Integer.parseInt(itens[2]);

        int idEstacionamento = Integer.parseInt(itens[3]);

        Veiculo veiculo = veiculoDB.getVeiculoById(idVeiculo);

        Estacionamento estac = estacionamentosDB.getEstacionamentoById(idEstacionamento);

        Parque parque = parqueDB.getParqueByID(estac.getIdParque());

        estacionamentosDB.addEstacionamentoVeiculo(estac, veiculo);

//        if (veiculo.getTipo().equalsIgnoreCase(parque.getTipo()) && estac.getCarregador() == 1) {
//            if (estimativa == -1) {
//                return notificaEstafeta(false, estimativa, emailEstafeta);
//            } else {
//                if (veiculo.getTipo().equalsIgnoreCase("scooter")) {
//                    try {
//                        veiculo.setEstadoVeiculo(0);
//                        veiculoDB.updateVeiculo(veiculo);
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                    return notificaEstafeta(true, estimativa, emailEstafeta);
//                } else {
//                    return true;
//                }
//            }
//        }else{
//            return notificaEstafeta(false, estimativa, emailEstafeta);
//        }
        return true; //pus isto aqui só para não dar erro quando comentei
    }

    /**
     * Envia um email ao estafeta com informação sobre o estacionamento da sua
     * scooter
     *
     * @param bemEstacionado reflete se foi bem estacionada a scooter
     * @param estimativa representa o número de horas que vai levar até o
     * carregamento se encontrar concluido
     * @param email email do estafeta
     * @return true se o email tiver sido enviado
     */
    public boolean notificaEstafeta(boolean bemEstacionado, int estimativa, String email) {
        String assunto = "Estacionamento Veiculo";
        String mensagem;
        if (bemEstacionado) {
            mensagem = "O veiculo foi estacionado com sucesso, com uma estimativa de " + estimativa + " horas até estar completamente carregada.";
        } else {
            mensagem = "O veiculo foi estacionado sem sucesso, tente novamente.";
        }
        return emailDB.sendEmail("admlapr123@gmail.com", email, assunto, mensagem);
    }

    /**
     * Busca o nome dos ficheiros introduzidos pelo utilizador na pasta de
     * simulação de carregamento de veículos
     *
     * @param path caminho para o diretório da pasta de ficheiros de simulação
     * de carregamento
     * @return primeiro ficheiro da pasta
     */
    public String getDiretory(String path) {
        String[] pathnames;

        File f = new File(path);

        pathnames = f.list();

        return pathnames[0];
    }
}
