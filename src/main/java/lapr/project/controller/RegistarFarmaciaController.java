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
     * @param farmaciaDB classe handler de farmácia
     * @param parqueDB clase handler de parques
     * @param enderecoDB clase handler de endereços
     */
    public RegistarFarmaciaController(FarmaciaDB farmaciaDB, ParqueDB parqueDB, EnderecoDB enderecoDB) {
        this.farmaciaDB = farmaciaDB;
        this.parqueDB = parqueDB;
        this.enderecoDB = enderecoDB;
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
     * Cria uma nova farmácia
     *
     * @param nif nif da farmácia
     * @return nova farmacia criada
     */
    public Farmacia novaFarmacia(int nif) {
        Farmacia farm = farmaciaDB.novaFarmacia(nif);
        return farm;
    }

    /**
     * Cria um novo parque
     *
     * @param nif nif do parque/farmácia
     * @param morada morada do parque
     * @param numMax limite máximo de scooters do parque
     * @return novo parque criado
     */
    public Parque novoParque(int nif, String morada, int numMax) {
        Parque park = parqueDB.novoParque(nif, morada, numMax);
        return park;
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
        Endereco end = enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
        return end;
    }

    /**
     * Regista a farmacia
     *
     * @param farm farmacia
     */
    public void registaFarmacia(Farmacia farm) {
        farmaciaDB.registaFarmacia(farm);
    }

    /**
     * Regista o parque
     *
     * @param park parque
     */
    public void registaParque(Parque park) {
        parqueDB.registaParque(park);
    }

    /**
     * Regista o endereço
     *
     * @param end endereço
     */
    public void registaEndereco(Endereco end) {
        enderecoDB.registaEndereco(end);
    }
}
