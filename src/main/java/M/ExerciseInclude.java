package M;

import C.Languages.JavaLanguage;
import C.Languages.Language;
import C.Languages.LanguageFactory;
import javafx.beans.property.adapter.JavaBeanBooleanProperty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The ExerciseInclude class represents an exercise that includes code in various languages.
 * It extends the Exercise class.
 */

public class ExerciseInclude extends Exercise {

    /**
     * The solution language for the exercise.
     */
    public String SolutionLang;


    /**
     * Constructs an ExerciseInclude instance with the given parameters.
     *
     * @param id the ID of the exercise.
     * @param ExoType the type of the exercise.
     * @param ExoName the name of the exercise.
     * @param Instruction the instructions for the exercise.
     * @param SolutionLang the solution language for the exercise.
     * @param SolutionCode the solution code for the exercise.
     * @param GeneratorCode the generator code for the exercise.
     * @param MainCode the main code for the exercise.
     * @param NbTry the number of tries for the exercise.
     * @param NbSucess the number of successes for the exercise.
     * @param NbSessionSucess the number of session successes for the exercise.
     * @param NbFirstTry the number of first tries for the exercise.
     */
    ExerciseInclude(int id, int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, String MainCode, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry){
        super(id, ExoType, ExoName, Instruction, SolutionLang, SolutionCode, GeneratorCode, NbTry, NbSucess, NbSessionSucess, NbFirstTry);

        switch (SolutionLang) {
            case 0:
                this.SolutionLang = "c";
                this.writeInclude(MainCode,"c");
                break;
            case 1:
                this.SolutionLang = "py";
                this.writeInclude(MainCode,"py");
                break;
            case 2:
                this.SolutionLang = "java";
                this.writeInclude(MainCode,"java");
                break;
            case 3:
                this.SolutionLang = "php";
                this.writeInclude(MainCode,"php");
                break;
            case 4:
                this.SolutionLang = "js";
                this.writeInclude(MainCode,"js");
                break;
            case 5:
                this.SolutionLang = "mjs";
                this.writeInclude(MainCode,"mjs"); //This extension serve for import and export functions in JS in the include mode
                break;
        }

    }

    /**
     * Reads the line from the genExo file and displays the type, name, and parameters of the function.
     *
     * @return a substring of the line until the first occurrence of '{' or ':'.
     * @throws IOException if an I/O error occurs.
     */

    //We read the genExo file and display the type, name and parameters of the function
    public String readLineFromFile() throws IOException {
        String genExoFile = "src/main/resources/Exercise/Exo" + this.Id + "/genExo." + this.SolutionLang;
        //Creates a BufferedReader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(genExoFile));
        String line;
        //Reads each line until a line with '{' or ':' is found
        while ((line = reader.readLine()) != null) {
            //Searches for the index of the first occurrence of '{' and ':'
            int indexOfBrace = line.indexOf('{');
            int indexOfTwoPoints = line.indexOf(':');
            //Checks if '{' or ':' is found in the line
            if (indexOfBrace != -1 || indexOfTwoPoints != -1) {
                //Determines the end index of the substring by taking the minimum of the index of '{' and ':'
                int endIndex = Math.min(indexOfBrace != -1 ? indexOfBrace : Integer.MAX_VALUE,
                                        indexOfTwoPoints != -1 ? indexOfTwoPoints : Integer.MAX_VALUE);
                return line.substring(0, endIndex);
                //Returns the substring until the index just before '{' or ':'
            }
        }
        reader.close();
        return null; //returns null if no line with '{' or ':' is found in the file
    }

    /**
     * Writes the main code to the include directory for the specified solution language.
     *
     * @param MainCode the main code to be included.
     * @param SolutionLang the solution language.
     */

    public void writeInclude(String MainCode, String SolutionLang) {

        String PATH = "src/main/resources/Exercise/Exo"+this.Id+"/";
        try {
            Files.createDirectories(Paths.get(PATH));
        }catch (IOException e){
            System.out.println("Cannot access resources directory : " + e.getMessage());
        }
        String main = PATH + "mainExo." + SolutionLang;
        try {
            FileWriter mainF = new FileWriter(main);
            mainF.write(MainCode);
            mainF.close();

        } catch (IOException e){
            System.out.println("Cannot write the file main : " + e.getMessage());
        }
    }

    /**
     * Saves the user's code to a file named userExo.extension (e.g., c, py, js, php, java).
     *
     * @param program the code entered by the user.
     */

