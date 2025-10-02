import java.util.Random;
import java.util.Scanner;

public class MatrixSequenceAnalyzer {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.print("Введите количество строк матрицы: ");
		int rows = scanner.nextInt();
		System.out.print("Введите количество столбцов матрицы: ");
		int cols = scanner.nextInt();

		// Создание и заполнение матрицы
		int[][] matrix = new int[rows][cols];

		System.out.print("Хотите ввести матрицу вручную? (y/n): ");
		scanner.nextLine();
		String choice = scanner.nextLine();

		if (choice.equalsIgnoreCase("y")) {
			// Ручной ввод
			System.out.println("Введите элементы матрицы:");
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					System.out.printf("matrix[%d][%d] = ", i, j);
					matrix[i][j] = scanner.nextInt();
				}
			}
		} else {
			// Автоматическое заполнение
			System.out.print("Введите максимальное значение для случайных чисел: ");
			int maxValue = scanner.nextInt();
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					matrix[i][j] = random.nextInt(maxValue) + 1;
				}
			}
		}

		// Вывод матрицы
		System.out.println("\nМатрица:");
		printMatrix(matrix);

		// Поиск максимальных последовательностей
		int maxIncreasing = findMaxIncreasingSequence(matrix);
		int maxDecreasing = findMaxDecreasingSequence(matrix);

		// Вывод результатов
		System.out.println("\nРезультаты:");
		System.out.println("Наибольшее число возрастающих элементов подряд: " + maxIncreasing);
		System.out.println("Наибольшее число убывающих элементов подряд: " + maxDecreasing);

		scanner.close();
	}

	// Метод для поиска максимальной возрастающей последовательности
	public static int findMaxIncreasingSequence(int[][] matrix) {
		int maxSequence = 1;
		int currentSequence = 1;

		// Обход матрицы построчно
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length - 1; j++) {
				if (matrix[i][j] < matrix[i][j + 1]) {
					currentSequence++;
					maxSequence = Math.max(maxSequence, currentSequence);
				} else {
					currentSequence = 1;
				}
			}
			currentSequence = 1; // Сброс для новой строки
		}

		// Обход матрицы по столбцам
		for (int j = 0; j < matrix[0].length; j++) {
			for (int i = 0; i < matrix.length - 1; i++) {
				if (matrix[i][j] < matrix[i + 1][j]) {
					currentSequence++;
					maxSequence = Math.max(maxSequence, currentSequence);
				} else {
					currentSequence = 1;
				}
			}
			currentSequence = 1; // Сброс для нового столбца
		}

		return maxSequence;
	}

	// Метод для поиска максимальной убывающей последовательности
	public static int findMaxDecreasingSequence(int[][] matrix) {
		int maxSequence = 1;
		int currentSequence = 1;

		// Обход матрицы построчно
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length - 1; j++) {
				if (matrix[i][j] > matrix[i][j + 1]) {
					currentSequence++;
					maxSequence = Math.max(maxSequence, currentSequence);
				} else {
					currentSequence = 1;
				}
			}
			currentSequence = 1;
		}

		// Обход матрицы по столбцам
		for (int j = 0; j < matrix[0].length; j++) {

			for (int i = 0; i < matrix.length - 1; i++) {
				if (matrix[i][j] > matrix[i + 1][j]) {
					currentSequence++;
					maxSequence = Math.max(maxSequence, currentSequence);
				} else {
					currentSequence = 1;
				}
			}
			currentSequence = 1;
		}

		return maxSequence;
	}

	// Метод для вывода матрицы
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}
}
