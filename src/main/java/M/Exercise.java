package M;

public class Exercise {

    int Id;
    int ExoType;
    String ExoName;
    String Instruction;
    int SolutionLang;
    String SolutionCode;
    String InputData;
    String OutputData;
    int NbTry;
    int NbSucess;
    int NbSessionSucess;
    int NbFirstTry;


    // Constructor of Exercise

    Exercise(int id, int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String InputData, String OutputData, int NbTry, int NbSucess, int NbSessionSucess, int NbFirstTry) {
        this.Id = id;
        this.ExoType = ExoType;
        this.ExoName = ExoName;
        this.Instruction = Instruction;
        this.SolutionLang = SolutionLang;
        this.SolutionCode = SolutionCode;
        this.InputData = InputData;
        this.OutputData = OutputData;
        this.NbTry = NbTry;
        this.NbSucess = NbSucess;
        this.NbSessionSucess = NbSessionSucess;
        this.NbFirstTry = NbFirstTry;
    }



    // Display all the atribute of Exercise

    @Override
    public String toString() {
        return "Exo{" +
                "\nId=" + this.Id +
                "\nExoType=" + this.ExoType +
                "\nExoName='" + this.ExoName + '\'' +
                "\nInstruction='" + this.Instruction + '\'' +
                "\nSolutionLang=" + this.SolutionLang +
                "\nSolutionCode='" + this.SolutionCode + '\'' +
                "\nInputData='" + this.InputData + '\'' +
                "\nOutputData='" + this.OutputData + '\'' +
                "\nNbTry=" + this.NbTry +
                "\nNbSucess=" + this.NbSucess +
                "\nNbSessionSucess=" + this.NbSessionSucess +
                "\nNbFirstTry=" + this.NbFirstTry +
                "\n}";
    }

}
