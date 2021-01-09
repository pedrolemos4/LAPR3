/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.EstacionamentosDB;
import lapr.project.model.Estacionamento;

/**
 *
 * @author josep
 */
public class InserirEstacionamentosController {

    private final EstacionamentosDB estacionamentoDB;

    public InserirEstacionamentosController(EstacionamentosDB estacionamentoDB) {
        this.estacionamentoDB = estacionamentoDB;
    }

    public List<Estacionamento> getListaFarmacias() {
        return estacionamentoDB.getLstEstacionamentos();
    }

    public Estacionamento novoEstacionamento(int numLote, int carregador, int nif) {
        Estacionamento estac = estacionamentoDB.novoEstacionamento(numLote, carregador, nif);
        return estac;
    }

    public void registaEstacionamento(Estacionamento estac) {
        estacionamentoDB.registaEstacionamento(estac);
    }
}
