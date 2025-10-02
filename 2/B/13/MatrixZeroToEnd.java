import java.util.Random;
import java.util.Scanner;

public class MatrixZeroToEnd {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод размерности матрицы
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        // Создание и заполнение матрицы
        int[][] matrix = generateRandomMatrix(n);

        System.out.println("\nИсходная матрица " + n + "x" + n + ":");
        printMatrix(matrix);

        // Преобразование матрицы
        transformMatrix(matrix);

        System.out.println("\nПреобразованная матрица (нули в конце):");
        printMatrix(matrix);

        scanner.close();

        // Дополнительные тесты
        System.out.println("\n" + "=".repeat(50));

    }

    // Генерация случайной матрицы
    public static int[][] generateRandomMatrix(int n) {
        Random random = new Random();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Генерируем числа в диапазоне от -n до n
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
            }
        }

        return matrix;
    }

    // Основной метод преобразования матрицы
    public static void transformMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            moveZerosToEnd(matrix[i]);
        }
    }

    // Метод для перемещения нулей в конец одной строки
    public static void moveZerosToEnd(int[] row) {
        int nonZeroIndex = 0;

        // Переносим все ненулевые элементы в начало
        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0) {
                row[nonZeroIndex++] = row[i];
            }
        }

        // Заполняем оставшуюся часть нулями
        while (nonZeroIndex < row.length) {
            row[nonZeroIndex++] = 0;
        }
    }

    // Метод для печати матрицы
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.printf("%4d", element);
            }
            System.out.println();
        }
    }

    // Дополнительный метод: подробная статистика по матрице
    public static void printMatrixStats(int[][] matrix) {
        int totalZeros = 0;
        int totalNonZeros = 0;
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (int[] row : matrix) {
            for (int element : row) {
                if (element == 0) {
                    totalZeros++;
                } else {
                    totalNonZeros++;
                }
                minValue = Math.min(minValue, element);
                maxValue = Math.max(maxValue, element);
            }
        }

        System.out.println("Статистика матрицы:");
        System.out.println("Размер: " + matrix.length + "x" + matrix[0].length);
        System.out.println("Всего элементов: " + (matrix.length * matrix[0].length));
        System.out.println("Ненулевых элементов: " + totalNonZeros);
        System.out.println("Нулевых элементов: " + totalZeros);
        System.out.println("Минимальное значение: " + minValue);
        System.out.println("Максимальное значение: " + maxValue);
    }

    // Тестирование с разными размерами

}
