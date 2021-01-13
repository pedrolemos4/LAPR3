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
    private List<Encomenda> listEncomendas = new ArrayList<>();
    private int idScooter;
    private int idEstafeta;
    
    /**
     * Constrói uma instância de Entrega recebendo a data de inicio da entrega, a
     * data final da entrega, a scooter associada à entrega,
     * o estafeta asscociado à entrega.
     * 
     * @param dataInicio data de incio da entrega
     * @param dataFim data de fim da entrega
     * @param idScooter scooter associada à entrega
     * @param idEstafeta estafeta associado à entrega
     */
    public Entrega(String dataInicio, String dataFim, int idScooter, int idEstafeta) {
        this.idEntrega = 0;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.listEncomendas = new ArrayList<>();
        this.idScooter = idScooter;
        this.idEstafeta = idEstafeta;
    }
    
    /**
     * Constrói uma instância de Entrega com a entrega por omissão.
     */
    public Entrega() {
        this.idEntrega = 0;
        this.dataInicio = null;
        this.dataFim = null;
        this.listEncomendas = null;
        this.idScooter = 0;
        this.idEstafeta = 0;
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
        return new ArrayList<>(listEncomendas);
    }
    
    /**
     * Modifica a lista de encomendas da entrega
     * @param listEncomendas a nova lista de encomendas da entrega
     */
    public void setListEncomendas(List<Encomenda> listEncomendas) {
        this.listEncomendas = new ArrayList<>(listEncomendas);
    }
    
    /**
     * Devolve a scooter associada à entrega
     * @return scooter asscoiada à entrega
     */
    public int getIdScooter() {
        return idScooter;
    }
    
    /**
     * Modifica a scooter associada à entrega
     * @param idScooter a nova scooter associada à entrega
     */
    public void setScooter(int idScooter) {
        this.idScooter = idScooter;
    }
    
    /**
     * Devolve o estafeta associado à entrega
     * @return estafeta associado à entrega
     */
    public int getidEstafeta() {
        return idEstafeta;
    }
    
    /**
     * Modifica o estafeta associado à entrega
     * @param estafeta o novo estafeta associado à entrega
     */
    public void setEstafeta(int idEstafeta) {
        this.idEstafeta = idEstafeta;
    }
       
}
