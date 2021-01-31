package lapr.project.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class ImprimirFicheiroTest {

    @Test
    void imprimirFicheiro() throws FileNotFoundException {
        String expected = "Ficheiro de teste";
        ImprimirFicheiro.imprimirFicheiro(expected);
        File teste = new File("output_caminho");
        Scanner scan = new Scanner(teste);
        String actual = scan.nextLine();
        scan.close();
        assertEquals(expected,actual);
    }

    @AfterEach
    void deleteTest(){
        File teste = new File("output_caminho");
        teste.delete();
    }
}