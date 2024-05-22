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
            String java2File = "src/main/java/C/ExercisesInclude/MainEx1.java";
            String java3File = "src/main/java/C/ExercisesInclude/Ex1Soluce.java";
            String javaMainFile = "C.ExercisesInclude.MainEx1";
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
            /*if (s.equals("1")) {
                // ---------------------------------------------
                // ExerciseStdinStdout part
                // ---------------------------------------------
                // ask for the number of exercise
                System.out.println("Select the exercise you want to do");
                String selectedExercise = bufferRead.readLine();

                // if exo1 selected
                if (selectedExercise.equals("1")) {
                    // initialing the exercise with name description entry and expected exit
                    System.out.println(exo[1].ExoName);
                    System.out.println(exo[1].ExoType);
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

            }*/
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
                System.out.println(exo[2].ExoName);
                System.out.println(exo[2].Instruction);
                String cgenExoFile = "src/main/resources/Exercise/Exo3/genExo.c";
                ExerciseInclude example = (ExerciseInclude) exo[2];
                String functionC = example.readLineFromFile(cgenExoFile);
                //Display the type, name and various parameters of the function requested for this exercise
                if (functionC != null) {
                    System.out.println(functionC);
                } else {
                    System.out.println("No lines with '{' or ':' were found");
                }
                String csoluceExoFile = "src/main/resources/Exercise/Exo3/soluceExo.c";
                Language ExerciseTest = LanguageFactory.assignLanguage(csoluceExoFile);
                BufferedReader readerFile = new BufferedReader(new InputStreamReader(System.in));
                boolean isProgramCorrect = false;

                //Repeat this loop until the code entered by the user is correct
                while(!isProgramCorrect) {
                    System.out.println("Enter your program to resolve this exercise (Write exit at the end of your program to send him)");
                    try {
                        StringBuilder program = new StringBuilder();
                        String line;
                        //Repeat this loop until the user has entered "exit"
                        while (!(line = readerFile.readLine()).equalsIgnoreCase("exit")) {
                            program.append(line).append("\n");
                        }
                        //Display the code entered by the user
                        System.out.println("You have coded this program :");
                        System.out.println(program.toString());
                        //Check if the code entered by the user is in the good language
                        if (ExerciseTest.checkLanguage(program)) {
                            //Save the code in a file named userExo.c here
                            example.saveToFile(program, "c");
                            String c2File = "src/main/resources/Exercise/Exo3/mainExo.c";
                            Language c2Language = LanguageFactory.assignLanguage(c2File);
                            c2Language.execute(c2File);
                            //We need to change the management of exception cases in the various functions like compile and execute
                            isProgramCorrect = true;
                        }
                    } catch (IOException es) {
                        es.printStackTrace();
                    }
                }

                System.out.println("Well done ! You have successfully completed this exercise in C language");

                System.out.println("Now, you are the same exercise in python");
                //Display the type, name and various parameters of the function requested for this exercise
                System.out.println(exo[3].ExoName);
                System.out.println(exo[3].Instruction);
                String pygenExoFile = "src/main/resources/Exercise/Exo4/genExo.py";
                ExerciseInclude pyExample = (ExerciseInclude) exo[3];
                String functionPy = pyExample.readLineFromFile(pygenExoFile);
                if (functionPy != null) {
                    System.out.println(functionPy);
                } else {
                    System.out.println("No lines with '{' or ':' were found");
                }
                String pySoluceExoFile = "src/main/resources/Exercise/Exo4/soluceExo.py";
                Language ExercisePyTest = LanguageFactory.assignLanguage(pySoluceExoFile);
                BufferedReader readerFilePy = new BufferedReader(new InputStreamReader(System.in));
                boolean isPyProgramCorrect = false;
                //Repeat this loop until the code entered by the user is correct
                while(!isPyProgramCorrect) {
                    System.out.println("Enter your program to resolve this exercise (Write exit at the end of your program to send him)");
                    try {
                        StringBuilder programPy = new StringBuilder();
                        String line;
                        //Repeat this loop until the user has entered "exit"
                        while (!(line = readerFilePy.readLine()).equalsIgnoreCase("exit")) {
                            programPy.append(line).append("\n");
                        }
                        //Display the code entered by the user
                        System.out.println("You have coded this program :");
                        System.out.println(programPy.toString());
                        //Check if the code entered by the user is in the good language
                        if (ExercisePyTest.checkLanguage(programPy)) {
                            //Save the code in a file named userExo.py here
                            pyExample.saveToFile(programPy, "py");
                            String py2File = "src/main/resources/Exercise/Exo4/mainExo.py";
                            Language py2Language = LanguageFactory.assignLanguage(py2File);
                            py2Language.execute(py2File);
                            //We need to change the management of exception cases in the various functions like compile and execute
                            isPyProgramCorrect = true;
                        }
                    } catch (IOException es) {
                        es.printStackTrace();
                    }
                }

                System.out.println("Well done ! You have successfully completed this exercise in Python language");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}