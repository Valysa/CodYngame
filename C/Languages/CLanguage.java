package C.Languages;
import java.io.IOException;

import C.Exercise.ExerciseStdinStdout;

public class CLanguage extends Language {

    public void compile(String sourceFilePath, String executableFile) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("gcc " + sourceFilePath + " -o " + executableFile);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation Error !");
        }
    }

    @Override
    public void execute(String executablePath) throws IOException, InterruptedException {
        String executableFile = "execName";
        try {        
            this.compile(executablePath, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        ;
        Process process = Runtime.getRuntime().exec("./" + executableFile);
        readStdout(process, null);
    }

    @Override
    //without exo, just run the file, with exo with entry input the entry in the file
    //with outpout, try if expected matches with gived output
    //We maybe want to only put entries (See ligne 45)
    public void execute(String executablePath, ExerciseStdinStdout exo) throws IOException, InterruptedException {
        String executableFile = "execName";
        try {        
            this.compile(executablePath, executableFile);
        } catch (Exception e) {
            System.out.println("Compilation error");
            throw new InterruptedException("Error");
        }
        ;
        Process process = Runtime.getRuntime().exec("./" + executableFile);
        readStdin(process, exo.entryData);
        //This part may be put in the main
        Boolean success = readStdout(process, exo.outputData);
        if(success){
            System.out.println("you win");
        }
        else{
            System.out.println("you loose");
        }
    }
}
