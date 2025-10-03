import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Класс Станция
class Station {
	private String name;
	private String code;

	public Station(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Station station = (Station) o;
		return Objects.equals(code, station.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public String toString() {
		return name + " (" + code + ")";
	}
}

// Класс Поезд
class Train {
	private String trainNumber;
	private String name;
	private List<Station> route;
	private Map<Station, LocalDateTime> schedule;
	private Map<String, Double> prices; // цена до каждой станции

	public Train(String trainNumber, String name) {
		this.trainNumber = trainNumber;
		this.name = name;
		this.route = new ArrayList<>();
		this.schedule = new HashMap<>();
		this.prices = new HashMap<>();
	}

	// Методы администратора для настройки поезда
	public void addStationToRoute(Station station, LocalDateTime arrivalTime, double price) {
		route.add(station);
		schedule.put(station, arrivalTime);
		prices.put(station.getCode(), price);
	}

	public boolean goesToStation(Station station) {
		return route.contains(station);
	}

	public LocalDateTime getArrivalTime(Station station) {
		return schedule.get(station);
	}

	public Double getPriceToStation(Station station) {
		return prices.get(station.getCode());
	}

	public List<Station> getStationsAfter(Station fromStation) {
		int index = route.indexOf(fromStation);
		if (index == -1)
			return new ArrayList<>();
		return new ArrayList<>(route.subList(index, route.size()));
	}

	// Геттеры
	public String getTrainNumber() {
		return trainNumber;
	}

	public String getName() {
		return name;
	}

	public List<Station> getRoute() {
		return new ArrayList<>(route);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Train train = (Train) o;
		return Objects.equals(trainNumber, train.trainNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(trainNumber);
	}

	@Override
	public String toString() {
		return "Поезд " + trainNumber + " \"" + name + "\"";
	}
}

// Класс Заявка
class BookingRequest {
	private static int nextId = 1;
	private int id;
	private Passenger passenger;
	private Station destination;
	private LocalDateTime desiredTime;
	private LocalDateTime requestTime;
	private RequestStatus status;

	public BookingRequest(Passenger passenger, Station destination, LocalDateTime desiredTime) {
		this.id = nextId++;
		this.passenger = passenger;
		this.destination = destination;
		this.desiredTime = desiredTime;
		this.requestTime = LocalDateTime.now();
		this.status = RequestStatus.PENDING;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public Station getDestination() {
		return destination;
	}

	public LocalDateTime getDesiredTime() {
		return desiredTime;
	}

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BookingRequest that = (BookingRequest) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		return "Заявка #" + id + " на " + destination.getName() +
				" на " + desiredTime.format(formatter) + " (" + status + ")";
	}
}

// Класс Пассажир
class Passenger {
	private String name;
	private String email;
	private String phone;
	private List<BookingRequest> requests;

	public Passenger(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.requests = new ArrayList<>();
	}

	// Пассажир делает заявку
	public BookingRequest makeRequest(Station destination, LocalDateTime desiredTime) {
		BookingRequest request = new BookingRequest(this, destination, desiredTime);
		requests.add(request);
		return request;
	}

	// Геттеры
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public List<BookingRequest> getRequests() {
		return new ArrayList<>(requests);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Passenger passenger = (Passenger) o;
		return Objects.equals(email, passenger.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public String toString() {
		return name + " (" + email + ")";
	}
}

// Класс Счет
class Invoice {
	private static int nextId = 1;
	private int id;
	private BookingRequest request;
	private Train selectedTrain;
	private double amount;
	private LocalDateTime issueTime;
	private PaymentStatus paymentStatus;

	public Invoice(BookingRequest request, Train selectedTrain) {
		this.id = nextId++;
		this.request = request;
		this.selectedTrain = selectedTrain;
		this.amount = selectedTrain.getPriceToStation(request.getDestination());
		this.issueTime = LocalDateTime.now();
		this.paymentStatus = PaymentStatus.PENDING;
	}

	public void markAsPaid() {
		this.paymentStatus = PaymentStatus.PAID;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public BookingRequest getRequest() {
		return request;
	}

	public Train getSelectedTrain() {
		return selectedTrain;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDateTime getIssueTime() {
		return issueTime;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Invoice invoice = (Invoice) o;
		return id == invoice.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Счет #" + id + " на сумму " + amount + " руб. (" + paymentStatus + ")";
	}
}

// Класс Администратор
class Administrator {
	private String username;
	private String name;

	public Administrator(String username, String name) {
		this.username = username;
		this.name = name;
	}

	// Администратор добавляет поезд
	public Train addTrain(RailwayTicketSystem system, String trainNumber, String trainName) {
		Train train = new Train(trainNumber, trainName);
		system.addTrain(train);
		return train;
	}

	// Администратор настраивает маршрут поезда
	public void configureTrainRoute(Train train, Station station, LocalDateTime arrivalTime, double price) {
		train.addStationToRoute(station, arrivalTime, price);
	}

	// Геттеры
	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Administrator that = (Administrator) o;
		return Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public String toString() {
		return name + " (" + username + ")";
	}
}

// Перечисления для статусов
enum RequestStatus {
	PENDING, PROCESSED, CANCELLED
}

enum PaymentStatus {
	PENDING, PAID, CANCELLED
}

// Главная система - Железнодорожная касса
class RailwayTicketSystem {
	private List<Train> trains;
	private List<BookingRequest> requests;
	private List<Invoice> invoices;
	private List<Station> stations;
	private List<Administrator> administrators;

	public RailwayTicketSystem() {
		this.trains = new ArrayList<>();
		this.requests = new ArrayList<>();
		this.invoices = new ArrayList<>();
		this.stations = new ArrayList<>();
		this.administrators = new ArrayList<>();
	}

	// Регистрация заявки пассажира
	public BookingRequest registerRequest(Passenger passenger, Station destination, LocalDateTime desiredTime) {
		BookingRequest request = passenger.makeRequest(destination, desiredTime);
		requests.add(request);
		return request;
	}

	// Поиск подходящих поездов
	public List<Train> findSuitableTrains(BookingRequest request) {
		List<Train> suitableTrains = new ArrayList<>();

		for (Train train : trains) {
			if (train.goesToStation(request.getDestination())) {
				LocalDateTime trainArrival = train.getArrivalTime(request.getDestination());
				// Проверяем, что поезд прибывает в нужный день
				if (trainArrival.toLocalDate().equals(request.getDesiredTime().toLocalDate())) {
					suitableTrains.add(train);
				}
			}
		}

		// Сортируем по времени прибытия
		suitableTrains.sort(Comparator.comparing(t -> t.getArrivalTime(request.getDestination())));
		return suitableTrains;
	}

	// Пассажир выбирает поезд и получает счет
	public Invoice selectTrainAndGetInvoice(BookingRequest request, Train selectedTrain) {
		if (!findSuitableTrains(request).contains(selectedTrain)) {
			throw new IllegalArgumentException("Выбранный поезд не подходит для данной заявки");
		}

		request.setStatus(RequestStatus.PROCESSED);
		Invoice invoice = new Invoice(request, selectedTrain);
		invoices.add(invoice);
		return invoice;
	}

	// Методы администратора
	public void addTrain(Train train) {
		trains.add(train);
	}

	public void addStation(Station station) {
		stations.add(station);
	}

	public void addAdministrator(Administrator admin) {
		administrators.add(admin);
	}

	// Геттеры
	public List<Train> getTrains() {
		return new ArrayList<>(trains);
	}

	public List<BookingRequest> getRequests() {
		return new ArrayList<>(requests);
	}

	public List<Invoice> getInvoices() {
		return new ArrayList<>(invoices);
	}

	public List<Station> getStations() {
		return new ArrayList<>(stations);
	}

	@Override
	public String toString() {
		return "Железнодорожная касса: " + trains.size() + " поездов, " +
				requests.size() + " заявок, " + invoices.size() + " счетов";
	}
}

// Демонстрационный класс
public class Main {
	public static void main(String[] args) {
		// Создаем систему
		RailwayTicketSystem system = new RailwayTicketSystem();

		// Создаем администратора
		Administrator admin = new Administrator("admin", "Иван Петров");
		system.addAdministrator(admin);

		// Создаем станции
		Station moscow = new Station("Москва", "MSK");
		Station tver = new Station("Тверь", "TVR");
		Station spb = new Station("Санкт-Петербург", "SPB");
		Station kazan = new Station("Казань", "KZN");

		system.addStation(moscow);
		system.addStation(tver);
		system.addStation(spb);
		system.addStation(kazan);

		// Администратор добавляет поезда
		Train train1 = admin.addTrain(system, "001A", "Красная стрела");
		Train train2 = admin.addTrain(system, "002B", "Сапсан");
		Train train3 = admin.addTrain(system, "003C", "Волга");

		// Администратор настраивает маршруты и цены
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

		// Поезд Москва - Санкт-Петербург
		admin.configureTrainRoute(train1, moscow, LocalDateTime.of(2024, 1, 20, 22, 0), 0);
		admin.configureTrainRoute(train1, tver, LocalDateTime.of(2024, 1, 21, 1, 30), 1500.0);
		admin.configureTrainRoute(train1, spb, LocalDateTime.of(2024, 1, 21, 7, 30), 3000.0);

		// Поезд Москва - Санкт-Петербург (скорый)
		admin.configureTrainRoute(train2, moscow, LocalDateTime.of(2024, 1, 20, 18, 0), 0);
		admin.configureTrainRoute(train2, spb, LocalDateTime.of(2024, 1, 20, 23, 30), 5000.0);

		// Поезд Москва - Казань
		admin.configureTrainRoute(train3, moscow, LocalDateTime.of(2024, 1, 20, 20, 0), 0);
		admin.configureTrainRoute(train3, kazan, LocalDateTime.of(2024, 1, 21, 8, 0), 2500.0);

		// Создаем пассажира
		Passenger passenger = new Passenger("Анна Сидорова", "anna@mail.ru", "+79161234567");

		System.out.println("=== СИСТЕМА ЖЕЛЕЗНОДОРОЖНОЙ КАССЫ ===\n");

		// Пассажир делает заявку на поездку в Санкт-Петербург 21.01.2024
		LocalDateTime desiredTime = LocalDateTime.of(2024, 1, 21, 0, 0);
		BookingRequest request = system.registerRequest(passenger, spb, desiredTime);

		System.out.println("1. Пассажир " + passenger.getName() + " создал заявку:");
		System.out.println("   " + request);

		// Система ищет подходящие поезда
		List<Train> suitableTrains = system.findSuitableTrains(request);

		System.out.println("\n2. Система нашла подходящие поезда:");
		for (Train train : suitableTrains) {
			LocalDateTime arrival = train.getArrivalTime(spb);
			Double price = train.getPriceToStation(spb);
			System.out.println("   - " + train + ", прибытие: " +
					arrival.format(formatter) + ", цена: " + price + " руб.");
		}

		// Пассажир выбирает поезд и получает счет
		if (!suitableTrains.isEmpty()) {
			Train selectedTrain = suitableTrains.get(0); // выбираем первый поезд
			Invoice invoice = system.selectTrainAndGetInvoice(request, selectedTrain);

			System.out.println("\n3. Пассажир выбрал поезд и получил счет:");
			System.out.println("   " + invoice);
			System.out.println("   Детали: " + selectedTrain + " до " + spb.getName());

			// Пассажир оплачивает счет
			invoice.markAsPaid();
			System.out.println("   Счет оплачен!");
		}

		// Выводим статистику системы
		System.out.println("\n4. Статистика системы:");
		System.out.println("   " + system);
		System.out.println("   Всего заявок: " + system.getRequests().size());
		System.out.println("   Всего счетов: " + system.getInvoices().size());
		System.out.println("   Всего поездов: " + system.getTrains().size());

		// Демонстрация методов equals, hashCode, toString
		System.out.println("\n5. Демонстрация методов Object:");

		Station spb2 = new Station("Санкт-Петербург", "SPB");
		System.out.println("   spb.equals(spb2): " + spb.equals(spb2));
		System.out.println("   spb.hashCode(): " + spb.hashCode());
		System.out.println("   spb2.hashCode(): " + spb2.hashCode());
		System.out.println("   passenger: " + passenger);
		System.out.println("   admin: " + admin);
	}
}
