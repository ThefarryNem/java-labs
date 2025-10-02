public class Book {
    private int id;
    private String title;
    private String authors;       // список авторов как строка
    private String publisher;
    private int year;
    private int pages;
    private double price;
    private String cover;

    // --- Конструктор ---
    public Book(int id, String title, String authors, String publisher, int year,
                int pages, double price, String cover) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.price = price;
        this.cover = cover;
    }

    // --- Сеттеры ---
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthors(String authors) { this.authors = authors; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setYear(int year) { this.year = year; }
    public void setPages(int pages) { this.pages = pages; }
    public void setPrice(double price) { this.price = price; }
    public void setCover(String cover) { this.cover = cover; }

    // --- Геттеры ---
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthors() { return authors; }
    public String getPublisher() { return publisher; }
    public int getYear() { return year; }
    public int getPages() { return pages; }
    public double getPrice() { return price; }
    public String getCover() { return cover; }

    // --- Красивый вывод ---
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", price=" + price +
                ", cover='" + cover + '\'' +
                '}';
    }
}
