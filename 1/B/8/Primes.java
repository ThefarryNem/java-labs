public class Primes {
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		for (int i = 0; i < array.length; i++) {
			System.out.println("is " + array[i] + " prime: " + isPrime(array[i]));
		}
	}

	private static boolean isPrime(int value) {
		if (value <= 1) {
			return false;
		}

		int sqrtFloored = (int) Math.sqrt(value);
		for (int i = 2; i <= sqrtFloored; i++) {
			if (value % i == 0) {
				return false;
			}
		}
		return true;
	}
}
