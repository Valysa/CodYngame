int array_sum1(int array[], int size){
	int sum = 0;
	for(int i = 0; i < size; i++){
		sum += array[i];
	}
	return sum;
}