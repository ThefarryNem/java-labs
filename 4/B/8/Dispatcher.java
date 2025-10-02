class Car {
	private String licensePlate;
	private String model;
	private boolean isAvailable;

	public Car(String licensePlate, String model) {
		this.licensePlate = licensePlate;
		this.model = model;
		this.isAvailable = true;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getModel() {
		return model;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	@Override
	public String toString() {
		return "Автомобиль{" +
				"номерной знак='" + licensePlate + '\'' +
				", модель='" + model + '\'' +
				", доступен=" + isAvailable +
				'}';
	}
}

class Driver {
	private String name;
	private boolean isSuspended;

	public Driver(String name) {
		this.name = name;
		this.isSuspended = false;
	}

	public String getName() {
		return name;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void suspend() {
		isSuspended = true;
	}

	public void unsuspend() {
		isSuspended = false;
	}

	public RepairRequest requestRepair() {
		return new RepairRequest(this);
	}

	@Override
	public String toString() {
		return "Водитель{" +
				"имя='" + name + '\'' +
				", снят=" + isSuspended +
				'}';
	}
}

class Trip {
	private Driver driver;
	private Car car;
	private boolean isCompleted;
	private String carCondition;

	public Trip(Driver driver, Car car) {
		this.driver = driver;
		this.car = car;
		this.isCompleted = false;
	}

	public void completeTrip(String carCondition) {
		this.isCompleted = true;
		this.carCondition = carCondition;
		car.setAvailable(true);
	}

	@Override
	public String toString() {
		return "Маршрут{" +
				"водитель=" + driver.getName() +
				", автомобиль=" + car.getLicensePlate() +
				", завершён=" + isCompleted +
				", состояние автомобиля='" + carCondition + '\'' +
				'}';
	}
}

class RepairRequest {
	private Driver driver;

	public RepairRequest(Driver driver) {
		this.driver = driver;
	}

	public Driver getDriver() {
		return driver;
	}

	@Override
	public String toString() {
		return "ЗапросНаПочинку{" +
				"водитель=" + driver.getName() +
				'}';
	}
}

class Dispatcher {
	public static void main(String[] args) {
		Dispatcher dispatcher = new Dispatcher();
		Driver driver1 = new Driver("Козловский Евгений");
		Car car1 = new Car("ABC123", "Toyota");

		Trip trip1 = dispatcher.assignTrip(driver1, car1);
		if (trip1 != null) {
			trip1.completeTrip("Хорошее состояние");
			System.out.println(trip1);
		}

		RepairRequest repairRequest = driver1.requestRepair();
		System.out.println(repairRequest);

		dispatcher.suspendDriver(driver1);
	}

	public Trip assignTrip(Driver driver, Car car) {
		if (!driver.isSuspended() && car.isAvailable()) {
			car.setAvailable(false);
			return new Trip(driver, car);
		} else {
			System.out.println("Невозможно назначить маршрут. Водитель снят или транспорт занят.");
			return null;
		}
	}

	public void suspendDriver(Driver driver) {
		driver.suspend();
		System.out.println(driver.getName() + " был отстранён.");
	}

	public void unsuspendDriver(Driver driver) {
		driver.unsuspend();
		System.out.println(driver.getName() + " был назначен.");
	}
}
