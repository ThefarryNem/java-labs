import java.util.Arrays;
import java.util.Scanner;

class Polynomial {
	private double[] coefficients;
	private int degree;

	// Конструкторы
	public Polynomial() {
		this.coefficients = new double[] { 0 };
		this.degree = 0;
	}

	public Polynomial(double[] coefficients) {
		if (coefficients == null || coefficients.length == 0) {
			throw new IllegalArgumentException("Коэффициенты не могут быть пустыми");
		}

		// Убираем ведущие нули
		int realLength = coefficients.length;
		while (realLength > 1 && coefficients[realLength - 1] == 0) {
			realLength--;
		}

		this.coefficients = Arrays.copyOf(coefficients, realLength);
		this.degree = realLength - 1;
	}

	public Polynomial(int degree) {
		if (degree < 0) {
			throw new IllegalArgumentException("Степень не может быть отрицательной");
		}
		this.coefficients = new double[degree + 1];
		this.degree = degree;
	}

	// Метод ввода полинома
	public static Polynomial inputPolynomial() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите степень полинома: ");
		int degree = scanner.nextInt();

		double[] coefficients = new double[degree + 1];
		System.out.println("Введите коэффициенты (от младшей степени к старшей):");

		for (int i = 0; i <= degree; i++) {
			System.out.printf("Коэффициент при x^%d: ", i);
			coefficients[i] = scanner.nextDouble();
		}

		return new Polynomial(coefficients);
	}

	// Метод сложения полиномов
	public Polynomial add(Polynomial other) {
		int maxDegree = Math.max(this.degree, other.degree);
		double[] result = new double[maxDegree + 1];

		for (int i = 0; i <= this.degree; i++) {
			result[i] += this.coefficients[i];
		}

		for (int i = 0; i <= other.degree; i++) {
			result[i] += other.coefficients[i];
		}

		return new Polynomial(result);
	}

	// Метод вывода для сложения
	public void printAddition(Polynomial other) {
		Polynomial result = this.add(other);
		System.out.println("СЛОЖЕНИЕ ПОЛИНОМОВ:");
		System.out.println("Полином 1: " + this);
		System.out.println("Полином 2: " + other);
		System.out.println("Результат: " + result);
		System.out.println("-----------------------------");
	}

	// Метод умножения полиномов
	public Polynomial multiply(Polynomial other) {
		int resultDegree = this.degree + other.degree;
		double[] result = new double[resultDegree + 1];

		for (int i = 0; i <= this.degree; i++) {
			for (int j = 0; j <= other.degree; j++) {
				result[i + j] += this.coefficients[i] * other.coefficients[j];
			}
		}

		return new Polynomial(result);
	}

	// Метод вывода для умножения
	public void printMultiplication(Polynomial other) {
		Polynomial result = this.multiply(other);
		System.out.println("УМНОЖЕНИЕ ПОЛИНОМОВ:");
		System.out.println("Полином 1: " + this);
		System.out.println("Полином 2: " + other);
		System.out.println("Результат: " + result);
		System.out.println("-----------------------------");
	}

	// Статический метод для суммы массива полиномов
	public static Polynomial sumArray(Polynomial[] polynomials) {
		if (polynomials == null || polynomials.length == 0) {
			return new Polynomial();
		}

		Polynomial result = polynomials[0];
		for (int i = 1; i < polynomials.length; i++) {
			result = result.add(polynomials[i]);
		}
		return result;
	}

	// Метод вывода для суммы массива полиномов
	public static void printArraySum(Polynomial[] polynomials) {
		Polynomial result = sumArray(polynomials);
		System.out.println("СУММА МАССИВА ПОЛИНОМОВ:");
		for (int i = 0; i < polynomials.length; i++) {
			System.out.printf("Полином %d: %s%n", i + 1, polynomials[i]);
		}
		System.out.println("Общая сумма: " + result);
		System.out.println("-----------------------------");
	}

	// Getters
	public double[] getCoefficients() {
		return coefficients.clone();
	}

	public int getDegree() {
		return degree;
	}

	public double getCoefficient(int index) {
		if (index < 0 || index > degree) {
			return 0;
		}
		return coefficients[index];
	}

	@Override
	public String toString() {
		if (degree == 0) {
			return String.valueOf(coefficients[0]);
		}

		StringBuilder sb = new StringBuilder();
		boolean firstTerm = true;

		for (int i = degree; i >= 0; i--) {
			double coef = coefficients[i];
			if (coef != 0) {
				if (!firstTerm) {
					sb.append(coef > 0 ? " + " : " - ");
				} else if (coef < 0) {
					sb.append("-");
				}

				double absCoef = Math.abs(coef);

				if (i == 0) {
					sb.append(absCoef);
				} else if (i == 1) {
					if (absCoef == 1) {
						sb.append("x");
					} else {
						sb.append(absCoef).append("x");
					}
				} else {
					if (absCoef == 1) {
						sb.append("x^").append(i);
					} else {
						sb.append(absCoef).append("x^").append(i);
					}
				}

				firstTerm = false;
			}
		}

		return sb.toString();
	}
}

class RationalPolynomial {
	private Polynomial numerator;
	private Polynomial denominator;

