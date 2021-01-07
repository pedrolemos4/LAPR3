package lapr.project.model;

public class Parque {
    private int numeroMaximo;
    private Estacionamento[] lugares;

    /**
     * Constrói uma instância de parque com o número máximo de lugares
     * @param numeroMaximo número máximo de lugares do parque
     */
    public Parque(int numeroMaximo){
        this.numeroMaximo = numeroMaximo;
    }

    /**
     * Constrói uma instância de parque com o número máximo de lugares por omissão
     */
    public Parque(){
        this.numeroMaximo = 0;
    }

    /**
     * Devolve o valor do número máximo de lugares do parque
     * @return número máximo de lugares do parque
     */
    public int getNumeroMaximo() {
        return numeroMaximo;
    }

    /**
     * Modifica o valor do número máximo de lugares do parque com o valor recebido por parâmetro
     * @param numeroMaximo novo número máximo de lugares do parque
     */
    public void setNumeroMaximo(int numeroMaximo) {
        this.numeroMaximo = numeroMaximo;
    }
}
