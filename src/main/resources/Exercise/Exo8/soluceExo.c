void insertionSort1(int array[], int size){
	int i,key,j;
	for(int i = 1; i < size; i++){
		key = array[i];
		j = i - 1;
		while(j >= 0 && array[j] > key){
			array[j + 1] = array[j];
			j = j - 1; 
		}
		array[j + 1] = key;
	}
}