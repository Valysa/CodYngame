package C.Languages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import C.Exercise.ExerciseStdinStdout;

public class Language {

    public void execute(String executablePath) throws IOException, InterruptedException {
    }

    public void execute(String executablePath, ExerciseStdinStdout exo) throws IOException, InterruptedException {
    }

    public void readStdin(Process process, int[] entryData) throws IOException, InterruptedException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        // This part put the entry of an exercice into the process
        for (int i = 0; i < entryData.length; i++) {
            writer.write(Integer.toString(entryData[i]));
            writer.newLine();
        }
        writer.flush();
        writer.close();

    }

    public void readStdout(Process process) throws IOException, InterruptedException {
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
    }

    public Boolean readStdout(Process process, int[] outputData) throws IOException, InterruptedException {
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
        // If no expected result then it's ok
        // if expected result but not the same size as returned result then KO
        // else compare line by line
        Boolean isOk = true;
        if (outputData != null && outputData.length == outputResult.size()) {

            for (int i = 0; i < outputResult.size(); i++) {
                isOk = isOk && outputResult.get(i).equals(String.valueOf(outputData[i]));
            }
        }
        return isOk;
    }
}
