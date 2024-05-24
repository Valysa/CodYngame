package V.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SubmitButton implements EventHandler<ActionEvent> {

    private MainView mainApp;
    public SubmitButton(MainView mainApp) {
        this.mainApp = mainApp;
    }

    public void handle(ActionEvent event){
        System.out.println("ça marche");
        mainApp.exerciseResolutionFXInclude();
    }
}
