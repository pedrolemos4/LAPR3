package lapr.project.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.IniciarEntregaController;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
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
    
    public IniciarEntregaController controller;

    public IniciarEntregaUI() {
        this.controller = new IniciarEntregaController(new EntregaDB(), new EncomendaDB(), new EstafetaDB(), new EnderecoDB(), new ScooterDB());
    }
    
    public void iniciaEntrega() {
        
        Estafeta est = controller.getEstafeta();
        
        System.out.println("Lista de entregas: ");
        List<Entrega> list = controller.getListaEntregaByNifEstafeta(est.getNIF());
        for(Entrega e : list){
            System.out.println(e);
        }
        
        System.out.println("Introduza o id de uma entrega apresentada: ");
        int idEntrega = LER.nextInt();
        List<Encomenda> listEnc = controller.getListaEncomendaById(idEntrega);
        
        Entrega entr = controller.getEntregaById(idEntrega);
        Scooter scooter = controller.getScooterById(entr.getIdScooter());
        
        System.out.println("IdEntrega:\t" + idEntrega
                + "\nLista de Encomendas associadas à entrega:\t" + listEnc
                + "\nEstafeta:\t" + est
                + "\nScooter associada à entrega:\t" + scooter);
        
        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();
        
        if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
            Endereco endOrigem = controller.getEnderecoParque();
            List<Endereco> listEnderecos = new LinkedList<>();
            listEnderecos.add(endOrigem);
            double pesoTotal = 0;
            
            for(Encomenda e : listEnc){
                pesoTotal = pesoTotal + e.getPesoEncomenda();
                Endereco end = controller.getEnderecoByNifCliente(e.getNif());
                listEnderecos.add(end);
            }
            
            LinkedList<Endereco> lEn = controller.generateGraph(listEnderecos, est, scooter, pesoTotal);
            
            System.out.println("\n\nCaminho com menor energia gasta: '" + lEn);
        }
    }
    
}