    //This function saves the code entered by the user in a file named userExo.extension ie c,py,js,php,java
    public void saveToFile(StringBuilder program) {
        String PATH = "src/main/resources/Exercise/Exo"+this.Id+"/";
        try {
            Files.createDirectories(Paths.get(PATH));
        }catch (IOException e){
            System.out.println("Cannot access resources directory : " + e.getMessage());
        }
        String filePath = PATH + "userExo." + this.SolutionLang;
        File file = new File(filePath);
        try {
            FileWriter mainF = new FileWriter(filePath);
            if(Objects.equals(SolutionLang, "mjs")){
                mainF.write("export " + program);
            }
            else if(Objects.equals(SolutionLang, "php")){
                mainF.write("<?php\n" + program + "?>");
            }
            else if(Objects.equals(SolutionLang, "java")){
                mainF.write("package Exo" + this.Id + ";\n\n" + "public class userExo {" + "\n\n" + program + "}");
            }
            else {
                mainF.write(String.valueOf(program));
            }
            mainF.close();
        } catch (IOException e){
            System.out.println("Cannot write the file main : " + e.getMessage());
        }
    }

    /**
     * Deletes the user's code file.
     */

    public void deleteUserFile(){
        Path path = Paths.get( "src/main/resources/Exercise/Exo"+this.Id+"/userExo."+SolutionLang);
        try {
            Files.delete(path);
        } catch (IOException e){
            System.err.println("Failed to delete the file: " +e.getMessage());
        }
        if(Objects.equals(this.SolutionLang, "java")){
            Path Solucepath = Paths.get( "src/main/resources/Exercise/Exo"+this.Id+"/soluceExo.class");
            Path Userpath = Paths.get( "src/main/resources/Exercise/Exo"+this.Id+"/userExo.class");
            Path Mainpath = Paths.get( "src/main/resources/Exercise/Exo"+this.Id+"/mainExo.class");
            try {
                Files.delete(Solucepath);
            } catch (IOException e){
                System.err.println("Failed to delete the file: " +e.getMessage());
            }
            try {
                Files.delete(Userpath);
            } catch (IOException e){
                System.err.println("Failed to delete the file: " +e.getMessage());
            }
            try {
                Files.delete(Mainpath);
            } catch (IOException e){
                System.err.println("Failed to delete the file: " +e.getMessage());
            }
        }
    }

    /**
     * Executes the exercise resolution process.
     * Displays the exercise name and instructions, reads the function details, and handles user input to resolve the exercise.
     */

    public void ExerciseResolution() {
        System.out.println(this.ExoName);
        System.out.println(this.Instruction);
        String function = null;
        try {
            function = this.readLineFromFile();
            //Display the type, name and various parameters of the function requested for this exercise
            if (function != null) {
                System.out.println(function);
            } else {
                System.out.println("No lines with '{' or ':' were found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String soluceExoFile = "src/main/resources/Exercise/Exo" + this.Id + "/soluceExo." + this.SolutionLang;
        Language Exercise = LanguageFactory.assignLanguage(soluceExoFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isProgramCorrect = false;
        //Repeat this loop until the code entered by the user is correct
        while(!isProgramCorrect) {
            System.out.println("Enter your program to resolve this exercise (Write exit at the end of your program to send him)");
            try {
                this.NbTry++;
                StringBuilder program = new StringBuilder();
                String line;
                //Repeat this loop until the user has entered "exit"
                while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
                    program.append(line).append("\n");
                }
                //Display the code entered by the user
                System.out.println("You have coded this program :");
                System.out.println(program.toString());
                //Check if the code entered by the user is in the good language
                if (Exercise.checkLanguage(program)) {
                    //Save the code in a file named userExo.c here
                    this.saveToFile(program);
                    String File = "src/main/resources/Exercise/Exo" + this.Id + "/mainExo." + this.SolutionLang;
                    Language Language = LanguageFactory.assignLanguage(File);
                    if(Language instanceof JavaLanguage){
                        String soluceExo = "src/main/resources/Exercise/Exo" + this.Id + "/soluceExo." + this.SolutionLang;
                        String userExo = "src/main/resources/Exercise/Exo" + this.Id + "/userExo." + this.SolutionLang;
                        String mainFile = "Exo" + this.Id + "/mainExo";
                        String[] files = {File, soluceExo, userExo};
                        if(((JavaLanguage) Language).execute(files, mainFile))
                            isProgramCorrect = true;
                    }else {
                        if(Language.execute(File)){
                            isProgramCorrect = true;
                        }
                    }
                    //We delete the file containing the user's code when he has programming in the good language
                    this.deleteUserFile();
                }
            } catch (IOException es) {
                es.printStackTrace();
            }
        }
    }

}
