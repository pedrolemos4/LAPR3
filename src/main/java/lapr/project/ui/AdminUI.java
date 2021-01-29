/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.VeiculoController;
import lapr.project.data.VeiculoDB;
import lapr.project.login.UserSession;
import lapr.project.model.Drone;
import lapr.project.model.Scooter;
import lapr.project.model.Veiculo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago
 */
public class AdminUI {

    private static final int PERCENTAGEM = 100;
    private static final String DRONE = "drone";
    private static final String SCOOTER = "scooter";
    private static final String CONFIRMACAO = "\nConfirme a informação relativa ao veículo(S/N)";

    /**
     * Scanner para leitura de teclado
     */
    public static final Scanner LER = new Scanner(System.in);
    public static final LoginUI loginUI = new LoginUI();

    /**
     *
     */
    public void showMenu() {
        System.out.println("\nRIDE SHARING - Menu Administrador\n--------------------------------"
                + "\n 1 - Adicionar veiculo"
                + "\n 2 - Remover veiculo"
                + "\n 3 - Atualizar veiculo"
                + "\n 4 - Registar estafeta"
                + "\n 5 - Atualizar estafeta"
                + "\n 6 - Adicionar item ao stock"
                + "\n 7 - Atualizar item"
                + "\n 8 - Registar farmácia"
                + "\n 9 - Adicionar parque"
                + "\n 0 - Exit"
                + "\n Escolha uma das opções.");
    }

    public void menu() throws ClassNotFoundException, SQLException, ParseException {
        String opt;
        do {
            showMenu();
            opt = LER.nextLine();

            switch (opt) {
                case "1":
                    addVeiculo();
                    break;
                case "2":
                    removeVeiculo();
                    break;
                case "3":
                    updateVeiculo();
                    break;
                case "4":
                    registarEstafeta();
                    break;
                case "5":
                    atualizarEstafeta();
                    break;
                case "6":
                    adicionarItem();
                    break;
                case "7":
                    atualizarItem();
                    break;
                case "8":
                    registarFarmacia();
                    break;
                case "9":
                    adicionarParque();
                    break;
                case "0":
                    UserSession.getInstance().doLogout();
                    loginUI.menu();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!opt.equals("0"));
    }

    /**
     * Interface that allows the addition of a veiculo into the system
     */
    public void addVeiculo() throws ClassNotFoundException, SQLException, ParseException {
        System.out.println("Pretende adicionar uma scooter ou um drone?");
        String descricao = LER.nextLine();
        System.out.println("Insira a capacidade máxima da bateria:");
        int capacidade = LER.nextInt();

        int percentagemBateria = PERCENTAGEM;

        System.out.println("\nO veículo vai estar disponível imediatamente após a sua criação? (S/N)");
        LER.nextLine();
        String resposta = LER.nextLine();
        int idestado;
        String estado;
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {
            idestado = 1;
            estado = "Disponível";
        } else {
            idestado = 0;
            estado = "Indisponível";
        }

        System.out.println("Insira o peso total do veículo:");
        double peso = LER.nextDouble();

        System.out.println("Insira o peso máximo que o veículo suporta:");
        double pesoMaximo = LER.nextDouble();

        System.out.println("Insira a potência do veículo:");
        double potencia = LER.nextDouble();

        System.out.println("Insira a área frontal do seu veículo: ");
        double areaFrontal = LER.nextDouble();

        if (descricao.equalsIgnoreCase(DRONE)) {
            System.out.println("Insira a potencia que o veiculo precisa para levantar voo");
            double hoverpower = LER.nextDouble();
            System.out.println("Descrição:\t" + descricao
                    + "\nEstado:\t" + estado
                    + "\nCapacidade de Bateria:\t" + capacidade
                    + "\nPercentagem de bateria:\t" + percentagemBateria
                    + "\nPeso:\t" + peso
                    + "\nPeso máximo:\t" + pesoMaximo
                    + "\nPotencia:\t" + potencia
                    + "\nHover Power:\t" + hoverpower);

            System.out.println(CONFIRMACAO);
            LER.nextLine();
            String confirmacao = LER.nextLine();

            if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {

                VeiculoController sc = new VeiculoController(new VeiculoDB());
                try {
                    Veiculo ve = sc.addVeiculo(descricao, capacidade, percentagemBateria, peso, pesoMaximo, potencia, idestado, areaFrontal);
                    Drone dr = sc.novoDrone(ve, ve.getId(), hoverpower);
                    sc.registaDrone(dr);
                    System.out.println("\n\nVeículo adicionado com sucesso");
                } catch (SQLException ex) {
                    Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                menu();
            } else {
                menu();
            }
        } else {
            System.out.println("Descrição:\t" + descricao
                    + "\nEstado:\t" + estado
                    + "\nCapacidade de Bateria:\t" + capacidade
                    + "\nPercentagem de bateria:\t" + percentagemBateria
                    + "\nPeso:\t" + peso
                    + "\nPeso máximo:\t" + pesoMaximo
                    + "\nPotencia:\t" + potencia
                    + "\nÁrea Frontal:\t" + areaFrontal);

            System.out.println(CONFIRMACAO);
            LER.nextLine();
            String confirmacao = LER.nextLine();

            if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {

                VeiculoController sc = new VeiculoController(new VeiculoDB());
                try {
                    Veiculo ve = sc.addVeiculo(descricao, capacidade, percentagemBateria, peso, pesoMaximo, potencia, idestado, areaFrontal);
                    Scooter sr = sc.novaScooter(ve, ve.getId());
                    sc.registaScooter(sr);
                    System.out.println("\n\nVeículo adicionado com sucesso'");
                } catch (SQLException ex) {
                    Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                menu();
            } else {
                menu();
            }
        }
    }

    public void removeVeiculo() throws ClassNotFoundException, SQLException, ParseException {
        System.out.println("Insira o id do veículo a remover:");
        int idVeiculo = LER.nextInt();
        VeiculoController sc = new VeiculoController(new VeiculoDB());
        Veiculo v = sc.getVeiculoById(idVeiculo);
        System.out.println("veiculo: " + v.toString());
        System.out.println("Confirma a remoção do veículo com o id " + idVeiculo + "?(S/N)");
        LER.nextLine();
        String resposta = LER.nextLine();

        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N")) {
            try {
                if (v.getDescricao().equalsIgnoreCase(DRONE)) {
                    sc.removeDrone(idVeiculo);
                }
                if (v.getDescricao().equalsIgnoreCase(SCOOTER)) {
                    sc.removeScooter(idVeiculo);
                }
                sc.removeVeiculo(idVeiculo);
                System.out.println("Veículo removido com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            menu();
        } else {
            menu();
        }
    }

    public void updateVeiculo() throws ClassNotFoundException, SQLException, ParseException {
        VeiculoController sc = new VeiculoController(new VeiculoDB());
        List<Veiculo> lista = sc.getListaVeiculo();

        double cap;
        double pesoMaximo;
        double peso;
        double potencia;
        double hoverpower = 0;
        for (Veiculo s : lista) {
            System.out.println();
            System.out.println(s.toString());
        }
        System.out.println("Insira o id do veículo que pretende atualizar");
        int id = LER.nextInt();
        Veiculo veiculo = sc.getVeiculoById(id);
        System.out.println("Pretende atualizar a capacidade máxima da bateria do veículo? (S/N)");
        LER.nextLine();
        String resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira a nova capacidade máxima da bateria do veículo:");
            cap = LER.nextDouble();
            veiculo.setCapacidade(cap);
        }
        System.out.println("Pretende atualizar o valor do peso máximo que o veículo suporta? (S/N)");
        LER.nextLine();
        resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira a nova peso máximo do veículo:");
            pesoMaximo = LER.nextDouble();
            veiculo.setPesoMaximo(pesoMaximo);
        }
        System.out.println("Pretende atualizar o valor do peso do veículo? (S/N)");
        LER.nextLine();
        resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira o peso atualizado do veículo:");
            peso = LER.nextDouble();
            veiculo.setPesoVeiculo(peso);
        }
        System.out.println("Pretende atualizar a potencia do veículo? (S/N)");
        resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira o novo valor da potencia do veículo:");
            potencia = LER.nextDouble();
            veiculo.setPotencia(potencia);
        }

        System.out.println("\nO veículo vai estar disponível imediatamente após a sua atualização? (S/N)");
        resposta = LER.nextLine();
        int idestado;
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {
            idestado = 1;
            veiculo.setEstadoVeiculo(idestado);
        } else {
            idestado = 0;
            veiculo.setEstadoVeiculo(idestado);
        }

        if (veiculo.getDescricao().equalsIgnoreCase(DRONE)) {
            System.out.println("Pretende atualizar o valor do power pro do veículo? (S/N)");
            resposta = LER.nextLine();
            if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
                System.out.println("Insira o novo valor da potencia que o veiculo precisa para poder levantar voo:");
                hoverpower = LER.nextDouble();
            }
        }

