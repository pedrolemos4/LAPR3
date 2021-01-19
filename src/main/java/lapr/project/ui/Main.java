package lapr.project.ui;

import lapr.project.controller.EstacionamentoController;
import lapr.project.data.EmailDB;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.ParqueDB;
import lapr.project.data.VeiculoDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Logger;
import lapr.project.model.LerFicheiro;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");
    private static final LerFicheiro readFile = new LerFicheiro();
    private static final String FARMACIAS = "docs/Dados_de_Leitura/farmacias.csv";
    private static final String PARQUES = "docs/Dados_de_Leitura/parques.csv";
    private static final String ESTACIONAMENTOS = "docs/Dados_de_Leitura/estacionamentos.csv";
    private static final String CARTOES = "docs/Dados_de_Leitura/cartoes.csv";
    private static final String ENDERECOS = "docs/Dados_de_Leitura/enderecos.csv";
    private static final String UTILIZADORES = "docs/Dados_de_Leitura/utilizadores.csv";
    private static final String CLIENTES = "docs/Dados_de_Leitura/clientes.csv";
    private static final String ESTAFETAS = "docs/Dados_de_Leitura/estafetas.csv";
    private static final String VEICULOS = "docs/Dados_de_Leitura/veiculos.csv";

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
        readFile.read(FARMACIAS);
        readFile.read(PARQUES);
        readFile.read(ESTACIONAMENTOS);
        readFile.read(CARTOES);
        readFile.read(ENDERECOS);
        readFile.read(UTILIZADORES);
        readFile.read(CLIENTES);
        readFile.read(ESTAFETAS);
        readFile.read(VEICULOS);
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

        EstacionamentoController cont = new EstacionamentoController(new EmailDB(), new EstacionamentosDB(), new VeiculoDB(), new ParqueDB());
        cont.checkParkings("src/main/java/lapr/project/parking/estacionamento");

        LoginUI login = new LoginUI();
        login.menu();

    }
}
