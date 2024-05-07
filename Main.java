import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Java hello");

            //Call of the C part
            String cFile = "codes-test/helloworld.c";
            Language cLanguage = new CLanguage();
            cLanguage.execute(cFile);

            //Call of the python part
            String pyFile = "codes-test/helloworld.py";
            Language pLanguage = new PythonLanguage();
            pLanguage.execute(pyFile);

            //Call of the java part (its javasception time)
            String javaFile = "codes-test/helloworld.java";
            Language javaLanguage = new JavaLanguage();
            javaLanguage.execute(javaFile);

            //Call of the php part
            String phpFile = "codes-test/helloworld.php";
            Language phpLanguage = new phpLanguage();
            phpLanguage.execute(phpFile);

            //Call of the js part
            String jsFile = "codes-test/helloworld.js";
            Language jsLanguage = new phpLanguage();
            jsLanguage.execute(jsFile);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
