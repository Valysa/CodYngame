package C.Languages;
import java.io.IOException;

public class JavaLanguage extends Language {


    public void compile(String sourceFilePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("javac " + sourceFilePath);
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
}
