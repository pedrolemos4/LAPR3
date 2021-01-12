/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Estacionamento;

/**
 *
 * @author josep
 */
public class InserirEstacionamentosController {

    private final EstacionamentosDB estacionamentoDB;
    private final ParqueDB parqueDB;

    /**
     * Cria instância do controlador InserirEstacionamentosController
     *
     */
    public InserirEstacionamentosController() {
        this.estacionamentoDB = new EstacionamentosDB();
        this.parqueDB = new ParqueDB();
    }

    /**
     * Retorna lista de todos os estacionamentos
     *
     * @return lista dos estacionamentos
     */
    public List<Estacionamento> getListaEstacionamentos() {
        return estacionamentoDB.getLstEstacionamentos();
    }

    /**
     * Retorna lista de estacionamento de um determinado parque recendo o seu
     * nif por parâmetro
     *
     * @param parqueNif nif do parque/farmácia
     * @return lista de estacionamento do parque
     */
    public List<Estacionamento> getListaEstacionamentosByParqueNif(int parqueNif) {
        return estacionamentoDB.getLstEstacionamentosByNif(parqueNif);
    }

    /**
     * Cria um novo estacionamento
     *
     * @param numLote número de lote do estacionamento
     * @param carregador disponibilidade do carregador do estacionamento (1 se
     * disponível, 0 se não esta disponível)
     * @param nif nif da farmácia/parque
     * @return novo estacionamento criado
     */
    public Estacionamento novoEstacionamento(int numLote, int carregador, int nif) {
        return estacionamentoDB.novoEstacionamento(numLote, carregador, nif);
    }

    /**
     * Regista estacionamentos recebendo a lista com os estacionamentos
     *
     * @param lestac lista de estacionamentos
     * @return
     */
    public boolean registaEstacionamentos(List<Estacionamento> lestac) {
        return estacionamentoDB.registaEstacionamento(lestac);
    }

    /**
     * Retorna o limite máximo de scooters do parque recebendo o nif da farmácia
     * referente ao parque
     *
     * @param nif nif do parque/farmácia
     * @return limite máximo de scooters do parque
     */
    public int getNumMaxParqueByNIF(int nif) {
        return parqueDB.getNumMaxParqueByNIF(nif);
    }
}
