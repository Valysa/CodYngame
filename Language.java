import java.io.IOException;

public abstract class Language {
    public abstract void compile(String sourceFilePath) throws IOException, InterruptedException;

    public abstract void execute(String executablePath) throws IOException, InterruptedException;
}
