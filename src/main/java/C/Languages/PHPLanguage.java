package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PHPLanguage extends Language {
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

    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("php " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

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
