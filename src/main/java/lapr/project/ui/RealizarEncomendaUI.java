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
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Produto;
import lapr.project.model.Recibo;
import static lapr.project.ui.RegistarEntregaUI.LER;

/**
 *
 * @author pedro
 */
public class RealizarEncomendaUI {

    public static final Scanner LER = new Scanner(System.in);

    RealizaEncomendaController controller;

    public RealizarEncomendaUI() {
        controller = new RealizaEncomendaController();
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
        
        for(int i=0;i<controller.getListaProdutoEncomenda().size();i++){
            System.out.println(controller.getListaProdutoEncomenda().get(i)+" "+controller.getListQuantidade().get(i));
        }
        
        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            
            Date date = new Date(System.currentTimeMillis());
            
            Encomenda enc = new Encomenda(controller.getNifCliente(), date.toString(), controller.getPreco(), controller.getPeso(), 0.6, 1);
            
            List<Produto> lst = controller.getListaProdutoEncomenda();
            List<Integer> listQuantidade = controller.getListQuantidade();
            
            controller.registaEncomenda(enc);
            
            for(Produto p : lst){
                controller.registaEncomendaProduto(enc, p);
            }
            
            controller.removerProdutosEncomenda(lst, listQuantidade);
            
            double precoTotal = controller.getPrecoTotal(enc.getTaxa());
            
            Recibo rec = new Recibo(controller.getNifCliente(), precoTotal, date.toString(), enc.getId());
            rec.setLst(lst);
            
            for(Produto p : lst){
                controller.novoRecibo(rec, p);
            }
            
            System.out.println("Data do Recibo:");
            System.out.println(rec.getData());
            System.out.println("Preco Total:");
            System.out.println(precoTotal);
            System.out.println("Lista de Produtos:");
            for(int i=0; i<lst.size(); i++){
                System.out.println(lst.get(i).getDesignacao() + " " + lst.get(i).getPrecoBase());
            }
            
            System.out.println("\n\nEncomenda adicionada com sucesso'");
        }
    }

}
