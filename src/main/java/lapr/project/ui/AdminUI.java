/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.ScooterController;
import lapr.project.data.ScooterDB;

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
    public static void showMenu() {
        System.out.println("\nRIDE SHARING - Menu Administrador\n--------------------------------"
                //+ "\n 1 - Add park"
                //+ "\n 2 - Add point of interest"
                //+ "\n 3 - Remove park"
                //+ "\n 4 - Update park"
                + "\n 1 - Adicionar scooter"
                + "\n 2 - Remover scooter"
                + "\n 3 - Atualizar scooter"
                //+ "\n 8 - Report about unlocked vehicles"
                //+ "\n 9 - Add path"
                + "\n 0 - Exit"
                + "\n Choose one of the options above.");
    }

    public void loop(){
            String opt;
        do {
            showMenu();
            opt = LER.nextLine();

            switch (opt) {
               /* case "1":
                    addPark();
                    break;
                case "2":
                    addPOI();
                    break;
                case "3":
                    removePark();
                    break;
                case "4":
                    updatePark();
                    break;*/
                case "1":
                    addScooter();
                    break;
                case "2":
                    removeScooter();
                    break;
               /* case "7":
                    updateVehicle();
                    break;
                case "8":
                    getUnlockedvehicles();
                    break;
                case "9":
                    addPath();
                    break;*/
                case "0":
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!opt.equals("0"));// || isNumeric(opt));
    }

    
    /**
     * Interface that allows the addition of a scooter into the system
     */
    public static void addScooter() {

        System.out.println("Insira uma descrição única da scooter:");
        LER.nextLine();
        String descricao = LER.nextLine();
        System.out.println("Insira a capacidade máxima da bateria:");
        int percentagemBateria = LER.nextInt();

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
        
        /*System.out.println("Insert the scooter's description:");
        String description = LER.nextLine();*/
        System.out.println("Insira o peso total da scooter:");
        double peso = LER.nextDouble();

        System.out.println("Insira o peso máximo que a scooter suporta");
        double pesoMaximo = LER.nextDouble();

        System.out.println("Insira a potência da scooter:");
        double potencia = LER.nextDouble();
        /*System.out.println("Insert the scooter's aerodynamic coefficient:");
        double aerodynamicCoefficient = LER.nextDouble();

        System.out.println("Insert the scooter's frontal area:");
        double frontalArea = LER.nextDouble();
        
        System.out.println("Insert the scooter's motor:");
        int motor = LER.nextInt();*/

        System.out.println("Descrição:\t" + descricao
                +"Estado:\t" + estado
                + "\nCapacidade de Bateria:\t" + percentagemBateria
                + "\nPeso:\t" + peso
                + "\nPeso máximo:\t" + pesoMaximo
                + "\nPotencia:\t" + potencia);

        System.out.println("\nConfirme a informação relativa à scooter(S/N)");
        LER.nextLine();
        String confirmacao = LER.nextLine();

        if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {

            ScooterController sc = new ScooterController(new ScooterDB());
            try {
                sc.addScooter(descricao,percentagemBateria, peso, pesoMaximo, potencia, idestado);
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("\n\nScooter adicionada com sucesso'");

//            loop();
//        } else {
//            loop();
        }
    }

    public static void removeScooter() {
        System.out.println("Insira o id da scooter a remover:");
        int idScooter = LER.nextInt();
        ScooterController sc = new ScooterController(new ScooterDB());

        System.out.println("Confirma a remoção da scooter com o id " + idScooter + "?(S/N)");
        LER.nextLine();
        String resposta = LER.nextLine();

        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N")) {
            try {
                sc.removeScooter(idScooter);
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Scooter removida com sucesso.");
        }
    }

}
