<?php
	include 'soluceExo.php';
	include 'userExo.php';

	function run_test($number){
		$result1 = isPrime1($number);
		$result2 = isPrime($number);
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
		$minValue = 0;
		$maxValue = 1000;
		$number1 = rand($minValue, $maxValue);
		$failed |= run_test($number1);
		$number2 = rand($minValue, $maxValue);
		$failed |= run_test($number2);
		$number3 = 11;
		$failed |= run_test($number3);
		$number4 = 0;
		$failed |= run_test($number4);
		return $failed;
	}

	main();
?>