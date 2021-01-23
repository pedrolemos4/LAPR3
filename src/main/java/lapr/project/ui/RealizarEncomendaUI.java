package lapr.project.ui;

import lapr.project.controller.EnviarNotaTransferenciaController;
import lapr.project.controller.PedirItemFarmaciaController;
import lapr.project.controller.RealizaEncomendaController;
import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.*;
import lapr.project.utils.Data;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class RealizarEncomendaUI {

    public static final Scanner LER = new Scanner(System.in);
    public static final RegistarClienteUI rcUI = new RegistarClienteUI();

    RealizaEncomendaController controller;
    PedirItemFarmaciaController controller2;
    EnviarNotaTransferenciaController controller3;

    public RealizarEncomendaUI() {
        controller = new RealizaEncomendaController(new ProdutosDB(), new EncomendaDB(), new ReciboDB(), new ClienteDB(), new EmailDB(), new EnderecoDB());
        controller2 = new PedirItemFarmaciaController(new FarmaciaDB(), new TransferenciaDB(), new EmailDB());
        controller3 = new EnviarNotaTransferenciaController(new EmailDB());
    }

    public void introduzEncomenda() throws SQLException, ParseException, ClassNotFoundException {

        List<Farmacia> lstFarmacias = controller2.getLstFarmacias();
        if (lstFarmacias.isEmpty()) {
            System.out.println("Não existem farmácias");
            rcUI.menuCliente();
        }

        System.out.println("--STOCK DAS FARMÁCIAS--");
        for (Farmacia f : lstFarmacias) {
            if (!controller.getListStock(f.getNIF()).isEmpty()) {
                System.out.println("FARMACIA: " + f.getMorada());
                System.out.println("--PRODUTOS--");
                for (Map.Entry<Produto, Integer> p : controller.getListStock(f.getNIF()).entrySet()) {
                    System.out.println(p.getKey().toString());
                    System.out.println("QUANTIDADE: " + p.getValue());
                }
            }
        }
        System.out.println("Introduza o id de um produto apresentado ou 0 para terminar.");
        Cliente cliente = controller.getCliente();

        Endereco enderecoCliente = controller.getEnderecoByNifCliente(cliente.getClienteNIF());

        Graph<Endereco, Double> graph = new Graph<>(true);
        graph = controller2.generateGrafo(graph);

        System.out.println("A BIA É BOOMER: " + graph.validVertex(enderecoCliente));

        System.out.println(graph.toString());

        Farmacia farm = controller2.getFarmaciaProxima(graph, enderecoCliente);
        int nif = farm.getNIF();
        Map<Produto, Integer> stock = controller.getListStock(nif);
        int nif1Farmacia = nif;

        boolean bool = true;

        System.out.println("FARMACIA MAIS PERTO CLIENTE: " + nif);
        while (bool) {

            int idInserido = LER.nextInt();

            if (idInserido == 0) {
                break;
            }

            System.out.println("Insira a quantidade do produto que pretende.");
            int qntd = LER.nextInt();
            Produto prod = controller.getProdutoByID(idInserido);
            int nifNextFarmacia = nif1Farmacia;

            if (!controller.produtoEncomenda(nifNextFarmacia, prod, qntd)) {

                while (qntd > 0) {

                    Farmacia farmaciaProximaFarmacia1 = controller2.getFarmaciaProxima(graph, controller.getEnderecoOrigem(nifNextFarmacia));
                    int farmaciaSeguinte = farmaciaProximaFarmacia1.getNIF();

                    Map<Produto, Integer> listStockFarmacias = controller.getListStock(farmaciaSeguinte);

                    if (listStockFarmacias.containsKey(prod) && listStockFarmacias.get(prod) >= qntd) {
                        System.out.println("If 1.");
                        System.out.println(" 101 FarmaciaSeguinte: "+controller2.getFarmaciaByNIF(farmaciaSeguinte).getEmail() + " nifNextFarmacia: "+controller2.getFarmaciaByNIF(nifNextFarmacia).getEmail());
                        controller2.realizaPedido(controller2.getFarmaciaByNIF(farmaciaSeguinte), controller2.getFarmaciaByNIF(nifNextFarmacia), prod, qntd);
                        System.out.println(" 102 FarmaciaSeguinte: "+controller2.getFarmaciaByNIF(farmaciaSeguinte).getEmail() + " nifNextFarmacia: "+controller2.getFarmaciaByNIF(nifNextFarmacia).getEmail());
                        controller3.enviarNotaTransferencia(controller2.getFarmaciaByNIF(farmaciaSeguinte), controller2.getFarmaciaByNIF(nifNextFarmacia), prod, qntd);
                        controller2.enviaNotaEntrega(controller2.getFarmaciaByNIF(farmaciaSeguinte).getEmail(), controller2.getFarmaciaByNIF(nifNextFarmacia).getEmail());
                        controller.produtoEncomenda(farmaciaSeguinte, prod, qntd);
                        qntd=0;
                        break;
                    }

                    if (listStockFarmacias.containsKey(prod) && listStockFarmacias.get(prod) < qntd) {
                        System.out.println("If 2.");
                        qntd = qntd - listStockFarmacias.get(prod);
                        controller2.realizaPedido(controller2.getFarmaciaByNIF(farmaciaSeguinte), controller2.getFarmaciaByNIF(nifNextFarmacia), prod, qntd);
                        controller3.enviarNotaTransferencia(controller2.getFarmaciaByNIF(farmaciaSeguinte), controller2.getFarmaciaByNIF(nifNextFarmacia), prod, qntd);
                        controller2.enviaNotaEntrega(controller2.getFarmaciaByNIF(farmaciaSeguinte).getEmail(), controller2.getFarmaciaByNIF(nifNextFarmacia).getEmail());
                        controller.produtoEncomenda(farmaciaSeguinte, prod, qntd);
                        nifNextFarmacia = farmaciaSeguinte;
                    }

                    if (!listStockFarmacias.containsKey(prod)) {
                        System.out.println("If 3.");
                        nifNextFarmacia = farmaciaSeguinte;
                    }
                }
                if (qntd > 0) {
                    System.out.println("Não havia a quantidade que pretende.");
                    String assunto = "Produto não disponível.";
                    String mensagem = "O produto não estava disponível na quantidade pretendida logo foi inserido a quantidade existente em stock.";
                    String email = UserSession.getInstance().getUser().getEmail();
                    controller.notificaCliente(email, assunto, mensagem);
                }
            } else {
                controller.produtoEncomenda(nifNextFarmacia, prod, qntd);
            }
            System.out.println("Insira o id do produto que pretende ou 0 se pretender terminar a lista de produtos.");
        }

        System.out.println("Lista de Produtos selecionados: ");

        for (Produto p : controller.getMapaEncomenda().keySet()) {
            System.out.println("Produto: " + p.getDesignacao() + " Quantidade: " + controller.getMapaEncomenda().get(p));
        }

        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date1 = new Date(System.currentTimeMillis());
            String dataInicio = formatter.format(date1);
           
            System.out.println("Possui " + controller.getCliente().getCreditos() + " creditos.");
            System.out.println("Deseja pagar com creditos? (S/N)");
            LER.nextLine();
            String credsC = LER.nextLine();

            if (credsC.equalsIgnoreCase("S") || credsC.equalsIgnoreCase("SIM")) {
                Data date = Data.dataAtual();
                double creditosData = controller.getCreditosData(date, controller.getPreco());
                if (controller.getCliente().getCreditos() < creditosData) {
                    System.out.println("Creditos insuficientes.");
                } else {
                    controller.removerCreditos(controller.getCliente().getClienteNIF(), controller.getCliente().getCreditos() - creditosData);
                    System.out.println("Foram retirados: " + creditosData + " creditos.");
                }
            }

            System.out.println("Peso: " + controller.getPeso());
            System.out.println("Preço: " + controller.getPreco());

            Encomenda enc = new Encomenda(controller.getCliente().getClienteNIF(), nif, dataInicio,
                    controller.getPreco(), controller.getPeso(), controller.getTaxa(controller.getPreco()), 1);

            Map<Produto, Integer> mapaEncomenda = controller.getMapaEncomenda();

            controller.registaEncomenda(enc);

            for (Produto p : mapaEncomenda.keySet()) {
                controller.registaEncomendaProduto(enc, p, mapaEncomenda.get(p));
            }

            for (Produto p1 : mapaEncomenda.keySet()) {
                controller.removerProdutosEncomenda(p1, nif, mapaEncomenda.get(p1), stock.get(p1));
            }

            double precoTotal = controller.getPrecoTotal(mapaEncomenda, enc.getTaxa());

            Recibo rec = new Recibo(controller.getCliente().getClienteNIF(), precoTotal,
                    dataInicio, enc.getId());
            rec.setLst(mapaEncomenda);

            controller.geraCreditos(controller.getCliente(), precoTotal);

            controller.registaRecibo(rec);

            String assunto = "Recibo.";
            String mensagem = rec.toString();
            controller.notificaCliente(UserSession.getInstance().getUser().getEmail(), assunto, mensagem);

            for (Produto p : mapaEncomenda.keySet()) {
                controller.novoRecibo(rec, p, mapaEncomenda.get(p));
            }

            System.out.println("Data do Recibo:");
            System.out.println(rec.getData());
            System.out.println("Preco Total:");
            System.out.println(precoTotal);
            System.out.println("Lista de Produtos:");
            for (Produto p : mapaEncomenda.keySet()) {
                System.out.println(p.getDesignacao() + " " + mapaEncomenda.get(p));
            }

            System.out.println("\n\nEncomenda adicionada com sucesso!");
            rcUI.menuCliente();
        } else {
            System.out.println("\n\nEncomenda cancelada.");
        }
    }
}
