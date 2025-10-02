import java.util.*;

public class LocalMaxFinder {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();

		System.out.print("Введите размерность матрицы n: ");
		int n = scanner.nextInt();

		int[][] matrix = new int[n][n];

		// Заполнение матрицы случайными числами от -n до n
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = rand.nextInt(2 * n + 1) - n;
			}
		}

		// Вывод матрицы
		System.out.println("Сгенерированная матрица:");
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}

		// Поиск наибольшего локального максимума
		Integer maxLocal = null;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int current = matrix[i][j];
				boolean isLocalMax = true;

				// Проверка всех соседей
				for (int di = -1; di <= 1; di++) {
					for (int dj = -1; dj <= 1; dj++) {
						if (di == 0 && dj == 0)
							continue; // пропустить сам элемент

						int ni = i + di;
						int nj = j + dj;

						if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
							if (matrix[ni][nj] >= current) {
								isLocalMax = false;
								break;
							}
						}
					}
					if (!isLocalMax)
						break;
				}

				if (isLocalMax) {
					if (maxLocal == null || current > maxLocal) {
						maxLocal = current;
					}
				}
			}
		}

		if (maxLocal != null) {
			System.out.println("Наибольший среди локальных максимумов: " + maxLocal);
		} else {
			System.out.println("Локальные максимумы не найдены.");
		}
	}
}
