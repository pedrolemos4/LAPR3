package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author beatr
 */
public class VeiculoTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    void VeiculoEmptyConstructorTest() {
        Veiculo instance = new Veiculo();

        assertEquals(instance, instance);
    }

    @Test
    void VeiculoConstructorTest() {
        Veiculo instance = new Veiculo("descricao","scooter", 85, 50, 30, 40, 50, 1);

        assertEquals(instance, instance);
    }

    @Test
    void VeiculoCompleteConstructorTest() {
        Veiculo instance = new Veiculo(1, "descricao", "scooter",85, 50, 30, 40, 50, 1);

        assertEquals(instance, instance);
    }

    @Test
    public void testGetDescricao() {
        System.out.println("getDescrição");
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        String expResult = "descricao";
        String result = instance.getDescricao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPercentagemBateria method, of class Veiculo.
     */
    @Test
    public void testGetPercentagemBateria() {
        System.out.println("getPercentagemBateria");
        Veiculo instance = new Veiculo("descricao", "drone",85, 50, 30, 40, 50, 1);
        double expResult = 85;
        double result = instance.getPercentagemBateria();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPesoMaximo method, of class Veiculo.
     */
    @Test
    public void testGetPesoMaximo() {
        System.out.println("getPesoMaximo");
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 2);
        double expResult = 50.0;
        double result = instance.getPesoMaximo();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getPesoVeiculo method, of class Veiculo.
     */
    @Test
    public void testGetPesoVeiculo() {
        System.out.println("getPesoVeiculo");
        Veiculo instance = new Veiculo("descricao","veiculo", 85, 50, 30, 40, 50, 2);
        double expResult = 30.0;
        double result = instance.getPesoVeiculo();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getPotencia method, of class Veiculo.
     */
    @Test
    public void testGetPotencia() {
        System.out.println("getPotencia");
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        double expResult = 40.0;
        double result = instance.getPotencia();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getAreaFrontal method, of class Veiculo.
     */
    @Test
    public void testGetAreaFrontal() {
        System.out.println("getAreaFrontal");
        Veiculo instance = new Veiculo("descricao", "drone",85, 50, 30, 40, 50, 1);
        double expResult = 50.0;
        double result = instance.getAreaFrontal();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAreaFrontal method, of class Veiculo.
     */
    @Test
    public void testSetAreaFrontal(){
        System.out.println("setAreaFrontal");
        Veiculo instance = new Veiculo("descricao", "drone",85, 50, 30, 40, 50, 1);
        double expResult = 30.0;
        instance.setAreaFrontal(expResult);
        assertEquals(expResult,instance.getAreaFrontal(),0.0);
    }
    /**
     * Test of setPercentagemBateria method, of class Veiculo.
     */
    @Test
    public void testSetPercentagemBateria() {
        System.out.println("setPercentagemBateria");
        double percentagemBateria = 75;
        Veiculo instance = new Veiculo("descricao", "drone",85, 50, 30, 40, 50, 1);
        instance.setPercentagemBateria(percentagemBateria);
        assertEquals(percentagemBateria, instance.getPercentagemBateria(), 0.0);
    }

    /**
     * Test of setPesoMaximo method, of class Veiculo.
     */
    @Test
    public void testSetPesoMaximo() {
        System.out.println("setPesoMaximo");
        double pesoMaximo = 36.0;
        Veiculo instance = new Veiculo("descricao","scooter", 85, 50, 30, 40, 50, 1);
        instance.setPesoMaximo(pesoMaximo);
        assertEquals(pesoMaximo, instance.getPesoMaximo(), 0.0);
    }

    /**
     * Test of setPesoVeiculo method, of class Veiculo.
     */
    @Test
    public void testSetPesoVeiculo() {
        System.out.println("setPesoVeiculo");
        double pesoVeiculo = 20.0;
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        instance.setPesoVeiculo(pesoVeiculo);
        assertEquals(pesoVeiculo, instance.getPesoVeiculo(), 0.0);
    }

    /**
     * Test of setPotencia method, of class Veiculo.
     */
    @Test
    public void testSetPotencia() {
        System.out.println("setPotencia");
        double potencia = 29.0;
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        instance.setPotencia(potencia);
        assertEquals(potencia, instance.getPotencia(), 0.0);
    }

    /**
     * Test of getEstadoVeiculo method, of class Veiculo.
     */
    @Test
    public void testGetEstadoVeiculo() {
        System.out.println("getEstadoVeiculo");
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        int expResult = 1;
        int result = instance.getEstadoVeiculo().getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEstadoVeiculo method, of class Veiculo.
     */
    @Test
    public void testSetEstadoVeiculo() {
        System.out.println("setEstadoVeiculo");
        int estado = 2;
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        instance.setEstadoVeiculo(estado);
        assertEquals(estado, instance.getEstadoVeiculo().getId());
    }

    /**
     * Test of getId method, of class Veiculo.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        int expResult = 1;
        instance.setId(1);
        assertEquals(expResult, instance.getId());
    }

    /**
     * Test of setId method, of class Veiculo.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        instance.setId(1);
        assertEquals(id, instance.getId());
    }

    @Test
    public void testSetDescricao(){
        System.out.println("setDescrição");
        String descricao = "teste";
        Veiculo instance = new Veiculo("descricao",  "drone",85,50, 30, 40, 50, 1);
        instance.setDescricao(descricao);
        assertEquals(descricao,instance.getDescricao());
    }

    /**
     * Test of toString method, of class Veiculo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Veiculo instance = new Veiculo("descricao", "drone",85, 50, 30, 40, 50, 1);
        String expResult = "Veiculoid" + 0 + "\nDescrição=" + "descricao" + "\nPercentagem de Bateria=" 
                + 85.0 + "\nPeso máximo=" + 50.0 + "\nPeso do Veiculo="
                + 30.0 + "\nPotência=" + 40.0 + "\nÁrea frontal=" + 50.0 +
                "\nEstado=" + "Disponível";
        assertEquals(expResult, instance.toString());
    }

    /**
     * Test of getTipo method, of class Veiculo.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
         Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        String expResult = "drone";
        instance.setTipo(expResult);
        String result = instance.getTipo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTipo method, of class Veiculo.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        String tipo = "scooter";
        Veiculo instance = new Veiculo("descricao","drone", 85, 50, 30, 40, 50, 1);
        instance.setTipo(tipo);

    }
}
