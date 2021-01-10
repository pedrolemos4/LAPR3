package lapr.project.ui;

import java.util.List;
import java.util.Scanner;
import lapr.project.controller.InserirEstacionamentosController;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Estacionamento;

public class InserirEstacionamentosUI {

    public static final Scanner LER = new Scanner(System.in);

    InserirEstacionamentosController controller;

    public InserirEstacionamentosUI() {
        this.controller = new InserirEstacionamentosController(new EstacionamentosDB(), new ParqueDB());
    }

    private void RegistaEstacionamento() {
        System.out.println("--Registo de Novo Estacionamento--");
        System.out.println("Introduza o NIF do parque/farmácia:");
        int nif = LER.nextInt();
        int numMax = controller.getNumMaxParqueByNIF(nif);
        for (int i = 0; i < numMax; i++) {
            System.out.println("Introduza a disponibilidade do carregador do estacionamento (0 ou 1):");
            int carregador = LER.nextInt();
            Estacionamento estac = controller.novoEstacionamento(i + 1, carregador, nif);
            System.out.println("--Estacionamento Criado--");
            System.out.println(estac.getNumeroLote());
            System.out.println(estac.getCarregador());
            System.out.println(estac.getNIF());
            System.out.println("Deseja registar o Estacionamento criado? (S/N)");
            String confirm = LER.next();

            if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
                controller.registaEstacionamento(estac);
                System.out.println("\n\nEstacionamento registado com sucesso!");
            } else {
                System.out.println("\n\nRegisto do Estacionamento cancelado!");
                break;
            }
        }
    }
}
