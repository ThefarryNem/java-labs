import java.util.Random;
import java.util.Scanner;

public class App3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ввод размерности матрицы
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();
        
        // Создание и заполнение матрицы случайными числами
        double[][] matrix = new double[n][n];
        Random random = new Random();
        
        System.out.println("\nИсходная матрица " + n + "x" + n + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Генерация вещественных чисел в интервале от -n до n
                matrix[i][j] = -n + random.nextDouble() * (2 * n);
                System.out.printf("%8.2f", matrix[i][j]);
            }
            System.out.println();
        }
        
        // Сортируем элементы на главной диагонали
        sortMatrixDiagonal(matrix);
        
        // Вывод результата
        System.out.println("\nМатрица после перестановки элементов на главную диагональ:");
        printMatrix(matrix);
        
        scanner.close();
    }
    
    /**
     * Сортирует матрицу так, чтобы на главной диагонали были максимальные элементы
     * в порядке убывания
     */
    public static void sortMatrixDiagonal(double[][] matrix) {
        int n = matrix.length;
        
        // Проходим по всем позициям главной диагонали
        for (int k = 0; k < n; k++) {
            // Находим максимальный элемент в оставшейся части матрицы
            int maxI = k;
            int maxJ = k;
            double maxValue = matrix[k][k];
            
            // Ищем максимальный элемент среди всех элементов, которые еще не на диагонали
            for (int i = k; i < n; i++) {
                for (int j = k; j < n; j++) {
                    if (matrix[i][j] > maxValue) {
                        maxValue = matrix[i][j];
                        maxI = i;
                        maxJ = j;
                    }
                }
            }
            
            // Переставляем найденный максимальный элемент на текущую позицию диагонали
            if (maxI != k || maxJ != k) {
                // Меняем строки местами, если нужно
                if (maxI != k) {
                    swapRows(matrix, k, maxI);
                }
                // Меняем столбцы местами, если нужно
                if (maxJ != k) {
                    swapColumns(matrix, k, maxJ);
                }
            }
        }
    }
    
    /**
     * Меняет две строки матрицы местами
     */
    public static void swapRows(double[][] matrix, int row1, int row2) {
        int n = matrix.length;
        for (int j = 0; j < n; j++) {
            double temp = matrix[row1][j];
            matrix[row1][j] = matrix[row2][j];
            matrix[row2][j] = temp;
        }
    }
    
    /**
     * Меняет два столбца матрицы местами
     */
    public static void swapColumns(double[][] matrix, int col1, int col2) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            double temp = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
    }
    
    /**
     * Выводит матрицу на экран
     */
    public static void printMatrix(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%8.2f", matrix[i][j]);
            }
            System.out.println();
        }
        
        // Дополнительно выводим элементы главной диагонали
        System.out.println("\nЭлементы главной диагонали:");
        for (int i = 0; i < n; i++) {
            System.out.printf("%8.2f", matrix[i][i]);
        }
        System.out.println();
    }
}
