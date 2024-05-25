package M;

import C.Languages.Language;
import C.Languages.LanguageFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ExerciseStdinStdout extends Exercise {

    public ExerciseStdinStdout(int id, int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry) {
        super(id, ExoType, ExoName, Instruction, SolutionLang, SolutionCode, GeneratorCode, NbTry, NbSucess, NbSessionSucess, NbFirstTry);
    }

    public boolean checkResult(String[] entries) {
        // if expected result but not the same size as returned result then KO
        // else compare line by line
        Boolean isOk = false;
        if (outputData != null && outputData.length == entries.length) {
            isOk = true;
            for (int i = 0; i < outputData.length; i++) {
                System.out.println("Expected : " + outputData[i]  + "|||||||"    + "Given :  " + entries[i]);
                isOk = isOk && entries[i].equals(outputData[i]);
            }
        }
        return isOk;
    }

    public String[] generateInputs(int mode) {
        try {
            String genExoFile = "src/main/resources/Exercise/Exo" + "1"+  "/genExo.java";
            Language cLanguage = LanguageFactory.assignLanguage(genExoFile);
            System.out.println("----- Here are the generated inputs --------");
            return (cLanguage.execute(genExoFile, mode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] generateOutputs(String [] entries) {
        try {
            String soluceExoFile = "src/main/resources/Exercise/Exo" + "1"+  "/soluceExo.java";
            Language cLanguage = LanguageFactory.assignLanguage(soluceExoFile);
            System.out.println("----- Here are the generated outputs  --------");
            return (cLanguage.execute(soluceExoFile, entries));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
