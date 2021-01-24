package lapr.project.utils;

import lapr.project.model.Endereco;

/**
 *
 * @author beatr
 */
public class CalculosFisica {
    
    private CalculosFisica(){
        //dummy constructor
    }
    
    public static final double GRAVITATIONAL_ACCELERATION = 9.8;
    public static final double AIR_DENSITY_20DEGREES = 1.2041;
    public static final double AIR_DRAG_COEFFICIENT = 0.3;
    public static final double SPEED = 13.56; // m/s
    public static final double EARTHRADIUS = 6371; // km
    public static final double LIFTTODRAG = 15; 
    public static final double EFICIENCIA = 0.70; 
    
    /**
     * Devolve o calculo da energia gasta no percurso pela scooter
     * @param pesoEstafeta peso do estafeta
     * @param pesoVeiculo peso do scooter
     * @param areaFrontal a area frontal da scooter
     * @param pesoEncomenda o peso da encomenda
     * @param end1 o endereco de origem
     * @param end2 o endereco de destino
     * @param roadResistanceCoefficient roadResistanceCoefficient do percurso
     * @param direcaoVento direcao do vento no percurso
     * @param velocidadeVento velocidade do vento no percurso
     * @return calculo da energia gasta no percurso
     */
    public static double calculoEnergiaScooter(double pesoEstafeta, double pesoVeiculo, double areaFrontal,
            double pesoEncomenda, Endereco end1, Endereco end2, double roadResistanceCoefficient, double direcaoVento, double velocidadeVento) {
        double pesoTotal = pesoEncomenda + pesoEstafeta + pesoVeiculo;
        double forcaTotalExercida = calculoForcaTotal(pesoTotal, areaFrontal, end1.getLatitude(), end1.getLongitude(), end1.getAltitude(),
                end2.getLatitude(), end2.getLongitude(), end2.getAltitude(), roadResistanceCoefficient, velocidadeVento, direcaoVento);
        double distancia = calculoDistancia(end1.getLatitude(), end1.getLongitude(), end1.getAltitude(),
                end2.getLatitude(), end2.getLongitude(), end2.getAltitude());
        double tempo = calculoTempo(distancia, velocidadeVento, direcaoVento);
        
        return forcaTotalExercida * calculoVelocidade(velocidadeVento, direcaoVento) * tempo;
    }
    
    /**
     * Devolve o calculo da energia gasta no percurso
     * @param pesoVeiculo peso do drone
     * @param largura largura do drone
     * @param areaFrontal area frontal do drone
     * @param pesoTotalEntrega o peso da encomenda
     * @param end1 o endereco de origem
     * @param end2 o endereco de destino
     * @param direcaoVento direcao do vento no percurso
     * @param velocidadeVento velocidade do vento no percurso
     * @return calculo da energia gasta no percurso
     */
    public static double calculoEnergiaDrone(double pesoVeiculo, double largura, double areaFrontal,
                        double pesoTotalEntrega, Endereco end1, Endereco end2,
                        double direcaoVento, double velocidadeVento) {
        double pesoTotalQuadrado = Math.pow(pesoTotalEntrega + pesoVeiculo, 2);
        double a = AIR_DENSITY_20DEGREES * Math.pow(largura,2) * calculoVelocidade(velocidadeVento, direcaoVento);
        double lift = pesoTotalQuadrado / a;
        
        double parasiticDrag = calculoAerodynamicDragForce(areaFrontal, velocidadeVento, direcaoVento, 3);
        double power = lift + parasiticDrag;
        double distancia = calculoDistancia(end1.getLatitude(), end1.getLongitude(), 0,
                end2.getLatitude(), end2.getLongitude(), 0);
        double tempo = calculoTempo(distancia, velocidadeVento, direcaoVento);
        return power * tempo;        
    }
    
