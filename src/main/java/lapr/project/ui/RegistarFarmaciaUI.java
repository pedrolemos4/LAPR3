package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RegistarFarmaciaController;
import lapr.project.data.EnderecoDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;

public class RegistarFarmaciaUI {

    public static final Scanner LER = new Scanner(System.in);

    RegistarFarmaciaController controller;

    /**
     * Criação do controlador responsável por registar farmácias
     */
    public RegistarFarmaciaUI() {
        this.controller = new RegistarFarmaciaController(new FarmaciaDB(), new ParqueDB(), new EnderecoDB());
    }

    /**
     * Interface responsável por registar farmácias
     */
    public void registaFarmacia() {
        List<Parque> lparks = new ArrayList<>();
        System.out.println("Lista de Farmácias: ");
        List<Farmacia> lf = controller.getListaFarmacias();

        for (Farmacia farm : lf) {
            System.out.println(farm);
            System.out.println(controller.getListaParquesByFarmaciaNif(farm.getNIF()));
        }
        System.out.println("--Registo de Nova Farmácia--");
        System.out.println("Introduza os dados relativos à nova Farmácia");
        System.out.println("NIF:");
        int nif = LER.nextInt();
        if (controller.getFarmaciaByNIF(nif) == null) {
            System.out.println("Email da farmácia:");
            LER.nextLine();
            String email = LER.nextLine();
            System.out.println("Morada da farmácia:");
            String morada = LER.nextLine();
            System.out.println("Latitude:");
            double lat = LER.nextDouble();
            System.out.println("Longitude:");
            double lon = LER.nextDouble();
            System.out.println("Altitude:");
            double alt = LER.nextDouble();
            System.out.println("Quantos parques pretende adicionar à farmácia:");
            int parkNum = LER.nextInt();
            for (int i = 0; i < parkNum; i++) {
                System.out.println("Limite máximo do parque:");
                int numMax = LER.nextInt();
                System.out.println("Qual o tipo do parque, drones ou scooters?");
                LER.nextLine();
                String tipo = LER.nextLine();
                while (!tipo.equalsIgnoreCase("drones") && !tipo.equalsIgnoreCase("scooters")) {
                    System.out.println("Tipo de parque inválido, introduzia novamente!");
                    tipo = LER.nextLine();
                }
                Parque park = controller.novoParque(nif, numMax, tipo.toLowerCase());
                lparks.add(park);
            }
            Endereco end = controller.novoEndereco(morada, lat, lon, alt);
            Farmacia farm = controller.novaFarmacia(nif, email, morada);

            System.out.println("--Farmácia Criada--");
            System.out.println(farm.getNIF());
            System.out.println(farm.getEmail());
            System.out.println(farm.getMorada());
            System.out.println(end.getLatitude());
            System.out.println(end.getLongitude());
            System.out.println(end.getAltitude());
            System.out.println("--Parques Criados--");
            for (Parque p : lparks) {
                System.out.println(p.getNumeroMaximo());
                System.out.println(p.getTipo());
            }
            System.out.println("Deseja registar a farmácia criada e os parques criados? (S/N)");
            String confirm = LER.next();
            if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
                controller.registaEndereco(end);
                controller.registaFarmacia(farm);
                controller.registaParques(lparks);
                System.out.println("\n\nFarmácia e parques registados com sucesso!");
            } else {
                System.out.println("\n\nRegisto da Farmácia cancelado!");
            }
        } else {
            System.out.println("\n\nRegisto da Farmácia não realizado!");
            System.out.println("\n\nJá existe uma farmácia registada com NIF igual ao introduzido!");
        }
    }
}
