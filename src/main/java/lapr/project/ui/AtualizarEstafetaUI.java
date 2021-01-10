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

    private void atualizarEstafeta() {
        System.out.println("Lista de Estafetas:");
        List<Estafeta> le = controller.getListaEstafetas();

        for(Estafeta est : le){
            System.out.println(est);
        }

        System.out.println("Insira o email do estafeta a atualizar:");
        String e_mail = LER.nextLine();

        Estafeta est = controller.getEstafetaByEmail(e_mail);

        if (!controller.getListaEstafetas().contains(est)){
            System.out.println("O estafeta não existe!");
        } else {
            System.out.println("--Atualização do Estafeta--");
            System.out.println("Introduza os novos dados relativos ao estafeta");
            System.out.println("NIF:");
            int nif = LER.nextInt();
            System.out.println("Nome:");
            String nome = LER.nextLine();
            System.out.println("Email:");
            String email = LER.nextLine();
            System.out.println("Peso:");
            double peso = LER.nextDouble();
            System.out.println("Número de Segurança Social:");
            int nss = LER.nextInt();
            System.out.println("Password:");
            String pwd = LER.nextLine();

            Estafeta new_est = new Estafeta(nif,nome,email,peso,nss,pwd,1);

            System.out.println("--Novos Dados do Estafeta--");
            System.out.println(new_est.getNIF());
            System.out.println(new_est.getNome());
            System.out.println(new_est.getEmail());
            System.out.println(new_est.getPesoEstafeta());
            System.out.println(new_est.getNumeroSegurancaSocial());
            System.out.println(new_est.getPassword());
            System.out.println("Deseja atualizar os dados do estafeta? (S/N)");
            String confirm = LER.next();

            if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
                est.setNIF(nif);
                est.setNome(nome);
                est.setEmail(email);
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
