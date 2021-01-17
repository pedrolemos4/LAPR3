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
     * Cria instância do controlador InserirEstacionamentosController
     *
     * @param edb
     * @param pdb
     * @param fdb
     */
    public InserirEstacionamentosController(EstacionamentosDB edb, ParqueDB pdb, FarmaciaDB fdb) {
        this.estacionamentoDB = edb;
        this.parqueDB = pdb;
        this.farmaciaDB = fdb;
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
     * @param farmNIF
     * @param parqueID
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
     * 
     * @param nif
     * @return 
     */
    public Farmacia getFarmaciaByNIF(int nif) {
        return farmaciaDB.getFarmaciaByNIF(nif);
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
        return (estacionamentoDB.registaEstacionamento(lestac) ? (true) : (false));
    }

    /**
     * Retorna o limite máximo de veiculos do parque recebendo o nif da farmácia
     * referente ao parque
     *
     * @param farmNIF
     * @param parqueID
     * @return limite máximo de veiculos do parque
     */
    public int getNumMaxByFarmaciaNifParqueId(int farmNIF, int parqueID) {
        return parqueDB.getNumMaxByFarmaciaNifParqueId(farmNIF, parqueID);
    }

    public Parque getParqueByFarmaciaNifParqueId(int farmNIF, int idParque) {
        return parqueDB.getParqueByFarmaciaNifParqueId(farmNIF, idParque);
    }
}
