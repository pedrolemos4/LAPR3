package lapr.project.controller;

import lapr.project.data.ProdutosDB;
import lapr.project.model.Produto;

import java.util.Map;

public class AtualizarItensStockController {
    private final ProdutosDB produtosDB;
    
    /**
     * Constroi uma instancia de AtualizarEstafetaController recebendo uma instancia de ProdutosDB
     * @param produtosDB instancia de ProdutosDB
     */
    public AtualizarItensStockController(ProdutosDB produtosDB){
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
     * Devolve um produto recebendo por parametro o seu idProduto
     * @param idProd id do produto
     * @return produto
     */
    public Produto getProdutoByID(int idProd) {
        return produtosDB.getProdutoByID(idProd);
    }
    
    /**
     * Verifica se produto é atualizaddo recebendo o produto por parametro
     * @param prod o produto que vai ser atualizado ou nao
     * @return true se os atributos do produto foram atualizados
     */
    public boolean atualizarProduto(Produto prod) {
        return (produtosDB.atualizarProduto(prod)? (true) : (false));
    }
}
