/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.EnderecoDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.ParqueDB;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;

/**
 *
 * @author josep
 */
public class RegistarFarmaciaController {

    private final FarmaciaDB farmaciaDB;
    private final ParqueDB parqueDB;
    private final EnderecoDB enderecoDB;

    /**
     * Cria instância do controlador InserirEstacionamentosController
     *
     * @param fdb
     * @param pdb
     * @param edb
     */
    public RegistarFarmaciaController(FarmaciaDB fdb, ParqueDB pdb, EnderecoDB edb) {
        this.farmaciaDB = fdb;
        this.parqueDB = pdb;
        this.enderecoDB = edb;
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

    public Farmacia getFarmaciaByNIF(int nif) {
        return farmaciaDB.getFarmaciaByNIF(nif);
    }

    public Endereco getEnderecoByFarmaciaMorada(String farmMorada) {
        return enderecoDB.getEnderecoByFarmaciaMorada(farmMorada);
    }

    /**
     * Cria uma nova farmácia
     *
     * @param nif nif da farmácia
     * @param email nif da farmácia
     * @param morada morada da farmácia
     * @return nova farmacia criada
     */
    public Farmacia novaFarmacia(int nif, String email, String morada) {
        return farmaciaDB.novaFarmacia(nif, email, morada);
    }

    /**
     * Cria um novo parque
     *
     * @param nif nif do parque/farmácia
     * @param numMax limite máximo de veiculos do parque
     * @param tipo tipo de veículos do parque
     * @return novo parque criado
     */
    public Parque novoParque(int nif, int numMax, String tipo) {
        return parqueDB.novoParque(nif, numMax, tipo);
    }

    /**
     * Cria um novo endereço
     *
     * @param morada
     * @param latitude
     * @param longitude
     * @param altitude
     * @return novo endereço criado
     */
    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        return enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
    }

    /**
     * Regista a farmacia
     *
     * @param farm farmacia
     * @return
     */
    public boolean registaFarmacia(Farmacia farm) {
        return (farmaciaDB.registaFarmacia(farm) ? (true) : (false));
    }

    /**
     * Regista o parque
     *
     * @param lparks lista dos parques a serem registados
     * @return true se os parques forem todos adicionados
     */
    public boolean registaParques(List<Parque> lparks) {
        return (parqueDB.registaParques(lparks) ? (true) : (false));
    }

    /**
     * Regista o endereço
     *
     * @param end endereço
     * @return
     */
    public boolean registaEndereco(Endereco end) {
        return (enderecoDB.registaEndereco(end) ? (true) : (false));
    }
}
