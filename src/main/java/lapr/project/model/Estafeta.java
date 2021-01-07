package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Estafeta extends Utilizador{
    
    private double pesoEstafeta;
    
    /**
     * Constrói uma instância de Estafeta recebendo o peso do estafeta, o
     * nif do estafeta, o nome do estafeta, o email do estafeta, o
     * o numero de Seguranca Social do estafeta, a password do estafeta.
     * 
     * @param pesoEstafeta o peso do estafeta
     * @param NIF o nif do estafeta
     * @param nome o nome do estafeta
     * @param email o email do estafeta
     * @param numeroSegurancaSocial o numero de Seguranca Social do estafeta
     * @param password a password do estafeta
     */
    public Estafeta(double pesoEstafeta, int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        super(NIF, nome, email, numeroSegurancaSocial, password);
        this.pesoEstafeta = pesoEstafeta;
    }
    
    /**
     * Constrói uma instância de Estafeta com o estafeta por omissão.
     */
    public Estafeta() {
        super(0, null, null, 0, null);
        this.pesoEstafeta = 0;
    }
    
    /**
     * Devolve o peso do estafeta.
     *
     * @return peso do estafeta.
     */
    public double getPesoEstafeta() {
        return pesoEstafeta;
    }
    /**
     * Modifica o peso do estafeta
     * @param pesoEstafeta o novo peso do estafeta.
     */
    public void setPesoEstafeta(double pesoEstafeta) {
        this.pesoEstafeta = pesoEstafeta;
    }
    
}
