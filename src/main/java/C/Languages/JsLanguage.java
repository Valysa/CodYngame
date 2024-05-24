package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class JsLanguage extends Language {
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

    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("node " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

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
