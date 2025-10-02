import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите размер квадратной матрицы: ");
        int n = in.nextInt();
        double[][] array = new double[n][n];
        System.out.println("Сгенерированный массив:");
        System.out.println();
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = ThreadLocalRandom.current().nextDouble(-n,n + 1);
                System.out.printf("%.2f ", array[i][j]);
            }
            System.out.println();
        }
        System.out.println("Матрица после округления: ");
        System.out.println();
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = Math.round(array[i][j]);
                System.out.printf("%.0f ", array[i][j]);
            }
            System.out.println();
        }
    }
}
