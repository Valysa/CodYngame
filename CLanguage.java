import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CLanguage extends Language {

    @Override
    public void compile(String sourceFilePath) throws IOException, InterruptedException {
        System.out.println(sourceFilePath);
        Process process = Runtime.getRuntime().exec("gcc " + sourceFilePath + " -o execName");
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation Error !");
        }
    }

    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        try {
            this.compile(executablePath);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        };
        Process process = Runtime.getRuntime().exec("./execName");

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
