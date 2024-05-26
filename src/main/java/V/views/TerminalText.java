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

    /**
     * Constructor of the TerminalText
     *
     * @param mainApp param of the exercises
     */
    public TerminalText(MainView mainApp){
        this.mainApp = mainApp;

        VBox testController = new VBox();
        testController.setPrefHeight(80);

        testController.getChildren().addAll(mainApp.getNbTry(), mainApp.getNbSuccess(), mainApp.getNbSessionSucess(), mainApp.getNbFirstTry());

        mainApp.getTerminalTextArea().setPrefWidth(500);
        mainApp.getTerminalTextArea().setPrefHeight(500);
        mainApp.getTerminalTextArea().setEditable(false);
        mainApp.getTerminalTextArea().setStyle("-fx-control-inner-background: #1e1e1e; -fx-text-fill: #c5c5c5; -fx-font-family: monospace;");

        Button submitButton = new Button("Submit");
        submitButton.addEventHandler(ActionEvent.ACTION, new SubmitButton(mainApp));

        this.getChildren().addAll(testController, mainApp.getTerminalTextArea(), submitButton);
    }
}
