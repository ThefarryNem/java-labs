import java.util.Scanner;

public class QuadraticEquationSolver {

	public static void main(String[] args) {
		// Если аргументы не переданы, используем интерактивный режим
		if (args.length == 0) {
			interactiveMode();
			return;
		}

		// Обработка аргументов командной строки
		processCommandLineArgs(args);
	}

	public static void processCommandLineArgs(String[] args) {
		if (args.length != 3) {
			System.out.println("Ошибка: Необходимо указать 3 параметра: a b c");
			showUsage();
			return;
		}

		try {
			double a = Double.parseDouble(args[0]);
			double b = Double.parseDouble(args[1]);
			double c = Double.parseDouble(args[2]);

			solveAndDisplay(a, b, c);

		} catch (NumberFormatException e) {
			System.out.println("Ошибка: Все параметры должны быть числами!");
			showUsage();
		} catch (Exception e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}

	public static void interactiveMode() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== РЕШЕНИЕ КВАДРАТНОГО УРАВНЕНИЯ ===");
		System.out.println("Введите коэффициенты a, b, c:");

		try {
			System.out.print("a = ");
			double a = scanner.nextDouble();

			System.out.print("b = ");
			double b = scanner.nextDouble();

			System.out.print("c = ");
			double c = scanner.nextDouble();

			solveAndDisplay(a, b, c);

		} catch (Exception e) {
			System.out.println("Ошибка: Введите корректные числа!");
		} finally {
			scanner.close();
		}
	}

	public static void solveAndDisplay(double a, double b, double c) {
		System.out.printf("%nУравнение: %sx² %sx %s = 0%n",
				formatCoefficient(a), formatCoefficient(b, true), formatCoefficient(c, true));
		System.out.println("================================================");

		solveQuadraticEquationDetailed(a, b, c);
	}

	public static String formatCoefficient(double coeff, boolean withSign) {
		if (coeff == 0)
			return "0";

		String sign = withSign ? (coeff >= 0 ? "+ " : "- ") : "";
		double absValue = Math.abs(coeff);

		return absValue == 1 ? sign : sign + absValue;
	}

	public static String formatCoefficient(double coeff) {
		return formatCoefficient(coeff, false);
	}

	public static void solveQuadraticEquationDetailed(double a, double b, double c) {
		if (a == 0) {
			solveLinearEquationDetailed(b, c);
			return;
		}

		// Вычисляем дискриминант с подробным выводом
		double discriminant = b * b - 4 * a * c;
		System.out.printf("Дискриминант D = b² - 4ac = %.2f² - 4*%.2f*%.2f = %.6f%n",
				b, a, c, discriminant);

		if (discriminant > 0) {
			double sqrtD = Math.sqrt(discriminant);
			double x1 = (-b + sqrtD) / (2 * a);
			double x2 = (-b - sqrtD) / (2 * a);

			System.out.printf("D > 0: два различных действительных корня%n");
			System.out.printf("x₁ = (-b + √D) / 2a = (%.2f + %.4f) / %.2f = %.6f%n",
					-b, sqrtD, 2 * a, x1);
			System.out.printf("x₂ = (-b - √D) / 2a = (%.2f - %.4f) / %.2f = %.6f%n",
					-b, sqrtD, 2 * a, x2);

		} else if (discriminant == 0) {
			double x = -b / (2 * a);
			System.out.printf("D = 0: один действительный корень (кратности 2)%n");
			System.out.printf("x = -b / 2a = %.2f / %.2f = %.6f%n", -b, 2 * a, x);

		} else {
			double realPart = -b / (2 * a);
			double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);

			System.out.printf("D < 0: два комплексных корня%n");
			System.out.printf("x₁ = %.6f + %.6fi%n", realPart, imaginaryPart);
			System.out.printf("x₂ = %.6f - %.6fi%n", realPart, imaginaryPart);
		}
	}

	public static void solveLinearEquationDetailed(double b, double c) {
		if (b == 0) {
			System.out
					.println(c == 0 ? "Уравнение имеет бесконечное количество решений" : "Уравнение не имеет решений");
		} else {
			double x = -c / b;
			System.out.println("Это линейное уравнение. Решение:");
			System.out.printf("x = -c / b = %.2f / %.2f = %.6f%n", -c, b, x);
		}
	}

	public static void showUsage() {
		System.out.println("\nИспользование:");
		System.out.println("1. Через командную строку:");
		System.out.println("   java AdvancedQuadraticSolver <a> <b> <c>");
		System.out.println("2. Интерактивный режим (без параметров)");
		System.out.println("\nПримеры через командную строку:");
		System.out.println("   java AdvancedQuadraticSolver 1 -5 6");
		System.out.println("   java AdvancedQuadraticSolver 2 4 2");
		System.out.println("   java AdvancedQuadraticSolver 1 0 -9");
	}
}
