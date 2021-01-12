/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author pedro
 */
public class Endereco {

    private String morada;
    private double latitude;
    private double longitude;
    private double altitude;

    /**
     * Constrói instância endereço com a morada, latitude, longitude e altitude
     *
     * @param morada morada do endereço
     * @param latitude latitude do endereço
     * @param longitude longitude do endereço
     * @param altitude altitude do endereço
     */
    public Endereco(String morada, double latitude, double longitude, double altitude) {
        this.morada = morada;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Endereco() {
        this.morada = null;
        this.latitude = 0;
        this.longitude = 0;
        this.altitude = 0;
    }

    /**
     * Devolve morada
     *
     * @return
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Devolve latitude
     *
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Devolve longitude
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Devolve altitude
     *
     * @return
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Modifica morada
     *
     * @param morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Modifica latitude
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Modifica longitude
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Modifica altitude
     *
     * @param altitude
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "Endereco{" + "morada=" + morada + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + '}';
    }
}
