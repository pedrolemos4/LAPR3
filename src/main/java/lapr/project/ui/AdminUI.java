/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.VeiculoController;
import lapr.project.data.VeiculoDB;
import lapr.project.model.Veiculo;

/**
 *
 * @author Tiago
 */
public class AdminUI {

    /**
     * Scanner para leitura de teclado
     */
    public static final Scanner LER = new Scanner(System.in);

    /**
     *
     */
    public void showMenu() {
        System.out.println("\nRIDE SHARING - Menu Administrador\n--------------------------------"
                //+ "\n 1 - Add park"
                //+ "\n 2 - Add point of interest"
                //+ "\n 3 - Remove park"
                //+ "\n 4 - Update park"
                + "\n 1 - Adicionar veiculo"
                + "\n 2 - Remover veiculo"
                + "\n 3 - Atualizar veiculo"
                + "\n 4 - Registar estafeta"
                + "\n 5 - Atualizar estafeta"
                + "\n 6 - Adicionar item ao stock"
                + "\n 7 - Atualizar item"
                //+ "\n 8 - Report about unlocked vehicles"
                //+ "\n 9 - Add path"
                + "\n 0 - Exit"
                + "\n Escolha uma das opções.");
    }

    public void menu() {
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
                case "0":
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!opt.equals("0"));
    }

    /**
     * Interface that allows the addition of a veiculo into the system
     */
    public void addVeiculo() {

        System.out.println("Insira uma descrição única da scooter:");
        String descricao = LER.nextLine();
        String tipo = LER.nextLine();
        System.out.println("Insira a capacidade máxima da bateria:");
        double percentagemBateria = LER.nextInt();

        System.out.println("\nA scooter vai estar disponível imediatamente após a sua criação? (S/N)");
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

        System.out.println("Insira o peso total da scooter:");
        double peso = LER.nextDouble();

        System.out.println("Insira o peso máximo que a scooter suporta:");
        double pesoMaximo = LER.nextDouble();

        System.out.println("Insira a potência da scooter:");
        double potencia = LER.nextDouble();

        System.out.println("Insira a área frontal da scooter:");
        double areaFrontal = LER.nextDouble();
        
        System.out.println("Descrição:\t" + descricao
                + "\nEstado:\t" + estado
                + "\nCapacidade de Bateria:\t" + percentagemBateria
                + "\nPeso:\t" + peso
                + "\nPeso máximo:\t" + pesoMaximo
                + "\nPotencia:\t" + potencia
                + "\nÁrea Frontal:\t" + areaFrontal);

        System.out.println("\nConfirme a informação relativa à scooter(S/N)");
        LER.nextLine();
        String confirmacao = LER.nextLine();

        if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {

            VeiculoController sc = new VeiculoController(new VeiculoDB());
            try {
                sc.addVeiculo(descricao, tipo,percentagemBateria, peso, pesoMaximo, potencia, areaFrontal, idestado);
                System.out.println("\n\nScooter adicionada com sucesso'");
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("\n\nScooter adicionada com sucesso'");

            menu();
        } else {
            menu();
        }
    }

    public void removeVeiculo() {
        System.out.println("Insira o id da scooter a remover:");
        int idVeiculo = LER.nextInt();
        VeiculoController sc = new VeiculoController(new VeiculoDB());

        System.out.println("Confirma a remoção da scooter com o id " + idVeiculo + "?(S/N)");
        LER.nextLine();
        String resposta = LER.nextLine();

        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N")) {
            try {
                sc.removeVeiculo(idVeiculo);
                System.out.println("Scooter removida com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            menu();
        } else {
            menu();
        }
    }

    public void updateVeiculo() {
        VeiculoController sc = new VeiculoController(new VeiculoDB());
        List<Veiculo> lista = sc.getListaVeiculo();

        System.out.println("Insira o id da scooter que pretende atualizar");
        for (Veiculo s : lista) {
            System.out.println();
            System.out.println(s.toString());
        }
        int id = LER.nextInt();
        Veiculo veiculo = sc.getVeiculoById(id);
        System.out.println("Pretende atualizar a descrição da scooter? (S/N)");
        LER.nextLine();
        String resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira a descrição atualizada da scooter:");
            String descricao = LER.nextLine();
            veiculo.setDescricao(descricao);
        }
        System.out.println("Pretende atualizar a capacidade máxima da bateria da scooter? (S/N)");
        resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira a nova capacidade máxima da bateria da scooter:");
            double cap = LER.nextDouble();
            veiculo.setPercentagemBateria(cap);
        }
        System.out.println("Pretende atualizar o valor do peso máximo que a scooter suporta? (S/N)");
        resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira a nova capacidade máxima da bateria da scooter:");
            double pesoMaximo = LER.nextDouble();
            veiculo.setPesoMaximo(pesoMaximo);
        }
        System.out.println("Pretende atualizar o valor do peso da scooter? (S/N)");
        resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira o peso atualizado da scooter:");
            double peso = LER.nextDouble();
            veiculo.setPesoVeiculo(peso);
        }
        System.out.println("Pretende atualizar o valor da área frontal da scooter? (S/N)");
        resposta = LER.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Insira o valor da área frontal atualizado da scooter:");
            double area = LER.nextDouble();
            veiculo.setPesoVeiculo(area);
        }

        System.out.println("\nA scooter vai estar disponível imediatamente após a sua atualização? (S/N)");
        resposta = LER.nextLine();
        int idestado;
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {
            idestado = 1;
            veiculo.setEstadoVeiculo(idestado);
        } else {
            idestado = 0;
            veiculo.setEstadoVeiculo(idestado);
        }

        System.out.println("Descrição:\t" + veiculo.getDescricao()
                + "Estado:\t" + veiculo.getEstadoVeiculo().getDesignacao()
                + "\nCapacidade de Bateria:\t" + veiculo.getPercentagemBateria()
                + "\nPeso:\t" + veiculo.getPesoVeiculo()
                + "\nPeso máximo:\t" + veiculo.getPesoMaximo()
                + "\nPotencia:\t" + veiculo.getPotencia()
                + "\nÁrea Frontal:\t" + veiculo.getAreaFrontal());

        System.out.println("\nConfirme a informação relativa à scooter(S/N)");
        LER.nextLine();
        String confirmacao = LER.nextLine();

        if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {
            try {
                sc.updateVeiculo(veiculo);
                System.out.println("Scooter atualizada com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            menu();
        } else {
            menu();
        }
    }

    public void registarEstafeta() {
        RegistarEstafetaUI regEstUI = new RegistarEstafetaUI();
        regEstUI.registaEstafeta();
        menu();
    }
    
    public void atualizarEstafeta(){
        AtualizarEstafetaUI atEstUI = new AtualizarEstafetaUI();
        atEstUI.atualizarEstafeta();
        menu();
    }
    
    public void adicionarItem(){
        InserirItensStockUI itStoUI = new InserirItensStockUI();
        itStoUI.registaProduto();
        menu();
    }
    
    public void atualizarItem(){
        AtualizarItensStockUI atStoUI = new AtualizarItensStockUI();
        atStoUI.atualizarEstafeta();
        menu();
    }
}
