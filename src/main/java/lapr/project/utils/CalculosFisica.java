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
    public static final double WIND_SPEED = 0.833334; // m/s
    
    /**
     * Devolve o calculo da energia gasta no percurso
     * @param pesoEstafeta peso do estafeta
     * @param pesoVeiculo peso do scooter
     * @param areaFrontal a area frontal da scooter
     * @param pesoEncomenda o peso da encomenda
     * @param end1 o endereco de origem
     * @param end2 o endereco de destino
     * @return calculo da energia gasta no percurso
     */
    public static double calculoEnergia(double pesoEstafeta, double pesoVeiculo, double areaFrontal,
            double pesoEncomenda, Endereco end1, Endereco end2) {
        double pesoTotal = pesoEncomenda + pesoEstafeta + pesoVeiculo;
        double forcaTotalExercida = calculoForcaTotal(pesoTotal, areaFrontal, end1, end2);
        double distancia = calculoDistancia(end1, end2);
        double tempo = calculoTempo(distancia);
        
        return forcaTotalExercida * calculoVelocidade() * tempo;
    }
    
    public static double calculoTempo(double distancia){
        return distancia/calculoVelocidade();
    }
    
    /**
     * Devolve o calculo de todas as forças
     * @param pesoTotal o peso total
     * @param areaFrontal a area frontal da scooter
     * @param end1 o endereço de origem
     * @param end2 o endereço de destino
     * @return calculo de todas as forças
     */
    public static double calculoForcaTotal(double pesoTotal, double areaFrontal, Endereco end1, Endereco end2) {
        double roadSlope = calculoRoadSlope(pesoTotal, end1, end2);
        double roadLoad = calculoRoadLoad(pesoTotal, end1, end2);
        double aerodynamicDragForce = calculoAerodynamicDragForce(areaFrontal);
        return roadSlope + roadLoad + aerodynamicDragForce;
    }
    
    public static double calculoInclinacao(Endereco end1, Endereco end2){
        double distancia = calculoDistancia(end1, end2);
        return (end2.getAltitude() - end1.getAltitude()) / distancia; // sin(α)
    }
    
    /**
     * Devolve o calculo da força associada ao movimento
     * @param pesoTotal o peso total
     * @param end1 o endereço de origem
     * @param end2 o endereco de destino
     * @return calculo da força associada ao movimento
     */
    public static double calculoRoadSlope(double pesoTotal, Endereco end1, Endereco end2) {
        return pesoTotal * GRAVITATIONAL_ACCELERATION * calculoDistancia(end1, end2);
    }
    
    /**
     * Devolve o calculo da força associada ao atrito de rolamento
     * @param pesoTotal o peso total
     * @param end1 o endereço de origem
     * @param end2 o endereco de destino
     * @return calculo da força associada ao atrito de rolamento
     */
    public static double calculoRoadLoad(double pesoTotal, Endereco end1, Endereco end2) {
        double cosseno = Math.sqrt(1 - Math.pow(calculoInclinacao(end1, end2), 2));
        return pesoTotal * GRAVITATIONAL_ACCELERATION * ROAD_ROOLING_RESISTANCE_COEFFICIENT * cosseno;
    }
    
    /**
     * Devolve o calculo da força de arrasto aerodinâmica
     * @param areaFrontal a area Frontal da scooter
     * @return calculo da força de arrasto aerodinâmica
     */
    public static double calculoAerodynamicDragForce(double areaFrontal) {
        return 0.5 * AIR_DENSITY_20DEGREES * AIR_DRAG_COEFFICIENT * areaFrontal * Math.pow(calculoVelocidade(), 2);
    }
    
    /**
     * Devolve a distancia percorrida
     * @param end1 o endereço de origem
     * @param end2 o endereço de destino
     * @return distancia percorrida
     */
    public static double calculoDistancia(Endereco end1, Endereco end2) {
        double distLatitude = Math.toRadians(end2.getLatitude() - end1.getLatitude());
        double distLongitude = Math.toRadians(end2.getLongitude() - end1.getLongitude());
        double distancia = Math.sin(distLatitude / 2) * Math.sin(distLatitude / 2)
                + Math.cos(Math.toRadians( end1.getLatitude()))
                * Math.cos(Math.toRadians(end2.getLatitude()))
                * Math.sin(distLongitude / 2) * Math.sin(distLongitude / 2);
        double i = 2 * Math.atan2(Math.sqrt(distancia), Math.sqrt(1 - distancia));
        double dist = EARTHRADIUS * i * 1000; // convert to meters
        double altura = end1.getAltitude() - end2.getAltitude();
        dist = Math.pow(dist, 2) + Math.pow(altura, 2);
        return Math.sqrt(dist);
    }
    
    public static double calculoVelocidade(){
        return SPEED - (WIND_SPEED * Math.cos(0));
    }
}
