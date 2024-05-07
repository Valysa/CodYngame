import java.io.IOException;

public class CLanguage extends Language {

    public void compile(String sourceFilePath, String executableFile) throws IOException, InterruptedException {

        Process process = Runtime.getRuntime().exec("gcc " + sourceFilePath + " -o " + executableFile);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation Error !");
        }
    }

    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        String executableFile = "execName";
        try {        
            this.compile(executablePath, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        ;
        Process process = Runtime.getRuntime().exec("./" + executableFile);

        readStdout(process);
    }
}
