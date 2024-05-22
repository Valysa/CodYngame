<?php
	include 'soluceExo.php';
	include 'userExo.php';

	function generateRandomArray($minSize, $maxSize, $minValue, $maxValue){
		$size = rand($minSize, $maxSize);
		for($i = 0; $i < $size; $i++){
			$array[$i] = rand($minValue, $maxValue);
		}
		return $array;
	}

	function run_test($array, $size){
		$result1 = arraySum1($array, $size);
		$result2 = arraySum($array, $size);
		if($result1 == $result2){
			echo "Test passed";
			return 0;
		}
		else{
			echo "Test failed";
			return 1;
		}
	}

	function main(){
		$failed = 0;
		$minSize = 5;
		$maxSize = 100;
		$minValue = -1000;
		$maxValue = 1000;
		$array1 = generateRandomArray($minSize, $maxSize, $minValue, $maxValue);
		$failed |= run_test($array1,count($array1));
		$array2 = generateRandomArray($minSize, $maxSize, $minValue, $maxValue);
		$failed |= run_test($array2,count($array2));
		$array3 = generateRandomArray($minSize, $maxSize, 0, 0);
		$failed |= run_test($array3,count($array3));
		$array4 = [];
		$failed |= run_test($array4,count($array4));
		return $failed;
	}

	main();
?>