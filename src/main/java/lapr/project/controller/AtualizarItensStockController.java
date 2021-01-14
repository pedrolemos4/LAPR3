package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.List;
import java.util.Map;

public class AtualizarItensStockController {
    private final ProdutosDB produtosDB;

    public AtualizarItensStockController(ProdutosDB produtosDB){
        this.produtosDB = produtosDB;
    }

    public Map<Produto, Integer> getListaProdutos(int nif) {
        return produtosDB.getLista(nif);
    }

    public Produto getProdutoByID(int idProd) {
        return produtosDB.getProdutoByID(idProd);
    }

    public boolean atualizarProduto(Produto prod) {
        return (produtosDB.atualizarProduto(prod)? true : false);
    }
}
