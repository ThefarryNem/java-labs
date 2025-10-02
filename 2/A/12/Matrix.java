package Lab2;

public class Matrix {
    public static void main(String[] args) {
        int rows = 5, cols = 5;
        int[][] mas = new int[rows][cols];
        int value = 1;

        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                mas[i][j] = value++;
            }
        }
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println();
        }
    }
}
