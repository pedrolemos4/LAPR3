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

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

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
            Properties properties =
                    new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        EstacionamentoController cont = new EstacionamentoController(new EmailDB(),new EstacionamentosDB(),new VeiculoDB(),new ParqueDB());
        cont.checkParkings("src/main/java/lapr/project/parking/estacionamento");

        LoginUI login = new LoginUI();
        login.menu();

    }
}

