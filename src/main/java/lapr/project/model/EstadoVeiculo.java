/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Tiago
 */
public class EstadoVeiculo {

    private final int idEstadoVeiculo;
    private String designacao;

    /**
     * Constrói uma instância de estado do veiculo com o id e a designação do
     * estado
     *
     * @param idEstadoVeiculo id do estado da veiculo
     * @param designacao designação do estado da veiculo
     */
    public EstadoVeiculo(int idEstadoVeiculo, String designacao) {
        this.idEstadoVeiculo = idEstadoVeiculo;
        this.designacao = designacao;
    }

    /**
     * Constrói uma instância de estado do veiculo com o id e designacao por
     * omissão
     */
    public EstadoVeiculo() {
        this.idEstadoVeiculo = 0;
        this.designacao = "Indisponível";
    }

    /**
     * Constroi uma instância de estado do veiculo com o id
     *
     * @param idEstadoVeiculo
     */
    public EstadoVeiculo(int idEstadoVeiculo) {
        this.idEstadoVeiculo = idEstadoVeiculo;
        if (this.idEstadoVeiculo == 1) {
            this.designacao = "Disponível";
        } else {
            this.designacao = "Indisponível";
        }
    }

    /**
     * Constrói uma instância de estado do veículo recebendo outro estado por parâmetro
     * @param outroEstado novo estado
     */
    public EstadoVeiculo(EstadoVeiculo outroEstado) {
        this.idEstadoVeiculo = outroEstado.idEstadoVeiculo;
        this.designacao = outroEstado.designacao;
    }

    /**
     * Devolve o id de estado do veiculo
     *
     * @return id de estado do veiculo
     */
    public int getId() {
        return idEstadoVeiculo;
    }

    /**
     * Devolve a designação do estado do veiculo
     *
     * @return designação do estado do veiculo
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Modifica o valor da designação do estado do veiculo com o valor recebido
     * por parâmetro
     *
     * @param designacao nova designação do estado do veiculo
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
