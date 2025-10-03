class House {
	private int id;
	private int apartmentNumber; // Номер квартиры
	private double area; // Площадь
	private int floor; // Этаж
	private int roomCount; // Количество комнат
	private String street; // Улица
	private String buildingType; // Тип здания
	private int serviceLife; // Срок эксплуатации (лет)

	// Конструктор
	public House(int id, int apartmentNumber, double area, int floor, int roomCount,
			String street, String buildingType, int serviceLife) {
		this.id = id;
		this.apartmentNumber = apartmentNumber;
		this.area = area;
		this.floor = floor;
		this.roomCount = roomCount;
		this.street = street;
		this.buildingType = buildingType;
		this.serviceLife = serviceLife;
	}

	// Геттеры для необходимой информации
	public int getRoomCount() {
		return roomCount;
	}

	public int getFloor() {
		return floor;
	}

	public double getArea() {
		return area;
	}

	public String toString() {
		return String.format("ID: %d, Кв.№: %d, Пл.: %.2f, Этаж: %d, Комнат: %d, Улица: %s, Тип: %s, Срок: %d лет",
				id, apartmentNumber, area, floor, roomCount, street, buildingType, serviceLife);
	}

	public static void main(String[] args) {
		House[] houses = {
				new House(1, 101, 45.5, 3, 2, "Ленина", "Панельный", 30),
				new House(2, 102, 75.0, 5, 3, "Пушкина", "Монолитный", 10),
				new House(3, 103, 60.0, 2, 3, "Ленина", "Кирпичный", 20),
				new House(4, 201, 30.0, 1, 1, "Гагарина", "Панельный", 40),
				new House(5, 202, 85.5, 7, 4, "Пушкина", "Монолитный", 15)
		};

		int searchRoomCount = 3;
		int floorFrom = 2;
		int floorTo = 5;
		double minArea = 50.0;

		System.out.println("a) Квартиры с " + searchRoomCount + " комнат(ами):");
		for (House h : houses) {
			if (h.getRoomCount() == searchRoomCount) {
				System.out.println(h);
			}
		}

		System.out.println(
				"\nb) Квартиры с " + searchRoomCount + " комнат(ами), этаж от " + floorFrom + " до " + floorTo + ":");
		for (House h : houses) {
			if (h.getRoomCount() == searchRoomCount && h.getFloor() >= floorFrom && h.getFloor() <= floorTo) {
				System.out.println(h);
			}
		}

		System.out.println("\nc) Квартиры с площадью больше " + minArea + ":");
		for (House h : houses) {
			if (h.getArea() > minArea) {
				System.out.println(h);
			}
		}
	}
}
