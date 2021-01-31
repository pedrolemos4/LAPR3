package lapr.project.ui;

import static java.lang.System.exit;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RegistarClienteController;
import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.login.UserSession;
import lapr.project.model.Cartao;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;

public class RegistarClienteUI {

    public static final Scanner LER = new Scanner(System.in);
    public static final LoginUI loginUI = new LoginUI();

    RegistarClienteController controller;

    /**
     * Criação do controlador responsável por registar clientes
     */
    public RegistarClienteUI() {
        this.controller = new RegistarClienteController(new ClienteDB(), new UtilizadorDB(), new EnderecoDB(), new CartaoDB());
    }

    /**
     * Interface responsável por registar clientes
     *
     * @return
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public boolean registaCliente() throws ParseException, ClassNotFoundException, SQLException {
        boolean aux = false;

        System.out.println("--Registo de Novo Cliente--");

        System.out.println("Introduza os dados relativos ao novo cliente");
        System.out.println("NIF:");
        int nif = LER.nextInt();
        System.out.println("Nome:");
        LER.nextLine();
        String nome = LER.nextLine();
        System.out.println("Email:");
        String email = LER.nextLine();
        System.out.println("Número de Segurança Social:");
        int nss = LER.nextInt();
        System.out.println("Endereço residencial:");
        LER.nextLine();
        String morada = LER.nextLine();
        System.out.println("Latitude:");
        double lat = LER.nextDouble();
        System.out.println("Longitude:");
        double lon = LER.nextDouble();
        System.out.println("Altitude:");
        double alt = LER.nextDouble();
        System.out.println("Número de Cartão de Crédito:");
        long ncc = LER.nextLong();
        System.out.println("Data de Validade do CC:");
        LER.nextLine();
        String dvcc = LER.nextLine();
        System.out.println("CCV:");
        int ccv = LER.nextInt();
        System.out.println("Password:");
        LER.nextLine();
        String pwd = LER.nextLine();
        int creditos = 0;
        Cliente cl = controller.novoCliente(nif, nome, email, nss, creditos, morada, ncc, pwd);
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
            controller.registaEndereco(end);
            controller.registaCartao(cc);
            controller.registaCliente(cl);
            System.out.println("\n\nCliente registado com sucesso!");
            menuCliente();
            aux = true;
        } else {
            System.out.println("\n\nRegisto do Cliente cancelado!");
            loginUI.menu();
        }
        return aux;
    }

    /**
     * Menu da interface cliente
     */
    public void textoMenuCliente() {
        System.out.println("\nRIDE SHARING - Menu Cliente\n-------------------"
                + "\n 1 - Realizar encomenda"
                + "\n 0 - Exit"
                + "\n Escolha uma das opcões apresentadas\n-------------------");
    }

    /**
     * Menu do cliente
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void menuCliente() throws ClassNotFoundException, SQLException, ParseException {
        String opt;
        do {
            textoMenuCliente();
            opt = LER.nextLine();
            switch (opt) {
                case "1":
                    realizarEncomenda();
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

    public void realizarEncomenda() throws SQLException, ClassNotFoundException, ParseException {
        RealizarEncomendaUI regEncUI = new RealizarEncomendaUI();
        regEncUI.introduzEncomenda();
        menuCliente();
    }
}
