import java.time.Year;
import java.util.ArrayList;
import java.util.List;

class Car {
	private int id;
	private String brand;
	private String model;
	private int year;
	private String color;
	private double price;
	private String registrationNumber;

	public Car(int id, String brand, String model, int year, String color, double price, String registrationNumber) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		this.registrationNumber = registrationNumber;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String getColor() {
		return color;
	}

	public double getPrice() {
		return price;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	// Метод для вычисления возраста автомобиля
	public int getAge() {
		return Year.now().getValue() - year;
	}

	@Override
	public String toString() {
		return String.format(
				"Автомобиль {id = %d, бренд = '%s', модель = '%s', год = %d, цвет = '%s', цена = %.2f, рег. номер = '%s'}",
				id, brand, model, year, color, price, registrationNumber);
	}
}

public class CarDemo {
	public static void main(String[] args) {
		// Создаем массив объектов Car
		Car[] cars = {
				new Car(1, "Toyota", "Camry", 2018, "Black", 25000.0, "AB1234CD"),
				new Car(2, "Toyota", "Corolla", 2020, "White", 20000.0, "EF5678GH"),
				new Car(3, "Honda", "Civic", 2015, "Red", 15000.0, "IJ9012KL"),
				new Car(4, "Honda", "Accord", 2022, "Blue", 30000.0, "MN3456OP"),
				new Car(5, "Toyota", "Camry", 2017, "Silver", 22000.0, "QR7890ST"),
				new Car(6, "BMW", "X5", 2019, "Black", 45000.0, "UV1234WX"),
				new Car(7, "BMW", "X3", 2020, "White", 35000.0, "YZ5678AB"),
				new Car(8, "Toyota", "RAV4", 2021, "Green", 28000.0, "CD9012EF")
		};

		System.out.println("Все автомобили:");
		for (Car car : cars) {
			System.out.println(car);
		}

		// a) Список автомобилей заданной марки
		System.out.println("\n--- a) Автомобили марки Toyota ---");
		List<Car> toyotaCars = getCarsByBrand(cars, "Toyota");
		for (Car car : toyotaCars) {
			System.out.println(car);
		}

		// b) Список автомобилей заданной модели, которые эксплуатируются больше n лет
		System.out.println("\n--- b) Автомобили модели Camry старше 5 лет ---");
		List<Car> oldCamryCars = getCarsByModelAndAge(cars, "Camry", 5);
		for (Car car : oldCamryCars) {
			System.out.println(car + " (возраст: " + car.getAge() + " лет)");
		}

		// c) Список автомобилей заданного года выпуска, цена которых больше указанной
		System.out.println("\n--- c) Автомобили 2020 года дороже 25000 ---");
		List<Car> expensive2020Cars = getCarsByYearAndMinPrice(cars, 2020, 25000.0);
		for (Car car : expensive2020Cars) {
			System.out.println(car);
		}

		// Дополнительные примеры
		System.out.println("\n--- Дополнительно: Honda старше 3 лет ---");
		List<Car> oldHondaCars = getCarsByBrandAndAge(cars, "Honda", 3);
		for (Car car : oldHondaCars) {
			System.out.println(car + " (возраст: " + car.getAge() + " лет)");
		}
	}

	// a) Метод для получения автомобилей заданной марки
	public static List<Car> getCarsByBrand(Car[] cars, String brand) {
		List<Car> result = new ArrayList<>();
		for (Car car : cars) {
			if (car.getBrand().equalsIgnoreCase(brand)) {
				result.add(car);
			}
		}
		return result;
	}

	// b) Метод для получения автомобилей заданной модели старше n лет
	public static List<Car> getCarsByModelAndAge(Car[] cars, String model, int minAge) {
		List<Car> result = new ArrayList<>();
		for (Car car : cars) {
			if (car.getModel().equalsIgnoreCase(model) && car.getAge() > minAge) {
				result.add(car);
			}
		}
		return result;
	}

	// c) Метод для получения автомобилей заданного года с ценой выше указанной
	public static List<Car> getCarsByYearAndMinPrice(Car[] cars, int year, double minPrice) {
		List<Car> result = new ArrayList<>();
		for (Car car : cars) {
			if (car.getYear() == year && car.getPrice() > minPrice) {
				result.add(car);
			}
		}
		return result;
	}

	// Дополнительный метод: автомобили заданной марки старше n лет
	public static List<Car> getCarsByBrandAndAge(Car[] cars, String brand, int minAge) {
		List<Car> result = new ArrayList<>();
		for (Car car : cars) {
			if (car.getBrand().equalsIgnoreCase(brand) && car.getAge() > minAge) {
				result.add(car);
			}
		}
		return result;
	}
}
