package lapr.project.ui;

import lapr.project.controller.NotificaEstafetaController;
import lapr.project.data.EntregaDB;
import lapr.project.data.EstafetaDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Entrega;

import java.io.IOException;

public class NotificaEstafetaUI {
    NotificaEstafetaController controller;

    public NotificaEstafetaUI() throws IOException {
        controller = new NotificaEstafetaController(new EstafetaDB(), new ScooterDB(), new EntregaDB());

    }
}
