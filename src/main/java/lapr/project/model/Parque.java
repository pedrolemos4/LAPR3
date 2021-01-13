package lapr.project.model;

public class Parque {

    private int nif;
    private String morada;
    private int numeroMaximo;
    private String tipo;

    /**
     * Constrói uma instância de parque com o nif do parque(igual ao nif da
     * farmácia), morada do parque e número máximo de lugares
     *
     * @param nif nif do parque
     * @param morada morada do parque
     * @param numeroMaximo número máximo de lugares do parque
     * @param tipo
     */
    public Parque(int nif, String morada, int numeroMaximo, String tipo) {
        this.nif = nif;
        this.morada = morada;
        this.numeroMaximo = numeroMaximo;
        this.tipo = tipo;
    }

    /**
     * Constrói uma instância de parque com o número máximo de lugares por
     * omissão
     */
    public Parque() {
        this.nif = 0;
        this.morada = "";
        this.numeroMaximo = 0;
        this.tipo = "";
    }

    /**
     * Devolve o valor do nif do parque
     *
     * @return nif do parque
     */
    public int getNIF() {
        return nif;
    }

    /**
     * Modifica o valor do nif do parque com o valor recebido por parâmetro
     *
     * @param nif novo nif do parque
     */
    public void setNIF(int nif) {
        this.nif = nif;
    }

    /**
     * Devolve a morada do parque
     *
     * @return endereço do parque
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Modifica a morada do parque com o valor recebido por parâmetro
     *
     * @param morada nova morada do parque
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Devolve o valor do número máximo de lugares do parque
     *
     * @return número máximo de lugares do parque
     */
    public int getNumeroMaximo() {
        return numeroMaximo;
    }

    /**
     * Modifica o valor do número máximo de lugares do parque com o valor
     * recebido por parâmetro
     *
     * @param numeroMaximo novo número máximo de lugares do parque
     */
    public void setNumeroMaximo(int numeroMaximo) {
        this.numeroMaximo = numeroMaximo;
    }

    /**
     * Devolve o tipo de veículos do parque
     *
     * @return tipo de veículos do parque
     */
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Parque{" + "nif=" + nif + ", morada=" + morada + ", numeroMaximo=" + numeroMaximo + ", tipo=" + tipo + '}';
    }
}
