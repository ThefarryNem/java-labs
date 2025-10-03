import java.util.Arrays;
import java.util.Objects;

// Класс Колесо
class Wheel {
	private String brand;
	private int diameter;
	private boolean isPunctured;

	public Wheel(String brand, int diameter) {
		this.brand = brand;
		this.diameter = diameter;
		this.isPunctured = false;
	}

	// Геттеры и сеттеры
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public boolean isPunctured() {
		return isPunctured;
	}

	public void setPunctured(boolean punctured) {
		isPunctured = punctured;
	}

	// Метод проколоть колесо
	public void puncture() {
		this.isPunctured = true;
	}

	// Метод починить колесо
	public void repair() {
		this.isPunctured = false;
	}

	// Переопределение equals
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Wheel wheel = (Wheel) o;
		return diameter == wheel.diameter &&
				isPunctured == wheel.isPunctured &&
				Objects.equals(brand, wheel.brand);
	}

	// Переопределение hashCode
	@Override
	public int hashCode() {
		return Objects.hash(brand, diameter, isPunctured);
	}

	// Переопределение toString
	@Override
	public String toString() {
		return "Wheel{" +
				"brand='" + brand + '\'' +
				", diameter=" + diameter +
				", isPunctured=" + isPunctured +
				'}';
	}
}

// Класс Автомобиль
class Car {
	private String brand;
	private int fuelLevel;
	private Wheel[] wheels;
	private boolean isMoving;

	// Конструктор
	public Car(String brand, Wheel[] wheels) {
		this.brand = brand;
		this.wheels = Arrays.copyOf(wheels, wheels.length);
		this.fuelLevel = 100; // полный бак при создании
		this.isMoving = false;
	}

	// Метод ехать
	public void drive() {
		if (fuelLevel <= 0) {
			System.out.println("Не могу ехать: нет топлива!");
			return;
		}

		// Проверяем все ли колеса в порядке
		for (Wheel wheel : wheels) {
			if (wheel.isPunctured()) {
				System.out.println("Не могу ехать: есть проколотое колесо!");
				return;
			}
		}

		if (isMoving) {
			System.out.println("Уже еду!");
		} else {
			isMoving = true;
			fuelLevel -= 10; // расход топлива
			System.out.println(brand + " поехал! Уровень топлива: " + fuelLevel + "%");
		}
	}

	// Метод заправляться
	public void refuel() {
		if (isMoving) {
			System.out.println("Не могу заправляться во время движения!");
			return;
		}

		fuelLevel = 100;
		System.out.println(brand + " заправлен. Уровень топлива: " + fuelLevel + "%");
	}

	// Метод менять колесо
	public void changeWheel(int wheelIndex, Wheel newWheel) {
		if (isMoving) {
			System.out.println("Не могу менять колесо во время движения!");
			return;
		}

		if (wheelIndex < 0 || wheelIndex >= wheels.length) {
			System.out.println("Неверный индекс колеса!");
			return;
		}

		wheels[wheelIndex] = newWheel;
		System.out.println("Колесо " + wheelIndex + " заменено на новое: " + newWheel);
	}

	// Метод вывести на консоль марку автомобиля
	public void printBrand() {
		System.out.println("Марка автомобиля: " + brand);
	}

	// Метод проверить состояние колес
	public void checkWheels() {
		System.out.println("Состояние колес автомобиля " + brand + ":");

		for (int i = 0; i < wheels.length; i++) {
			System.out.println("Колесо " + i + ": " + wheels[i]);
		}
	}

	// Метод остановиться
	public void stop() {
		if (isMoving) {
			isMoving = false;
			System.out.println(brand + " остановился.");
		} else {
			System.out.println("Уже стою!");
		}
	}

	// Геттеры
	public String getBrand() {
		return brand;
	}

	public int getFuelLevel() {
		return fuelLevel;
	}

	public Wheel[] getWheels() {
		return Arrays.copyOf(wheels, wheels.length);
	}

