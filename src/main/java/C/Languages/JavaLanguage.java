package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The JavaLanguage class provides methods to compile and execute Java programs.
 * It extends the abstract Language class.
 */

public class JavaLanguage extends Language {

    /**
     * Compiles a single Java source file.
     *
     * @param sourceFilePath the path to the Java source file.
     * @throws IOException if an I/O error occurs during the compilation process.
     * @throws InterruptedException if the compilation process is interrupted.
     */

    public void compile(String sourceFilePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("javac " + sourceFilePath);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation error");
        }
    }

    /**
     * Compiles multiple Java source files.
     *
     * @param sourceFilePaths an array of paths to the Java source files.
     * @throws IOException if an I/O error occurs during the compilation process.
     * @throws InterruptedException if the compilation process is interrupted.
     */

    public void compile(String[] sourceFilePaths) throws IOException, InterruptedException {
        StringBuilder command = new StringBuilder("javac ");
        // Add all the file to compile
        for (String sourceFilePath : sourceFilePaths) {
            command.append(" ").append(sourceFilePath);
        }

        Process process = Runtime.getRuntime().exec(command.toString());
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation error");
        }
    }

    /**
     * Executes a compiled Java program.
     *
     * @param sourceFilePath the path to the compiled Java class file.
     * @return true if the execution was successful, false otherwise.
     */

    @Override
    public boolean execute(String sourceFilePath) {
        try {
            Process process = Runtime.getRuntime().exec("java " + sourceFilePath);
            return readStdout(process) != null;
        } catch (IOException e){
            System.out.println("Execution error");
            return false;
        }
    }

    /**
     * Executes a compiled Java program with a mode parameter.
     *
     * @param sourceFilePath the path to the compiled Java class file.
     * @param mode an integer mode parameter for the execution.
     * @return the output of the executable as an array of Strings.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */

    public String[] execute(String sourceFilePath, int mode) throws IOException, InterruptedException {
        //System.out.println(sourceFilePath);
        compile(sourceFilePath);
        Process process = Runtime.getRuntime().exec("java " + sourceFilePath + " " + mode);
        return readStdout(process);
    }

    /**
     * Executes a compiled Java program with input entries.
     *
     * @param sourceFilePath the path to the compiled Java class file.
     * @param entries the input entries for the executable.
     * @return the output of the executable as an array of Strings.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */

    @Override
    public String[] execute(String sourceFilePath, String[] entries) throws IOException, InterruptedException {
        /*try {
            compile(sourceFilePath);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }*/
        compile(sourceFilePath);
        Process process = Runtime.getRuntime().exec("java " + sourceFilePath);
        readStdin(process, entries);
        return readStdout(process);
    }

    /**
     * Compiles and executes multiple Java source files.
     *
     * @param sourceFilePath an array of paths to the Java source files.
     * @param mainFileName the name of the main Java class to execute.
     * @return true if the execution was successful, false otherwise.
     */

    public boolean execute(String[] sourceFilePath, String mainFileName){
        try {
            this.compile(sourceFilePath);
        } catch (Exception e) {
            System.out.println("Compilation error");
            return false;
        }
        try {
            Process process = Runtime.getRuntime().exec("java -cp src/main/resources/Exercise " + mainFileName);
            return readStdout(process) != null;
        } catch (IOException e){
            System.out.println("Execution error");
            return false;
        }
    }

    /**
     * Checks if the given program code is written in Java language.
     *
     * @param program the program code to check.
     * @return true if the program is written in Java language, false otherwise.
     */

    @Override
    public boolean checkLanguage(StringBuilder program) {
        try {
            String JavaSyntax = "public\\s+(static\\s+)?\\w+\\s+\\w+\\(.*?\\)\\s*\\{\\s*(.*?)\\s*\\}";
            Pattern patternJava = Pattern.compile(JavaSyntax, Pattern.DOTALL);
            Matcher matcherJava = patternJava.matcher(program);
            boolean foundJavaCode = false;
            while (matcherJava.find()) {
                if (program.toString().contains("public")) {
                    foundJavaCode = true;
                    break;
                }
            }
            if (foundJavaCode) {
                System.out.println("Yes, you understand Java language !");
                return true;
            } else {
                System.out.println("No, you haven't programming in Java language");
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
