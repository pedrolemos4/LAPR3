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
     * Test of calculoEnergiaScooter method, of class CalculosFisica.
     */
    @Test
    public void testCalculoEnergiaScooter() {
        System.out.println("calculoEnergiaScooter");
        double pesoEstafeta = 12.0;
        double pesoVeiculo = 23.0;
        double areaFrontal = 3.0;
        double pesoEncomenda = 5.0;
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double expResult = 5.311420519345936E15;
        double result = CalculosFisica.calculoEnergiaScooter(pesoEstafeta, pesoVeiculo, areaFrontal, pesoEncomenda, end1, end2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoEnergiaDrone method, of class CalculosFisica.
     */
    @Test
    public void testCalculoEnergiaDrone() {
        System.out.println("calculoEnergiaDrone");
        double pesoEstafeta = 15.0;
        double pesoVeiculo = 48.0;
        double areaFrontal = 6.0;
        double pesoEncomenda = 89.0;
        Endereco end1 = new Endereco("vfve", 56, 78, 132);
        Endereco end2 = new Endereco("tg", 89, 52, 321);
        double expResult = 2.0610664723944008E16;
        double result = CalculosFisica.calculoEnergiaDrone(pesoEstafeta, pesoVeiculo, areaFrontal, pesoEncomenda, end1, end2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoTempo method, of class CalculosFisica.
     */
    @Test
    public void testCalculoTempo() {
        System.out.println("calculoTempo");
        double distancia = 48.0;
        double expResult = 3.771608369387552;
        double result = CalculosFisica.calculoTempo(distancia);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoForcaTotal method, of class CalculosFisica.
     */
    @Test
    public void testCalculoForcaTotal() {
        System.out.println("calculoForcaTotal");
        double pesoTotal = 48.0;
        double areaFrontal = 89.0;
        double end1Lat = 85.0;
        double end1Lon = 48.0;
        double end1Alt = 48.0;
        double end2Lat = 78.0;
        double end2Lon = 52.0;
        double end2Alt = 26.0;
        double expResult = 3.672275869836575E8;
        double result = CalculosFisica.calculoForcaTotal(pesoTotal, areaFrontal, end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoInclinacao method, of class CalculosFisica.
     */
    @Test
    public void testCalculoInclinacao() {
        System.out.println("calculoInclinacao");
        double end1Lat = 45.0;
        double end1Lon = 23.0;
        double end1Alt = 67.0;
        double end2Lat = 34.0;
        double end2Lon = 67.0;
        double end2Alt = 98.0;
        double expResult = 7.919137730440388E-6;
        double result = CalculosFisica.calculoInclinacao(end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoRoadSlope method, of class CalculosFisica.
     */
    @Test
    public void testCalculoRoadSlope() {
        System.out.println("calculoRoadSlope");
        double pesoTotal = 34.0;
        double end1Lat = 56.0;
        double end1Lon = 23.0;
        double end1Alt = 78.0;
        double end2Lat = 54.0;
        double end2Lon = 76.0;
        double end2Alt = 80.0;
        double expResult = 1.1008959952639685E9;
        double result = CalculosFisica.calculoRoadSlope(pesoTotal, end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoRoadLoad method, of class CalculosFisica.
     */
    @Test
    public void testCalculoRoadLoad() {
        System.out.println("calculoRoadLoad");
        double pesoTotal = 48.0;
        double end1Lat = 47.0;
        double end1Lon = 52.0;
        double end1Alt = 25.0;
        double end2Lat = 36.0;
        double end2Lon = 15.0;
        double end2Alt = 54.0;
        double expResult = 2.587199999898758;
        double result = CalculosFisica.calculoRoadLoad(pesoTotal, end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoAerodynamicDragForce method, of class CalculosFisica.
     */
    @Test
    public void testCalculoAerodynamicDragForce() {
        System.out.println("calculoAerodynamicDragForce");
        double areaFrontal = 23.0;
        double expResult = 269.1354685989774;
        double result = CalculosFisica.calculoAerodynamicDragForce(areaFrontal);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoDistancia method, of class CalculosFisica.
     */
    @Test
    public void testCalculoDistancia() {
        System.out.println("calculoDistancia");
        double end1Lat = 23.0;
        double end1Lon = 45.0;
        double end1Alt = 21.0;
        double end2Lat = 76.0;
        double end2Lon = 654.0;
        double end2Alt = 21.0;
        double expResult = 8070889.554742143;
        double result = CalculosFisica.calculoDistancia(end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt);
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
    
}
