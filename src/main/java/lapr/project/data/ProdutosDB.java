package lapr.project.data;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Produto;

public class ProdutosDB {

    private Produto prod;
    private final DataHandler dataHandler;
    private List<Produto> lstProdutosEncomenda;
    private List<Produto> lstStock;

    private double peso;
    private double preco;

    public ProdutosDB() {
        this.dataHandler = DataHandler.getInstance();
        lstProdutosEncomenda = new ArrayList<>();
        lstStock = new ArrayList<>();
        peso = 0;
        preco = 0;
    }
    
    public List<Produto> getListaProdutos() {
        return lstProdutosEncomenda;
    }

    public int generateID() {
        return lstStock.size() + 1;
    }

    public boolean novoProduto(int id, String desig, double peso, double preco_base) {
        Produto prod1 = new Produto(id, desig, peso, preco_base);
        for(int i=0;i<lstStock.size();i++){
            if(lstStock.get(i).equals(prod1)){
                return true;
            }
        }
        return false;
    }

    public boolean validaProduto(Produto prod) {
        if (prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0) {
            return false;
        }
        return true;
    }

    public void addListaProds(Produto prod) {
        if (validaProduto(prod) == true) {
            preco = preco + prod.getPrecoBase();
            peso = peso + prod.getPeso();
            lstProdutosEncomenda.add(prod);
            lstStock.remove(prod);
        }
    }

    public void addProdutos(List<Produto> lprods){
        lstStock.addAll(lprods);
    }
    
    public boolean validaListaProdutos(List<Produto> lprods) {
        boolean check = true;
        for (Produto prod : lprods) {
            if(prod.getDesignacao() == null || prod.getPeso() < 0 || prod.getPrecoBase() < 0){
                check = false;
            } else {
                check = true;
            }
        }
        return check;
    }

    /**
     * Lista do stock
     * @return 
     */
    public List<Produto> getLista(){
        return lstStock;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public double getPeso(){
        return peso;
    }

    public void registaProduto(Produto prod) {
        if(novoProduto(prod.getId(), prod.getDesignacao(), prod.getPeso(), prod.getPrecoBase())==true){
            addListaProds(prod);
        }
    }
    
}
