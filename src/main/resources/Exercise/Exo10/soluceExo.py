def isPalindrome1(string):
	string = string.lower()
	string = ''.join(c for c in string if c.isalnum())
	return string == string[::-1]