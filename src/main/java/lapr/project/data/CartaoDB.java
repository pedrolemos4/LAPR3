/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Cartao;

/**
 *
 * @author josep
 */
public class CartaoDB {

    Cartao cc;
    private final DataHandler dataHandler;
    private List<Cartao> lstCartoes;

    public CartaoDB() {
        this.dataHandler = DataHandler.getInstance();
        lstCartoes = new ArrayList<>();
    }

    public List<Cartao> getLstCartoes() {
        return lstCartoes;
    }

    public Cartao novoCartao(int numeroCartao, String dataDeValidade, int CCV) {
        cc = new Cartao(numeroCartao, dataDeValidade, CCV);
        return cc;
    }

    public boolean registaCartao(Cartao cc) {
        if (validaCartao(cc)) {
            return addCartao(cc);
        }
        return false;
    }

    public boolean validaCartao(Cartao cc) {
        if (cc.getNumeroCartao() <= 0 || cc.getDataDeValidade().isEmpty() || cc.getDataDeValidade() == null || cc.getCCV() <= 0) {
            return false;
        }
        for (Cartao e : lstCartoes) {
            if (e.equals(cc) || e.getNumeroCartao() == cc.getNumeroCartao() || e.getCCV() == cc.getCCV()) {
                return false;
            }
        }
        return true;
    }

    public boolean addCartao(Cartao cc) {
        return lstCartoes.add(cc);
    }
}
