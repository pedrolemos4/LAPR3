package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Estafeta extends Utilizador{
    
    private double pesoEstafeta;
    private EstadoEstafeta estado;

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
     * @param estado o estado atual do estafeta
     */
    public Estafeta(double pesoEstafeta, int NIF, String nome, String email, int numeroSegurancaSocial, String password, EstadoEstafeta estado) {
        super(NIF, nome, email, numeroSegurancaSocial, password);
        this.pesoEstafeta = pesoEstafeta;
        this.estado = estado;
    }
    
    /**
     * Constrói uma instância de Estafeta com o estafeta por omissão.
     */
    public Estafeta() {
        super(0, null, null, 0, null);
        this.pesoEstafeta = 0;
        this.estado = new EstadoEstafeta();
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
     * Modifica o peso do estafeta pelo valor recebido por parâmetro
     * @param pesoEstafeta o novo peso do estafeta.
     */
    public void setPesoEstafeta(double pesoEstafeta) {
        this.pesoEstafeta = pesoEstafeta;
    }
    /**
     * Devolve o estado do estafeta.
     *
     * @return estado do estafeta.
     */
    public EstadoEstafeta getEstado() {
        return estado;
    }
    /**
     * Modifica o estado do estafeta pelo valor recebido por parâmetro
     * @param estado o novo estado do estafeta.
     */
    public void setEstado(EstadoEstafeta estado) {
        this.estado = estado;
    }
}
