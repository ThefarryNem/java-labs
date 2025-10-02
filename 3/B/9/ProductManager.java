import java.util.ArrayList;
import java.util.List;

public class ProductManager {
	private List<StoreProduct> products;

	public ProductManager() {
		products = new ArrayList<>();
	}

	public void addProduct(StoreProduct p) {
		products.add(p);
	}

	// a) Поиск по наименованию
	public List<StoreProduct> findByName(String name) {
		List<StoreProduct> result = new ArrayList<>();
		for (StoreProduct p : products) {
			if (p.getName().equalsIgnoreCase(name)) {
				result.add(p);
			}
		}
		return result;
	}

	// b) Поиск по наименованию и цене
	public List<StoreProduct> findByNameAndPrice(String name, double maxPrice) {
		List<StoreProduct> result = new ArrayList<>();
		for (StoreProduct p : products) {
			if (p.getName().equalsIgnoreCase(name) && p.getPrice() <= maxPrice) {
				result.add(p);
			}
		}
		return result;
	}

	// c) Поиск по сроку хранения
	public List<StoreProduct> findByShelfLifeGreaterThan(int minShelfLife) {
		List<StoreProduct> result = new ArrayList<>();
		for (StoreProduct p : products) {
			if (p.getShelfLife() > minShelfLife) {
				result.add(p);
			}
		}
		return result;
	}

	// Вывод списка
	public void printList(List<StoreProduct> list) {
		for (StoreProduct p : list) {
			System.out.println(p);
		}
	}
}
