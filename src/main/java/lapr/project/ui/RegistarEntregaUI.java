package lapr.project.ui;

import lapr.project.controller.RegistarEntregaController;
import lapr.project.data.*;
import lapr.project.model.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author beatr
 */
public class RegistarEntregaUI {

    public static final Scanner LER = new Scanner(System.in);
    public final RegistarEntregaController controller;
    private static final String DRONE = "drone";
    private static final String SCOOTER = "scooter";

    public RegistarEntregaUI() {
        this.controller = new RegistarEntregaController(new UtilizadorDB(), new FarmaciaDB(), new EstafetaDB(), new EntregaDB(), new EncomendaDB(), new VeiculoDB(), new EnderecoDB(), new EmailDB(), new ClienteDB());
    }

    public void introduzEntrega() throws SQLException, ParseException {

        List<Encomenda> listAllEncomenda = new ArrayList<>();
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

//        System.out.println("Lista de veiculos: ");
//        List<Veiculo> list = controller.getListVeiculo();
//        for (Veiculo s : list) {
//            System.out.println(s);
//        }
//
//        System.out.println("Introduza o id de um veiculo apresentado: ");
//        int idVeiculo = LER.nextInt();
//        Veiculo veiculo = controller.getVeiculo(idVeiculo);
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
                + "\nPeso maximo da entrega:\t" + pesoMaximoEntrega
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

            LinkedList<Endereco> listMinScooter = new LinkedList<>();
            LinkedList<Endereco> listMinDrone = new LinkedList<>();

            Veiculo scooter = null;
            Veiculo drone = null;

            Graph<Endereco, Double> graphScooter = new Graph<>(true);
            Graph<Endereco, Double> graphDrone = new Graph<>(true);

            double minScooter = Double.MAX_VALUE;
            double minDrone = Double.MAX_VALUE;

            if (listEnderecos.isEmpty()) {
                System.out.println("0000000");
            }

            List<Veiculo> listVeiculos = controller.getListaVeiculoEntrega(pesoMaximoEntrega);
            for (Veiculo v : listVeiculos) {
                if ((v.getDescricao()).equalsIgnoreCase(SCOOTER)) {
                    if (listEnderecos.isEmpty()) {
                        System.out.println("111111");
                    }
                    graphScooter = controller.generateGraphScooter(listEnderecosScooter, new ArrayList<>(listEnderecos), est, v, pesoEntrega);
                    if (listEnderecos.isEmpty()) {
                        System.out.println("2222222");
                    }
                    double energiaTotalGastaScooter = controller.getPath(graphScooter, new ArrayList<>(listEnderecos), finalShortPathScooter, controller.getEnderecoOrigem(nifFarmacia), 0);
                    System.out.println("energiaScooter1: " + energiaTotalGastaScooter);
                    if (energiaTotalGastaScooter < minScooter) {
                        minScooter = energiaTotalGastaScooter;
                        System.out.println("minScooter2: " + minScooter);
                        scooter = v;
                        listMinScooter = finalShortPathScooter;
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
                        if (listEnderecos.isEmpty()) {
                            System.out.println("4444444444");
                        }
                        Drone d = controller.getDroneById(v.getId());
                        graphDrone = controller.generateGraphDrone(listEnderecosDrone, new ArrayList<>(listEnderecos), est, v, d.getLargura(), pesoEntrega);
                        if (listEnderecos.isEmpty()) {
                            System.out.println("55555555555555");
                        }
                        double energiaTotalGastaDrone = controller.getPath(graphDrone, new ArrayList<>(listEnderecos), finalShortPathDrone, controller.getEnderecoOrigem(nifFarmacia), 0);
                        System.out.println("energiaDrone1: " + energiaTotalGastaDrone);
                        if (energiaTotalGastaDrone < minDrone) {
                            System.out.println("energiaDrone2: " + energiaTotalGastaDrone);
                            minDrone = energiaTotalGastaDrone;
                            drone = v;
                            listMinDrone = finalShortPathDrone;
                        }
                        finalShortPathDrone = new LinkedList<>();
                    }
                }
            }

            System.out.println("Tendo em conta que por meio terrestre a entrega tem um custo de: " + minScooter
                    + "\ne por meio aereo a entrega tem um custo de: " + minDrone
                    + "\nEscolha o meio por onde pretende realizar a entrega (terrestre/aereo)");
            String escolha = LER.nextLine();
            Veiculo v = null;
            double energia = 0;
            LinkedList<Endereco> listaFinal = new LinkedList<>();
//            while (!escolha.equalsIgnoreCase("terrestre") || !escolha.equalsIgnoreCase("aereo")) {
//                System.out.println("Essa escolha nao é possivel. Introduza novamente uma opçao");
//                escolha = LER.nextLine();
//            }
            if (escolha.equalsIgnoreCase("terrestre")) {
                v = scooter;
                listaFinal = listMinScooter;
                energia = minScooter;
            } else {
                v = drone;
                listaFinal = listMinDrone;
                energia = minDrone;
            }
            for (Endereco end : listaFinal) {
                System.out.println("endre: " + end);
            }
            //tenho que adicionar à lista o endereço inicial
            //CERTO????
            listaFinal.addFirst(controller.getEnderecoOrigem(nifFarmacia));

            System.out.println("veiculo: " + v);
            String data = controller.getDuracaoPercurso(listaFinal, v);
            System.out.println("StringUIdate: " +data);
            DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
            Date date2 = format1.parse(data);
            Date newDate = new Date(date.getTime() + date2.getTime() + 3600 * 1000);
            System.out.println("UIDate1: " +date.getTime());
            System.out.println("UIdate2: " +date2.getTime() + 3600 * 1000);
//            Entrega entrega = new Entrega(dataInicio, formatter.format(newDate), idVeiculo, nifEstafeta);
//
//            controller.updateEntrega(entrega);
            System.out.println("UIDATE: " + formatter.format(newDate));
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
            for(Endereco endereco :  listaFinal){
                System.out.println(endereco.toString());
            }
            System.out.println();
            System.out.println("\n\nEnergia gasta: " + energia + "J");

        }
    }
