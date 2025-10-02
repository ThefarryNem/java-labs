import java.util.Scanner;

public class Fraction {
	private int m; // числитель
	private int n; // знаменатель

	// Конструкторы
	public Fraction() {
		this.m = 0;
		this.n = 1;
	}

	public Fraction(int m, int n) {
		if (n == 0) {
			throw new IllegalArgumentException("Знаменатель не может быть равен нулю!");
		}
		this.m = m;
		this.n = n;
		simplify();
	}

	public Fraction(int wholeNumber) {
		this.m = wholeNumber;
		this.n = 1;
	}

	public Fraction(Fraction other) {
		this.m = other.m;
		this.n = other.n;
	}

	// Методы арифметических операций
	public Fraction add(Fraction other) {
		int newM = this.m * other.n + other.m * this.n;
		int newN = this.n * other.n;
		return new Fraction(newM, newN);
	}

	public Fraction subtract(Fraction other) {
		int newM = this.m * other.n - other.m * this.n;
		int newN = this.n * other.n;
		return new Fraction(newM, newN);
	}

	public Fraction multiply(Fraction other) {
		int newM = this.m * other.m;
		int newN = this.n * other.n;
		return new Fraction(newM, newN);
	}

	public Fraction divide(Fraction other) {
		if (other.m == 0) {
			throw new ArithmeticException("Деление на нулевую дробь!");
		}
		int newM = this.m * other.n;
		int newN = this.n * other.m;
		return new Fraction(newM, newN);
	}

	// Метод для упрощения дроби
	private void simplify() {
		if (n < 0) {
			m = -m;
			n = -n;
		}

		int gcd = gcd(Math.abs(m), Math.abs(n));
		m /= gcd;
		n /= gcd;
	}

