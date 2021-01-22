/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import lapr.project.controller.VeiculoController;
import lapr.project.data.CaminhoDB;
import lapr.project.data.CartaoDB;
import lapr.project.data.ClienteDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.EstafetaDB;

import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.data.ProdutosDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.data.VeiculoDB;
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
    private EstacionamentosDB edb;
    private CartaoDB cdb;
    private EnderecoDB endb;
    private UtilizadorDB udb;
    private ClienteDB cldb;
    private EstafetaDB esdb;
    private VeiculoDB vdb;
    private CaminhoDB pathdb;
    private ProdutosDB proddb;

    private Farmacia farm;

    @BeforeEach
    public void setUp() throws SQLException {
        farmaciaMock = mock(FarmaciaDB.class);
        parqueMock = mock(ParqueDB.class);
        edb = mock(EstacionamentosDB.class);
        cdb = mock(CartaoDB.class);
        endb = mock(EnderecoDB.class);
        udb = mock(UtilizadorDB.class);
        cldb = mock(ClienteDB.class);
        esdb = mock(EstafetaDB.class);
        vdb = mock(VeiculoDB.class);
        pathdb = mock(CaminhoDB.class);
        proddb = mock(ProdutosDB.class);
        instance = new LerFicheiro(farmaciaMock, parqueMock, edb, cdb, endb, udb, cldb, esdb,
                pathdb, vdb, proddb);
        farm = new Farmacia(123456789, "email", "rua1");
        when(farmaciaMock.addFarmacia(farm)).thenReturn(true);
        String item = "item";
        double parseDouble = 10.0;
        double parseDouble0 = 20.0;
        double parseDouble1 = 30.0;
        double parseDouble2 = 40.0;
        double parseDouble3 = 50.0;
        int parseInt = 1;
        Veiculo vei = new Veiculo(item, parseDouble, parseDouble0, parseDouble1, parseDouble2, parseDouble3, parseInt);
        when(vdb.addVeiculo(vei)).thenReturn(1);
    }

    public LerFicheiroTest() throws ParseException, SQLException {
        instance = new LerFicheiro(farmaciaMock, parqueMock, edb, cdb, endb, udb, cldb, esdb,
                pathdb, vdb, proddb);
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
    public void testAddFarmacia() throws Exception {
        System.out.println("addFarmacia");
        int nif = 111111111;
        String email = "email";
        String morada = "bla";
        when(farmaciaMock.addFarmacia(nif, email, morada)).thenReturn(true);
        assertEquals(true, instance.addFarmacia(nif, email, morada));
    }

    @Test
    public void addParque() {
        System.out.println("Add Parque");
        int i1 = 111;
        int i2 = 222;
        String i3 = "teste";
        int i4 = 333;
        when(parqueMock.addParque(i1, i2, i3, i4)).thenReturn(1);
        assertEquals(1, instance.addParque(i1, i2, i3, i4));
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

    /**
     * Test of addEstacionamento method, of class LerFicheiro.
     */
    @Test
    public void testAddEstacionamento() {
        System.out.println("addEstacionamento");
        int parseInt = 1;
        int parseInt0 = 2;
        int parseInt1 = 3;
        when(edb.addEstacionamento(parseInt, parseInt0, parseInt1)).thenReturn(true);
        assertEquals(true, instance.addEstacionamento(parseInt, parseInt0, parseInt1));
    }

    /**
     * Test of addCartao method, of class LerFicheiro.
     */
    @Test
    public void testAddCartao() throws Exception {
        System.out.println("addCartao");
        long parseLong = 555;
        String item = "item";
        int parseInt = 1;
        when(cdb.addCartao(parseLong, item, parseInt)).thenReturn(true);
        assertEquals(true, instance.addCartao(parseLong, item, parseInt));
    }

    /**
     * Test of addEndereco method, of class LerFicheiro.
     */
    @Test
    public void testAddEndereco() {
        System.out.println("addEndereco");
        String item = "item";
        double parseDouble = 30.0;
        double parseDouble0 = 10.0;
        double parseDouble1 = 20.0;
        when(endb.addEndereco(item, parseDouble, parseDouble, parseDouble)).thenReturn(true);
        assertEquals(true, instance.addEndereco(item, parseDouble, parseDouble, parseDouble));
    }

    /**
     * Test of addUtilizador method, of class LerFicheiro.
     */
    @Test
    public void testAddUtilizador() {
        System.out.println("addUtilizador");
        int parseInt = 1;
        String item = "item";
        String item0 = "item1";
        int parseInt0 = 2;
        String item1 = "item1";
        when(udb.addUtilizador(parseInt, item1, item1, parseInt0, item1)).thenReturn(true);
        assertEquals(false, instance.addUtilizador(parseInt, item, item0, parseInt0, item1));
    }

    /**
     * Test of addCliente method, of class LerFicheiro.
     */
    @Test
    public void testAddCliente() {
        System.out.println("addCliente");
        int parseInt = 1;
        double parseDouble = 10.0;
        String item = "item";
        long parseLong = 33;
        when(cldb.addCliente(parseInt, parseDouble, item, parseLong)).thenReturn(true);
        assertEquals(true, instance.addCliente(parseInt, parseDouble, item, parseLong));
    }

    /**
     * Test of addEstafeta method, of class LerFicheiro.
     */
    @Test
    public void testAddEstafeta() {
        System.out.println("addEstafeta");
        int parseInt = 1;
        int parseInt0 = 2;
        double parseDouble = 20.0;
        when(esdb.addEstafeta(parseInt, parseInt0, parseDouble)).thenReturn(true);
        assertEquals(true, instance.addEstafeta(parseInt, parseInt0, parseDouble));
    }

    /**
     * Test of addVeiculo method, of class LerFicheiro.
     */
    @Test
    public void testAddVeiculo() throws Exception {
        System.out.println("addVeiculo");
        String item = "item";
        double parseDouble = 10.0;
        double parseDouble0 = 20.0;
        double parseDouble1 = 30.0;
        double parseDouble2 = 40.0;
        double parseDouble3 = 50.0;
        int parseInt = 1;
        Veiculo vei = new Veiculo(item, parseDouble, parseDouble0, parseDouble1, parseDouble2, parseDouble3, parseInt);
        when(vdb.addVeiculo(vei)).thenReturn(1);
        instance.addVeiculo(item, parseDouble, parseDouble0, parseDouble1, parseDouble2, parseDouble3, parseInt);
        vei.setId(1);
        assertEquals(1,vei.getId());
    }

    /**
     * Test of addCaminho method, of class LerFicheiro.
     */
    @Test
    public void testAddCaminho() {
        System.out.println("addCaminho");
        String item = "item";
        String item0 = "item0";
        double parseDouble = 20.0;
        double parseDouble0 = 30.0;
        double parseDouble1 = 40.0;
        when(pathdb.addCaminho(item, item0, parseDouble, parseDouble0, parseDouble1)).thenReturn(true);
        assertEquals(true, instance.addCaminho(item, item0, parseDouble, parseDouble0, parseDouble1));
    }

    /**
     * Test of addDrone method, of class LerFicheiro.
     */
    @Test
    public void testAddDrone() throws Exception {
        System.out.println("addDrone");
        int parseInt = 1;
        double parseDouble = 10.0;
        Drone drone = new Drone(parseInt, parseDouble);
        when(vdb.addDrone(drone)).thenReturn(true);
        assertEquals(false, instance.addDrone(parseInt, parseDouble));
    }

    /**
     * Test of addScooter method, of class LerFicheiro.
     */
    @Test
    public void testAddScooter() throws Exception {
        System.out.println("addScooter");
        int parseInt = 10;
        double parseDouble = 20.0;
        Scooter scooter = new Scooter(parseInt, parseDouble);
        when(vdb.addScooter(scooter)).thenReturn(true);
        assertEquals(false, instance.addScooter(parseInt, parseDouble));
    }

    /**
     * Test of addProduto method, of class LerFicheiro.
     */
    @Test
    public void testAddProduto() {
        System.out.println("addProduto");
        String item = "item";
        double parseDouble = 10.0;
        double parseDouble0 = 20.0;
        Produto prod = new Produto(item,parseDouble,parseDouble0);
        when(proddb.addProduto(item, parseDouble, parseDouble)).thenReturn(1);
        prod.setId(1);
        assertEquals(1, prod.getId());
    }

    /**
     * Test of addProdutoStock method, of class LerFicheiro.
     */
    @Test
    public void testAddProdutoStock() {
        System.out.println("addProdutoStock");
        int parseInt = 10;
        int parseInt0 = 20;
        int parseInt1 = 30;
        when(proddb.addProdutoStock(parseInt, parseInt0, parseInt1)).thenReturn(true);
        assertEquals(true, instance.addProdutoStock(parseInt, parseInt0, parseInt1));
    }

}
