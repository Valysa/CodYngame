import java.util.Scanner;

public class userExo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input from stdin
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            // Print the input to stdout
            System.out.println(input);
        }

        scanner.close();
    }
}