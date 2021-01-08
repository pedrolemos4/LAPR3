package lapr.project.data;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Produto;

public class ProdutosDB {
   
    private Produto prod;
    private final DataHandler dataHandler;
    private List<Produto> lstProdutosEncomenda;

    public ProdutosDB() {
        this.dataHandler = DataHandler.getInstance();
        lstProdutosEncomenda = new ArrayList<>();
    }
    
    /*prod = novoProduto();
    */
    public List<Produto> getListaProdutos() {
        return lstProdutosEncomenda;
    }

    public void generateID() {

    }

    public void novoProduto(int id, String desig, double peso, double preco_base) {
        prod = new Produto (desig, peso, preco_base);
    }

    public boolean validaProduto(Produto prod) {
        if(prod.getDesignacao()==null || prod.getPeso() < 0 || prod.getPrecoBase() < 0){
            return false;
        }
        return true;
    }

    public void addListaProds(Produto prod) {
        if(validaProduto(prod)==true){
            lstProdutosEncomenda.add(prod);
        }
    }

    public void addProdutos(List<Produto> lprods) {
        
    }

    public void validaListaProdutos(List<Produto> lprods) {

    }
    
    //    /**
//     * Lista do stock
//     * @return 
//     */
//    public List<Produto> getLista(){
//        
//    }
}
