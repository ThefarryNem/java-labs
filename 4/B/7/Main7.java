import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Класс Услуга
class Usluga {
	private String name;
	private double price;

	public Usluga(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Услуга{" + name + ", цена=" + price + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Usluga))
			return false;
		Usluga usluga = (Usluga) o;
		return Double.compare(usluga.price, price) == 0 &&
				Objects.equals(name, usluga.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, price);
	}
}

// Класс Счет
class Schet {
	private double dolg;

	public void dobavitRasxod(double summa) {
		dolg += summa;
	}

	public void oplata(double summa) {
		dolg -= summa;
		if (dolg < 0)
			dolg = 0;
	}

	public double getDolg() {
		return dolg;
	}

	@Override
	public String toString() {
		return "Счет {долг=" + dolg + "}";
	}
}

// Класс Абонент
class Abonent {
	private String name;
	private String phoneNumber;
	private boolean active;
	private Schet schet;
	private List<Usluga> uslugi;

	public Abonent(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.active = true;
		this.schet = new Schet();
		this.uslugi = new ArrayList<>();
	}

	public void oplatitSchet(double summa) {
		schet.oplata(summa);
		System.out.println(name + " оплатил " + summa + " руб.");
	}

	public void otkazatsyaOtUslug() {
		uslugi.clear();
		System.out.println(name + " отказался от всех услуг.");
	}

	public void dobavitUslugu(Usluga usluga) {
		uslugi.add(usluga);
		schet.dobavitRasxod(usluga.getPrice());
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Schet getSchet() {
		return schet;
	}

	public boolean isActive() {
		return active;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Абонент{" + name + ", номер=" + phoneNumber +
				", активен=" + active + ", " + schet +
				", услуги=" + uslugi + "}";
	}
}

// Класс Администратор
class Administrator {

	public void smenitNomer(Abonent abonent, String newNumber) {
		abonent.setPhoneNumber(newNumber);
		System.out.println("Администратор сменил номер абоненту " + newNumber);
	}

	public void otklyuchitZaNeuplatu(Abonent abonent) {
		if (abonent.getSchet().getDolg() > 0) {
			abonent.setActive(false);
			System.out.println("Абонент отключен за неуплату.");
		}
	}

	public void vklyuchitAbonenta(Abonent abonent) {
		abonent.setActive(true);
		System.out.println("Абонент снова подключен.");
	}
}

// Тест
public class Main7 {
	public static void main(String[] args) {
		Abonent ivan = new Abonent("Иван", "123-45-67");
		Administrator admin = new Administrator();

		// Подключение услуг
		ivan.dobavitUslugu(new Usluga("Интернет", 500));
		ivan.dobavitUslugu(new Usluga("Голосовая почта", 100));
		System.out.println(ivan);

		// Проверка отключения
		admin.otklyuchitZaNeuplatu(ivan);
		System.out.println(ivan);

		// Оплата
		ivan.oplatitSchet(600);
		admin.vklyuchitAbonenta(ivan);
		System.out.println(ivan);

		// Смена номера
		admin.smenitNomer(ivan, "555-77-88");
		System.out.println(ivan);

		// Отказ от услуг
		ivan.otkazatsyaOtUslug();
		System.out.println(ivan);
	}
}
