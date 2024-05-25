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
    public boolean execute(String sourceFilePath) {
        /*try {
            compile(sourceFilePath);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }*/
        try {
            Process process = Runtime.getRuntime().exec("java " + sourceFilePath);
            return readStdout(process) != null;
        } catch (IOException e){
            System.out.println("Execution error");
            return false;
        }
    }

    public String[] execute(String sourceFilePath, int mode) throws IOException, InterruptedException {
        //System.out.println(sourceFilePath);
        compile(sourceFilePath);
        Process process = Runtime.getRuntime().exec("java " + sourceFilePath + " " + mode);
        return readStdout(process);
    }


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
