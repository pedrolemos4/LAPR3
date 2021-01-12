package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.List;

public class AtualizarItensStockController {
    private final ProdutosDB produtosDB;

    public AtualizarItensStockController(ProdutosDB produtosDB){
        this.produtosDB = produtosDB;
    }

    public List<Produto> getListaProdutos() {
        return produtosDB.getLista();
    }

    public Produto getProdutoByID(int idProd) {
        Produto prod = produtosDB.getProdutoByID(idProd);
        return prod;
    }

    public void atualizarProduto(Produto prod) {
        produtosDB.atualizarProduto(prod);
    }
}
