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

    public void compile(String[] sourceFilePaths) throws IOException, InterruptedException {
        StringBuilder command = new StringBuilder("javac");
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
}
