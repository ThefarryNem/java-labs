import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentManagement {
	public static void main(String[] args) {
		Student[] students = {
				new Student(1, "Иванов", "Иван", "Иванович",
						LocalDate.of(2000, 5, 15), "ул. Ленина, 10", "+79123456789",
						"Информатика", 3, "ИВТ-301"),
				new Student(2, "Петрова", "Мария", "Сергеевна",
						LocalDate.of(2001, 8, 22), "ул. Пушкина, 25", "+79129876543",
						"Экономика", 2, "ЭК-202"),
				new Student(3, "Сидоров", "Алексей", "Петрович",
						LocalDate.of(1999, 12, 5), "ул. Гагарина, 17", "+79125554433",
						"Информатика", 3, "ИВТ-301"),
				new Student(4, "Козлова", "Елена", "Владимировна",
						LocalDate.of(2002, 3, 30), "ул. Мира, 8", "+79127778899",
						"Экономика", 1, "ЭК-101"),
				new Student(5, "Николаев", "Дмитрий", "Александрович",
						LocalDate.of(2000, 7, 18), "ул. Советская, 45", "+79123332211",
						"Механика", 4, "МХ-401"),
				new Student(6, "Федорова", "Ольга", "Игоревна",
						LocalDate.of(2001, 11, 12), "ул. Кирова, 33", "+79124445566",
						"Информатика", 2, "ИВТ-201")
		};

		StudentManagement management = new StudentManagement(students);

		management.printAllStudents();

		// a) Список студентов заданного факультета
		management.printStudentsByFaculty("Информатика");

		// b) Списки студентов для каждого факультета и курса
		System.out.println("Списки студентов по факультетам и курсам:");
		management.printStudentsByFacultyAndCourse();

		// c) Список студентов, родившихся после заданного года
		management.printStudentsBornAfterYear(2000);

		// d) Список учебной группы
		management.printStudentsByGroup("ИВТ-301");
	}

	private Student[] students;

	public StudentManagement(Student[] students) {
		this.students = students;
	}

	// a) Список студентов заданного факультета
	public void printStudentsByFaculty(String faculty) {
		System.out.println("Список студентов факультета '" + faculty + "':");
		boolean found = false;
		for (Student student : students) {
			if (student.getFaculty().equalsIgnoreCase(faculty)) {
				System.out.println(student);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Студентов на факультете '" + faculty + "' не найдено.");
		}
		System.out.println();
	}

	// b) Списки студентов для каждого факультета и курса
	public void printStudentsByFacultyAndCourse() {
		Map<String, Map<Integer, List<Student>>> facultyCourseMap = new HashMap<>();

		// Группируем студентов по факультетам и курсам
		for (Student student : students) {
			String faculty = student.getFaculty();
			int course = student.getCourse();

			facultyCourseMap.putIfAbsent(faculty, new HashMap<>());
			facultyCourseMap.get(faculty).putIfAbsent(course, new ArrayList<>());
			facultyCourseMap.get(faculty).get(course).add(student);
		}

		// Выводим результаты
		for (String faculty : facultyCourseMap.keySet()) {
			System.out.println("Факультет: " + faculty);
			Map<Integer, List<Student>> courseMap = facultyCourseMap.get(faculty);

			for (int course : courseMap.keySet()) {
				System.out.println("  Курс " + course + ":");
				for (Student student : courseMap.get(course)) {
					System.out.println("    " + student.getLastName() + " " +
							student.getFirstName() + " " +
							student.getMiddleName() + " - " +
							student.getGroup());
				}
			}
			System.out.println();
		}
	}

	// c) Список студентов, родившихся после заданного года
	public void printStudentsBornAfterYear(int year) {
		System.out.println("Список студентов, родившихся после " + year + " года:");
		boolean found = false;
		for (Student student : students) {
			if (student.getBirthDate().getYear() > year) {
				System.out.println(student);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Студентов, родившихся после " + year + " года, не найдено.");
		}
		System.out.println();
	}

	// d) Список учебной группы
	public void printStudentsByGroup(String group) {
		System.out.println("Список студентов группы '" + group + "':");
		boolean found = false;
		for (Student student : students) {
			if (student.getGroup().equalsIgnoreCase(group)) {
				System.out.println(student);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Студентов в группе '" + group + "' не найдено.");
		}
		System.out.println();
	}

	// Дополнительный метод: вывод всех студентов
	public void printAllStudents() {
		System.out.println("Полный список студентов:");
		for (Student student : students) {
			System.out.println(student);
		}
		System.out.println();
	}
}

class Student {
	private int id;
	private String lastName;
	private String firstName;
	private String middleName;
	private LocalDate birthDate;
	private String address;
	private String phone;
	private String faculty;
	private int course;
	private String group;

	// Конструкторы
	public Student() {
	}

	public Student(int id, String lastName, String firstName, String middleName,
			LocalDate birthDate, String address, String phone,
			String faculty, int course, String group) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.faculty = faculty;
		this.course = course;
		this.group = group;
	}

	// Геттеры
	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getFaculty() {
		return faculty;
	}

	public int getCourse() {
		return course;
	}

	public String getGroup() {
		return group;
	}

	// Сеттеры
	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return String.format("ID: %d, ФИО: %s %s %s, Дата рождения: %s, " +
				"Адрес: %s, Телефон: %s, Факультет: %s, Курс: %d, Группа: %s",
				id, lastName, firstName, middleName,
				birthDate.format(formatter), address, phone, faculty, course, group);
	}
}
