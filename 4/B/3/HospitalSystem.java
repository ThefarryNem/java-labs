import java.util.*;
import java.time.LocalDate;

// Перечисление для статуса пациента
enum PatientStatus {
	IN_TREATMENT,
	DISCHARGED_CURED,
	DISCHARGED_VIOLATION,
	DISCHARGED_OTHER
}

// Перечисление для типа назначения
enum AppointmentType {
	PROCEDURE,
	MEDICATION,
	OPERATION
}

// Перечисление для статуса назначения
enum AppointmentStatus {
	PRESCRIBED,
	IN_PROGRESS,
	COMPLETED,
	CANCELLED
}

// Класс Больница
class Hospital {
	private String name;
	private List<Doctor> doctors;
	private List<Patient> patients;
	private List<Nurse> nurses;

	public Hospital(String name) {
		this.name = name;
		this.doctors = new ArrayList<>();
		this.patients = new ArrayList<>();
		this.nurses = new ArrayList<>();
	}

	// Геттеры
	public String getName() {
		return name;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public List<Nurse> getNurses() {
		return nurses;
	}

	// Методы для добавления персонала и пациентов
	public void addDoctor(Doctor doctor) {
		doctors.add(doctor);
	}

	public void addNurse(Nurse nurse) {
		nurses.add(nurse);
	}

	public void admitPatient(Patient patient) {
		patients.add(patient);
	}

	// Поиск врача по ID
	public Doctor findDoctorById(int id) {
		return doctors.stream()
				.filter(d -> d.getId() == id)
				.findFirst()
				.orElse(null);
	}

	// Поиск медсестры по ID
	public Nurse findNurseById(int id) {
		return nurses.stream()
				.filter(n -> n.getId() == id)
				.findFirst()
				.orElse(null);
	}

	// Получение списка пациентов в лечении
	public List<Patient> getPatientsInTreatment() {
		return patients.stream()
				.filter(p -> p.getStatus() == PatientStatus.IN_TREATMENT)
				.toList();
	}
}

// Абстрактный класс Сотрудник
abstract class Employee {
	private static int nextId = 1;

	protected int id;
	protected String firstName;
	protected String lastName;
	protected String position;

