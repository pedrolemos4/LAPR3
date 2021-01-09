package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Estafeta extends Utilizador{
    
    private double pesoEstafeta;
    private int idEstado;
    private int NIF;
    private String nome;

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
     * @param idEstado o estado atual do estafeta
     */
    public Estafeta(int NIF, String nome, String email, double pesoEstafeta, int numeroSegurancaSocial, String password, int idEstado) {
        super(NIF, nome, email, numeroSegurancaSocial, password);
        this.pesoEstafeta = pesoEstafeta;
        this.idEstado = idEstado;
    }

    /**
     * Constrói uma instância de Estafeta
     * @param NIF
     * @param nome
     * @param idEstado
     * @param pesoEstafeta
     */
    public Estafeta(int NIF, String nome, int idEstado, double pesoEstafeta) {
        this.NIF = NIF;
        this.nome = nome;
        this.pesoEstafeta = pesoEstafeta;
        this.idEstado = idEstado;
    }
    /**
     * Constrói uma instância de Estafeta com o estafeta por omissão.
     */
    public Estafeta() {
        super(0, null, null, 0, null);
        this.pesoEstafeta = 0;
        this.idEstado = 0;
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
    public int getEstado() {
        return idEstado;
    }
    /**
     * Modifica o estado do estafeta pelo valor recebido por parâmetro
     * @param idEstado o novo estado do estafeta.
     */
    public void setEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}
