package lapr.project.ui;

import lapr.project.controller.InserirItensStockController;
import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.List;
import java.util.Scanner;

public class InserirItensStockUI {
    public static final Scanner LER = new Scanner(System.in);

    InserirItensStockController controller;

    public InserirItensStockUI() {
        this.controller = new InserirItensStockController(new ProdutosDB());
    }

    public void registaProduto() {
        System.out.println("Stock da farmácia: ");
        List<Produto> lp = controller.getListaProdutos();

        for(Produto prod : lp){
            System.out.println(prod);
        }

        System.out.println("--Registo de Novo Produto--");

        System.out.println("Introduza os dados relativos ao novo produto");
        System.out.println("Designação:");
        String desig = LER.nextLine();
        System.out.println("Peso:");
        double peso = LER.nextDouble();
        System.out.println("Preço Base:");
        double preco = LER.nextDouble();

        Produto prod = controller.novoProduto(desig,peso,preco);

        System.out.println("--Produto Criado--");
        System.out.println(prod.getDesignacao());
        System.out.println(prod.getPeso());
        System.out.println(prod.getPrecoBase());

        System.out.println("Deseja adicionar ao stock o produto criado? (S/N)");
        String confirm = LER.next();

        if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
            controller.registaProduto(prod);
            System.out.println("\n\nProduto adicionado ao stock com sucesso!");
        }else{
            System.out.println("\n\nRegisto do produto cancelado!");
        }
    }
}
