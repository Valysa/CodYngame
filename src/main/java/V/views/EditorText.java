package V.views;

import M.Exercise;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EditorText extends VBox {
    private MainView mainApp;
    /**
     * Constructor of the EditorText
     *
     * @param mainApp param of the exercises
     */
    public EditorText(MainView mainApp){
        this.mainApp = mainApp;

        HBox choicesExerciseType = new HBox();


        choicesExerciseType.getChildren().addAll(mainApp.getMods(), mainApp.getLanguages());

        VBox editorText = new VBox(new ControllerText(mainApp));

        mainApp.getInitTextArea().setPrefWidth(800);
        mainApp.getInitTextArea().setPrefHeight(700);

        this.getChildren().addAll(choicesExerciseType, editorText);


    }
}
