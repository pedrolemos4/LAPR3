/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.EstacionamentosDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Estacionamento;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;

/**
 *
 * @author josep
 */
public class InserirEstacionamentosController {

    private final EstacionamentosDB estacionamentoDB;
    private final ParqueDB parqueDB;
    private final FarmaciaDB farmaciaDB;

    /**
     * Cria instância do InserirEstacionamentosController
     *
     * @param edb instancia de EstacionamentosDB
     * @param pdb instancia de ParqueDB
     * @param fdb instancia de FarmaciaDB
     */
    public InserirEstacionamentosController(EstacionamentosDB edb, ParqueDB pdb, FarmaciaDB fdb) {
        this.estacionamentoDB = edb;
        this.parqueDB = pdb;
        this.farmaciaDB = fdb;
    }

    /**
     * Retorna lista de estacionamento de um determinado parque recendo o seu
     * nif por parâmetro
     *
     * @param farmNIF nif da farmacia
     * @param parqueID id do parque
     * @return lista de estacionamento do parque
     */
    public List<Estacionamento> getListaEstacionamentosByFarmaciaNifParqueId(int farmNIF, int parqueID) {
        return estacionamentoDB.getListaEstacionamentosByFarmaciaNifParqueId(farmNIF, parqueID);
    }

    /**
     * Retorna lista com todas as farmácias
     *
     * @return lista das farmácias
     */
    public List<Farmacia> getListaFarmacias() {
        return farmaciaDB.getLstFarmacias();
    }

    /**
     * Retorna lista com todas os parques de uma determinada farmácia
     *
     * @param nif da farmacia
     * @return lista dos parques da farmácia
     */
    public List<Parque> getListaParquesByFarmaciaNif(int nif) {
        return parqueDB.getLstParquesByFarmaciaNif(nif);
    }

    /**
     * Devolve a farmacia recebendo o nif da farmacia
     * @param nif nif da farmacia
     * @return farmacia
     */
    public Farmacia getFarmaciaByNIF(int nif) {
        return farmaciaDB.getFarmaciaByNIF(nif);
    }

    /**
     * Cria um novo estacionamento recebendo o numero de lote, a disponibilidade do carregador do estacionamento e o id do parque
     *
     * @param numLote número de lote do estacionamento
     * @param carregador disponibilidade do carregador do estacionamento (1 se
     * disponível, 0 se não esta disponível)
     * @param idParque id do parque
     * @return novo estacionamento criado
     */
    public Estacionamento novoEstacionamento(int numLote, int carregador, int idParque) {
        return estacionamentoDB.novoEstacionamento(numLote, carregador, idParque);
    }

    /**
     * Verifica se regista estacionamentos recebendo a lista com os estacionamentos por parametro
     * @param lestac lista de estacionamentos
     * @return true se o estacionamento foi registado
     */
    public boolean registaEstacionamentos(List<Estacionamento> lestac) {
        return (estacionamentoDB.registaEstacionamento(lestac) ? (true) : (false));
    }

    /**
     * Retorna o limite máximo de veiculos do parque recebendo o nif da farmácia
     * referente ao parque
     * @param farmNIF nif da farmacia
     * @param parqueID id do parque
     * @return limite máximo de veiculos do parque
     */
    public int getNumMaxByFarmaciaNifParqueId(int farmNIF, int parqueID) {
        return parqueDB.getNumMaxByFarmaciaNifParqueId(farmNIF, parqueID);
    }
}
