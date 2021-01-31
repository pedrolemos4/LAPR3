package lapr.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ImprimirFicheiro {

    private static final String OUTPUT = "output_caminho";

    public ImprimirFicheiro() {
        // dummy constructor
    }

    public static void imprimirFicheiro(String caminho) {

        try (FileWriter writer = new FileWriter(OUTPUT, true)) {
            writer.write(caminho + "\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
