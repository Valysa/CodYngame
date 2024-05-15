package fr.cyu.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TerminalText extends VBox{

    public TerminalText(TextArea editorTextArea){
        //CODE CHATGPT QUI SERA MODIFIE PLUS TARD POUR LE MOMENT C'EST POUR VOIR CE QUE çA POURRAIT DONNER VISUELLEMENT

        // Création d'une TextArea pour afficher le contenu du terminal
        TextArea terminalTextArea = new TextArea();
        terminalTextArea.setPrefWidth(600);
        terminalTextArea.setPrefHeight(600);
        terminalTextArea.setEditable(false);
        terminalTextArea.setStyle("-fx-control-inner-background: #1e1e1e; -fx-text-fill: #c5c5c5; -fx-font-family: monospace;");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String command = editorTextArea.getText();
            terminalTextArea.appendText("$ " + command + "\n");
            executeCommand(command, terminalTextArea);
            editorTextArea.clear();
        });


        this.getChildren().addAll(terminalTextArea, submitButton);
    }

    private void executeCommand(String command, TextArea terminalTextArea) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                terminalTextArea.appendText(line + "\n");
            }
            process.waitFor();
        } catch (Exception ex) {
            terminalTextArea.appendText("Erreur: " + ex.getMessage() + "\n");
        }
    }

}
