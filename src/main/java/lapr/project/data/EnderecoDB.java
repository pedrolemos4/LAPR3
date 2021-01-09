/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Endereco;

/**
 *
 * @author josep
 */
public class EnderecoDB {

    Endereco end;
    private final DataHandler dataHandler;
    private List<Endereco> lstEnderecos;

    public EnderecoDB() {
        this.dataHandler = DataHandler.getInstance();
        lstEnderecos = new ArrayList<>();
    }

    public List<Endereco> getLstEnderecos() {
        return lstEnderecos;
    }

    public Endereco novoEndereco(String morada, double latitude, double longitude, double altitude) {
        end = new Endereco(morada, latitude, longitude, altitude);
        return end;
    }

    public boolean registaCliente(Endereco end) {
        if (validaEndereco(end)) {
            return addEndereco(end);
        }
        return false;
    }

    public boolean validaEndereco(Endereco end) {
        if (end.getMorada() == null || end.getMorada().isEmpty() || end.getLatitude() <= 0 || end.getLongitude() <= 0 || end.getAltitude() <= 0) {
            return false;
        }
        for (Endereco e : lstEnderecos) {
            if (e.equals(end)) {
                return false;
            }
        }
        return true;
    }

    public boolean addEndereco(Endereco end) {
        return lstEnderecos.add(end);
    }
}
