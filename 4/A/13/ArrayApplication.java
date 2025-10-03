import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс Массив - базовый класс для работы с массивами
 * Принадлежность методов классу:
 * - Конструкторы: создание объекта
 * - Геттеры/сеттеры: доступ к данным
 * - equals/hashCode/toString: стандартные методы Object
 * - Арифметические операции: функциональность массива
 */
class Array {
	protected double[] elements;

	// Конструкторы
	public Array() {
		this.elements = new double[0];
	}

	public Array(double[] elements) {
		this.elements = elements != null ? elements.clone() : new double[0];
	}

	public Array(int size) {
		this.elements = new double[size];
	}

	// Геттеры и сеттеры
	public double[] getElements() {
		return elements.clone();
	}

	public void setElements(double[] elements) {
		this.elements = elements != null ? elements.clone() : new double[0];
	}

	public int getSize() {
		return elements.length;
	}

	// Арифметические операции
	public Array add(Array other) {
		if (this.elements.length != other.elements.length) {
			throw new IllegalArgumentException("Массивы должны быть одинаковой длины для сложения");
		}

		double[] result = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			result[i] = this.elements[i] + other.elements[i];
		}
		return new Array(result);
	}

	public Array subtract(Array other) {
		if (this.elements.length != other.elements.length) {
			throw new IllegalArgumentException("Массивы должны быть одинаковой длины для вычитания");
		}

		double[] result = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			result[i] = this.elements[i] - other.elements[i];
		}
		return new Array(result);
	}

	public Array multiply(Array other) {
		if (this.elements.length != other.elements.length) {
			throw new IllegalArgumentException("Массивы должны быть одинаковой длины для умножения");
		}

		double[] result = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			result[i] = this.elements[i] * other.elements[i];
		}
		return new Array(result);
	}

	// Переопределение методов Object
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Array array = (Array) o;
		return Arrays.equals(elements, array.elements);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements);
	}

	@Override
	public String toString() {
		return "Array{" +
				"elements=" + Arrays.toString(elements) +
				'}';
	}
}

/**
 * Класс Одномерный массив - наследует функциональность класса Массив
 * Принадлежность методов классу:
 * - Конструкторы: специализированное создание одномерного массива
 * - createFromUserInput: создание с пользовательским вводом
 * - display: вывод на консоль (специфичный для одномерного массива)
 * - Наследует операции: функциональность родительского класса
 */
class OneDimensionalArray extends Array {

	// Конструкторы
	public OneDimensionalArray() {
		super();
	}

	public OneDimensionalArray(double[] elements) {
		super(elements);
	}

	public OneDimensionalArray(int size) {
		super(size);
	}

	// Метод создания массива
	public void create(double[] values) {
		this.elements = values != null ? values.clone() : new double[0];
	}

	// Метод вывода на консоль
	public void display() {
		System.out.println("Одномерный массив:");
		System.out.print("[");
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i]);
			if (i < elements.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

	// Метод для создания массива с пользовательским вводом (упрощенная версия)
	public void createFromUserInput(double[] inputValues) {
		if (inputValues != null && inputValues.length > 0) {
			this.elements = inputValues.clone();
		} else {
			this.elements = new double[0];
		}
	}

	// Переопределение методов Object с учетом наследования
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		OneDimensionalArray that = (OneDimensionalArray) o;
		return Arrays.equals(elements, that.elements);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), Arrays.hashCode(elements));
	}

	@Override
	public String toString() {
		return "OneDimensionalArray{" +
				"elements=" + Arrays.toString(elements) +
				'}';
	}
}

/**
 * Демонстрационное приложение для работы с одномерными массивами
 */

public class ArrayApplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== Демонстрация работы с одномерными массивами ===");

		// Создание первого массива
		System.out.println("\nСоздание первого массива:");
		OneDimensionalArray array1 = new OneDimensionalArray();
		double[] values1 = { 1.5, 2.3, 3.7, 4.1 };
		array1.create(values1);
		array1.display();

		// Создание второго массива
		System.out.println("\nСоздание второго массива:");
		OneDimensionalArray array2 = new OneDimensionalArray();
		double[] values2 = { 0.5, 1.2, 2.0, 3.9 };
		array2.create(values2);
		array2.display();

		// Выполнение операций
		System.out.println("\n--- Выполнение операций ---");

		// Сложение
		try {
			Array sumResult = array1.add(array2);
			System.out.println("Результат сложения:");
			System.out.println(sumResult);
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка при сложении: " + e.getMessage());
		}

		// Вычитание
		try {
			Array subtractResult = array1.subtract(array2);
			System.out.println("Результат вычитания:");
			System.out.println(subtractResult);
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка при вычитании: " + e.getMessage());
		}

		// Умножение
		try {
			Array multiplyResult = array1.multiply(array2);
			System.out.println("Результат умножения:");
			System.out.println(multiplyResult);
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка при умножении: " + e.getMessage());
		}

		// Демонстрация equals, hashCode, toString
		System.out.println("\n--- Демонстрация методов Object ---");

		OneDimensionalArray array3 = new OneDimensionalArray(values1.clone());

		System.out.println("array1: " + array1);
		System.out.println("array3: " + array3);
		System.out.println("array1.equals(array3): " + array1.equals(array3));
		System.out.println("array1.hashCode(): " + array1.hashCode());
		System.out.println("array3.hashCode(): " + array3.hashCode());

		// Создание массива разной длины для демонстрации ошибок
		System.out.println("\n--- Демонстрация обработки ошибок ---");
		OneDimensionalArray array4 = new OneDimensionalArray(new double[] { 1, 2 });

		try {
			array1.add(array4);
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}

		// Демонстрация работы с пользовательским вводом
		System.out.println("\n--- Демонстрация пользовательского ввода ---");
		System.out.print("Введите размер массива: ");

		int size = scanner.nextInt();

		OneDimensionalArray userArray = new OneDimensionalArray(size);
		double[] userValues = new double[size];

		System.out.println("Введите " + size + " элементов массива:");
		for (int i = 0; i < size; i++) {
			System.out.print("Элемент " + (i + 1) + ": ");
			userValues[i] = scanner.nextDouble();
		}

		userArray.createFromUserInput(userValues);
		userArray.display();

		scanner.close();
	}
}