    /**
     * Devolve o tempo gasto no percurso
     * @param distancia distancia do percurso
     * @param velocidadeVento velocidade do vento no percurso
     * @param direcaoVento direçao do vento no percurso
     * @return tempo gasto no percurso
     */
    public static double calculoTempo(double distancia, double velocidadeVento, double direcaoVento){
        return distancia/calculoVelocidade(velocidadeVento, direcaoVento);
    }
    
    /**
     * Devolve o calculo de todas as forças
     * @param pesoTotal o peso total
     * @param areaFrontal a area frontal da scooter
     * @param end1Lat a latitude do endereço de origem
     * @param end1Lon a longitude do endereço de origem
     * @param end1Alt a altitude do endereço de origem
     * @param end2Lat a latitude do endereço de destino
     * @param end2Lon a longitude do endereço de destino
     * @param end2Alt a altitude do endereço de destino
     * @param roadResistanceCoefficient roadResistanceCoefficient do percurso
     * @param velocidadeVento velocidade do vento
     * @param direcaoVento direcao do vento
     * @return calculo de todas as forças
     */
    public static double calculoForcaTotal(double pesoTotal, double areaFrontal,
            double end1Lat, double end1Lon, double end1Alt,
            double end2Lat, double end2Lon, double end2Alt, double roadResistanceCoefficient, double velocidadeVento, double direcaoVento) {
        double roadSlope = calculoRoadSlope(pesoTotal,end1Lat, end1Lon, end1Alt,
                end2Lat, end2Lon, end2Alt);
        double roadLoad = calculoRoadLoad(pesoTotal, end1Lat, end1Lon, end1Alt,
                end2Lat, end2Lon, end2Alt, roadResistanceCoefficient);
        double aerodynamicDragForce = calculoAerodynamicDragForce(areaFrontal, velocidadeVento, direcaoVento,2);
        return roadSlope + roadLoad + aerodynamicDragForce;
    }
    
    /**
     * Devolve o calculo de inclinaçao
     * @param end1Lat a latitude do endereço de origem
     * @param end1Lon a longitude do endereço de origem
     * @param end1Alt a altitude do endereço de origem
     * @param end2Lat a latitude do endereço de destino
     * @param end2Lon a longitude do endereço de destino
     * @param end2Alt a altitude do endereço de destino
     * @return calculo da inclinaçao
     */
    public static double calculoInclinacao(double end1Lat, double end1Lon, double end1Alt,
            double end2Lat, double end2Lon, double end2Alt){
        double distancia = calculoDistancia(end1Lat, end1Lon, end1Alt,
                end2Lat, end2Lon, end2Alt);
        return (end2Alt - end1Alt) / distancia; // sin(α)
    }
    
    /**
     * Devolve o calculo da força associada ao movimento
     * @param pesoTotal o peso total
     * @param end1Lat a latitude do endereço de origem
     * @param end1Lon a longitude do endereço de origem
     * @param end1Alt a altitude do endereço de origem
     * @param end2Lat a latitude do endereço de destino
     * @param end2Lon a longitude do endereço de destino
     * @param end2Alt a altitude do endereço de destino
     * @return calculo da força associada ao movimento
     */
    public static double calculoRoadSlope(double pesoTotal, double end1Lat, double end1Lon, double end1Alt,
            double end2Lat, double end2Lon, double end2Alt) {
        return pesoTotal * GRAVITATIONAL_ACCELERATION * calculoInclinacao(end1Lat, end1Lon, end1Alt,
                end2Lat, end2Lon, end2Alt);
    }
    
