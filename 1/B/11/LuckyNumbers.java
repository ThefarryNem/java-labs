public class LuckyNumbers {
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		int luckyNumber = getRandomNumber();
		for (var i : array) {
			if (i == luckyNumber) {
				System.out.println("Счастливое число: " + i);
			}
		}
	}

	private static int getRandomNumber() {
		// Случайное число, выпранное
		// броском игрального кубика;
		// гарантированно быть случайным.
		return 5;
	}
}
