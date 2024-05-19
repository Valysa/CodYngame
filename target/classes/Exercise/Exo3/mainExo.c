#include <stdio.h>
#include "soluceExo.c"

int main(){
	int array1[] = {1,2,3,4,5};
	int array2[] = {10,20,30,40,50};
	//Test games
	printf("C : Exercise 1\n");
	printf("Sum array1 : %d\n", array_sum(array1, 5));
	printf("Sum array2 : %d\n", array_sum(array2, 5));
	return 0;
}