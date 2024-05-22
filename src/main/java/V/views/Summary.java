package V.views;

import M.Exercise;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class Summary extends ScrollPane{

    Exercise[] exo;
    int idExo;

    public Summary(Label lbl, Exercise[] exo, int idExo){
        this.exo = exo;
        this.idExo = idExo;

        VBox menuBar = new VBox();
        menuBar.setPrefWidth(300);

        Label lblSummary = new Label("Liste des exos disponibles :");
        menuBar.getChildren().addAll(lblSummary);


        TextArea searching = new TextArea();
        searching.setPrefHeight(8.0);
        searching.setPromptText("Tapez pour rechercher...");
        menuBar.getChildren().addAll(searching);

        //import number of exercice (bdd)
        final int NUMBER_EX = exo.length;

        for(int i=1; i<NUMBER_EX; i++){
            //import title of each exercice (bdd)
            String nameExercise = exo[i].ExoName;
            Button exercise = new Button(nameExercise);
            exercise.setPrefSize(300.0, 100.0 );
            exercise.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            exercise.addEventHandler(ActionEvent.ACTION, new ButtonScene(i, lbl, exo, idExo));
            menuBar.getChildren().addAll(exercise);
        }


        this.setContent(menuBar);
        this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }
}
