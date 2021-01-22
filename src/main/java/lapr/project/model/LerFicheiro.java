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
import lapr.project.data.ProdutosDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.data.VeiculoDB;

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
                            addParque(Integer.parseInt(items[0]), Integer.parseInt(items[1]), items[2], Integer.parseInt(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/estacionamentos.csv":
                            addEstacionamento(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Integer.parseInt(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/cartoes.csv":
                            addCartao(Long.parseLong(items[0]), items[1], Integer.parseInt(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/enderecos.csv":
                            addEndereco(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/utilizadores.csv":
                            addUtilizador(Integer.parseInt(items[0]), items[1], items[2], Integer.parseInt(items[3]), items[4]);
                            break;
                        case "docs/Dados_de_Leitura/clientes.csv":
                            addCliente(Integer.parseInt(items[0]), Double.parseDouble(items[1]), items[2], Long.parseLong(items[3]));
                            break;
                        case "docs/Dados_de_Leitura/estafetas.csv":
                            addEstafeta(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Double.parseDouble(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/veiculos.csv":
                            addVeiculo(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]), Double.parseDouble(items[5]), Integer.parseInt(items[6]));
                            break;
                        case "docs/Dados_de_Leitura/caminhos.csv":
                            addCaminho(items[0], items[1], Double.parseDouble(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]));
                            break;
                        case "docs/Dados_de_Leitura/drones.csv":
                            addDrone(Integer.parseInt(items[0]), Double.parseDouble(items[1]));
                            break;
                        case "docs/Dados_de_Leitura/scooters.csv":
                            addScooter(Integer.parseInt(items[0]), Double.parseDouble(items[1]));
                            break;
                        case "docs/Dados_de_Leitura/produtos.csv":
                            addProduto(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/stock.csv":
                            addProdutoStock(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Integer.parseInt(items[2]));
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
        return (fdb.addFarmacia(i, s, r) ? (true) : (false));
    }

    public int addParque(int i, int i1, String s, int i2) {
        return pdb.addParque(i, i1, s, i2);
    }

    public boolean addEstacionamento(int parseInt, int parseInt0, int parseInt1) {
        return (edb.addEstacionamento(parseInt, parseInt0, parseInt1) ? (true) : (false));
    }

    public boolean addCartao(long parseLong, String item, int parseInt) throws ParseException {
        return (cdb.addCartao(parseLong, item, parseInt) ? (true) : (false));
    }

    public boolean addEndereco(String item, double parseDouble, double parseDouble0, double parseDouble1) {
        return (endb.addEndereco(item, parseDouble, parseDouble0, parseDouble1) ? (true) : (false));
    }

    public boolean addUtilizador(int parseInt, String item, String item0, int parseInt0, String item1) {
        return (udb.addUtilizador(parseInt, item, item0, parseInt0, item1) ? (true) : (false));
    }

    public boolean addCliente(int parseInt, double parseDouble, String item, long parseLong) {
        return (cldb.addCliente(parseInt, parseDouble, item, parseLong) ? (true) : (false));
    }

    public boolean addEstafeta(int parseInt, int parseInt0, double parseDouble) {
        return (esdb.addEstafeta(parseInt, parseInt0, parseDouble) ? (true) : (false));
    }

    public int addVeiculo(String item, double parseDouble, double parseDouble0, double parseDouble1, double parseDouble2, double parseDouble3, int parseInt) throws SQLException {
        Veiculo vei = new Veiculo(item, parseDouble, parseDouble0, parseDouble1, parseDouble2, parseDouble3, parseInt);
        return vdb.addVeiculo(vei);
    }

    public boolean addCaminho(String item, String item0, double parseDouble, double parseDouble0, double parseDouble1) {
        return (pathdb.addCaminho(item, item0, parseDouble, parseDouble0, parseDouble1) ? (true) : (false));
    }

    public boolean addDrone(int parseInt, double parseDouble) throws SQLException {
        Drone drone = new Drone(parseInt, parseDouble);

        return (vdb.addDrone(drone) ? (true) : (false));
    }

    public boolean addScooter(int parseInt, double parseDouble) throws SQLException {
        Scooter scooter = new Scooter(parseInt, parseDouble);
        return (vdb.addScooter(scooter) ? (true) : (false));
    }

    public int addProduto(String item, double parseDouble, double parseDouble0) {
        return proddb.addProduto(item, parseDouble, parseDouble0);
    }

    public boolean addProdutoStock(int parseInt, int parseInt0, int parseInt1) {
        return (proddb.addProdutoStock(parseInt,parseInt0,parseInt1) ? (true) : (false));
    }
}
