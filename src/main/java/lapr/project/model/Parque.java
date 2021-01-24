package lapr.project.model;

public class Parque {

    private int nif;
    private int numeroMaximo;
    private String tipo;
    private int idParque;
    private int maxCap;

    /**
     * Constrói uma instância de parque com o nif do parque(igual ao nif da
     * farmácia), morada do parque e número máximo de lugares
     *
     * @param nif nif do parque
     * @param numeroMaximo número máximo de lugares do parque
     * @param tipo
     * @param maxCap
     */
    public Parque(int nif, int numeroMaximo, String tipo, int maxCap) {
        this.nif = nif;
        this.numeroMaximo = numeroMaximo;
        this.tipo = tipo;
        this.idParque = 0;
        this.maxCap = maxCap;
    }

    /**
     * Constrói uma instância de parque com o nif do parque(igual ao nif da
     * farmácia), morada do parque e número máximo de lugares
     *
     * @param id id do parque
     * @param nif nif do parque
     * @param numeroMaximo número máximo de lugares do parque
     * @param tipo
     * @param maxCap
     */
    public Parque(int id, int nif, int numeroMaximo, String tipo, int maxCap) {
        this.nif = nif;
        this.numeroMaximo = numeroMaximo;
        this.tipo = tipo;
        this.idParque = id;
        this.maxCap = maxCap;
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
        this.maxCap = 1;
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

    /**
     * Modifica o tipo de veículos do parque
     *
     * @param tipo novo tipo de veículos do parque
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devolve o id do parque
     *
     * @return id do parque
     */
    public int getIdParque() {
        return idParque;
    }

    /**
     * Modifica o id do parque
     *
     * @param idParque novo id do parque
     */
    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    /**
     * Devolve a capacidade máxima de carregamento do parque
     *
     * @return capacidade máxima de carregamento do parque
     */
    public int getMaxCap() {
        return maxCap;
    }

    /**
     * Modifica a capacidade máxima de carregamento do parque
     *
     * @param maxCap nova capacidade máxima de carregamento do parque
     */
    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    /**
     * Devolve a descrição do parque
     *
     * @return
     */
    @Override
    public String toString() {
        return "Parque{" + "id=" + idParque + ", nif=" + nif + ", numeroMaximo=" + numeroMaximo + ", tipo=" + tipo + ", maxCap=" + maxCap + '}';
    }
}
