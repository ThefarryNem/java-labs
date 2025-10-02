import java.time.LocalDateTime;

// Основной класс для запуска системы
public class AeroflotSystem {
	public static void main(String[] args) {
		// Инициализация системы
		Airport svo = new Airport("SVO", "Шереметьево", "Москва");
		Airport led = new Airport("LED", "Пулково", "Санкт-Петербург");
		Airport krr = new Airport("KRR", "Пашковский", "Краснодар");

		// Создание самолетов
		Aircraft aircraft1 = new Aircraft("SU-1001", "Boeing 737-800", 180, 5400);
		Aircraft aircraft2 = new Aircraft("SU-1002", "Airbus A320", 150, 5700);

		// Создание сотрудников
		CrewMember pilot1 = new CrewMember(1, "Иван Сидоров", Position.CAPTAIN, 15);
		CrewMember pilot2 = new CrewMember(2, "Петр Иванов", Position.FIRST_OFFICER, 8);
		CrewMember navigator = new CrewMember(3, "Сергей Петров", Position.NAVIGATOR, 12);
		CrewMember radio = new CrewMember(4, "Алексей Смирнов", Position.RADIO_OPERATOR, 10);
		CrewMember stewardess1 = new CrewMember(5, "Мария Козлова", Position.FLIGHT_ATTENDANT, 5);
		CrewMember stewardess2 = new CrewMember(6, "Анна Новикова", Position.FLIGHT_ATTENDANT, 3);

		// Администратор создает бригады
		FlightAdministrator admin = new FlightAdministrator(7, "Ольга Васильева");

		Flight flight1 = admin.createFlight("SU-100", svo, led,
				LocalDateTime.of(2024, 1, 15, 10, 0),
				aircraft1);

		// Формирование бригады на рейс
		Crew brigade1 = admin.createCrew(flight1, pilot1, pilot2, navigator, radio);
		admin.addFlightAttendant(brigade1, stewardess1);
		admin.addFlightAttendant(brigade1, stewardess2);

		System.out.println("=== ИНИЦИАЛИЗАЦИЯ СИСТЕМЫ ===");
		System.out.println("Создан рейс: " + flight1);
		System.out.println("Сформирована бригада: " + brigade1.getCrewMembers().size() + " человек");

		// Проверка погодных условий
		svo.setWeatherConditions(WeatherConditions.GOOD);
		led.setWeatherConditions(WeatherConditions.STORM);

		// Попытка выполнения рейса
		System.out.println("\n=== ПРОВЕРКА РЕЙСА ===");
		flight1.checkFlightStatus();

		// Изменение погоды и выполнение рейса
		System.out.println("\n=== ИЗМЕНЕНИЕ ПОГОДЫ ===");
		led.setWeatherConditions(WeatherConditions.GOOD);
		flight1.checkFlightStatus();

		// Начало рейса
		System.out.println("\n=== ВЫПОЛНЕНИЕ РЕЙСА ===");
		flight1.startFlight();

		// Имитация технической неисправности в полете
		System.out.println("\n=== ТЕХНИЧЕСКАЯ НЕИСПРАВНОСТЬ ===");
		pilot1.reportTechnicalIssue(flight1, "Неисправность гидросистемы", krr);

		// Завершение рейса
		flight1.completeFlight();

		// Создание отмененного рейса
		System.out.println("\n=== ОТМЕНЕННЫЙ РЕЙС ===");
		Flight flight2 = admin.createFlight("SU-200", svo, krr,
				LocalDateTime.of(2024, 1, 16, 14, 0),
				aircraft2);

		svo.setWeatherConditions(WeatherConditions.SNOW);
		flight2.checkFlightStatus();
	}
}
