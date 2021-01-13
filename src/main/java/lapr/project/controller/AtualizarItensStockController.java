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
        return produtosDB.getProdutoByID(idProd);
    }

    public boolean atualizarProduto(Produto prod) {
        return (produtosDB.atualizarProduto(prod)? true : false);
    }
}
