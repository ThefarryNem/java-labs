import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Перечисление для статуса заявки
enum RequestStatus {
	PENDING, // Ожидает рассмотрения
	APPROVED, // Подтверждена
	REJECTED, // Отклонена
	PAID // Оплачена
}

// Перечисление для класса апартаментов
enum ApartmentClass {
	STANDARD, // Стандарт
	COMFORT, // Комфорт
	LUXURY, // Люкс
	PRESIDENTIAL // Президентский
}

// Класс заявки на номер
class BookingRequest {
	private static final AtomicInteger idCounter = new AtomicInteger(1);

	private final int id;
	private final Client client;
	private final int numberOfGuests;
	private final ApartmentClass apartmentClass;
	private final LocalDate checkInDate;
	private final LocalDate checkOutDate;
	private RequestStatus status;
	private String adminNotes;
	private double totalPrice;

	public BookingRequest(Client client, int numberOfGuests, ApartmentClass apartmentClass,
			LocalDate checkInDate, LocalDate checkOutDate) {
		this.id = idCounter.getAndIncrement();
		this.client = client;
		this.numberOfGuests = numberOfGuests;
		this.apartmentClass = apartmentClass;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.status = RequestStatus.PENDING;
		this.adminNotes = "";
		calculatePrice();
	}

