import java.util.Scanner;

public class NonLinearEquation {
	private String equation;

	// Конструкторы
	public NonLinearEquation() {
		this.equation = "x + y";
	}

	public NonLinearEquation(String equation) {
		this.equation = equation;
	}

	// Сложение уравнений
	public NonLinearEquation add(NonLinearEquation other) {
		String newEquation = "(" + this.equation + ") + (" + other.equation + ")";
		return new NonLinearEquation(newEquation);
	}

	// Умножение уравнений
	public NonLinearEquation multiply(NonLinearEquation other) {
		String newEquation = "(" + this.equation + ") * (" + other.equation + ")";
		return new NonLinearEquation(newEquation);
	}

	// Вычисление значения уравнения
	public double evaluate(double x, double y) {
		String expr = equation.replace("x", String.valueOf(x))
				.replace("y", String.valueOf(y))
				.replace(" ", "")
				.replace("^", "**"); // Заменяем ^ на ** для удобства

		return evaluateExpression(expr);
	}

	private double evaluateExpression(String expr) {
		// Упрощенная версия - используем встроенный калькулятор
		// В реальном приложении нужно использовать парсер выражений
		try {
			// Простая замена для основных операций
			if (expr.equals("x"))
				return 0;
			if (expr.equals("y"))
				return 0;

			// Для демонстрации используем простые вычисления
			if (expr.contains("+")) {
				String[] parts = expr.split("\\+");
				return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
			}
			if (expr.contains("-")) {
				String[] parts = expr.split("-");
				return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
			}
			if (expr.contains("*")) {
				String[] parts = expr.split("\\*");
				return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
			}

			return Double.parseDouble(expr);
		} catch (Exception e) {
			return 0; // Упрощенная обработка ошибок
		}
	}

	// Метод биекции для нахождения корня
	public double findRootBisection(double a, double b, double fixedY, double precision) {
		double fa = evaluate(a, fixedY);
		double fb = evaluate(b, fixedY);

		if (fa * fb > 0) {
			return Double.NaN; // Нет корня на интервале
		}

		while (b - a > precision) {
			double c = (a + b) / 2;
			double fc = evaluate(c, fixedY);

			if (Math.abs(fc) < precision) {
				return c;
			}

			if (fa * fc < 0) {
				b = c;
				fb = fc;
			} else {
				a = c;
				fa = fc;
			}
		}

		return (a + b) / 2;
	}

	@Override
	public String toString() {
		return "f(x,y) = " + equation;
	}

	// Основной метод с вводом с клавиатуры
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== РАБОТА С НЕЛИНЕЙНЫМИ УРАВНЕНИЯМИ ===");

		// Ввод первого уравнения
		System.out.print("Введите первое уравнение (используйте x и y как переменные): ");
		String eq1Str = scanner.nextLine();
		NonLinearEquation eq1 = new NonLinearEquation(eq1Str);

		// Ввод второго уравнения
		System.out.print("Введите второе уравнение: ");
		String eq2Str = scanner.nextLine();
		NonLinearEquation eq2 = new NonLinearEquation(eq2Str);

		System.out.println("\n=== ВВЕДЕННЫЕ УРАВНЕНИЯ ===");
		System.out.println("Уравнение 1: " + eq1);
		System.out.println("Уравнение 2: " + eq2);

		// Операции с уравнениями
		System.out.println("\n=== ОПЕРАЦИИ С УРАВНЕНИЯМИ ===");
		NonLinearEquation sum = eq1.add(eq2);
		NonLinearEquation product = eq1.multiply(eq2);

		System.out.println("Сумма уравнений: " + sum);
		System.out.println("Произведение уравнений: " + product);

		// Вычисление значений
		System.out.println("\n=== ВЫЧИСЛЕНИЕ ЗНАЧЕНИЙ ===");
		System.out.print("Введите значение x: ");
		double x = scanner.nextDouble();
		System.out.print("Введите значение y: ");
		double y = scanner.nextDouble();

		System.out.printf("eq1(%.2f, %.2f) = %.4f\n", x, y, eq1.evaluate(x, y));
		System.out.printf("eq2(%.2f, %.2f) = %.4f\n", x, y, eq2.evaluate(x, y));
		System.out.printf("Сумма(%.2f, %.2f) = %.4f\n", x, y, sum.evaluate(x, y));
		System.out.printf("Произведение(%.2f, %.2f) = %.4f\n", x, y, product.evaluate(x, y));

		// Поиск корней методом биекции
		System.out.println("\n=== ПОИСК КОРНЕЙ МЕТОДОМ БИЕКЦИИ ===");
		System.out.print("Введите начало интервала a: ");
		double a = scanner.nextDouble();
		System.out.print("Введите конец интервала b: ");
		double b = scanner.nextDouble();
		System.out.print("Введите фиксированное значение y: ");
		double fixedY = scanner.nextDouble();
		System.out.print("Введите точность: ");
		double precision = scanner.nextDouble();

		System.out.println("\nПоиск корней для уравнения: " + eq1);
		double root1 = eq1.findRootBisection(a, b, fixedY, precision);
		if (!Double.isNaN(root1)) {
			System.out.printf("Найден корень: x = %.6f\n", root1);
			System.out.printf("Проверка: f(%.6f, %.2f) = %.8f\n", root1, fixedY, eq1.evaluate(root1, fixedY));
		} else {
			System.out.println("Корень не найден на заданном интервале");
		}

		System.out.println("\nПоиск корней для уравнения: " + eq2);
		double root2 = eq2.findRootBisection(a, b, fixedY, precision);
		if (!Double.isNaN(root2)) {
			System.out.printf("Найден корень: x = %.6f\n", root2);
			System.out.printf("Проверка: f(%.6f, %.2f) = %.8f\n", root2, fixedY, eq2.evaluate(root2, fixedY));
		} else {
			System.out.println("Корень не найден на заданном интервале");
		}

		scanner.close();
		System.out.println("\nПрограмма завершена.");
	}
}
