
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

/** Класс Vector размерности n */
class Vector {
	private final int n; // Размерность вектора
	private final double[] data;

	/** Конструктор: создает вектор размерности n, заполненный нулями */
	public Vector(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Размерность вектора должна быть положительной");
		}
		this.n = n;
		data = new double[n];
	}

	/** Конструктор: создает вектор на основе массива */
	public Vector(double[] data) {
		this.n = data.length;
		this.data = new double[n];
		System.arraycopy(data, 0, this.data, 0, n);
	}

	/** Получить размерность */
	public int size() {
		return n;
	}

	/** Индексирование: получить значение по индексу */
	public double get(int index) {
		checkIndex(index);
		return data[index];
	}

	/** Индексирование: установить значение по индексу */
	public void set(int index, double value) {
		checkIndex(index);
		data[index] = value;
	}

	/** Проверить индекс на валидность */
	private void checkIndex(int index) {
		if (index < 0 || index >= n) {
			throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
		}
	}

	/** Сложение векторов */
	public Vector add(Vector other) {
		checkSize(other);
		double[] result = new double[n];
		for (int i = 0; i < n; i++) {
			result[i] = this.data[i] + other.data[i];
		}
		return new Vector(result);
	}

	/** Вычитание векторов */
	public Vector subtract(Vector other) {
		checkSize(other);
		double[] result = new double[n];
		for (int i = 0; i < n; i++) {
			result[i] = this.data[i] - other.data[i];
		}
		return new Vector(result);
	}

	/** Скалярное произведение */
	public double dot(Vector other) {
		checkSize(other);
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += this.data[i] * other.data[i];
		}
		return sum;
	}

	/** Инкремент: увеличить каждый компонент на 1 */
	public void increment() {
		for (int i = 0; i < n; i++) {
			data[i]++;
		}
	}

	/** Декремент: уменьшить каждый компонент на 1 */
	public void decrement() {
		for (int i = 0; i < n; i++) {
			data[i]--;
		}
	}

	/** Длина (норма) вектора */
	public double length() {
		double sum = 0;
		for (double v : data) {
			sum += v * v;
		}
		return Math.sqrt(sum);
	}

	/** Проверка совпадения размерностей */
	private void checkSize(Vector other) {
		if (this.n != other.n) {
			throw new IllegalArgumentException("Размерности векторов не совпадают");
		}
	}

	/** Красивый вывод вектора */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < n; i++) {
			sb.append(data[i]);
			if (i != n - 1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}

/** Основной класс для демонстрации работы с Vector */
public class Main {

	/** Метод для вычисления угла в градусах между двумя векторами */
	public static double angleBetween(Vector v1, Vector v2) {
		double dot = v1.dot(v2);
		double lenProduct = v1.length() * v2.length();
		if (lenProduct == 0) {
			throw new ArithmeticException("Длина одного из векторов равна нулю, угол неопределен");
		}
		// Защита от погрешностей => значение косинуса должно быть в [-1,1]
		double cosTheta = dot / lenProduct;
		cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));
		return Math.toDegrees(Math.acos(cosTheta));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите размерность векторов n: ");
		int n = scanner.nextInt();
		System.out.print("Введите количество векторов m: ");
		int m = scanner.nextInt();
		scanner.nextLine(); // очистка буфера

		Vector[] vectors = new Vector[m];

		// Ввод векторов
		for (int i = 0; i < m; i++) {
			double[] components = new double[n];
			System.out.printf("Введите %d компонентов вектора %d через пробел: ", n, i + 1);
			String line = scanner.nextLine();
			String[] parts = line.trim().split("\\s+");
			if (parts.length != n) {
				System.out.println("Ошибка: введено неверное количество компонентов.");
				i--; // повторить ввод
				continue;
			}
			try {
				for (int j = 0; j < n; j++) {
					components[j] = Double.parseDouble(parts[j]);
				}
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: неверный формат числа. Повторите ввод.");
				i--;
				continue;
			}
			vectors[i] = new Vector(components);
		}

		// Вывод всех векторов
		System.out.println("\nВведенные векторы:");
		for (int i = 0; i < m; i++) {
			System.out.printf("Вектор %d: %s%n", i + 1, vectors[i]);
		}

		// Для каждой пары векторов вычислить скалярное произведение, длины и угол
		System.out.println("\nСкалярное произведение, длины и угол между векторами:");

		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				Vector v1 = vectors[i];
				Vector v2 = vectors[j];
				double dot = v1.dot(v2);
				double len1 = v1.length();
				double len2 = v2.length();
				try {
					double angle = angleBetween(v1, v2);
					System.out.printf("Векторы %d и %d: dot=%.3f, |v1|=%.3f, |v2|=%.3f, угол=%.2f°%n",
							i + 1, j + 1, dot, len1, len2, angle);
				} catch (ArithmeticException e) {
					System.out.printf("Векторы %d и %d: один из векторов нулевой, угол не определен.%n", i + 1, j + 1);
				}
			}
		}

		scanner.close();
	}
}