	private void calculatePrice() {
		long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		double basePricePerNight = 0;

		switch (apartmentClass) {
			case STANDARD -> basePricePerNight = 2000;
			case COMFORT -> basePricePerNight = 3500;
			case LUXURY -> basePricePerNight = 6000;
			case PRESIDENTIAL -> basePricePerNight = 12000;
		}

		this.totalPrice = basePricePerNight * nights;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public ApartmentClass getApartmentClass() {
		return apartmentClass;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public String getAdminNotes() {
		return adminNotes;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	// Сеттеры для администратора
	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public void setAdminNotes(String notes) {
		this.adminNotes = notes;
	}

	@Override
	public String toString() {
		return String.format("Заявка #%d: %s, %d гостей, класс: %s, %s - %s, Статус: %s, Цена: %.2f руб.",
				id, client.getName(), numberOfGuests, apartmentClass,
				checkInDate, checkOutDate, status, totalPrice);
	}
}

// Класс клиента
class Client {
	private static final AtomicInteger idCounter = new AtomicInteger(1);

	private final int id;
	private final String name;
	private final String email;
	private final String phone;
	private final List<BookingRequest> requests;

	public Client(String name, String email, String phone) {
		this.id = idCounter.getAndIncrement();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.requests = new ArrayList<>();
	}

	public BookingRequest createRequest(int numberOfGuests, ApartmentClass apartmentClass,
			LocalDate checkInDate, LocalDate checkOutDate) {
		BookingRequest request = new BookingRequest(this, numberOfGuests, apartmentClass, checkInDate, checkOutDate);
		requests.add(request);
		return request;
	}

	public void viewRequests() {
		System.out.println("\n--- Заявки клиента " + name + " ---");
		if (requests.isEmpty()) {
			System.out.println("Заявок нет.");
		} else {
			for (BookingRequest request : requests) {
				System.out.println(request);
				if (!request.getAdminNotes().isEmpty()) {
					System.out.println("   Примечание администратора: " + request.getAdminNotes());
				}
			}
		}
	}

	public void payForRequest(int requestId) {
		for (BookingRequest request : requests) {
			if (request.getId() == requestId && request.getStatus() == RequestStatus.APPROVED) {
				request.setStatus(RequestStatus.PAID);
				System.out.println("Оплата успешно проведена для заявки #" + requestId);
				return;
			}
		}
		System.out.println("Не удалось найти подтвержденную заявку с ID: " + requestId);
	}

	// Геттеры
	public int getId() {
		return id;
	}

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
}

// Класс администратора
class Administrator {
	private final String username;
	private final String password;
	private final List<BookingRequest> allRequests;

	public Administrator(String username, String password) {
		this.username = username;
		this.password = password;
		this.allRequests = new ArrayList<>();
	}

	public void addRequest(BookingRequest request) {
		allRequests.add(request);
	}

	public void reviewRequests() {
		System.out.println("\n--- Просмотр заявок администратором ---");
		List<BookingRequest> pendingRequests = allRequests.stream()
				.filter(req -> req.getStatus() == RequestStatus.PENDING)
				.toList();

		if (pendingRequests.isEmpty()) {
			System.out.println("Нет заявок, ожидающих рассмотрения.");
			return;
		}

		for (BookingRequest request : pendingRequests) {
			System.out.println(request);
		}
	}

	public void approveRequest(int requestId, String notes) {
		for (BookingRequest request : allRequests) {
			if (request.getId() == requestId && request.getStatus() == RequestStatus.PENDING) {
				request.setStatus(RequestStatus.APPROVED);
				request.setAdminNotes(notes);
				System.out.println("Заявка #" + requestId + " подтверждена.");
				return;
			}
		}
		System.out.println("Не удалось найти ожидающую заявку с ID: " + requestId);
	}

	public void rejectRequest(int requestId, String reason) {
		for (BookingRequest request : allRequests) {
			if (request.getId() == requestId && request.getStatus() == RequestStatus.PENDING) {
				request.setStatus(RequestStatus.REJECTED);
				request.setAdminNotes(reason);
				System.out.println("Заявка #" + requestId + " отклонена. Причина: " + reason);
				return;
			}
		}
		System.out.println("Не удалось найти ожидающую заявку с ID: " + requestId);
	}

	public void viewAllRequests() {
		System.out.println("\n--- Все заявки в системе ---");
		for (BookingRequest request : allRequests) {
			System.out.println(request);
			if (!request.getAdminNotes().isEmpty()) {
				System.out.println("   Примечание: " + request.getAdminNotes());
			}
		}
	}

	// Геттеры
	public String getUsername() {
		return username;
	}

	public boolean authenticate(String password) {
		return this.password.equals(password);
	}
}

// Главный класс системы
public class HotelBookingSystem {
	private final List<Client> clients;
	private final Administrator admin;
	private final Scanner scanner;

	public HotelBookingSystem() {
		this.clients = new ArrayList<>();
		this.admin = new Administrator("admin", "admin123");
		this.scanner = new Scanner(System.in);

		// Создаем тестовых клиентов
		initializeTestData();
	}

	private void initializeTestData() {
		clients.add(new Client("Иван Иванов", "ivan@mail.ru", "+79161234567"));
		clients.add(new Client("Мария Петрова", "maria@mail.ru", "+79167654321"));
	}

	public void run() {
		System.out.println("=== СИСТЕМА БРОНИРОВАНИЯ ГОСТИНИЦЫ ===");

		while (true) {
			System.out.println("\nВыберите роль:");
			System.out.println("1. Клиент");
			System.out.println("2. Администратор");
			System.out.println("3. Выход");
			System.out.print("Ваш выбор: ");

			String choice = scanner.nextLine();

			switch (choice) {
				case "1" -> clientMenu();
				case "2" -> adminMenu();
				case "3" -> {
					System.out.println("До свидания!");
					return;
				}
				default -> System.out.println("Неверный выбор. Попробуйте снова.");
			}
		}
	}

	private void clientMenu() {
		Client currentClient = selectClient();
		if (currentClient == null)
			return;

		while (true) {
			System.out.println("\n--- Меню клиента: " + currentClient.getName() + " ---");
			System.out.println("1. Создать заявку на номер");
			System.out.println("2. Просмотреть мои заявки");
			System.out.println("3. Оплатить подтвержденную заявку");
			System.out.println("4. Вернуться в главное меню");
			System.out.print("Ваш выбор: ");

			String choice = scanner.nextLine();

			switch (choice) {
				case "1" -> createBookingRequest(currentClient);
				case "2" -> currentClient.viewRequests();
				case "3" -> payForBooking(currentClient);
				case "4" -> {
					return;
				}
				default -> System.out.println("Неверный выбор. Попробуйте снова.");
			}
		}
	}

	private Client selectClient() {
		System.out.println("\n--- Выбор клиента ---");
		for (int i = 0; i < clients.size(); i++) {
			System.out.println((i + 1) + ". " + clients.get(i).getName());
		}
		System.out.println((clients.size() + 1) + ". Создать нового клиента");
		System.out.print("Ваш выбор: ");

		try {
			int choice = Integer.parseInt(scanner.nextLine());
			if (choice >= 1 && choice <= clients.size()) {
				return clients.get(choice - 1);
			} else if (choice == clients.size() + 1) {
				return createNewClient();
			} else {
				System.out.println("Неверный выбор.");
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println("Неверный формат ввода.");
			return null;
		}
	}

	private Client createNewClient() {
		System.out.print("Введите имя: ");
		String name = scanner.nextLine();
		System.out.print("Введите email: ");
		String email = scanner.nextLine();
		System.out.print("Введите телефон: ");
		String phone = scanner.nextLine();

		Client newClient = new Client(name, email, phone);
		clients.add(newClient);
		System.out.println("Новый клиент создан: " + name);
		return newClient;
	}

	private void createBookingRequest(Client client) {
		try {
			System.out.print("Количество гостей: ");
			int guests = Integer.parseInt(scanner.nextLine());

			System.out.println("Класс апартаментов:");
			for (ApartmentClass ac : ApartmentClass.values()) {
				System.out.println((ac.ordinal() + 1) + ". " + ac);
			}
			System.out.print("Ваш выбор: ");
			int classChoice = Integer.parseInt(scanner.nextLine());
			ApartmentClass apartmentClass = ApartmentClass.values()[classChoice - 1];

			System.out.print("Дата заезда (гггг-мм-дд): ");
			LocalDate checkIn = LocalDate.parse(scanner.nextLine());

			System.out.print("Дата выезда (гггг-мм-дд): ");
			LocalDate checkOut = LocalDate.parse(scanner.nextLine());

			if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
				System.out.println("Дата выезда должна быть позже даты заезда.");
				return;
			}

			BookingRequest request = client.createRequest(guests, apartmentClass, checkIn, checkOut);
			admin.addRequest(request);
			System.out.println("Заявка создана успешно! ID: " + request.getId());

		} catch (Exception e) {
			System.out.println("Ошибка при создании заявки: " + e.getMessage());
		}
	}

	private void payForBooking(Client client) {
		System.out.print("Введите ID заявки для оплаты: ");
		try {
			int requestId = Integer.parseInt(scanner.nextLine());
			client.payForRequest(requestId);
		} catch (NumberFormatException e) {
			System.out.println("Неверный формат ID.");
		}
	}

	private void adminMenu() {
		System.out.print("Введите пароль администратора: ");
		String password = scanner.nextLine();

		if (!admin.authenticate(password)) {
			System.out.println("Неверный пароль!");
			return;
		}

		while (true) {
			System.out.println("\n--- Меню администратора ---");
			System.out.println("1. Просмотреть ожидающие заявки");
			System.out.println("2. Подтвердить заявку");
			System.out.println("3. Отклонить заявку");
			System.out.println("4. Просмотреть все заявки");
			System.out.println("5. Вернуться в главное меню");
			System.out.print("Ваш выбор: ");

			String choice = scanner.nextLine();

			switch (choice) {
				case "1" -> admin.reviewRequests();
				case "2" -> approveBooking();
				case "3" -> rejectBooking();
				case "4" -> admin.viewAllRequests();
				case "5" -> {
					return;
				}
				default -> System.out.println("Неверный выбор. Попробуйте снова.");
			}
		}
	}

	private void approveBooking() {
		try {
			System.out.print("Введите ID заявки для подтверждения: ");
			int requestId = Integer.parseInt(scanner.nextLine());
			System.out.print("Введите примечание: ");
			String notes = scanner.nextLine();
			admin.approveRequest(requestId, notes);
		} catch (NumberFormatException e) {
			System.out.println("Неверный формат ID.");
		}
	}

	private void rejectBooking() {
		try {
			System.out.print("Введите ID заявки для отклонения: ");
			int requestId = Integer.parseInt(scanner.nextLine());
			System.out.print("Введите причину отклонения: ");
			String reason = scanner.nextLine();
			admin.rejectRequest(requestId, reason);
		} catch (NumberFormatException e) {
			System.out.println("Неверный формат ID.");
		}
	}

	public static void main(String[] args) {
		HotelBookingSystem system = new HotelBookingSystem();
		system.run();
	}
}
