import java.util.ArrayList;
import java.util.List;

// Базовый класс Животное
class Animal {
	protected String name;
	protected int age;

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Animal() {
		this("Неизвестно", 0);
	}

	// Метод для вывода имени
	public void displayName() {
		System.out.println("Имя: " + name);
	}

	// Метод для подачи голоса (будет переопределен в потомках)
	public void makeSound() {
		System.out.println("Животное издает звук");
	}

	// Геттеры и сеттеры
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

// Класс Кошка, наследуется от Животного
class Cat extends Animal {
	protected String color;
	protected boolean isPregnant;

	public Cat(String name, int age, String color) {
		super(name, age);
		this.color = color;
		this.isPregnant = false;
	}

	public Cat(String name, int age) {
		this(name, age, "неизвестный");
	}

	// Переопределяем метод подачи голоса
	@Override
	public void makeSound() {
		System.out.println(name + " говорит: Мяу-мяу!");
	}

	// Метод для рожания потомства
	public List<Kitten> giveBirth(String... kittenNames) {
		if (!isPregnant) {
			System.out.println(name + " не беременна, не может рожать котят");
			return new ArrayList<>();
		}

		List<Kitten> kittens = new ArrayList<>();
		System.out.println(name + " рожает котят:");

		for (String kittenName : kittenNames) {
			Kitten kitten = new Kitten(kittenName, 0, this.color, this);
			kittens.add(kitten);
			System.out.println("Родился котенок: " + kittenName);
		}

		this.isPregnant = false;
		return kittens;
	}

	// Метод для обозначения беременности
	public void setPregnant(boolean pregnant) {
		this.isPregnant = pregnant;
		if (pregnant) {
			System.out.println(name + " теперь беременна");
		}
	}

	// Геттеры и сеттеры
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isPregnant() {
		return isPregnant;
	}
}

// Класс Котёнок, наследуется от Кошка
class Kitten extends Cat {
	private Cat mother;

	public Kitten(String name, int age, String color, Cat mother) {
		super(name, age, color);
		this.mother = mother;
	}

	public Kitten(String name, Cat mother) {
		this(name, 0, mother.getColor(), mother);
	}

	// Переопределяем метод подачи голоса для котенка
	@Override
	public void makeSound() {
		System.out.println(name + " говорит: Пи-пи-пи! Мяу!");
	}

	// Метод для получения информации о матери
	public void displayMotherInfo() {
		if (mother != null) {
			System.out.println("Мама котенка " + name + ": " + mother.getName());
		} else {
			System.out.println("Информация о матери отсутствует");
		}
	}

	// Геттер для матери
	public Cat getMother() {
		return mother;
	}

	// Переопределяем метод вывода информации
	@Override
	public void displayName() {
		System.out.println("Котенок: " + name + " (возраст: " + age + " месяцев)");
	}
}

// Демонстрационный класс
public class CatFamilyDemo {
	public static void main(String[] args) {
		System.out.println("=== Создание кошки ===");
		Cat motherCat = new Cat("Мурка", 3, "рыжий");
		motherCat.displayName();
		motherCat.makeSound();

		System.out.println("\n=== Кошка беременеет ===");
		motherCat.setPregnant(true);

		System.out.println("\n=== Рождение котят ===");
		List<Kitten> kittens = motherCat.giveBirth("Барсик", "Мурзик", "Снежок");

		System.out.println("\n=== Информация о котятах ===");
		for (Kitten kitten : kittens) {
			kitten.displayName();
			kitten.makeSound();
			kitten.displayMotherInfo();
			System.out.println("---");
		}

		System.out.println("\n=== Создание отдельного котенка ===");
		Kitten singleKitten = new Kitten("Пушистик", 2, "серый", motherCat);
		singleKitten.displayName();
		singleKitten.makeSound();

		System.out.println("\n=== Демонстрация полиморфизма ===");
		Animal[] animals = {
				new Cat("Васька", 2, "черный"),
				new Kitten("Комочек", 1, "белый", motherCat),
				new Animal("Неизвестное животное", 1)
		};

		for (Animal animal : animals) {
			animal.displayName();
			animal.makeSound();
			System.out.println("---");
		}

		System.out.println("\n=== Котята растут ===");
		for (Kitten kitten : kittens) {
			kitten.setAge(3); // Котятам исполнилось 3 месяца
			kitten.displayName();
			kitten.makeSound();
		}
	}
}
