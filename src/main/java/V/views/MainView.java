package V.views;


import M.Exercise;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;


public class MainView extends HBox {

    Exercise[] exo;

    int idExo;
    //Constructor VBox
        public MainView(Exercise[] exo) {
        this.exo = exo;


        Label lblSTTest = new Label("Veuillez selectionner votre Consigne \n test");
        ScrollPane menuBar = new ScrollPane(new Summary(lblSTTest, exo, idExo));



        VBox labelText = new VBox();
        //labelText.setPrefHeight(400);
        //IL FAUT MODIFIER LA CONSIGNE ICI
        Label lbl = new Label("BIENVENUE SUR CODYINGGAME");
        Label lblTwo = new Label("Voici votre consigne : ");

        HBox editorAndTerminal = new HBox();

        //Initialiser selon la base de données
        TextArea initTextArea = new TextArea();
        VBox editorText = new VBox(new EditorText(initTextArea));


        HBox editTerm = new HBox(new TerminalText(initTextArea));

        editorAndTerminal.getChildren().addAll(editorText, editTerm);


        labelText.getChildren().addAll(lbl, lblTwo, lblSTTest, editorAndTerminal);

        this.getChildren().addAll(menuBar, labelText);

    }


}
