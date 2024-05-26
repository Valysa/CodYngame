package V.views;

import M.Bdd;
import M.Exercise;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class Summary extends ScrollPane{
    private final MainView mainApp;

    public Summary(MainView mainApp){
        this.mainApp = mainApp;
        VBox menuBar = new VBox();
        menuBar.setPrefWidth(350);

        Label lblSummary = new Label("Liste des exos disponibles :");
        menuBar.getChildren().addAll(lblSummary);


        TextArea searching = new TextArea();
        searching.setPrefHeight(8.0);
        searching.setPromptText("Tapez pour rechercher...");
        menuBar.getChildren().addAll(searching);

        //Take all the exercise of the bdd and display in a list of buttons
        for(int i=1; i<Bdd.count(); i++){
            Exercise exerciseBdd = Bdd.take(i);
            String nameExercise = exerciseBdd.ExoName;
            Button exercise = new Button(nameExercise);
            exercise.setPrefSize(350.0, 70.0 );
            //Event when we do an action on the button
            exercise.addEventHandler(ActionEvent.ACTION, new ButtonScene(mainApp, i));
            exercise.setAlignment(Pos.CENTER_LEFT);
            menuBar.getChildren().addAll(exercise);
        }

        this.setContent(menuBar);
        this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }
}
