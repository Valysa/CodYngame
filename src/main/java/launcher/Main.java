package launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


import C.Languages.CLanguage;
import C.Languages.JavaLanguage;
import C.Languages.Language;
import C.Languages.LanguageFactory;
import M.Bdd;
import M.Exercise;
import M.ExerciseStdinStdout;
import M.ExerciseInclude;

public class Main {
    public static void main(String[] args) {

        Bdd.idBdd("3306", "root", "MyS3cur3P@sswOrd!");
        Bdd.create();
        Exercise[] exo = Exercise.allExo();

        for (Exercise ex : exo) {

            System.out.println(ex.toString() + "\n");
        }
        try {
            System.out.println("Java hello");

            // Call of the C part
/*            String cFile = "src/main/java/C/Test/codes-test/helloworld.c";
            Language cLanguage = LanguageFactory.assignLanguage(cFile);
            cLanguage.execute(cFile);

            // Call of the php part
            String phpFile = "src/main/java/C/Test/codes-test/helloworld.php";
            Language phpLanguage = LanguageFactory.assignLanguage(phpFile);
            phpLanguage.execute(phpFile);

            // Call of the python part
            String pyFile = "src/main/java/C/Test/codes-test/helloworld.py";
            Language pLanguage = LanguageFactory.assignLanguage(pyFile);
            pLanguage.execute(pyFile);

            // Call of the js part
            String jsFile = "src/main/java/C/Test/codes-test/helloworld.js";
            Language jsLanguage = LanguageFactory.assignLanguage(jsFile);
            jsLanguage.execute(jsFile);

            // Call of the java part (its javasception time)
            String javaFile = "src/main/java/C/Test/codes-test/helloworld.java";
            Language javaLanguage = LanguageFactory.assignLanguage(javaFile);
            javaLanguage.execute(javaFile);
*/
            //Call of the c part for two files
/*          String c2File = "src/main/java/C/ExercisesInclude/mainEx1.c";
            String c3File = "src/main/java/C/ExercisesInclude/ex1Soluce.c";
            CLanguage c2Language = new CLanguage();
            String[] cFiles = {c2File, c3File};
            c2Language.execute(cFiles);

            // Call of the php part
            String php2File = "src/main/java/C/ExercisesInclude/mainEx1.php";
            Language php2Language = LanguageFactory.assignLanguage(php2File);
            php2Language.execute(php2File);

            //Call of the python part
            String py2File = "src/main/java/C/ExercisesInclude/mainEx1.py";
            Language p2Language = LanguageFactory.assignLanguage(py2File);
            p2Language.execute(py2File);

            String js2File = "src/main/java/C/ExercisesInclude/mainEx1.mjs";
            Language js2Language = LanguageFactory.assignLanguage(js2File);
            js2Language.execute(js2File);

            // Call of the java part for two files (its javasception time)
            String java2File = "src/main/java/C/ExercisesInclude/launcher.MainEx1.java";
            String java3File = "src/main/java/C/ExercisesInclude/launcher.Ex1Soluce.java";
            String javaMainFile = "C.ExercisesInclude.launcher.MainEx1";
            JavaLanguage java2Language = new JavaLanguage();
            String[] javaFiles = {java2File, java3File};
            java2Language.execute(javaFiles, javaMainFile);
*/
            // Sort of menu displays
            System.out.println("------------------------------");
            System.out.println("             Menu             ");
            System.out.println("1-Sin/Sout exo | 2-Include exo");
            System.out.println("------------------------------");

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            if (s.equals("1")) {
                // ---------------------------------------------
                // ExerciseStdinStdout part
                // ---------------------------------------------
                // ask for the number of exercise
                //should maybe put a verification of the exercise type
                System.out.println("Select the exercise you want to do");
                String selectedExercise = bufferRead.readLine();
                /*ExerciseStdinStdout exo1 = (ExerciseStdinStdout) exo[Integer.parseInt(selectedExercise)];
                System.out.println(exo1.ExoName + " Selected");
                System.out.println(exo1.Instruction);
                System.out.print("enter the solution file name\n");
                exo1.NbTry ++;
                String filePath = bufferRead.readLine();

                // initialisating exercise file
                String file = filePath;
                Language solutionFile = LanguageFactory.assignLanguage(filePath);
                int succes = 0;
                for (int i = 1; i < 4; i++) {
                    // initialing the exercise with name description entry and expected exit
                    exo1.inputData = exo1.generateInputs(i);
                    exo1.outputData = exo1.generateOutputs(exo1.inputData);
                    //exo1.ExerciseResolution();
                    // executing file, with this specific exercise (entry and expected output)
                    // in 3 different ways; the classic, the random and the error one;
                    String[] givenResult;
                    givenResult = solutionFile.execute(file, exo1.inputData);
                    succes = 1;
                    if (exo1.checkResult(givenResult)) {
                        // case where utilisator's programm output matches the expected output
                        System.out.println("You win, congrats");
                    } else {
                        // case where it doesnt
                        System.out.println("You loose, try again by modifing your source code");
                        succes = 0;
                        if (i == 1) {
                            System.out.println("You failed the basic part");
                        }
                        if (i == 2) {
                            System.out.println("You failed the random part");
                        }
                        if (i == 1) {
                            System.out.println("You failed the error part");
                        }
                    }
                }
                if (succes == 1) {
                    exo1.NbSucess++;
                }
                System.out.println("Your stats on this exercise are : ");
                System.out.println("Try :  " + exo1.NbTry);
                System.out.println("Success :  " + exo1.NbSucess);*/
                ExerciseStdinStdout exo1 = (ExerciseStdinStdout) exo[Integer.parseInt(selectedExercise)];
                exo1.ExerciseResolution();
            }
            if (s.equals("2")) {

                    //Part that reads a file and checks that its language has the same syntax as the language requested by the exercise.
                    /*System.out.println("Enter the solution file name");
                    String filePathIncludeSolution = "src/main/java/C/ExercisesInclude/" + bufferRead.readLine();
                    try (BufferedReader FileReader = new BufferedReader(new FileReader(filePathIncludeSolution))) {
                        String line;
                        StringBuilder content = new StringBuilder();
                        while ((line = FileReader.readLine()) != null) {
                            content.append(line).append(("\n"));
                        }
                        Exercise.checkLanguage(content);
                    } catch (IOException es) {
                        System.err.println("Error during the reading file " + es.getMessage());
                    }*/
                    //Part that asks the user to enter code and checks that its language has the same syntax as the language requested by the exercise
                ExerciseInclude exampleJava1 = (ExerciseInclude) exo[6];
                exampleJava1.ExerciseResolution();
                System.out.println("Well done ! You have successfully completed this exercise in Java language");

                ExerciseInclude exampleC = (ExerciseInclude) exo[7];
                exampleC.ExerciseResolution();
                System.out.println("Well done ! You have successfully completed this exercise in C language");

                ExerciseInclude exampleJava = (ExerciseInclude) exo[8];
                exampleJava.ExerciseResolution();
                System.out.println("Well done ! You have successfully completed this exercise in Java language");

                ExerciseInclude examplePy = (ExerciseInclude) exo[9];
                examplePy.ExerciseResolution();
                System.out.println("Well done ! You have successfully completed this exercise in Py language");

                ExerciseInclude examplePHP = (ExerciseInclude) exo[10];
                examplePHP.ExerciseResolution();
                System.out.println("Well done ! You have successfully completed this exercise in PHP language");

                ExerciseInclude exampleJS = (ExerciseInclude) exo[11];
                exampleJS.ExerciseResolution();
                System.out.println("Well done ! You have successfully completed this exercise in JS language");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}