package lapr.project.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.IniciarEntregaController;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

/**
 *
 * @author beatr
 */
public class IniciarEntregaUI {
    
    public static final Scanner LER = new Scanner(System.in);
    
    IniciarEntregaController controller;

    public IniciarEntregaUI(IniciarEntregaController controller) {
        this.controller = controller;
    }
    
    public void iniciaEntrega() {
        
        System.out.println("Lista de entregas: ");
        List<Entrega> list = controller.getListaEntrega();
        for(Entrega e : list){
            System.out.println(e);
        }
        
        System.out.println("Introduza o id de uma entrega apresentada: ");
        int idEntrega = LER.nextInt();
        List<Encomenda> listEnc = controller.getListaEncomenda(idEntrega);
        
        Estafeta est = controller.getEstafeta();
        
        System.out.println("IdEntrega:\t" + idEntrega
                + "\nLista de Encomendas associadas à entrega:\t" + listEnc
                + "\nEstafeta:\t" + est);
        
        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();
        
        if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
            List<Endereco> listEnderecos = new ArrayList<>();
            
            for(Encomenda e : listEnc){
                Endereco end = controller.getEnderecoByNifCliente(e.getNif());
                listEnderecos.add(end);
            }
            
            System.out.println("\n\nEntrega adicionada com sucesso'");
        }
    }
    
    
}
