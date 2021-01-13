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
import lapr.project.data.VeiculoDB;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Veiculo;

/**
 *
 * @author beatr
 */
public class RegistarEntregaUI {
    
    public static final Scanner LER = new Scanner(System.in);
    public final RegistarEntregaController controller;

    public RegistarEntregaUI() {
        this.controller = new RegistarEntregaController(new EstafetaDB(),new EntregaDB(), new EncomendaDB(), new VeiculoDB());
    }
    
    public void introduzEntrega() throws SQLException {
        
        System.out.println("Lista de scooters: ");
        List<Veiculo> list = controller.getListVeiculo();
        for(Veiculo s : list){
            System.out.println(s);
        }
        
        System.out.println("Introduza o id de uma scooter apresentada: ");
        int idVeiculo = LER.nextInt();
        Veiculo veiculo = controller.getVeiculo(idVeiculo);
        
        Estafeta est = controller.getEstafeta();
        int nifEstafeta = est.getNIF();
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String dataInicio = formatter.format(date);
        String dataFim = null;
        
        double pesoMaximoPorEntrega = veiculo.getPesoMaximo();
        double pesoEntrega = 0;
        
        System.out.println("Scooter:\t" + veiculo
                + "\nEstafeta:\t" + est
                + "\nDataInicio:\t" + dataInicio
                + "\nDataFim:\t" + dataFim);
        
        System.out.println("Confirme os dados introduzidos: (S/N)");
        LER.nextLine();
        String confirm = LER.nextLine();
        
        if(confirm.equalsIgnoreCase("S") || confirm.equalsIgnoreCase("SIM")){
            Entrega entr = new Entrega(dataInicio, dataFim, idVeiculo, nifEstafeta);
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
