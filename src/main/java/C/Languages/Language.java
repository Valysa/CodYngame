package C.Languages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Language {

    public boolean execute(String executablePath) {
        return false;
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
    public String[] readStdout(Process process) {
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            ArrayList<String> outputResult = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                outputResult.add(line);
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if(exitCode == 1){
                System.err.println("Your program hasn't passed all tests. Please modify");
                return null;
            } else if (exitCode != 0) {
                System.err.println("Run error" + exitCode);
                return null;
            }
            return outputResult.toArray(new String[0]); // Convert list in String[]
        } catch (IOException | InterruptedException e){
            System.err.println("Error reading stdout" + e.getMessage());
            return null;
        } finally {
            try {
                reader.close();
                inputStream.close();
            } catch (IOException e){
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public boolean checkLanguage(StringBuilder program){
        return false;
    }
}
