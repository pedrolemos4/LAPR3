package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class EstacionamentoController {

    private final EmailDB emailDB;
    private final EstacionamentosDB estacionamentosDB;
    private final VeiculoDB veiculoDB;
    private final ParqueDB parqueDB;
    private final EstafetaDB estafetaDB;
    private static final String ADMINEMAIL = "admlapr123@gmail.com";
    private static final String SCOOTER = "scooter";

    /**
     * Constroi uma instancia de EstacionamentoController recebendo uma
     * instancia de emailDB, estacionamentosDB, veiculoDB
     *
     * @param emailDB instancia de emailDB
     * @param estacionamentosDB instancia de estacionamentosDB
     * @param veiculoDB instancia de veiculoDB
     */
    public EstacionamentoController(EmailDB emailDB, EstacionamentosDB estacionamentosDB, VeiculoDB veiculoDB, ParqueDB parqueDB, EstafetaDB estafetaDB) {
        this.emailDB = emailDB;
        this.estacionamentosDB = estacionamentosDB;
        this.veiculoDB = veiculoDB;
        this.parqueDB = parqueDB;
        this.estafetaDB = estafetaDB;
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
        if(estimatePath != null) {
            StringBuilder sb;
            sb = new StringBuilder();
            path = sb.append(path).append("/").append(estimatePath).toString();

            return (simulateParkingVeiculo(path));
        }
        return false;
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
        File newFileFlag = new File(path + ".flag");
        Scanner scan = new Scanner(newFile);

        String line = scan.nextLine();

        scan.close();

        String[] itens = line.split(",");

        int estimativa = Integer.parseInt(itens[0]);

        int idParque = Integer.parseInt(itens[1]);

        int idVeiculo = Integer.parseInt(itens[2]);

        int nifEstafeta = Integer.parseInt(itens[3]);

        int idEstacionamento = Integer.parseInt(itens[4]);

        Veiculo veiculo = veiculoDB.getVeiculoById(idVeiculo);

        Estacionamento estac = estacionamentosDB.getEstacionamentoById(idEstacionamento);

        Parque parque = parqueDB.getParqueByID(idParque);

        Utilizador estafeta = estafetaDB.getUtilizadorEstafetaByNIF(nifEstafeta);

        if (newFile.delete() && newFileFlag.delete()) {
            return adicionarEstacionamentoVeiculo(veiculo, parque, estac, estafeta,estimativa);
        }else {
            return false;
        }
    }

    public boolean adicionarEstacionamentoVeiculo(Veiculo veiculo, Parque parque, Estacionamento estac, Utilizador estafeta, int estimativa) {
        if (veiculo.getDescricao().equalsIgnoreCase(parque.getTipo()) && estac.getCarregador() == 1) {
            if (estimativa == -1) {
                return notificarMauEstacionamento(veiculo, estafeta, estimativa) ;
            } else if (estacionamentosDB.getEstacionamentoVeiculo(estac, veiculo)) {
                estacionamentosDB.addEstacionamentoVeiculo(estac, veiculo, estac.getIdParque());
                try {
                    veiculo.setEstadoVeiculo(2);
                    veiculoDB.updateVeiculo(veiculo);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return notificarNovaEstimativa(veiculo, estafeta, estimativa);
            } else {
                return notificarUpdateEstimativa(veiculo, estafeta, estimativa);
            }

        } else {
            return false;
        }
    }

    public boolean notificarNovaEstimativa(Veiculo veiculo, Utilizador estafeta, int estimativa){
        if (veiculo.getDescricao().equalsIgnoreCase(SCOOTER)) {
            return (notificaEstafeta(true, estimativa, estafeta.getEmail()));
        } else {
            return (notificaAdministrador(true, estimativa, veiculo.getId()));
        }
    }

    public boolean notificarMauEstacionamento(Veiculo veiculo, Utilizador estafeta, int estimativa){
        if (veiculo.getDescricao().equalsIgnoreCase(SCOOTER)) {
            return (notificaEstafeta(false, estimativa, estafeta.getEmail()));
        } else {
            return (notificaAdministrador(false, estimativa, veiculo.getId()));
        }
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
        String assunto = "Estacionamento Scooter";
        String mensagem;
        if (bemEstacionado) {
            mensagem = "A scooter foi estacionada com sucesso, com uma estimativa de cerca de " + estimativa + " horas até estar completamente carregada.";
        } else {
            mensagem = "A scooter foi estacionada sem sucesso, tente novamente.";
        }
        return emailDB.sendEmail(ADMINEMAIL, email, assunto, mensagem);
    }

    public boolean notificaAdministrador(boolean bemEstacionado, int estimativa, int idVeiculo) {
        String assunto = "Acoplagem Drone";
        String mensagem;
        if (bemEstacionado) {
            mensagem = "O drone " + idVeiculo + " foi acoplado com sucesso, com uma estimativa de cerca de " + estimativa + " horas até estar completamente carregado.";
        } else {
            mensagem = "O drone " + idVeiculo + " foi acoplado sem sucesso.";
        }
        return emailDB.sendEmail(ADMINEMAIL, ADMINEMAIL, assunto, mensagem);
    }

    public boolean notificarUpdateEstimativa(Veiculo veiculo, Utilizador estafeta, int estimativa){
        if (veiculo.getDescricao().equalsIgnoreCase(SCOOTER)) {
            return (updateEstimativa(estimativa, estafeta.getEmail()));
        } else {
            return (updateEstimativa(estimativa, ADMINEMAIL));
        }
    }

    public boolean updateEstimativa(int estimativa, String email) {
        String assunto = "Atualização de carregamento";
        String mensagem = "Devido à quantidade de carregamentos a serem realizados em simultâneo, a nova estimativa de carregamento do seu veículo é de cerca de " + estimativa + " horas.";
        return emailDB.sendEmail(ADMINEMAIL, email, assunto, mensagem);
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

        String estimate;
        String fileExtension;
        boolean flag = true;
        String filename = null;
        if (pathnames != null) {
            for (int i = 0; (i < pathnames.length) && (flag); i++) {
                estimate = pathnames[i].substring(0, 8);
                int length = pathnames[i].length();
                fileExtension = pathnames[i].substring(length - 5, length);
                if (estimate.equals("estimate") && fileExtension.equals(".data")) {
                    flag = false;
                    filename = pathnames[i];
                }
            }
        }
        return filename;
    }
}
