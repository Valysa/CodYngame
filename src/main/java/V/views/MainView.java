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
    private String stringTerminalArea;

    private Label mods = new Label(" ");
    private ChoiceBox languages = new ChoiceBox<>();
    private Label labelInstruction;

    private HBox editorAndTerminal = new HBox();
    private VBox rightPart = new VBox();
    /*
    private Language exerciseLang;
     */
    private Label nbTry = new Label();
    private Label nbSuccess = new Label();
    private Label nbSessionSucess = new Label();
    private Label nbFirstTry = new Label();

    private int[] nbTrySession;


    /**
     * Constructor of the MainView of the application.
     *
     * @return Summary on the left and Main Scene on the right
     */
    public MainView() {
        Bdd.idBdd("3306", "root", "MyS3cur3P@sswOrd!");
        Bdd.create();
        Exercise[] exo = Exercise.allExo();
        this.exo = exo;

        //initialize sessions param
        nbTrySession = new int[Bdd.count()+1];
        for(int i = 1; i<Bdd.count(); i++){
            Exercise exUpdated = Bdd.take(i);
            exUpdated.NbSessionSucess = 0;
            nbTrySession[i] = 0;
        }

        ScrollPane menuBar = new ScrollPane(new Summary(this));

            Font fnt1 = new Font("Arial", 50);
            Label titleGame = new Label("CODYINGAME");
            titleGame.setFont(fnt1);
            titleGame.setMaxHeight(100);
            titleGame.setMinHeight(100);
            titleGame.setPrefHeight(100);
            rightPart.setAlignment(Pos.CENTER);
            Text linejump = new Text("\n");
            Font fntSubtitle = new Font("Arial",15);
            labelInstruction = new Label("Codyingame is a fun application that allows you to practice coding through numerous exercises in different programming languages.\nAll exercises are included in 2 different modes: \"Stdin/Stdout\" and \"Include\".\nIn Stdin/Stdout mode you must retrieve inputs via a scanf and modify these inputs so that the outgoing data corresponds to the instructions of the exercise.\nIn Include mode, you must program a function called by a hidden main, with input data in parameters and data to output in a return.\nGood luck and have fun\nClick on an exercise to start playing");
            labelInstruction.setFont(fntSubtitle);
            labelInstruction.setWrapText(true);
            labelInstruction.setMinHeight(100);

        rightPart.setMaxWidth(2000.0);
        languages.getItems().setAll("c", "py", "java", "js", "php");
        languages.setValue("c");
        VBox editorPart = new VBox(new EditorText(this));

        HBox editTerm = new HBox(new TerminalText(this));


        editorAndTerminal.setVisible(false);
        editorAndTerminal.getChildren().addAll(editorPart, editTerm);

        rightPart.getChildren().addAll(titleGame, labelInstruction, linejump, editorAndTerminal);

        this.getChildren().addAll(menuBar, rightPart);

    }

    /**
     * Modify the intern score variables.
     */
    public void setScore(){
        Exercise exUpdated = Bdd.take(idExo);
        this.nbTry.setText("Number of try : " + exUpdated.NbTry);
        this.nbFirstTry.setText("Number of first try : " + exUpdated.NbFirstTry);
        this.nbSuccess.setText("Number of success : " + exUpdated.NbSucess);
        this.nbSessionSucess.setText("Number of success in this session : " + exUpdated.NbSessionSucess);
    }

    /**
     * Update Result of the Include exercise.
     *
     * @param result true if the exercise is correct | false if it's not
     */
    public void updateResult(boolean result){
        Exercise exUpdated = Bdd.take(idExo);
        if (result){
            terminalTextArea.setText("Good Job! You're function work \n Number of try : " + nbTrySession[idExo]);
            exUpdated.NbSucess++;
            exUpdated.NbSessionSucess++;

            if(nbTrySession[idExo] == 1){
                exUpdated.NbFirstTry++;
                Bdd.update(idExo, 1, 1, 1, 1);
            }
            else{
                Bdd.update(idExo, 1, 1, 1, 0);
            }
            nbTrySession[idExo] = 0;
        }else {
            terminalTextArea.setText("The code isn't correct! Try again!");
            Bdd.update(idExo, 1, 0, 0, 0);
        }
        setScore();
    }
    /**
     * Update Result of the Stdin/Stdout exercise.
     *
     * @param result true if the exercise is correct | false if it's not
     * @param errors In case there are error(s)
     */
    public void updateResult(boolean result, String errors){
        Exercise exUpdated = Bdd.take(idExo);
        if(!errors.isBlank()){
            terminalTextArea.setText("Error detected" + errors);
        }else if (result){
            terminalTextArea.setText("Good Job! You're function work \nNumber of try in the session : " + exUpdated.NbTry);
            exUpdated.NbSucess++;
            exUpdated.NbSessionSucess++;

            if(nbTrySession[idExo] == 1){
                exUpdated.NbFirstTry++;
                Bdd.update(idExo, 1, 1, 1, 1);
            }
            else{
                Bdd.update(idExo, 1, 1, 1, 0);
            }
            nbTrySession[idExo] = 0;
        }else {
            terminalTextArea.setText(stringTerminalArea + "The code doesn't work well! Try again!");
            Bdd.update(idExo, 1, 0, 0, 0);
        }
        setScore();
    }

    /**
     * Function updated each time you click on a button (Summary).
     *
     * @param i the id of the exercise
     */
    public void updateIdExo(int i){
        //initialize idExo & exUpdated for the exercise chosen
        idExo = i;
        Exercise exUpdated = Bdd.take(idExo);
        //Replace the instruction by the correct instruction
        labelInstruction.setText("\n Instruction : " + exUpdated.Instruction);
        labelInstruction.setMaxWidth(1300);
        //Show Editor, terminal and Stats
        editorAndTerminal.setVisible(true);
        terminalTextArea.setText("Exercise " + idExo + " chosen\n");
        setScore();
        //STDIN/STDOUT MOD
        if(exUpdated.ExoType == 0){
            ExerciseStdinStdout exerciseStdinStdout = (ExerciseStdinStdout)exo[idExo-1];
            mods.setText("Mode Stdin/Stdout ");
            languages.getItems().setAll("c", "py", "java", "js", "php");
            languages.setValue("c");
            //INCLUDE MOD
        } else if (exUpdated.ExoType == 1) {
            ExerciseInclude exerciseInclude = (ExerciseInclude)exo[idExo-1];
            mods.setText("Mode Include ");
            languages.getItems().setAll(exerciseInclude.SolutionLang);
            languages.setValue(exerciseInclude.SolutionLang);
            try {
                stringInitTextArea = exerciseInclude.readLineFromFile();
                initTextArea.replaceText(stringInitTextArea);
            }catch (IOException e){
                System.err.println("Can't read the file " + e.getMessage());
            }
        }
    }

    /**
     * Execution and Resolution of a Stdin/Stdout exercise.
     */
    public void exerciseResolutionFXStdinStdout(){
        Exercise exUpdated = Bdd.take(idExo);
        exUpdated.NbTry ++;
        nbTrySession[idExo]++;
        ExerciseStdinStdout exerciseStdinStdout = (ExerciseStdinStdout)exUpdated;

        boolean result = false;
        String language = " ";
        language = languages.getValue().toString();
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
            String errors = "";
            for (int i = 1; i < 4; i++) {
                exerciseStdinStdout.inputData = exerciseStdinStdout.generateInputs(i);
                exerciseStdinStdout.outputData = exerciseStdinStdout.generateOutputs(exerciseStdinStdout.inputData);

                String[] givenResult;
                try {
                    givenResult = langExecutor.execute(userExoFile, exerciseStdinStdout.inputData);
                    if (exerciseStdinStdout.checkResult(givenResult)) {
                        succes++;
                    } else {
                        stringTerminalArea = exerciseStdinStdout.takeResult(givenResult);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (succes == 3) {
                result = true;

            }
            exerciseStdinStdout.deleteUserFile(language);
            updateResult(result, errors);
        } else {
            System.out.println("Language executor not found for: " + language);
        }

    }
    /**
     * Execution and Resolution of an Include exercise.
     */
    public void exerciseResolutionFXInclude() {
        Exercise exUpdated = Bdd.take(idExo);
        boolean result = false;
        String soluceExoFile = "src/main/resources/Exercise/Exo" + this.idExo + "/soluceExo." + this.languages.getValue();
        Language exerciseLang = LanguageFactory.assignLanguage(soluceExoFile);
        ExerciseInclude exerciseInclude = (ExerciseInclude)exUpdated;
        exUpdated.NbTry++;
        nbTrySession[idExo]++;
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
    /**
     * Getter idExo
     *
     * @return idExo
     */
    public int getIdExo() {
        return idExo;
    }
    /**
     * Getter initTextArea
     *
     * @return initTextArea
     */
    public CodeArea getInitTextArea() {
        return initTextArea;
    }
    /**
     * Getter mods
     *
     * @return mods
     */
    public Label getMods() {
        return mods;
    }
    /**
     * Getter languages
     *
     * @return languages
     */
    public ChoiceBox getLanguages() {
        return languages;
    }
    /**
     * Getter stringInitTextArea
     *
     * @return stringInitTextArea
     */
    public String getStringInitTextArea() {
        return stringInitTextArea;
    }
    /**
     * Getter terminalTextArea
     *
     * @return terminalTextArea
     */
    public TextArea getTerminalTextArea() {
        return terminalTextArea;
    }
    /**
     * Getter nbTry
     *
     * @return nbTry
     */
    public Label getNbTry() {
        return nbTry;
    }
    /**
     * Getter nbSuccess
     *
     * @return nbSuccess
     */
    public Label getNbSuccess() {
        return nbSuccess;
    }
    /**
     * Getter nbSessionSucess
     *
     * @return nbSessionSucess
     */
    public Label getNbSessionSucess() {
        return nbSessionSucess;
    }
    /**
     * Getter nbFirstTry
     *
     * @return nbFirstTry
     */
    public Label getNbFirstTry() {
        return nbFirstTry;
    }
    /**
     * Setter
     *
     * @param stringInitTextArea to display the init text for the editor text
     */
    public void setStringInitTextArea(String stringInitTextArea) {
        this.stringInitTextArea = stringInitTextArea;
    }
}
