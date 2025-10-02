import java.util.ArrayList;
import java.util.List;

public class ProductManager {
	private List<Product> products;

	public ProductManager() {
		products = new ArrayList<>();
	}

	public void addProduct(Product p) {
		products.add(p);
	}

	// a) Поиск по наименованию
	public List<Product> findByName(String name) {
		List<Product> result = new ArrayList<>();
		for (Product p : products) {
			if (p.getName().equalsIgnoreCase(name)) {
				result.add(p);
			}
		}
		return result;
	}

	// b) Поиск по наименованию и цене
	public List<Product> findByNameAndPrice(String name, double maxPrice) {
		List<Product> result = new ArrayList<>();
		for (Product p : products) {
			if (p.getName().equalsIgnoreCase(name) && p.getPrice() <= maxPrice) {
				result.add(p);
			}
		}
		return result;
	}

	// c) Поиск по сроку хранения
	public List<Product> findByShelfLifeGreaterThan(int minShelfLife) {
		List<Product> result = new ArrayList<>();
		for (Product p : products) {
			if (p.getShelfLife() > minShelfLife) {
				result.add(p);
			}
		}
		return result;
	}

	// Вывод списка
	public void printList(List<Product> list) {
		for (Product p : list) {
			System.out.println(p);
		}
	}
}
