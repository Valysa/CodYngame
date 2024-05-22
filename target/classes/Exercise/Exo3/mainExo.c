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

int main(){
	int failed = 0;
	int array1[] = {1,2,3,4,5};
	failed |= run_test(array1,5);
	int array2[] = {-1,-2,-3,-4,-5,-6};
	failed |= run_test(array2,5);
	int array3[] = {0,0,0};
	failed |= run_test(array3,3);
	return failed;
}