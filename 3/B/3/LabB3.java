import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Patient {
	private int id;
	private String surname;
	private String name;
	private String patr;
	private String address;
	private String telephone;
	private int cardNum;
	private String illness;

	public Patient() {
	}

	public void setPatient(int id, String surname, String name, String patr, String address, String telephone,
			int cardNum, String illness) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.patr = patr;
		this.address = address;
		this.telephone = telephone;
		this.cardNum = cardNum;
		this.illness = illness;
	}

	public String getIllness() {
		return illness;
	}

	public int getCardNum() {
		return cardNum;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return id + " " + surname + " " + name + " " + patr + " " + address + " " + telephone + " " + cardNum + " "
				+ illness;
	}
}

public class LabB3 {
	public static void main(String[] args) {
		Patient[] patients = new Patient[3];
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "Cp866"));

			patients[0] = new Patient();
			patients[1] = new Patient();
			patients[2] = new Patient();

			patients[0].setPatient(1, "Петров", "Василий", "Иванович", "Новая, 35", "+375295556677", 10, "Простуда");
			// rip 😔
			patients[1].setPatient(2, "Саулич", "Евгения", "Ивановна", "Сурганова 37", "+375292148340", 17,
					"Миопия, гонартроз, варикоз");
			patients[2].setPatient(3, "Максимова", "Анна", "Иоанновна", "Республиканская, 25", "+375334567890", 3,
					"Депрессия");

			System.out.println("Введите диагноз для поиска: ");
			String searchIllness = reader.readLine().trim();

			boolean found = false;
			for (int i = 0; i < patients.length; i++) {
				String patientIllness = patients[i].getIllness().toLowerCase();
				String searchLower = searchIllness.toLowerCase();

				if (patientIllness.contains(searchLower)) {
					System.out.println("Найден: " + patients[i]);
					found = true;
				}
			}

			if (!found) {
				System.out.println("Пациенты с диагнозом '" + searchIllness + "' не найдены");
			}
			System.out.println("Проверка, находится ли номер карты в интервале от 5 до 15.");
			for (int i = 0; i < 3; i++) {
				if (patients[i].getCardNum() <= 15 && patients[i].getCardNum() >= 5) {
					System.out.println(
							"Номер карты пациента " + patients[i].getId() + " находится в заданном интервале.");
				} else {
					System.out.println("Номер карты пациента " + patients[i].getId() + " не в этом интервале");
				}
			}
		} catch (IOException e) {
			System.out.println("Ошибка ввода: " + e.getMessage());
		}
	}
}
