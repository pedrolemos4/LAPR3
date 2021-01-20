package lapr.project.model;


/**
 *
 * @author pedro
 */
public class Farmacia {

    private int nif;
    private String morada;
    private String email;

    /**
     * Constrói uma instância de farmácia com o nif da mesma e o seu parque
     *
     * @param nif nif da farmácia
     * @param email email da farmácia
     * @param morada da farmácia
     */
    public Farmacia(int nif, String email, String morada) {
        this.nif = nif;
        this.email = email;
        this.morada = morada;
    }

    /**
     * Constrói uma instância de farmácia com o nif e o parque por omissão
     */
    public Farmacia() {
        this.nif = 0;
        this.email = "sem email";
        this.morada = "sem morada";
    }

    /**
     * Devolve o valor do nif da farmácia
     *
     * @return nif da farmácia
     */
    public int getNIF() {
        return nif;
    }

    /**
     * Modifica o valor do nif da farmácia com o valor recebido por parâmetro
     *
     * @param nif novo nif da farmácia
     */
    public void setNIF(int nif) {
        this.nif = nif;
    }

    /**
     * Devolve a morada da farmácia
     *
     * @return morada da farmácia
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Modifica a morada
     * @param morada 
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Devolve o email da farmácia
     *
     * @return email da farmácia
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifica o email da farmacia
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devolve a descrição textual da Farmacia
     * @return 
     */
    @Override
    public String toString() {
        return "Farmacia{" + "nif=" + nif + ", email=" + email + ", morada=" + morada + '}';
    }
}
