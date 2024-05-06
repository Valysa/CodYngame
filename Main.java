import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Java hello");
            Language cLanguage = new CLanguage();
            // cLanguage.compile("helloworld.c");
            cLanguage.execute("helloworld.c");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
