 import java.util.*;

public class MatrixSortByRowSum {

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

        // Заполнение матрицы случайными числами [-10, 10]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(21) - 10; // от -10 до 10
            }
        }

        System.out.println("\nИсходная матрица:");
        printMatrix(matrix);

        // Сортировка строк по сумме элементов
        Arrays.sort(matrix, Comparator.comparingInt(MatrixSortByRowSum::rowSum));

        System.out.println("\nМатрица после сортировки строк по возрастанию суммы:");
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

    /** Подсчёт суммы строки */
    public static int rowSum(int[] row) {
        int sum = 0;
        for (int val : row) {
            sum += val;
        }
        return sum;
    }
} 
