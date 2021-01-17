package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.InserirEstacionamentosController;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Estacionamento;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;

public class InserirEstacionamentosUI {

    public static final Scanner LER = new Scanner(System.in);

    InserirEstacionamentosController controller;

    /**
     * Criação do controlador responsável por inserir estacionamentos
     */
    public InserirEstacionamentosUI() {
        this.controller = new InserirEstacionamentosController(new EstacionamentosDB(), new ParqueDB(), new FarmaciaDB());
    }

    /**
     * Interface responsável por inserir estacionamentos
     */
    public void inserirEstacionamento() {
        List<Estacionamento> lestac = new ArrayList<>();
        System.out.println("--Registo de Novo Estacionamento--");
        System.out.println("Lista de Farmácias:");
        List<Farmacia> lf = controller.getListaFarmacias();
        for (Farmacia farm : lf) {
            System.out.println(farm);
        }
        System.out.println("Introduza o NIF do farmácia:");
        int nif = LER.nextInt();
        Farmacia farmacia = controller.getFarmaciaByNIF(nif);
        List<Parque> lstParques = controller.getListaParquesByFarmaciaNif(farmacia.getNIF());
        for (Parque p : lstParques) {
            System.out.println(p);
        }
        System.out.println("Introduza o id do parque para o qual pretende adicionar os estacionamentos:");
        int idParque = LER.nextInt();
        List<Estacionamento> lstEstacs = controller.getListaEstacionamentosByFarmaciaNifParqueId(nif, idParque);
        if (lstEstacs == null) {
            int numMax = controller.getNumMaxByFarmaciaNifParqueId(nif, idParque);
            for (int i = 0; i < numMax; i++) {
                System.out.println("Introduza a disponibilidade do carregador do estacionamento " + i);
                System.out.println("Indisponível - 0");
                System.out.println("Disponível - 1");
                int carregador = LER.nextInt();
                while (carregador != 0 && carregador != 1) {
                    System.out.println("Número inválido, introduzia novamente!");
                    carregador = LER.nextInt();
                }
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
                controller.registaEstacionamentos(lestac);
                System.out.println("\n\nEstacionamentos registados com sucesso!");
            } else {
                System.out.println("\n\nRegisto de Estacionamentos cancelado!");
            }

        } else {
            System.out.println("Registo de Estacionamentos cancelado!");
            System.out.println("O parque selecionado já tem estacionamentos registados!");
            for (Estacionamento e : lstEstacs) {
                System.out.println(e);
            }
        }
    }
}
