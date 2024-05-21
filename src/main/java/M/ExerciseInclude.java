package M;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExerciseInclude extends Exercise {

    public String SolutionLang;
    ExerciseInclude(int id, int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, String MainCode, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry){
        super(id, ExoType, ExoName, Instruction, SolutionLang, SolutionCode, GeneratorCode, NbTry, NbSucess, NbSessionSucess, NbFirstTry);

        switch (SolutionLang) {
            case 0:
                this.SolutionLang = "c";
                this.writeInclude(MainCode,"c");
                break;
            case 1:
                this.SolutionLang = "py";
                this.writeInclude(MainCode,"py");
                break;
            case 2:
                this.SolutionLang = "java";
                this.writeInclude(MainCode,"java");
                break;
            case 3:
                this.SolutionLang = "php";
                this.write(SolutionCode,GeneratorCode,"php");
                break;
            case 4:
                this.SolutionLang = "js";
                this.write(SolutionCode,GeneratorCode,"js");
                break;
        }

    }

    public String readLineFromFile(String filePath) throws IOException {
        //Creates a BufferedReader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        //Reads each line until a line with '{' or ':' is found
        while ((line = reader.readLine()) != null) {
            //Searches for the index of the first occurrence of '{' and ':'
            int indexOfBrace = line.indexOf('{');
            int indexOfTwoPoints = line.indexOf(':');
            //Checks if '{' or ':' is found in the line
            if (indexOfBrace != -1 || indexOfTwoPoints != -1) {
                //Determines the end index of the substring by taking the minimum of the index of '{' and ':'
                int endIndex = Math.min(indexOfBrace != -1 ? indexOfBrace : Integer.MAX_VALUE,
                                        indexOfTwoPoints != -1 ? indexOfTwoPoints : Integer.MAX_VALUE);
                return line.substring(0, endIndex);
                //Returns the substring until the index just before '{' or ':'
            }
        }
        reader.close();
        return null; //returns null if no line with '{' or ':' is found in the file
    }
    public void writeInclude(String MainCode, String SolutionLang) {

        String PATH = "src/main/resources/Exercise/Exo"+this.Id+"/";
        try {
            Files.createDirectories(Paths.get(PATH));
        }catch (IOException e){
            System.out.println("Cannot access resources directory : " + e.getMessage());
        }
        String main = PATH + "mainExo." + SolutionLang;
        try {


            FileWriter mainF = new FileWriter(main);
            mainF.write(MainCode);
            mainF.close();

        } catch (IOException e){
            System.out.println("Cannot write the file main : " + e.getMessage());
        }
    }

    //This function saves the code entered by the user in a file named userExo.extension ie c,py,js,php,java
    public void saveToFile(StringBuilder program, String SolutionLang) {
        String PATH = "src/main/resources/Exercise/Exo"+this.Id+"/";
        try {
            Files.createDirectories(Paths.get(PATH));
        }catch (IOException e){
            System.out.println("Cannot access resources directory : " + e.getMessage());
        }
        String filePath = PATH + "userExo." + SolutionLang;
        File file = new File(filePath);
        try {
            FileWriter mainF = new FileWriter(filePath);
            mainF.write(String.valueOf(program));
            mainF.close();
        } catch (IOException e){
            System.out.println("Cannot write the file main : " + e.getMessage());
        }
    }

}
