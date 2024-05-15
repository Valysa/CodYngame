package fr.cyu.views;


import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MainView extends HBox {
    //Constructor VBox
    public MainView(double v) {
        super(v);
/*
        Menu mainMenu = new Menu("Menu Principal");
        MenuItem testMenu = new MenuItem("testbug");
        mainMenu.getItems().addAll(testMenu);

        //SeparatorMenuItem sep = new SeparatorMenuItem();
        Menu exerciceOne = new Menu("Exercice 1");
        MenuItem stdItem = new MenuItem("stdin/stdout");
        MenuItem includeItem = new MenuItem("Include");
        exerciceOne.getItems().addAll(stdItem, includeItem);

        MenuBar someMenuBar = new MenuBar(mainMenu, exerciceOne);

        HBox vbox = new HBox();
        vbox.getChildren().add(someMenuBar);
*/
        Label lblSTTest = new Label("Veuillez selectionner votre Consigne \n test");
        ScrollPane menuBar = new ScrollPane(new Summary(lblSTTest));



        VBox labelText = new VBox();
        //labelText.setPrefHeight(400);
        //IL FAUT MODIFIER LA CONSIGNE ICI
        Label lbl = new Label("BIENVENUE SUR CODYINGGAME");
        Label lblTwo = new Label("Voici votre consigne :");

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
