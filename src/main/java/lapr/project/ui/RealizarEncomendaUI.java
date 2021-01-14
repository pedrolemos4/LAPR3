/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lapr.project.controller.PedirItemFarmaciaController;
import lapr.project.controller.RealizaEncomendaController;
import lapr.project.data.*;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
import lapr.project.model.Farmacia;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import lapr.project.utils.Data;

/**
 *
 * @author pedro
 */
public class RealizarEncomendaUI {

    public static final Scanner LER = new Scanner(System.in);

    RealizaEncomendaController controller;
    PedirItemFarmaciaController controller2;
    FarmaciaDB fdb = new FarmaciaDB();
    TransferenciaDB tdb = new TransferenciaDB();

    public RealizarEncomendaUI() {
        controller = new RealizaEncomendaController(new ProdutosDB(), new EncomendaDB(), new ReciboDB(), new ClienteDB(), new EmailDB());
        controller2 = new PedirItemFarmaciaController(fdb, tdb);
    }

    public void introduzEncomenda() throws SQLException {

        System.out.println("Insira o NIF da fármácia que pretende encomendar os produtos: ");

        int nif = LER.nextInt();
        List<Produto> stock = controller2.getFarmaciaByNIF(nif).getStock();
        
        System.out.println("Lista de produtos disponível: ");
        
        for (Produto p : stock) {
            System.out.println(p);
        }
        
        int nif1=-1;
        while (LER.hasNextLine()) {
            System.out.println("Introduza o id de um produto apresentado: ");
            int id = LER.nextInt();
            System.out.println("Introduza a quantidade que pretende: ");
            int qntd = LER.nextInt();
            Produto prod = controller.getProdutoByID(id);

            if (controller.produtoEncomenda(nif, prod, qntd) == false) {
                while (true) {
                    System.out.println("A farmácia não tem o produto que deseja na quantidade pretendida! Por favor selecione outra farmácia para fazer o pedido:");
                    // TEM QUE SER PELO grafo 
                    if (controller2.getFarmaciaByNIF(nif1).getStock().contains(prod)) {
                        controller2.realizaPedido(controller2.getFarmaciaByNIF(nif1), controller2.getFarmaciaByNIF(nif1), prod, qntd);
                        //falta adicionar a lista de produtos da encomenda da 2 farmacia
                        break;
                    }
                }
            }
            
        }

        System.out.println("Lista de Produtos selecionados: ");

        for (Produto p : controller.getMapaEncomenda().keySet()) {
            System.out.println("Produto: "+p.getDesignacao()+" Quantidade: "+controller.getMapaEncomenda().get(p));
        }

        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {

            Data date = Data.dataAtual();

            System.out.println("Deseja pagar com creditos? (S/N)");
            LER.nextLine();
            String credsC = LER.nextLine();

            if (credsC.equalsIgnoreCase("S") || credsC.equalsIgnoreCase("SIM")) {

                double creditosData = controller.getCreditosData(date, controller.getPreco());
                String email = UserSession.getInstance().getUser().getEmail();

                if (controller.getCliente().getCreditos() < creditosData) {
                    System.out.println("Creditos insuficientes.");
                } else {
                    controller.removerCreditos(email, creditosData);
                    System.out.println("Foram retirados: " + creditosData + " creditos.");
                }

                Encomenda enc = new Encomenda(controller.getCliente().getNIF(), date.toString(), controller.getPreco(), controller.getPeso(), 0.6, 1);

                Map<Produto, Integer> mapaEncomenda = controller.getMapaEncomenda();

                controller.registaEncomenda(enc);

                for (Produto p : mapaEncomenda.keySet()) {
                    controller.registaEncomendaProduto(enc, p);
                }
                
                if(nif1==-1){
                    controller.removerProdutosEncomenda(/*mandar o nif da 1 farmacia que o cliente insere*/mapaEncomenda); //remover por farmacia
                }else{
                    controller.removerProdutosEncomenda(/*mandar o nif das 2 farmacias que o cliente insere*/mapaEncomenda); //remover por farmacia
                }
                
                double precoTotal = controller.getPrecoTotal(enc.getTaxa());

                Recibo rec = new Recibo(controller.getCliente().getNIF(), precoTotal, date.toString(), enc.getId());
                rec.setLst(mapaEncomenda);

                for (Produto p : mapaEncomenda.keySet()) {
                    controller.novoRecibo(rec, p);
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

            }
        }
    }

}
