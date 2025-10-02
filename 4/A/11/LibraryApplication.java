import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

// Базовый класс для всех сущностей библиотеки
abstract class LibraryEntity {
	protected final String id;
	protected final String title;

	public LibraryEntity(String id, String title) {
		this.id = Objects.requireNonNull(id, "ID cannot be null");
		this.title = Objects.requireNonNull(title, "Title cannot be null");
	}

	// Геттеры - принадлежат базовому классу, так как нужны всем наследникам
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	// Абстрактные методы - должны быть реализованы в наследниках
	public abstract String getDescription();

	// equals и hashCode в базовом классе для избежания дублирования кода
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LibraryEntity that = (LibraryEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return String.format("%s{id='%s', title='%s'}",
				getClass().getSimpleName(), id, title);
	}
}

// Класс Книга - принадлежит доменной модели библиотеки
class Book extends LibraryEntity {
	private final String author;
	private final String isbn;
	private final int publicationYear;
	private final Genre genre;

	public enum Genre {
		FICTION, SCIENCE, HISTORY, BIOGRAPHY, TECHNOLOGY
	}

	public Book(String id, String title, String author, String isbn,
			int publicationYear, Genre genre) {
		super(id, title);
		this.author = Objects.requireNonNull(author, "Author cannot be null");
		this.isbn = Objects.requireNonNull(isbn, "ISBN cannot be null");
		this.publicationYear = publicationYear;
		this.genre = Objects.requireNonNull(genre, "Genre cannot be null");
	}

	// Геттеры принадлежат классу Book - предоставляют доступ к специфичным данным
	// книги
	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public Genre getGenre() {
		return genre;
	}

	// Метод проверки доступности для выдачи - бизнес-логика книги
	public boolean isAvailableForBorrowing() {
		return publicationYear >= 1900; // Пример бизнес-правила
	}

	@Override
	public String getDescription() {
		return String.format("Book '%s' by %s (%d, %s)",
				title, author, publicationYear, genre);
	}

	// Переопределение equals с учетом специфичных полей Book
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Book book = (Book) o;
		return publicationYear == book.publicationYear &&
				Objects.equals(author, book.author) &&
				Objects.equals(isbn, book.isbn) &&
				genre == book.genre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), author, isbn, publicationYear, genre);
	}

	@Override
	public String toString() {
		return String.format("Book{id='%s', title='%s', author='%s', isbn='%s', year=%d, genre=%s}",
				id, title, author, isbn, publicationYear, genre);
	}
}

// Класс Читатель - представляет пользователя библиотеки
class Reader extends LibraryEntity {
	private final String email;
	private final String phone;
	private final LocalDate registrationDate;
	private int borrowedBooksCount;

	public Reader(String id, String name, String email, String phone) {
		super(id, name);
		this.email = Objects.requireNonNull(email, "Email cannot be null");
		this.phone = Objects.requireNonNull(phone, "Phone cannot be null");
		this.registrationDate = LocalDate.now();
		this.borrowedBooksCount = 0;
	}

	// Геттеры принадлежат Reader - предоставляют данные читателя
	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public int getBorrowedBooksCount() {
		return borrowedBooksCount;
	}

	// Методы управления книгами - бизнес-логика читателя
	public void borrowBook() {
		borrowedBooksCount++;
	}

	public void returnBook() {
		if (borrowedBooksCount > 0) {
			borrowedBooksCount--;
		}
	}

	public boolean canBorrowMoreBooks(int maxBooks) {
		return borrowedBooksCount < maxBooks;
	}

	@Override
	public String getDescription() {
		return String.format("Reader: %s (Email: %s, Books borrowed: %d)",
				title, email, borrowedBooksCount);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Reader reader = (Reader) o;
		return borrowedBooksCount == reader.borrowedBooksCount &&
				Objects.equals(email, reader.email) &&
				Objects.equals(phone, reader.phone) &&
				Objects.equals(registrationDate, reader.registrationDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), email, phone, registrationDate, borrowedBooksCount);
	}

	@Override
	public String toString() {
		return String.format("Reader{id='%s', name='%s', email='%s', phone='%s', registered=%s, booksCount=%d}",
				id, title, email, phone, registrationDate, borrowedBooksCount);
	}
}

// Сервисный класс для управления библиотекой - содержит бизнес-логику
// приложения
class LibraryService {
	private final Map<String, Book> books;
	private final Map<String, Reader> readers;
	private final Map<String, String> borrowedBooks; // bookId -> readerId

