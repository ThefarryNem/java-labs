// Класс GuitarString (переименован чтобы избежать конфликта)
class GuitarString {
	private int number; // номер струны
	private String note; // нота
	private boolean isTuned; // натянута ли струна
	private double tension; // натяжение

	public GuitarString(int number, String note) {
		this.number = number;
		this.note = note;
		this.isTuned = false;
		this.tension = 0.0;
	}

	public GuitarString(int number) {
		this(number, "E"); // по умолчанию нота E
	}

	// Метод для натягивания струны
	public void tune() {
		this.isTuned = true;
		this.tension = 8.0; // стандартное натяжение
		System.out.println("Струна " + number + " натянута до ноты " + note);
	}

	// Метод для натягивания струны до определенной ноты
	public void tune(String targetNote) {
		this.note = targetNote;
		this.isTuned = true;
		this.tension = 8.0;
		System.out.println("Струна " + number + " натянута до ноты " + targetNote);
	}

	// Метод для игры на струне
	public void play() {
		if (isTuned) {
			System.out.println("Струна " + number + " звучит нота " + note + " ♫");
		} else {
			System.out.println("Струна " + number + " не натянута! Звука нет.");
		}
	}

	// Метод для ослабления струны
	public void loosen() {
		this.isTuned = false;
		this.tension = 0.0;
		System.out.println("Струна " + number + " ослаблена");
	}

	// Геттеры
	public int getNumber() {
		return number;
	}

	public String getNote() {
		return note;
	}

	public boolean isTuned() {
		return isTuned;
	}

	public double getTension() {
		return tension;
	}

	// Сеттер для ноты
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Струна " + number + " [нота: " + note + ", натянута: " + isTuned + "]";
	}
}

// Класс Гитара
class Guitar {
	private String brand;
	private String model;
	private int stringCount;
	private GuitarString[] strings; // массив струн

	// Стандартные ноты для 6-струнной гитары
	private static final String[] STANDARD_TUNING = { "E", "A", "D", "G", "B", "E" };

	public Guitar(String brand, String model, int stringCount) {
		this.brand = brand;
		this.model = model;
		this.stringCount = stringCount;
		this.strings = new GuitarString[stringCount];
		initializeStrings();
	}

	public Guitar(String brand, String model) {
		this(brand, model, 6); // по умолчанию 6 струн
	}

	public Guitar() {
		this("Fender", "Stratocaster", 6);
	}

	// Инициализация струн
	private void initializeStrings() {
		for (int i = 0; i < stringCount; i++) {
			String note = (stringCount == 6 && i < STANDARD_TUNING.length) ? STANDARD_TUNING[i] : "E";
			strings[i] = new GuitarString(i + 1, note);
		}
	}

	// Метод для игры на гитаре
	public void play() {
		System.out.println("🎸 Играем на гитаре " + brand + " " + model + "...");
		for (GuitarString string : strings) {
			string.play();
		}
		System.out.println();
	}

	// Метод для игры на конкретной струне
	public void playString(int stringNumber) {
		if (stringNumber >= 1 && stringNumber <= stringCount) {
			strings[stringNumber - 1].play();
		} else {
			System.out.println("Струны с номером " + stringNumber + " не существует!");
		}
	}

	// Метод для игры аккорда (несколько струн одновременно)
	public void playChord(int... stringNumbers) {
		System.out.println("🎶 Играем аккорд: ");
		for (int stringNumber : stringNumbers) {
			if (stringNumber >= 1 && stringNumber <= stringCount) {
				strings[stringNumber - 1].play();
			}
		}
		System.out.println();
	}

	// Метод для натягивания всех струн
	public void tuneAll() {
		System.out.println("Настраиваем все струны гитары:");
		for (GuitarString string : strings) {
			string.tune();
		}
		System.out.println("Гитара настроена!\n");
	}

	// Метод для натягивания конкретной струны
	public void tuneString(int stringNumber) {
		if (stringNumber >= 1 && stringNumber <= stringCount) {
			strings[stringNumber - 1].tune();
		} else {
			System.out.println("Струны с номером " + stringNumber + " не существует!");
		}
	}

	// Метод для натягивания конкретной струны до определенной ноты
	public void tuneString(int stringNumber, String note) {
		if (stringNumber >= 1 && stringNumber <= stringCount) {
			strings[stringNumber - 1].tune(note);
		} else {
			System.out.println("Струны с номером " + stringNumber + " не существует!");
		}
	}

	// Метод для настройки гитары в альтернативный строй
	public void setCustomTuning(String... notes) {
		if (notes.length != stringCount) {
			System.out.println("Количество нот должно соответствовать количеству струн!");
			return;
		}

		System.out.println("Устанавливаем альтернативный строй:");
		for (int i = 0; i < stringCount; i++) {
			strings[i].tune(notes[i]);
		}
		System.out.println();
	}

	// Метод для проверки состояния гитары
	public void checkTuning() {
		System.out.println("Состояние гитары " + brand + " " + model + ":");
		for (GuitarString string : strings) {
			System.out.println("  " + string);
		}
		System.out.println();
	}

	// Геттеры
	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getStringCount() {
		return stringCount;
	}

	public String getStringInfo(int stringNumber) {
		if (stringNumber >= 1 && stringNumber <= stringCount) {
			return strings[stringNumber - 1].toString();
		}
		return "Струны с номером " + stringNumber + " не существует!";
	}

	@Override
	public String toString() {
		return "Гитара: " + brand + " " + model + " (" + stringCount + " струн)";
	}
}

// Демонстрационный класс
public class GuitarDemo {
	public static void main(String[] args) {
		System.out.println("=== Создание гитары ===");
		Guitar myGuitar = new Guitar("Gibson", "Les Paul", 6);
		System.out.println(myGuitar);

		System.out.println("=== Проверка состояния ===");
		myGuitar.checkTuning();

		System.out.println("=== Попытка игры на ненастроенной гитаре ===");
		myGuitar.playString(1);

		System.out.println("=== Настройка гитары ===");
		myGuitar.tuneAll();

		System.out.println("=== Игра на настроенной гитаре ===");
		myGuitar.play();

		System.out.println("=== Игра на отдельных струнах ===");
		myGuitar.playString(1);
		myGuitar.playString(3);
		myGuitar.playString(6);

		System.out.println("=== Игра аккордов ===");
		myGuitar.playChord(1, 2, 3); // Простой аккорд

		System.out.println("=== Альтернативная настройка ===");
		myGuitar.setCustomTuning("D", "A", "D", "G", "A", "D"); // Строй Drop D
		myGuitar.checkTuning();
		myGuitar.play();

		System.out.println("=== Создание другой гитары ===");
		Guitar acousticGuitar = new Guitar("Yamaha", "F310");
		System.out.println(acousticGuitar);
		acousticGuitar.tuneAll();
		acousticGuitar.play();

		System.out.println("=== Демонстрация работы со струнами ===");
		GuitarString singleString = new GuitarString(1, "A");
		singleString.play(); // Не натянута
		singleString.tune(); // Натягиваем
		singleString.play(); // Теперь звучит
	}
}
