import java.util.Objects;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private int stockQuantity;

	public Product(int id, String name, String description, double price, int stockQuantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	// Уменьшение количества товара на складе
	public void decreaseStock(int quantity) {
		if (quantity <= stockQuantity) {
			stockQuantity -= quantity;
		}
	}

	// Увеличение количества товара на складе
	public void increaseStock(int quantity) {
		stockQuantity += quantity;
	}

	public String toString() {
		return String.format("Product{id=%d, name='%s', description='%s', price=%.2f, stock=%d}",
				id, name, description, price, stockQuantity);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Product product = (Product) obj;
		return id == product.id;
	}

	public int hashCode() {
		return Objects.hash(id);
	}
}

class Client {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private boolean inBlacklist;

	public Client(int id, String firstName, String lastName, String email, String phone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.inBlacklist = false;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isInBlacklist() {
		return inBlacklist;
	}

	public void setInBlacklist(boolean inBlacklist) {
		this.inBlacklist = inBlacklist;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String toString() {
		return String.format("Client{id=%d, name='%s', email='%s', phone='%s', blacklist=%s}",
				id, getFullName(), email, phone, inBlacklist);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Client client = (Client) obj;
		return id == client.id;
	}

	public int hashCode() {
		return Objects.hash(id);
	}
}

class Order {
	private int id;
	private Client client;
	private Map<Product, Integer> products; // Товар -> Количество
	private LocalDateTime orderDate;
	private String status; // "PENDING", "PAID", "CANCELLED", "COMPLETED"
	private double totalAmount;

	public Order(int id, Client client) {
		this.id = id;
		this.client = client;
		this.products = new HashMap<>();
		this.orderDate = LocalDateTime.now();
		this.status = "PENDING";
		this.totalAmount = 0.0;
	}

	public int getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public Map<Product, Integer> getProducts() {
		return new HashMap<>(products);
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public String getStatus() {
		return status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	// Добавление товара в заказ
	public void addProduct(Product product, int quantity) {
		if (product.getStockQuantity() >= quantity) {
			products.put(product, products.getOrDefault(product, 0) + quantity);
			calculateTotalAmount();
		}
	}

	// Удаление товара из заказа
	public void removeProduct(Product product) {
		products.remove(product);
		calculateTotalAmount();
	}

	// Расчет общей суммы заказа
	private void calculateTotalAmount() {
		totalAmount = products.entrySet().stream()
				.mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
				.sum();
	}

	// Оплата заказа
	public boolean pay() {
		if ("PENDING".equals(status)) {
			status = "PAID";
			// Уменьшаем количество товаров на складе
			products.forEach((product, quantity) -> product.decreaseStock(quantity));
			return true;
		}
		return false;
	}

	// Отмена заказа
	public void cancel() {
		status = "CANCELLED";
	}

	// Завершение заказа
	public void complete() {
		if ("PAID".equals(status)) {
			status = "COMPLETED";
		}
	}

	@Override
	public String toString() {
		return String.format("Order{id=%d, client='%s', products=%d, total=%.2f, status='%s', date=%s}",
				id, client.getFullName(), products.size(), totalAmount, status, orderDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Order order = (Order) obj;
		return id == order.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

class Sale {
	private int id;
	private Order order;
	private LocalDateTime saleDate;
	private String paymentMethod;

	public Sale(int id, Order order, String paymentMethod) {
		this.id = id;
		this.order = order;
		this.paymentMethod = paymentMethod;
		this.saleDate = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public String toString() {
		return String.format("Sale{id=%d, orderId=%d, client='%s', amount=%.2f, method='%s', date=%s}",
				id, order.getId(), order.getClient().getFullName(),
				order.getTotalAmount(), paymentMethod, saleDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Sale sale = (Sale) obj;
		return id == sale.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

class OnlineStore {
	private Map<Integer, Product> products;
	private Map<Integer, Client> clients;
	private Map<Integer, Order> orders;
	private Map<Integer, Sale> sales;
	private Set<Client> blacklist;
	private int productIdCounter;
	private int clientIdCounter;
	private int orderIdCounter;
	private int saleIdCounter;

	public OnlineStore() {
		this.products = new HashMap<>();
		this.clients = new HashMap<>();
		this.orders = new HashMap<>();
		this.sales = new HashMap<>();
		this.blacklist = new HashSet<>();
		this.productIdCounter = 1;
		this.clientIdCounter = 1;
		this.orderIdCounter = 1;
		this.saleIdCounter = 1;
	}

	// === МЕТОДЫ АДМИНИСТРАТОРА ===

	// Добавление товара
	public void addProduct(String name, String description, double price, int stockQuantity) {
		Product product = new Product(productIdCounter++, name, description, price, stockQuantity);
		products.put(product.getId(), product);
		System.out.println("Товар добавлен: " + product);
	}

	// Регистрация продажи
	public void registerSale(int orderId, String paymentMethod) {
		Order order = orders.get(orderId);
		if (order != null && "PAID".equals(order.getStatus())) {
			Sale sale = new Sale(saleIdCounter++, order, paymentMethod);
			sales.put(sale.getId(), sale);
			order.complete();
			System.out.println("Продажа зарегистрирована: " + sale);
		} else {
			System.out.println("Ошибка: заказ не найден или не оплачен");
		}
	}

	// Добавление в черный список
	public void addToBlacklist(int clientId) {
		Client client = clients.get(clientId);
		if (client != null) {
			client.setInBlacklist(true);
			blacklist.add(client);
			System.out.println("Клиент добавлен в черный список: " + client.getFullName());
		}
	}

	// Удаление из черного списка
	public void removeFromBlacklist(int clientId) {
		Client client = clients.get(clientId);
		if (client != null) {
			client.setInBlacklist(false);
			blacklist.remove(client);
			System.out.println("Клиент удален из черного списка: " + client.getFullName());
		}
	}

	// === МЕТОДЫ КЛИЕНТА ===

	// Регистрация клиента
	public Client registerClient(String firstName, String lastName, String email, String phone) {
		Client client = new Client(clientIdCounter++, firstName, lastName, email, phone);
		clients.put(client.getId(), client);
		System.out.println("Клиент зарегистрирован: " + client);
		return client;
	}

	// Создание заказа
	public Order createOrder(int clientId) {
		Client client = clients.get(clientId);
		if (client != null && !client.isInBlacklist()) {
			Order order = new Order(orderIdCounter++, client);
			orders.put(order.getId(), order);
			System.out.println("Заказ создан: " + order);
			return order;
		} else {
			System.out.println("Ошибка: клиент не найден или в черном списке");
			return null;
		}
	}

	// Добавление товара в заказ
	public void addProductToOrder(int orderId, int productId, int quantity) {
		Order order = orders.get(orderId);
		Product product = products.get(productId);

		if (order != null && product != null) {
			order.addProduct(product, quantity);
			System.out.println("Товар добавлен в заказ: " + product.getName() + " x" + quantity);
		}
	}

	// Оплата заказа
	public boolean payOrder(int orderId) {
		Order order = orders.get(orderId);
		if (order != null && order.pay()) {
			System.out.println("Заказ оплачен: " + order);
			return true;
		} else {
			System.out.println("Ошибка оплаты заказа: " + orderId);
			return false;
		}
	}

	// === ГЕТТЕРЫ ДЛЯ ОТЧЕТНОСТИ ===

	public Collection<Product> getAllProducts() {
		return new ArrayList<>(products.values());
	}

	public Collection<Client> getAllClients() {
		return new ArrayList<>(clients.values());
	}

	public Collection<Order> getAllOrders() {
		return new ArrayList<>(orders.values());
	}

	public Collection<Sale> getAllSales() {
		return new ArrayList<>(sales.values());
	}

	public Set<Client> getBlacklist() {
		return new HashSet<>(blacklist);
	}

	@Override
	public String toString() {
		return String.format("OnlineStore{products=%d, clients=%d, orders=%d, sales=%d, blacklist=%d}",
				products.size(), clients.size(), orders.size(), sales.size(), blacklist.size());
	}
}

public class OnlineStoreDemo {
	public static void main(String[] args) {
		OnlineStore store = new OnlineStore();

		System.out.println("=== ИНИЦИАЛИЗАЦИЯ ИНТЕРНЕТ-МАГАЗИНА ===\n");

		// Администратор добавляет товары
		System.out.println("1. АДМИНИСТРАТОР ДОБАВЛЯЕТ ТОВАРЫ:");
		store.addProduct("iPhone 15", "Смартфон Apple", 999.99, 10);
		store.addProduct("Samsung Galaxy", "Смартфон Android", 799.99, 15);
		store.addProduct("MacBook Pro", "Ноутбук Apple", 1999.99, 5);

		// Регистрация клиентов
		System.out.println("\n2. РЕГИСТРАЦИЯ КЛИЕНТОВ:");
		Client client1 = store.registerClient("Иван", "Петров", "ivan@mail.com", "+79161234567");
		Client client2 = store.registerClient("Мария", "Сидорова", "maria@mail.com", "+79167654321");

		// Клиенты делают заказы
		System.out.println("\n3. КЛИЕНТЫ ДЕЛАЮТ ЗАКАЗЫ:");
		Order order1 = store.createOrder(client1.getId());
		store.addProductToOrder(order1.getId(), 1, 1); // iPhone
		store.addProductToOrder(order1.getId(), 3, 1); // MacBook

		Order order2 = store.createOrder(client2.getId());
		store.addProductToOrder(order2.getId(), 2, 2); // Samsung x2

		// Оплата заказов
		System.out.println("\n4. ОПЛАТА ЗАКАЗОВ:");
		store.payOrder(order1.getId());
		store.payOrder(order2.getId());

		// Администратор регистрирует продажи
		System.out.println("\n5. РЕГИСТРАЦИЯ ПРОДАЖ:");
		store.registerSale(order1.getId(), "CREDIT_CARD");
		store.registerSale(order2.getId(), "PAYPAL");

		// Ситуация с неплательщиком
		System.out.println("\n6. СИТУАЦИЯ С НЕПЛАТЕЛЬЩИКОМ:");
		Client client3 = store.registerClient("Алексей", "Неплательщиков", "alex@mail.com", "+79161112233");
		Order order3 = store.createOrder(client3.getId());
		store.addProductToOrder(order3.getId(), 1, 1);
		// Заказ не оплачен

		// Администратор добавляет в черный список
		System.out.println("\n7. ЧЕРНЫЙ СПИСОК:");
		store.addToBlacklist(client3.getId());

		// Попытка создать заказ из черного списка
		System.out.println("\n8. ПОПЫТКА ЗАКАЗА ИЗ ЧЕРНОГО СПИСКА:");
		Order failedOrder = store.createOrder(client3.getId());

		// Вывод отчетов
		System.out.println("\n9. ОТЧЕТЫ:");
		System.out.println("Общая информация: " + store);
		System.out.println("\nВсе товары:");
		store.getAllProducts().forEach(System.out::println);
		System.out.println("\nВсе клиенты:");
		store.getAllClients().forEach(System.out::println);
		System.out.println("\nВсе заказы:");
		store.getAllOrders().forEach(System.out::println);
		System.out.println("\nВсе продажи:");
		store.getAllSales().forEach(System.out::println);
		System.out.println("\nЧерный список:");
		store.getBlacklist().forEach(System.out::println);

		// Тестирование equals() и hashCode()
		System.out.println("\n10. ТЕСТИРОВАНИЕ EQUALS() И HASHCODE():");
		Product p1 = new Product(1, "Test", "Desc", 100, 10);
		Product p2 = new Product(1, "Test2", "Desc2", 200, 20);
		System.out.println("p1.equals(p2): " + p1.equals(p2));
		System.out.println("p1.hashCode() == p2.hashCode(): " + (p1.hashCode() == p2.hashCode()));
	}
}
