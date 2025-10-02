import java.util.Random;
import java.util.Scanner;

public class LocalMinimumFinder {
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

		// Поиск локальных минимумов
		int localMinCount = findLocalMinimums(matrix);

		// Вывод результатов
		System.out.println("\nРезультаты:");
		System.out.println("Количество локальных минимумов: " + localMinCount);

		scanner.close();
	}

	// Метод для поиска локальных минимумов
	public static int findLocalMinimums(int[][] matrix) {
		int count = 0;
		int rows = matrix.length;
		int cols = matrix[0].length;

		// Массив для хранения позиций локальных минимумов
		System.out.println("\nЛокальные минимумы:");

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (isLocalMinimum(matrix, i, j)) {
					count++;
					System.out.printf("matrix[%d][%d] = %d\n", i, j, matrix[i][j]);
				}
			}
		}

		return count;
	}

	// Метод для проверки, является ли элемент локальным минимумом
	public static boolean isLocalMinimum(int[][] matrix, int row, int col) {
		int current = matrix[row][col];
		int rows = matrix.length;
		int cols = matrix[0].length;

		// Проверяем всех соседей (8 направлений)
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				// Пропускаем сам элемент
				if (i == 0 && j == 0)
					continue;

				int neighborRow = row + i;
				int neighborCol = col + j;

				// Проверяем, находится ли сосед в пределах матрицы
				if (neighborRow >= 0 && neighborRow < rows &&
						neighborCol >= 0 && neighborCol < cols) {

					// Если найден сосед, который меньше или равен текущему элементу,
					// то текущий элемент не является локальным минимумом
					if (matrix[neighborRow][neighborCol] <= current) {
						return false;
					}
				}
			}
		}

		return true;
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
