package lapr.project.ui;

import lapr.project.controller.RegistarEntregaController;
import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.CalculosFisica;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author beatr
 */
public class RegistarEntregaUI {

    public static final Scanner LER = new Scanner(System.in);
    public final RegistarEntregaController controller;
    private static final String DRONE = "drone";
    private static final String SCOOTER = "scooter";

    public RegistarEntregaUI() {
        this.controller = new RegistarEntregaController(new UtilizadorDB(), new FarmaciaDB(), new EstafetaDB(), new EntregaDB(), new EncomendaDB(), new VeiculoDB(), new EnderecoDB(), new EmailDB(), new ClienteDB(), new CaminhoDB());
    }

    public void introduzEntrega() throws SQLException, ParseException {

        List<Encomenda> listAllEncomenda;
        System.out.println("Lista de farmacias: ");
        List<Farmacia> listFarmacia = controller.getLstFarmacias();
        for (Farmacia f : listFarmacia) {
            System.out.println(f);
            System.out.println("Lista de encomendas por fazer entrega: ");
            listAllEncomenda = controller.getListaEncomenda(f.getNIF());
            for (Encomenda e : listAllEncomenda) {
                System.out.println(e.toString());
            }
        }
        System.out.println("Escolha o nif da farmacia para a qual irá fazer uma entrega");
        int nifFarmacia = LER.nextInt();
        Farmacia farmacia = controller.getFarmaciaByNif(nifFarmacia);

        Estafeta est = controller.getEstafeta();
        int nifEstafeta = est.getNIF();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(System.currentTimeMillis());
        String dataInicio = formatter.format(date);

        System.out.println("Escolha o peso maximo para realizar a entrega");
        double pesoMaximoEntrega = LER.nextDouble();

        double pesoEntrega = 0;

        List<Encomenda> listEncomendaFarmacia = controller.getListaEncomenda(nifFarmacia);
        List<Encomenda> listEncomendaByEntrega = new ArrayList<>();
        List<Endereco> listEnderecos = new LinkedList<>();

        for (Encomenda e : listEncomendaFarmacia) {
            Endereco end1 = controller.getEnderecoByNifCliente(e.getNif());
            if (e.getPesoEncomenda() + pesoEntrega < pesoMaximoEntrega) {
                listEncomendaByEntrega.add(e);
                pesoEntrega = pesoEntrega + e.getPesoEncomenda();
                listEnderecos.add(end1);
            }
        }

        System.out.println("\nEstafeta:\t" + est
                + "\nDataInicio:\t" + dataInicio
                + "\nPeso maximo da entrega:\t" + pesoMaximoEntrega + " Kg"
                + "\nLista das encomendas:\t");
        for (Encomenda e : listEncomendaByEntrega) {
            System.out.println(e.toString());
        }

        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {

            List<Endereco> listEnderecosScooter = controller.getLstEnderecosScooter();
            List<Endereco> listEnderecosDrone = controller.getLstEnderecosDrone();
            LinkedList<Endereco> finalShortPathScooter = new LinkedList<>();
            LinkedList<Endereco> finalShortPathDrone = new LinkedList<>();
            Graph<Endereco, Double> graphDistanciaScooter = new Graph<>(true);
            controller.generateGrafo(graphDistanciaScooter, listEnderecosScooter,"Terrestre");
            Graph<Endereco, Double> graphDistanciaDrone = new Graph<>(true);
            controller.generateGrafo(graphDistanciaDrone, listEnderecosDrone,"Aerea");

            LinkedList<Endereco> listMinScooter = new LinkedList<>();
            LinkedList<Endereco> listMinDrone = new LinkedList<>();

            Veiculo scooter = new Veiculo();
            Veiculo drone = new Veiculo();

            Graph<Endereco, Double> graphScooter = new Graph<>(true);
            Graph<Endereco, Double> graphDrone;

            double minScooter = Double.MAX_VALUE;
            double minDrone = Double.MAX_VALUE;

            List<Veiculo> listVeiculos = controller.getListaVeiculoEntrega(pesoMaximoEntrega, nifFarmacia);
            for (Veiculo v : listVeiculos) {
                if ((v.getDescricao()).equalsIgnoreCase(SCOOTER)) {
                    double distanciaVeiculo = CalculosFisica.getDistanciaQueScooterPodePercorrer(v.getCapacidade(), v.getPercentagemBateria(), v.getPotencia());
                    graphScooter = controller.generateGraphScooter(listEnderecosScooter, new ArrayList<>(listEnderecos), est, v, pesoEntrega);
                    double energiaTotalGastaScooter = controller.getPath(graphScooter, graphDistanciaScooter, new ArrayList<>(listEnderecos), finalShortPathScooter, controller.getEnderecoOrigem(nifFarmacia), 0, v, distanciaVeiculo);
                    if (energiaTotalGastaScooter != 0 && energiaTotalGastaScooter < minScooter) {
                        minScooter = energiaTotalGastaScooter;
                        scooter = v;
                        listMinScooter = new LinkedList<>();
                        listMinScooter.addAll(finalShortPathScooter);
                    }
                    finalShortPathScooter = new LinkedList<>();
                } else if ((v.getDescricao()).equalsIgnoreCase(DRONE)) {
                    boolean flag = false;
                    for (Endereco e : listEnderecos) {
                        if (e.getAltitude() > 150) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        break;
                    } else {
                        double distanciaVeiculo = CalculosFisica.getDistanciaQueDronePodePercorrer(v.getCapacidade(), v.getPercentagemBateria(), v.getPotencia());
                        Drone d = controller.getDroneById(v.getId());
                        graphDrone = controller.generateGraphDrone(listEnderecosDrone, new ArrayList<>(listEnderecos), est, v, d.getHoverPower(), pesoEntrega);
                        double energiaTotalGastaDrone = controller.getPath(graphDrone, graphDistanciaDrone, new ArrayList<>(listEnderecos), finalShortPathDrone, controller.getEnderecoOrigem(nifFarmacia), 0, v, distanciaVeiculo);
                        if (energiaTotalGastaDrone != 0 && energiaTotalGastaDrone < minDrone) {
                            minDrone = energiaTotalGastaDrone;
                            drone = v;
                            listMinDrone.addAll(finalShortPathDrone);
                        }
                        finalShortPathDrone = new LinkedList<>();
                    }
                }
            }

            boolean a = true;
            boolean b = true;
            System.out.println("\n\nTendo em conta que por meio terrestre a entrega tem um custo de: ");
            if (minScooter == Double.MAX_VALUE) {
                System.out.println("Não é possivel realizar a encomenda por este meio.");
                a = false;
            } else {
                System.out.println(minScooter + " J");
            }
            System.out.println("Por meio aereo a entrega tem um custo de: ");
            if (minDrone == Double.MAX_VALUE) {
                System.out.println("Não é possivel realizar a encomenda por este meio.");
                b = false;
            } else {
                System.out.println(minDrone + " J");
            }
            if (a == true || b == true) {
                System.out.println("Escolha o meio por onde pretende realizar a entrega (terrestre/aereo)");
                String escolha = LER.nextLine();
                Veiculo v = null;
                double energia = 0;
                LinkedList<Endereco> listaFinal;
                while (!escolha.equalsIgnoreCase("terrestre") && !escolha.equalsIgnoreCase("aereo")) {
                    System.out.println("Essa escolha nao é possivel. Introduza novamente uma opçao");
                    escolha = LER.nextLine();
                }
                if (escolha.equalsIgnoreCase("terrestre")) {
                    v = scooter;
                    listaFinal = listMinScooter;
                    energia = minScooter;
                } else {
                    v = drone;
                    listaFinal = listMinDrone;
                    energia = minDrone;
                }

                String data = controller.getDuracaoPercurso(listaFinal, v);
                DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
                Date date2 = format1.parse(data);
                Date newDate = new Date(date.getTime() + date2.getTime() + 3600 * 1000);

                Entrega entr = controller.addEntrega(dataInicio, formatter.format(newDate), v.getId(), nifEstafeta, pesoMaximoEntrega);

                for (Encomenda e : listEncomendaByEntrega) {
                    controller.addEncomendaEntrega(entr, e);
                    Endereco end = controller.getEnderecoByNifCliente(e.getNif());
                    Cliente c = controller.getClienteByEndereco(end);
                    Utilizador u = controller.getUtilizadorByNif(c.getClienteNIF());
                    controller.enviarNotaCliente(farmacia, u);
                    controller.updateEncomenda(e.getId(), 3);
                }

                System.out.println("\n\nEntrega adicionada com sucesso");
                System.out.println("\n\nCaminho com menor energia gasta: ");

                int i = listaFinal.size() - 1;
                for (int aux = 0; aux < i; aux++) {
                    if (v.getDescricao().equalsIgnoreCase(SCOOTER)) {
                        double energiaPorTroco = graphScooter.getEdge(listaFinal.get(aux), listaFinal.get(aux + 1)).getWeight();
                        double distancia = CalculosFisica.calculoDistancia(listaFinal.get(aux).getLatitude(), listaFinal.get(aux).getLongitude(), listaFinal.get(aux).getAltitude(),
                                listaFinal.get(aux).getLatitude(), listaFinal.get(aux).getLongitude(), listaFinal.get(aux).getAltitude());
                        Caminho c = controller.getCaminhoByEnderecos(listaFinal.get(aux).getMorada(), listaFinal.get(aux + 1).getMorada());
                        double tempo = CalculosFisica.calculoTempoScooter(distancia, c.getVelocidadeVento(), c.getDirecaoVento());
                        System.out.println("Caminho: " + c.toString() + "Distancia: " + distancia + "Energia: " + energiaPorTroco + "Tempo: " + tempo);
                    }
                    if (v.getDescricao().equalsIgnoreCase(DRONE)) {
                        double energiaPorTroco = graphScooter.getEdge(listaFinal.get(aux), listaFinal.get(aux + 1)).getWeight();
                        double distancia = CalculosFisica.calculoDistancia(listaFinal.get(aux).getLatitude(), listaFinal.get(aux).getLongitude(), 0,
                                listaFinal.get(aux).getLatitude(), listaFinal.get(aux).getLongitude(), 0);
                        Caminho c = controller.getCaminhoByEnderecos(listaFinal.get(aux).getMorada(), listaFinal.get(aux + 1).getMorada());
                        double tempo = CalculosFisica.calculoTempoDrone(distancia, c.getVelocidadeVento(), c.getDirecaoVento());
                        System.out.println("Caminho: " + c.toString() + "Distancia: " + distancia + "Energia: " + energiaPorTroco + "Tempo: " + tempo);
                    }
                }


                System.out.println();
                System.out.println("Densidade do Ar utilizada: " + CalculosFisica.AIR_DENSITY_20DEGREES + " Kg/m^3");
                System.out.println("Coeficiente de Resistência do Ar utilizado: " + CalculosFisica.AIR_DRAG_COEFFICIENT);
                System.out.println("Raio da Terra utilizado: " + CalculosFisica.EARTHRADIUS + " Km");
                System.out.println("Eficiencia da Carga utilizada: " + CalculosFisica.EFICIENCIA * 100 + " %");
                System.out.println("Gravidade na Superficie da Terra utilizada: " + CalculosFisica.GRAVITATIONAL_ACCELERATION + " m/s^2");
                System.out.println("Velocidade do veiculo utilizado: " + CalculosFisica.SPEED + " m/s");
                System.out.println("\n\nEnergia gasta: " + energia + " J");
            } else {
                System.out.println("\n\nEntrega cancelada.");
            }
        }
    }

