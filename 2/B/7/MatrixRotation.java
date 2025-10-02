import java.util.Random;
import java.util.Scanner;

/** Главный класс программы для выполнения задачи с матрицей. */
public class MatrixRotation {

    /** Основной метод. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;

        // Ввод размера матрицы с обработкой ошибок
        do {
            System.out.print("Введите размерность матрицы n: ");
            try {
                n = scanner.nextInt();
                if (n <= 0) {
                    System.out.println("Размерность должна быть положительным числом. Попробуйте снова.");
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите целое число.");
                scanner.nextLine(); // Очистка буфера
            }
        } while (!validInput);

        // Создаем матрицу
        int[][] a = new int[n][n];

        // Заполняем матрицу случайными числами в диапазоне [-n, n]
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextInt(2 * n + 1) - n; // от -n до n
            }
        }

        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        printMatrix(a);

        // Поворот матрицы на 90 градусов против часовой стрелки
        int[][] rotatedMatrix = rotateMatrixCounterClockwise(a);

        // Вывод повернутой матрицы
        System.out.println("\nПовернутая матрица на 90 градусов против часовой стрелки:");
        printMatrix(rotatedMatrix);

        scanner.close();
    }

    /** Метод для вывода матрицы на экран. */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.printf("%4d", elem);
            }
            System.out.println();
        }
    }

    /** Метод для поворота матрицы на 90 градусов против часовой стрелки. */
    public static int[][] rotateMatrixCounterClockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[n - j - 1][i] = matrix[i][j];
            }
        }
        return result;
    }
}
