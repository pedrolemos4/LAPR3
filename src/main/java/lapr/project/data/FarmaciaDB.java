/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;

/**
 *
 * @author josep
 */
public class FarmaciaDB {

    Farmacia farm;
    private EnderecoDB edb;
    private final DataHandler dataHandler;
    private List<Farmacia> lstFarmacias;

    public FarmaciaDB() {
        this.dataHandler = DataHandler.getInstance();
        lstFarmacias = new ArrayList<>();
    }

    public List<Farmacia> getLstFarmacias() {
        return lstFarmacias;
    }

    public Farmacia novaFarmacia(int nif, Parque park) {
        farm = new Farmacia(nif, park);
        return farm;
    }

    public boolean registaFarmacia(Farmacia farm) {
        if (validaFarmacia(farm)) {
            return addFarmacia(farm);
        }
        return false;
    }

    public boolean validaFarmacia(Farmacia farm) {
        if (farm.getNif() <= 0 || farm.getParque() == null) {
            return false;
        }
        for (Farmacia f : lstFarmacias) {
            if (f.equals(farm) || f.getNif() == farm.getNif()) {
                return false;
            }
        }
        return true;
    }

    public boolean addFarmacia(Farmacia farm) {
        return lstFarmacias.add(farm);
    }
}
