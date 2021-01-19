package lapr.project.ui;

import java.sql.SQLException;
import java.text.ParseException;
import lapr.project.controller.RegistarEstafetaController;
import lapr.project.data.EstafetaDB;
import lapr.project.model.*;

import java.util.List;
import java.util.Scanner;
import lapr.project.data.UtilizadorDB;
import lapr.project.login.UserSession;
import static lapr.project.ui.AdminUI.loginUI;

public class RegistarEstafetaUI {

    public static final Scanner LER = new Scanner(System.in);

    RegistarEstafetaController controller;

    public RegistarEstafetaUI() {
        this.controller = new RegistarEstafetaController(new EstafetaDB(), new UtilizadorDB());
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
        LER.nextLine();
        String nome = LER.nextLine();
        System.out.println("Email:");
        String email = LER.nextLine();
        System.out.println("Peso:");
        double peso = LER.nextDouble();
        System.out.println("Número de Segurança Social:");
        int nss = LER.nextInt();
        System.out.println("Password:");
        LER.nextLine();
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
    
    /**
     * Menu da interface cliente
     */
    public static void textoMenuEstafeta() {
        System.out.println("\nRIDE SHARING - Menu Estafeta\n------------"
                + "\n 1 - Realizar entrega"/*
                + "\n 2 - Find parks near me"
                + "\n 3 - Check rentals history"
                + "\n 4 - Park vehicle"
                + "\n 5 - Pay monthly invoice"
                + "\n 6 - Check Spots in a Park for my loaned vehicle"
                + "\n 7 - Check Spots in a Park for Scooters"
                + "\n 8 - Check Spots in a Park for Bicycles"*/
                + "\n 0 - Exit"
                + "\n Escolha uma opção válida.");
    }

    /**
     * Menu do cliente
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void menuEstafeta() throws ClassNotFoundException, SQLException, ParseException {
        String opt;
        do {
            textoMenuEstafeta();
            opt = LER.nextLine();
            switch (opt) {
                case "1":
                    realizarEntrega();
                    break;
                case "0":
                    UserSession.getInstance().doLogout();
                    loginUI.menu();
                    break;
                default:
                    System.out.println("");
                    System.out.println("Insira uma opção válida");
            }
        } while (!opt.equals("0"));
    }
    
    public void realizarEntrega() throws SQLException, ClassNotFoundException, ParseException {
        RegistarEntregaUI regEntUI = new RegistarEntregaUI();
        regEntUI.introduzEntrega();
        menuEstafeta();
    }
}
