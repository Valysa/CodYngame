import { array_moy } from "./userExo.mjs";
import { array_moy1 } from "./soluceExo.mjs";

function generateRandomArray(minSize, maxSize, minValue, maxValue){
	const size = Math.floor(Math.random() * (maxSize - minSize + 1)) + minSize;
	const array = [];
	for(let i = 0; i < size; i++){
		const value = Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;
		array.push(value);
	}
	return array;
}

function run_test(array, size){
	let result1 = array_moy1(array, size);
	let result2 = array_moy(array, size);
	if(result1 === result2){
		console.log("Test passed");
		return 0;
	}
	else{
		console.log("Test failed");
		return 1;
	}
}

function main(){
	let failed = 0;
	const minSize = 5;
	const maxSize = 100;
	const minValue = -1000;
	const maxValue = 1000;
	const array1 = generateRandomArray(minSize,maxSize,minValue,maxValue);
	failed |= run_test(array1,array1.length);
	const array2 = generateRandomArray(minSize,maxSize,minValue,maxValue);
	failed |= run_test(array2,array2.length);
	const array3 = generateRandomArray(minSize,maxSize,0,0);
	failed |= run_test(array3,array3.length);
	const array4 = [];
	failed |= run_test(array4,array4.length);
	return failed;
}

process.exit(main());