public class Matrix {
	private int[][] elements;
	private int rows;
	private int cols;

	public Matrix(int[][] elements) {
		this.elements = elements;
		this.rows = elements.length;
		this.cols = elements[0].length;
	}

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.elements = new int[rows][cols];
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();

		for (int[] row : elements) {
			builder.append("|\t");
			for (int value : row) {
				builder.append(value + "\t");
			}
			builder.append("|\n");
		}
		return builder.toString();
	}

	public void swapRowsWithMinMaxInColumn(int k) {
		if (k < 0 || k >= cols) {
			throw new IllegalArgumentException("Индекс столбца вне диапазона.");
		}

		int minRow = 0;
		int maxRow = 0;
		int minValue = elements[0][k];
		int maxValue = elements[0][k];

		for (int i = 0; i < rows; i++) {
			if (elements[i][k] < minValue) {
				minValue = elements[i][k];
				minRow = i;
			}
			if (elements[i][k] > maxValue) {
				maxValue = elements[i][k];
				maxRow = i;
			}
		}

		if (minRow != maxRow) {
			int[] temp = elements[minRow];
			elements[minRow] = elements[maxRow];
			elements[maxRow] = temp;
		}
	}

	public Matrix square() {
		if (rows != cols) {
			throw new IllegalArgumentException("Матрица должна быть квадратной для возведения в квадрат.");
		}

		int[][] result = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				for (int k = 0; k < cols; k++) {
					result[i][j] += elements[i][k] * elements[k][j];
				}
			}
		}

		return new Matrix(result);
	}

	public static void main(String[] args) {
		Matrix[] matrices = new Matrix[2];
		matrices[0] = new Matrix(new int[][] {
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 }
		});

		matrices[1] = new Matrix(new int[][] {
				{ 9, 8, 7 },
				{ 6, 5, 4 },
				{ 3, 2, 1 }
		});

		System.out.println("Первая матрица:");
		System.out.println(matrices[0]);

		matrices[0].swapRowsWithMinMaxInColumn(0);
		System.out.println("Матрица после замены строк в 1-м столбце:");
		System.out.println(matrices[0]);

		Matrix squaredMatrix = matrices[1].square();
		System.out.println("Вторая матрица в квадрате:");
		System.out.println(squaredMatrix);
	}
}
