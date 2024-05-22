export function array_moy1(array, size){
	if(size === 0){
		return null;
	}
	let sum = 0;
	for(let i = 0; i < size; i++){
		sum = sum + array[i];
	}
	return sum/size;
}