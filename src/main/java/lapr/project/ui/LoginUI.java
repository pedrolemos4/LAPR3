/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import static java.lang.System.exit;
import java.util.Scanner;
import lapr.project.controller.RegistarClienteController;
import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.login.UserSession;
import lapr.project.model.Utilizador;

/**
 *
 * @author Tiago
 */
public class LoginUI {

    public static final Scanner LER = new Scanner(System.in);
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_ACESS = "adminpass";

    public LoginUI() {
    }

    public static void textoMenuLogin() {
        System.out.println("ISTO É RIDE SHARING MOTHERFUCKERS!!");
        System.out.println("1- Login    \n"
                + "2- Registo \n"
                + "0- Sair");
    }

    public void menu() {
        String opcao;
        do {
            textoMenuLogin();
            opcao = LER.nextLine();
            switch (opcao) {
                case "1":
                    login();
                    break;
                case "2":
                    registo();
                    break;
                case "3":
                    exit(0);
                    break;
                default:
                    System.out.println("Insira uma opção válida");
            }
        } while (!opcao.equals("0"));
    }

    public void login() {
        System.out.println("\nEmail:");
        String email = LER.nextLine();

        System.out.println("\nPassword:");
        String pwd = LER.nextLine();

        Utilizador utilizador = null;
        RegistarClienteController ctrl = new RegistarClienteController(new ClienteDB(),
                new UtilizadorDB(), new EnderecoDB(), new CartaoDB());
        if (email.equalsIgnoreCase(ADMIN_LOGIN) && pwd.equalsIgnoreCase(ADMIN_ACESS)) {
            AdminUI aui = new AdminUI();
            aui.menu();
        } else if ((utilizador = ctrl.login(email, pwd)) != null) {
            RegistarClienteUI rcui = new RegistarClienteUI();
            UserSession.getInstance().getUser();
            //rcui.registaCliente();
        } else {
            System.err.println("\nE-mail or Password are incorrect.\n");
            menu();
        }
    }

    public void registo() {
        RegistarClienteUI rcui = new RegistarClienteUI();
        boolean res = rcui.registaCliente();
        if (res == true) {
            //rcui.menuCliente();
        } else{
            menu();
        }
    }
}
