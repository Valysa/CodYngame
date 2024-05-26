package M;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Exercise class represents an exercise with various attributes and functionalities.
 */

public class Exercise {

    public int Id;
    public int ExoType;
    public String ExoName;
    public String Instruction;
    public int NbTry;
    public int NbSucess;
    public int NbSessionSucess;
    public int NbFirstTry;
    public String[] outputData;
    public String[] inputData;
    public String GeneratorCode;
    public String SolutionCode;

    /**
     * Constructor for Exercise class.
     *
     * @param id The id of the exercise.
     * @param ExoType The type of the exercise.
     * @param ExoName The name of the exercise.
     * @param Instruction The instructions of the exercise.
     * @param SolutionLang The language of the solution.
     * @param SolutionCode The code of the solution.
     * @param GeneratorCode The code of the generator.
     * @param NbTry The number of tries.
     * @param NbSucess The number of successes.
     * @param NbSessionSucess The number of session successes.
     * @param NbFirstTry The number of first tries.
     */

    // Constructor of Exercise
    Exercise(int id, int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry) {
        this.Id = id;
        this.ExoType = ExoType;
        this.ExoName = ExoName;
        this.Instruction = Instruction;
        this.NbTry = NbTry;
        this.NbSucess = NbSucess;
        this.NbSessionSucess = NbSessionSucess;
        this.NbFirstTry = NbFirstTry;
        this.GeneratorCode = GeneratorCode; // Initialiser ce champ
        this.SolutionCode = SolutionCode; // Initialiser ce champ
        switch (SolutionLang) {
            case 0:
                this.write(SolutionCode, GeneratorCode, "c");
                break;
            case 1:
                this.write(SolutionCode, GeneratorCode, "py");
                break;
            case 2:
                this.write(SolutionCode, GeneratorCode, "java");
                break;
            case 3:
                this.write(SolutionCode, GeneratorCode, "php");
                break;
            case 4:
                this.write(SolutionCode, GeneratorCode, "js");
                break;
            case 5:
                this.write(SolutionCode, GeneratorCode, "mjs");
                break;
        }

    }

    /**
     * Returns a string representation of the Exercise object.
     *
     * @return A string containing the attributes of the Exercise object.
     */

    // Display all the atribute of Exercise
    @Override
    public String toString() {
        return "Exo{" +
                "\nId=" + this.Id +
                "\nExoType=" + this.ExoType +
                "\nExoName='" + this.ExoName + '\'' +
                "\nInstruction='" + this.Instruction + '\'' +
                "\nNbTry=" + this.NbTry +
                "\nNbSucess=" + this.NbSucess +
                "\nNbSessionSucess=" + this.NbSessionSucess +
                "\nNbFirstTry=" + this.NbFirstTry +
                "\n}";
    }

    /**
     * Retrieves all exercises from the database and stores them in an array.
     *
     * @return An array containing all exercises.
     */

    // Take all exercise in database and stock them in an array
    public static Exercise[] allExo() {

        int c = Bdd.count();
        Exercise[] exo = new Exercise[c];

        for (int i = 0; i < c; i++) {
            exo[i] = Bdd.take(i + 1);
        }

        return exo;
    }

    /**
     * Writes the solution code and the generator code to respective files.
     *
     * @param SolutionCode The solution code to be written.
     * @param GeneratorCode The generator code to be written.
     * @param SolutionLang The language of the solution and generator code.
     */

    // Create file who contain the solution code and the input generator
    public void write(String SolutionCode, String GeneratorCode, String SolutionLang) {

        String PATH = "src/main/resources/Exercise/Exo" + this.Id + "/";
        try {
            Files.createDirectories(Paths.get(PATH));
        } catch (IOException e) {
            System.out.println("Cannot access resources directory : " + e.getMessage());
        }
        String soluce = PATH + "soluceExo." + SolutionLang;
        String generator = PATH + "genExo." + SolutionLang;
        try {
            FileWriter soluceF = new FileWriter(soluce);
            soluceF.write(SolutionCode);
            soluceF.close();

            FileWriter soluceG = new FileWriter(generator);
            soluceG.write(GeneratorCode);
            soluceG.close();

        } catch (IOException e) {
            System.out.println("Cannot write the file soluce and generator : " + e.getMessage());
        }

    }
}
