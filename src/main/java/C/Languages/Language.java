package C.Languages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * The Language class provides methods to execute programs and handle input/output streams.
 * This class is intended to be extended by specific language implementations.
 */

public class Language {

    /**
     * Executes a program.
     *
     * @param executablePath the path to the executable file.
     * @return false by default, should be overridden by subclasses.
     */

    public boolean execute(String executablePath) {
        return false;
    }

    /**
     * Executes a program with input entries.
     *
     * @param executablePath the path to the executable file.
     * @param entries the input entries for the executable.
     * @return the input entries by default, should be overridden by subclasses.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */

    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        return entries;
    }

    /**
     * Writes input data to the standard input of a process.
     *
     * @param process the process to which input data is written.
     * @param entryData the input data to write to the process.
     * @throws IOException if an I/O error occurs during the writing process.
     * @throws InterruptedException if the writing process is interrupted.
     */

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

    /**
     * Reads the standard output of a process.
     *
     * @param process the process from which to read the output.
     * @return the output of the process as an array of Strings, or null if an error occurs.
     */

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

    /**
     * Checks if the given program code is written in the specific language.
     *
     * @param program the program code to check.
     * @return false by default, should be overridden by subclasses.
     */
    public boolean checkLanguage(StringBuilder program){
        return false;
    }

    /**
     * Executes a program with a given parameter.
     * @param genExoFile the path to the executable file.
     * @param i the parameter to pass to the executable.
     * @return an array containing a single "0" by default, should be overridden by subclasses.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */

    public String[] execute(String genExoFile, int i) throws IOException, InterruptedException {
        return new String[]{"0"};
    }
}
