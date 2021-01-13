package lapr.project.utils;

import lapr.project.model.Endereco;

/**
 *
 * @author beatr
 */
public class CalculosFisica {

    public static final double GRAVITATIONAL_ACCELERATION = 9.8;
    public static final double ROAD_ROOLING_RESISTANCE_COEFFICIENT = 0.0055;
    public static final double AIR_DENSITY_20DEGREES = 1.2041;
    public static final double AIR_DRAG_COEFFICIENT = 0.12;
    public static final double SPEED = 13.56; // m/s
    public static final double EARTHRADIUS = 6371; // km
    
    /**
     * Devolve o calculo da energia gasta no percurso
     * @param pesoEstafeta peso do estafeta
     * @param pesoScooter peso do scooter
     * @param areaFrontal a area frontal da scooter
     * @param pesoEncomenda o peso da encomenda
     * @param end1 o endereco de origem
     * @param end2 o endereco de destino
     * @return calculo da energia gasta no percurso
     */
    public static double calculoEnergia(double pesoEstafeta, double pesoScooter, double areaFrontal,
            double pesoEncomenda, Endereco end1, Endereco end2) {
        double pesoTotal = pesoEncomenda + pesoEstafeta + pesoScooter;
        double forcaTotalExercida = calculoForcaTotal(pesoTotal, areaFrontal);
        double distancia = calculoDistancia(end1, end2);
        double tempo = distancia/SPEED;
        
        return forcaTotalExercida * SPEED * tempo;
    }
    
    /**
     * Devolve o calculo de todas as forças
     * @param pesoTotal o peso total
     * @param areaFrontal a area frontal da scooter
     * @return calculo de todas as forças
     */
    public static double calculoForcaTotal(double pesoTotal, double areaFrontal) {
        double roadSlope = calculoRoadSlope(pesoTotal);
        double roadLoad = calculoRoadLoad(pesoTotal);
        double aerodynamicDragForce = calculoAerodynamicDragForce(areaFrontal);
        return roadSlope + roadLoad + aerodynamicDragForce;
    }
    
    /**
     * Devolve o calculo da força associada ao movimento
     * @param pesoTotal o peso total
     * @return calculo da força associada ao movimento
     */
    public static double calculoRoadSlope(double pesoTotal) {
        return pesoTotal * GRAVITATIONAL_ACCELERATION;
    }
    
    /**
     * Devolve o calculo da força associada ao atrito de rolamento
     * @param pesoTotal o peso total
     * @return calculo da força associada ao atrito de rolamento
     */
    public static double calculoRoadLoad(double pesoTotal) {
        return pesoTotal * GRAVITATIONAL_ACCELERATION * ROAD_ROOLING_RESISTANCE_COEFFICIENT;
    }
    
    /**
     * Devolve o calculo da força de arrasto aerodinâmica
     * @param areaFrontal a area Frontal da scooter
     * @return calculo da força de arrasto aerodinâmica
     */
    public static double calculoAerodynamicDragForce(double areaFrontal) {
        return 0.5 * AIR_DENSITY_20DEGREES * AIR_DRAG_COEFFICIENT * areaFrontal * Math.pow(SPEED, 2);
    }
    
    /**
     * Devolve a distancia percorrida
     * @param end1 o endereço de origem
     * @param end2 o endereço de destino
     * @return distancia percorrida
     */
    public static double calculoDistancia(Endereco end1, Endereco end2) {
        return Math.acos(Math.sin(end2.getLatitude() * Math.PI / 180.0) * Math.sin(end1.getLatitude() * Math.PI / 180.0)
                + Math.cos(end2.getLatitude() * Math.PI / 180.0) * Math.cos(end1.getLatitude() * Math.PI / 180.0)
                * Math.cos((end1.getLongitude() - end2.getLongitude()) * Math.PI / 180.0)) * EARTHRADIUS;
    }
}
