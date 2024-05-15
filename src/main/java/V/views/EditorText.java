package V.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EditorText extends VBox {
    TextArea textArea = new TextArea();

    public EditorText(TextArea textArea){
        this.textArea = textArea;

        String[] languages = {"C", "Python", "Java", "JavaScript", "PHP"};
        ChoiceBox<String> choiceLanguages = new ChoiceBox<>();
        choiceLanguages.getItems().addAll(languages);
        String usingLanguage = choiceLanguages.getValue();

        textArea.setPrefWidth(600);
        textArea.setPrefHeight(600);
        /*
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveToFile(textArea,"c","extest");
            }
        });
         */

        this.getChildren().addAll(choiceLanguages, textArea);


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
