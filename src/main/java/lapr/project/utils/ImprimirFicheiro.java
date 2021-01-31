package lapr.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ImprimirFicheiro {

    private static final String OUTPUT = "output_caminho";

    public static void imprimirFicheiro(String caminho) {
        // try {
        // File myObj = new File(OUTPUT);
        // if (myObj.createNewFile()) {

        // FileWriter out = new PrintWriter(OUTPUT);

        // out.write(caminho);
        // out.println();
        // out.close();
        // } else {
        // PrintWriter out = new PrintWriter(OUTPUT);

        // out.write(caminho);
        // out.println();
        // out.close();
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        try (FileWriter writer = new FileWriter(OUTPUT, true)) {
            writer.write(caminho + "\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
