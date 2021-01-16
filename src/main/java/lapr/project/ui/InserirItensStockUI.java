package lapr.project.ui;

import lapr.project.controller.InserirItensStockController;
import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.Map;
import java.util.Scanner;
import lapr.project.controller.PedirItemFarmaciaController;
import lapr.project.data.EmailDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.TransferenciaDB;

public class InserirItensStockUI {

    public static final Scanner LER = new Scanner(System.in);

    InserirItensStockController controller;
    PedirItemFarmaciaController controller2;

    public InserirItensStockUI() {
        this.controller = new InserirItensStockController(new ProdutosDB());
        controller2 = new PedirItemFarmaciaController(new FarmaciaDB(),  new TransferenciaDB(), new EmailDB());
    }

    public void registaProduto() {
        System.out.println("NIF da farmácia onde enviar:");
        int nif = LER.nextInt();
        while (controller2.getFarmaciaByNIF(nif) == null) {
            System.out.println("Não existe farmácia com este nif. Por favor insira "
                    + "novamente.");
            nif = LER.nextInt();
        };

        Map<Produto, Integer> map = controller.getListaProdutos(nif);
        System.out.println("Stock da farmácia: ");
        for (Produto prod : map.keySet()) {
            System.out.println(prod.getDesignacao() + " " + map.get(prod));
        }

        System.out.println("--Registo de Novo Produto--");

        System.out.println("Introduza os dados relativos ao novo produto");
        System.out.println("Designação:");
        LER.nextLine();
        String desig = LER.nextLine();
        System.out.println("Peso:");
        double peso = LER.nextDouble();
        System.out.println("Preço Base:");
        double preco = LER.nextDouble();
        System.out.println("Nº de produtos a criar:");
        int qtd = LER.nextInt();

        Produto prod = controller.novoProduto(desig, peso, preco);

        System.out.println("--Produto Criado--");
        System.out.println(prod.getDesignacao());
        System.out.println(prod.getPeso());
        System.out.println(prod.getPrecoBase());
        System.out.println("Nº de produtos a adicionar" + qtd);

        System.out.println("Deseja adicionar ao stock da farmácia o produto criado? (S/N)");
        String confirm = LER.next();

        if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
            controller.registaProduto(prod, nif, qtd);
            System.out.println("\n\nProduto adicionado ao stock com sucesso!");
        } else {
            System.out.println("\n\nRegisto do produto cancelado!");
        }
    }
}
