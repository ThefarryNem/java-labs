import java.util.*;

class Archive {
	private static Archive instance;
	private List<GradeRecord> gradeRecords;

	private Archive() {
		gradeRecords = new ArrayList<>();
	}

	public static Archive getInstance() {
		if (instance == null) {
			instance = new Archive();
		}
		return instance;
	}

	public static void addGradeRecord(GradeRecord record) {
		getInstance().gradeRecords.add(record);
		System.out.println("Оценка добавлена в архив: " + record);
	}

	public static List<GradeRecord> getGradeRecords() {
		return new ArrayList<>(getInstance().gradeRecords);
	}

	public static void displayAllRecords() {
		System.out.println("\n=== АРХИВ ОЦЕНОК ===");
		for (GradeRecord record : getInstance().gradeRecords) {
			System.out.println(record);
		}
	}

	public static void displayStudentGrades(int studentId) {
		System.out.println("\n=== ОЦЕНКИ СТУДЕНТА ID:" + studentId + " ===");
		for (GradeRecord record : getInstance().gradeRecords) {
			if (record.getStudent().getId() == studentId) {
				System.out.println("Курс: " + record.getCourse().getCourseName() +
						", Оценка: " + record.getGrade() +
						", Дата: " + record.getDateRecorded());
			}
		}
	}

	public static double getStudentAverageGrade(int studentId) {
		List<GradeRecord> studentRecords = getInstance().gradeRecords.stream()
				.filter(record -> record.getStudent().getId() == studentId)
				.toList();

		if (studentRecords.isEmpty())
			return 0.0;

		double sum = 0;
		for (GradeRecord record : studentRecords) {
			sum += record.getGrade();
		}
		return sum / studentRecords.size();
	}
}
