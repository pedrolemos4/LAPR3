package lapr.project.model;

/**
 *
 * @author beatr
 */
public class Utilizador {
    
    private int NIF;
    private String nome;
    private String email;
    private int numeroSegurancaSocial;
    private String password;
    /**
     * Constrói uma instância de Utilizador recebendo o nif do utilizador, o
     * nome do utilizador, o email do utilizador, o numero de Seguranca Social do utilizador,
     * a password do utilizador.
     *
     * @param NIF nif do utilizador
     * @param nome nome do utilizador
     * @param email email do utilizador
     * @param numeroSegurancaSocial numero de seguranca social do utilizador
     * @param password password do utilizador
     */
    public Utilizador(int NIF, String nome, String email, int numeroSegurancaSocial, String password) {
        this.NIF = NIF;
        this.nome = nome;
        this.email = email;
        this.numeroSegurancaSocial = numeroSegurancaSocial;
        this.password = password;
    }
    
    /**
     * Constrói uma instância de Utilizador com o utilizador por omissão.
     */
    public Utilizador(){
        this.NIF = 0;
        this.nome = null;
        this.email = null;
        this.numeroSegurancaSocial = 0;
        this.password = null;
    }
    
    /**
     * Devolve o nif do utilizador
     * @return nif do utilizador
     */
    public int getNIF() {
        return NIF;
    }
    
    /**
     * Modifica o nif do utilizador
     * @param NIF o novo nif do utilizador
     */
    public void setNIF(int NIF) {
        this.NIF = NIF;
    }
    
    /**
     * Devolve o nome do utilizador
     * @return nome do utilizador
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Modifica o nome do utilizador
     * @param nome o novo nome do utilizador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Devolve o email do utilizador
     * @return email do utilizador
     */
    public String getEmail() {
        return email;
    }
    /**
     * Modifica o email do utilizador
     * @param email o novo email do utilizador
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Devolve o numero de seguranca social do utilizador
     * @return numero de seguranca social do utilizador
     */
    public int getNumeroSegurancaSocial() {
        return numeroSegurancaSocial;
    }
    
    /**
     * Modifica o numero de seguranca social do utilizador
     * @param numeroSegurancaSocial o novo numero de seguranca social do utilizador
     */
    public void setNumeroSegurancaSocial(int numeroSegurancaSocial) {
        this.numeroSegurancaSocial = numeroSegurancaSocial;
    }
    
    /**
     * Devolve a password do utilizador
     * @return password do utilizador
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Modifica a password do utilizador
     * @param password a nova password do utilizador
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
