import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class MatrixSort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();

		// Ввод размерности матрицы
		System.out.print("Введите размерность матрицы n: ");
		int n = scanner.nextInt();

		// Ввод номера строки или столбца, по которому будет сортировка
		System.out.print("Введите индекс k (от 0 до " + (n - 1) + "): ");
		int k = scanner.nextInt();

		// Ввод варианта сортировки: строка или столбец
		System.out.print("Выберите тип сортировки (1 — по строкам, 2 — по столбцам): ");
		int choice = scanner.nextInt();

		// Создаем матрицу
		int[][] matrix = new int[n][n];

		// Заполняем матрицу случайными числами от -n до n
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = rand.nextInt(2 * n + 1) - n; // диапазон [-n, n]
			}
		}

		System.out.println("Исходная матрица:");
		printMatrix(matrix);

		// Проверка корректности входных данных
		if (k < 0 || k >= n) {
			System.out.println("Некорректный индекс k. Завершение программы.");
			scanner.close();
			return;
		}
		if (choice != 1 && choice != 2) {
			System.out.println("Некорректный выбор типа сортировки. Завершение программы.");
			scanner.close();
			return;
		}

		// Выполняем сортировку
		if (choice == 1) {
			final int fixedK = k;
			Integer[] rowIndices = new Integer[n];
			for (int i = 0; i < n; i++) {
				rowIndices[i] = i;
			}
			// Сортируем индексы по значению элементов k-й строки
			final int[][] currentMatrix = matrix;
			Arrays.sort(rowIndices, (i1, i2) -> Integer.compare(currentMatrix[i1][fixedK], currentMatrix[i2][fixedK]));
			// Формируем новую матрицу с отсортированными строками
			int[][] sortedMatrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				sortedMatrix[i] = matrix[rowIndices[i]];
			}
			matrix = sortedMatrix;
			System.out.println("Матрица после сортировки строк по " + k + "-й строке:");
		} else {
			// Сортировка столбцов по значениям k-го столбца
			// Создаем массив индексов столбцов
			final int fixedK = k;
			Integer[] colIndices = new Integer[n];
			for (int j = 0; j < n; j++) {
				colIndices[j] = j;
			}
			final int[][] currentMatrix = matrix;
			Arrays.sort(colIndices, (j1, j2) -> Integer.compare(currentMatrix[fixedK][j1], currentMatrix[fixedK][j2]));
			// Перестраиваем матрицу, чтобы столбцы шли в отсортированном порядке
			int[][] sortedMatrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sortedMatrix[i][j] = matrix[i][colIndices[j]];
				}
			}
			matrix = sortedMatrix;
			System.out.println("Матрица после сортировки столбцов по " + k + "-му столбцу:");
		}

		// Вывод итоговой матрицы
		printMatrix(matrix);
		scanner.close();
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			for (int elem : row) {
				System.out.printf("%4d", elem);
			}
			System.out.println();
		}
	}
}
