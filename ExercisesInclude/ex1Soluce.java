package ExercisesInclude;

import java.util.ArrayList;

public class ex1Soluce {
    private final int[] array;

    public ex1Soluce(int[] Array) {
        this.array = Array ;
    }

    public int array_sum(){
        int sum = 0;
        for (int i : this.array) {
            sum += i;
        }
        return sum;
    }
}
