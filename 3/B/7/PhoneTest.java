import java.util.*;

/**
 * Класс Phone описывает абонента телефонной связи
 */
class Phone {
	private int id;
	private String lastName;
	private String firstName;
	private String patronymic;
	private String address;
	private String creditCard;
	private double debit;
	private double credit;
	private int cityTime; // время городских разговоров
	private int intercityTime; // время междугородных разговоров

	// Конструктор
	public Phone(int id, String lastName, String firstName, String patronymic,
			String address, String creditCard, double debit, double credit,
			int cityTime, int intercityTime) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.address = address;
		this.creditCard = creditCard;
		this.debit = debit;
		this.credit = credit;
		this.cityTime = cityTime;
		this.intercityTime = intercityTime;
	}

	// Методы set/get
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getDebit() {
		return debit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCityTime(int cityTime) {
		this.cityTime = cityTime;
	}

	public int getCityTime() {
		return cityTime;
	}

	public void setIntercityTime(int intercityTime) {
		this.intercityTime = intercityTime;
	}

	public int getIntercityTime() {
		return intercityTime;
	}

	// toString()
	@Override
	public String toString() {
		return "Абонент {" +
				"id=" + id +
				", ФИО='" + lastName + " " + firstName + " " + patronymic + '\'' +
				", адрес='" + address + '\'' +
				", карта='" + creditCard + '\'' +
				", дебет=" + debit +
				", кредит=" + credit +
				", городское время=" + cityTime +
				", межгород=" + intercityTime +
				'}';
	}
}

/**
 * Основной класс программы
 */
public class PhoneTest {
	public static void main(String[] args) {
		// Массив абонентов
		Phone[] phones = {
				new Phone(1, "Иванов", "Иван", "Иванович", "Минск", "4251901 234516", 1000, 500, 120, 0),
				new Phone(2, "Петров", "Петр", "Петрович", "Брест", "9182342 123422", 2000, 1500, 80, 30),
				new Phone(3, "Сидоров", "Сидор", "Сидорович", "Гомель", "4216876 435219", 3000, 100, 200, 50),
				new Phone(4, "Алексеев", "Алексей", "Алексеевич", "Могилев", "442123 24236", 500, 250, 60, 0)
		};

		Scanner sc = new Scanner(System.in);
		System.out.print("Введите лимит времени для внутригородских разговоров: ");
		int limit = sc.nextInt();

		System.out.println("\n(a) Абоненты с городскими разговорами больше " + limit + ":");
		for (Phone p : phones) {
			if (p.getCityTime() > limit) {
				System.out.println(p);
			}
		}

		System.out.println("\n(b) Абоненты, которые пользовались междугородной связью:");
		for (Phone p : phones) {
			if (p.getIntercityTime() > 0) {
				System.out.println(p);
			}
		}

		System.out.println("\n(c) Абоненты в алфавитном порядке:");
		Arrays.sort(phones, Comparator.comparing(Phone::getLastName));
		for (Phone p : phones) {
			System.out.println(p);
		}
	}
}
