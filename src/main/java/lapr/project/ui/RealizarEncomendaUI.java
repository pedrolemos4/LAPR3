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
import java.util.Scanner;
import lapr.project.controller.RealizaEncomendaController;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.data.ProdutosDB;
import lapr.project.data.ReciboDB;
import lapr.project.login.UserSession;
import lapr.project.model.Encomenda;
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

    public RealizarEncomendaUI() {
        controller = new RealizaEncomendaController(new ProdutosDB(), new EncomendaDB(), new ReciboDB(), new ClienteDB(), new EmailDB());
    }

    public void introduzEncomenda() throws SQLException {
        System.out.println("Lista de produtos disponível: ");

        List<Produto> list = controller.getListStock();

        for (Produto s : list) {
            System.out.println(s);
        }

        while (LER.hasNextLine()) {
            System.out.println("Introduza o id de um produto apresentado: ");
            int id = LER.nextInt();
            System.out.println("Introduza a quantidade que pretende: ");
            int qntd = LER.nextInt();
            Produto prod = controller.getProdutoByID(id);
            controller.produtoEncomenda(prod, qntd);
        }

        System.out.println("Lista de Produtos: ");

        for (int i = 0; i < controller.getListaProdutoEncomenda().size(); i++) {
            System.out.println(controller.getListaProdutoEncomenda().get(i) + " " + controller.getListQuantidade().get(i));
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
                }else{
                    controller.removerCreditos(email, creditosData);
                    System.out.println("Foram retirados: "+creditosData+" creditos.");
                }

                Encomenda enc = new Encomenda(controller.getCliente().getNIF(), date.toString(), controller.getPreco(), controller.getPeso(), 0.6, 1);

                List<Produto> lst = controller.getListaProdutoEncomenda();
                List<Integer> listQuantidade = controller.getListQuantidade();

                controller.registaEncomenda(enc);

                for (Produto p : lst) {
                    controller.registaEncomendaProduto(enc, p);
                }

                controller.removerProdutosEncomenda(lst, listQuantidade);

                double precoTotal = controller.getPrecoTotal(enc.getTaxa());

                Recibo rec = new Recibo(controller.getCliente().getNIF(), precoTotal, date.toString(), enc.getId());
                rec.setLst(lst);

                for (Produto p : lst) {
                    controller.novoRecibo(rec, p);
                }

                System.out.println("Data do Recibo:");
                System.out.println(rec.getData());
                System.out.println("Preco Total:");
                System.out.println(precoTotal);
                System.out.println("Lista de Produtos:");
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(lst.get(i).getDesignacao() + " " + lst.get(i).getPrecoBase());
                }

                System.out.println("\n\nEncomenda adicionada com sucesso'");

            }
        }
    }

}
