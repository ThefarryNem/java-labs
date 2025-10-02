public class Palindromes {
	public static void main(String[] args) {
		int[] array = { 111, 123, 121, 133, 1551, 12321, 100001, 10001 };
		for (var i : array) {
			System.out.println("is " + i + " a palindrome:" + isPalindrome(i));
		}
	}

	private static boolean isPalindrome(int value) {
		var str = Integer.toString(value);

		int length = str.length();
		int halfway = length / 2;

		for (int i = 0; i <= halfway; i++) {
			if (str.charAt(i) != str.charAt(length - 1 - i))
				return false;
		}

		return true;
	}
}
