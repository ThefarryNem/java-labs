 import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MatrixShift {

    /**
     * Основной метод программы
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Дата получения задания (фиксированная)
        String dateGiven = "2025-09-09 09:00";

        // Текущая дата сдачи
        Date dateNow = new Date();

        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        // Заполнение матрицы случайными числами [-n, n]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
            }
        }

        System.out.println("\nИсходная матрица:");
        printMatrix(matrix);

        System.out.print("\nВведите количество позиций для сдвига k: ");
        int k = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        System.out.print("Введите направление (влево/вправо/вверх/вниз): ");
        String direction = scanner.nextLine().trim().toLowerCase();

        // Выполняем сдвиг
        switch (direction) {
            case "влево":
                shiftLeft(matrix, k);
                break;
            case "вправо":
                shiftRight(matrix, k);
                break;
            case "вверх":
                shiftUp(matrix, k);
                break;
            case "вниз":
                shiftDown(matrix, k);
                break;
            default:
                System.out.println("Некорректное направление!");
                return;
        }

        System.out.println("\nМатрица после сдвига:");
        printMatrix(matrix);

        // Информация об авторе
        System.out.println("\n-----------------------------------");
        System.out.println("Разработчик: ФИО");
        System.out.println("Дата получения задания: " + dateGiven);
        System.out.println("Дата и время сдачи: " + dateNow);
    }

    /** Вывод матрицы */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    /** Циклический сдвиг строк влево */
    public static void shiftLeft(int[][] matrix, int k) {
        int n = matrix.length;
        k %= n;
        for (int i = 0; i < n; i++) {
            int[] newRow = new int[n];
            for (int j = 0; j < n; j++) {
                newRow[j] = matrix[i][(j + k) % n];
            }
            matrix[i] = newRow;
        }
    }

    /** Циклический сдвиг строк вправо */
    public static void shiftRight(int[][] matrix, int k) {
        int n = matrix.length;
        k %= n;
        for (int i = 0; i < n; i++) {
            int[] newRow = new int[n];
            for (int j = 0; j < n; j++) {
                newRow[(j + k) % n] = matrix[i][j];
            }
            matrix[i] = newRow;
        }
    }

    /** Циклический сдвиг столбцов вверх */
    public static void shiftUp(int[][] matrix, int k) {
        int n = matrix.length;
        k %= n;
        for (int j = 0; j < n; j++) {
            int[] newCol = new int[n];
            for (int i = 0; i < n; i++) {
                newCol[i] = matrix[(i + k) % n][j];
            }
            for (int i = 0; i < n; i++) {
                matrix[i][j] = newCol[i];
            }
        }
    }

    /** Циклический сдвиг столбцов вниз */
    public static void shiftDown(int[][] matrix, int k) {
        int n = matrix.length;
        k %= n;
        for (int j = 0; j < n; j++) {
            int[] newCol = new int[n];
            for (int i = 0; i < n; i++) {
                newCol[(i + k) % n] = matrix[i][j];
            }
            for (int i = 0; i < n; i++) {
                matrix[i][j] = newCol[i];
            }
        }
    }
}
