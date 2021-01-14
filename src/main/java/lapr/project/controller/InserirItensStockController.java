package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.List;
import java.util.Map;

public class InserirItensStockController {
    private final ProdutosDB produtosDB;

    public InserirItensStockController(ProdutosDB produtosDB){
        this.produtosDB = produtosDB;
    }

    public Map<Produto, Integer> getListaProdutos(int nif) {
        return produtosDB.getLista(nif);
    }

    public Produto novoProduto(String desig, double peso, double precoBase) {
        return produtosDB.novoProduto(desig,peso,precoBase);
    }

    public boolean registaProduto(Produto prod, int farm) {
        return (produtosDB.registaProduto(prod, farm)? true : false);
    }
}
