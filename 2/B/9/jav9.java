import java.util.Scanner;

public class jav9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество строк: ");
        int n = sc.nextInt();
        System.out.print("Введите количество столбцов: ");
        int m = sc.nextInt();

        double[][] matrix = new double[n][m];

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }

        // Обработка каждой строки
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < m; j++) {
                sum += matrix[i][j];
            }
            double avg = sum / m; // среднее арифметическое строки

            for (int j = 0; j < m; j++) {
                matrix[i][j] -= avg; // вычитаем среднее
            }
        }

        System.out.println("Новая матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%8.2f", matrix[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }
}
