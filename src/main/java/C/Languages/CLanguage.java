package C.Languages;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The CLanguage class provides methods to compile and execute C language programs.
 * It extends the abstract Language class.
 */

public class CLanguage extends Language {

    /**
     * Compiles a single C source file into an executable.
     *
     * @param sourceFilePath the path to the C source file.
     * @param executableFile the name of the output executable file.
     * @throws IOException if an I/O error occurs during the compilation process.
     * @throws InterruptedException if the compilation process is interrupted.
     */

    public void compile(String sourceFilePath, String executableFile) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("gcc " + sourceFilePath + " -o " + executableFile);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation Error !");
        }
    }

    /**
     * Compiles multiple C source files into a single executable.
     *
     * @param sourceFilePaths an array of paths to the C source files.
     * @param executableFile the name of the output executable file.
     * @throws IOException if an I/O error occurs during the compilation process.
     * @throws InterruptedException if the compilation process is interrupted.
     */

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

    /**
     * Compiles and executes a single C source file.
     *
     * @param executablePath the path to the C source file.
     * @return true if the execution was successful, false otherwise.
     */
    @Override
    public boolean execute(String executablePath){
        String executableFile = "execName";
        try {
            this.compile(executablePath, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            return false;
        }
        try {
            Process process = Runtime.getRuntime().exec("./" + executableFile);
            return readStdout(process) != null;
        } catch (IOException e){
            System.out.println("Execution error");
            return false;
        }
    }

    /**
     * Compiles and executes a single C source file with input entries.
     *
     * @param executablePath the path to the C source file.
     * @param entries the input entries for the executable.
     * @return the output of the executable as an array of Strings.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */
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

    /**
     * Compiles and executes multiple C source files.
     *
     * @param executablePaths an array of paths to the C source files.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */

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

    /**
     * Checks if the given program code is written in C language.
     *
     * @param program the program code to check.
     * @return true if the program is written in C language, false otherwise.
     */
    @Override
    public boolean checkLanguage(StringBuilder program) {
        try {
            String CSyntax = "\\b(?:int|char|float|double|void)\\s+[a-zA-Z_]\\w*\\s*(?:\\[\\d*\\])?\\s*(?:=[^;]+)?\\s*;|\\bif\\s*\\(|\\bwhile\\s*\\(|\\bdo\\s*\\{|\\bswitch\\s*\\(|\\bfor\\s*\\(";
            Pattern patternC = Pattern.compile(CSyntax, Pattern.DOTALL);
            Matcher matcherC = patternC.matcher(program);
            boolean foundCCode = false;
            while (matcherC.find()) {
                if (!program.toString().contains("def") && !program.toString().contains("function") && !program.toString().contains("public")){
                    foundCCode = true;
                    break;
                }
            }
            if (foundCCode) {
                System.out.println("Yes, you understand C language !");
                return true;
            } else {
                System.out.println("No, you haven't programming in C language");
                return false;
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("There was in the regular expression pattern: " + pse.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        return false;
    }
}
