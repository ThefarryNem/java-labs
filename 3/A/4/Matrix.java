import java.util.Arrays;

public class Matrix {
	private double[][] data;
	private int n;

	public Matrix(int n) {
		this.n = n;
		this.data = new double[n][n];
	}

	public Matrix(double[][] data) {
		this.n = data.length;
		this.data = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	public Matrix(Matrix other) {
		this.n = other.n;
		this.data = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.data[i][j] = other.data[i][j];
			}
		}
	}

	public Matrix add(Matrix other) {
		if (this.n != other.n) {
			throw new IllegalArgumentException("Матрицы должны быть одного размера");
		}

		Matrix result = new Matrix(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result.data[i][j] = this.data[i][j] + other.data[i][j];
			}
		}
		return result;
	}

	public Matrix subtract(Matrix other) {
		if (this.n != other.n) {
			throw new IllegalArgumentException("Матрицы должны быть одного размера");
		}

		Matrix result = new Matrix(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result.data[i][j] = this.data[i][j] - other.data[i][j];
			}
		}
		return result;
	}

	public Matrix multiply(Matrix other) {
		if (this.n != other.n) {
			throw new IllegalArgumentException("Матрицы должны быть одного размера");
		}

		Matrix result = new Matrix(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					result.data[i][j] += this.data[i][k] * other.data[k][j];
				}
			}
		}
		return result;
	}

	public double firstNorm() {
		double maxNorm = 0;
		for (int j = 0; j < n; j++) {
			double columnSum = 0;
			for (int i = 0; i < n; i++) {
				columnSum += Math.abs(data[i][j]);
			}
			if (columnSum > maxNorm) {
				maxNorm = columnSum;
			}
		}
		return maxNorm;
	}

	public double secondNorm() {
		double maxNorm = 0;
		for (int i = 0; i < n; i++) {
			double rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += Math.abs(data[i][j]);
			}
			if (rowSum > maxNorm) {
				maxNorm = rowSum;
			}
		}
		return maxNorm;
	}

	public double getElement(int i, int j) {
		return data[i][j];
	}

	public void setElement(int i, int j, double value) {
		data[i][j] = value;
	}

	public int getSize() {
		return n;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(Arrays.toString(data[i])).append("\n");
		}
		return sb.toString();
	}

	public static Matrix findMatrixWithMinFirstNorm(Matrix[] matrices) {
		if (matrices == null || matrices.length == 0) {
			return null;
		}

		Matrix minNormMatrix = matrices[0];
		double minNorm = matrices[0].firstNorm();

		for (int i = 1; i < matrices.length; i++) {
			double currentNorm = matrices[i].firstNorm();
			if (currentNorm < minNorm) {
				minNorm = currentNorm;
				minNormMatrix = matrices[i];
			}
		}
		return minNormMatrix;
	}

	public static Matrix findMatrixWithMinSecondNorm(Matrix[] matrices) {
		if (matrices == null || matrices.length == 0) {
			return null;
		}

		Matrix minNormMatrix = matrices[0];
		double minNorm = matrices[0].secondNorm();

		for (int i = 1; i < matrices.length; i++) {
			double currentNorm = matrices[i].secondNorm();
			if (currentNorm < minNorm) {
				minNorm = currentNorm;
				minNormMatrix = matrices[i];
			}
		}
		return minNormMatrix;
	}

	public static void main(String[] args) {
		Matrix[] matrices = new Matrix[3];

		matrices[0] = new Matrix(new double[][] {
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 }
		});

		matrices[1] = new Matrix(new double[][] {
				{ 0.5, 1.0, 1.5 },
				{ 2.0, 2.5, 3.0 },
				{ 3.5, 4.0, 4.5 }
		});

		matrices[2] = new Matrix(new double[][] {
				{ 10, 0, 0 },
				{ 0, 5, 0 },
				{ 0, 0, 1 }
		});

		System.out.println("Матрицы и их нормы:");
		for (int i = 0; i < matrices.length; i++) {
			System.out.println("Матрица " + (i + 1) + ":");
			System.out.println(matrices[i]);
			System.out.printf("Первая норма: %.2f\n", matrices[i].firstNorm());
			System.out.printf("Вторая норма: %.2f\n", matrices[i].secondNorm());
			System.out.println();
		}

		// Находим матрицы с минимальными нормами
		Matrix minFirstNorm = findMatrixWithMinFirstNorm(matrices);
		Matrix minSecondNorm = findMatrixWithMinSecondNorm(matrices);

		System.out.println("Матрица с наименьшей первой нормой:");
		System.out.println(minFirstNorm);
		System.out.printf("Первая норма: %.2f\n", minFirstNorm.firstNorm());

		System.out.println("\nМатрица с наименьшей второй нормой:");
		System.out.println(minSecondNorm);
		System.out.printf("Вторая норма: %.2f\n", minSecondNorm.secondNorm());

		// Демонстрация операций с матрицами
		System.out.println("\nОперации с матрицами:");
		Matrix sum = matrices[0].add(matrices[1]);
		System.out.println("Сумма матриц 1 и 2:");
		System.out.println(sum);

		Matrix product = matrices[0].multiply(matrices[1]);
		System.out.println("Произведение матриц 1 и 2:");
		System.out.println(product);
	}
}
