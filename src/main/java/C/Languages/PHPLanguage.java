package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The PHPLanguage class provides methods to execute PHP programs.
 * It extends the abstract Language class.
 */

public class PHPLanguage extends Language {

    /**
     * Executes a PHP file.
     *
     * @param executablePath the path to the PHP file.
     * @return true if the execution was successful, false otherwise.
     */
    @Override
    public boolean execute(String executablePath) {
        try {
            Process process = Runtime.getRuntime().exec("php " + executablePath);
            return readStdout(process) != null;
        } catch (IOException e){
            System.out.println("Execution error");
            return false;
        }
    }

    /**
     * Executes a PHP file with input entries.
     *
     * @param executablePath the path to the PHP file.
     * @param entries the input entries for the executable.
     * @return the output of the executable as an array of Strings.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */
    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("php " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

    /**
     * Checks if the given program code is written in PHP language.
     *
     * @param program the program code to check.
     * @return true if the program is written in PHP language, false otherwise.
     */
    @Override
    public boolean checkLanguage(StringBuilder program) {
        try {
            String PHPSyntax = "function\\s+\\w+\\s*\\(.*\\)\\s*\\{\\s*(.*?)\\s*\\}";
            Pattern patternPHP = Pattern.compile(PHPSyntax, Pattern.DOTALL);
            Matcher matcherPHP = patternPHP.matcher(program);
            boolean foundPHPCode = false;
            while (matcherPHP.find()) {
                if (program.toString().contains("function") && program.toString().contains("$")) {
                    foundPHPCode = true;
                    break;
                }
            }
            if (foundPHPCode) {
                System.out.println("Yes, you understand PHP language !");
                return true;
            } else {
                System.out.println("No, you haven't programming in PHP language");
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
