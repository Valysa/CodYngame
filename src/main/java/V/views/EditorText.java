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
    /*
    TextArea textArea = new TextArea();
    public Exercise[] exo;
    public int idExo;

     */
    private MainView mainApp;

    public EditorText(MainView mainApp){
        this.mainApp = mainApp;

        HBox choicesExerciseType = new HBox();

        mainApp.getLanguages().setVisible(false);

        choicesExerciseType.getChildren().addAll(mainApp.getMods(), mainApp.getLanguages());

        VBox editorText = new VBox(new ControllerText(mainApp));

/*
        mainApp.getInitTextArea().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mainApp.setInitTextArea(newValue);
            }
        });

 */


        mainApp.getInitTextArea().setPrefWidth(600);
        mainApp.getInitTextArea().setPrefHeight(800);
        /*
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveToFile(textArea,"c","extest");
            }
        });
         */

        this.getChildren().addAll(choicesExerciseType, editorText);


    }
/*
    private void saveToFile(TextArea textArea,String extension,String exName) {
        String filePath = ("/home/cytech/CodYngame/CodYngame/M/submittedEx/"+extension.toUpperCase()+"/" + exName+ "." + extension);
        try {
            File file = new File(filePath);

            try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
                writer.print(textArea.getText());
                writer.flush();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "File saved successfully at " + filePath + "!");
                alert.showAndWait();
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving file: " + ex.getMessage());
            alert.showAndWait();
        }
    }

 */

}
