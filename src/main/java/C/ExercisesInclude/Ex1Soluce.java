package C.ExercisesInclude;

public class Ex1Soluce {
    private final int[] array;

    public Ex1Soluce(int[] Array) {
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
