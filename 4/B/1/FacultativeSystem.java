// Основной класс для запуска системы
public class FacultativeSystem {
	public static void main(String[] args) {
		// Создание тестовых данных
		Teacher teacher1 = new Teacher(1, "Иван Петров", "история");
		Teacher teacher2 = new Teacher(2, "Мария Сидорова", "математика");

		StudentPerson student1 = new StudentPerson(1, "Алексей Иванов");
		StudentPerson student2 = new StudentPerson(2, "Елена Смирнова");
		StudentPerson student3 = new StudentPerson(3, "Дмитрий Козлов");

		// Создание курсов
		Course historyCourse = teacher1.createCourse("История Древнего мира",
				"Изучение древних цивилизаций",
				30);
		Course mathCourse = teacher2.createCourse("Высшая математика",
				"Основы математического анализа",
				45);

		// Запись студентов на курсы
		historyCourse.enrollStudent(student1);
		historyCourse.enrollStudent(student2);

		mathCourse.enrollStudent(student2);
		mathCourse.enrollStudent(student3);

		// Завершение курсов и выставление оценок
		teacher1.finalizeCourse(historyCourse);
		teacher2.finalizeCourse(mathCourse);

		// Просмотр архива оценок
		Archive.displayAllRecords();

		// Просмотр оценок конкретного студента
		System.out.println("\nОценки студента " + student2.getName() + ":");
		Archive.displayStudentGrades(student2.getId());
	}

}
