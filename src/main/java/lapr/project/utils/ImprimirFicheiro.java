package lapr.project.utils;

import java.io.FileWriter;
import java.io.IOException;

public class ImprimirFicheiro {

    private static final String OUTPUT = "output_caminho";

    public ImprimirFicheiro() {
        // dummy constructor
    }

    public static void imprimirFicheiro(String caminho) {
        try (FileWriter writer = new FileWriter(OUTPUT, true)) {
            writer.write(caminho + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
