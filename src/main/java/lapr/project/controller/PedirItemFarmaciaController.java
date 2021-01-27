package lapr.project.controller;

import lapr.project.data.EmailDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.TransferenciaDB;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Graph;
import lapr.project.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class PedirItemFarmaciaController {
    FarmaciaDB fdb;
    TransferenciaDB tdb;
    EmailDB emDB;
    
    /**
     * Constroi uma instancia de PedirItemFarmaciaController recebendo uma instancia de FarmaciaDB, TransferenciaDB e EmailDB
     * @param fdb instancia de FarmaciaDB
     * @param tdb instancia de TransferenciaDB
     * @param emDB instancia de EmailDB
     */
    public PedirItemFarmaciaController(FarmaciaDB fdb, TransferenciaDB tdb, EmailDB emDB){
        this.fdb = fdb;
        this.tdb = tdb;
        this.emDB=emDB;
    }
    
    /**
     * Devolve a farmacia recebendo o nif da farmacia
     * @param nif nif da farmacia
     * @return farmacia
     */
    public Farmacia getFarmaciaByNIF(int nif) {
        return fdb.getFarmaciaByNIF(nif);
    }
    
    /**
     * Verifica se pedido é realizado recebendo a farmacia de origem, a farmacia de destino, o produto e a quantidade
     * @param fOri farmacia de origem
     * @param fDest farmacia de destino
     * @param prod produto
     * @param quantidade quantidade do produto
     * @return true se pedido é realizado
     */
    public boolean realizaPedido(Farmacia fOri, Farmacia fDest, Produto prod, int quantidade) {
        return (tdb.realizaPedido(fOri,fDest,prod,quantidade)? (true) : (false));
    }
    
    /**
     * Devolve uma lista de farmacias recebendo o produto e a quantidade
     * @param p produto
     * @param quant quantidade do produto
     * @return lista de farmacia
     */
    public List<Farmacia> getListaFarmaciaByProduto(Produto p, int quant){
        return fdb.getLstFarmaciasByProdutos(p, quant);
    }
    
    /**
     * Devolve um grafo recebendo uma lista de farmacias
     * @return grafo
     */
    public Graph<Endereco, Double> generateGrafo(Graph<Endereco, Double> graph, List<Endereco> lst) {
        Graph<Endereco, Double> graph1 = fdb.generateGrafo(graph, lst);
        return graph1;
    }

    /**
     * Devolve o nif da farmacia mais proxima recebendo um grafo e o nif da farmacia
     * @param generateGrafo grafo
     * @param end endereço da farmacia
     * @param list
     * @return nif da farmacia mais proxima
     */
    public Farmacia getFarmaciaProxima(Graph<Endereco, Double> generateGrafo, Endereco end, List<Endereco> list) {
        return fdb.getFarmaciaProxima(generateGrafo,end, list);
    }

    /**
     * Verifica se a nota de entrega é enviada
     * @param email o email de origem
     * @param email1 o email de destino
     * @return true se nota de entrega for enviada
     */
    public boolean enviaNotaEntrega(String email, String email1) { 
        return (emDB.sendEmail(email, email1, "Adicionar Stock.", "Confirmamos que os produtos enviados foram recebidos.")? (true) : (false));
    }
    
    /**
     * Devolve lista de farmacia 
     * @return lista de farmacias
     */
    public List<Farmacia> getLstFarmacias() {
        return new ArrayList<>(fdb.getLstFarmacias());
    }

}
