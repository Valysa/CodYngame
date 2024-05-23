package V.views;


import M.Exercise;
import M.ExerciseInclude;
import M.ExerciseStdinStdout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.fxmisc.richtext.CodeArea;

import java.io.IOException;


public class MainView extends HBox {

    private Exercise[] exo;
    private int idExo;
    private TextArea initTextArea = new TextArea();
    private String stringInitTextArea;

    private Label mods = new Label(" ");
    private ChoiceBox languages = new ChoiceBox<>();
    private Label labelInstruction;

    private CodeArea codeArea = new CodeArea();


    //Constructor VBox
        public MainView(Exercise[] exo) {
        this.exo = exo;


        //ScrollPane menuBar = new ScrollPane(new Summary(lblSTTest, exo, idExo));

            ScrollPane menuBar = new ScrollPane(new Summary(this));

        VBox rightPart = new VBox();
            Font fnt1 = new Font("Arial", 50);
            Label lbl = new Label("CODYINGGAME");
            lbl.setFont(fnt1);
            rightPart.setAlignment(Pos.CENTER);
            Text linejump = new Text("\n");
            Font fntSubtitle = new Font("Arial",15);
            labelInstruction = new Label("\nPlease click on an exercise on the left part to start ! ");
            labelInstruction.setFont(fntSubtitle);

        HBox editorAndTerminal = new HBox();

        //Initialiser selon la base de données


        VBox editorPart = new VBox(new EditorText(this));






        HBox editTerm = new HBox(new TerminalText(this));

        editorAndTerminal.getChildren().addAll(editorPart, editTerm);


        rightPart.getChildren().addAll(lbl, labelInstruction, linejump ,editorAndTerminal);

        this.getChildren().addAll(menuBar, rightPart);

    }

    public void updateIdExo(int i){
        idExo = i;
        System.out.println(idExo);
        labelInstruction.setText("\n Instruction : " + exo[idExo].Instruction);
        labelInstruction.setWrapText(true);
        labelInstruction.setMaxWidth(1300);
        languages.setVisible(true);
        initTextArea.setVisible(true);
        if(exo[idExo].ExoType == 0){
            ExerciseStdinStdout exerciseStdinStdout = (ExerciseStdinStdout)exo[idExo];
            mods.setText("Mode Stdin/Stdout ");
            languages.getItems().setAll("C", "Python", "Java", "JavaScript", "PHP");
            languages.setValue("C");
            languages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println("Vous avez sélectionné : " + newValue);
                    languages.getValue();
                }
            });
            //updateEditor(exerciseStdinStdout.inputData);

        } else if (exo[idExo].ExoType == 1) {
            ExerciseInclude exerciseInclude = (ExerciseInclude)exo[idExo];
            mods.setText("Mode Include ");
            languages.getItems().setAll(exerciseInclude.SolutionLang);
            languages.setValue(exerciseInclude.SolutionLang);
            try {
                stringInitTextArea = exerciseInclude.readLineFromFile();
                initTextArea.setText(stringInitTextArea);
            }catch (IOException e){
                System.err.println("Can't read the file " + e.getMessage());
            }



        }

    }
/*
    public void updateEditor(String[] inputData){
            initTextArea = new TextArea(String.join("\n", inputData));
    }

 */

    //public void updateLabel(int)

    public Exercise[] getExo() {
        return exo;
    }

    public int getIdExo() {
        return idExo;
    }

    public TextArea getInitTextArea() {
        return initTextArea;
    }

    public Label getMods() {
        return mods;
    }

    public ChoiceBox getLanguages() {
        return languages;
    }

    public Label getLabelInstruction() {
        return labelInstruction;
    }

    public String getStringInitTextArea() {
        return stringInitTextArea;
    }


}
