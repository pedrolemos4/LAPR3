package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Cliente extends Utilizador {

    private int nif;
    private double creditos;
    private String enderecomorada;
    private long numCartaoCredito;

    /**
     * Controi uma instância de cliente
     *
     * @param nif nif do cliente
     * @param nome nome do cliente
     * @param email email do cliente
     * @param numeroSegurancaSocial numero de seguranca social do cliente
     * @param creditos creditos do cliente
     * @param morada morada do cliente
     * @param numCC número de cartão de cidadão do cliente
     * @param password password do cliente
     */
    public Cliente(int nif, String nome, String email, int numeroSegurancaSocial, double creditos, String morada, long numCC, String password) {
        super(nif, nome, email, numeroSegurancaSocial, password);
        this.creditos = creditos;
        this.enderecomorada = morada;
        this.numCartaoCredito = numCC;
    }

    /**
     * Controi uma instância de cliente
     *
     * @param nif nif do cliente
     * @param creditos creditos do cliente
     * @param morada morada do cliente
     * @param numCC número de cartão de cidadão do cliente
     */
    public Cliente(int nif, double creditos, String morada, long numCC) {
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
    public double getCreditos() {
        return creditos;
    }

    /**
     * Modifica os creditos do cliente
     *
     * @param creditos os novos credito do cliente
     */
    public void setCreditos(double creditos) {
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
    public long getNumCartaoCredito() {
        return numCartaoCredito;
    }

    /**
     * Modifica o número de cartão de crédito do cliente
     *
     * @param numCartaoCredito o novo número de cartão de crédito do cliente
     */
    public void setNumCartaoCredito(long numCartaoCredito) {
        this.numCartaoCredito = numCartaoCredito;
    }

    /**
     * Devolve a descrição textual do Cliente
     * @return 
     */
    @Override
    public String toString() {
        return "Cliente{" + "nif=" + nif + ", creditos=" + creditos + ", enderecomorada=" + enderecomorada + ", numCartaoCredito" + numCartaoCredito + '}';
    }
}
