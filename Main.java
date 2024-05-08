import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import C.Exercise.ExerciseStdinStdout;
import C.Languages.CLanguage;
import C.Languages.JavaLanguage;
import C.Languages.JsLanguage;
import C.Languages.Language;
import C.Languages.PythonLanguage;
import C.Languages.phpLanguage;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Java hello");

            // Call of the C part
            String cFile = "C/Test/codes-test/helloworld.c";
            Language cLanguage = new CLanguage();
            cLanguage.execute(cFile);

            /*
             * // Call of the python part
             * String pyFile = "C/Test/codes-test/helloworld.py";
             * Language pLanguage = new PythonLanguage();
             * pLanguage.execute(pyFile);
             * 
             * // Call of the php part
             * String phpFile = "C/Test/codes-test/helloworld.php";
             * Language phpLanguage = new phpLanguage();
             * phpLanguage.execute(phpFile);
             * 
             * // Call of the js part
             * String jsFile = "C/Test/codes-test/helloworld.js";
             * Language jsLanguage = new JsLanguage();
             * jsLanguage.execute(jsFile);
             * 
             * //Call of the java part (its javasception time)
             * String javaFile = "C/Test/codes-test/helloworld.java";
             * Language javaLanguage = new JavaLanguage();
             * javaLanguage.execute(javaFile);
             */

            // Sort of menu displays
            System.out.println("------------------------------");
            System.out.println("             Menu             ");
            System.out.println("1-Sin/Sout exo | 2-Include exo");
            System.out.println("------------------------------");

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            if (s.equals("1")) {
                // ExerciseStdinStdout part
                ExerciseStdinStdout exo1 = new ExerciseStdinStdout();
                // initialing the exercise with name description entry and expected exit
                exo1.name = "exo1";
                exo1.description = "You will be given in entry a list of 10 integer and we ask you to give back their values multiplied by two";
                exo1.entryData = new String[10];
                exo1.outputData = new String[10];
                for (int i = 0; i < 10; i++) {
                    exo1.entryData[i] = String.valueOf(i);
                    exo1.outputData[i] = String.valueOf(i * 2);
                }
                // ask for the number of exercise
                // this part is unused yet cuz there is only one exercise
                System.out.println(exo1.name);
                String selectedExercise = bufferRead.readLine();

                // ask the file for the first exercise
                System.out.println(exo1.description);
                System.out.print("enter the file name\n");
                String filePath = bufferRead.readLine();

                // initialisating exercise file
                String file = filePath;
                Language cSolutionFile = new CLanguage();
                // executing file, with this specific exercise (entry and expected output)
                String[] givenResult;
                givenResult = cSolutionFile.execute(file, exo1.entryData);
                System.out.println(Arrays.toString(givenResult));
                System.out.println(Arrays.toString(cSolutionFile.execute(file, exo1.entryData)));
                if (exo1.checkResult(givenResult)) {
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
