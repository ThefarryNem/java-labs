import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Класс Луна
class Moon {
	private String name;
	private double radius;
	private double mass;

	public Moon(String name, double radius, double mass) {
		this.name = name;
		this.radius = radius;
		this.mass = mass;
	}

	// Геттеры
	public String getName() {
		return name;
	}

	public double getRadius() {
		return radius;
	}

	public double getMass() {
		return mass;
	}

	// Метод для получения информации о луне
	public String getMoonInfo() {
		return name + " (радиус: " + radius + " км, масса: " + mass + " кг)";
	}

	// Переопределение equals
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Moon moon = (Moon) o;
		return Double.compare(moon.radius, radius) == 0 &&
				Double.compare(moon.mass, mass) == 0 &&
				Objects.equals(name, moon.name);
	}

	// Переопределение hashCode
	@Override
	public int hashCode() {
		return Objects.hash(name, radius, mass);
	}

	// Переопределение toString
	@Override
	public String toString() {
		return "Moon{" +
				"name='" + name + '\'' +
				", radius=" + radius +
				", mass=" + mass +
				'}';
	}
}

// Класс Планета
class Planet {
	private String name;
	private double radius;
	private double mass;
	private double distanceFromStar;
	private List<Moon> moons;

	public Planet(String name, double radius, double mass, double distanceFromStar) {
		this.name = name;
		this.radius = radius;
		this.mass = mass;
		this.distanceFromStar = distanceFromStar;
		this.moons = new ArrayList<>();
	}

	// Метод добавления луны к планете
	public void addMoon(Moon moon) {
		moons.add(moon);
	}

	// Метод получения количества лун
	public int getMoonCount() {
		return moons.size();
	}

	// Метод для получения информации о планете
	public String getPlanetInfo() {
		return name + " (радиус: " + radius + " км, масса: " + mass +
				" кг, расстояние от звезды: " + distanceFromStar + " млн км)";
	}

	// Геттеры
	public String getName() {
		return name;
	}

	public double getRadius() {
		return radius;
	}

	public double getMass() {
		return mass;
	}

	public double getDistanceFromStar() {
		return distanceFromStar;
	}

	public List<Moon> getMoons() {
		return new ArrayList<>(moons);
	}

	// Переопределение equals
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Planet planet = (Planet) o;
		return Double.compare(planet.radius, radius) == 0 &&
				Double.compare(planet.mass, mass) == 0 &&
				Double.compare(planet.distanceFromStar, distanceFromStar) == 0 &&
				Objects.equals(name, planet.name) &&
				Objects.equals(moons, planet.moons);
	}

	// Переопределение hashCode
	@Override
	public int hashCode() {
		return Objects.hash(name, radius, mass, distanceFromStar, moons);
	}

	// Переопределение toString
	@Override
	public String toString() {
		return "Planet{" +
				"name='" + name + '\'' +
				", radius=" + radius +
				", mass=" + mass +
				", distanceFromStar=" + distanceFromStar +
				", moons=" + moons +
				'}';
	}
}

// Класс Звезда
class Star {
	private String name;
	private String spectralClass;
	private double temperature;
	private double mass;

	public Star(String name, String spectralClass, double temperature, double mass) {
		this.name = name;
		this.spectralClass = spectralClass;
		this.temperature = temperature;
		this.mass = mass;
	}

	// Метод для получения информации о звезде
	public String getStarInfo() {
		return name + " (спектральный класс: " + spectralClass +
				", температура: " + temperature + " K, масса: " + mass + " солнц)";
	}

	// Геттеры
	public String getName() {
		return name;
	}

	public String getSpectralClass() {
		return spectralClass;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getMass() {
		return mass;
	}

	// Переопределение equals
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Star star = (Star) o;
		return Double.compare(star.temperature, temperature) == 0 &&
				Double.compare(star.mass, mass) == 0 &&
				Objects.equals(name, star.name) &&
				Objects.equals(spectralClass, star.spectralClass);
	}

	// Переопределение hashCode
	@Override
	public int hashCode() {
		return Objects.hash(name, spectralClass, temperature, mass);
	}

	// Переопределение toString
	@Override
	public String toString() {
		return "Star{" +
				"name='" + name + '\'' +
				", spectralClass='" + spectralClass + '\'' +
				", temperature=" + temperature +
				", mass=" + mass +
				'}';
	}
}

// Класс Звездная система
class StarSystem {
	private String name;
	private Star star;
	private List<Planet> planets;

	public StarSystem(String name, Star star) {
		this.name = name;
		this.star = star;
		this.planets = new ArrayList<>();
	}

	// Метод добавления планеты в систему
	public void addPlanet(Planet planet) {
		planets.add(planet);
	}

	// Метод вывода количества планет в системе
	public void printPlanetCount() {
		System.out.println("Количество планет в системе " + name + ": " + planets.size());
	}

	// Метод вывода названия звезды
	public void printStarName() {
		System.out.println("Название звезды: " + star.getName());
	}

	// Метод получения количества планет
	public int getPlanetCount() {
		return planets.size();
	}

