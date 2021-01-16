/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import lapr.project.controller.PedirItemFarmaciaController;
import lapr.project.controller.RealizaEncomendaController;
import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Farmacia;
import lapr.project.model.Graph;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import lapr.project.utils.Data;

/**
 *
 * @author pedro
 */
public class RealizarEncomendaUI {

    public static final Scanner LER = new Scanner(System.in);
    public static final RegistarClienteUI rcUI = new RegistarClienteUI();

    RealizaEncomendaController controller;
    PedirItemFarmaciaController controller2;

    public RealizarEncomendaUI() {
        controller = new RealizaEncomendaController(new ProdutosDB(), new EncomendaDB(), new ReciboDB(), new ClienteDB(), new EmailDB());
        controller2 = new PedirItemFarmaciaController(new FarmaciaDB(), new TransferenciaDB(), new EmailDB());
    }

    public void introduzEncomenda() throws SQLException, ParseException, ClassNotFoundException {

        System.out.println("Insira o NIF da farmácia que pretende encomendar os produtos: ");
        List<Farmacia> lstFarmacias = controller2.getLstFarmacias();
        
        if(lstFarmacias.isEmpty()){
            System.out.println("Não existem farmácias");
            rcUI.menuCliente();
        }
        
        boolean aux = false;
        for (Farmacia f : lstFarmacias) {
            System.out.println(f.toString());
        }

        int nif = LER.nextInt();
        if(controller2.getFarmaciaByNIF(nif) == null){
            while (controller2.getFarmaciaByNIF(nif) == null) {
            System.out.println("Não existe farmácia com este nif. Por favor insira "
                    + "novamente.");
            nif = LER.nextInt();
            }
        }

        Map<Produto, Integer> stock = controller.getListStock(nif);
        while (stock.keySet().isEmpty()) {
            System.out.println("Neste momento esta farmácia não tem produtos em stock."
                    + " Insira o nif de outra farmácia.");
            nif = LER.nextInt();
            stock = controller.getListStock(nif);
        }

        System.out.println("Lista de produtos disponível: ");

        for (Produto p : stock.keySet()) {
            System.out.println(p);
        }

        int nif1;

        while (LER.hasNextLine()) {
            System.out.println("Introduza o id de um produto apresentado ou 0 para terminar ");
            int id = LER.nextInt();
            if (id == 0) {
                break;
            }
            System.out.println("Introduza a quantidade que pretende mesmo não tendo a quantidade que pretende: ");
            int qntd = LER.nextInt();
            Produto prod = controller.getProdutoByID(id);
            if (controller.produtoEncomenda(nif, prod, qntd) == false) {
                List<Farmacia> farms = controller2.getListaFarmaciaByProduto(prod, qntd);
                while (qntd > 0) {
                    if(farms.isEmpty()){
                        break;
                    }
                    Graph<Farmacia, Double> generateGrafo = controller2.generateGrafo(farms);
                    nif1 = controller2.getFarmaciaProxima(generateGrafo, nif);
                    if (controller.getListStock(nif1).containsKey(prod) && controller.getListStock(nif1).containsValue(qntd)) {
                        controller2.realizaPedido(controller2.getFarmaciaByNIF(nif), controller2.getFarmaciaByNIF(nif1), prod, qntd);
                        controller.produtoEncomenda(nif1, prod, qntd);
                        qntd = 0;
                        break;

                    }
                    if (controller.getListStock(nif1).containsKey(prod) && !controller.getListStock(nif1).containsValue(qntd)) {
                        controller2.realizaPedido(controller2.getFarmaciaByNIF(nif), controller2.getFarmaciaByNIF(nif1), prod, controller.getListStock(nif1).get(prod));
                        controller2.enviaNotaEntrega(controller2.getFarmaciaByNIF(nif).getEmail(), controller2.getFarmaciaByNIF(nif1).getEmail());
                        qntd = qntd - controller.getListStock(nif1).get(prod);
                        controller.produtoEncomenda(nif1, prod, qntd);
                        farms.remove(controller2.getFarmaciaByNIF(nif1));
                    }
                }
                if (qntd > 0) {
                    System.out.println("Não havia a quantidade que pretende.");
                    String assunto = "Produto não disponível.";
                    String mensagem = "O produto não estava disponível na quantidade pretendida logo foi inserido a quantidade existente em stock.";
                    String email = UserSession.getInstance().getUser().getEmail();
                    controller.notificaCliente(email, assunto, mensagem);
                }
            }

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
            
            Encomenda enc = new Encomenda(controller.getCliente().getClienteNIF(), dataInicio,
                    controller.getPreco(), controller.getPeso(), 0.6, 1);

            Map<Produto, Integer> mapaEncomenda = controller.getMapaEncomenda();
            
            controller.registaEncomenda(enc);
            
            for (Produto p : mapaEncomenda.keySet()) {
                controller.registaEncomendaProduto(enc, p, mapaEncomenda.get(p));
            }
            
            for(Produto p1 : mapaEncomenda.keySet()){
                controller.removerProdutosEncomenda(p1, nif, mapaEncomenda.get(p1), stock.get(p1));
            }
            
            double precoTotal = controller.getPrecoTotal(mapaEncomenda,enc.getTaxa());

            Recibo rec = new Recibo(controller.getCliente().getClienteNIF(), precoTotal,
                    dataInicio, enc.getId());
            rec.setLst(mapaEncomenda);

            controller.geraCreditos(controller.getCliente(),precoTotal);
            
            controller.registaRecibo(rec);
            
            String assunto = "Recibo.";
            String mensagem = rec.toString();
            System.out.println("EMAIL NOTIFICA: "+UserSession.getInstance().getUser().getEmail());
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

            System.out.println("\n\nEncomenda adicionada com sucesso'");
            rcUI.menuCliente();
        }
    }

}
