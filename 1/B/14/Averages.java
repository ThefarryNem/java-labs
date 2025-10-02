public class Averages {
	public static void main(String[] args) {
		int[] array = { 1, 2, 5, 8, 10, 11, 12, 6 };
		for (int i = 0; i < array.length; i++) {
			int value = array[i];
			int next = 0, previous = 0;
			if (i == 0) {
				previous = 0;
				next = array[1];
			} else if (i == array.length - 1) {
				previous = array[array.length - 2];
				next = 0;
			} else {
				previous = array[i - 1];
				next = array[i + 1];
			}
			System.out.println("is " + value + " equal to average of " + previous + " and " + next + ": "
					+ isEqualToAverage(array, i));
		}
	}

	public static boolean isEqualToAverage(int[] array, int i) {
		if (i == 0) {
			return array[0] == array[1] / 2;
		}

		if (i == array.length - 1) {
			int last = array[array.length - 1];
			int secondToLast = array[array.length - 2];
			return last == secondToLast / 2;
		}

		return array[i] == (array[i - 1] + array[i + 1]) / 2;
	}
}
