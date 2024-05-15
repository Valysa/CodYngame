package C.Languages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import C.Exercise.ExerciseStdinStdout;

public class Language {

    public void execute(String executablePath) throws IOException, InterruptedException {
    }

    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        return entries;
    }

    public void readStdin(Process process, String[] entryData) throws IOException, InterruptedException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        // This part put the entry of an exercice into the process
        for (int i = 0; i < entryData.length; i++) {
            writer.write(entryData[i]);
            writer.newLine();
        }
        writer.flush();
        writer.close();

    }

    //this function return the standard output of a running programm (in any language)
    public String[] readStdout(Process process) throws IOException, InterruptedException {
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> outputResult = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            outputResult.add(line);
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Run error");
        }
        return outputResult.toArray(new String[0]); // Convert list in String[]
    }

    public void checkLanguage(StringBuilder program){
    }
}
