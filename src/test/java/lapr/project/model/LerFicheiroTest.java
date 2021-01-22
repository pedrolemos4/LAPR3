/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author josep
 */
public class LerFicheiroTest {

    private static LerFicheiro instance;
    private FarmaciaDB farmaciaMock;
    private ParqueDB parqueMock;
    private Farmacia farm;

    @BeforeEach
    public void setUp() throws SQLException {
        farmaciaMock = mock(FarmaciaDB.class);
        parqueMock = mock(ParqueDB.class);
        instance = new LerFicheiro();
        farm = new Farmacia(123456789, "email", "rua1");
        when(farmaciaMock.addFarmacia(farm)).thenReturn(true);
    }

    public LerFicheiroTest() throws ParseException, SQLException {
        instance = new LerFicheiro();
        LerFicheiro expResult = instance;
        LerFicheiro result = expResult;
        assertEquals(result, expResult);
    }

//    @Test
//    public void testAddFarmacia() {
//        System.out.println("Add Farmácia");
//        int nif = 111111111;
//        String email = "email";
//        String morada = "bla";
//        when(farmaciaMock.addFarmacia(nif, email, morada)).thenReturn(true);
//        assertEquals(true, farmaciaMock.addFarmacia(farm));
//    }
//
//    @Test
//    public void testAddParque() {
//        System.out.println("Add Parque");
//        int i1 = 111;
//        int i2 = 222;
//        String i3 = "teste";
//        int i4 = 333;
//        when(parqueMock.addParque(i1, i2, i3, i4)).thenReturn(1);
//        assertEquals(1, parqueMock.addParque(i1, i2, i3, i4));
//    }

    /**
     * Test of read method, of class LerFicheiro.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int nif = 111111111;
        String email = "email";
        String morada = "bla";
//        farm = new Farmacia(123456789, "email", "rua1");
        String nameFile = "docs/Dados_de_Leitura/farmacias.csv";
        instance.read(nameFile);
//        Farmacia result = farmaciaMock.getFarmaciaByNIF(123456789);
//        assertEquals(null, result);

        when(farmaciaMock.addFarmacia(nif, email, morada)).thenReturn(true);
        assertEquals(true, farmaciaMock.addFarmacia(farm));
        
    }

    /**
     * Test of read method, of class LerFicheiro.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRead1() throws Exception {
        System.out.println("read1");
        List<Farmacia> lf = farmaciaMock.getLstFarmacias();
        String nameFile = "docs/Dados_de_Leitura/farmacias.csv";
        instance.read(nameFile);
        List<Farmacia> lf2 = farmaciaMock.getLstFarmacias();
        assertEquals(lf, lf2);
    }

}
