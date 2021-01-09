/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RealizaEncomendaController;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Produto;
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

    private void introduzEncomenda() {

        System.out.println("Lista de produtos: ");
        List<Produto> list = controller.getListStock();

        for (Produto s : list) {
            System.out.println(s);
        }

        while (LER.hasNextLine()) {
            System.out.println("Introduza o id de um produto apresentado: ");
            int id = LER.nextInt();
            Produto prod = controller.getProdutoByID(id);
            controller.produtoEncomenda(prod);
        }

        System.out.println(controller.getListaEncomenda().toString());

        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            
            Date date = new Date(System.currentTimeMillis());
            
            Encomenda enc = new Encomenda(controller.getNifCliente(), date.toString(), controller.getPreco(), controller.getPreco(), 1.0, 1);

            controller.registaEncomenda(enc);

            System.out.println("\n\nEntrega adicionada com sucesso'");
        }
    }

}