package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Cliente extends Utilizador{
    
    private int creditos;
    private Cartao cartaoCredito;
    
    /**
     * Constrói uma instância de Cliente recebendo os creditos do cliente, o
     * o cartao de credito do cliente, o nif do cliente, o nome do cliente, o
     * email do cliente, o numero de Seguranca Social do estafeta,
     * a password do estafeta.
     *
     * @param creditos creditos do cliente
     * @param cartaoCredito cartao de credito do cliente
     * @param NIF nif do cliente
     * @param nome nome do cliente
     * @param email email do cliente
     * @param numeroSegurancaSocial numero de seguranca social do cliente
     * @param password password do cliente
     */
    public Cliente(int creditos, Cartao cartaoCredito, int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        super(NIF, nome, email, numeroSegurancaSocial, password);
        this.creditos = creditos;
        this.cartaoCredito = cartaoCredito;
    }
    
    /**
     * Constrói uma instância de Cliente com o cliente por omissão.
     */
    public Cliente() {
        super(0, null, null, 0, null);
        this.creditos = 0;
        this.cartaoCredito = null;
    }
    
    /**
     * Devolve os creditos do cliente
     * @return creditos do cliente
     */
    public int getCreditos() {
        return creditos;
    }
    
    /**
     * Modifica os creditos do cliente
     * @param creditos os novos credito do cliente
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    /**
     * Devolve o cartao de credito do cliente
     * @return cartao de credito do cliente
     */
    public Cartao getCartaoCredito() {
        return cartaoCredito;
    }
    
    /**
     * Modifica o cartao de credito do cliente
     * @param cartaoCredito o novo cartao de credito do cliente
     */
    public void setCartaoCredito(Cartao cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }
    
}
