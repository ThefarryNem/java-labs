import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Класс книги
class Book {
	String title;
	String author;
	boolean isAvailable;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.isAvailable = true;
	}

	public void setAvailability(boolean status) {
		this.isAvailable = status;
	}

	@Override
	public String toString() {
		return "Книга: " + title + ", автор: " + author + ", доступна: " + isAvailable;
	}
}

// Класс читателя
class Reader {
	String name;
	boolean blacklisted;

	public Reader(String name) {
		this.name = name;
		this.blacklisted = false;
	}

	public boolean isBlacklisted() {
		return blacklisted;
	}

	public void setBlacklisted(boolean status) {
		this.blacklisted = status;
	}
}

// Каталог книг
class LibraryCatalog {
	List<Book> books;

	public LibraryCatalog() {
		books = new ArrayList<>();
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public Book findBookByTitle(String title) {
		for (Book book : books) {
			if (book.title.equalsIgnoreCase(title)) {
				return book;
			}
		}
		return null;
	}
}

// Библиотекарь
class Librarian {
	LibraryCatalog catalog;

	public Librarian(LibraryCatalog catalog) {
		this.catalog = catalog;
	}

	// Метод обновляет черный список читателя через переменную, переданную извне
	public void updateReaderBlackListStatus(Reader reader, boolean blackListStatus) {
		reader.setBlacklisted(blackListStatus);
		if (blackListStatus) {
			System.out.println("Читатель " + reader.name + " внесён в черный список.");
		} else {
			System.out.println("Читатель " + reader.name + " снят с черного списка.");
		}
	}

	// Метод выдачи книги с проверкой черного списка
	public void issueBook(Reader reader, String bookTitle, String issueType) {
		if (reader.isBlacklisted()) {
			System.out.println("Выдача невозможна! Читатель " + reader.name + " в черном списке.");
			return;
		}
		Book book = catalog.findBookByTitle(bookTitle);
		if (book == null) {
			System.out.println("Книга '" + bookTitle + "' не найдена в каталоге.");
			return;
		}
		if (!book.isAvailable) {
			System.out.println("Книга '" + bookTitle + "' сейчас недоступна.");
			return;
		}
		book.setAvailability(false);
		System.out.println("Книга '" + bookTitle + "' выдана читателю " + reader.name + " (" + issueType + ").");
	}
}

class LibrarySystemWithInput {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Создаем каталог
		LibraryCatalog catalog = new LibraryCatalog();
		catalog.addBook(new Book("Война и мир", "Толстой"));
		catalog.addBook(new Book("Преступление и наказание", "Достоевский"));

		// Создаем читателя
		System.out.print("Введите имя читателя: ");
		String readerName = scanner.nextLine();
		Reader reader = new Reader(readerName);

		// Создаем библиотекаря
		Librarian librarian = new Librarian(catalog);

		// Пример выдачи книги до изменения статуса черного списка
		librarian.issueBook(reader, "Война и мир", "на абонемент");

		// Ввод от пользователя: добавлять ли в черный список?
		System.out.print("Добавить читателя в черный список? (да/нет): ");
		boolean blackListStatus;
		while (true) {
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.equals("да")) {
				blackListStatus = true;
				break;
			} else if (input.equals("нет")) {
				blackListStatus = false;
				break;
			} else {
				System.out.print("Некорректный ввод. Пожалуйста, введите true или false: ");
			}
		}

		// Обновляем статус читателя
		librarian.updateReaderBlackListStatus(reader, blackListStatus);

		// Пытаемся выдать книгу после обновления
		librarian.issueBook(reader, "Преступление и наказание", "в читальный зал");

		scanner.close();
	}
}
