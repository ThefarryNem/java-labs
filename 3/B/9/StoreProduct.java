public class StoreProduct {
	private int id;
	private String name;
	private String upc;
	private String manufacturer;
	private double price;
	private int shelfLife; // в днях
	private int quantity;

	// Конструктор
	public StoreProduct(int id, String name, String upc, String manufacturer, double price, int shelfLife,
			int quantity) {
		this.id = id;
		this.name = name;
		this.upc = upc;
		this.manufacturer = manufacturer;
		this.price = price;
		this.shelfLife = shelfLife;
		this.quantity = quantity;
	}

	// Сеттеры
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUpc() {
		return upc;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public double getPrice() {
		return price;
	}

	public int getShelfLife() {
		return shelfLife;
	}

	public int getQuantity() {
		return quantity;
	}

	// toString
	@Override
	public String toString() {
		return String.format(
				"Product{id=%d, name='%s', UPC='%s', manufacturer='%s', price=%.2f, shelfLife=%d, quantity=%d}",
				id, name, upc, manufacturer, price, shelfLife, quantity);
	}

	public static void main(String[] args) {
		ProductManager manager = new ProductManager();

		manager.addProduct(new StoreProduct(1, "Молоко", "123456", "Простоквашино", 2.5, 7, 100));
		manager.addProduct(new StoreProduct(2, "Хлеб", "654321", "Домашний", 1.2, 3, 50));
		manager.addProduct(new StoreProduct(3, "Молоко", "789012", "Савушкин", 2.0, 10, 80));
		manager.addProduct(new StoreProduct(4, "Сыр", "345678", "Беллакт", 5.5, 30, 40));

		System.out.println("a) Товары с наименованием 'Молоко':");
		manager.printList(manager.findByName("Молоко"));

		System.out.println("\nb) Товары 'Молоко' с ценой <= 2.2:");
		manager.printList(manager.findByNameAndPrice("Молоко", 2.2));

		System.out.println("\nc) Товары с сроком хранения > 7 дней:");
		manager.printList(manager.findByShelfLifeGreaterThan(7));
	}
}
