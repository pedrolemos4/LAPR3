package lapr.project.data;

import lapr.project.controller.VeiculoController;
import lapr.project.model.Drone;
import lapr.project.model.Estacionamento;
import lapr.project.model.Scooter;
import lapr.project.model.Veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final EncomendaDB encdb;

    public LerFicheiro(FarmaciaDB far, ParqueDB par, EstacionamentosDB est, CartaoDB car,
                       EnderecoDB end, UtilizadorDB uti, ClienteDB cli, EstafetaDB estDB, CaminhoDB cam,
                       VeiculoDB vei, ProdutosDB prod, EncomendaDB encDB) {
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
        this.encdb = encDB;
    }

    public void read(String nameFile) throws ParseException, SQLException {
        try {
            try (Scanner in = new Scanner(new File(nameFile))) {
                while (in.hasNextLine()) {
                    String[] items = in.nextLine().split(";");
                    System.out.println("Ficheiro: "+nameFile);
                    switch (nameFile) {
                        case "docs/Dados_de_Leitura/farmacias.csv":
                            fdb.addFarmacia(Integer.parseInt(items[0]), items[1], items[2]);
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
                            vctrl.addVeiculo(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]), Double.parseDouble(items[5]), Integer.parseInt(items[6]), Double.parseDouble(items[7]));
                            break;
                        case "docs/Dados_de_Leitura/caminhos.csv":
                            pathdb.addCaminho(items[0], items[1], Double.parseDouble(items[2]), Double.parseDouble(items[3]), Double.parseDouble(items[4]));
                            break;
                        case "docs/Dados_de_Leitura/drones.csv":
                            Drone drone = new Drone(Integer.parseInt(items[0]), Double.parseDouble(items[1]));
                            vdb.addDrone(drone);
                            break;
                        case "docs/Dados_de_Leitura/scooters.csv":
                            Scooter scooter = new Scooter(Integer.parseInt(items[0]));
                            vdb.addScooter(scooter);
                            break;
                        case "docs/Dados_de_Leitura/produtos.csv":
                            proddb.addProduto(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/stock.csv":
                            proddb.addProdutoStock(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Integer.parseInt(items[2]));
                            break;
                        case "docs/Dados_de_Leitura/encomendas.csv":
                            encdb.addEncomenda(Integer.parseInt(items[0]), Integer.parseInt(items[1]), items[2], Double.parseDouble(items[3]), Double.parseDouble(items[4]), Double.parseDouble(items[5]), Integer.parseInt(items[6]));
                            break;
                        case "docs/Dados_de_Leitura/estacionamentoveiculo.csv":
                            Estacionamento e = new Estacionamento();
                            Veiculo v = new Veiculo();
                            e.setNumeroLote(Integer.parseInt(items[2]));
                            v.setId(Integer.parseInt(items[1]));
                            edb.addEstacionamentoVeiculo(e, v, Integer.parseInt(items[0]));
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

    public boolean baseDadosCheia() {
        String query = "SELECT COUNT(*) FROM caminho";
        boolean bool = true;
        try (Statement stm = getConnection().createStatement()) {
            try (ResultSet rSet = stm.executeQuery(query)) {
                if (rSet.next()) {
                    int contador = rSet.getInt(1);
                    if (contador == 0) {
                        bool = false;
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(LerFicheiro.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return bool;
    }
}