	public LibraryService() {
		this.books = new HashMap<>();
		this.readers = new HashMap<>();
		this.borrowedBooks = new HashMap<>();
	}

	// Методы управления книгами - принадлежат сервису как бизнес-операции
	public void addBook(Book book) {
		books.put(book.getId(), book);
	}

	public void addReader(Reader reader) {
		readers.put(reader.getId(), reader);
	}

	public boolean borrowBook(String bookId, String readerId) {
		Book book = books.get(bookId);
		Reader reader = readers.get(readerId);

		if (book == null || reader == null) {
			return false;
		}

		if (borrowedBooks.containsKey(bookId)) {
			return false; // Книга уже выдана
		}

		if (!reader.canBorrowMoreBooks(3)) { // Максимум 3 книги
			return false;
		}

		if (!book.isAvailableForBorrowing()) {
			return false;
		}

		borrowedBooks.put(bookId, readerId);
		reader.borrowBook();
		return true;
	}

	public boolean returnBook(String bookId) {
		String readerId = borrowedBooks.remove(bookId);
		if (readerId != null) {
			Reader reader = readers.get(readerId);
			if (reader != null) {
				reader.returnBook();
			}
			return true;
		}
		return false;
	}

	// Методы поиска - бизнес-функциональность сервиса
	public List<Book> findBooksByAuthor(String author) {
		return books.values().stream()
				.filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Book> findAvailableBooks() {
		return books.values().stream()
				.filter(book -> !borrowedBooks.containsKey(book.getId()))
				.collect(Collectors.toList());
	}

	public Optional<Reader> findReaderByBook(String bookId) {
		String readerId = borrowedBooks.get(bookId);
		return Optional.ofNullable(readers.get(readerId));
	}

	// Геттеры для доступа к данным
	public Collection<Book> getAllBooks() {
		return Collections.unmodifiableCollection(books.values());
	}

	public Collection<Reader> getAllReaders() {
		return Collections.unmodifiableCollection(readers.values());
	}
}

// Главный класс приложения
public class LibraryApplication {
	public static void main(String[] args) {
		LibraryService library = new LibraryService();

		// Создание и добавление книг
		Book book1 = new Book("B001", "Java Programming", "John Smith",
				"978-0134685991", 2022, Book.Genre.TECHNOLOGY);
		Book book2 = new Book("B002", "Effective Java", "Joshua Bloch",
				"978-0134685992", 2018, Book.Genre.TECHNOLOGY);
		Book book3 = new Book("B003", "Clean Code", "Robert Martin",
				"978-0134685993", 2008, Book.Genre.TECHNOLOGY);

		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);

		// Создание и добавление читателей
		Reader reader1 = new Reader("R001", "Alice Johnson", "alice@email.com", "+123456789");
		Reader reader2 = new Reader("R002", "Bob Wilson", "bob@email.com", "+987654321");

		library.addReader(reader1);
		library.addReader(reader2);

		// Демонстрация работы методов
		System.out.println("=== Демонстрация методов equals(), hashCode(), toString() ===");

		// Тестирование equals и hashCode
		Book book1Copy = new Book("B001", "Java Programming", "John Smith",
				"978-0134685991", 2022, Book.Genre.TECHNOLOGY);

		System.out.println("book1.equals(book1Copy): " + book1.equals(book1Copy));
		System.out.println("book1.hashCode() == book1Copy.hashCode(): " +
				(book1.hashCode() == book1Copy.hashCode()));

		// Демонстрация toString
		System.out.println("\nBooks:");
		library.getAllBooks().forEach(System.out::println);

		System.out.println("\nReaders:");
		library.getAllReaders().forEach(System.out::println);

		// Демонстрация бизнес-логики
		System.out.println("\n=== Демонстрация бизнес-логики ===");

		// Выдача книг
		boolean success1 = library.borrowBook("B001", "R001");
		System.out.println("Book B001 borrowed by R001: " + success1);

		boolean success2 = library.borrowBook("B002", "R001");
		System.out.println("Book B002 borrowed by R001: " + success2);

		// Поиск книг по автору
		System.out.println("\nBooks by 'Joshua Bloch':");
		library.findBooksByAuthor("Joshua Bloch").forEach(System.out::println);

		// Доступные книги
		System.out.println("\nAvailable books:");
		library.findAvailableBooks().forEach(System.out::println);

		// Поиск читателя по книге
		library.findReaderByBook("B001")
				.ifPresent(reader -> System.out.println("\nBook B001 is borrowed by: " + reader.getTitle()));
	}
}
