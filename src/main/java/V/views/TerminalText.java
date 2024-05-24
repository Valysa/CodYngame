package V.views;

import C.Languages.JavaLanguage;
import C.Languages.Language;
import C.Languages.LanguageFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalText extends VBox{

    private MainView mainApp;

    public TerminalText(MainView mainApp){
        this.mainApp = mainApp;

        mainApp.getTerminalTextArea().setVisible(false);

        mainApp.getTerminalTextArea().setPrefWidth(700);
        mainApp.getTerminalTextArea().setPrefHeight(800);
        mainApp.getTerminalTextArea().setEditable(false);
        mainApp.getTerminalTextArea().setStyle("-fx-control-inner-background: #1e1e1e; -fx-text-fill: #c5c5c5; -fx-font-family: monospace;");

        Button submitButton = new Button("Submit");
        submitButton.addEventHandler(ActionEvent.ACTION, new SubmitButton(mainApp));
        /*
        submitButton.setOnAction(e -> {

            String command = mainApp.getInitTextArea().getText();
            mainApp.getTerminalTextArea().appendText("$ " + command + "\n");
            executeCommand(command, mainApp.getTerminalTextArea());
            mainApp.getInitTextArea().clear();


        });
*/

        this.getChildren().addAll(mainApp.getTerminalTextArea(), submitButton);
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
