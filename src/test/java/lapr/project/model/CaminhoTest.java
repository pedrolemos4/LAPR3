package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *
 * @author beatr
 */
public class CaminhoTest {
    
    public CaminhoTest() {
    }

    /**
     * Test of getEnd1 method, of class Caminho.
     */
    @Test
    public void testGetEnd1() {
        System.out.println("getEnd1");
        Endereco end1 = new Endereco("morada1", 15, 48, 48);
        Endereco end2 = new Endereco("morada2", 13, 56, 84);
        Caminho instance = new Caminho(end1, end2, 12, 45, 45,"Terrestre");
        Endereco expResult = end1;
        Endereco result = instance.getEnd1();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEnd1 method, of class Caminho.
     */
    @Test
    public void testSetEnd1() {
        System.out.println("setEnd1");
        Endereco end1 = new Endereco("morada", 321, 32, 54);
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        instance.setEnd1(end1);

    }

    /**
     * Test of getEnd2 method, of class Caminho.
     */
    @Test
    public void testGetEnd2() {
        System.out.println("getEnd2");
        Endereco end1 = new Endereco("morada1", 15, 48, 48);
        Endereco end2 = new Endereco("morada2", 13, 56, 84);
        Caminho instance = new Caminho(end1, end2, 12, 45, 45,"Terrestre");
        Endereco expResult = end2;
        Endereco result = instance.getEnd2();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setEnd2 method, of class Caminho.
     */
    @Test
    public void testSetEnd2() {
        System.out.println("setEnd2");
        Endereco end2 = new Endereco("morada", 48, 12, 36);
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        instance.setEnd2(end2);

    }

    /**
     * Test of getRoadResistanceCoefficient method, of class Caminho.
     */
    @Test
    public void testGetRoadResistanceCoefficient() {
        System.out.println("getRoadResistanceCoefficient");
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        double expResult = 12.0;
        double result = instance.getRoadResistanceCoefficient();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setRoadResistanceCoefficient method, of class Caminho.
     */
    @Test
    public void testSetRoadResistanceCoefficient() {
        System.out.println("setRoadResistanceCoefficient");
        double roadResistanceCoefficient = 45.0;
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        instance.setRoadResistanceCoefficient(roadResistanceCoefficient);

    }

    /**
     * Test of getVelocidadeVento method, of class Caminho.
     */
    @Test
    public void testGetVelocidadeVento() {
        System.out.println("getVelocidadeVento");
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        double expResult = 45.0;
        double result = instance.getVelocidadeVento();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setVelocidadeVento method, of class Caminho.
     */
    @Test
    public void testSetVelocidadeVento() {
        System.out.println("setVelocidadeVento");
        double velocidadeVento = 12.0;
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        instance.setVelocidadeVento(velocidadeVento);

    }

    /**
     * Test of getDirecaoVento method, of class Caminho.
     */
    @Test
    public void testGetDirecaoVento() {
        System.out.println("getDirecaoVento");
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        double expResult = 45.0;
        double result = instance.getDirecaoVento();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setDirecaoVento method, of class Caminho.
     */
    @Test
    public void testSetDirecaoVento() {
        System.out.println("setDirecaoVento");
        double direcaoVento = 180.0;
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        instance.setDirecaoVento(direcaoVento);

    }

    @Test
    public void testGetTipo(){
        System.out.println("testGetTipo");
        double direcaoVento = 180.0;
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        assertEquals("Terrestre", instance.getTipo());
    }

    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        instance.setTipo("Aerea");
    }

    /**
     * Test of toString method, of class Caminho.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Caminho instance = new Caminho(new Endereco("morada1", 15, 48, 48), new Endereco("morada2", 13, 56, 84), 12, 45, 45,"Terrestre");
        String expResult = "Caminho{end1=Endereco{morada=morada1, latitude=15.0, longitude=48.0, altitude=48.0}, end2=Endereco{morada=morada2, latitude=13.0, longitude=56.0, altitude=84.0}, roadResistanceCoefficient=12.0, velocidadeVento=45.0, direcaoVento=45.0, tipo='Terrestre'}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
