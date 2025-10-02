public class Main {
    public static void main(String[] args) {
        Book[] library = {
                new Book(1, "Java Programming", "John Doe", "TechBooks", 2018, 500, 39.99, "Hardcover"),
                new Book(2, "Python Basics", "Jane Smith", "CodePress", 2020, 300, 29.99, "Paperback"),
                new Book(3, "Advanced Java", "John Doe", "TechBooks", 2021, 600, 49.99, "Hardcover"),
                new Book(4, "Web Development", "Alice Brown", "WebBooks", 2019, 400, 34.99, "Paperback")
        };

        BookManager manager = new BookManager(library);

        System.out.println("Книги автора 'John Doe':");
        for (Book b : manager.booksByAuthor("John Doe")) {
            System.out.println(b);
        }

        System.out.println("\nКниги издательства 'TechBooks':");
        for (Book b : manager.booksByPublisher("TechBooks")) {
            System.out.println(b);
        }

        System.out.println("\nКниги после 2019 года:");
        for (Book b : manager.booksAfterYear(2019)) {
            System.out.println(b);
        }
    }
}
