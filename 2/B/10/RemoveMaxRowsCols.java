import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class RemoveMaxRowsCols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];
        Random rand = new Random();
        System.out.println("Исходная матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(2 * n + 1) - n;
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        int max = matrix[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > max) max = matrix[i][j];
            }
        }
        System.out.println("Максимальный элемент: " + max);

        ArrayList<Integer> rowsToRemove = new ArrayList<>();
        ArrayList<Integer> colsToRemove = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == max) {
                    if (!rowsToRemove.contains(i)) rowsToRemove.add(i);
                    if (!colsToRemove.contains(j)) colsToRemove.add(j);
                }
            }
        }

        int newRows = n - rowsToRemove.size();
        int newCols = n - colsToRemove.size();
        int[][] newMatrix = new int[newRows][newCols];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (rowsToRemove.contains(i)) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (colsToRemove.contains(j)) continue;
                newMatrix[r][c++] = matrix[i][j];
            }
            r++;
        }

        System.out.println("Матрица после удаления строк и столбцов с максимальным элементом:");
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                System.out.print(newMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
