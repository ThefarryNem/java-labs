import java.util.function.DoubleUnaryOperator;
import java.util.Scanner;

public class Main {

	// Класс для вычисления определённого интеграла
	public static class DefiniteIntegral {
		private DoubleUnaryOperator function;
		private double a;
		private double b;
		private int n;

		public DefiniteIntegral(DoubleUnaryOperator function, double a, double b, int n) {
			if (n <= 0) {
				throw new IllegalArgumentException("Число разбиений должно быть положительным");
			}
			this.function = function;
			this.a = a;
			this.b = b;
			this.n = n;
		}

		// Методы вычисления (оставлены без изменений)
		public double leftRectangles() {
			double h = (b - a) / n;
			double sum = 0;
			for (int i = 0; i < n; i++) {
				double x = a + i * h;
				sum += function.applyAsDouble(x);
			}
			return sum * h;
		}

		public double rightRectangles() {
			double h = (b - a) / n;
			double sum = 0;
			for (int i = 1; i <= n; i++) {
				double x = a + i * h;
				sum += function.applyAsDouble(x);
			}
			return sum * h;
		}

		public double midRectangles() {
			double h = (b - a) / n;
			double sum = 0;
			for (int i = 0; i < n; i++) {
				double x = a + (i + 0.5) * h;
				sum += function.applyAsDouble(x);
			}
			return sum * h;
		}

		public double trapezoids() {
			double h = (b - a) / n;
			double sum = 0.5 * (function.applyAsDouble(a) + function.applyAsDouble(b));
			for (int i = 1; i < n; i++) {
				double x = a + i * h;
				sum += function.applyAsDouble(x);
			}
			return sum * h;
		}

		public double simpson() {
			if (n % 2 != 0) {
				throw new IllegalArgumentException("Для метода Симпсона n должно быть чётным");
			}
			double h = (b - a) / n;
			double sum = function.applyAsDouble(a) + function.applyAsDouble(b);
			for (int i = 1; i < n; i++) {
				double x = a + i * h;
				sum += (i % 2 == 0 ? 2 : 4) * function.applyAsDouble(x);
			}
			return sum * h / 3.0;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите левую границу интегрирования (a):");
		double a = scanner.nextDouble();

		System.out.println("Введите правую границу интегрирования (b):");
		double b = scanner.nextDouble();

		System.out.println("Введите количество разбиений n (лучше четное для метода Симпсона):");
		int n = scanner.nextInt();

		System.out.println("Выберите функцию для интегрирования:");
		System.out.println("1 - f(x) = x^2");
		System.out.println("2 - f(x) = sin(x)");
		System.out.println("3 - f(x) = e^x");
		int choice = scanner.nextInt();

		DoubleUnaryOperator f;

		switch (choice) {
			case 1:
				f = x -> x * x;
				break;
			case 2:
				f = Math::sin;
				break;
			case 3:
				f = Math::exp;
				break;
			default:
				System.out.println("Некорректный выбор. Используем x^2 по умолчанию.");
				f = x -> x * x;
		}

		try {
			DefiniteIntegral integral = new DefiniteIntegral(f, a, b, n);
			System.out.printf("Левые прямоугольники:  %.6f%n", integral.leftRectangles());
			System.out.printf("Правые прямоугольники:  %.6f%n", integral.rightRectangles());
			System.out.printf("Средние прямоугольники: %.6f%n", integral.midRectangles());
			System.out.printf("Трапеции:              %.6f%n", integral.trapezoids());
			if (n % 2 == 0) {
				System.out.printf("Симпсон:               %.6f%n", integral.simpson());
			} else {
				System.out.println("n должно быть чётным для метода Симпсона.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}

		scanner.close();
	}
}
