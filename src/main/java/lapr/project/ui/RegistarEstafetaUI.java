package lapr.project.ui;

import lapr.project.controller.RegistarEstafetaController;
import lapr.project.data.EstafetaDB;
import lapr.project.model.*;

import java.util.List;
import java.util.Scanner;

public class RegistarEstafetaUI {

    public static final Scanner LER = new Scanner(System.in);

    RegistarEstafetaController controller;

    public RegistarEstafetaUI() {
        this.controller = new RegistarEstafetaController(new EstafetaDB());
    }

    public void registaEstafeta() {
        System.out.println("Lista de Estafetas: ");
        List<Estafeta> le = controller.getListaEstafetas();

        for(Estafeta est : le){
            System.out.println(est);
        }

        System.out.println("--Registo de Novo Estafeta--");

        System.out.println("Introduza os dados relativos ao novo estafeta");
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

        Estafeta est = controller.novoEstafeta(nif,nome,email,peso,nss,pwd);

        System.out.println("--Estafeta Criado--");
        System.out.println(est.getNIF());
        System.out.println(est.getNome());
        System.out.println(est.getEmail());
        System.out.println(est.getPesoEstafeta());
        System.out.println(est.getNumeroSegurancaSocial());
        System.out.println(est.getPassword());
        System.out.println("Deseja registar o estafeta criado? (S/N)");
        String confirm = LER.next();

        if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
            controller.registaEstafeta(est);
            System.out.println("\n\nEstafeta registado com sucesso!");
        }else{
            System.out.println("\n\nRegisto do estafeta cancelado!");
        }
    }
}
