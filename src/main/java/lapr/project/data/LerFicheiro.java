package lapr.project.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import lapr.project.controller.VeiculoController;
import lapr.project.model.Drone;
import lapr.project.model.Scooter;

public class LerFicheiro extends DataHandler {

    private final FarmaciaDB fdb;
    private final ParqueDB pdb;
    private final EstacionamentosDB edb;
    private final CartaoDB cdb;
    private final EnderecoDB endb;
    private final UtilizadorDB udb;
    private final ClienteDB cldb;
    private final EstafetaDB esdb;
    private final VeiculoController vctrl;
    private final VeiculoDB vdb;
    private final CaminhoDB pathdb;
    private final ProdutosDB proddb;

    public LerFicheiro(FarmaciaDB far, ParqueDB par, EstacionamentosDB est, CartaoDB car,
            EnderecoDB end, UtilizadorDB uti, ClienteDB cli, EstafetaDB estDB, CaminhoDB cam,
            VeiculoDB vei, ProdutosDB prod) {
        this.fdb = far;
        this.pdb = par;
        this.edb = est;
        this.cdb = car;
        this.endb = end;
        this.udb = uti;
        this.cldb = cli;
        this.esdb = estDB;
        this.vctrl = new VeiculoController(vei);
        this.pathdb = cam;
        this.vdb = vei;
        this.proddb = prod;
    }

    public void read(String nameFile) throws ParseException, SQLException {
        try {
            try ( Scanner in = new Scanner(new File(nameFile))) {
                while (in.hasNextLine()) {
                    String[] items = in.nextLine().split(";");
                    System.out.println("Items: " + items[0]);
                    switch (nameFile) {
                        case "docs/Dados_de_Leitura/farmacias.csv":
                            addFarmacia(Integer.parseInt(items[0]), items[1], items[2]);
                            break;
                        case "docs/Dados_de_Leitura/parques.csv":
                            pdb.addParque(Integer.parseInt(items[0]), Integer.parseInt(items[1]), items[2], Integer.parseInt(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/estacionamentos.csv":
                            edb.addEstacionamento(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Integer.parseInt(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/cartoes.csv":
                            cdb.addCartao(Long.parseLong(items[0]), items[1], Integer.parseInt(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/enderecos.csv":
                            endb.addEndereco(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/utilizadores.csv":
                            udb.addUtilizador(Integer.parseInt(items[0]), items[1], items[2], Integer.parseInt(items[3]), items[4]);
                            break;
                        case "docs/Dados_de_Leitura/clientes.csv":
                            cldb.addCliente(Integer.parseInt(items[0]), Double.parseDouble(items[1]), items[2], Long.parseLong(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/estafetas.csv":
                            esdb.addEstafeta(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Double.parseDouble(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/veiculos.csv":
                            vctrl.addVeiculo(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]), Double.parseDouble(items[5]), Integer.parseInt(items[6]));
                            break;
                        case "docs/Dados_de_Leitura/caminhos.csv":
                            pathdb.addCaminho(items[0], items[1], Double.parseDouble(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]));
                            break;
                        case "docs/Dados_de_Leitura/drones.csv":
                            Drone drone = new Drone(Integer.parseInt(items[0]), Double.parseDouble(items[1]));
                            vdb.addDrone(drone);
                            break;
                        case "docs/Dados_de_Leitura/scooters.csv":
                            Scooter scooter = new Scooter(Integer.parseInt(items[0]), Double.parseDouble(items[1]));
                            vdb.addScooter(scooter);
                            break;
                        case "docs/Dados_de_Leitura/produtos.csv":
                            proddb.addProduto(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/stock.csv":
                            proddb.addProdutoStock(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Integer.parseInt(items[2]));
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean addFarmacia(int i, String s, String r) {
        return fdb.addFarmacia(i, s, r);
    }
}
