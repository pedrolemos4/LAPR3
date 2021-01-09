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

    public void novaFarmacia(int nif, String morada, double latitude, double longitude, double altitude, int numMax) {
        Endereco end = enderecoDB.novoEndereco(morada, latitude, longitude, altitude);
        Parque park = parqueDB.novoParque(nif, end, numMax);
        Farmacia farm = farmaciaDB.novaFarmacia(nif, park);
        registaFarmacia(farm);
    }

    public boolean registaFarmacia(Farmacia farm) {
        return farmaciaDB.registaFarmacia(farm);
    }
}
