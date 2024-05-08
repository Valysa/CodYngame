package C.Languages;
import java.io.IOException;

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
}
