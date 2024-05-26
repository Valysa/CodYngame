package V.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonScene implements EventHandler<ActionEvent> {
    private final MainView mainApp;
    private final int i;
    /**
     * Constructor of the ButtonScene
     *
     * @param mainApp param of the exercise
     * @param i number of the exercise
     */
    public ButtonScene(MainView mainApp, int i) {
        this.mainApp = mainApp;
        this.i = i;
    }
    /**
     * Event handle when we do an action on a submit button
     */
    @Override
    public void handle(ActionEvent event)
    {
        System.out.println("L'exercise "+ i + " a été séléctionné");
        mainApp.updateIdExo(i);
    }

}