    //criar grafo com tempo????
//
//    public void getRota(List<Veiculo> listVeiculos, ){
//        for (Veiculo v : listVeiculos) {
//            if ((v.getDescricao()).equalsIgnoreCase(SCOOTER)) {
//                double distanciaVeiculo = CalculosFisica.getDistanciaQuePodePercorrer(v.getCapacidade(), v.getPercentagemBateria(), v.getPotencia());
//                graphScooter = controller.generateGraphScooter(listEnderecosScooter, new ArrayList<>(listEnderecos), est, v, pesoEntrega);
//                double energiaTotalGastaScooter = controller.getPath(graphScooter, graphDistancia, new ArrayList<>(listEnderecos), finalShortPathScooter, controller.getEnderecoOrigem(nifFarmacia), 0, v, distanciaVeiculo);
//                if (energiaTotalGastaScooter != 0) {
//                    if (energiaTotalGastaScooter < minScooter) {
//                        minScooter = energiaTotalGastaScooter;
//                        scooter = v;
//                        listMinScooter.addAll(finalShortPathScooter);
//                    }
//                }
//                finalShortPathScooter = new LinkedList<>();
//            } else if ((v.getDescricao()).equalsIgnoreCase(DRONE)) {
//                boolean flag = false;
//                for (Endereco e : listEnderecos) {
//                    if (e.getAltitude() > 150) {
//                        flag = true;
//                    }
//                }
//                if (flag) {
//                    break;
//                } else {
//                    double distanciaVeiculo = CalculosFisica.getDistanciaQuePodePercorrer(v.getCapacidade(), v.getPercentagemBateria(), v.getPotencia());
//                    Drone d = controller.getDroneById(v.getId());
//                    graphDrone = controller.generateGraphDrone(listEnderecosDrone, new ArrayList<>(listEnderecos), est, v, d.getLargura(), pesoEntrega);
//                    double energiaTotalGastaDrone = controller.getPath(graphDrone, graphDistancia, new ArrayList<>(listEnderecos), finalShortPathDrone, controller.getEnderecoOrigem(nifFarmacia), 0, v, distanciaVeiculo);
//                    if (energiaTotalGastaDrone != 0) {
//                        if (energiaTotalGastaDrone < minDrone) {
//                            minDrone = energiaTotalGastaDrone;
//                            drone = v;
//                            listMinDrone.addAll(finalShortPathDrone);
//                        }
//                    }
//                    finalShortPathDrone = new LinkedList<>();
//                }
//            }
//        }
//    }
}
