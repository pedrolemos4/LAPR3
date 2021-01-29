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
        Endereco end1 = new Endereco("vfve", 41.149742, 8.646549, 20);
        Endereco end2 = new Endereco("tg", 41.150171, 8.645606, 47);
        double road = 12.0;
        double velocidadeVento = 12.0;
        double direcaoVento = 15.0;
        double expResult = 444718.3637051812;
        double result = CalculosFisica.calculoEnergiaScooter(pesoEstafeta, pesoVeiculo, areaFrontal, pesoEncomenda, end1, end2, road, direcaoVento, velocidadeVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoEnergiaDrone method, of class CalculosFisica.
     */
    @Test
    public void testCalculoEnergiaDrone() {
        System.out.println("calculoEnergiaDrone");
        double pesoVeiculo = 3.3;
        double areaFrontal = 3.0;
        double hoverPower = 1000;
        double pesoEncomenda = 5.0;
        Endereco end1 = new Endereco("vfve", 41.149742, 8.646549, 20);
        Endereco end2 = new Endereco("tg", 41.150171, 8.645606, 47);
        double velocidadeVento = 9.0;
        double direcaoVento = 30.0;
        double expResult = 2.895310462861433E9;
        double result = CalculosFisica.calculoEnergiaDrone(pesoVeiculo, hoverPower, areaFrontal, pesoEncomenda, end1, end2, direcaoVento, velocidadeVento);
        System.out.println(result);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoTempo method, of class CalculosFisica.
     */
    @Test
    public void testCalculoTempoScooter() {
        System.out.println("calculoTempoScooter");
        double distancia = 48.0;
        double velocidadeVento = 12.0;
        double direcaoVento = 15.0;
        double expResult = 24.379217700936035;
        double result = CalculosFisica.calculoTempoScooter(distancia, velocidadeVento, direcaoVento);
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
        double road = 12.0;
        double velocidadeVento = 12.0;
        double direcaoVento = 15.0;
        double expResult = 5664.671510221156;
        double result = CalculosFisica.calculoForcaTotal(pesoTotal, areaFrontal, end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt, road, direcaoVento, velocidadeVento);
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
        double end1Lat = 41.149742;
        double end1Lon = 8.646549;
        double end1Alt = 20.0;
        double end2Lat = 41.150171;
        double end2Lon = 8.645606;
        double end2Alt = 47.0;
        double expResult = 93.59775514214645;
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
        double road = 12.0;
        double expResult = 5644.7999997791085;
        double result = CalculosFisica.calculoRoadLoad(pesoTotal, end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt, road);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoAerodynamicDragForce method, of class CalculosFisica.
     */
    @Test
    public void testCalculoAerodynamicDragForce() {
        System.out.println("calculoAerodynamicDragForce");
        double areaFrontal = 23.0;
        double velocidadeVento = 12.0;
        double direcaoVento = 15.0;
        double expResult = 16.103660093849353;
        double result = CalculosFisica.calculoAerodynamicDragForce(areaFrontal, velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoDistancia method, of class CalculosFisica.
     */
    @Test
    public void testCalculoDistancia() {
        System.out.println("calculoDistancia");
        double end1Lat = 41.149742;
        double end1Lon = 8.646549;
        double end1Alt = 20.0;
        double end2Lat = 41.150171;
        double end2Lon = 8.645606;
        double end2Alt = 47.0;
        double expResult = 96.11768985631346;
        double result = CalculosFisica.calculoDistancia(end1Lat, end1Lon, end1Alt, end2Lat, end2Lon, end2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoVelocidadeScooter method, of class CalculosFisica.
     */
    @Test
    public void testCalculoVelocidadeScooter() {
        System.out.println("calculoVelocidadeScooter");
        double velocidadeVento = 12.0;
        double direcaoVento = 15.0;
        double expResult = 1.9688900845311803;
        double result = CalculosFisica.calculoVelocidadeScooter(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoVelocidadeDrone method, of class CalculosFisica.
     */
    @Test
    public void calculoVelocidadeDrone() {
        System.out.println("calculoVelocidadeDrone");
        double velocidadeVento = 12.0;
        double direcaoVento = 15.0;
        double expResult = 46.00889008453118;
        double result = CalculosFisica.calculoVelocidadeDrone(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of calculoVelocidade method, of class CalculosFisica.
     */
    @Test
    public void testCalculoVelocidadeScooter1() {
        System.out.println("calculoVelocidadeScooter1");
        double velocidadeVento = 12.0;
        double direcaoVento = 90.0;
        double expResult = 1.5600000000000005;
        double result = CalculosFisica.calculoVelocidadeScooter(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoVelocidadeDrone method, of class CalculosFisica.
     */
    @Test
    public void calculoVelocidadeDrone1() {
        System.out.println("calculoVelocidadeDrone1");
        double velocidadeVento = 12.0;
        double direcaoVento = 90.0;
        double expResult = 45.6;
        double result = CalculosFisica.calculoVelocidadeDrone(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of calculoVelocidade method, of class CalculosFisica.
     */
    @Test
    public void testCalculoVelocidadeScooter2() {
        System.out.println("calculoVelocidadeScooter2");
        double velocidadeVento = 12.0;
        double direcaoVento = 185.0;
        double expResult = 13.56;
        double result = CalculosFisica.calculoVelocidadeScooter(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoVelocidadeDrone method, of class CalculosFisica.
     */
    @Test
    public void calculoVelocidadeDrone2() {
        System.out.println("calculoVelocidadeDrone2");
        double velocidadeVento = 12.0;
        double direcaoVento = 185.0;
        double expResult = 57.6;
        double result = CalculosFisica.calculoVelocidadeDrone(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of calculoVelocidade method, of class CalculosFisica.
     */
    @Test
    public void testCalculoVelocidadeScooter3() {
        System.out.println("calculoVelocidadeScooter3");
        double velocidadeVento = 12.0;
        double direcaoVento = -1.0;
        double expResult = 13.56;
        double result = CalculosFisica.calculoVelocidadeScooter(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoVelocidadeDrone method, of class CalculosFisica.
     */
    @Test
    public void calculoVelocidadeDrone3() {
        System.out.println("calculoVelocidadeDrone3");
        double velocidadeVento = 12.0;
        double direcaoVento = -1.0;
        double expResult = 57.6;
        double result = CalculosFisica.calculoVelocidadeDrone(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of calculoVelocidade method, of class CalculosFisica.
     */
    @Test
    public void testCalculoVelocidadeScooter4() {
        System.out.println("calculoVelocidadeScooter4");
        double velocidadeVento = 12.0;
        double direcaoVento = 180.0;
        double expResult = 13.56;
        double result = CalculosFisica.calculoVelocidadeScooter(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }
    
    /**
     * Test of calculoVelocidadeDrone method, of class CalculosFisica.
     */
    @Test
    public void calculoVelocidadeDrone4() {
        System.out.println("calculoVelocidadeDrone4");
        double velocidadeVento = 12.0;
        double direcaoVento = 180.0;
        double expResult = 57.6;
        double result = CalculosFisica.calculoVelocidadeDrone(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of calculoVelocidade method, of class CalculosFisica.
     */
    @Test
    public void testCalculoVelocidadeScooterScooter5() {
        System.out.println("calculoVelocidade5");
        double velocidadeVento = 12.0;
        double direcaoVento = 0.0;
        double expResult = 13.56;
        double result = CalculosFisica.calculoVelocidadeScooter(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoVelocidadeDrone method, of class CalculosFisica.
     */
    @Test
    public void calculoVelocidadeDrone5() {
        System.out.println("calculoVelocidadeDrone5");
        double velocidadeVento = 12.0;
        double direcaoVento = 0.0;
        double expResult = 57.6;
        double result = CalculosFisica.calculoVelocidadeDrone(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDistanciaQuePodePercorrer method, of class CalculosFisica.
     */
    @Test
    public void testGetDistanciaQuePodePercorrer() {
        System.out.println("getDistanciaQuePodePercorrer");
        double capacidade = 350.0;
        double percentagemBateria = 100.0;
        double potencia = 250.0;
        double expResult = 47839.68;
        double result = CalculosFisica.getDistanciaQueScooterPodePercorrer(capacidade, percentagemBateria, potencia);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculoTempoDrone method, of class CalculosFisica.
     */
    @Test
    public void calculoTempoDrone() {
        System.out.println("calculoTempoDrone");
        double distancia = 48.0;
        double velocidadeVento = 12.0;
        double direcaoVento = 15.0;
        double expResult = 1.0432766344028424;
        double result = CalculosFisica.calculoTempoDrone(distancia, velocidadeVento, direcaoVento);
        System.out.println(result);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculoSubidaDescida method, of class CalculosFisica.
     */
    @Test
    public void testCalculoSubidaDescida() {
        System.out.println("calculoSubidaDescida");
        double pesoVeiculo = 3.3;
        double pesoTotalEntrega = 5.0;
        double areaFrontal = 1.0;
        double expResult = 23636.326496798683;
        double result = CalculosFisica.calculoSubidaDescida(pesoVeiculo, pesoTotalEntrega, areaFrontal);
        assertEquals(expResult, result, 0.0);

    }



    /**
     * Test of calculoHeadWindDrone method, of class CalculosFisica.
     */
    @Test
    public void testCalculoHeadWindDrone() {
        System.out.println("calculoHeadWindDrone");
        double velocidadeVento = 9.0;
        double direcaoVento = 15.0;
        double expResult = 8.693332436601615;
        double result = CalculosFisica.calculoHeadWindDrone(velocidadeVento, direcaoVento);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getDistanciaQueDronePodePercorrer method, of class CalculosFisica.
     */
    @Test
    public void getDistanciaQueDronePodePercorrer() {
        System.out.println("getDistanciaQuePodePercorrer");
        double capacidade = 350.0;
        double percentagemBateria = 100.0;
        double potencia = 250.0;
        double expResult = 56448.0;
        double result = CalculosFisica.getDistanciaQueDronePodePercorrer(capacidade, percentagemBateria, potencia);
        assertEquals(expResult, result, 0.0);

    }
}