	// Метод для получения информации о системе
	public void printSystemInfo() {
		System.out.println("=== Звездная система: " + name + " ===");
		printStarName();
		System.out.println("Информация о звезде: " + star.getStarInfo());
		printPlanetCount();
		System.out.println("\nПланеты системы:");
		for (int i = 0; i < planets.size(); i++) {
			Planet planet = planets.get(i);
			System.out.println((i + 1) + ". " + planet.getPlanetInfo());
			System.out.println("   Количество лун: " + planet.getMoonCount());
			if (planet.getMoonCount() > 0) {
				System.out.println("   Луны:");
				for (Moon moon : planet.getMoons()) {
					System.out.println("     - " + moon.getMoonInfo());
				}
			}
		}
		System.out.println("=================================");
	}

	// Геттеры
	public String getName() {
		return name;
	}

	public Star getStar() {
		return star;
	}

	public List<Planet> getPlanets() {
		return new ArrayList<>(planets);
	}

	// Переопределение equals
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		StarSystem that = (StarSystem) o;

		return Objects.equals(name, that.name) &&
				Objects.equals(star, that.star) &&
				Objects.equals(planets, that.planets);
	}

	// Переопределение hashCode
	@Override
	public int hashCode() {
		return Objects.hash(name, star, planets);
	}

	// Переопределение toString
	@Override
	public String toString() {
		return "StarSystem{" +
				"name='" + name + '\'' +
				", star=" + star +
				", planets=" + planets +
				'}';
	}
}

// Демонстрационный класс
public class StarSystemDemo {
	public static void main(String[] args) {
		// Создаем звезду - Солнце
		Star sun = new Star("Солнце", "G2V", 5778, 1.0);

		// Создаем звездную систему - Солнечная система
		StarSystem solarSystem = new StarSystem("Солнечная система", sun);

		// Создаем луны
		Moon moon = new Moon("Луна", 1737.1, 7.35e22);
		Moon phobos = new Moon("Фобос", 11.1, 1.0659e16);
		Moon deimos = new Moon("Деймос", 6.2, 1.4762e15);
		Moon europa = new Moon("Европа", 1560.8, 4.8e22);
		Moon titan = new Moon("Титан", 2574.7, 1.345e23);

		// Создаем планеты и добавляем к ним луны
		Planet mercury = new Planet("Меркурий", 2439.7, 3.301e23, 57.9);
		Planet venus = new Planet("Венера", 6051.8, 4.867e24, 108.2);
		Planet earth = new Planet("Земля", 6371.0, 5.972e24, 149.6);
		earth.addMoon(moon);

		Planet mars = new Planet("Марс", 3389.5, 6.39e23, 227.9);
		mars.addMoon(phobos);
		mars.addMoon(deimos);

		Planet jupiter = new Planet("Юпитер", 69911.0, 1.898e27, 778.5);
		jupiter.addMoon(europa);

		Planet saturn = new Planet("Сатурн", 58232.0, 5.683e26, 1432.0);
		saturn.addMoon(titan);

		// Добавляем планеты в звездную систему
		solarSystem.addPlanet(mercury);
		solarSystem.addPlanet(venus);
		solarSystem.addPlanet(earth);
		solarSystem.addPlanet(mars);
		solarSystem.addPlanet(jupiter);
		solarSystem.addPlanet(saturn);

		// Демонстрация работы методов
		System.out.println("=== Демонстрация работы звездной системы ===\n");

		// Выводим информацию о системе
		solarSystem.printSystemInfo();

		// Отдельно демонстрируем требуемые методы
		System.out.println("=== Требуемые методы ===");
		solarSystem.printPlanetCount(); // Вывод количества планет
		solarSystem.printStarName(); // Вывод названия звезды

		// Добавляем еще одну планету
		System.out.println("\n=== Добавление новой планеты ===");
		Planet uranus = new Planet("Уран", 25362.0, 8.681e25, 2867.0);
		solarSystem.addPlanet(uranus);
		solarSystem.printPlanetCount();

		// Демонстрация методов equals, hashCode, toString
		System.out.println("\n=== Демонстрация методов Object ===");

		// Создаем аналогичные объекты для сравнения
		Star sun2 = new Star("Солнце", "G2V", 5778, 1.0);
		StarSystem solarSystem2 = new StarSystem("Солнечная система", sun2);
		solarSystem2.addPlanet(new Planet("Меркурий", 2439.7, 3.301e23, 57.9));

		Moon moon1 = new Moon("Луна", 1737.1, 7.35e22);
		Moon moon2 = new Moon("Луна", 1737.1, 7.35e22);
		Moon moon3 = new Moon("Фобос", 11.1, 1.0659e16);

		System.out.println("moon1.equals(moon2): " + moon1.equals(moon2));
		System.out.println("moon1.equals(moon3): " + moon1.equals(moon3));
		System.out.println("moon1.hashCode(): " + moon1.hashCode());
		System.out.println("moon2.hashCode(): " + moon2.hashCode());
		System.out.println("moon1: " + moon1);

		System.out.println("\nsolarSystem.equals(solarSystem2): " + solarSystem.equals(solarSystem2));
		System.out.println("solarSystem: " + solarSystem);
	}
}
