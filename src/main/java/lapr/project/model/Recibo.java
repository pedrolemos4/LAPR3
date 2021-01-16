/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author pedro
 */
public class Recibo {

    private int nif;
    private int id;
    private double preco;
    private String data;
    private final int idEncomenda;
    private Map<Produto, Integer> map;

    /**
     * Cria instancia receibo com nif do cliente, a data gerada e o preco total
     *
     * @param nif
     * @param data
     */
    public Recibo(int nif, double preco, String data, int idEncomenda) {
        this.id = 0;
        this.preco = preco;
        this.nif = nif;
        this.data = data;
        this.idEncomenda = idEncomenda;
        map = new TreeMap<>();
    }

    /**
     * Cria construtor vazio
     */
    public Recibo() {
        this.id = 0;
        this.nif = 0;
        this.preco = 0;
        this.idEncomenda = 0;
        this.data = "";
        map = new TreeMap<>();
    }

    /**
     * Devolve o preco
     *
     * @return
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Modifica o preco
     *
     * @param preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Devolve o id da encomenda presente no recibo
     *
     * @return
     */
    public int getIdEncomenda() {
        return idEncomenda;
    }

    /**
     * Devolve nif
     *
     * @return
     */
    public int getNif() {
        return nif;
    }

    /**
     * Devolve id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Devolve data
     *
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * Devolve a lista
     *
     * @return
     */
    public Map<Produto, Integer> getMap() {
        return map;
    }

    /**
     * Modifica o nif
     *
     * @param nif
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /**
     * Modifica id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifica data
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Modifica a lista
     *
     * @param lst
     */
    public void setLst(Map<Produto, Integer> mapa) {
        this.map = new HashMap<>(mapa);
    }

    @Override
    public String toString() {
        System.out.println("Recibo{" + "nif=" + nif + ", id=" + id + ", preco=" + preco + ", data=" + data + ", idEncomenda="
                    + idEncomenda );
        for (Produto p : map.keySet()) {
            return String.format(", produtos=" + map +"quantidade=" + map.get(p) + '}');
        }
        return "";
    }

}
