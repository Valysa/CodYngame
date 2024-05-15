package fr.cyu.views;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class Summary extends ScrollPane{

    public Summary(Label lbl){
        VBox menuBar = new VBox();
        menuBar.setPrefWidth(300);

        Label lblSummary = new Label("Liste des exos disponibles :");
        menuBar.getChildren().addAll(lblSummary);


        TextArea searching = new TextArea();
        searching.setPrefHeight(8.0);
        searching.setPromptText("Tapez pour rechercher...");
        menuBar.getChildren().addAll(searching);


        //J'ai défini pour l'instant à 20 mais ça va dépendre de la base de données
        for(int i=1; i<20; i++){
            Button exercice = new Button("Exercice "+i);
            exercice.setPrefSize(300.0, 100.0 );
            exercice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            exercice.addEventHandler(ActionEvent.ACTION, new ButtonScene(i, lbl));
            menuBar.getChildren().addAll(exercice);
        }


        this.setContent(menuBar);
        this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }
}
