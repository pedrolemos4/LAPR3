package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.List;

public class InserirItensStockController {
    private final ProdutosDB produtosDB;

    public InserirItensStockController(ProdutosDB produtosDB){
        this.produtosDB = produtosDB;
    }

    public List<Produto> getListaProdutos() {
        return produtosDB.getListaProdutos();
    }

    public Produto novoProduto(String desig, double peso, double preco_base) {
        return produtosDB.novoProduto(desig,peso,preco_base);
    }

    public boolean registaProduto(Produto prod) {
        return produtosDB.registaProduto(prod);
    }
}
