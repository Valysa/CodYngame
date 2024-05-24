package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PythonLanguage extends Language {

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

    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("python3 " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

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
