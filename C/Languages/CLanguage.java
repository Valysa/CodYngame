package C.Languages;

import java.io.IOException;
import java.util.Arrays;

import C.Exercise.ExerciseStdinStdout;

public class CLanguage extends Language {

    /*public void compileTwoFiles(String source1FilePath, String source2FilePath, String executableFile) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("gcc " + source1FilePath + " " + source2FilePath + " -o " + executableFile);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation Error !");
        }
    }*/

    //Maybe this function will be in class Language, I will see it later
    /*public void executeTwoFiles(String executable1Path, String executable2Path) throws IOException, InterruptedException {
        String executableFile = "ex1";
        try {
            this.compileTwoFiles(executable1Path, executable2Path, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        ;
        Process process = Runtime.getRuntime().exec("./" + executableFile);
        readStdout(process);
    }*/

    public void compile(String sourceFilePath, String executableFile) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("gcc " + sourceFilePath + " -o " + executableFile);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation Error !");
        }
    }

    public void compile(String[] sourceFilePaths, String executableFile) throws IOException, InterruptedException {
        StringBuilder command = new StringBuilder("gcc");
        // Add all the file to compile
        for (String sourceFilePath : sourceFilePaths) {
            command.append(" ").append(sourceFilePath);
        }
        // Add the output name file
        command.append(" -o ").append(executableFile);
        
        // Execute the command
        Process process = Runtime.getRuntime().exec(command.toString());
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation Error !");
        }
    }

    @Override
    // Here its a void but I maybe want to take the output ? (thinking of returning
    // String[])
    public void execute(String executablePath) throws IOException, InterruptedException {
        String executableFile = "execName";
        try {
            this.compile(executablePath, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        ;
        Process process = Runtime.getRuntime().exec("./" + executableFile);
        readStdout(process);
    }

    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        String executableFile = "execName";
        try {
            this.compile(executablePath, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        ;
        Process process = Runtime.getRuntime().exec("./" + executableFile);
        readStdin(process, entries);
        return readStdout(process);
    }

    public void execute(String[] executablePaths) throws IOException, InterruptedException {
        String executableFile = "execName";
        try {
            this.compile(executablePaths, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        ;
        Process process = Runtime.getRuntime().exec("./" + executableFile);
        readStdout(process);
    }
}
