package V.views;

import M.Bdd;
import M.Exercise;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SubmitButton implements EventHandler<ActionEvent> {

    private MainView mainApp;

    /**
     * Constructor of the SubmitButton
     *
     * @param mainApp param of the exercise
     */
    public SubmitButton(MainView mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Event handle when we do an action on a summary button
     */
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
