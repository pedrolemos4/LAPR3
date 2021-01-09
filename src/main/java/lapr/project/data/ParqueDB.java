/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Endereco;
import lapr.project.model.Parque;

/**
 *
 * @author josep
 */
public class ParqueDB {

    Parque park;
    private EnderecoDB edb;
    private final DataHandler dataHandler;
    private List<Parque> lstParques;

    public ParqueDB() {
        this.dataHandler = DataHandler.getInstance();
        lstParques = new ArrayList<>();
    }

    public List<Parque> getLstParques() {
        return lstParques;
    }

    public Parque novoParque(int nif, Endereco end, int numMax) {
        park = new Parque(nif, end, numMax);
        return park;
    }

    public boolean registaParque(Parque park) {
        if (validaParque(park)) {
            return addParque(park);
        }
        return false;
    }

    public boolean validaParque(Parque park) {
        if (park.getNif() <= 0 || park.getNumeroMaximo() <= 0 || park.getEndereco() == null) {
            return false;
        }
        if (!edb.validaEndereco(park.getEndereco())) {
            return false;
        }
        for (Parque p : lstParques) {
            if (p.equals(park) || p.getNif() == park.getNif()) {
                return false;
            }
        }
        return true;
    }

    public boolean addParque(Parque park) {
        return lstParques.add(park);
    }
}
