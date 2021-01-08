package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beatr
 */
public class Entrega {
    
    private int idEntrega;
    private String dataInicio;
    private String dataFim;
    private List<Encomenda> listEncomendas;
    private Scooter scooter;
    private Estafeta estafeta;
    
    /**
     * Constrói uma instância de Entrega recebendo a data de inicio da entrega, a
     * data final da entrega, a lista de encomendas da entrega, a scooter associada à entrega,
     * o estafeta asscociado à entrega.
     * 
     * @param dataInicio data de incio da entrega
     * @param dataFim data de fim da entrega
     * @param listEncomendas lista de encomendas da entrega
     * @param scooter scooter associada à entrega
     * @param estafeta estafeta associado à entrega
     */
    public Entrega(String dataInicio, String dataFim, List<Encomenda> listEncomendas, Scooter scooter, Estafeta estafeta) {
        this.idEntrega = 0;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.listEncomendas = new ArrayList<>();
        this.scooter = scooter;
        this.estafeta = estafeta;
    }
    
    /**
     * Constrói uma instância de Entrega com a entrega por omissão.
     */
    public Entrega() {
        this.idEntrega = 0;
        this.dataInicio = null;
        this.dataFim = null;
        this.listEncomendas = null;
        this.scooter = null;
        this.estafeta = null;
    }
    
    /**
     * Devolve o id da entrega
     * @return id da entrega
     */
    public int getIdEntrega() {
        return idEntrega;
    }
    
    /**
     * Modifica o id da entrega
     * @param idEntrega o novo id da entrega
     */
    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }
    
    /**
     * Devolve a data de inicio da entrega
     * @return data de inicio da entrega
     */
    public String getDataInicio() {
        return dataInicio;
    }
    
    /**
     * Modifica a data de inicio da entrega
     * @param dataInicio a nova data de inicio de entrega
     */
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    /**
     * Devolve a data de fim da entrega
     * @return data de fim da entrega
     */
    public String getDataFim() {
        return dataFim;
    }
    
    /**
     * Modifica a data de fim da entrega
     * @param dataFim a nova data de fim da entrega
     */
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
    
    /**
     * Devolve a lista de encomendas da entrega
     * @return lista de encomendas da entrega
     */
    public List<Encomenda> getListEncomendas() {
        return listEncomendas;
    }
    
    /**
     * Modifica a lista de encomendas da entrega
     * @param listEncomendas a nova lista de encomendas da entrega
     */
    public void setListEncomendas(List<Encomenda> listEncomendas) {
        this.listEncomendas = listEncomendas;
    }
    
    /**
     * Adiciona à lista de encomendas da entrega uma entrega
     * @param enc a encomenda a ser adicionada à lista
     */
    public void addListEncomendas(Encomenda enc) {
        this.listEncomendas.add(enc);
    }
    
    /**
     * Devolve a scooter associada à entrega
     * @return scooter asscoiada à entrega
     */
    public Scooter getScooter() {
        return scooter;
    }
    
    /**
     * Modifica a scooter associada à entrega
     * @param scooter a nova scooter associada à entrega
     */
    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }
    
    /**
     * Devolve o estafeta associado à entrega
     * @return estafeta associado à entrega
     */
    public Estafeta getEstafeta() {
        return estafeta;
    }
    
    /**
     * Modifica o estafeta associado à entrega
     * @param estafeta o novo estafeta associado à entrega
     */
    public void setEstafeta(Estafeta estafeta) {
        this.estafeta = estafeta;
    }
       
}