	public boolean isMoving() {
		return isMoving;
	}

	// Переопределение equals
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Car car = (Car) o;
		return fuelLevel == car.fuelLevel &&
				isMoving == car.isMoving &&
				Objects.equals(brand, car.brand) &&
				Arrays.equals(wheels, car.wheels);
	}

	// Переопределение hashCode
	@Override
	public int hashCode() {
		int result = Objects.hash(brand, fuelLevel, isMoving);
		result = 31 * result + Arrays.hashCode(wheels);
		return result;
	}

	// Переопределение toString
	@Override
	public String toString() {
		return "Car{" +
				"brand='" + brand + '\'' +
				", fuelLevel=" + fuelLevel +
				", wheels=" + Arrays.toString(wheels) +
				", isMoving=" + isMoving +
				'}';
	}
}

// Демонстрационный класс
public class CarDemo {
	public static void main(String[] args) {
		// Создаем колеса
		Wheel[] wheels = {
				new Wheel("Michelin", 17),
				new Wheel("Michelin", 17),
				new Wheel("Michelin", 17),
				new Wheel("Michelin", 17)
		};

		// Создаем автомобиль
		Car car = new Car("Toyota Camry", wheels);

		// Демонстрация работы методов
		System.out.println("=== Демонстрация работы автомобиля ===");

		// Выводим марку автомобиля
		car.printBrand();
		System.out.println();

		// Проверяем состояние колес
		car.checkWheels();
		System.out.println();

		// Пытаемся ехать
		car.drive();
		System.out.println();

		// Останавливаемся
		car.stop();
		System.out.println();

		// Проколем одно колесо
		wheels[0].puncture();
		System.out.println("Прокололи колесо 0!");
		car.checkWheels();
		System.out.println();

		// Пытаемся ехать с проколотым колесом
		car.drive();
		System.out.println();

		// Меняем проколотое колесо
		car.changeWheel(0, new Wheel("Bridgestone", 17));
		System.out.println();

		// Снова проверяем состояние колес
		car.checkWheels();
		System.out.println();

		// Едем несколько раз (расходуем топливо)
		car.drive();
		car.stop();
		car.drive();
		car.stop();
		car.drive();
		car.stop();
		System.out.println();

		// Заправляемся
		car.refuel();
		System.out.println();

		// Демонстрация equals, hashCode и toString
		System.out.println("=== Демонстрация методов Object ===");

		// Создаем второй идентичный автомобиль
		Wheel[] wheels2 = {
				new Wheel("Bridgestone", 17),
				new Wheel("Michelin", 17),
				new Wheel("Michelin", 17),
				new Wheel("Michelin", 17)
		};
		Car car2 = new Car("Toyota Camry", wheels2);

		System.out.println("car.toString(): " + car);
		System.out.println("car2.toString(): " + car2);
		System.out.println("car.equals(car2): " + car.equals(car2));
		System.out.println("car.hashCode(): " + car.hashCode());
		System.out.println("car2.hashCode(): " + car2.hashCode());

		// Демонстрация для Wheel
		Wheel wheel1 = new Wheel("Michelin", 17);
		Wheel wheel2 = new Wheel("Michelin", 17);
		Wheel wheel3 = new Wheel("Bridgestone", 16);

		System.out.println("\nДемонстрация для Wheel:");
		System.out.println("wheel1: " + wheel1);
		System.out.println("wheel2: " + wheel2);
		System.out.println("wheel3: " + wheel3);
		System.out.println("wheel1.equals(wheel2): " + wheel1.equals(wheel2));
		System.out.println("wheel1.equals(wheel3): " + wheel1.equals(wheel3));
		System.out.println("wheel1.hashCode(): " + wheel1.hashCode());
		System.out.println("wheel2.hashCode(): " + wheel2.hashCode());
		System.out.println("wheel3.hashCode(): " + wheel3.hashCode());
	}
}
