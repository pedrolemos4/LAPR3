package lapr.project.model;

public class Parque {

    private int nif;
    private Endereco end;
    private int numeroMaximo;

    /**
     * Constrói uma instância de parque com o nif do parque(igual ao nif da
     * farmácia), endereço do parque e número máximo de lugares
     *
     * @param nif nif do parque
     * @param end endereço do parque
     * @param numeroMaximo número máximo de lugares do parque
     */
    public Parque(int nif, Endereco end, int numeroMaximo) {
        this.nif = nif;
        this.end = end;
        this.numeroMaximo = numeroMaximo;
    }

    /**
     * Constrói uma instância de parque com o número máximo de lugares por
     * omissão
     */
    public Parque() {
        this.nif = 0;
        this.end = null;
        this.numeroMaximo = 0;
    }

    /**
     * Devolve o valor do nif do parque
     *
     * @return nif do parque
     */
    public int getNif() {
        return nif;
    }

    /**
     * Modifica o valor do nif do parque com o valor recebido por parâmetro
     *
     * @param nif novo nif do parque
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /**
     * Devolve o endereço do parque
     *
     * @return endereço do parque
     */
    public Endereco getEndereco() {
        return end;
    }

    /**
     * Modifica o endereço do parque com o valor recebido por parâmetro
     *
     * @param end novo endereço do parque
     */
    public void setEndereco(Endereco end) {
        this.end = end;
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
}
