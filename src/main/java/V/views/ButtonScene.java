package V.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class ButtonScene implements EventHandler<ActionEvent> {
    public int i;
    private Label lbl;
    public ButtonScene(int i, Label lbl) {
        this.i = i;
        this.lbl = lbl;
    }

    @Override
    public void handle(ActionEvent event)
    {
        System.out.println("L'exercise "+ this.i + " a été sélectioné");
        this.lbl.setText("L'exercise "+ this.i + " a été sélectioné");
    }

}
