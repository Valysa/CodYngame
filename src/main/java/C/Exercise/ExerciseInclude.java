package C.Exercise;

import C.Languages.CLanguage;
import C.Languages.Language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseInclude extends Exercise {
    public String[] entryData;

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

}
