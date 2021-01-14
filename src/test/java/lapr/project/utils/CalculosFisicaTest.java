package lapr.project.utils;

import lapr.project.model.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author beatr
 */
public class CalculosFisicaTest {

    /**
     * Test of calculoEnergia method, of class CalculosFisica.
     */
    @Test
    public void testCalculoEnergia() {
        System.out.println("calculoEnergia");
        double pesoEstafeta = 12.0;
        double pesoScooter = 12.0;
        double areaFrontal = 4.0;
        double pesoEncomenda = 3.0;
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double expResult = 3.5852089356277385E15;
        double result = CalculosFisica.calculoEnergia(pesoEstafeta, pesoScooter, areaFrontal, pesoEncomenda, end1, end2);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculoForcaTotal method, of class CalculosFisica.
     */
    @Test
    public void testCalculoForcaTotal() {
        System.out.println("calculoForcaTotal");
        double pesoTotal = 10.0;
        double areaFrontal = 5.0;
        double expResult = 3.6073514101639044E8;
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double result = CalculosFisica.calculoForcaTotal(pesoTotal, areaFrontal, end1, end2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoRoad_slope method, of class CalculosFisica.
     */
    @Test
    public void testCalculoRoadSlope() {
        System.out.println("calculoRoad_slope");
        double pesoTotal = 5.0;
        double expResult = 1.8036754098483995E8;
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double result = CalculosFisica.calculoRoadSlope(pesoTotal, end1, end2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoRoad_load method, of class CalculosFisica.
     */
    @Test
    public void testCalculoRoadLoad() {
        System.out.println("calculoRoad_load");
        double pesoTotal = 10.0;
        double expResult = 0.5389999992895103;
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double result = CalculosFisica.calculoRoadLoad(pesoTotal, end1, end2);
        System.out.println(result);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoAerodynamic_drag_force method, of class CalculosFisica.
     */
    @Test
    public void testCalculoAerodynamicDragForce() {
        System.out.println("calculoAerodynamic_drag_force");
        double areaFrontal = 10.0;
        double expResult = 117.01542112999017;
        double result = CalculosFisica.calculoAerodynamicDragForce(areaFrontal);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoDistancia method, of class CalculosFisica.
     */
    @Test
    public void testCalculoDistancia() {
        System.out.println("calculoDistancia");
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double expResult = 3680970.2241804074;
        double result = CalculosFisica.calculoDistancia(end1, end2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoInclinacao method, of class CalculosFisica.
     */
    @Test
    public void testCalculoInclinacao() {
        System.out.println("calculoInclinacao");
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double expResult = 5.134515861020911E-5;
        double result = CalculosFisica.calculoInclinacao(end1, end2);
        System.out.println(result);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoVelocidade method, of class CalculosFisica.
     */
    @Test
    public void testCalculoVelocidade() {
        System.out.println("calculoVelocidade");
        double expResult = 12.726666;
        double result = CalculosFisica.calculoVelocidade();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoTempo method, of class CalculosFisica.
     */
    @Test
    public void testCalculoTempo() {
        System.out.println("calculoTempo");
        double distancia = 38.108959565686725;
        double expResult = 2.994418142637414;
        double result = CalculosFisica.calculoTempo(distancia);
        assertEquals(expResult, result, 0.0);

    }
    
}
