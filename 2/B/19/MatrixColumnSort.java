import java.util.Random;
import java.util.Scanner;

public class MatrixColumnSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();
        scanner.close();

        int[][] matrix = new int[n][n];
        Random random = new Random();
        
        System.out.println("Исходная матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }

        sortColumnsByCharacteristic(matrix);

        System.out.println("Матрица после перестановки столбцов:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int calculateColumnCharacteristic(int[][] matrix, int col) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += Math.abs(matrix[i][col]);
        }
        return sum;
    }

    public static void sortColumnsByCharacteristic(int[][] matrix) {
        int n = matrix.length;
        int[] characteristics = new int[n];
        
        for (int j = 0; j < n; j++) {
            characteristics[j] = calculateColumnCharacteristic(matrix, j);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (characteristics[j] < characteristics[j + 1]) {
                    int tempChar = characteristics[j];
                    characteristics[j] = characteristics[j + 1];
                    characteristics[j + 1] = tempChar;
                    
                    swapColumns(matrix, j, j + 1);
                }
            }
        }
    }

    public static void swapColumns(int[][] matrix, int col1, int col2) {
        for (int i = 0; i < matrix.length; i++) {
            int temp = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
    }
}
//2.B.19
