// Статусы рейса
enum FlightStatus {
	SCHEDULED("Запланирован"),
	BOARDING("Посадка"),
	IN_FLIGHT("В полете"),
	COMPLETED("Завершен"),
	CANCELLED("Отменен"),
	DIVERTED("Перенаправлен");

	private final String description;

	FlightStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
