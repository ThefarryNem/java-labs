import java.util.*;

/**
 * Реализует булеву матрицу и основные операции:
 * дизъюнкция (OR), конъюнкция (AND), инверсия,
 * подсчет числа единиц и сортировка строк
 * в лексикографическом порядке.
 */
public class BoolMatrix {
	private int n; // строки
	private int m; // столбцы
	private int[][] data;

	/** Конструктор: пустая матрица n×m (нулевая) */
	public BoolMatrix(int n, int m) {
		this.n = n;
		this.m = m;
		data = new int[n][m];
	}

	/** Конструктор: случайная матрица n×m */
	public BoolMatrix(int n, int m, boolean randomFill) {
		this.n = n;
		this.m = m;
		data = new int[n][m];
		if (randomFill) {
			Random rand = new Random();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					data[i][j] = rand.nextInt(2); // 0 или 1
				}
			}
		}
	}

	/** Конструктор: по готовому массиву */
	public BoolMatrix(int[][] arr) {
		this.n = arr.length;
		this.m = arr[0].length;
		data = new int[n][m];
		for (int i = 0; i < n; i++) {
			System.arraycopy(arr[i], 0, data[i], 0, m);
		}
	}

	/** Копирующий конструктор */
	public BoolMatrix(BoolMatrix other) {
		this(other.data);
	}

	/** Дизъюнкция (OR) с другой матрицей */
	public BoolMatrix or(BoolMatrix other) {
		checkSize(other);
		int[][] res = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				res[i][j] = (this.data[i][j] | other.data[i][j]);
			}
		}
		return new BoolMatrix(res);
	}

	/** Конъюнкция (AND) с другой матрицей */
	public BoolMatrix and(BoolMatrix other) {
		checkSize(other);
		int[][] res = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				res[i][j] = (this.data[i][j] & other.data[i][j]);
			}
		}
		return new BoolMatrix(res);
	}

	/** Инверсия (NOT) */
	public BoolMatrix invert() {
		int[][] res = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				res[i][j] = (data[i][j] == 0) ? 1 : 0;
			}
		}
		return new BoolMatrix(res);
	}

	/** Подсчет числа единиц */
	public int countOnes() {
		int count = 0;
		for (int[] row : data) {
			for (int val : row) {
				if (val == 1)
					count++;
			}
		}
		return count;
	}

	/** Сортировка строк в лексикографическом порядке */
	public void sortRows() {
		Arrays.sort(data, (a, b) -> {
			for (int i = 0; i < m; i++) {
				if (a[i] != b[i]) {
					return Integer.compare(a[i], b[i]);
				}
			}
			return 0;
		});
	}

	/** Проверка совпадения размеров */
	private void checkSize(BoolMatrix other) {
		if (this.n != other.n || this.m != other.m) {
			throw new IllegalArgumentException("Размеры матриц не совпадают!");
		}
	}

	/** Печать матрицы */
	public void print() {
		for (int[] row : data) {
			for (int val : row) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}

	/** Демонстрация работы класса */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Введите количество строк (n): ");
		int n = sc.nextInt();
		System.out.print("Введите количество столбцов (m): ");
		int m = sc.nextInt();

		// Создаем случайные матрицы A и B
		BoolMatrix A = new BoolMatrix(n, m, true);
		BoolMatrix B = new BoolMatrix(n, m, true);

		System.out.println("\nМатрица A:");
		A.print();
		System.out.println("\nМатрица B:");
		B.print();

		System.out.println("\nA ИЛИ B:");
		A.or(B).print();

		System.out.println("\nA И B:");
		A.and(B).print();

		System.out.println("\nНЕ A:");
		A.invert().print();

		System.out.println("\nЧисло единиц в A: " + A.countOnes());

		System.out.println("\nМатрица A после сортировки строк:");
		A.sortRows();
		A.print();

		String dateGiven = "2025-09-09 09:00";
		Date dateNow = new Date();

		System.out.println("\n-----------------------------------");
		System.out.println("Разработчик: ФИО");
		System.out.println("Дата получения задания: " + dateGiven);
		System.out.println("Дата и время сдачи: " + dateNow);
	}
}
