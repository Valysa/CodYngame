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

    private Language exerciseLang;
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

/*

        languages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    //ExerciseStdinStdout exerciseStdinStdout = (ExerciseStdinStdout)exo[idExo];
               // Exercise exUpdated = Bdd.take(idExo);
                System.out.println("Vous avez sélectionné : " + newValue);
                languages.getValue();
                System.out.println(languages.getValue());

                stringInitTextArea = " ";
                initTextArea.replaceText(stringInitTextArea);

                String soluceExoFile = "src/main/resources/Exercise/Exo" + idExo + "/soluceExo." + languages.getValue();System.out.println(soluceExoFile);

                exercise = LanguageFactory.assignLanguage(soluceExoFile);


                }
            });

 */

        this.getChildren().addAll(menuBar, rightPart);

    }
    public void setScore(){
        Exercise exUpdated = Bdd.take(idExo);
        this.nbTry.setText("Number of try : " + exUpdated.NbTry);
        this.nbFirstTry.setText("Number of first try : " + exUpdated.NbFirstTry);
        this.nbSuccess.setText("Number of success : " + exUpdated.NbSucess);
        this.nbSessionSucess.setText("Number of success in this session : " + exUpdated.NbSessionSucess);
    }

    public void updateResult(boolean result){
        Exercise exUpdated = Bdd.take(idExo);
        if (result){
            terminalTextArea.setText("Good Job! You're function work \n Number of try : " + exUpdated.NbTry);
            exUpdated.NbSucess++;
            exUpdated.NbSessionSucess++;

            if(nbTrySession == 1){
                exUpdated.NbFirstTry++;
                Bdd.update(idExo, 1, 1, 0, 1);
            }
            else{
                Bdd.update(idExo, 1, 1, 0, 0);
            }
            nbTrySession = 0;
        }else {
            terminalTextArea.setText("The code isn't correct! Try again!");
            Bdd.update(idExo, 1, 0, 0, 0);
        }
        setScore();
    }

    public void updateIdExo(int i){
        idExo = i;
        Exercise exUpdated = Bdd.take(idExo);
        labelInstruction.setText("\n Instruction : " + exUpdated.Instruction);
        labelInstruction.setWrapText(true);
        labelInstruction.setMaxWidth(1300);
        languages.setVisible(true);
        initTextArea.setVisible(true);
        terminalTextArea.setVisible(true);
        setScore();
        System.out.println(exUpdated.ExoType);

        if(exUpdated.ExoType == 0){
            ExerciseStdinStdout exerciseStdinStdout = (ExerciseStdinStdout)exo[idExo-1];
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
        } else if (exUpdated.ExoType == 1) {
            System.out.println(languages.getValue());
            ExerciseInclude exerciseInclude = (ExerciseInclude)exo[idExo-1];
            mods.setText("Mode Include ");
            System.out.println(languages.getValue());
            languages.getItems().setAll(exerciseInclude.SolutionLang);
            System.out.println(languages.getValue());
            languages.setValue(exerciseInclude.SolutionLang);
            System.out.println(languages.getValue());
            try {
                stringInitTextArea = exerciseInclude.readLineFromFile();
                initTextArea.replaceText(stringInitTextArea);
            }catch (IOException e){
                System.err.println("Can't read the file " + e.getMessage());
            }
            String soluceExoFile = "src/main/resources/Exercise/Exo" + this.idExo + "/soluceExo." + this.languages.getValue();
            exerciseLang = LanguageFactory.assignLanguage(soluceExoFile);


        }

    }

    public void exerciseResolutionFXStdinStdout(){
        Exercise exUpdated = Bdd.take(idExo);
        exUpdated.NbTry ++;
        nbTrySession++;
        ExerciseStdinStdout exerciseStdinStdout = (ExerciseStdinStdout)exUpdated;

        boolean result = false;
        String language = " ";
        language = languages.getValue().toString();
        nbTrySession++;
        stringInitTextArea = initTextArea.getText();
        System.out.println("You have coded this program :");
        System.out.println(stringInitTextArea);
        //Check if the code entered by the user is in the good language
        StringBuilder initTextAreaStringBuilder = new StringBuilder(stringInitTextArea);
        String userExoFile = "src/main/resources/Exercise/Exo" + idExo + "/userExo." + language;
        Language langExecutor = LanguageFactory.assignLanguage(userExoFile);

        if (langExecutor != null) {
            exerciseStdinStdout.saveToFile(initTextAreaStringBuilder, language);
            int succes = 0;
            for (int i = 1; i < 4; i++) {
                exerciseStdinStdout.inputData = exerciseStdinStdout.generateInputs(i);
                System.out.println(exerciseStdinStdout.inputData);
                exerciseStdinStdout.outputData = exerciseStdinStdout.generateOutputs(exerciseStdinStdout.inputData);
                System.out.println(exerciseStdinStdout.outputData);
                String[] givenResult;
                try {
                    givenResult = langExecutor.execute(userExoFile, exerciseStdinStdout.inputData);
                    if (exerciseStdinStdout.checkResult(givenResult)) {
                        System.out.println("You win, congrats");
                        succes++;
                    } else {
                        System.out.println("You lose, try again by modifying your source code");
                        if (i == 1) {
                            System.out.println("You failed the basic part");
                        }
                        if (i == 2) {
                            System.out.println("You failed the random part");
                        }
                        if (i == 3) {
                            System.out.println("You failed the error part");
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (succes == 3) {
                result = true;
            }

            exerciseStdinStdout.deleteUserFile(language);
        } else {
            System.out.println("Language executor not found for: " + language);
        }
        updateResult(result);
    }
    public void exerciseResolutionFXInclude() {
        Exercise exUpdated = Bdd.take(idExo);
        boolean result = false;

        ExerciseInclude exerciseInclude = (ExerciseInclude)exUpdated;
            exUpdated.NbTry++;
            nbTrySession++;
            stringInitTextArea = initTextArea.getText();
            System.out.println("You have coded this program :");
            System.out.println(stringInitTextArea);
            //Check if the code entered by the user is in the good language
            StringBuilder initTextAreaStringBuilder = new StringBuilder(stringInitTextArea);
            if (exerciseLang.checkLanguage(initTextAreaStringBuilder)) {
                //Save the code in a file named userExo.c here
                exerciseInclude.saveToFile(initTextAreaStringBuilder);
                String file = "src/main/resources/Exercise/Exo" + idExo + "/mainExo." + exerciseInclude.SolutionLang;
                Language language = LanguageFactory.assignLanguage(file);
                if(language instanceof JavaLanguage){
                    String soluceExo = "src/main/resources/Exercise/Exo" + idExo + "/soluceExo." + exerciseInclude.SolutionLang;
                    String userExo = "src/main/resources/Exercise/Exo" + idExo + "/userExo." + exerciseInclude.SolutionLang;
                    String mainFile = "Exo" + idExo + "/mainExo";
                    String[] files = {file, soluceExo, userExo};

                    result = ((JavaLanguage) language).execute(files, mainFile);
                }else {
                    System.out.println(file);
                    result = language.execute(file);
                }
                //We delete the file containing the user's code when he has successfully completed the exercise
                exerciseInclude.deleteUserFile();
            }
            updateResult(result);


    }

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
