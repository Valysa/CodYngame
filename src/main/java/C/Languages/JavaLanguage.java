package C.Languages;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class JavaLanguage extends Language {


    public void compile(String sourceFilePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("javac " + sourceFilePath);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation error");
        }
    }

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

    @Override
    public void execute(String sourceFilePath) throws IOException, InterruptedException {
        /*try {
            compile(sourceFilePath);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }*/
        Process process = Runtime.getRuntime().exec("java " + sourceFilePath);

        readStdout(process);
    }


    @Override
    public String[] execute(String executablePath, String[] entries) throws IOException, InterruptedException {
        /*try {
            compile(sourceFilePath);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }*/
        Process process = Runtime.getRuntime().exec("php " + executablePath);
        readStdin(process, entries);
        return readStdout(process);
    }

    public void execute(String[] sourceFilePath, String mainFileName) throws IOException, InterruptedException {
        try {
            this.compile(sourceFilePath);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        Process process = Runtime.getRuntime().exec("java -cp src/main/resources/Exercise " + mainFileName);

        readStdout(process);
    }

    @Override
    public boolean checkLanguage(StringBuilder program) {
        try {
            String JavaSyntax = "(import\\s.*|package\\s.*|class\\s.*|public\\s.*|private\\s.*|protected\\s.*|interface\\s.*|extends\\s.*|implements\\s.*|abstract\\s.*|final\\s.*|static\\s.*|void\\s.*|int\\s.*|long\\s.*|float\\s.*|double\\s.*|char\\s.*|boolean\\s.*|String\\s.*|if\\s.*|else\\s.*|for\\s.*|while\\s.*|do\\s.*|switch\\s.*|case\\s.*|break\\s.*|continue\\s.*|default\\s.*|return\\s.*|try\\s.*|catch\\s.*|finally\\s.*|throw\\s.*|throws\\s.*|assert\\s.*|new\\s.*|instanceof\\s.*|super\\s.*|this\\s.*|null\\s.*)";
            Pattern patternJava = Pattern.compile(JavaSyntax, Pattern.DOTALL);
            Matcher matcherJava = patternJava.matcher(program);
            boolean foundJavaCode = false;
            while (matcherJava.find()) {
                if (program.toString().contains("class")) {
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
