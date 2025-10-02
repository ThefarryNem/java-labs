import java.util.function.Function;
import java.util.Arrays;

public class LabA16 {
	public static void main(String[] args) {
		// Исходные данные
		double[] original = { 5.2, 3.1, 4.4, 1.9, 2.8, 0.5, 6.3 };

		System.out.println("Исходный массив:");
		printArray(original);

		// Пузырьковая сортировка
		Massiv bubble = new Massiv(original);
		bubble.bubbleSort();
		System.out.println("\nПузырьковая сортировка:");
		bubble.print();

		// Шейкер-сортировка
		Massiv shaker = new Massiv(original);
		shaker.shakerSort();
		System.out.println("\nШейкер-сортировка:");
		shaker.print();

		// Сортировка выбором
		Massiv selection = new Massiv(original);
		selection.selectionSort();
		System.out.println("\nСортировка выбором:");
		selection.print();

		// Сортировка вставками
		Massiv insertion = new Massiv(original);
		insertion.insertionSort();
		System.out.println("\nСортировка вставками:");
		insertion.print();

		// Сортировка Шелла
		Massiv shell = new Massiv(original);
		shell.shellSort();
		System.out.println("\nСортировка Шелла:");
		shell.print();

		// Сортировка слиянием
		Massiv merge = new Massiv(original);
		merge.mergeSort();
		System.out.println("\nСортировка слиянием:");
		merge.print();

		// Пример аналитической функции: f(x) = sin(x)
		Function<Double, Double> f = Math::sin;
		Massiv analytic = new Massiv(10, 0, Math.PI, f);
		System.out.println("\nМассив, построенный по f(x) = sin(x):");
		analytic.print();
		analytic.shellSort();
		System.out.println("Отсортированный массив sin(x):");
		analytic.print();
	}

	private static void printArray(double[] arr) {
		for (double d : arr) {
			System.out.printf("%.2f ", d);
		}
		System.out.println();
	}
}

class Massiv {
	private double[] data;

	// Конструктор по размеру
	public Massiv(int size) {
		data = new double[size];
	}

	// Конструктор по массиву
	public Massiv(double[] input) {
		data = input.clone();
	}

	// Конструктор по аналитической функции f(x)
	public Massiv(int size, double a, double b, Function<Double, Double> f) {
		data = new double[size];
		double step = (b - a) / (size - 1);
		for (int i = 0; i < size; i++) {
			double x = a + i * step;
			data[i] = f.apply(x); // подынтегральное значение
		}
	}

	public double[] getData() {
		return data;
	}

	public void print() {
		for (double d : data) {
			System.out.printf("%.4f ", d);
		}
		System.out.println();
	}

	private void swap(int i, int j) {
		double temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	public void bubbleSort() {
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = 0; j < data.length - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					swap(j, j + 1);
				}
			}
		}
	}

	public void shakerSort() {
		int left = 0, right = data.length - 1;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = left; i < right; i++) {
				if (data[i] > data[i + 1]) {
					swap(i, i + 1);
					swapped = true;
				}
			}
			right--;
			for (int i = right; i > left; i--) {
				if (data[i] < data[i - 1]) {
					swap(i, i - 1);
					swapped = true;
				}
			}
			left++;
		}
	}

	public void selectionSort() {
		for (int i = 0; i < data.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[minIndex]) {
					minIndex = j;
				}
			}
			swap(i, minIndex);
		}
	}

	public void insertionSort() {
		for (int i = 1; i < data.length; i++) {
			double key = data[i];
			int j = i - 1;
			while (j >= 0 && data[j] > key) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = key;
		}
	}

	public void shellSort() {
		int gap = data.length / 2;
		while (gap > 0) {
			for (int i = gap; i < data.length; i++) {
				double temp = data[i];
				int j = i;
				while (j >= gap && data[j - gap] > temp) {
					data[j] = data[j - gap];
					j -= gap;
				}
				data[j] = temp;
			}
			gap /= 2;
		}
	}

	public void mergeSort() {
		data = mergeSortRecursive(data);
	}

	private double[] mergeSortRecursive(double[] arr) {
		if (arr.length <= 1)
			return arr;
		int mid = arr.length / 2;
		double[] left = mergeSortRecursive(Arrays.copyOfRange(arr, 0, mid));
		double[] right = mergeSortRecursive(Arrays.copyOfRange(arr, mid, arr.length));
		return merge(left, right);
	}

	private double[] merge(double[] left, double[] right) {
		double[] result = new double[left.length + right.length];
		int i = 0, j = 0, k = 0;
		while (i < left.length && j < right.length) {
			result[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
		}
		while (i < left.length)
			result[k++] = left[i++];
		while (j < right.length)
			result[k++] = right[j++];
		return result;
	}
}
