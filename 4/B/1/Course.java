import java.util.*;

class Course {
	private String courseName;
	private String description;
	private int totalHours;
	private Teacher teacher;
	private List<StudentPerson> enrolledStudents;
	private boolean isCompleted;
	private Date startDate;
	private Date endDate;

	public Course(String courseName, String description, int totalHours, Teacher teacher) {
		this.courseName = courseName;
		this.description = description;
		this.totalHours = totalHours;
		this.teacher = teacher;
		this.enrolledStudents = new ArrayList<>();
		this.isCompleted = false;
		this.startDate = new Date(); // Дата создания курса
	}

	public String getCourseName() {
		return courseName;
	}

	public String getDescription() {
		return description;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public List<StudentPerson> getEnrolledStudents() {
		return enrolledStudents;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void enrollStudent(StudentPerson student) {
		if (!enrolledStudents.contains(student)) {
			enrolledStudents.add(student);
			student.enrollInCourse(this);
			System.out.println("Студент " + student.getName() + " записался на курс: " + courseName);
		}
	}

	public void completeCourse() {
		this.isCompleted = true;
		this.endDate = new Date();

		// Автоматическое выставление оценок (в реальной системе оценки выставлялись бы
		// преподавателем)
		for (StudentPerson student : enrolledStudents) {
			int grade = generateRandomGrade();
			GradeRecord record = new GradeRecord(student, this, grade);
			Archive.addGradeRecord(record);
		}
	}

	private int generateRandomGrade() {
		// Генерация случайной оценки от 3 до 5
		return (int) (Math.random() * 3) + 3;
	}

	@Override
	public String toString() {
		return "Course{name='" + courseName + "', teacher=" + teacher.getName() +
				", students=" + enrolledStudents.size() + ", completed=" + isCompleted + "}";
	}
}
