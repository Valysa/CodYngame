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


    //Constructor VBox
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
            rightPart.setAlignment(Pos.CENTER);
            Text linejump = new Text("\n");
            Font fntSubtitle = new Font("Arial",15);
            labelInstruction = new Label("\nPlease click on an exercise on the left part to start ! \n test\n test\n test\n test\n test\n test\n test\n test\n test\n test\n test");
            labelInstruction.setFont(fntSubtitle);


        languages.getItems().setAll("c", "py", "java", "js", "php");
        languages.setValue("c");
        VBox editorPart = new VBox(new EditorText(this));

        HBox editTerm = new HBox(new TerminalText(this));

        VBox testController = new VBox();


        testController.getChildren().addAll(nbTry, nbSuccess, nbSessionSucess, nbFirstTry);

        editorAndTerminal.setVisible(false);
        editorAndTerminal.getChildren().addAll(editorPart, editTerm, testController);

        rightPart.getChildren().addAll(titleGame, labelInstruction, linejump, editorAndTerminal);

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

            if(nbTrySession[idExo] == 1){
                exUpdated.NbFirstTry++;
                Bdd.update(idExo, 1, 1, 0, 1);
            }
            else{
                Bdd.update(idExo, 1, 1, 0, 0);
            }
            nbTrySession[idExo] = 0;
        }else {
            terminalTextArea.setText("The code isn't correct! Try again!");
            Bdd.update(idExo, 1, 0, 0, 0);
        }
        setScore();
    }

    public void updateResult(boolean result, String errors){
        Exercise exUpdated = Bdd.take(idExo);
        if(errors.isBlank()){
            terminalTextArea.setText("Error detected" + errors);
        }else if (result){
            terminalTextArea.setText("Good Job! You're function work \n Number of try : " + exUpdated.NbTry);
            exUpdated.NbSucess++;
            exUpdated.NbSessionSucess++;

            if(nbTrySession[idExo] == 1){
                exUpdated.NbFirstTry++;
                Bdd.update(idExo, 1, 1, 0, 1);
            }
            else{
                Bdd.update(idExo, 1, 1, 0, 0);
            }
            nbTrySession[idExo] = 0;
        }else {
            terminalTextArea.setText("The code isn't correct! Try again!");
            Bdd.update(idExo, 1, 0, 0, 0);
        }
        setScore();
    }


    //Function updated each time you click on a button (Summary)
    public void updateIdExo(int i){
        //initialize idExo & exUpdated for the exercise chosen
        idExo = i;
        Exercise exUpdated = Bdd.take(idExo);
        //Replace the instruction by the correct instruction
        labelInstruction.setText("\n Instruction : " + exUpdated.Instruction);
        labelInstruction.setWrapText(true);
        labelInstruction.setMaxWidth(1300);
        //Show Editor, terminal and Stats
        editorAndTerminal.setVisible(true);
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
            updateResult(result, errors);
        } else {
            System.out.println("Language executor not found for: " + language);
        }

    }
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

    public void setInitTextArea(CodeArea initTextArea) {
        this.initTextArea = initTextArea;
    }

    public void setStringInitTextArea(String stringInitTextArea) {
        this.stringInitTextArea = stringInitTextArea;
    }
}
