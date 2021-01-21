package lapr.project.ui;

import java.sql.SQLException;
import lapr.project.controller.AtualizarItensStockController;
import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.Map;
import java.util.Scanner;

public class AtualizarItensStockUI {

    public static final Scanner LER = new Scanner(System.in);

    AtualizarItensStockController controller;

    public AtualizarItensStockUI() {
        this.controller = new AtualizarItensStockController(new ProdutosDB());
    }

    public void atualizarProduto() throws SQLException {
        System.out.println("NIF da farmácia onde enviar:");
        int farm = LER.nextInt();

        System.out.println("Stock da farmácia:");
        Map<Produto, Integer> map = controller.getListaProdutos(farm);

        for (Produto prod : map.keySet()) {
            System.out.println("Id produto: \t" + prod.getId()
                    + "\nDesignação: \t" + prod.getDesignacao()
                    + "\nQuantidade: \t" + map.get(prod));
        }

        System.out.println("Insira o id do produto a atualizar:");
        int id = LER.nextInt();

        Produto produto = controller.getProdutoByID(id);

        if (!controller.getListaProdutos(farm).containsKey(produto)) {
            System.out.println("O produto não existe!");
        } else {
            System.out.println("--Atualização do Produto--");

            System.out.println("Introduza os novos dados relativos ao produto");
            System.out.println("Designação:");
            LER.nextLine();
            String desig = LER.nextLine();
            System.out.println("Peso:");
            double peso = LER.nextDouble();
            System.out.println("Preço Base:");
            double preco = LER.nextDouble();

            Produto newProd = new Produto(desig, peso, preco);

            System.out.println("--Novos Dados do Produto--");
            System.out.println(newProd.getDesignacao());
            System.out.println(newProd.getPeso());
            System.out.println(newProd.getPrecoBase());
            System.out.println("Deseja atualizar os dados do produto? (S/N)");
            String confirm = LER.next();

            if (confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")) {
                System.out.println("Produto id: " + produto.getId());
                produto.setDesignacao(desig);
                produto.setPeso(peso);
                produto.setPrecoBase(preco);
                controller.atualizarProduto(produto);
                System.out.println("\n\nProduto atualizado com sucesso!");
            } else {
                System.out.println("\n\nAtualização do produto cancelada!");
            }
        }
    }
}
