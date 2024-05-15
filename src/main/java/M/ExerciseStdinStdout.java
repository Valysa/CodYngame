package M;


public class ExerciseStdinStdout extends Exercise {


    ExerciseStdinStdout(int id, int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry){
        super(id, ExoType, ExoName, Instruction, SolutionLang, SolutionCode, GeneratorCode, NbTry, NbSucess, NbSessionSucess, NbFirstTry);

    }
    public boolean checkResult(String[] entries){
        // if expected result but not the same size as returned result then KO
        // else compare line by line
        Boolean isOk = true;
        if (outputData != null && outputData.length == entries.length) {

            for (int i = 0; i < outputData.length; i++) {
                isOk = isOk && entries[i].equals(outputData[i]);
            }
        }
        return isOk;
    }
}