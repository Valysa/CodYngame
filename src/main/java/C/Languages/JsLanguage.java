package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class JsLanguage extends Language {
    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("node " + executablePath);

        readStdout(process);
    }

    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("node " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

    public void checkLanguage(StringBuilder program) {
        try {
            String JsSyntax = "(?:function\\s+\\w+\\s*\\(.*?\\)\\s*\\{)|(?:var\\s+\\w+\\s*=)|(?:let\\s+\\w+\\s*=)|(?:const\\s+\\w+\\s*=)|(?:\\<script\\>)";
            Pattern patternJs = Pattern.compile(JsSyntax, Pattern.DOTALL);
            Matcher matcherJs = patternJs.matcher(program);
            boolean foundJsCode = false;
            while (matcherJs.find()) {
                if (program.toString().contains("function") && !program.toString().contains("<?php") && !program.toString().contains("?>")) {
                    foundJsCode = true;
                    break;
                }
            }
            if (foundJsCode) {
                System.out.println("Yes, you understand JS language !");
            } else {
                System.out.println("No, you haven't programming in JS language");
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("There was in the regular expression pattern: " + pse.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
