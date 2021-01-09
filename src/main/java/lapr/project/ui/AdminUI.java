/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.ScooterController;
import lapr.project.data.ScooterDataHandler;

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
     * Interface that allows the addition of a scooter into the system
     */
    public static void addScooter()  {

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
            idestado = 2;
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

        System.out.println("Estado:\t" + estado
                + "\nCapacidade de Bateria:\t" + percentagemBateria
                + "\nPeso:\t" + peso
                + "\nPeso máximo:\t" + pesoMaximo
                + "\nPotencia:\t" + potencia);

        System.out.println("\nConfirme a informação relativa à scooter(S/N)");
        LER.nextLine();
        String confirmacao = LER.nextLine();

        if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {

            ScooterController sc = new ScooterController(new ScooterDataHandler());
            try {
                sc.addScooter(percentagemBateria, peso, pesoMaximo, potencia, idestado);
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("\n\nScooter adicionada com sucesso'");

//            loop();
//        } else {
//            loop();
        }
    }

}
