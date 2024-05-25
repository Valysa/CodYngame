package V.views;

import M.Exercise;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class ButtonScene implements EventHandler<ActionEvent> {
    private MainView mainApp;
    private int i;

    // Constructeur qui prend une référence à la classe principale
    public ButtonScene(MainView mainApp, int i) {
        this.mainApp = mainApp;
        this.i = i;
    }

    @Override
    public void handle(ActionEvent event)
    {
        int j = this.i+1;
        System.out.println("L'exercise "+ j + " a été séléctionné");
        mainApp.updateIdExo(i);
    }

}
