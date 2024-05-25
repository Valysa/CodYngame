import random
import string
import soluceExo
import userExo

def run_test(string):
	result1 = soluceExo.isPalindrome1(string)
	result2 = userExo.isPalindrome(string)
	if(result1 == result2):
		print("Test passed")
		return 0
	else:
		print("Test failed")
		return 1

def generateRandomString(length):
	characters = string.ascii_letters + string.digits
	return ''.join(random.choice(characters) for _ in range(length))

def main():
	failed = 0
	minSize = 1
	maxSize = 100
	stringLength1 = random.randint(minSize, maxSize)
	string1 = generateRandomString(stringLength1)
	failed |= run_test(string1)
	stringLength2 = random.randint(minSize, maxSize)
	string2 = generateRandomString(stringLength2)
	failed |= run_test(string2)
	string3 = "radar"
	failed |= run_test(string3)
	string4 = ""
	failed |= run_test(string4)
	return failed

main()