import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Java hello");

            //Call of the C part
            Language cLanguage = new CLanguage();
            cLanguage.execute("codes-test/helloworld.c");

            //Call of the python part
            Language pLanguage = new PythonLanguage();
            pLanguage.execute("codes-test/helloworld.py");

            //Call of the java part (its javaseption time)
            Language javaLanguage = new JavaLanguage();
            javaLanguage.execute("codes-test/helloworld.java");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
