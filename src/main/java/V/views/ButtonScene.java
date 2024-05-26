package V.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonScene implements EventHandler<ActionEvent> {
    private final MainView mainApp;
    private final int i;
    public ButtonScene(MainView mainApp, int i) {
        this.mainApp = mainApp;
        this.i = i;
    }
    @Override
    public void handle(ActionEvent event)
    {
        System.out.println("L'exercise "+ i + " a été séléctionné");
        mainApp.updateIdExo(i);
    }

}
