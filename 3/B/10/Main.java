import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Train {
	private String destination;
	private String trainNumber;
	private String departureTime;
	private int commonSeats;
	private int compartmentSeats;
	private int reservedSeats;
	private int luxurySeats;

	// Конструктор по умолчанию
	public Train() {
	}

	// Конструктор с параметрами
	public Train(String destination, String trainNumber, String departureTime,
			int commonSeats, int compartmentSeats, int reservedSeats, int luxurySeats) {
		this.destination = destination;
		this.trainNumber = trainNumber;
		this.departureTime = departureTime;
		this.commonSeats = commonSeats;
		this.compartmentSeats = compartmentSeats;
		this.reservedSeats = reservedSeats;
		this.luxurySeats = luxurySeats;
	}

	// Getter и Setter методы
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public int getCommonSeats() {
		return commonSeats;
	}

	public void setCommonSeats(int commonSeats) {
		this.commonSeats = commonSeats;
	}

	public int getCompartmentSeats() {
		return compartmentSeats;
	}

	public void setCompartmentSeats(int compartmentSeats) {
		this.compartmentSeats = compartmentSeats;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public int getLuxurySeats() {
		return luxurySeats;
	}

	public void setLuxurySeats(int luxurySeats) {
		this.luxurySeats = luxurySeats;
	}

	// Метод для получения часа отправления
	public int getDepartureHour() {
		try {
			return Integer.parseInt(departureTime.split(":")[0]);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public String toString() {
		return String.format("Поезд №%s -> %s, отправление: %s, места: общие(%d), купе(%d), плацкарт(%d), люкс(%d)",
				trainNumber, destination, departureTime, commonSeats, compartmentSeats, reservedSeats, luxurySeats);
	}
}

class TrainManager {
	private List<Train> trains;

	public TrainManager() {
		trains = new ArrayList<>();
	}

	// Метод для добавления поезда
	public void addTrain(Train train) {
		trains.add(train);
	}

	// a) Список поездов до заданного пункта назначения
	public List<Train> getTrainsByDestination(String destination) {
		List<Train> result = new ArrayList<>();
		for (Train train : trains) {
			if (train.getDestination().equalsIgnoreCase(destination)) {
				result.add(train);
			}
		}
		return result;
	}

	// b) Список поездов до заданного пункта и после заданного часа
	public List<Train> getTrainsByDestinationAndTime(String destination, int hour) {
		List<Train> result = new ArrayList<>();
		for (Train train : trains) {
			if (train.getDestination().equalsIgnoreCase(destination) &&
					train.getDepartureHour() > hour) {
				result.add(train);
			}
		}
		return result;
	}

	// c) Список поездов до заданного пункта с общими местами
	public List<Train> getTrainsByDestinationWithCommonSeats(String destination) {
		List<Train> result = new ArrayList<>();
		for (Train train : trains) {
			if (train.getDestination().equalsIgnoreCase(destination) &&
					train.getCommonSeats() > 0) {
				result.add(train);
			}
		}
		return result;
	}

	// Метод для вывода списка поездов
	public void printTrains(List<Train> trainList, String message) {
		System.out.println("\n" + message);
		if (trainList.isEmpty()) {
			System.out.println("Поезда не найдены.");
		} else {
			for (Train train : trainList) {
				System.out.println(train);
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TrainManager manager = new TrainManager();

		initializeTrains(manager);

		System.out.println("=== СИСТЕМА ПОИСКА ПОЕЗДОВ ===");

		// a) Список поездов до заданного пункта назначения
		System.out.print("\nВведите пункт назначения для поиска: ");
		String destination = scanner.nextLine();
		List<Train> destinationTrains = manager.getTrainsByDestination(destination);
		manager.printTrains(destinationTrains,
				"Поезда до " + destination + ":");

		// b) Список поездов до заданного пункта и после заданного часа
		System.out.print("\nВведите час отправления (0-23): ");
		int hour = scanner.nextInt();
		scanner.nextLine(); // очистка буфера
		List<Train> timeTrains = manager.getTrainsByDestinationAndTime(destination, hour);
		manager.printTrains(timeTrains,
				String.format("Поезда до %s после %d:00:", destination, hour));

		// c) Список поездов до заданного пункта с общими местами
		List<Train> commonSeatsTrains = manager.getTrainsByDestinationWithCommonSeats(destination);
		manager.printTrains(commonSeatsTrains,
				"Поезда до " + destination + " с общими местами:");

		scanner.close();
	}

	private static void initializeTrains(TrainManager manager) {
		manager.addTrain(new Train("Москва", "001А", "08:30", 50, 20, 30, 10));
		manager.addTrain(new Train("Санкт-Петербург", "102Б", "14:15", 0, 15, 25, 5));
		manager.addTrain(new Train("Москва", "203В", "18:45", 30, 10, 40, 8));
		manager.addTrain(new Train("Казань", "304Г", "06:20", 60, 25, 35, 0));
		manager.addTrain(new Train("Москва", "405Д", "22:10", 40, 30, 20, 12));
		manager.addTrain(new Train("Сочи", "506Е", "12:00", 0, 20, 45, 15));
		manager.addTrain(new Train("Москва", "607Ж", "16:30", 25, 15, 30, 5));
	}
}
