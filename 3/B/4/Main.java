import java.util.*;

// Класс Abiturient
class Abiturient {
	private int id;
	private String lastName;
	private String firstName;
	private String middleName;
	private String address;
	private String phone;
	private int[] grades;

	// Конструкторы
	public Abiturient() {
		this.grades = new int[0];
	}

	public Abiturient(int id, String lastName, String firstName, String middleName,
			String address, String phone, int[] grades) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.address = address;
		this.phone = phone;
		this.grades = grades != null ? grades.clone() : new int[0];
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public int[] getGrades() {
		return grades.clone();
	}

	// Сеттеры
	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setGrades(int[] grades) {
		this.grades = grades != null ? grades.clone() : new int[0];
	}

	// Основные методы
	public double getAverageGrade() {
		if (grades.length == 0)
			return 0.0;
		int sum = 0;
		for (int grade : grades)
			sum += grade;
		return (double) sum / grades.length;
	}

	public boolean hasUnsatisfactoryGrades() {
		for (int grade : grades) {
			if (grade < 3)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("ID: %d, ФИО: %s %s %s, Средний балл: %.2f, Оценки: %s",
				id, lastName, firstName, middleName, getAverageGrade(), Arrays.toString(grades));
	}
}

// Класс для работы с абитуриентами
class AbiturientManager {
	private Abiturient[] abiturients;

	public AbiturientManager(Abiturient[] abiturients) {
		this.abiturients = abiturients != null ? abiturients.clone() : new Abiturient[0];
	}

	// a) Список абитуриентов с неудовлетворительными оценками
	public void printWithUnsatisfactoryGrades() {
		System.out.println("Абитуриенты с неудовлетворительными оценками:");
		boolean found = false;
		for (Abiturient a : abiturients) {
			if (a.hasUnsatisfactoryGrades()) {
				System.out.println(a);
				found = true;
			}
		}
		if (!found)
			System.out.println("Не найдено");
		System.out.println();
	}

	// b) Список абитуриентов со средним баллом выше заданного
	public void printWithAverageAbove(double minAverage) {
		System.out.printf("Абитуриенты со средним баллом выше %.2f:\n", minAverage);
		boolean found = false;
		for (Abiturient a : abiturients) {
			if (a.getAverageGrade() > minAverage) {
				System.out.println(a);
				found = true;
			}
		}
		if (!found)
			System.out.println("Не найдено");
		System.out.println();
	}

	// c) Топ n абитуриентов
	public void printTopAbiturients(int n) {
		System.out.printf("Топ-%d абитуриентов:\n", n);

		// Сортируем по убыванию среднего балла
		Abiturient[] sorted = abiturients.clone();
		Arrays.sort(sorted, (a1, a2) -> Double.compare(a2.getAverageGrade(), a1.getAverageGrade()));

		for (int i = 0; i < Math.min(n, sorted.length); i++) {
			System.out.printf("%d место: %s\n", i + 1, sorted[i]);
		}
		System.out.println();
	}

	// Вывод всех абитуриентов
	public void printAll() {
		System.out.println("Все абитуриенты:");
		for (Abiturient a : abiturients) {
			System.out.println(a);
		}
		System.out.println();
	}
}

// Главный класс
public class Main {
	public static void main(String[] args) {
		// Создаем тестовых абитуриентов
		Abiturient[] abiturients = {
				new Abiturient(1, "Иванов", "Иван", "Иванович", "ул. Ленина, 10", "+7-911-111-11-11",
						new int[] { 5, 4, 3, 5, 4 }),
				new Abiturient(2, "Петров", "Петр", "Петрович", "ул. Пушкина, 25", "+7-911-222-22-22",
						new int[] { 2, 3, 2, 4, 3 }),
				new Abiturient(3, "Сидорова", "Анна", "Сергеевна", "ул. Гагарина, 15", "+7-911-333-33-33",
						new int[] { 5, 5, 5, 5, 5 }),
				new Abiturient(4, "Кузнецов", "Алексей", "Владимирович", "ул. Мира, 8", "+7-911-444-44-44",
						new int[] { 4, 4, 4, 3, 4 }),
				new Abiturient(5, "Смирнова", "Ольга", "Дмитриевна", "ул. Садовая, 30", "+7-911-555-55-55",
						new int[] { 3, 2, 4, 3, 2 })
		};

		AbiturientManager manager = new AbiturientManager(abiturients);

		// Демонстрация всех функций
		manager.printAll();
		manager.printWithUnsatisfactoryGrades();
		manager.printWithAverageAbove(4.0);
		manager.printTopAbiturients(3);
	}
}
