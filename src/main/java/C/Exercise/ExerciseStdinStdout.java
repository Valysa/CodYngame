package C.Exercise;

public class ExerciseStdinStdout extends Exercise{
    public String[] entryData; //temporaly an int but in the end (it doesnt even matter no im just kidding) it should be a String to be as global as possible
    public String[] outputData;

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