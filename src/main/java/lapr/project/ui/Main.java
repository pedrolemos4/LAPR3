package lapr.project.ui;

import lapr.project.controller.EstacionamentoController;
import lapr.project.data.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final String FARMACIAS = "docs/Dados_de_Leitura/farmacias.csv";
    private static final String PARQUES = "docs/Dados_de_Leitura/parques.csv";
    private static final String ESTACIONAMENTOS = "docs/Dados_de_Leitura/estacionamentos.csv";
    private static final String ESTACIONAMENTOVEICULO = "docs/Dados_de_Leitura/estacionamentoveiculo.csv";
    private static final String CARTOES = "docs/Dados_de_Leitura/cartoes.csv";
    private static final String ENDERECOS = "docs/Dados_de_Leitura/enderecos.csv";
    private static final String UTILIZADORES = "docs/Dados_de_Leitura/utilizadores.csv";
    private static final String CLIENTES = "docs/Dados_de_Leitura/clientes.csv";
    private static final String ESTAFETAS = "docs/Dados_de_Leitura/estafetas.csv";
    private static final String VEICULOS = "docs/Dados_de_Leitura/veiculos.csv";
    private static final String DRONES = "docs/Dados_de_Leitura/drones.csv";
    private static final String SCOOTERS = "docs/Dados_de_Leitura/scooters.csv";
    private static final String CAMINHOS = "docs/Dados_de_Leitura/caminhos.csv";
    private static final String ENCOMENDAS = "docs/Dados_de_Leitura/encomendas.csv";
    private static final String PRODUTOS = "docs/Dados_de_Leitura/produtos.csv";
    private static final String STOCK = "docs/Dados_de_Leitura/stock.csv";

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {
    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParseException {

        try {
            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        LerFicheiro readFile = new LerFicheiro(new FarmaciaDB(), new ParqueDB(),
                new EstacionamentosDB(), new CartaoDB(), new EnderecoDB(), new UtilizadorDB(),
                new ClienteDB(), new EstafetaDB(), new CaminhoDB(), new VeiculoDB(),
                new ProdutosDB(), new EncomendaDB());

        if (!readFile.baseDadosCheia()) {
            readFile.read(ENDERECOS);
            readFile.read(CARTOES);
            readFile.read(VEICULOS);
            readFile.read(DRONES);
            readFile.read(SCOOTERS);
            readFile.read(UTILIZADORES);
            readFile.read(FARMACIAS);
            readFile.read(PARQUES);
            readFile.read(ESTACIONAMENTOS);
            readFile.read(PRODUTOS);
            readFile.read(STOCK);
            readFile.read(CLIENTES);
            readFile.read(ESTAFETAS);
            readFile.read(ENCOMENDAS);
            readFile.read(ESTACIONAMENTOVEICULO);
            readFile.read(CAMINHOS);
        }

        EstacionamentoController cont = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB(), new EstafetaDB());
        cont.checkParkings("src/main/java/lapr/project/parking/estacionamento");

        LoginUI login = new LoginUI();
        login.menu();

    }
}
