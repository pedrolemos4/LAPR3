package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

/**
 *
 * @author Tiago
 */
public class ScooterController {

    private final ScooterDB scooterDB;

    public ScooterController(ScooterDB scooterDB) {
        this.scooterDB = scooterDB;
    }

    public Scooter addScooter(String descricao, int percentagemBateria, double pesoMaximo,
            double pesoScooter, double potencia,double areaFrontal, int estado) throws SQLException {
        Scooter scooter = new Scooter(descricao, percentagemBateria, pesoMaximo, 
                pesoScooter, potencia,areaFrontal, estado);

        scooter.setId(scooterDB.addScooter(scooter));
        return scooter;
    }

    public boolean updateScooter(Scooter scooter) throws SQLException {
        boolean updated = false;
        //Scooter scooter = new Scooter(idScooter, descricao, percentagemBateria, pesoMaximo,
          //      pesoScooter, potencia,areaFrontal, estado);

        return scooterDB.updateScooter(scooter);
    }
    
    public List<Scooter> getListaScooter(){
        return scooterDB.getListaScooter();
    }

    public Scooter getScooterById(int id){
        return scooterDB.getScooterById(id);
    }
    
    public boolean removeScooter(int idScooter) throws SQLException {
        boolean removed = false;

        return scooterDB.removeScooter(idScooter);
    }
}
