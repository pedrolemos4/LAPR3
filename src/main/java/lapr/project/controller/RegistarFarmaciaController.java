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
     * Constroi uma instancia de RegistarFarmaciaController recebendo uma instancia de FarmaciaDB, ParqueDB e EnderecoDB
     * @param fdb uma instancia de FarmaciaDB
     * @param pdb uma instancia de ParqueDB
     * @param edb uma instancia de EnderecoDB
     */
    public RegistarFarmaciaController(FarmaciaDB fdb, ParqueDB pdb, EnderecoDB edb) {
        this.farmaciaDB = fdb;
        this.parqueDB = pdb;
        this.enderecoDB = edb;
    }

    /**
     * Retorna lista com todas as farmácias
     * @return lista das farmácias
     */
    public List<Farmacia> getListaFarmacias() {
        return farmaciaDB.getLstFarmacias();
    }

    /**
     * Retorna lista com todas os parques de uma determinada farmácia
     * @param nif da farmacia
     * @return lista dos parques da farmácia
     */
    public List<Parque> getListaParquesByFarmaciaNif(int nif) {
        return parqueDB.getLstParquesByFarmaciaNif(nif);
    }
    
    /**
     * Devolve uma farmacia recebendo por parametro o nif da farmacia
     * @param nif nif da farmacia
     * @return farmacia
     */
    public Farmacia getFarmaciaByNIF(int nif) {
        return farmaciaDB.getFarmaciaByNIF(nif);
    }
    
    /**
     * Devolve um endereço da farmacia recebendo por parametro a morada da farmacia
     * @param farmMorada morada da farmacia
     * @return endereço da farmacia 
     */
    public Endereco getEnderecoByMorada(String farmMorada) {
        return enderecoDB.getEnderecoByMorada(farmMorada);
    }

    /**
     * Cria uma nova farmácia recebendo por parametro o nif, email e morada
     * @param nif nif da farmácia
     * @param email nif da farmácia
     * @param morada morada da farmácia
     * @return nova farmacia criada
     */
    public Farmacia novaFarmacia(int nif, String email, String morada) {
        return farmaciaDB.novaFarmacia(nif, email, morada);
    }

    /**
     * Cria um novo parque recebendo o nif, numero maximo e tipo
     * @param nif nif do parque/farmácia
     * @param numMax limite máximo de veiculos do parque
     * @param tipo tipo de veículos do parque
     * @param maxCap capacidade máxima de carregamento do parque
     * @return novo parque criado
     */
    public Parque novoParque(int nif, int numMax, String tipo, int maxCap) {
        return parqueDB.novoParque(nif, numMax, tipo, maxCap);
    }

    /**
     * Cria um novo endereço recebendo por parametro a morada, latitude, longitude e altitude
     * @param morada morada do endereço
     * @param latitude latitude do endereço
     * @param longitude longitude do endereço
     * @param altitude altitude do endereço
     * @return novo endereço criado
     */
    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        return enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
    }

    /**
     * Verifica se a farmacia foi registada recebendo a farmacia
     * @param farm farmacia
     * @return true se a farmacia foi registada
     */
    public boolean registaFarmacia(Farmacia farm) {
        return (farmaciaDB.registaFarmacia(farm) ? (true) : (false));
    }

    /**
     * Verifica se o parque foi registado recebendo uma lista de parques
     * @param lparks lista dos parques a serem registados
     * @return true se os parques forem todos adicionados
     */
    public boolean registaParques(List<Parque> lparks) {
        return (parqueDB.registaParques(lparks) ? (true) : (false));
    }

    /**
     * Verifica se o endereço foi registado recebendo o endereço por parametro
     * @param end endereço
     * @return true se o endereço foi registado
     */
    public boolean registaEndereco(Endereco end) {
        return (enderecoDB.registaEndereco(end) ? (true) : (false));
    }
}
