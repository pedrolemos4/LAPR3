package lapr.project.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import lapr.project.controller.VeiculoController;
import lapr.project.data.CaminhoDB;
import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.DataHandler;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.data.VeiculoDB;

public class LerFicheiro extends DataHandler{

    private final FarmaciaDB fdb;
    private final ParqueDB pdb;
    private final EstacionamentosDB edb;
    private final CartaoDB cdb;
    private final EnderecoDB endb;
    private final UtilizadorDB udb;
    private final ClienteDB cldb;
    private final EstafetaDB esdb;
    private final VeiculoController vctrl;
    private final CaminhoDB pathdb;

    public LerFicheiro() {
        this.fdb = new FarmaciaDB();
        this.pdb = new ParqueDB();
        this.edb = new EstacionamentosDB();
        this.cdb = new CartaoDB();
        this.endb = new EnderecoDB();
        this.udb = new UtilizadorDB();
        this.cldb = new ClienteDB();
        this.esdb = new EstafetaDB();
        this.vctrl = new VeiculoController(new VeiculoDB());
        this.pathdb = new CaminhoDB();
    }

    public void read(String nameFile) throws ParseException, SQLException {
        System.out.println("1");        
        try {
            try ( Scanner in = new Scanner(new File(nameFile))) {
                System.out.println("2");
                while (in.hasNextLine()) {
                    System.out.println("3");
                    String[] items = in.nextLine().split(";");
                    System.out.println("Items: "+items[0]);
                    switch (nameFile) {
                        case "docs/Dados_de_Leitura/farmacias.csv":
                            fdb.addFarmacia(Integer.parseInt(items[0]), items[1], items[2]);
                            System.out.println("Entre Far");
                            break;
                        case "docs/Dados_de_Leitura/parques.csv":
                            System.out.println("Parques");
                            pdb.addParque(Integer.parseInt(items[0]), Integer.parseInt(items[1]), items[2], Integer.parseInt(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/estacionamentos.csv":
                            System.out.println("Estacionamentos");
                            edb.addEstacionamento(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Integer.parseInt(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/cartoes.csv":
                            cdb.addCartao(Integer.parseInt(items[0]), items[1], Integer.parseInt(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/enderecos.csv":
                            endb.addEndereco(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/utilizadores.csv":
                            udb.addUtilizador(Integer.parseInt(items[0]), items[1], items[2], Integer.parseInt(items[3]), items[4]);
                            break;
                        case "docs/Dados_de_Leitura/clientes.csv":
                            cldb.addCliente(Integer.parseInt(items[0]), Double.parseDouble(items[1]), items[2], Integer.parseInt(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/estafetas.csv":
                            esdb.addEstafeta(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Double.parseDouble(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/veiculos.csv":
                            //   vctrl.addVeiculo(items[0], items[1], Integer.parseInt(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]), Double.parseDouble(items[5]), Double.parseDouble(items[6]), Double.parseDouble(items[7]), Integer.parseInt(items[8]));
                            break;
                        case "docs/Dados_de_Leitura/caminhos.csv":
                            pathdb.addCaminho(items[0], items[1], Double.parseDouble(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
