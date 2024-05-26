package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The JsLanguage class provides methods to execute JavaScript programs.
 * It extends the abstract Language class.
 */

public class JsLanguage extends Language {

    /**
     * Executes a JavaScript file using Node.js.
     *
     * @param executablePath the path to the JavaScript file.
     * @return true if the execution was successful, false otherwise.
     */
    @Override
    public boolean execute(String executablePath) {
        try {
            Process process = Runtime.getRuntime().exec("node " + executablePath);
            return readStdout(process) != null;
        } catch (IOException e){
            System.out.println("Execution error");
            return false;
        }
    }

    /**
     * Executes a JavaScript file using Node.js with input entries.
     *
     * @param executablePath the path to the JavaScript file.
     * @param entries the input entries for the executable.
     * @return the output of the executable as an array of Strings.
     * @throws IOException if an I/O error occurs during the execution process.
     * @throws InterruptedException if the execution process is interrupted.
     */

    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("node " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

    /**
     * Checks if the given program code is written in JavaScript language.
     *
     * @param program the program code to check.
     * @return true if the program is written in JavaScript language, false otherwise.
     */

    @Override
    public boolean checkLanguage(StringBuilder program) {
        try {
            String JsSyntax = "(?:function\\s+\\w+\\s*\\(.*?\\)\\s*\\{)|(?:var\\s+\\w+\\s*=)|(?:let\\s+\\w+\\s*=)|(?:const\\s+\\w+\\s*=)|(?:\\<script\\>)";
            Pattern patternJs = Pattern.compile(JsSyntax, Pattern.DOTALL);
            Matcher matcherJs = patternJs.matcher(program);
            boolean foundJsCode = false;
            while (matcherJs.find()) {
                if (program.toString().contains("function") && !program.toString().contains("$")) {
                    foundJsCode = true;
                    break;
                }
            }
            if (foundJsCode) {
                System.out.println("Yes, you understand JS language !");
                return true;
            } else {
                System.out.println("No, you haven't programming in JS language");
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
