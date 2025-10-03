import java.util.Scanner;

class SaddlePointsFinder {
	// Метод для подсчёта седловых точек
	public static int countSaddlePoints(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		int count = 0;

		for (int i = 0; i < n; i++) {
			// Находим минимальное значение в i-й строке
			int minVal = A[i][0];
			for (int j = 1; j < m; j++) {
				if (A[i][j] < minVal) {
					minVal = A[i][j];
				}
			}

			// Проверяем все элементы с этим значением на максимальность в столбце
			for (int j = 0; j < m; j++) {
				if (A[i][j] == minVal) {
					boolean isMaxInColumn = true;
					for (int k = 0; k < n; k++) {
						if (A[k][j] > A[i][j]) {
							isMaxInColumn = false;
							break;
						}
					}
					if (isMaxInColumn) {
						count++;
					}
				}
			}
		}

		return count;
	}

	// Метод для вывода матрицы
	public static void printMatrix(int[][] A) {
		for (int[] row : A) {
			for (int val : row) {
				System.out.printf("%5d", val);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите количество строк матрицы (n): ");
		int n = scanner.nextInt();

		System.out.print("Введите количество столбцов матрицы (m): ");
		int m = scanner.nextInt();

		int[][] matrix = new int[n][m];

		// Ввод элементов матрицы
		System.out.println("Введите элементы матрицы построчно:");
		for (int i = 0; i < n; i++) {
			System.out.printf("Строка %d: ", i + 1);
			for (int j = 0; j < m; j++) {
				matrix[i][j] = scanner.nextInt();
			}
		}

		System.out.println("\nВведённая матрица:");
		printMatrix(matrix);

		// Поиск и вывод количества седловых точек
		int saddlePointsCount = countSaddlePoints(matrix);
		System.out.println("\nКоличество седловых точек в матрице: " + saddlePointsCount);

		scanner.close();
	}
}