//
//    public void checkEntregaEficiente() {
//        System.out.println("Lista de farmacias: ");
//        List<Farmacia> listFarmacia = controller.getLstFarmacias();
//        for (Farmacia f : listFarmacia) {
//            System.out.println(f);
//        }
//        System.out.println("Escolha a farmacia para a qual irá fazer uma entrega");
//        int nifFarmacia = LER.nextInt();
//        while (controller.getFarmaciaByNif(nifFarmacia) == null) {
//            System.out.println("Não existe farmácia com este nif.");
//            nifFarmacia = LER.nextInt();
//        }
//
//        System.out.println("Lista de encomendas por fazer entrega: ");
//        List<Encomenda> listAllEncomenda = controller.getListaEncomenda(nifFarmacia);
//        for (Encomenda e : listAllEncomenda) {
//            System.out.println(e);
//        }
//
//        boolean flag = true;
//        double pesoTotal = 0;
//        List<Encomenda> listEncomendas = new ArrayList<>();
//        List<Endereco> listEnderecos = new ArrayList<>();
//        System.out.println("Introduza o id das encomendas que pretende fazer entrega:");
//        while (flag) {
//            int idEncomenda = LER.nextInt();
//            Encomenda e = controller.getEncomenda(idEncomenda);
//            listEncomendas.add(e);
//            pesoTotal = pesoTotal + controller.getEncomenda(e.getId()).getPesoEncomenda();
//            Endereco end = controller.getEnderecoByNifCliente(e.getNif());
//            listEnderecos.add(end);
//            System.out.println("Deseja adicionar mais encomendas (S/N)?");
//            String resposta = LER.nextLine();
//            if (resposta.equalsIgnoreCase("N") || resposta.equalsIgnoreCase("NAO")) {
//                flag = false;
//            }
//        }
//
//        Estafeta est = new Estafeta(123456789, "Tiago Sucks", "tiagosucks@gmail.com", 60, 12, "pass", 1);
//        Veiculo v = new Veiculo(SCOOTER, 123, 12, 100, 10, 25, 560, 30);
//        Scooter s = new Scooter(SCOOTER, 123, 12, 100, 10, 25, 560, 30, 1);
//        Drone d = new Drone(DRONE, 123, 12, 100, 10, 1, 560, 30, 1, 30);
//
//        Graph<Endereco, Double> graphScooter = new Graph<>(true);
//        Graph<Endereco, Double> graphDrone = new Graph<>(true);
//
//        graphScooter = controller.generateGraph(listEnderecos, est, v, s.getAreaFrontal(), pesoTotal);
//        LinkedList<Endereco> finalShortPathScooter = new LinkedList<>();
//
//        double energiaTotalGastaScooter = controller.getPath(graphScooter, listEnderecos, finalShortPathScooter, controller.getEnderecoOrigem(nifFarmacia), 0);
//
//        graphDrone = controller.generateGraph(listEnderecos, est, v, d.getPowerPro(), pesoTotal);
//        LinkedList<Endereco> finalShortPathDrone = new LinkedList<>();
//
//        double energiaTotalGastaDrone = controller.getPath(graphDrone, listEnderecos, finalShortPathDrone, controller.getEnderecoOrigem(nifFarmacia), 0);
//
//        if (energiaTotalGastaDrone < energiaTotalGastaScooter) {
//            System.out.println("A entrega por via aérea é mais eficiente");
//        } else if (energiaTotalGastaDrone > energiaTotalGastaScooter) {
//            System.out.println("A entrega por via terrestre é mais eficiente");
//        } else {
//            System.out.println("O gasto energético é igual tanto por via terrestre como por via aérea ");
//        }
//    }

}
