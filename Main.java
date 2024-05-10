import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import C.Exercise.ExerciseStdinStdout;
import C.Languages.CLanguage;
import C.Languages.JavaLanguage;
import C.Languages.Language;
import C.Languages.LanguageFactory;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Java hello");

            // Call of the C part
            String cFile = "C/Test/codes-test/helloworld.c";
            Language cLanguage = LanguageFactory.assignLanguage(cFile);
            cLanguage.execute(cFile);

            // Call of the php part
            String phpFile = "C/Test/codes-test/helloworld.php";
            Language phpLanguage = LanguageFactory.assignLanguage(phpFile);
            phpLanguage.execute(phpFile);

            // Call of the python part
            String pyFile = "C/Test/codes-test/helloworld.py";
            Language pLanguage = LanguageFactory.assignLanguage(pyFile);
            pLanguage.execute(pyFile);

            // Call of the js part
            String jsFile = "C/Test/codes-test/helloworld.js";
            Language jsLanguage = LanguageFactory.assignLanguage(jsFile);
            jsLanguage.execute(jsFile);

            // Call of the java part (its javasception time)
            String javaFile = "C/Test/codes-test/helloworld.java";
            Language javaLanguage = LanguageFactory.assignLanguage(javaFile);
            javaLanguage.execute(javaFile);

            //Call of the c part for two files
            String c2File = "ExercisesInclude/mainEx1.c";
            String c3File = "ExercisesInclude/ex1Soluce.c";
            CLanguage c2Language = new CLanguage();
            String[] cFiles = {c2File, c3File};
            c2Language.execute(cFiles);

            // Call of the php part for two files
            String php2File = "ExercisesInclude/mainEx1.php";
            Language ph2pLanguage = LanguageFactory.assignLanguage(php2File);
            ph2pLanguage.execute(php2File);

            //Call of the python part for two files
            String py2File = "ExercisesInclude/mainEx1.py";
            Language p2Language = LanguageFactory.assignLanguage(py2File);
            p2Language.execute(py2File);

            // Call of the js part for two files
            String js2File = "ExercisesInclude/mainEx1.mjs";
            Language js2Language = LanguageFactory.assignLanguage(js2File);
            js2Language.execute(js2File);

            // Call of the java part for two files (its javasception time)
            String java2File = "ExercisesInclude/mainEx1.java";
            String java3File = "ExercisesInclude/ex1Soluce.java";
            String javaMainFile = "ExercisesInclude/mainEx1";
            JavaLanguage java2Language = new JavaLanguage();
            String[] javaFiles = {java2File, java3File};
            java2Language.execute(javaFiles, javaMainFile);

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
                System.out.println("Select the exercise you want to do");
                String selectedExercise = bufferRead.readLine();

                // Creates exercises
                ExerciseStdinStdout exo = new ExerciseStdinStdout();
                // if exo1 selected
                if (selectedExercise.equals("1")) {
                    // initialing the exercise with name description entry and expected exit
                    exo.name = "exo1";
                    exo.description = "You will be given in entry a list of 10 integer and we ask you to give back their values multiplied by two";
                    exo.entryData = new String[10];
                    exo.outputData = new String[10];
                    for (int i = 0; i < 10; i++) {
                        exo.entryData[i] = String.valueOf(i);
                        exo.outputData[i] = String.valueOf(i * 2);
                    }
                    System.out.println("The selected exercise is : " + exo.name);
                } else if (selectedExercise.equals("2")) {
                    // initialing the exercise with name description entry and expected exit
                    exo.name = "exo2";
                    exo.description = "You will be given in entry a list of 10 integer and we ask you to give back their values multiplied by ten";
                    exo.entryData = new String[10];
                    exo.outputData = new String[10];
                    for (int i = 0; i < 10; i++) {
                        exo.entryData[i] = String.valueOf(i);
                        exo.outputData[i] = String.valueOf(i * 10);
                    }
                    System.out.println("The selected exercise is : " + exo.name);
                } else {
                    // initialing the exercise with name description entry and expected exit
                    exo.name = "error exo";
                    exo.description = "This exercise does not exist";
                    exo.entryData = new String[10];
                    exo.outputData = new String[10];
                    for (int i = 0; i < 10; i++) {
                        exo.entryData[i] = String.valueOf(i);
                        exo.outputData[i] = String.valueOf(i * 10);
                    }
                    System.out.println("The selected exercise is : " + exo.name);
                }

                // ask the file for the first exercise
                System.out.print("enter the file name\n");
                String filePath = bufferRead.readLine();

                // initialisating exercise file
                String file = filePath;
                Language solutionFile = LanguageFactory.assignLanguage(filePath);
                // executing file, with this specific exercise (entry and expected output)
                String[] givenResult;
                givenResult = solutionFile.execute(file, exo.entryData);
                System.out.println(Arrays.toString(givenResult));
                System.out.println(Arrays.toString(solutionFile.execute(file, exo.entryData)));
                if (exo.checkResult(givenResult)) {
                    // case where utilisator's programm output matches the expected output
                    System.out.println("You win, congrats");
                } else {
                    // case where it doesnt
                    System.out.println("You loose, try again by modifing your source code");
                }

            }
            if (s.equals("2")) {
                System.out.println("Unfortunatly this mode isnt dev yet");
                
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
