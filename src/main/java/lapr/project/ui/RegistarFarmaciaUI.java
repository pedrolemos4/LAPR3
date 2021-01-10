package lapr.project.ui;

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

    public RegistarFarmaciaUI() {
        this.controller = new RegistarFarmaciaController(new FarmaciaDB(), new ParqueDB(), new EnderecoDB());
    }

    private void registaFarmacia() {
        System.out.println("Lista de Farmácias: ");
        List<Farmacia> lf = controller.getListaFarmacias();

        for (Farmacia farm : lf) {
            System.out.println(farm);
        }

        System.out.println("--Registo de Nova Farmácia--");
        System.out.println("Introduza os dados relativos à nova Farmácia");
        System.out.println("NIF:");
        int nif = LER.nextInt();
        System.out.println("Endereço do parque:");
        String morada = LER.nextLine();
        System.out.println("Latitude:");
        double lat = LER.nextDouble();
        System.out.println("Longitude:");
        double lon = LER.nextDouble();
        System.out.println("Altitude:");
        double alt = LER.nextDouble();
        System.out.println("Limite máximo de scooters do parque:");
        int numMax = LER.nextInt();
        Farmacia farm = controller.novaFarmacia(nif);
        Endereco end = controller.novoEndereco(morada, lat, lon, alt);
        Parque park = controller.novoParque(nif, morada, numMax);

        System.out.println("--Farmácia Criada--");
        System.out.println(farm.getNIF());
        System.out.println(end.getMorada());
        System.out.println(end.getLatitude());
        System.out.println(end.getLongitude());
        System.out.println(end.getAltitude());
        System.out.println(park.getNumeroMaximo());
        System.out.println("Deseja registar a farmácia criada? (S/N)");
        String confirm = LER.next();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
            controller.registaFarmacia(farm);
            controller.registaEndereco(end);
            controller.registaParque(park);
            System.out.println("\n\nFarmácia registada com sucesso!");
        } else {
            System.out.println("\n\nRegisto da Farmácia cancelado!");
        }
    }
}
