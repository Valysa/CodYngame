package C.Languages;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class CLanguage extends Language {

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

    public void checkLanguage(StringBuilder program) {
        try {
            String CSyntax = "\\b(?:int|char|float|double|void)\\s+[a-zA-Z_]\\w*\\s*(?:\\[\\d*\\])?\\s*(?:=[^;]+)?\\s*;|\\bif\\s*\\(|\\bwhile\\s*\\(|\\bdo\\s*\\{|\\bswitch\\s*\\(|\\bfor\\s*\\(";
            Pattern patternC = Pattern.compile(CSyntax, Pattern.DOTALL);
            Matcher matcherC = patternC.matcher(program);
            boolean foundCCode = false;
            while (matcherC.find()) {
                if (!program.toString().contains("def") && !program.toString().contains("function") && !program.toString().contains("class")){
                    foundCCode = true;
                    break;
                }
            }
            if (foundCCode) {
                System.out.println("Yes, you understand C language !");
            } else {
                System.out.println("No, you haven't programming in C language");
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("There was in the regular expression pattern: " + pse.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
