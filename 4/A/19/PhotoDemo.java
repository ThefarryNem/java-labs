import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

class Photo {
	private String name;

	public Photo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void displayInfo() {
		System.out.println("Название фотографии: " + name);
	}

	public String toString() {
		return "Photo{name='" + name + "}";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Photo that = (Photo) obj;
		return Objects.equals(name, that.name);
	}

	public int hashCode() {
		return Objects.hash(name);
	}
}

class Album {
	private String title;
	private List<Photo> photographs;

	public Album(String title) {
		this.title = title;
		this.photographs = new ArrayList<>();
	}

	// Метод для задания названия фотографии по индексу
	public void setPhotoTitle(int index, String newTitle) {
		if (index >= 0 && index < photographs.size()) {
			photographs.get(index).setName(newTitle);
		} else {
			System.out.println("Ошибка: неверный индекс фотографии");
		}
	}

	// Метод для дополнения фотоальбома фотографией
	public void addPhotograph(Photo photo) {
		photographs.add(photo);
	}

	// Перегруженный метод для быстрого добавления фотографии
	public void addPhotograph(String title) {
		photographs.add(new Photo(title));
	}

	// Метод для вывода количества фотографий
	public void displayPhotoCount() {
		System.out.println("Количество фотографий в альбоме '" + title + "': " + photographs.size());
	}

	public int getPhotoCount() {
		return photographs.size();
	}

	// Метод для вывода информации обо всех фотографиях
	public void displayAllPhotos() {
		System.out.println("=== Фотоальбом: " + title + " ===");
		for (int i = 0; i < photographs.size(); i++) {
			System.out.print((i + 1) + ". ");
			photographs.get(i).displayInfo();
		}
	}

	// Геттеры
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Photo> getPhotographs() {
		return new ArrayList<>(photographs);
	}

	public String toString() {
		return "Album{title='" + title + "', photographs=" + photographs +
				", photoCount=" + photographs.size() + "}";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Album that = (Album) obj;
		return Objects.equals(title, that.title) &&
				Objects.equals(photographs, that.photographs);
	}

	// Переопределение метода hashCode()
	@Override
	public int hashCode() {
		return Objects.hash(title, photographs);
	}
}

public class PhotoDemo {
	public static void main(String[] args) {
		// Создаем фотоальбом
		Album vacationAlbum = new Album("Летний отпуск 2024");

		// Добавляем фотографии разными способами
		vacationAlbum.addPhotograph("Пляж утром");
		vacationAlbum.addPhotograph("Закат на море");
		vacationAlbum.addPhotograph(new Photo("Горный поход"));

		// Тестируем основные методы
		System.out.println("=== Тестирование основных методов ===");
		vacationAlbum.displayPhotoCount();
		vacationAlbum.displayAllPhotos();

		// Изменяем название фотографии
		System.out.println("\n=== Изменение названия фотографии ===");
		vacationAlbum.setPhotoTitle(0, "Утренний пляж с зонтиками");
		vacationAlbum.displayAllPhotos();

		// Тестируем методы toString()
		System.out.println("\n=== Тестирование toString() ===");
		System.out.println("Фотоальбом: " + vacationAlbum.toString());
		System.out.println("Первая фотография: " + vacationAlbum.getPhotographs().get(0).toString());

		// Тестируем методы equals() и hashCode()
		System.out.println("\n=== Тестирование equals() и hashCode() ===");

		// Создаем идентичный фотоальбом
		Album vacationAlbum2 = new Album("Летний отпуск 2024");
		vacationAlbum2.addPhotograph("Утренний пляж с зонтиками");
		vacationAlbum2.addPhotograph("Закат на море");
		vacationAlbum2.addPhotograph(new Photo("Горный поход"));

		// Создаем другой фотоальбом
		Album weddingAlbum = new Album("Свадьба");
		weddingAlbum.addPhotograph("Молодожены у фонтана");

		// Создаем идентичные фотографии
		Photo photo1 = new Photo("Тест");
		Photo photo2 = new Photo("Тест");
		Photo photo3 = new Photo("Другая");

		// Тестируем equals() для фотоальбомов
		System.out.println("vacationAlbum.equals(vacationAlbum2): " + vacationAlbum.equals(vacationAlbum2));
		System.out.println("vacationAlbum.equals(weddingAlbum): " + vacationAlbum.equals(weddingAlbum));
		System.out.println("vacationAlbum.equals(null): " + vacationAlbum.equals(null));
		System.out.println("vacationAlbum.equals(vacationAlbum): " + vacationAlbum.equals(vacationAlbum));

		// Тестируем equals() для фотографий
		System.out.println("photo1.equals(photo2): " + photo1.equals(photo2));
		System.out.println("photo1.equals(photo3): " + photo1.equals(photo3));

		// Тестируем hashCode()
		System.out.println("\n=== Тестирование hashCode() ===");
		System.out.println("vacationAlbum.hashCode(): " + vacationAlbum.hashCode());
		System.out.println("vacationAlbum2.hashCode(): " + vacationAlbum2.hashCode());
		System.out.println("weddingAlbum.hashCode(): " + weddingAlbum.hashCode());

		System.out.println("photo1.hashCode(): " + photo1.hashCode());
		System.out.println("photo2.hashCode(): " + photo2.hashCode());
		System.out.println("photo3.hashCode(): " + photo3.hashCode());

		// Проверяем согласованность equals() и hashCode()
		System.out.println("\n=== Проверка согласованности ===");
		System.out.println(
				"vacationAlbum.equals(vacationAlbum2) && vacationAlbum.hashCode() == vacationAlbum2.hashCode(): " +
						(vacationAlbum.equals(vacationAlbum2)
								&& vacationAlbum.hashCode() == vacationAlbum2.hashCode()));
		System.out.println("photo1.equals(photo2) && photo1.hashCode() == photo2.hashCode(): " +
				(photo1.equals(photo2) && photo1.hashCode() == photo2.hashCode()));

		// Дополнительное тестирование методов
		System.out.println("\n=== Дополнительное тестирование ===");
		System.out.println("Количество фотографий (через геттер): " + vacationAlbum.getPhotoCount());

		// Добавляем еще фотографий
		vacationAlbum.addPhotograph("Ночной город");
		vacationAlbum.displayPhotoCount();
	}
}
