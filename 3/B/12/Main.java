import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	// Класс для описания рейса
	static class Flight {
		String destination; // Пункт назначения
		String flightNumber; // Номер рейса
		String aircraftType; // Тип самолета
		String departureTime; // Время вылета (в формате HH:MM)
		String dayOfWeek; // День недели

		public Flight(String destination, String flightNumber, String aircraftType, String departureTime,
				String dayOfWeek) {
			this.destination = destination;
			this.flightNumber = flightNumber;
			this.aircraftType = aircraftType;
			this.departureTime = departureTime;
			this.dayOfWeek = dayOfWeek;
		}

		// Метод для сравнения времени
		public int compareTime(String time) {
			String[] partsThis = this.departureTime.split(":");
			String[] partsTime = time.split(":");
			int hoursThis = Integer.parseInt(partsThis[0]);
			int minutesThis = Integer.parseInt(partsThis[1]);
			int hoursTime = Integer.parseInt(partsTime[0]);
			int minutesTime = Integer.parseInt(partsTime[1]);
			if (hoursThis != hoursTime) {
				return hoursThis - hoursTime;
			}
			return minutesThis - minutesTime;
		}

		@Override
		public String toString() {
			return String.format("Рейс: %s, №: %s, Тип: %s, Время: %s, День: %s",
					destination, flightNumber, aircraftType, departureTime, dayOfWeek);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Создаем массив рейсов
		Flight[] flights = {
				new Flight("Москва", "SU100", "Boing 737", "09:30", "Понедельник"),
				new Flight("Санкт-Петербург", "SU200", "Airbus A320", "14:45", "Вторник"),
				new Flight("Москва", "SU101", "Boing 737", "12:00", "Понедельник"),
				new Flight("Киев", "PS300", "Airbus A321", "16:15", "Среда"),
				new Flight("Москва", "SU102", "Boing 737", "08:15", "Понедельник"),
				new Flight("Лондон", "BA400", "Boeing 747", "11:00", "Вторник"),
				new Flight("Москва", "SU103", "Boing 737", "20:00", "Среда"),
				new Flight("Киев", "PS301", "Airbus A321", "09:45", "Понедельник"),
				new Flight("Лондон", "BA401", "Boeing 747", "13:30", "Понедельник")
		};

		try {
			System.out.println("Выберите действие:");
			System.out.println("1 - Список рейсов для заданного пункта назначения");
			System.out.println("2 - Список рейсов для заданного дня недели");
			System.out.println("3 - Список рейсов для дня недели, время вылета которых больше заданного");
			int choice = scanner.nextInt();
			scanner.nextLine(); // очищение буфера

			List<Flight> filteredFlights = new ArrayList<>();

			switch (choice) {
				case 1:
					System.out.println("Введите пункт назначения:");
					String dest = scanner.nextLine();
					for (Flight f : flights) {
						if (f.destination.equalsIgnoreCase(dest)) {
							filteredFlights.add(f);
						}
					}
					break;

				case 2:
					System.out.println("Введите день недели (например, Понедельник):");
					String day = scanner.nextLine();
					for (Flight f : flights) {
						if (f.dayOfWeek.equalsIgnoreCase(day)) {
							filteredFlights.add(f);
						}
					}
					break;

				case 3:
					System.out.println("Введите день недели:");
					String day2 = scanner.nextLine();
					System.out.println("Введите время вылета (в формате HH:MM):");
					String time = scanner.nextLine();
					for (Flight f : flights) {
						if (f.dayOfWeek.equalsIgnoreCase(day2) && f.compareTime(time) > 0) {
							filteredFlights.add(f);
						}
					}
					break;

				default:
					System.out.println("Некорректный выбор.");
					return;
			}

			if (filteredFlights.isEmpty()) {
				System.out.println("Рейсы не найдены по выбранным критериям.");
			} else {
				System.out.println("Найденные рейсы:");
				for (int i = 0; i < filteredFlights.size(); i++) {
					System.out.printf("%d. %s%n", i + 1, filteredFlights.get(i));
				}

				System.out.println("Введите номер рейса для отображения подробной информации:");
				int selectedNumber = scanner.nextInt();
				scanner.nextLine(); // очистка буфера

				if (selectedNumber >= 1 && selectedNumber <= filteredFlights.size()) {
					Flight selectedFlight = filteredFlights.get(selectedNumber - 1);
					System.out.println("Вы выбрали рейс:");
					System.out.println(selectedFlight);
				} else {
					System.out.println("Некорректный номер рейса.");
				}
			}

		} catch (Exception e) {
			System.out.println("Ошибка ввода: " + e.getMessage());
		}

		scanner.close();
	}
}
