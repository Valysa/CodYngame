package Exo7;

import java.util.Random;

public class mainExo {

	public static int run_test(soluceExo array1, userExo array2, int[] array, int size){
		float result1 = array1.array_moy(array, size);
		float result2 = array2.array_moy(array, size);
		if(result1 == result2){
			System.out.println("Test passed");
			return 0;
		}
		else{
			System.out.println("Test failed");
			return 1;
		}
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
	public static void main(String[] args){
		int failed  = 0;
		int minSize = 5;
		int maxSize = 100;
		int minValue = -1000;
		int maxValue = 1000;
		int[] randomArray1 = generateRandomArray(minSize, maxSize, minValue, maxValue);
		soluceExo arrays1 = new soluceExo();
		userExo array1 = new userExo();
		failed |= run_test(arrays1, array1, randomArray1, randomArray1.length);
		int[] randomArray2 = generateRandomArray(minSize, maxSize, minValue, maxValue);
		soluceExo arrays2 = new soluceExo();
		userExo array2 = new userExo();
		failed |= run_test(arrays2, array2, randomArray2, randomArray2.length);
		int[] randomArray3 = generateRandomArray(minSize, maxSize, 0, 0);
		soluceExo arrays3 = new soluceExo();
		userExo array3 = new userExo();
		failed |= run_test(arrays3, array3, randomArray3, randomArray3.length);
		int[] randomArray4 = new int[0];
		soluceExo arrays4 = new soluceExo();
		userExo array4 = new userExo();
		failed |= run_test(arrays4, array4, randomArray4, randomArray4.length);
		System.exit(failed);
	}
}