package lapr.project.ui;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RegistarEntregaController;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Scooter;

/**
 *
 * @author beatr
 */
public class RegistarEntregaUI {
    
    public static final Scanner LER = new Scanner(System.in);
    
    RegistarEntregaController controller;

    public RegistarEntregaUI(RegistarEntregaController controller) {
        this.controller = controller;
    }
    
    public void introduzEntrega() throws SQLException {
        
        System.out.println("Lista de scooters: ");
        List<Scooter> list = controller.getListScooter();
        for(Scooter s : list){
            System.out.println(s);
        }
        
        System.out.println("Introduza o id de uma scooter apresentada: ");
        int idScooter = LER.nextInt();
        Scooter scooter = controller.getScooter(idScooter);
        
        Estafeta est = controller.getEstafeta();
        int NifEstafeta = est.getNIF();
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String dataInicio = formatter.format(date);
        String dataFim = null;
        
        double pesoMaximoPorEntrega = scooter.getPesoMaximo();
        double pesoEntrega = 0;
        
        System.out.println("Scooter:\t" + scooter
                + "\nEstafeta:\t" + est
                + "\nDataInicio:\t" + dataInicio
                + "\nDataFim:\t" + dataFim);
        
        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();
        
        if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
            Entrega entr = new Entrega(dataInicio, dataFim, idScooter, NifEstafeta);
            controller.addEntrega(entr);
            
            System.out.println("Lista de encomendas por fazer entrega: ");
            List<Encomenda> listEntrega = controller.getListaEncomenda();
            
            for(Encomenda e : listEntrega){
                if(e.getPesoEncomenda() + pesoEntrega < pesoMaximoPorEntrega){
                    controller.addEncomendaEntrega(entr, e);
                }
            }
            
            System.out.println("\n\nEntrega adicionada com sucesso'");
        }
    }
    
    
}
