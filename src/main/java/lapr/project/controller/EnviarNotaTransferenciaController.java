package lapr.project.controller;

import lapr.project.data.EmailDB;
import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

public class EnviarNotaTransferenciaController {
    private final EmailDB edb;

    public EnviarNotaTransferenciaController(EmailDB edb){
        this.edb = edb;
    }

    public boolean enviarNotaTransferencia(Farmacia fOrig, Farmacia fDest, Produto prod, int qtd){
        String mensagem = "Ser-lhe-á enviado o produto " + prod.getDesignacao() + " na quantidade necessária (" + qtd +")";
        return edb.sendEmail(fOrig.getEmail(),fDest.getEmail(), "Transferência de produto", mensagem);
    }
}
