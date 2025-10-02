import java.util.Arrays;

public class BoolVector {
	private boolean[] vector;
	private int n;

	public BoolVector(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Размерность вектора должна быть положительной");
		}
		this.n = n;
		this.vector = new boolean[n];
	}

	public BoolVector(boolean[] vector) {
		if (vector == null) {
			throw new IllegalArgumentException("Вектор не может быть null");
		}
		this.n = vector.length;
		this.vector = Arrays.copyOf(vector, vector.length);
	}

	public BoolVector(String binaryString) {
		if (binaryString == null || binaryString.isEmpty()) {
			throw new IllegalArgumentException("Строка не может быть пустой или null");
		}
		this.n = binaryString.length();
		this.vector = new boolean[n];
		for (int i = 0; i < n; i++) {
			char c = binaryString.charAt(i);
			if (c == '1') {
				vector[i] = true;
			} else if (c == '0') {
				vector[i] = false;
			} else {
				throw new IllegalArgumentException("Строка должна содержать только символы '1' и '0'");
			}
		}
	}

	public BoolVector(BoolVector other) {
		if (other == null) {
			throw new IllegalArgumentException("Вектор не может быть null");
		}
		this.n = other.n;
		this.vector = Arrays.copyOf(other.vector, other.n);
	}

	public int getSize() {
		return n;
	}

	public boolean get(int index) {
		if (index < 0 || index >= n) {
			throw new IndexOutOfBoundsException("Индекс выходит за границы вектора");
		}
		return vector[index];
	}

	public void set(int index, boolean value) {
		if (index < 0 || index >= n) {
			throw new IndexOutOfBoundsException("Индекс выходит за границы вектора");
		}
		vector[index] = value;
	}

	// Поразрядная конъюнкция (AND)
	public BoolVector and(BoolVector other) {
		checkVectorCompatibility(other);
		boolean[] result = new boolean[n];

		for (int i = 0; i < n; i++) {
			result[i] = this.vector[i] && other.vector[i];
		}

		return new BoolVector(result);
	}

	// Поразрядная дизъюнкция (OR)
	public BoolVector or(BoolVector other) {
		checkVectorCompatibility(other);
		boolean[] result = new boolean[n];

		for (int i = 0; i < n; i++) {
			result[i] = this.vector[i] || other.vector[i];
		}

		return new BoolVector(result);
	}

	// Отрицание (NOT)
	public BoolVector not() {
		boolean[] result = new boolean[n];

		for (int i = 0; i < n; i++) {
			result[i] = !this.vector[i];
		}

		return new BoolVector(result);
	}

	// Подсчет числа единиц
	public int countOnes() {
		int count = 0;
		for (boolean value : vector) {
			if (value) {
				count++;
			}
		}
		return count;
	}

	// Подсчет числа нулей
	public int countZeros() {
		int count = 0;
		for (boolean value : vector) {
			if (!value) {
				count++;
			}
		}
		return count;
	}

	// Проверка совместимости векторов
	private void checkVectorCompatibility(BoolVector other) {
		if (other == null) {
			throw new IllegalArgumentException("Вектор не может быть null");
		}
		if (this.n != other.n) {
			throw new IllegalArgumentException("Векторы должны иметь одинаковую размерность");
		}
	}

	// Представление в виде строки
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < n; i++) {
			sb.append(vector[i] ? "1" : "0");
			if (i < n - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// Проверка равенства
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		BoolVector that = (BoolVector) obj;
		return n == that.n && Arrays.equals(vector, that.vector);
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(vector);
		result = 31 * result + n;
		return result;
	}

	// Пример использования
	public static void main(String[] args) {
		// Создание векторов разными способами
		BoolVector v1 = new BoolVector(new boolean[] { true, false, true, false });
		BoolVector v2 = new BoolVector("1010");
		BoolVector v3 = new BoolVector(4);
		v3.set(0, true);
		v3.set(2, true);

		System.out.println("Вектор 1: " + v1);
		System.out.println("Вектор 2: " + v2);
		System.out.println("Вектор 3: " + v3);

		// Операции
		System.out.println("\nОперации:");
		System.out.println("v1 AND v2: " + v1.and(v2));
		System.out.println("v1 OR v2: " + v1.or(v2));
		System.out.println("NOT v1: " + v1.not());

		// Подсчет
		System.out.println("\nПодсчет:");
		System.out.println("Единиц в v1: " + v1.countOnes());
		System.out.println("Нулей в v1: " + v1.countZeros());

		// Создание из строки
		try {
			BoolVector v4 = new BoolVector("11001");
			System.out.println("\nВектор из строки: " + v4);
			System.out.println("Единиц: " + v4.countOnes());
			System.out.println("Нулей: " + v4.countZeros());
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}
}
