public class Program1to6 {
	public static void main(String[] args) {
		int[] array = { 30, 20, 195, 123 };

		printOddEven(array);

		printLargestSmallest(array);

		printDivisibleBy(array, 3);
		printDivisibleBy(array, 5 * 7);

		printReverseBubbleSort(array);

		printThreeDigitValues(array);

	}

	static void printOddEven(int[] array) {
		int[] odds = new int[array.length];
		int oddCount = 0;

		System.out.print("even: ");
		for (int i : array) {
			if (i % 2 == 1) {
				odds[oddCount] = i;
				oddCount++;
			} else {
				System.out.print(i + " ");
			}
		}

		System.out.println();
		System.out.print("odd: ");

		for (int i = 0; i < oddCount; i++) {
			System.out.print(odds[i] + " ");
		}
		System.out.println('\n');
	}

	static void printLargestSmallest(int[] array) {
		int min = array[0];
		int max = array[0];

		for (int i = 1; i < array.length; i++) {
			max = Math.max(array[i], max);
			min = Math.min(array[i], min);
		}

		System.out.println("largest: " + max);
		System.out.println("smallest: " + min);
		System.out.println();
	}

	static void printDivisibleBy(int[] array, int divisor) {
		System.out.print("divisible by " + divisor + ": ");
		for (var i : array) {
			if (i % divisor == 0) {
				System.out.print(i + " ");
			}
		}
		System.out.println('\n');
	}

	static void printReverseBubbleSort(int[] array) {
		int n = array.length;
		boolean swapped;

		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - 1 - i; j++) {
				if (array[j] < array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}

		System.out.print("reverse bubble sort: ");
		for (int i = 0; i < n; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println('\n');
	}

	static void printThreeDigitValues(int[] array) {
		int[] validValues = new int[array.length];
		int validValueCount = 0;

		for (int i = 0; i < array.length; i++) {
			int value = array[i];

			if (value < 99 || value > 1000) {
				continue;
			}
			int one = value / 100;
			int two = value % 100 / 10;
			int three = value % 10;

			if (one == two || one == three || two == three) {
				continue;
			}

			validValues[validValueCount] = array[i];
			validValueCount++;
		}

		System.out.print("different digits: ");
		for (int i = 0; i < validValueCount; i++) {
			System.out.print(validValues[i] + " ");
		}
		System.out.println('\n');
	}
}
