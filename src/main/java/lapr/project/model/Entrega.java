package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author beatr
 */
public class Entrega {
    
    private int idEntrega;
    private String dataInicio;
    private String dataFim;
    private int idVeiculo;
    private int idEstafeta;
    private double pesoEntrega;
    
    /**
     * Constrói uma instância de Entrega recebendo a data de inicio da entrega, a
     * data final da entrega, o veiculo associado à entrega,
     * o estafeta asscociado à entrega.
     * 
     * @param dataInicio data de incio da entrega
     * @param dataFim data de fim da entrega
     * @param idVeiculo veiculo associado à entrega
     * @param idEstafeta estafeta associado à entrega
     */
    public Entrega(String dataInicio, String dataFim, int idVeiculo, int idEstafeta, double pesoEntrega) {
        this.idEntrega = 0;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idVeiculo = idVeiculo;
        this.idEstafeta = idEstafeta;
        this.pesoEntrega = pesoEntrega;
    }
    
    /**
     * Constrói uma instância de Entrega com a entrega por omissão.
     */
    public Entrega() {
        this.idEntrega = 0;
        this.dataInicio = null;
        this.dataFim = null;
        this.idVeiculo = 0;
        this.idEstafeta = 0;
        this.pesoEntrega = 0;
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
     * Devolve a veículo associada à entrega
     * @return veículo asscoiada à entrega
     */
    public int getIdVeiculo() {
        return idVeiculo;
    }
    
    /**
     * Modifica a veiculo associada à entrega
     * @param idVeiculo a nova veiculo associada à entrega
     */
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
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
     * @param idEstafeta o novo estafeta associado à entrega
     */
    public void setEstafeta(int idEstafeta) {
        this.idEstafeta = idEstafeta;
    }
    
    /**
     * Devolve o peso de entrega
     * @return peso de entrega
     */
    public double getPesoEntrega() {
        return pesoEntrega;
    }
    
    /**
     * Modifica o peso de entrega
     * @param pesoEntrega o novo peso de entrega
     */
    public void setPesoEntrega(double pesoEntrega) {
        this.pesoEntrega = pesoEntrega;
    }

    /**
     * Devolve o hash code da instância de entrega
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(idEntrega, dataInicio, dataFim, idVeiculo, idEstafeta, pesoEntrega);
    }

    /**
     * Compara o obj com a instância criada da Entrega
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrega other = (Entrega) obj;
        if (this.idEntrega != other.idEntrega) {
            return false;
        }
        if (this.idVeiculo != other.idVeiculo) {
            return false;
        }
        if (this.idEstafeta != other.idEstafeta) {
            return false;
        }
        if (Double.doubleToLongBits(this.pesoEntrega) != Double.doubleToLongBits(other.pesoEntrega)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        return Objects.equals(this.dataFim, other.dataFim);
    }
    
    /**
     * Devolve a descrição textual da entrega
     * @return descrição textual da entrega
     */
    @Override
    public String toString() {
        return "Entrega{" + "idEntrega=" + idEntrega + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", idVeiculo=" + idVeiculo + ", idEstafeta=" + idEstafeta + ", pesoEntrega=" + pesoEntrega + '}';
    }
            
}
