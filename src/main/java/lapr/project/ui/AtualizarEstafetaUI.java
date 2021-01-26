package lapr.project.ui;

import lapr.project.controller.AtualizarEstafetaController;
import lapr.project.data.EstafetaDB;
import lapr.project.model.Estafeta;

import java.util.List;
import java.util.Scanner;

public class AtualizarEstafetaUI {
    public static final Scanner LER = new Scanner(System.in);

    AtualizarEstafetaController controller;

    public AtualizarEstafetaUI() {
        this.controller = new AtualizarEstafetaController(new EstafetaDB());
    }

    public void atualizarEstafeta() {
        System.out.println("Lista de Estafetas:");
        List<Estafeta> le = controller.getListaEstafetas();

        for(Estafeta est : le){
            System.out.println(est);
        }

        System.out.println("Insira o nif do estafeta a atualizar:");
        int nif = LER.nextInt();

        Estafeta est = controller.getEstafetaByNIF(nif);

        if (est == null){
            System.out.println("O estafeta não existe!");
        } else {
            System.out.println("--Atualização do Estafeta--");
            System.out.println("Introduza os novos dados relativos ao estafeta");
            System.out.println("Nome:");
            LER.nextLine();
            String nome = LER.nextLine();
            System.out.println("Email:");
            String email1 = LER.nextLine();
            System.out.println("Peso:");
            double peso = LER.nextDouble();
            System.out.println("Número de Segurança Social:");
            int nss = LER.nextInt();
            System.out.println("Password:");
            LER.nextLine();
            String pwd = LER.nextLine();

            Estafeta newEst = new Estafeta(nif,nome,email1,peso,nss,pwd,1);

            System.out.println("--Novos Dados do Estafeta--");
            System.out.println(newEst.getNIF());
            System.out.println(newEst.getNome());
            System.out.println(newEst.getEmail());
            System.out.println(newEst.getPesoEstafeta());
            System.out.println(newEst.getNumeroSegurancaSocial());
            System.out.println(newEst.getPassword());
            System.out.println("Deseja atualizar os dados do estafeta? (S/N)");
            String confirm = LER.next();

            if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
                est.setNome(nome);
                est.setEmail(email1);
                est.setPesoEstafeta(peso);
                est.setNumeroSegurancaSocial(nss);
                est.setPassword(pwd);
                controller.atualizarEstafeta(est);
                System.out.println("\n\nEstafeta atualizado com sucesso!");
            } else {
                System.out.println("\n\nAtualização do estafeta cancelada!");
            }
        }
    }
}
