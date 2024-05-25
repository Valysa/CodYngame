#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "soluceExo.c"
#include "userExo.c"

int run_test(int array[], int size){
	int result1 = array_sum1(array, size);
	int result2 = array_sum(array, size);
	puts("");
	if(result1 == result2){
		printf("Test passed");
		return 0;
	}
	else{
		printf("Test failed");
		return 1;
	}
}

int* generateRandomArray(int size, int minValue, int maxValue){
	int* array = malloc(size*sizeof(int));
	if(array == NULL){
		exit(2);
	}
	for(int i = 0; i < size; i++){
		array[i] = rand()%(maxValue - minValue + 1) + minValue;
	}
	return array; 
}

int main(){
	srand(time(NULL));
	int minSize = 5;
	int maxSize = 95;
	int minValue = -1000;
	int maxValue = 2001;
	int failed = 0;
	int size1 = rand()%(maxSize - minSize + 1) + minSize;
	int* array1 = generateRandomArray(size1, minValue, maxValue);
	failed |= run_test(array1,size1);
	int size2 = rand()%(maxSize - minSize + 1) + minSize;
	int* array2 = generateRandomArray(size2, minValue, maxValue);
	failed |= run_test(array2,size2);
	int size3 = rand()%(maxSize - minSize + 1) + minSize;
	int* array3 = generateRandomArray(size3, 0, 0);
	failed |= run_test(array3,size3);
	int size4 = 0;
	int* array4 = generateRandomArray(size4, minValue, maxValue);
	failed |= run_test(array4,size4);
	return failed;
}