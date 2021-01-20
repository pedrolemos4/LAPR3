package lapr.project.ui;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RegistarEntregaController;
import lapr.project.data.ClienteDB;
import lapr.project.data.EmailDB;
import lapr.project.data.EncomendaDB;
import lapr.project.data.EnderecoDB;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.FarmaciaDB;
import lapr.project.data.UtilizadorDB;
import lapr.project.data.VeiculoDB;
import lapr.project.model.Cliente;
import lapr.project.model.Drone;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Graph;
import lapr.project.model.Scooter;
import lapr.project.model.Utilizador;
import lapr.project.model.Veiculo;

/**
 *
 * @author beatr
 */
public class RegistarEntregaUI {
    
    public static final Scanner LER = new Scanner(System.in);
    public final RegistarEntregaController controller;
    private static final String DRONE = "drone";
    private static final String SCOOTER = "scooter";

    public RegistarEntregaUI() {
        this.controller = new RegistarEntregaController(new UtilizadorDB(), new FarmaciaDB(), new EstafetaDB(),new EntregaDB(), new EncomendaDB(), new VeiculoDB(), new EnderecoDB(), new EmailDB(), new ClienteDB());
    }
    
    public void introduzEntrega() throws SQLException, ParseException {
        
        System.out.println("Lista de farmacias: ");
        List<Farmacia> listFarmacia = controller.getLstFarmacias();
        for(Farmacia f : listFarmacia){
            System.out.println(f);
        }
        System.out.println("Escolha a farmacia para a qual irá fazer uma entrega");
        int nifFarmacia = LER.nextInt();
        Farmacia farmacia = controller.getFarmaciaByNif(nifFarmacia);
                
        System.out.println("Lista de veiculos: ");
        List<Veiculo> list = controller.getListVeiculo();
        for(Veiculo s : list){
            System.out.println(s);
        }
        
        System.out.println("Introduza o id de um veiculo apresentado: ");
        int idVeiculo = LER.nextInt();
        Veiculo veiculo = controller.getVeiculo(idVeiculo);
        
        Estafeta est = controller.getEstafeta();
        int nifEstafeta = est.getNIF();
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(System.currentTimeMillis());
        String dataInicio = formatter.format(date);
        String dataFim = "0000-00-00 00:00:00.000";
        
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
            Entrega entr = controller.addEntrega(dataInicio, dataFim, idVeiculo, nifEstafeta);
            List<Endereco> listEnderecos = new LinkedList<>();
            
            System.out.println("Lista de encomendas por fazer entrega: ");
            List<Encomenda> listAllEncomenda = controller.getListaEncomenda();
            List<Encomenda> listEncomendaByEntrega = new ArrayList<>();
            
            for(Encomenda e : listAllEncomenda){
                if(e.getPesoEncomenda() + pesoEntrega < pesoMaximoPorEntrega){
                    controller.addEncomendaEntrega(entr, e);
                    listEncomendaByEntrega.add(e);
                }
            }
            double pesoTotal = 0;
            
            for(Encomenda e : listEncomendaByEntrega){
                pesoTotal = pesoTotal + controller.getEncomenda(e.getId()).getPesoEncomenda();
                Endereco end = controller.getEnderecoByNifCliente(e.getNif());
                Cliente c = controller.getClienteByEndereco(end);
                Utilizador u = controller.getUtilizadorByNif(c.getClienteNIF());
                listEnderecos.add(end);
                controller.enviarNotaCliente(farmacia, u);
                controller.updateEncomenda(e.getId(),3);
            }
            Graph<Endereco,Double> graph = new Graph<>(true);
            
            if((veiculo.getDescricao()).equalsIgnoreCase(SCOOTER)){
                Scooter s = controller.getScooterById(veiculo.getId());
                 graph = controller.generateGraph(listEnderecos, est, veiculo, s.getAreaFrontal(), pesoTotal);
            } else if((veiculo.getDescricao()).equalsIgnoreCase(DRONE)){
                Drone d = controller.getDroneById(veiculo.getId());
                graph = controller.generateGraph(listEnderecos, est, veiculo, d.getPowerPro(), pesoTotal);
            }
            
            LinkedList<Endereco> finalShortPath = new LinkedList<>();
            
            double energiaTotalGasta = controller.getPath(graph, listEnderecos, finalShortPath, controller.getEnderecoOrigem(nifFarmacia), 0);
            
            String data = controller.getDuracaoPercurso(finalShortPath, veiculo);
            DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
            Date date2 = format1.parse(data);
            Date newDate = new Date(date.getTime() + date2.getTime() + 3600*1000);
            
            
            Entrega entrega = new Entrega(dataInicio, formatter.format(newDate), idVeiculo, nifEstafeta);
            
            controller.updateEntrega(entrega);
            
            System.out.println("\n\nEntrega adicionada com sucesso");
            System.out.println("\n\nCaminho com menor energia gasta: " + finalShortPath);
            System.out.println("\n\nEnergia gasta: " + energiaTotalGasta);
            
        }
    }
    
    
}
