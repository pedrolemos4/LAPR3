package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.InserirEstacionamentosController;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Estacionamento;

public class InserirEstacionamentosUI {

    public static final Scanner LER = new Scanner(System.in);

    InserirEstacionamentosController controller;

    /**
     * Criação do controlador responsável por inserir estacionamentos
     */
    public InserirEstacionamentosUI() {
        this.controller = new InserirEstacionamentosController(new EstacionamentosDB(), new ParqueDB());
    }

    /**
     * Interface responsável por inserir estacionamentos
     */
    public void InserirEstacionamento() {
        List<Estacionamento> lestac = new ArrayList<>();
        System.out.println("--Registo de Novo Estacionamento--");
        System.out.println("Introduza o NIF do parque/farmácia:");
        int nif = LER.nextInt();
        int numMax = controller.getNumMaxParqueByNIF(nif);
        for (int i = 0; i < numMax; i++) {
            System.out.println("Introduza a disponibilidade do carregador do estacionamento " + i);
            System.out.println("Indisponível - 0");
            System.out.println("Disponível - 1");
            int carregador = LER.nextInt();
            int numLote = i + 1;
            Estacionamento estac = controller.novoEstacionamento(numLote, carregador, nif);
            lestac.add(estac);
            System.out.println("--Estacionamento Criado--");
        }
        for (Estacionamento est : lestac) {
            System.out.println(est);
        }
        System.out.println("Deseja registar os Estacionamentos criados? (S/N)");
        String confirm = LER.next();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
            controller.registaEstacionamento(lestac);
            System.out.println("\n\nEstacionamentos registados com sucesso!");
        } else {
            System.out.println("\n\nRegisto de Estacionamentos cancelado!");
        }
    }
}
