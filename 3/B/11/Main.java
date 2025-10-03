import java.util.ArrayList;
import java.util.List;

// Класс Bus
class Bus {
	private String driverName; // Фамилия и инициалы водителя
	private String busNumber; // Номер автобуса
	private int routeNumber; // Номер маршрута
	private String brand; // Марка
	private int startYear; // Год начала эксплуатации
	private double mileage; // Пробег

	// Конструктор по умолчанию
	public Bus() {
	}

	// Конструктор с параметрами
	public Bus(String driverName, String busNumber, int routeNumber,
			String brand, int startYear, double mileage) {
		this.driverName = driverName;
		this.busNumber = busNumber;
		this.routeNumber = routeNumber;
		this.brand = brand;
		this.startYear = startYear;
		this.mileage = mileage;
	}

	// Методы set/get для driverName
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverName() {
		return driverName;
	}

	// Методы set/get для busNumber
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public String getBusNumber() {
		return busNumber;
	}

	// Методы set/get для routeNumber
	public void setRouteNumber(int routeNumber) {
		this.routeNumber = routeNumber;
	}

	public int getRouteNumber() {
		return routeNumber;
	}

	// Методы set/get для brand
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	// Методы set/get для startYear
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getStartYear() {
		return startYear;
	}

	// Методы set/get для mileage
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public double getMileage() {
		return mileage;
	}

	// Метод toString
	@Override
	public String toString() {
		return "Bus{" +
				"Водитель='" + driverName + '\'' +
				", Номер='" + busNumber + '\'' +
				", Маршрут=" + routeNumber +
				", Марка='" + brand + '\'' +
				", Год начала=" + startYear +
				", Пробег=" + mileage + " км" +
				'}';
	}
}

// Класс для работы с массивом автобусов
class BusManager {
	private List<Bus> buses;

	// Конструктор
	public BusManager() {
		this.buses = new ArrayList<>();
	}

	// Добавление автобуса
	public void addBus(Bus bus) {
		buses.add(bus);
	}

	// Получение всех автобусов
	public List<Bus> getAllBuses() {
		return new ArrayList<>(buses);
	}

	// a) Список автобусов для заданного номера маршрута
	public List<Bus> getBusesByRoute(int routeNumber) {
		List<Bus> result = new ArrayList<>();
		for (Bus bus : buses) {
			if (bus.getRouteNumber() == routeNumber) {
				result.add(bus);
			}
		}
		return result;
	}

	// b) Список автобусов, которые эксплуатируются больше заданного количества лет
	public List<Bus> getBusesOlderThan(int years, int currentYear) {
		List<Bus> result = new ArrayList<>();
		for (Bus bus : buses) {
			if ((currentYear - bus.getStartYear()) > years) {
				result.add(bus);
			}
		}
		return result;
	}

	// c) Список автобусов, пробег у которых больше заданного значения
	public List<Bus> getBusesWithMileageOver(double minMileage) {
		List<Bus> result = new ArrayList<>();
		for (Bus bus : buses) {
			if (bus.getMileage() > minMileage) {
				result.add(bus);
			}
		}
		return result;
	}

	// Вывод списка автобусов на консоль
	public void printBuses(List<Bus> busList, String title) {
		System.out.println("\n" + title + ":");
		if (busList.isEmpty()) {
			System.out.println("Автобусы не найдены");
		} else {
			for (Bus bus : busList) {
				System.out.println(bus);
			}
		}
	}
}

// Главный класс для демонстрации
public class Main {
	public static void main(String[] args) {
		// Создаем менеджер автобусов
		BusManager busManager = new BusManager();

		// Добавляем автобусы в массив
		busManager.addBus(new Bus("Иванов И.И.", "А123БВ", 15, "ПАЗ", 2015, 85000));
		busManager.addBus(new Bus("Петров П.П.", "Б456ГД", 10, "ЛиАЗ", 2010, 150000));
		busManager.addBus(new Bus("Сидоров С.С.", "В789ЕЖ", 15, "МАЗ", 2018, 45000));
		busManager.addBus(new Bus("Кузнецов К.К.", "Г012ИК", 20, "ПАЗ", 2008, 220000));
		busManager.addBus(new Bus("Смирнов С.С.", "Д345ЛМ", 10, "ЛиАЗ", 2012, 120000));
		busManager.addBus(new Bus("Васильев В.В.", "Е678НО", 15, "МАЗ", 2020, 30000));

		// Выводим все автобусы
		busManager.printBuses(busManager.getAllBuses(), "ВСЕ АВТОБУСЫ");

		// a) Список автобусов для заданного номера маршрута (маршрут 15)
		int targetRoute = 15;
		busManager.printBuses(busManager.getBusesByRoute(targetRoute),
				"АВТОБУСЫ МАРШРУТА №" + targetRoute);

		// b) Список автобусов, которые эксплуатируются больше 10 лет (текущий год 2024)
		int currentYear = 2024;
		int minYears = 10;
		busManager.printBuses(busManager.getBusesOlderThan(minYears, currentYear),
				"АВТОБУСЫ С ЭКСПЛУАТАЦИЕЙ БОЛЕЕ " + minYears + " ЛЕТ");

		// c) Список автобусов, пробег у которых больше 100000 км
		double minMileage = 100000;
		busManager.printBuses(busManager.getBusesWithMileageOver(minMileage),
				"АВТОБУСЫ С ПРОБЕГОМ БОЛЕЕ " + minMileage + " КМ");
	}
}
