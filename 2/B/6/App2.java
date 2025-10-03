import java.util.Random;
import java.util.Scanner;

public class App2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ввод размерности матрицы
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();
        
        // Создание и заполнение матрицы случайными числами
        int[][] matrix = new int[n][n];
        Random random = new Random();
        
        System.out.println("\nСгенерированная матрица " + n + "x" + n + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Генерация чисел в интервале от -n до n
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
        
        // Вычисление нормы матрицы (максимальная сумма модулей элементов строки)
        double norm = calculateMatrixNorm(matrix);
        
        System.out.printf("\nНорма матрицы: %.2f\n", norm);
        
        scanner.close();
    }
    
    /**
     * Вычисляет норму матрицы как максимальную сумму модулей элементов строки
     * @param matrix матрица для вычисления нормы
     * @return норма матрицы
     */
    public static double calculateMatrixNorm(int[][] matrix) {
        double maxRowSum = 0;
        int n = matrix.length;
        
        for (int i = 0; i < n; i++) {
            double rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += Math.abs(matrix[i][j]);
            }
            if (rowSum > maxRowSum) {
                maxRowSum = rowSum;
            }
        }
        
        return maxRowSum;
    }
}