    /**
     * Devolve o calculo da força associada ao atrito de rolamento
     * @param pesoTotal o peso total
     * @param end1Lat a latitude do endereço de origem
     * @param end1Lon a longitude do endereço de origem
     * @param end1Alt a altitude do endereço de origem
     * @param end2Lat a latitude do endereço de destino
     * @param end2Lon a longitude do endereço de destino
     * @param end2Alt a altitude do endereço de destino
     * @param roadResistanceCoefficient roadResistanceCoefficient
     * @return calculo da força associada ao atrito de rolamento
     */
    public static double calculoRoadLoad(double pesoTotal, double end1Lat, double end1Lon, double end1Alt,
            double end2Lat, double end2Lon, double end2Alt, double roadResistanceCoefficient) {
        double cosseno = Math.sqrt(1 - Math.pow(calculoInclinacao(end1Lat, end1Lon, end1Alt,
            end2Lat, end2Lon, end2Alt), 2));
        return pesoTotal * GRAVITATIONAL_ACCELERATION * roadResistanceCoefficient * cosseno;
    }
    
    /**
     * Devolve o calculo da força de arrasto aerodinâmica
     * @param areaFrontal a area Frontal da scooter
     * @param velocidadeVento velocidade do vento
     * @param direcaoVento direcao do vento
     * @return calculo da força de arrasto aerodinâmica
     */
    public static double calculoAerodynamicDragForce(double areaFrontal, double velocidadeVento, double direcaoVento, double potencia) {
        return 0.5 * AIR_DENSITY_20DEGREES * AIR_DRAG_COEFFICIENT * areaFrontal * Math.pow(calculoVelocidade(velocidadeVento, direcaoVento), potencia);
    }
    
    /**
     * Devolve a distancia percorrida
     * @param end1Lat a latitude do endereço de origem
     * @param end1Lon a longitude do endereço de origem
     * @param end1Alt a altitude do endereço de origem
     * @param end2Lat a latitude do endereço de destino
     * @param end2Lon a longitude do endereço de destino
     * @param end2Alt a altitude do endereço de destino
     * @return distancia percorrida
     */
    public static double calculoDistancia(double end1Lat, double end1Lon, double end1Alt,
            double end2Lat, double end2Lon, double end2Alt) {
        double distLatitude = Math.toRadians(end2Lat - end1Lat);
        double distLongitude = Math.toRadians(end2Lon - end1Lon);
        double distancia = Math.sin(distLatitude / 2) * Math.sin(distLatitude / 2)
                + Math.cos(Math.toRadians( end1Lat))
                * Math.cos(Math.toRadians(end2Lat))
                * Math.sin(distLongitude / 2) * Math.sin(distLongitude / 2);
        double i = 2 * Math.atan2(Math.sqrt(distancia), Math.sqrt(1 - distancia));
        double dist = EARTHRADIUS * i * 1000; // converte para metros
        double altura = end1Alt - end2Alt;
        dist = Math.pow(dist, 2) + Math.pow(altura, 2);
        return Math.sqrt(dist);
    }
    
    /**
     * Devolve o calculo da velocidade
     * @param velocidadeVento velocidade do vento
     * @param direcaoVento direçao do vento
     * @return calculo da velocidade
     */
    public static double calculoVelocidade(double velocidadeVento, double direcaoVento){
        if(direcaoVento == 90){
            return SPEED - (velocidadeVento /** Math.cos(0)*/);
        }else if (direcaoVento < 180 && direcaoVento > 0){
            return SPEED - (velocidadeVento * Math.cos(Math.toRadians(direcaoVento)));
        }else{
            return SPEED;
        }
        
    }
    
    /**
     * Devolve a distancia que o veiculo pode percorrer até ficar sem bateria
     * @param capacidade capacidade do veiculo
     * @param percentagemBateria percentagem da bateria do veiculo
     * @param potencia potencia do veiculo
     * @return distancia que o veiculo pode percorrer até ficar sem bateria
     */
    public static double getDistanciaQuePodePercorrer(double capacidade, double percentagemBateria, double potencia){
        double cap = capacidade * 3600;
        double percentagem = percentagemBateria / 100;
        double a = cap * percentagem;
        double tempo = a / potencia;
        return tempo * SPEED * EFICIENCIA;
    }
}
