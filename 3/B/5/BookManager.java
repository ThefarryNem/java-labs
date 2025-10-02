import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private Book[] books;

    public BookManager(Book[] books) {
        this.books = books;
    }

    // Книги заданного автора
    public List<Book> booksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthors().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    // Книги заданного издательства
    public List<Book> booksByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(b);
            }
        }
        return result;
    }

    // Книги после заданного года
    public List<Book> booksAfterYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getYear() > year) {
                result.add(b);
            }
        }
        return result;
    }
}
