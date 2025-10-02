// Погодные условия
enum WeatherConditions {
	GOOD("Хорошая"),
	RAIN("Дождь"),
	SNOW("Снег"),
	FOG("Туман"),
	STORM("Шторм"),
	HURRICANE("Ураган");

	private final String description;

	WeatherConditions(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
