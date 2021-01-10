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
     */
    public RegistarFarmaciaController() {
        this.farmaciaDB = new FarmaciaDB();
        this.parqueDB = new ParqueDB();
        this.enderecoDB = new EnderecoDB();
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
        return farmaciaDB.novaFarmacia(nif);
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
        return parqueDB.novoParque(nif, morada, numMax);
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
