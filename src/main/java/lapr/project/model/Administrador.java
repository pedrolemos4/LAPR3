package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Administrador extends Utilizador{
    /**
     * Constrói uma instância de Administrador recebendo o nif do administrador, o
     * nome do administrador, o email do administrador, o
     * o numero de Seguranca Social do administrador, a password do administrador.
     * @param NIF
     * @param nome
     * @param email
     * @param numeroSegurancaSocial
     * @param password 
     */
    public Administrador(int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        super(NIF, nome, email, numeroSegurancaSocial, password);
    }
    /**
     * Constrói uma instância de Administrador com o administrador por omissão.
     */
    public Administrador() {
        super(0, null, null, 0, null);
    }
    
}
