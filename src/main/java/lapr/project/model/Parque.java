package lapr.project.model;

public class Parque {

    private int nif;
    private int numeroMaximo;
    private String tipo;
    private int idParque;

    /**
     * Constrói uma instância de parque com o nif do parque(igual ao nif da
     * farmácia), morada do parque e número máximo de lugares
     *
     * @param nif nif do parque
     * @param numeroMaximo número máximo de lugares do parque
     * @param tipo
     */
    public Parque(int nif, int numeroMaximo, String tipo) {
        this.nif = nif;
        this.numeroMaximo = numeroMaximo;
        this.tipo = tipo;
        this.idParque = 0;
    }

    public Parque(int id, int nif, int numeroMaximo, String tipo) {
        this.nif = nif;
        this.numeroMaximo = numeroMaximo;
        this.tipo = tipo;
        this.idParque = id;
    }

    /**
     * Constrói uma instância de parque com o número máximo de lugares por
     * omissão
     */
    public Parque() {
        this.nif = 0;
        this.numeroMaximo = 0;
        this.tipo = "sem tipo";
        this.idParque = 0;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdParque() {
        return idParque;
    }

    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    @Override
    public String toString() {
        return "Parque{" + "id=" + idParque + ", nif=" + nif + ", numeroMaximo=" + numeroMaximo + ", tipo=" + tipo + '}';
    }
}
