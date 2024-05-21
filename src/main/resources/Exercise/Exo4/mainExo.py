import random
import soluceExo
import userExo

def run_test(array, size):
	result1 = soluceExo.array_sum1(array, size)
	result2 = userExo.array_sum(array, size)
	if(result1 == result2):
		print("Test passed")
		return 0
	else:
		print("Test failed")
		return 1

def main():
	failed = 0
	minSize = 5
	maxSize = 100
	minValue = -1000
	maxValue = 1000
	arraySize1 = random.randint(minSize, maxSize)
	array1 = [random.randint(minValue, maxValue) for _ in range(arraySize1)]
	failed |= run_test(array1,arraySize1)
	arraySize2 = random.randint(minSize, maxSize)
	array2 = [random.randint(minValue, maxValue) for _ in range(arraySize2)]
	failed |= run_test(array2,arraySize2)
	arraySize3 = random.randint(minSize, maxSize)
	array3 = [0 for _ in range(arraySize3)]
	failed |= run_test(array3,arraySize3)
	array4 = []
	failed |= run_test(array4,0)
	return failed

main()