import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TaskB12 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Введите размер квадратной матрицы: ");
		int n = in.nextInt();
		int[][] array = new int[n][n];
		System.out.println("Сгенерированный массив:");
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = ThreadLocalRandom.current().nextInt(-n, n + 1); // случайные значения от -n до n
				System.out.printf("%4d", array[i][j]);
			}
			System.out.println();
		}
		int min = array[0][0];
		int neededrow = 3;
		int neededcol = 4;
		int minrow = 0;
		int mincol = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] < min) {
					min = array[i][j];
					minrow = i;
					mincol = j;
				}
			}
		}
		if (neededrow >= n || neededcol >= n) {
			System.out.println("Ошибка: заданная позиция выходит за пределы матрицы.");
			return;
		}
		System.out.println("\nМинимальный элемент: " + min + " на позиции [" + minrow + "][" + mincol + "]");
		// перестановка строк
		int[] tempRow = array[minrow];
		array[minrow] = array[neededrow];
		array[neededrow] = tempRow;

		for (int i = 0; i < n; i++) { // перестановка столбцов
			int temp = array[i][mincol];
			array[i][mincol] = array[i][neededcol];
			array[i][neededcol] = temp;
		}
		System.out.println("\nМатрица после перемещения минимального элемента на позицию [3][4]:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%4d", array[i][j]);
			}
			System.out.println();
		}
	}
}
