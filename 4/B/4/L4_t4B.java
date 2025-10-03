import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Интерфейс Person
interface Person {
	String getName();
}

// Абитуриент
class Applicant implements Person {
	private String name;
	private Faculty faculty;
	private List<Exam> exams = new ArrayList<>();
	private double averageScore;
	private boolean admitted;

	public Applicant(String name) {
		this.name = name;
	}

	public void registerToFaculty(Faculty faculty) {
		this.faculty = faculty;
		faculty.registerApplicant(this);
	}

	public void takeExam(Exam exam) {
		exams.add(exam);
	}

	public void calculateAverage() {
		if (exams.isEmpty()) {
			averageScore = 0;
		} else {
			double sum = 0;
			for (Exam e : exams) {
				sum += e.getScore();
			}
			averageScore = sum / exams.size();
		}
	}

	public double getAverageScore() {
		return averageScore;
	}

	public boolean isAdmitted() {
		return admitted;
	}

	public void setAdmitted(boolean admitted) {
		this.admitted = admitted;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Абитуриент " + name + ", средский балл: " + String.format("%.2f", averageScore) + ", зачислен: "
				+ admitted;
	}
}

// Факультет
class Faculty {
	private String name;
	private List<Applicant> applicants = new ArrayList<>();

	public Faculty(String name) {
		this.name = name;
	}

	public void registerApplicant(Applicant applicant) {
		applicants.add(applicant);
	}

	public void evaluateApplicants(double threshold) {
		for (Applicant a : applicants) {
			a.calculateAverage();
			a.setAdmitted(a.getAverageScore() >= threshold);
		}
	}
}

// Экзамен
class Exam {
	String subject;
	private int score;

	public Exam(String subject, int score) {
		this.subject = subject;
		this.score = score;
	}

	public int getScore() {
		return score;
	}
}

// Преподаватель
class Teacher implements Person {
	private String name;

	public Teacher(String name) {
		this.name = name;
	}

	public void setExamScore(Applicant applicant, String subject, int score) {
		Exam exam = new Exam(subject, score);
		applicant.takeExam(exam);
	}

	@Override
	public String getName() {
		return name;
	}
}

public class L4_t4B {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			// Ввод названия факультета
			System.out.print("Введите название факультета: ");
			String facultyName = scanner.nextLine();
			Faculty faculty = new Faculty(facultyName);

			// Ввод количества абитуриентов
			int applicantsCount = readInt(scanner, "Введите количество абитуриентов: ");

			List<Applicant> applicants = new ArrayList<>();
			for (int i = 0; i < applicantsCount; i++) {
				System.out.print("Введите имя абитуриента #" + (i + 1) + ": ");
				String name = scanner.nextLine();
				Applicant applicant = new Applicant(name);
				applicant.registerToFaculty(faculty);
				applicants.add(applicant);
			}

			// Ввод количества экзаменов
			int examsCount = readInt(scanner, "Введите количество экзаменов: ");

			// Создаем преподавателя
			Teacher teacher = new Teacher("Петров");

			// Для каждого абитуриента вводим оценки по экзаменам
			for (Applicant applicant : applicants) {
				System.out.println("Ввод оценок для абитуриента " + applicant.getName() + ":");
				for (int j = 0; j < examsCount; j++) {
					System.out.print("  Название предмета #" + (j + 1) + ": ");
					String subject = scanner.nextLine();
					int score = readInt(scanner, "  Оценка по предмету " + subject + ": ");
					teacher.setExamScore(applicant, subject, score);
				}
			}

			// Ввод порога для зачисления
			double threshold = readDouble(scanner, "Введите проходной балл: ");

			// Оценка абитуриентов
			faculty.evaluateApplicants(threshold);

			// Вывод результатов
			System.out.println("\nРезультаты:");
			for (Applicant applicant : applicants) {
				System.out.println(applicant);
			}

		} catch (Exception e) {
			System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	// Метод для безопасного чтения int
	public static int readInt(Scanner scanner, String prompt) {
		int value;
		while (true) {
			System.out.print(prompt);
			try {
				value = Integer.parseInt(scanner.nextLine());
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: введите целое число.");
			}
		}
	}

	// Метод для безопасного чтения double
	public static double readDouble(Scanner scanner, String prompt) {
		double value;
		while (true) {
			System.out.print(prompt);
			try {
				value = Double.parseDouble(scanner.nextLine());
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: введите числовое значение.");
			}
		}
	}
}
