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
public class EstadoScooter {
    
    private int id_estado_scooter;
    private String designacao;

    /**
     * Constrói uma instância de estado da scooter com o id e a designação do estado
     * @param id_estado_scooter id do estado da scooter
     * @param designacao designação do estado da scooter
     */
    public EstadoScooter(int id_estado_scooter, String designacao){
        this.id_estado_scooter = id_estado_scooter;
        this.designacao = designacao;
    }

    /**
     * Constrói uma instância de estado da scooter com o id e designacao por omissão
     */
    public EstadoScooter(){
        this.id_estado_scooter = 0;
        this.designacao = "Indisponível";
    }

    public EstadoScooter(int id_estado_scooter) {
        this.id_estado_scooter = id_estado_scooter;
        if(this.id_estado_scooter==1){
            this.designacao="Disponível";
        } else{
            this.designacao="Indisponível";
        }
    }

    /**
     * Devolve a designação do estado da scooter
     * @return designação do estado da scooter
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Modifica o valor da designação do estado da scooter com o valor recebido por parâmetro
     * @param designacao nova designação do estado da scooter
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}

