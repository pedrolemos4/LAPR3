/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.text.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author josep
 */
public class LerFicheiroTest {

    private static LerFicheiro instance;

    public LerFicheiroTest() throws ParseException, SQLException {
        instance = new LerFicheiro();
        instance.read("docs/Dados_de_Leitura/farmacias.csv");
    }

    /**
     * Test of read method, of class LerFicheiro.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        String nameFile = "docs/Dados_de_Leitura/farmacias.csv";
        instance.read(nameFile);
    }

}
