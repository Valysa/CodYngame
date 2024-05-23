package launcher;

import java.util.Random;

public class MainEx {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static String generateRandomString(){
        Random random = new Random();
        int minLength = 1;
        int maxLength = 100;
        int length = random.nextInt((maxLength - minLength) + 1) + minLength;
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static int run_test(TestEx string1, User string2, String str){
        boolean result1 = string1.isPalindrome(str);
        boolean result2 = string2.isPalindrome(str);
        if(result1 == result2){
            System.out.println("Test passed");
            return 0;
        }
        else{
            System.out.println("Test failed");
            return 1;
        }
    }

    public static void main(String[] args){
        int failed  = 0;
        String str1 = generateRandomString();
        TestEx stringS1 = new TestEx();
        User string1 = new User();
        failed |= run_test(stringS1, string1, str1);
        String str2 = generateRandomString();
        TestEx stringS2 = new TestEx();
        User string2 = new User();
        failed |= run_test(stringS2, string2, str2);
        String str3 = "radar";
        TestEx stringS3 = new TestEx();
        User string3 = new User();
        failed |= run_test(stringS3, string3, str3);
        String str4 = "";
        TestEx stringS4 = new TestEx();
        User string4 = new User();
        failed |= run_test(stringS4, string4, str4);
        System.exit(failed);
    }
}
