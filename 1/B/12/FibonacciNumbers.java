public class FibonacciNumbers {
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 13, 21};
		for (var i : array) {
			System.out.println("is " + i + " fibonacci: " + isNumberAFibonacciNumber(i));
		}
	}

	private static boolean isNumberAFibonacciNumber(int value) {
		if (value < 1)
			return false;

		int one = 1;
		int two = 1;
		while (one < value){
			int temp = one;
			one = one + two;
			two = temp;
		}

		return value == one;
	}
}
