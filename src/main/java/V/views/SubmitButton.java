package V.views;

import M.Bdd;
import M.Exercise;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SubmitButton implements EventHandler<ActionEvent> {

    private MainView mainApp;
    public SubmitButton(MainView mainApp) {
        this.mainApp = mainApp;
    }

    public void handle(ActionEvent event) {
        Exercise exUpdated = Bdd.take(mainApp.getIdExo());
        if (exUpdated.ExoType == 0) {
            mainApp.exerciseResolutionFXStdinStdout();
            System.out.println("Mode Stdin/Stdout");
        } else if (exUpdated.ExoType == 1){
            mainApp.exerciseResolutionFXInclude();
            System.out.println("Mode Include");

        }
    }
}
