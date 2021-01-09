package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Cliente extends Utilizador {

    private int nif;
    private int creditos;
    private String enderecomorada;
    private int numCartaoCredito;

    public Cliente(int nif, int creditos, String morada, int numCC) {
        this.nif = nif;
        this.creditos = creditos;
        this.enderecomorada = morada;
        this.numCartaoCredito = numCC;
    }

    /**
     * Constrói uma instância de Cliente com o cliente por omissão.
     */
    public Cliente() {
        this.nif = 0;
        this.creditos = 0;
        this.enderecomorada = "";
        this.numCartaoCredito = 0;
    }

    /**
     * Devolve o nif do cliente
     *
     * @return nif do cliente
     */
    public int getClienteNIF() {
        return nif;
    }

    /**
     * Modifica o nif do cliente
     *
     * @param nif o novo nif do cliente
     */
    public void setClienteNIF(int nif) {
        this.nif = nif;
    }

    /**
     * Devolve os creditos do cliente
     *
     * @return creditos do cliente
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * Modifica os creditos do cliente
     *
     * @param creditos os novos credito do cliente
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    /**
     * Devolve o endereco do cliente
     *
     * @return endereco do cliente
     */
    public String getEnderecoMorada() {
        return enderecomorada;
    }

    /**
     * Modifica o endereco do cliente
     *
     * @param enderecomorada o novo endereco do cliente
     */
    public void setEnderecoMorada(String enderecomorada) {
        this.enderecomorada = enderecomorada;
    }

    /**
     * Devolve o número de cartão de crédito do cliente
     *
     * @return número de cartão de crédito do cliente
     */
    public int getNumCartaoCredito() {
        return numCartaoCredito;
    }

    /**
     * Modifica o número de cartão de crédito do cliente
     *
     * @param numCartaoCredito o novo número de cartão de crédito do cliente
     */
    public void setNumCartaoCredito(int numCartaoCredito) {
        this.numCartaoCredito = numCartaoCredito;
    }
}
