import java.util.Arrays;
import java.util.HashMap;

public class FrequencySort {
	public static void main(String[] args) {
		int[] array = { 1, 4, 4, 4, 4, 2, 2, 3, 3, 3 };
		System.out.println("initial: " + Arrays.toString(array));
		FrequencySort.frequencySort(array);
		System.out.println("sorted: " + Arrays.toString(array));
	}

	private static void frequencySort(int[] array) {
		var frequencyMap = new HashMap<Integer, Integer>();
		for (var value : array) {
			var occurenceCount = frequencyMap.getOrDefault(value, 0);
			frequencyMap.put(value, occurenceCount + 1);
		}

		int n = array.length;
		boolean swapped;

		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - 1 - i; j++) {
				int frequencyA = frequencyMap.get(array[j]);
				int frequencyB = frequencyMap.get(array[j + 1]);
				if (frequencyA < frequencyB) {
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
	}
}
