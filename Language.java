import java.io.IOException;

public abstract class Language {

    public abstract void execute(String executablePath) throws IOException, InterruptedException;
}