	public Employee(String firstName, String lastName, String position) {
		this.id = nextId++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPosition() {
		return position;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}

// Класс Врач
class Doctor extends Employee {
	private String specialization;

	public Doctor(String firstName, String lastName, String specialization) {
		super(firstName, lastName, "Врач");
		this.specialization = specialization;
	}

	public String getSpecialization() {
		return specialization;
	}

	// Врач назначает лечение пациенту
	public Appointment prescribeTreatment(Patient patient, AppointmentType type,
			String description, LocalDate date) {
		Appointment appointment = new Appointment(this, patient, type, description, date);
		patient.addAppointment(appointment);
		return appointment;
	}

	// Врач может выполнить назначение
	public void performAppointment(Appointment appointment) {
		appointment.perform(this);
	}

	// Врач выписывает пациента
	public void dischargePatient(Patient patient, PatientStatus dischargeReason, String notes) {
		patient.discharge(dischargeReason, notes);
	}
}

// Класс Медсестра
class Nurse extends Employee {
	public Nurse(String firstName, String lastName) {
		super(firstName, lastName, "Медсестра");
	}

	// Медсестра выполняет назначение
	public void performAppointment(Appointment appointment) {
		appointment.perform(this);
	}
}

// Класс Пациент
class Patient {
	private static int nextId = 1;

	private int id;
	private String firstName;
	private String lastName;
	private LocalDate admissionDate;
	private PatientStatus status;
	private Doctor attendingDoctor;
	private List<Appointment> appointments;
	private String dischargeNotes;

	public Patient(String firstName, String lastName, LocalDate admissionDate) {
		this.id = nextId++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admissionDate = admissionDate;
		this.status = PatientStatus.IN_TREATMENT;
		this.appointments = new ArrayList<>();
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public PatientStatus getStatus() {
		return status;
	}

	public Doctor getAttendingDoctor() {
		return attendingDoctor;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public String getDischargeNotes() {
		return dischargeNotes;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	// Сеттер для лечащего врача
	public void setAttendingDoctor(Doctor doctor) {
		this.attendingDoctor = doctor;
	}

	// Добавление назначения
	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}

	// Выписка пациента
	public void discharge(PatientStatus dischargeReason, String notes) {
		this.status = dischargeReason;
		this.dischargeNotes = notes;

		// Отмена всех невыполненных назначений
		for (Appointment appointment : appointments) {
			if (appointment.getStatus() == AppointmentStatus.PRESCRIBED ||
					appointment.getStatus() == AppointmentStatus.IN_PROGRESS) {
				appointment.cancel();
			}
		}
	}

	// Получение активных назначений
	public List<Appointment> getActiveAppointments() {
		return appointments.stream()
				.filter(a -> a.getStatus() == AppointmentStatus.PRESCRIBED ||
						a.getStatus() == AppointmentStatus.IN_PROGRESS)
				.toList();
	}

	// Получение выполненных назначений
	public List<Appointment> getCompletedAppointments() {
		return appointments.stream()
				.filter(a -> a.getStatus() == AppointmentStatus.COMPLETED)
				.toList();
	}
}

// Класс Назначение
class Appointment {
	private static int nextId = 1;

	private int id;
	private Doctor prescribingDoctor;
	private Patient patient;
	private AppointmentType type;
	private String description;
	private LocalDate prescribedDate;
	private LocalDate performedDate;
	private Employee performedBy;
	private AppointmentStatus status;
	private String notes;

	public Appointment(Doctor prescribingDoctor, Patient patient, AppointmentType type,
			String description, LocalDate prescribedDate) {
		this.id = nextId++;
		this.prescribingDoctor = prescribingDoctor;
		this.patient = patient;
		this.type = type;
		this.description = description;
		this.prescribedDate = prescribedDate;
		this.status = AppointmentStatus.PRESCRIBED;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public Doctor getPrescribingDoctor() {
		return prescribingDoctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public AppointmentType getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getPrescribedDate() {
		return prescribedDate;
	}

	public LocalDate getPerformedDate() {
		return performedDate;
	}

	public Employee getPerformedBy() {
		return performedBy;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public String getNotes() {
		return notes;
	}

	// Выполнение назначения (может выполнить врач или медсестра)
	public void perform(Employee performer) {
		if (status != AppointmentStatus.PRESCRIBED && status != AppointmentStatus.IN_PROGRESS) {
			throw new IllegalStateException("Назначение не может быть выполнено в текущем статусе: " + status);
		}

		this.status = AppointmentStatus.COMPLETED;
		this.performedBy = performer;
		this.performedDate = LocalDate.now();
	}

	// Отмена назначения
	public void cancel() {
		if (status == AppointmentStatus.COMPLETED) {
			throw new IllegalStateException("Нельзя отменить выполненное назначение");
		}
		this.status = AppointmentStatus.CANCELLED;
	}

	// Начать выполнение назначения
	public void startProgress() {
		if (status == AppointmentStatus.PRESCRIBED) {
			this.status = AppointmentStatus.IN_PROGRESS;
		}
	}

	// Добавление заметок к назначению
	public void addNotes(String notes) {
		this.notes = notes;
	}
}

// Демонстрационный класс
public class HospitalSystem {
	public static void main(String[] args) {
		// Создание больницы
		Hospital hospital = new Hospital("Городская больница №1");

		// Создание врачей
		Doctor doctor1 = new Doctor("Иван", "Петров", "Терапевт");
		Doctor doctor2 = new Doctor("Мария", "Сидорова", "Хирург");

		// Создание медсестер
		Nurse nurse1 = new Nurse("Анна", "Иванова");
		Nurse nurse2 = new Nurse("Ольга", "Кузнецова");

		// Добавление персонала в больницу
		hospital.addDoctor(doctor1);
		hospital.addDoctor(doctor2);
		hospital.addNurse(nurse1);
		hospital.addNurse(nurse2);

		// Создание пациентов
		Patient patient1 = new Patient("Алексей", "Смирнов", LocalDate.now());
		Patient patient2 = new Patient("Елена", "Попова", LocalDate.now());

		// Назначение лечащих врачей
		patient1.setAttendingDoctor(doctor1);
		patient2.setAttendingDoctor(doctor2);

		// Госпитализация пациентов
		hospital.admitPatient(patient1);
		hospital.admitPatient(patient2);

		// Врач назначает лечение
		System.out.println("=== Назначения лечения ===");
		Appointment appointment1 = doctor1.prescribeTreatment(
				patient1, AppointmentType.MEDICATION,
				"Антибиотики: по 1 таблетке 3 раза в день",
				LocalDate.now());

		Appointment appointment2 = doctor1.prescribeTreatment(
				patient1, AppointmentType.PROCEDURE,
				"Физиотерапия: электрофорез",
				LocalDate.now());

		Appointment appointment3 = doctor2.prescribeTreatment(
				patient2, AppointmentType.OPERATION,
				"Аппендэктомия",
				LocalDate.now().plusDays(1));

		// Выполнение назначений
		System.out.println("\n=== Выполнение назначений ===");
		nurse1.performAppointment(appointment1);
		System.out.println("Медсестра " + nurse1.getFullName() +
				" выполнила назначение: " + appointment1.getDescription());

		doctor1.performAppointment(appointment2);
		System.out.println("Врач " + doctor1.getFullName() +
				" выполнил назначение: " + appointment2.getDescription());

		// Выписка пациента
		System.out.println("\n=== Выписка пациентов ===");
		doctor1.dischargePatient(patient1, PatientStatus.DISCHARGED_CURED,
				"Пациент полностью выздоровел");
		System.out.println("Пациент " + patient1.getFullName() +
				" выписан с диагнозом: " + patient1.getDischargeNotes());

		// Статус пациентов
		System.out.println("\n=== Статус пациентов ===");
		for (Patient patient : hospital.getPatients()) {
			System.out.println("Пациент: " + patient.getFullName() +
					", Статус: " + patient.getStatus() +
					", Лечащий врач: " +
					(patient.getAttendingDoctor() != null ? patient.getAttendingDoctor().getFullName()
							: "не назначен"));
		}

		// Активные назначения
		System.out.println("\n=== Активные назначения ===");
		for (Patient patient : hospital.getPatientsInTreatment()) {
			List<Appointment> activeAppointments = patient.getActiveAppointments();
			if (!activeAppointments.isEmpty()) {
				System.out.println("Пациент " + patient.getFullName() +
						" имеет активные назначения:");
				for (Appointment appointment : activeAppointments) {
					System.out.println("  - " + appointment.getDescription() +
							" (Статус: " + appointment.getStatus() + ")");
				}
			}
		}

		// Информация о выполненных назначениях

		System.out.println("\n=== Выполненные назначения ===");
		List<Appointment> completedAppointments = patient1.getCompletedAppointments();
		if (!completedAppointments.isEmpty()) {
			System.out.println("Пациент " + patient1.getFullName() +
					" имеет выполненные назначения:");
			for (Appointment appointment : completedAppointments) {
				System.out.println("  - " + appointment.getDescription() +
						" (Выполнил: " + appointment.getPerformedBy().getFullName() +
						", Дата: " + appointment.getPerformedDate() + ")");
			}
		}
	}
}
