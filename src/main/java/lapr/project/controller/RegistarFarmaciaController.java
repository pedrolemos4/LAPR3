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

    public RegistarFarmaciaController(FarmaciaDB farmaciaDB, ParqueDB parqueDB, EnderecoDB enderecoDB) {
        this.farmaciaDB = farmaciaDB;
        this.parqueDB = parqueDB;
        this.enderecoDB = enderecoDB;
    }

    public List<Farmacia> getListaFarmacias() {
        return farmaciaDB.getLstFarmacias();
    }

    public Farmacia novaFarmacia(int nif) {
        Farmacia farm = farmaciaDB.novaFarmacia(nif);
        return farm;
    }

    public Parque novoParque(int nif, String morada, int numMax) {
        Parque park = parqueDB.novoParque(nif, morada, numMax);
        return park;
    }

    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        Endereco end = enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
        return end;
    }

    public void registaFarmacia(Farmacia farm) {
        farmaciaDB.registaFarmacia(farm);
    }

    public void registaParque(Parque park) {
        parqueDB.registaParque(park);
    }

    public void registaEndereco(Endereco end) {
        enderecoDB.registaEndereco(end);
    }
}
