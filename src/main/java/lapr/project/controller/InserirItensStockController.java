package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class InserirItensStockController {
    private final ProdutosDB produtosDB;

    public InserirItensStockController(ProdutosDB produtosDB){
        this.produtosDB = produtosDB;
    }

    public List<Produto> getListaProdutos() {
        return produtosDB.getListaProdutos();
    }

    public void novoProduto(String desig, double peso, double preco_base) {
        Produto prod = produtosDB.novoProduto(desig,peso,preco_base);
        registaProduto(prod);
    }

    public void registaProduto(Produto prod) {
        produtosDB.registaProduto(prod);
    }
}
