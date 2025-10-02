import java.util.Arrays;
import java.util.Collections;

public class Sorting {
	public static void main(String[] args) {

		Integer[] array = { 5, 3, 8, 1, 2, 7, 4, 6 };

		System.out.println("Original array: " + Arrays.toString(array));

		Arrays.sort(array);
		System.out.println("Sorted array (ascending): " + Arrays.toString(array));

		Arrays.sort(array, Collections.reverseOrder());
		System.out.println("Sorted array (descending): " + Arrays.toString(array));
	}
}
