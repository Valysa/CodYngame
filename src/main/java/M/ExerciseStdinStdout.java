package M;

import C.Languages.JavaLanguage;
import C.Languages.Language;
import C.Languages.LanguageFactory;
import javafx.scene.control.TextArea;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ExerciseStdinStdout extends Exercise {

    public ExerciseStdinStdout(int id, int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry) {
        super(id, ExoType, ExoName, Instruction, SolutionLang, SolutionCode, GeneratorCode, NbTry, NbSucess, NbSessionSucess, NbFirstTry);
    }

    public boolean checkResult(String[] entries) {
        System.out.println("We are testing your returns");
        Boolean isOk = false;
        if (outputData != null && outputData.length == entries.length) {
            isOk = true;
            for (int i = 0; i < outputData.length; i++) {
                System.out.println("Expected : " + outputData[i]  + " | "    + "Given :  " + entries[i]);
                isOk = isOk && entries[i].equals(outputData[i]);
            }
        }
        return isOk;
    }

    public boolean checkResult(String[] entries, TextArea terminalTextArea) {
        String print = new String();
        Boolean isOk = false;
        if (outputData != null && outputData.length == entries.length) {
            isOk = true;
            for (int i = 0; i < outputData.length; i++) {
                print = print + ("Expected : " + outputData[i]  + " | "    + "Given :  " + entries[i] + "\n");
                isOk = isOk && entries[i].equals(outputData[i]);
            }
        }
        terminalTextArea.setText("We are testing you results\n" + print);
        return isOk;
    }

    public String[] generateInputs(int mode) {
        try {
            String genExoFile = "src/main/resources/Exercise/Exo" + this.Id +  "/genExo.java";
            Language cLanguage = LanguageFactory.assignLanguage(genExoFile);
            System.out.println("----- Here are the generated inputs --------");
            return (cLanguage.execute(genExoFile, mode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] generateOutputs(String[] entries) {
        try {
            String soluceExoFile = "src/main/resources/Exercise/Exo" + this.Id +  "/soluceExo.java";
            Language cLanguage = LanguageFactory.assignLanguage(soluceExoFile);
            System.out.println("----- Here are the generated outputs  --------");
            return (cLanguage.execute(soluceExoFile, entries));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveToFile(StringBuilder program, String language) {
        String PATH = "src/main/resources/Exercise/Exo" + this.Id + "/";
        try {
            Files.createDirectories(Paths.get(PATH));
        } catch (IOException e) {
            System.out.println("Cannot access resources directory: " + e.getMessage());
        }
        String filePath = PATH + "userExo." + language;
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(program.toString());
        } catch (IOException e) {
            System.out.println("Cannot write the file: " + e.getMessage());
        }
    }

    //We read the minimal file and display it
    public static String readMinimalFIle(String lang) throws IOException {
        String genExoFile = "src/main/resources/Minimal Code Stdin Stdout/minimal." + lang;
        // Creates a StringBuilder to accumulate the content of the file
        StringBuilder content = new StringBuilder();
        // Creates a BufferedReader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(genExoFile));
        String line;
        // Reads each line from the file
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n"); // Adds each line to the StringBuilder
        }
        reader.close();
        return content.toString(); // Returns the accumulated content as a string
    }

    public void deleteUserFile(String language) {
        Path path = Paths.get("src/main/resources/Exercise/Exo" + this.Id + "/userExo." + language);
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.println("Failed to delete the file: " + e.getMessage());
        }
        if (Objects.equals(language, "java")) {
            Path Solucepath = Paths.get("src/main/resources/Exercise/Exo" + this.Id + "/soluceExo.class");
            Path Userpath = Paths.get("src/main/resources/Exercise/Exo" + this.Id + "/userExo.class");
            Path Mainpath = Paths.get("src/main/resources/Exercise/Exo" + this.Id + "/mainExo.class");
            try {
                Files.delete(Solucepath);
            } catch (IOException e) {
                System.err.println("Failed to delete the file: " + e.getMessage());
            }
            try {
                Files.delete(Userpath);
            } catch (IOException e) {
                System.err.println("Failed to delete the file: " + e.getMessage());
            }
            try {
                Files.delete(Mainpath);
            } catch (IOException e) {
                System.err.println("Failed to delete the file: " + e.getMessage());
            }
        }
    }

    public void ExerciseResolution() {
        System.out.println(this.ExoName);
        System.out.println(this.Instruction);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isProgramCorrect = false;

        System.out.println("Enter the language you want to use (e.g., java, python, php, c): ");
        String language = "";
        try {
            language = reader.readLine().trim().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!isProgramCorrect) {
            System.out.println("Enter your program to resolve this exercise (Write exit at the end of your program to send it)");

            try {
                this.NbTry++;
                StringBuilder program = new StringBuilder();
                String line;

                while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
                    program.append(line).append("\n");
                }

                System.out.println("You have coded this program:");
                System.out.println(program.toString());

                saveToFile(program, language);

                String userExoFile = "src/main/resources/Exercise/Exo" + this.Id + "/userExo." + language;
                Language langExecutor = LanguageFactory.assignLanguage(userExoFile);

                if (langExecutor != null) {
                    int succes = 0;
                    for (int i = 1; i < 4; i++) {
                        this.inputData = this.generateInputs(i);
                        this.outputData = this.generateOutputs(this.inputData);

                        String[] givenResult;
                        givenResult = langExecutor.execute(userExoFile, this.inputData);

                        if (this.checkResult(givenResult)) {
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
                    }
                    if (succes == 3) {
                        isProgramCorrect = true;
                        this.NbSucess++;
                    }
                } else {
                    System.out.println("Language executor not found for: " + language);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        deleteUserFile(language);
    }

}
