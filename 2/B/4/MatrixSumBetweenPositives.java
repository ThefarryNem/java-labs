import java.util.*;

public class MatrixSumBetweenPositives {
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

        // Поиск суммы между первым и вторым положительными элементами каждой строки
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            int firstPos = -1;
            int secondPos = -1;

            // Поиск индексов первого и второго положительных элементов
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) {
                    if (firstPos == -1) {
                        firstPos = j;
                    } else {
                        secondPos = j;
                        break;
                    }
                }
            }

            // Если найдены оба положительных элемента
            if (firstPos != -1 && secondPos != -1 && secondPos - firstPos > 1) {
                for (int j = firstPos + 1; j < secondPos; j++) {
                    totalSum += matrix[i][j];
                }
            }
        }

        System.out.println("Сумма элементов между первым и вторым положительными элементами каждой строки: " + totalSum);
    }
}
