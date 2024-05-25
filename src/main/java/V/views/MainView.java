package V.views;


import C.Languages.JavaLanguage;
import C.Languages.Language;
import C.Languages.LanguageFactory;
import M.Bdd;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


public class MainView extends HBox {

    private Exercise[] exo;
    private int idExo;
    private CodeArea initTextArea = new CodeArea();
    private String stringInitTextArea;

    private TextArea terminalTextArea = new TextArea();

    private Label mods = new Label(" ");
    private ChoiceBox languages = new ChoiceBox<>();
    private Label labelInstruction;

    private Language exercise;
    private Label nbTry = new Label();
    private Label nbSuccess = new Label();
    private Label nbSessionSucess = new Label();
    private Label nbFirstTry = new Label();

    private int nbTrySession = 0;


    //Constructor VBox
        public MainView() {
            Bdd.idBdd("3306", "root", "MyS3cur3P@sswOrd!");
            Bdd.create();
            Exercise[] exo = Exercise.allExo();
            System.out.println(exo[2].NbTry + " " + exo[2].NbSucess + " " + exo[2].NbSessionSucess + " " + exo[2].NbFirstTry);

            //initialize sessions param
            for(int i =0; i< exo.length; i++){
                exo[i].NbSessionSucess = 0;
            }
        this.exo = exo;

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

        languages.getItems().setAll("c", "py", "java", "js", "php");
        languages.setValue("c");
        VBox editorPart = new VBox(new EditorText(this));

        HBox editTerm = new HBox(new TerminalText(this));

        VBox testController = new VBox();


        testController.getChildren().addAll(nbTry, nbSuccess, nbSessionSucess, nbFirstTry);


        editorAndTerminal.getChildren().addAll(editorPart, editTerm, testController);

        rightPart.getChildren().addAll(lbl, labelInstruction, linejump ,editorAndTerminal);

        this.getChildren().addAll(menuBar, rightPart);

    }
    public void setScore(){
        this.nbTry.setText("Number of try : " + this.exo[idExo].NbTry);
        this.nbFirstTry.setText("Number of first try : " + this.exo[idExo].NbFirstTry);
        this.nbSuccess.setText("Number of success : " + this.exo[idExo].NbSucess);
        this.nbSessionSucess.setText("Number of success in this session : " + this.exo[idExo].NbSessionSucess);
    }
    public void updateIdExo(int i){
        idExo = i;
        System.out.println(idExo);
        labelInstruction.setText("\n Instruction : " + exo[idExo].Instruction);
        labelInstruction.setWrapText(true);
        labelInstruction.setMaxWidth(1300);
        languages.setVisible(true);
        initTextArea.setVisible(true);
        terminalTextArea.setVisible(true);
        setScore();

        if(exo[idExo].ExoType == 0){
            ExerciseStdinStdout exerciseStdinStdout = (ExerciseStdinStdout)exo[idExo];
            mods.setText("Mode Stdin/Stdout ");
            languages.getItems().setAll("c", "py", "java", "js", "php");
            languages.setValue("c");
            /*
            languages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println("Vous avez sélectionné : " + newValue);
                    languages.getValue();
                }
            });

             */
            //updateEditor(exerciseStdinStdout.inputData);

        } else if (exo[idExo].ExoType == 1) {
            ExerciseInclude exerciseInclude = (ExerciseInclude)exo[idExo];
            mods.setText("Mode Include ");
            languages.getItems().setAll(exerciseInclude.SolutionLang);
            languages.setValue(exerciseInclude.SolutionLang);
            try {
                stringInitTextArea = exerciseInclude.readLineFromFile();
                initTextArea.replaceText(stringInitTextArea);
            }catch (IOException e){
                System.err.println("Can't read the file " + e.getMessage());
            }
            String soluceExoFile = "src/main/resources/Exercise/Exo" + this.idExo + "/soluceExo." + this.languages.getValue();
            exercise = LanguageFactory.assignLanguage(soluceExoFile);


        }

    }


    public void exerciseResolutionFXInclude() {
        boolean result = false;
        ExerciseInclude exerciseInclude = (ExerciseInclude)exo[idExo];
        //Create idExoBDD --> ex[idExo] correspond to ex n°(idExo +1)
        int idExoBDD = idExo + 1;
            this.exo[this.idExo].NbTry++;
            nbTrySession++;
            stringInitTextArea = initTextArea.getText();
            System.out.println("You have coded this program :");
            System.out.println(stringInitTextArea);
            //Check if the code entered by the user is in the good language
            StringBuilder initTextAreaStringBuilder = new StringBuilder(stringInitTextArea);
            if (exercise.checkLanguage(initTextAreaStringBuilder)) {
                //Save the code in a file named userExo.c here
                    exerciseInclude.saveToFile(initTextAreaStringBuilder);
                    String file = "src/main/resources/Exercise/Exo" + idExoBDD + "/mainExo." + exerciseInclude.SolutionLang;
                    Language language = LanguageFactory.assignLanguage(file);
                    if(language instanceof JavaLanguage){
                        String soluceExo = "src/main/resources/Exercise/Exo" + idExoBDD + "/soluceExo." + exerciseInclude.SolutionLang;
                        String userExo = "src/main/resources/Exercise/Exo" + idExoBDD + "/userExo." + exerciseInclude.SolutionLang;
                        String mainFile = "Exo" + idExoBDD + "/mainExo";
                        String[] files = {file, soluceExo, userExo};

                        result = ((JavaLanguage) language).execute(files, mainFile);
                    }else {
                        System.out.println(file);
                        result = language.execute(file);
                    }
            }
        //We delete the file containing the user's code when he has successfully completed the exercise
        exerciseInclude.deleteUserFile();
            if (result){
                terminalTextArea.setText("Good Job! You're function work \n Number of try : " + exo[idExo].NbTry);
                exo[idExo].NbSucess++;
                exo[idExo].NbSessionSucess++;

                if(nbTrySession == 1){
                    exo[idExo].NbFirstTry++;
                    Bdd.update(idExoBDD, 1, 1, 0, 1);
                }
                else{
                    Bdd.update(idExoBDD, 1, 1, 0, 0);
                }
                nbTrySession = 0;
                System.out.println(exerciseInclude.NbTry + " " + exerciseInclude.NbSucess + " " + exerciseInclude.NbSessionSucess + " " + exerciseInclude.NbFirstTry);
            }else {
                terminalTextArea.setText("The code isn't correct! Try again!");
                Bdd.update(idExoBDD, 1, 0, 0, 0);
            }
        setScore();

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

    public CodeArea getInitTextArea() {
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

    public TextArea getTerminalTextArea() {
        return terminalTextArea;
    }

}
