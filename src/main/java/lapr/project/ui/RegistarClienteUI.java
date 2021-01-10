package lapr.project.ui;

import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RegistarClienteController;
import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Utilizador;

public class RegistarClienteUI {

    public static final Scanner LER = new Scanner(System.in);

    RegistarClienteController controller;

    public RegistarClienteUI() {
        this.controller = new RegistarClienteController(new ClienteDB(), new UtilizadorDB(), new EnderecoDB(), new CartaoDB());
    }

    public boolean registaCliente() {
        boolean aux = false;
        System.out.println("Lista de Clientes: ");
        List<Cliente> lc = controller.getListaClientes();

        for (Cliente cl : lc) {
            System.out.println(cl);
        }

        System.out.println("--Registo de Novo Cliente--");

        System.out.println("Introduza os dados relativos ao novo cliente");
        System.out.println("NIF:");
        int nif = LER.nextInt();
        System.out.println("Nome:");
        String nome = LER.nextLine();
        System.out.println("Email:");
        String email = LER.nextLine();
        System.out.println("Número de Segurança Social:");
        int nss = LER.nextInt();
        System.out.println("Endereço residencial:");
        String morada = LER.nextLine();
        System.out.println("Latitude:");
        double lat = LER.nextDouble();
        System.out.println("Longitude:");
        double lon = LER.nextDouble();
        System.out.println("Altitude:");
        double alt = LER.nextDouble();
        System.out.println("Número de Cartão de Cidadão:");
        int ncc = LER.nextInt();
        System.out.println("Data de Validade do CC:");
        String dvcc = LER.nextLine();
        System.out.println("CCV:");
        int ccv = LER.nextInt();
        System.out.println("Password:");
        String pwd = LER.nextLine();
        Cliente cl = controller.novoCliente(nif, nome, email, nss, morada, ncc, pwd);
        Endereco end = controller.novoEndereco(morada, lat, lon, alt);
        Cartao cc = controller.novoCartao(ncc, dvcc, ccv);

        System.out.println("--Cliente Criado--");
        System.out.println(cl.getNIF());
        System.out.println(cl.getNome());
        System.out.println(cl.getEmail());
        System.out.println(cl.getNumeroSegurancaSocial());
        System.out.println(end.getMorada());
        System.out.println(end.getLatitude());
        System.out.println(end.getLongitude());
        System.out.println(end.getAltitude());
        System.out.println(cc.getNumeroCartao());
        System.out.println(cc.getDataDeValidade());
        System.out.println(cc.getCCV());
        System.out.println(cl.getPassword());
        System.out.println("Deseja registar o cliente criado? (S/N)");
        String confirm = LER.next();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
            controller.registaCliente(cl);
            controller.registaCartao(cc);
            controller.registaEndereco(end);
            System.out.println("\n\nCliente registado com sucesso!");
            aux = true;
        } else {
            System.out.println("\n\nRegisto do Cliente cancelado!");
        }
        return aux;
    }
    
    public static void textoMenuCliente() {
        System.out.println("\nRIDE SHARING - Menu Cliente\n------------"
                + "\n 1 - Realizar encomenda"/*
                + "\n 2 - Find parks near me"
                + "\n 3 - Check rentals history"
                + "\n 4 - Park vehicle"
                + "\n 5 - Pay monthly invoice"
                + "\n 6 - Check Spots in a Park for my loaned vehicle"
                + "\n 7 - Check Spots in a Park for Scooters"
                + "\n 8 - Check Spots in a Park for Bicycles"*/
                + "\n 0 - Exit"
                + "\n Choose one of the options above.");
    }

    public void menuCliente() throws ClassNotFoundException, SQLException {
        String opt;
        do {
            textoMenuCliente();
            opt = LER.nextLine();

            switch (opt) {
                case "1":
                    realizarEncomenda();
                    break;
                /*case "2":
                    getNearestParks();
                    break;
                case "3":
                    getUserHistorical();
                    break;
                case "4":
                    parkVehicleAtAGivenPark();
                    break;
                case "5":
                    System.out.println("Not available at the moment.");
                    break;
                case "6":
                    checkParkFeeSpotsForMyLoanedVehicle();
                    break;
                case "7":
                    checkParkFreeScooterSpots();
                    break;
                case "8":
                    checkParkFreeBicycleSpots();
                    break;*/
                case "0":
                    exit(0);
                    break;
                default:
                    System.out.println("Insira uma opção válida");
            }
        } while (!opt.equals("0"));// || isNumeric(opt));
    }

    public void realizarEncomenda() throws SQLException, ClassNotFoundException{
        RealizarEncomendaUI regEncUI = new RealizarEncomendaUI();
        regEncUI.introduzEncomenda();
        menuCliente();
    }
}
