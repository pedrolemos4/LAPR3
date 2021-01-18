package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.Map;

public class InserirItensStockController {
    private final ProdutosDB produtosDB;
    
    /**
     * Constroi uma instancia de InserirItensStockController recebendo uma instancia de ProdutosDB
     * @param produtosDB instancia de ProdutosDB
     */
    public InserirItensStockController(ProdutosDB produtosDB){
        this.produtosDB = produtosDB;
    }
    
    /**
     * Devolve um mapa cuja key é um produto e o value é a sua quantidade recebendo por parametro o nif da farmacia
     * @param nif nif da farmacia
     * @return mapa cuja key é um produto e o value é a sua quantidade
     */
    public Map<Produto, Integer> getListaProdutos(int nif) {
        return produtosDB.getLista(nif);
    }
    
    /**
     * Devolve um produto recebendo designacao, peso e precoBase
     * @param desig designacao do produto
     * @param peso peso do produto
     * @param precoBase preco base do produto
     * @return produto
     */
    public Produto novoProduto(String desig, double peso, double precoBase) {
        return produtosDB.novoProduto(desig,peso,precoBase);
    }
    
    /**
     * Verifica se o produto foi registado recebendo por parametro o produto, o nif da farmacia e a quantidade
     * @param prod o produto a ser registado
     * @param farm nif da farmacia
     * @param qtd quantidade do produto a ser registada
     * @return true se o produto foi registado
     */
    public boolean registaProduto(Produto prod, int farm, int qtd) {
        return (produtosDB.registaProduto(prod, farm, qtd)? (true) : (false));
    }
}