	// Нахождение НОД (алгоритм Евклида)
	private int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	// Геттеры и сеттеры
	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
		simplify();
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		if (n == 0) {
			throw new IllegalArgumentException("Знаменатель не может быть равен нулю!");
		}
		this.n = n;
		simplify();
	}

	// Методы ввода/вывода
	public void input(Scanner scanner) {
		System.out.print("Введите числитель: ");
		m = scanner.nextInt();

		do {
			System.out.print("Введите знаменатель (не 0): ");
			n = scanner.nextInt();
			if (n == 0) {
				System.out.println("Знаменатель не может быть нулем! Повторите ввод.");
			}
		} while (n == 0);

		simplify();
	}

	public void display() {
		if (n == 1) {
			System.out.print(m);
		} else {
			System.out.print(m + "/" + n);
		}
	}

	// Преобразование в строку
	@Override
	public String toString() {
		if (n == 1) {
			return String.valueOf(m);
		} else {
			return m + "/" + n;
		}
	}

	// Преобразование в десятичное число
	public double toDouble() {
		return (double) m / n;
	}

	// Метод для изменения дроби путем добавления следующей дроби (для четных
	// индексов)
	public void addNextFraction(Fraction next) {
		Fraction result = this.add(next);
		this.m = result.m;
		this.n = result.n;
	}

	// Статические методы для работы с массивом дробей
	public static Fraction[] createFractionArray(int k, Scanner scanner) {
		Fraction[] fractions = new Fraction[k];
		for (int i = 0; i < k; i++) {
			System.out.println("Ввод дроби " + (i + 1) + ":");
			fractions[i] = new Fraction();
			fractions[i].input(scanner);
		}
		return fractions;
	}

	public static void displayFractionArray(Fraction[] fractions) {
		System.out.println("Массив дробей:");
		for (int i = 0; i < fractions.length; i++) {
			System.out.print("Дробь " + (i + 1) + ": ");
			fractions[i].display();
			System.out.println(" (" + fractions[i].toDouble() + ")");
		}
	}

	// Метод для изменения элементов с четными индексами
	public static void modifyEvenIndexElements(Fraction[] fractions) {
		for (int i = 0; i < fractions.length - 1; i += 2) {
			System.out.print("Изменяем дробь с индексом " + i + ": ");
			fractions[i].display();
			System.out.print(" + ");
			fractions[i + 1].display();
			System.out.print(" = ");

			fractions[i].addNextFraction(fractions[i + 1]);

			fractions[i].display();
			System.out.println();
		}
	}

	// Демонстрационная программа
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Демонстрация конструкторов
		System.out.println("=== ДЕМОНСТРАЦИЯ КОНСТРУКТОРОВ ===");
		Fraction f1 = new Fraction(); // конструктор по умолчанию
		Fraction f2 = new Fraction(3, 4); // конструктор с параметрами
		Fraction f3 = new Fraction(5); // конструктор с целым числом
		Fraction f4 = new Fraction(f2); // конструктор копирования

		System.out.print("f1 (по умолчанию): ");
		f1.display();
		System.out.println();
		System.out.print("f2 (3/4): ");
		f2.display();
		System.out.println();
		System.out.print("f3 (5): ");
		f3.display();
		System.out.println();
		System.out.print("f4 (копия f2): ");
		f4.display();
		System.out.println();

		// Демонстрация арифметических операций
		System.out.println("\n=== АРИФМЕТИЧЕСКИЕ ОПЕРАЦИИ ===");
		Fraction a = new Fraction(1, 2);
		Fraction b = new Fraction(1, 3);

		System.out.print("a = ");
		a.display();
		System.out.println();
		System.out.print("b = ");
		b.display();
		System.out.println();

		Fraction sum = a.add(b);
		System.out.print("a + b = ");
		sum.display();
		System.out.println();

		Fraction diff = a.subtract(b);
		System.out.print("a - b = ");
		diff.display();
		System.out.println();

		Fraction product = a.multiply(b);
		System.out.print("a * b = ");
		product.display();
		System.out.println();

		Fraction quotient = a.divide(b);
		System.out.print("a / b = ");
		quotient.display();
		System.out.println();

		// Работа с массивом дробей
		System.out.println("\n=== РАБОТА С МАССИВОМ ДРОБЕЙ ===");
		System.out.print("Введите количество дробей k: ");
		int k = scanner.nextInt();

		Fraction[] fractions = createFractionArray(k, scanner);

		System.out.println("\nИсходный массив дробей:");
		displayFractionArray(fractions);

		// Изменение элементов с четными индексами
		System.out.println("\n=== ИЗМЕНЕНИЕ ЭЛЕМЕНТОВ С ЧЕТНЫМИ ИНДЕКСАМИ ===");
		modifyEvenIndexElements(fractions);

		System.out.println("\nМассив после изменений:");
		displayFractionArray(fractions);

		// Дополнительные примеры операций
		System.out.println("\n=== ДОПОЛНИТЕЛЬНЫЕ ПРИМЕРЫ ===");
		demonstrateAdditionalOperations();

		scanner.close();
	}

	// Демонстрация дополнительных операций
	public static void demonstrateAdditionalOperations() {
		System.out.println("Примеры упрощения дробей:");

		Fraction f1 = new Fraction(4, 8);
		System.out.print("4/8 = ");
		f1.display();
		System.out.println();

		Fraction f2 = new Fraction(15, 25);
		System.out.print("15/25 = ");
		f2.display();
		System.out.println();

		Fraction f3 = new Fraction(-6, 9);
		System.out.print("-6/9 = ");
		f3.display();
		System.out.println();

		Fraction f4 = new Fraction(3, -4);
		System.out.print("3/-4 = ");
		f4.display();
		System.out.println();

		// Цепочка операций
		System.out.println("\nЦепочка операций:");
		Fraction result = new Fraction(1, 2)
				.add(new Fraction(1, 3))
				.multiply(new Fraction(3, 4))
				.subtract(new Fraction(1, 8));

		System.out.print("(1/2 + 1/3) * 3/4 - 1/8 = ");
		result.display();
		System.out.println(" = " + result.toDouble());
	}
}
