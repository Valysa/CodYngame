import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class phpLanguage extends Language{
    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("php " + executablePath);

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Run error");
        }
    }
}
