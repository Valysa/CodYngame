package V.views;

import M.Exercise;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class ButtonScene implements EventHandler<ActionEvent> {
    public int i;
    private Label lbl;
    public Exercise[] exo;
    public int idExo;

    public ButtonScene(int i, Label lbl, Exercise[] exo, int idExo) {
        this.i = i;
        this.lbl = lbl;
        this.exo = exo;
        this.idExo = idExo;
    }

    @Override
    public void handle(ActionEvent event)
    {
        System.out.println("L'exercise "+ this.i + " a été sélectioné");
        this.lbl.setText(exo[i].Instruction);
        this.idExo = i;
    }

}
