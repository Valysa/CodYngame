import { isPrime } from "./userExo.mjs";
import { isPrime1 } from "./soluceExo.mjs";

function run_test(number){
	let result1 = isPrime1(number);
	let result2 = isPrime(number);
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
		const minValue = 0;
		const maxValue = 1000;
		const number1 = Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;
		failed |= run_test(number1);
		const number2 = Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;
		failed |= run_test(number2);
		const number3 = 11;
		failed |= run_test(number3);
		const number4 = 0;
		failed |= run_test(number4);
		return failed;
	}

	main();