import java.util.Objects;
import java.util.Arrays;

class Continent {
	private String name;

	public Continent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void displayName() {
		System.out.println("Материк: " + name);
	}

	public String toString() {
		return "Continent{name='" + name + "'}";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Continent continent = (Continent) obj;
		return Objects.equals(name, continent.name);
	}

	public int hashCode() {
		return Objects.hash(name);
	}
}

class Planet {
	private String name;
	private Continent[] continents;

	public Planet(String name, Continent[] continents) {
		this.name = name;
		this.continents = continents;
	}

	public void displayPlanetName() {
		System.out.println("Планета: " + name);
	}

	// Метод для вывода названий всех материков
	public void displayAllContinents() {
		System.out.println("Материки планеты " + name + ":");
		for (Continent continent : continents) {
			continent.displayName();
		}
	}

	public void displayContinentCount() {
		System.out.println("Количество материков: " + continents.length);
	}

	public String toString() {
		return "Planet{name='" + name + "', continents=" + Arrays.toString(continents) + "}";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Planet planet = (Planet) obj;

		if (!Objects.equals(name, planet.name)) { // Сравниваем названия планет
			return false;
		}

		return Arrays.equals(continents, planet.continents); // Сравниваем массивы материков
	}

	// Метод который выводит всю информацию
	public void displayAllInfo() {
		displayPlanetName();
		displayContinentCount();
		displayAllContinents();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Continent[] getContinents() {
		return continents;
	}

	public void setContinents(Continent[] continents) {
		this.continents = continents;
	}

	public int hashCode() {
		int result = Objects.hash(name);
		result = 31 * result + Arrays.hashCode(continents);
		return result;
	}
}

public class PlanetDemo {
	public static void main(String[] args) {
		Continent[] earthContinents = {
				new Continent("Евразия"),
				new Continent("Африка"),
				new Continent("Северная Америка"),
				new Continent("Южная Америка"),
				new Continent("Австралия"),
				new Continent("Антарктида")
		};

		Planet earth = new Planet("Земля", earthContinents);

		System.out.println("=== Тестирование toString() ===");
		System.out.println("Планета: " + earth.toString());
		System.out.println("Материк: " + earthContinents[0].toString());

		System.out.println("\n=== Тестирование equals() ===");

		Continent[] earthContinents2 = { // Создаем идентичную планету
				new Continent("Евразия"),
				new Continent("Африка"),
				new Continent("Северная Америка"),
				new Continent("Южная Америка"),
				new Continent("Австралия"),
				new Continent("Антарктида")
		};
		Planet earth2 = new Planet("Земля", earthContinents2);

		Continent[] marsContinents = { // Создаем другую планету
				new Continent("Великая равнина"),
				new Continent("Красное плато")
		};
		Planet mars = new Planet("Марс", marsContinents);

		System.out.println("earth.equals(earth2): " + earth.equals(earth2));
		System.out.println("earth.equals(mars): " + earth.equals(mars));
		System.out.println("earth.equals(null): " + earth.equals(null));
		System.out.println("earth.equals(earth): " + earth.equals(earth));

		Continent eurasia1 = new Continent("Евразия"); // Тестируем методы equals() для материков
		Continent eurasia2 = new Continent("Евразия");
		Continent africa = new Continent("Африка");

		System.out.println("\nСравнение материков:");
		System.out.println("eurasia1.equals(eurasia2): " + eurasia1.equals(eurasia2));
		System.out.println("eurasia1.equals(africa): " + eurasia1.equals(africa));

		// Тестируем методы hashCode()
		System.out.println("\n=== Тестирование hashCode() ===");
		System.out.println("earth.hashCode(): " + earth.hashCode());
		System.out.println("earth2.hashCode(): " + earth2.hashCode());
		System.out.println("mars.hashCode(): " + mars.hashCode());

		System.out.println("eurasia1.hashCode(): " + eurasia1.hashCode());
		System.out.println("eurasia2.hashCode(): " + eurasia2.hashCode());
		System.out.println("africa.hashCode(): " + africa.hashCode());

		// Проверяем согласованность equals() и hashCode()
		System.out.println("\n=== Проверка согласованности ===");
		System.out.println("earth.equals(earth2) && earth.hashCode() == earth2.hashCode(): " +
				(earth.equals(earth2) && earth.hashCode() == earth2.hashCode()));
		System.out.println("eurasia1.equals(eurasia2) && eurasia1.hashCode() == eurasia2.hashCode(): " +
				(eurasia1.equals(eurasia2) && eurasia1.hashCode() == eurasia2.hashCode()));
	}
}
