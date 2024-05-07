package C.Languages;
import java.io.IOException;

public class JsLanguage extends Language {
    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("node " + executablePath);

        readStdout(process);
    }
}
