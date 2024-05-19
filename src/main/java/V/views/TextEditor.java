package V.views;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;

public class TextEditor extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");

        TextArea textArea = new TextArea();
        textArea.setPrefWidth(1000);
        textArea.setPrefHeight(600);
        Button submitButton = new Button("Submit");
        submitButton.setTextFill(Color.BLACK);
        submitButton.setStyle("-fx-background-color: green;");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveToFile(textArea,"c","extest");
            }
        });

        VBox layout = new VBox(10, textArea, submitButton); // A vertical container
        Scene scene = new Scene(layout, 1000, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveToFile(TextArea textArea,String extension,String exName) {
        String filePath = ("~/CodYngame/M/submittedEx/"+extension.toUpperCase()+"/" + exName+ "." + extension);
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

    public static void main(String[] args) {
        launch(args);
    }
}
