import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import C.Languages.CLanguage;
import C.Languages.JavaLanguage;
import C.Languages.Language;
import C.Languages.PythonLanguage;
import C.Languages.phpLanguage;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Java hello");

            //Call of the C part
            String cFile = "C/Test/codes-test/helloworld.c";
            Language cLanguage = new CLanguage();
            cLanguage.execute(cFile);

            //Call of the python part
            String pyFile = "C/Test/codes-test/helloworld.py";
            Language pLanguage = new PythonLanguage();
            pLanguage.execute(pyFile);

            //Call of the php part
            String phpFile = "C/Test/codes-test/helloworld.php";
            Language phpLanguage = new phpLanguage();
            phpLanguage.execute(phpFile);

            //Call of the js part
            String jsFile = "C/Test/codes-test/helloworld.js";
            Language jsLanguage = new phpLanguage();
            jsLanguage.execute(jsFile);

            /*//Call of the java part (its javasception time)
            String javaFile = "C/Test/codes-test/helloworld.java";
            Language javaLanguage = new JavaLanguage();
            javaLanguage.execute(javaFile);*/

            //Sort of menu displays
            System.out.println("------------------------------");
            System.out.println("             Menu             "); 
            System.out.println("1-Sin/Sout exo | 2-Include exo");
            System.out.println("------------------------------");

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            if(s.equals("1")){
                System.out.println("This part neither");
            }
            if(s.equals("2")){
                System.out.println("Unfortunatly this mode isnt dev yet");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
