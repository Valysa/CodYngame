package Exo7;

import java.util.Random;

public class mainExo {

	public static void main(String[] args){
		int failed  = 0;
		int minSize = 5;
		int maxSize = 100;
		int minValue = -1000;
		int maxValue = 1000;
		int[] randomArray1 = generateRandomArray(minSize, maxSize, minValue, maxValue);
		soluceExo arrays1 = new soluceExo(randomArray1);
		userExo array1 = new userExo(randomArray1);
		failed |= run_test(arrays1, array1);
		int[] randomArray2 = generateRandomArray(minSize, maxSize, minValue, maxValue);
		soluceExo arrays2 = new soluceExo(randomArray2);
		userExo array2 = new userExo(randomArray2);
		failed |= run_test(arrays2, array2);
		int[] randomArray3 = generateRandomArray(minSize, maxSize, 0, 0);
		soluceExo arrays3 = new soluceExo(randomArray3);
		userExo array3 = new userExo(randomArray3);
		failed |= run_test(arrays3, array3);
		int[] randomArray4 = new int[0];
		soluceExo arrays4 = new soluceExo(randomArray4);
		userExo array4 = new userExo(randomArray4);
		failed |= run_test(arrays4, array4);
		System.exit(failed);
	}

	public static int[] generateRandomArray(int minSize, int maxSize, int minValue, int maxValue){
		Random random = new Random();
		int size = random.nextInt((maxSize - minSize) + 1) + minSize;
		int[] array = new int[size];
		for(int i = 0; i < size; i++){
			array[i] = random.nextInt((maxValue - minValue) + 1) + minValue;
		}
		return array;
	}

	public static int run_test(soluceExo array1, userExo array2){
		float result1 = array1.array_moy();
		float result2 = array2.array_moy();
		if(result1 == result2){
			System.out.println("Test passed");
			return 0;
		}
		else{
			System.out.println("Test failed");
			return 1;
		}
	}
}