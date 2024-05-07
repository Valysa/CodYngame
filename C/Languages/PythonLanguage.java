package C.Languages;
import java.io.IOException;

public class PythonLanguage extends Language {

    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("python3 " + executablePath);

        readStdout(process);
    }
}
