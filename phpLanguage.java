import java.io.IOException;

public class phpLanguage extends Language {
    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("php " + executablePath);

        readStdout(process);
    }
}
