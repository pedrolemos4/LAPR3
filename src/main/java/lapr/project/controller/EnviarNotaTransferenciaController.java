package lapr.project.controller;

import lapr.project.data.EmailDB;
import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

public class EnviarNotaTransferenciaController {
    private final EmailDB edb;
    
    /**
     * Constroi uma instancia de EnviarNotaTransferenciaController recebendo uma instancia de EmailDB
     * @param edb instancia de EmailDB
     */
    public EnviarNotaTransferenciaController(EmailDB edb){
        this.edb = edb;
    }
    
    /**
     * Verifica se nota de transferencia foi enviada recebendo uma farmacia origem,uma farmacia destino, o produto e a quantidade
     * @param fOrig farmacia de origem
     * @param fDest farmacia de destino
     * @param prod produto
     * @param qtd quantidade
     * @return true se nota de transferencia foi enviada
     */
    public boolean enviarNotaTransferencia(Farmacia fOrig, Farmacia fDest, Produto prod, int qtd){
        String mensagem = "Ser-lhe-á enviado o produto " + prod.getDesignacao() + " na quantidade necessária (" + qtd +")";
        return (edb.sendEmail(fOrig.getEmail(),fDest.getEmail(), "Transferência de produto", mensagem)? (true) : (false));
    }
}
