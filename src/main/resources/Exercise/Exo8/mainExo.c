#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "soluceExo.c"
#include "userExo.c"

int run_test(int array1[], int array2[], int size){
	insertionSort1(array1, size);
	insertionSort(array2, size);
	puts("");
	for(int i = 0; i < size; i++){
		if(array1[i] != array2[i]){
			printf("Test failed");
			return 1;
		}
	}
	printf("Test passed");
	return 0;
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

void arrayCopy(int array[], int copy[], int size){
	for(int i = 0; i < size; i++){
		copy[i] = array[i];
	}
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
	int* arrayCopy1 = malloc(size1*sizeof(int));
	arrayCopy(array1,arrayCopy1,size1);
	failed |= run_test(array1,arrayCopy1,size1);
	free(array1);
	free(arrayCopy1);
	int size2 = rand()%(maxSize - minSize + 1) + minSize;
	int* array2 = generateRandomArray(size2, minValue, maxValue);
	int* arrayCopy2 = malloc(size2*sizeof(int));
	arrayCopy(array2,arrayCopy2,size2);
	failed |= run_test(array2,arrayCopy2,size2);
	free(array2);
	free(arrayCopy2);
	int size3 = rand()%(maxSize - minSize + 1) + minSize;
	int* array3 = generateRandomArray(size3, 0, 0);
	int* arrayCopy3 = malloc(size3*sizeof(int));
	arrayCopy(array3,arrayCopy3,size3);
	failed |= run_test(array3,arrayCopy3,size3);
	free(array3);
	free(arrayCopy3);
	int size4 = 0;
	int* array4 = generateRandomArray(size4, minValue, maxValue);
	int* arrayCopy4 = malloc(size4*sizeof(int));
	arrayCopy(array4,arrayCopy4,size4);
	failed |= run_test(array4,arrayCopy4,size4);
	free(array4);
	free(arrayCopy4);
	return failed;
}