        System.out.println(CONFIRMACAO);
        LER.nextLine();
        String confirmacao = LER.nextLine();

        if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {
            try {
                if (veiculo.getDescricao().equalsIgnoreCase(DRONE)) {
                    sc.updateDrone(veiculo.getId(), hoverpower);
                }
                sc.updateVeiculo(veiculo);
                System.out.println("Veículo atualizado com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            menu();
        } else {
            menu();
        }
    }

    public void registarEstafeta() throws ClassNotFoundException, SQLException, ParseException {
        RegistarEstafetaUI regEstUI = new RegistarEstafetaUI();
        regEstUI.registaEstafeta();
        menu();
    }

    public void atualizarEstafeta() throws ClassNotFoundException, SQLException, ParseException {
        AtualizarEstafetaUI atEstUI = new AtualizarEstafetaUI();
        atEstUI.atualizarEstafeta();
        menu();
    }

    public void adicionarItem() throws ClassNotFoundException, SQLException, ParseException {
        InserirItensStockUI itStoUI = new InserirItensStockUI();
        itStoUI.registaProduto();
        menu();
    }

    public void atualizarItem() throws ClassNotFoundException, SQLException, ParseException {
        AtualizarItensStockUI atStoUI = new AtualizarItensStockUI();
        atStoUI.atualizarProduto();
        menu();
    }

    public void registarFarmacia() throws ClassNotFoundException, SQLException, ParseException {
        RegistarFarmaciaUI regFarUI = new RegistarFarmaciaUI();
        regFarUI.registaFarmacia();
        menu();
    }

    public void adicionarParque() throws ClassNotFoundException, SQLException, ParseException {
        AdicionarParqueUI adcParkUI = new AdicionarParqueUI();
        adcParkUI.adicionaParque();
        menu();
    }
}
