package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The PythonLanguage class provides methods to execute Python programs.
 * It extends the abstract Language class.
 */
public class PythonLanguage extends Language {

    /**
     * Executes a Python file using Python3.
     *
     * @param executablePath the path to the Python file.
     * @return true if the execution was successful, false otherwise.
     */
    @Override
    public boolean execute(String executablePath) {
        try {
            Process process = Runtime.getRuntime().exec("python3 " + executablePath);
            return readStdout(process) != null;
        } catch (IOException e){
            System.out.println("Execution error");
            return false;
        }
    }

    /**
     * Executes a Python file using Python3 with input entries.
     *
     * @param executablePath the path to the Python file.
     * @param entries the input entries for the executable.
     * @return the output of the executable as an array of Strings.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */
    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("python3 " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

    /**
     * Checks if the given program code is written in Python language.
     *
     * @param program the program code to check.
     * @return true if the program is written in Python language, false otherwise.
     */
    @Override
    public boolean checkLanguage(StringBuilder program) {
        try {
            String PythonSyntax = "\\s*def\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*\\(.*\\)\\s*:";
            Pattern patternPython = Pattern.compile(PythonSyntax, Pattern.DOTALL);
            Matcher matcherPython = patternPython.matcher(program);
            boolean foundPythonCode = false;
            while (matcherPython.find()) {
                if (program.toString().contains("def")) {
                    foundPythonCode = true;
                    break;
                }
            }
            if (foundPythonCode) {
                System.out.println("Yes, you understand Python language !");
                return true;
            } else {
                System.out.println("No, you haven't programming in Python language");
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
