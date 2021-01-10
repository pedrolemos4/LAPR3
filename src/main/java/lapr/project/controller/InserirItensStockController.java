package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.List;

public class InserirItensStockController {
    private final ProdutosDB produtosDB;

    public InserirItensStockController(){
        this.produtosDB = new ProdutosDB();
    }

    public List<Produto> getListaProdutos() {
        return produtosDB.getListaProdutos();
    }

    public Produto novoProduto(String desig, double peso, double preco_base) {
        return produtosDB.novoProduto(desig,peso,preco_base);
    }

    public void registaProduto(Produto prod) {
        produtosDB.registaProduto(prod);
    }
}
