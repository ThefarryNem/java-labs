import java.util.Random;
import java.util.Scanner;

public class MatrixDeterminant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];
        Random rand = new Random();

        System.out.println("Матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(2 * n + 1) - n;
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        int det = determinant(matrix);
        System.out.println("Определитель матрицы: " + det);
    }

    public static int determinant(int[][] mat) {
        int n = mat.length;
        if (n == 1) return mat[0][0];
        if (n == 2) return mat[0][0]*mat[1][1] - mat[0][1]*mat[1][0];

        int det = 0;
        for (int col = 0; col < n; col++) {
            det += Math.pow(-1, col) * mat[0][col] * determinant(minor(mat, 0, col));
        }
        return det;
    }

    // Получение минорной матрицы
    public static int[][] minor(int[][] mat, int row, int col) {
        int n = mat.length;
        int[][] minor = new int[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[r][c++] = mat[i][j];
            }
            r++;
        }
        return minor;
    }
}
