package ExercisesInclude;
public class mainEx1 {
    public static void main(String[] args){
        ex1Soluce array1 = new ex1Soluce(new int[]{1, 2, 3, 4, 5});
        ex1Soluce array2 = new ex1Soluce(new int[]{10,20,30,40,50});
        System.out.println("Java : Exercise 1");
        System.out.println("Sum array1 : "  + array1.array_sum());
        System.out.println("Sum array2 : " + array2.array_sum());
    }
}
