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

        //System.out.println(generateGrafo.toString());

        Farmacia farm = controller2.getFarmaciaProxima(graph, enderecoCliente);
        int nif = farm.getNIF();
        Map<Produto, Integer> stock = controller.getListStock(nif);
        int nifFarmacia1 = nif;

        boolean bool = true;
        
        System.out.println("FARMACIA MAIS PERTO CLIENTE: "+nif);
        while (bool) {

            int id = LER.nextInt();

            if (id == 0) {
                break;
            }

            System.out.println("Introduza a quantidade que pretende comprar: ");

            int qntd = LER.nextInt();

            Produto prod = controller.getProdutoByID(id);
            System.out.println("Produto: " + prod);
            if (!controller.produtoEncomenda(nif, prod, qntd)) {
                int aux;
                if (stock.get(prod) == null) {
                    aux = 0;
                } else {
                    aux = stock.get(prod);
                }
                qntd = qntd - aux;
                Farmacia farm1 = controller2.getFarmaciaProxima(graph, controller.getEnderecoOrigem(nif));
                int nif1 = farm1.getNIF();
                System.out.println("FARMACIA + PERTO DA 1 FARMACIA: "+nif1);
                while (qntd > 0) {
                    System.out.println("Farmacia + proxima: " + farm1.toString());
                    if (controller.getListStock(nif1).containsKey(prod) && controller.getListStock(nif1).get(prod) >= qntd) {
                        System.out.println("1 if");
                        controller2.realizaPedido(controller2.getFarmaciaByNIF(nif1), controller2.getFarmaciaByNIF(nif), prod, qntd);
                        controller3.enviarNotaTransferencia(controller2.getFarmaciaByNIF(nif1), controller2.getFarmaciaByNIF(nif), prod, qntd);
                        controller2.enviaNotaEntrega(controller2.getFarmaciaByNIF(nif).getEmail(), controller2.getFarmaciaByNIF(nif1).getEmail());
                        controller.produtoEncomenda(nif1, prod, qntd);
                        qntd = 0;
                        break;

                    } else {
                        if (controller.getListStock(nif1).containsKey(prod) && controller.getListStock(nif1).get(prod) < qntd) {
                            System.out.println("2 if");
                            controller2.realizaPedido(controller2.getFarmaciaByNIF(nif1), controller2.getFarmaciaByNIF(nif), prod, qntd);
                            controller3.enviarNotaTransferencia(controller2.getFarmaciaByNIF(nif1), controller2.getFarmaciaByNIF(nif), prod, qntd);
                            controller2.enviaNotaEntrega(controller2.getFarmaciaByNIF(nif).getEmail(), controller2.getFarmaciaByNIF(nif1).getEmail());
                            qntd = qntd - controller.getListStock(nif1).get(prod);
                            controller.produtoEncomenda(nif1, prod, qntd);
                            Farmacia farm2 = controller2.getFarmaciaProxima(graph, controller.getEnderecoOrigem(nif1));
                            nif1 = farm2.getNIF();
                        }
                        if (!controller.getListStock(nif1).containsKey(prod)) {
                            System.out.println("3 if");
                            Farmacia farm2 = controller2.getFarmaciaProxima(graph, controller.getEnderecoOrigem(nif1));
                            nif1 = farm2.getNIF();
                            System.out.println("NIF: "+nif1);
                        }
                    }

                }
                if (graph.numVertices() == 0 && qntd > 0) {
                    System.out.println("Não havia a quantidade que pretende.");
                    String assunto = "Produto não disponível.";
                    String mensagem = "O produto não estava disponível na quantidade pretendida logo foi inserido a quantidade existente em stock.";
                    String email = UserSession.getInstance().getUser().getEmail();
                    controller.notificaCliente(email, assunto, mensagem);
                }
            }
            System.out.println("Introduza o id de um produto apresentado ou 0 para terminar.");
        }

        System.out.println("Lista de Produtos selecionados: ");

        for (Produto p : controller.getMapaEncomenda().keySet()) {
            System.out.println("Produto: " + p.getDesignacao() + " Quantidade: " + controller.getMapaEncomenda().get(p));
        }

        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Date date1 = new Date(System.currentTimeMillis());
            String dataInicio = formatter.format(date1);

            System.out.println("Deseja pagar com creditos? (S/N)");
            LER.nextLine();
            String credsC = LER.nextLine();

            if (credsC.equalsIgnoreCase("S") || credsC.equalsIgnoreCase("SIM")) {
                Data date = Data.dataAtual();
                double creditosData = controller.getCreditosData(date, controller.getPreco());

                if (controller.getCliente().getCreditos() < creditosData) {
                    System.out.println("Creditos insuficientes.");
                } else {
                    controller.removerCreditos(controller.getCliente().getClienteNIF(), creditosData);
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