	// Конструкторы
	public RationalPolynomial(Polynomial numerator, Polynomial denominator) {
		if (denominator == null || Arrays.stream(denominator.getCoefficients()).allMatch(x -> x == 0)) {
			throw new IllegalArgumentException("Знаменатель не может быть нулевым полиномом");
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public RationalPolynomial(Polynomial numerator) {
		this(numerator, new Polynomial(new double[] { 1 }));
	}

	// Метод сложения рациональных полиномов
	public RationalPolynomial add(RationalPolynomial other) {
		// R = (p1(x)/Q1(x) + p2(x)/Q2(x)) = (p1*Q2 + p2*Q1) / (Q1*Q2)
		Polynomial p1 = this.numerator;
		Polynomial Q1 = this.denominator;
		Polynomial p2 = other.numerator;
		Polynomial Q2 = other.denominator;

		Polynomial newNumerator = p1.multiply(Q2).add(p2.multiply(Q1));
		Polynomial newDenominator = Q1.multiply(Q2);

		return new RationalPolynomial(newNumerator, newDenominator);
	}

	// Метод вывода для сложения рациональных полиномов
	public void printAddition(RationalPolynomial other) {
		RationalPolynomial result = this.add(other);
		System.out.println("СЛОЖЕНИЕ РАЦИОНАЛЬНЫХ ПОЛИНОМОВ:");
		System.out.println("Рациональный полином 1: " + this);
		System.out.println("Рациональный полином 2: " + other);
		System.out.println("Результат: " + result);
		System.out.println("-----------------------------");
	}

	// Getters
	public Polynomial getNumerator() {
		return numerator;
	}

	public Polynomial getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		if (denominator.equals(new Polynomial(new double[] { 1 }))) {
			return numerator.toString();
		}
		return "(" + numerator + ") / (" + denominator + ")";
	}
}

public class Main {
	public static void main(String[] args) {
		System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С ПОЛИНОМАМИ И РАЦИОНАЛЬНЫМИ ПОЛИНОМАМИ ===\n");

		// Демонстрация работы с полиномами
		System.out.println("ЧАСТЬ 1: ОПЕРАЦИИ С ПОЛИНОМАМИ");

		// Ввод двух полиномов
		System.out.println("Введите первый полином:");
		Polynomial p1 = Polynomial.inputPolynomial();

		System.out.println("Введите второй полином:");
		Polynomial p2 = Polynomial.inputPolynomial();

		// Сложение полиномов с выводом
		p1.printAddition(p2);

		// Умножение полиномов с выводом
		p1.printMultiplication(p2);

		// Работа с массивом полиномов
		int m = 2;

		Polynomial[] polyArray = new Polynomial[m];
		for (int i = 0; i < m; i++) {
			System.out.printf("Введите полином %d:%n", i + 1);
			polyArray[i] = Polynomial.inputPolynomial();
		}

		// Сумма массива полиномов с выводом
		Polynomial.printArraySum(polyArray);

		// Демонстрация работы с рациональными полиномами
		System.out.println("\nЧАСТЬ 2: ОПЕРАЦИИ С РАЦИОНАЛЬНЫМИ ПОЛИНОМАМИ");

		// Создание рациональных полиномов из введенных полиномов
		System.out.println("Создание рациональных полиномов из введенных данных:");

		// Для демонстрации создадим знаменатели
		Polynomial den1 = new Polynomial(new double[] { 1, 1 }); // x + 1
		Polynomial den2 = new Polynomial(new double[] { 1, 2 }); // 2x + 1

		RationalPolynomial rp1 = new RationalPolynomial(p1, den1);
		RationalPolynomial rp2 = new RationalPolynomial(p2, den2);

		System.out.println("Рациональный полином 1: " + rp1);
		System.out.println("Рациональный полином 2: " + rp2);

		rp1.printAddition(rp2);
		/*
		 * // Дополнительная демонстрация с заранее заданными полиномами
		 * System.out.println("\nЧАСТЬ 3: ДОПОЛНИТЕЛЬНАЯ ДЕМОНСТРАЦИЯ");
		 * 
		 * Polynomial demoP1 = new Polynomial(new double[]{1, 2, 3}); // 3x^2 + 2x + 1
		 * Polynomial demoP2 = new Polynomial(new double[]{4, 5}); // 5x + 4
		 * 
		 * // Демонстрация операций с полиномами
		 * demoP1.printAddition(demoP2);
		 * demoP1.printMultiplication(demoP2);
		 * 
		 * // Демонстрация с рациональными полиномами
		 * Polynomial demoNum1 = new Polynomial(new double[]{1, 1}); // x + 1
		 * Polynomial demoDen1 = new Polynomial(new double[]{1, 2}); // 2x + 1
		 * Polynomial demoNum2 = new Polynomial(new double[]{2, 1}); // x + 2
		 * Polynomial demoDen2 = new Polynomial(new double[]{1, 3}); // 3x + 1
		 * 
		 * RationalPolynomial demoRp1 = new RationalPolynomial(demoNum1, demoDen1);
		 * RationalPolynomial demoRp2 = new RationalPolynomial(demoNum2, demoDen2);
		 */

	}
}//
