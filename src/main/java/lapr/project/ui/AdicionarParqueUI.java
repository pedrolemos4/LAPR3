/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RegistarFarmaciaController;
import lapr.project.data.EnderecoDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;

/**
 *
 * @author josep
 */
public class AdicionarParqueUI {

    public static final Scanner LER = new Scanner(System.in);

    RegistarFarmaciaController controller;

    /**
     * Criação do controlador responsável por registar farmácias
     */
    public AdicionarParqueUI() {
        this.controller = new RegistarFarmaciaController(new FarmaciaDB(), new ParqueDB(), new EnderecoDB());
    }

    /**
     * Interface responsável por adicionar parques
     */
    public void adicionaParque() {
        System.out.println("Lista de Farmácias:");
        List<Farmacia> lf = controller.getListaFarmacias();

        for (Farmacia farm : lf) {
            System.out.println(farm);
            System.out.println(controller.getListaParquesByFarmaciaNif(farm.getNIF()));
        }
        System.out.println("--Adição de um novo parque--");
        System.out.println("Introduza o nif da farmácia para o qual deseja adicionar um parque:");
        System.out.println("NIF:");
        int nif = LER.nextInt();
        if (controller.getFarmaciaByNIF(nif) != null) {
            System.out.println("Introduza os dados do parque:");
            System.out.println("Limite máximo do parque:");
            int numMax = LER.nextInt();
            System.out.println("Qual o tipo do parque, drone ou scooter?");
            LER.nextLine();
            String tipo = LER.nextLine();
            while (!tipo.equalsIgnoreCase("drone") && !tipo.equalsIgnoreCase("scooter")) {
                System.out.println("Tipo de parque inválido, introduzia novamente!");
                tipo = LER.nextLine();
            }
            System.out.println("Qual a capacidade máxima de carregamento do parque?");
            int maxCap = LER.nextInt();
            Parque park = controller.novoParque(nif, numMax, tipo.toLowerCase(), maxCap);
            System.out.println("--Parque Criado--");
            System.out.println(park.getNumeroMaximo());
            System.out.println(park.getTipo());
            System.out.println(park.getMaxCap());

            System.out.println("Deseja adicionar o parque criado à farmácia selecionada? (S/N)");
            String confirm = LER.next();
            if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
                controller.registaParque(park);
                System.out.println("\n\nParque adicionado com sucesso!");
            } else {
                System.out.println("\n\nAdição do Parque cancelada!");
            }
        } else {
            System.out.println("\n\nAdição do Parque não realizada!");
            System.out.println("\n\nNIF da farmácia introduzido inválido!");
        }
    }
}
